/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.ssp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.jasig.ssp.dao.AbstractPlanDao;
import org.jasig.ssp.model.AbstractPlan;
import org.jasig.ssp.model.AbstractPlanCourse;
import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.SubjectAndBody;
import org.jasig.ssp.model.TermCourses;
import org.jasig.ssp.model.external.ExternalCourseRequisite;
import org.jasig.ssp.model.external.ExternalStudentTranscriptCourse;
import org.jasig.ssp.model.external.RequisiteCode;
import org.jasig.ssp.model.external.Term;
import org.jasig.ssp.model.reference.Config;
import org.jasig.ssp.service.AbstractAuditableCrudService;
import org.jasig.ssp.service.AbstractPlanService;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.PersonService;
import org.jasig.ssp.service.SecurityService;
import org.jasig.ssp.service.external.ExternalCourseRequisiteService;
import org.jasig.ssp.service.external.ExternalCourseService;
import org.jasig.ssp.service.external.ExternalStudentTranscriptCourseService;
import org.jasig.ssp.service.external.TermService;
import org.jasig.ssp.service.reference.ConfigService;
import org.jasig.ssp.service.reference.MessageTemplateService;
import org.jasig.ssp.transferobject.AbstractPlanCourseTO;
import org.jasig.ssp.transferobject.AbstractPlanOutputTO;
import org.jasig.ssp.transferobject.AbstractPlanTO;
import org.jasig.ssp.transferobject.PlanTO;
import org.jasig.ssp.transferobject.TermNoteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * 
 * @author tony.arland
 */
@Transactional
public  abstract class AbstractPlanServiceImpl<T extends AbstractPlan,
		TO extends AbstractPlanTO<T>, TOO extends AbstractPlanOutputTO<T,TO>>
	extends  AbstractAuditableCrudService<T>
	implements AbstractPlanService<T,TO, TOO> {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private TermService termService;
	
	@Autowired
	private MessageTemplateService messageTemplateService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ExternalCourseService courseService;
	
	@Autowired
	private ExternalCourseRequisiteService courseRequisiteService;
	
	@Autowired
	private ExternalStudentTranscriptCourseService studentTranscriptService;

	@Autowired
	private transient ConfigService configService;
	
	@Override
	public T save(T obj) {
		return getDao().save(obj);
	}
	
	@Override
	public T get(@NotNull final UUID id) throws ObjectNotFoundException {
		final T obj = getDao().get(id);
		if(obj == null)
			throw new ObjectNotFoundException(id, this.getClass().getName());
		return obj;

	}
	
	@Override
	protected abstract AbstractPlanDao<T> getDao();

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@Override
	public T copyAndSave(T model, Person newOwner) throws CloneNotSupportedException {
		return getDao().cloneAndSave(model, newOwner);
	}

	protected String getInstitutionName() {
		return configService.getByNameNullOrDefaultValue("inst_name");
	}
	
	@Override
	@Transactional(readOnly=true)
	public SubjectAndBody createMatrixOutput(final TO outputPlan) throws ObjectNotFoundException {
		
		//TODO eventually find a better  way to set the student when in context
		Person student = outputPlan instanceof PlanTO ?  personService.get(UUID.fromString(((PlanTO)outputPlan).getPersonId())) : null;
		Person owner = getPersonService().get(UUID.fromString(outputPlan.getOwnerId()));
		
		
		List<TermCourses<T,TO>> courses = collectTermCourses(outputPlan);
		Float totalPlanCreditHours = calculateTotalPlanHours(courses);
		 
		SubjectAndBody subjectAndBody = getMessageTemplateService().createMapPlanMatrixOutput(student, owner, outputPlan, totalPlanCreditHours, courses, getInstitutionName());
		return subjectAndBody;
	}
	@Override
	@Transactional(readOnly=true)
	public SubjectAndBody createFullOutput(TOO planOutput) throws ObjectNotFoundException
	{
		TO plan = planOutput.getNonOutputTO();
		//TODO eventually find a better way to set the student when in context
		Person student = plan instanceof PlanTO ?  personService.get(UUID.fromString(((PlanTO)plan).getPersonId())) : null;
		Person owner = personService.get(UUID.fromString(plan.getOwnerId()));
		
		
		List<TermCourses<T,TO>> courses = collectTermCourses(plan);
		Float totalPlanCreditHours = calculateTotalPlanHours(courses);
		Float totalPlanDevHours = calculateTotalPlanDevHours(courses);
		
		SubjectAndBody subjectAndBody = messageTemplateService.createMapPlanFullOutput(student, owner, 
				planOutput, 
				totalPlanCreditHours, 
				totalPlanDevHours, 
				courses,
				getInstitutionName());
		return subjectAndBody;
	}
	
	@Override
	@Transactional(readOnly=true)
	public TO validate(TO model) throws ObjectNotFoundException{

		List<? extends AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>> courses = model.getCourses();
		Map<String, List<String>> coursesByTerm = new HashMap<String, List<String>>();
		Map<String, AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>> courseCodeCourse = new HashMap<String,AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>>();
		for(AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>  course: courses){
			course.setIsValidInTerm(true);//Set all courses valid in term
			courseCodeCourse.put(course.getCourseCode(), course);
			if(coursesByTerm.containsKey(course.getTermCode())){
				coursesByTerm.get(course.getTermCode()).add(course.getCourseCode());
			}else {
				List<String> termCourses = Lists.newArrayList(course.getCourseCode());
				coursesByTerm.put(course.getTermCode(), termCourses);
			}
		}
		for(String termCode: coursesByTerm.keySet()){
			List<String> validCourseCodes = getCourseService().getValidCourseCodesForTerm(termCode, coursesByTerm.get(termCode));
			List<String> courseCodesTerm = coursesByTerm.get(termCode);
			for(String courseCode:courseCodesTerm){
				if(validCourseCodes.contains(courseCode)){
					continue;
				}else{
					  AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>> course = courseCodeCourse.get(courseCode);
					  course.setIsValidInTerm(false);
					  model.setIsValid(false);
					  course.setInvalidReasons("Course: " + course.getFormattedCourse() + " is not currently offered in the selected term.");
				}
			}
		}
		model = validatePrerequisites(model);
		return model;
	}
	
	public TO validatePrerequisites(TO model) throws ObjectNotFoundException{
		List<? extends AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>> courses = model.getCourses();
		List<String> requiringCourseCodes = new ArrayList<String>();
		for(AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>  course: courses){
			requiringCourseCodes.add(course.getCourseCode());
		}
		
		List<String> transcriptedCourseCodeCourse = new ArrayList<String>();
		Map<String, ExternalStudentTranscriptCourse> transcriptedCoursesByFormattedCourseCode = new HashMap<String,ExternalStudentTranscriptCourse>();

		String studentSchoolId = getSchoolIdPlannedFor(model);

		if(StringUtils.isNotBlank(studentSchoolId)){
			List<ExternalStudentTranscriptCourse> transcriptedCourses = studentTranscriptService.getTranscriptsBySchoolId(studentSchoolId);
			for(ExternalStudentTranscriptCourse transcriptedCourse:transcriptedCourses){
				transcriptedCourseCodeCourse.add(transcriptedCourse.getFormattedCourse());
				transcriptedCoursesByFormattedCourseCode.put(transcriptedCourse.getFormattedCourse(), transcriptedCourse);
			}
		}
		
		Map<String, Term> termCodeTerm = new HashMap<String, Term>();
		Map<String, String> courseCodeTermCode = new HashMap<String,String>();
		Map<String, AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>> courseCodeCourse = new HashMap<String,AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>>();
		List<ExternalCourseRequisite> requisiteCourses = getCourseRequisiteService().getRequisitesForCourses(requiringCourseCodes);
		for(AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>>  course: courses){
			
			if(!termCodeTerm.containsKey(course.getTermCode())){
				termCodeTerm.put(course.getTermCode(), getTermService().getByCode(course.getTermCode()));
			}
			courseCodeTermCode.put(course.getCourseCode(), course.getTermCode());
			courseCodeCourse.put(course.getCourseCode(), course);
			
			// This section sets isTranscript and duplicateOfTranscript since course may be from client.
			if(transcriptedCoursesByFormattedCourseCode.containsKey(course.getFormattedCourse())){
				ExternalStudentTranscriptCourse transcriptCourse = transcriptedCoursesByFormattedCourseCode.get(course.getFormattedCourse());
				course.setIsTranscript(true);
				if(!transcriptCourse.getTermCode().equals(course.getTermCode())){
					course.setDuplicateOfTranscript(true);
				}
			}
		}
		
		for(ExternalCourseRequisite  requisiteCourse: requisiteCourses){
			if(transcriptedCourseCodeCourse.contains(requisiteCourse.getRequiredFormattedCourse())){
				continue;
			}
			AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>> requiringCourse = courseCodeCourse.get(requisiteCourse.getRequiringCourseCode());
			AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>> requiredCourse = courseCodeCourse.get(requisiteCourse.getRequiredCourseCode());
			if(courseCodeTermCode.containsKey(requisiteCourse.getRequiredCourseCode())){
				String requiredTermCode = courseCodeTermCode.get(requisiteCourse.getRequiredCourseCode());
				String requireingTermCode = courseCodeTermCode.get(requisiteCourse.getRequiringCourseCode());
				Term requiredTerm = termCodeTerm.get(requiredTermCode);
				Term requireingTerm = termCodeTerm.get(requireingTermCode);
				requiringCourse.setInvalidReasons(null);
				if(requisiteCourse.getRequisiteCode().equals(RequisiteCode.CO)){
					if(!requiredTerm.getCode().equals(requireingTerm.getCode())){
						requiringCourse.setHasCorequisites(false);
						model.setIsValid(false);
						requiringCourse.setInvalidReasons(" Corequisite " + requiredCourse.getFormattedCourse() + " is not in same term.");
					}		
				}else if(requisiteCourse.getRequisiteCode().equals(RequisiteCode.PRE_CO)){
					if(requiredTerm.getCode().equals(requireingTerm.getCode())){
						continue;
					}
					if(requireingTerm.getStartDate().before(requiredTerm.getStartDate())){
						requiringCourse.setHasCorequisites(false);
						requiringCourse.setHasPrerequisites(false);
						model.setIsValid(false);
						requiringCourse.setInvalidReasons(" Pre/Corequisite " + requiredCourse.getFormattedCourse() + " is not in previous term.");
					}
				}else if(requisiteCourse.getRequisiteCode().equals(RequisiteCode.PRE)){
					if(requiredTerm.getCode().equals(requireingTerm.getCode())){
						requiringCourse.setHasPrerequisites(false);
						model.setIsValid(false);
						requiringCourse.setInvalidReasons(" Prerequisite " + requiredCourse.getFormattedCourse() + " is in same term.");
					}else if(requireingTerm.getStartDate().before(requiredTerm.getStartDate())){
						requiringCourse.setHasPrerequisites(false);
						model.setIsValid(false);
						requiringCourse.setInvalidReasons(" Prerequisite " + requiredCourse.getFormattedCourse() + " is not in previous term.");
					}
				}
				
			}else{
				requiringCourse.setHasCorequisites(false);
				requiringCourse.setHasPrerequisites(false);
				model.setIsValid(false);
				requiringCourse.setInvalidReasons(" Pre/co requisite + " + requisiteCourse.getRequiredCourseCode() + " is missing.");
			}
		}
		
		return model;
	}

	protected String getSchoolIdPlannedFor(TO model) throws ObjectNotFoundException {
		UUID personId = getPersonIdPlannedFor(model);
		return personId == null ? null : personService.getSchoolIdForPersonId(personId);
	}

	protected abstract UUID getPersonIdPlannedFor(TO model);

	private Float calculateTotalPlanDevHours(List<TermCourses<T, TO>> courses) {
		Float totalDevCreditHours = new Float(0);
		for(TermCourses<T,TO> termCourses : courses){
			totalDevCreditHours = totalDevCreditHours + termCourses.getTotalDevCreditHours();
		}
		return totalDevCreditHours;
	}

	private Float calculateTotalPlanHours(List<TermCourses<T,TO>> courses) {
		Float totalPlanCreditHours = new Float(0);
		for(TermCourses<T,TO> termCourses : courses){
			totalPlanCreditHours = totalPlanCreditHours + termCourses.getTotalCreditHours();
		}
		return totalPlanCreditHours;
	}

	private List<TermCourses<T,TO>> collectTermCourses(TO plan) throws ObjectNotFoundException {
		Map<String,TermCourses<T,TO>> semesterCourses = new HashMap<String, TermCourses<T,TO>>();
		List<TermNoteTO> termNotes = plan.getTermNotes();
		List<Term> futureTerms = getTermService().getCurrentAndFutureTerms().subList(0, 6);
		for(AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>> course : plan.getCourses()){				
			if(!semesterCourses.containsKey(course.getTermCode())){
				Term term = getTermService().getByCode(course.getTermCode());
				
				
				TermCourses<T,TO> termCourses = new TermCourses<T,TO>(term);
				for(TermNoteTO termNote:termNotes){
					if(termNote.getTermCode().equals(term.getCode())){
						termCourses.setContactNotes(termNote.getContactNotes());
						termCourses.setStudentNotes(termNote.getStudentNotes());
						termCourses.setIsImportant(termNote.getIsImportant());
					}
				}
				course.setPlanToOffer(getPlanToOfferTerms(course, futureTerms));
				termCourses.addCourse(course);
				semesterCourses.put(term.getCode(), termCourses);
			}else{
				semesterCourses.get(course.getTermCode()).addCourse(course);
			}
		}
		List<TermCourses<T,TO>> courses =  Lists.newArrayList(semesterCourses.values());
	
		return courses;
	}
	
	

	private String getPlanToOfferTerms(
			AbstractPlanCourseTO<T, ? extends AbstractPlanCourse<T>> course,
			List<Term> futureTerms) {
		String planToOffer = "";
		Integer offeredTerms = 0;
		for(Term offeredTerm:futureTerms){
			if(getCourseService().validateCourseForTerm(course.getCourseCode(), offeredTerm.getCode())){
				planToOffer = planToOffer + offeredTerm.getName() + " ";
				offeredTerms = offeredTerms + 1;
			}
			if(offeredTerms >= 4)
				break;
		}
		return planToOffer;	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public MessageTemplateService getMessageTemplateService() {
		return messageTemplateService;
	}

	public void setMessageTemplateService(MessageTemplateService messageTemplateService) {
		this.messageTemplateService = messageTemplateService;
	}

	public TermService getTermService() {
		return termService;
	}

	public void setTermService(TermService termService) {
		this.termService = termService;
	}

	public ExternalCourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ExternalCourseService courseService) {
		this.courseService = courseService;
	}
	
	public ExternalCourseRequisiteService getCourseRequisiteService() {
		return courseRequisiteService;
	}

	public void setCourseService(ExternalCourseRequisiteService courseRequisiteService) {
		this.courseRequisiteService = courseRequisiteService;
	}
}