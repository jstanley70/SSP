package org.studentsuccessplan.ssp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentsuccessplan.ssp.dao.PersonDao;
import org.studentsuccessplan.ssp.model.ObjectStatus;
import org.studentsuccessplan.ssp.model.Person;
import org.studentsuccessplan.ssp.model.PersonChallenge;
import org.studentsuccessplan.ssp.model.PersonDemographics;
import org.studentsuccessplan.ssp.model.PersonEducationGoal;
import org.studentsuccessplan.ssp.model.PersonEducationLevel;
import org.studentsuccessplan.ssp.model.PersonEducationPlan;
import org.studentsuccessplan.ssp.model.PersonFundingSource;
import org.studentsuccessplan.ssp.service.ObjectNotFoundException;
import org.studentsuccessplan.ssp.service.PersonService;
import org.studentsuccessplan.ssp.service.reference.ChallengeService;
import org.studentsuccessplan.ssp.service.reference.ChildCareArrangementService;
import org.studentsuccessplan.ssp.service.reference.CitizenshipService;
import org.studentsuccessplan.ssp.service.reference.EducationLevelService;
import org.studentsuccessplan.ssp.service.reference.EthnicityService;
import org.studentsuccessplan.ssp.service.reference.FundingSourceService;
import org.studentsuccessplan.ssp.service.reference.MaritalStatusService;
import org.studentsuccessplan.ssp.service.reference.VeteranStatusService;
import org.studentsuccessplan.ssp.service.tool.IntakeService;
import org.studentsuccessplan.ssp.util.sort.SortingAndPaging;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao dao;

	@Autowired
	private MaritalStatusService maritalStatusService;

	@Autowired
	private EthnicityService ethnicityService;

	@Autowired
	private CitizenshipService citizenshipService;

	@Autowired
	private VeteranStatusService veteranStatusService;

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private EducationLevelService educationLevelService;

	@Autowired
	private FundingSourceService fundingSourceService;

	@Autowired
	private ChildCareArrangementService childCareArrangementService;

	@Override
	public List<Person> getAll(SortingAndPaging sAndP) {
		return dao.getAll(sAndP);
	}

	/**
	 * Retrieves the specified Person.
	 * 
	 * @param id
	 *            Required identifier for the Person to retrieve. Can not be
	 *            null.
	 * @exception ObjectNotFoundException
	 *                If the supplied identifier does not exist in the database.
	 * @return The specified Person instance.
	 */
	@Override
	public Person get(UUID id) throws ObjectNotFoundException {
		Person obj = dao.get(id);
		if (null == obj) {
			throw new ObjectNotFoundException(id, "Person");
		}

		return obj;
	}

	@Override
	public Person personFromUsername(String username)
			throws ObjectNotFoundException {
		Person obj = dao.fromUsername(username);
		if (null == obj) {
			throw new ObjectNotFoundException(
					"Could not find person with username: " + username);
		}
		return obj;
	}

	/**
	 * Creates a new Person instance based on the supplied model.
	 * 
	 * @param obj
	 *            Model instance
	 */
	@Override
	public Person create(Person obj) {
		return dao.save(obj);
	}

	/**
	 * Updates values of direct Person properties, but not any associated
	 * children or collections.
	 * 
	 * WARNING: Copies system-only (based on business logic rules) properties,
	 * so ensure that the incoming values have already been sanitized.
	 * 
	 * @param obj
	 *            Model instance from which to copy the simple properties.
	 * @see IntakeService
	 */
	@Override
	public Person save(Person obj) throws ObjectNotFoundException {
		Person current = get(obj.getId());

		current.setObjectStatus(obj.getObjectStatus());
		current.setFirstName(obj.getFirstName());
		current.setMiddleInitial(obj.getMiddleInitial());
		current.setLastName(obj.getLastName());
		current.setBirthDate(obj.getBirthDate());
		current.setPrimaryEmailAddress(obj.getPrimaryEmailAddress());
		current.setSecondaryEmailAddress(obj.getSecondaryEmailAddress());
		current.setUsername(obj.getUsername());
		current.setHomePhone(obj.getHomePhone());
		current.setWorkPhone(obj.getWorkPhone());
		current.setCellPhone(obj.getCellPhone());
		current.setAddressLine1(obj.getAddressLine1());
		current.setAddressLine2(obj.getAddressLine2());
		current.setCity(obj.getCity());
		current.setState(obj.getState());
		current.setZipCode(obj.getZipCode());
		current.setPhotoUrl(obj.getPhotoUrl());
		current.setSchoolId(obj.getSchoolId());

		current.setEnabled(obj.isEnabled());

		return dao.save(current);
	}

	/**
	 * Mark a Person as deleted.
	 * 
	 * Does not remove them from persistent storage, but marks their status flag
	 * to {@link ObjectStatus#DELETED}.
	 */
	@Override
	public void delete(UUID id) throws ObjectNotFoundException {
		Person current = get(id);

		if (null != current) {
			current.setObjectStatus(ObjectStatus.DELETED);
			save(current);
		}
	}

	protected void setDao(PersonDao dao) {
		this.dao = dao;
	}

	/**
	 * Overwrites simple properties with the parameter's properties. Does not
	 * include the Enabled property.
	 * 
	 * @param target
	 *            Target (original) to overwrite.
	 * @param source
	 *            Source to use for overwrites.
	 */
	@Override
	public void overwrite(Person target, Person source) {
		target.setFirstName(source.getFirstName());
		target.setMiddleInitial(source.getMiddleInitial());
		target.setLastName(source.getLastName());
		target.setBirthDate(source.getBirthDate());
		target.setPrimaryEmailAddress(source.getPrimaryEmailAddress());
		target.setSecondaryEmailAddress(source.getSecondaryEmailAddress());
		target.setUsername(source.getUsername());
		target.setHomePhone(source.getHomePhone());
		target.setWorkPhone(source.getWorkPhone());
		target.setCellPhone(source.getCellPhone());
		target.setAddressLine1(source.getAddressLine1());
		target.setAddressLine2(source.getAddressLine2());
		target.setCity(source.getCity());
		target.setState(source.getState());
		target.setZipCode(source.getZipCode());
		target.setPhotoUrl(source.getPhotoUrl());
		target.setSchoolId(source.getSchoolId());
	}

	/**
	 * Overwrites simple and collection properties with the parameter's
	 * properties, but not the Enabled property.
	 * 
	 * @param target
	 *            Target (original) to overwrite.
	 * @param source
	 *            Source to use for overwrites.
	 * @see #overwrite(Person, Person)
	 */
	@Override
	public void overwriteWithCollections(Person target, Person source)
			throws ObjectNotFoundException {
		overwrite(target, source);

		// Demographics
		if ((target.getDemographics() == null)
				&& (source.getDemographics() != null)) {
			target.setDemographics(new PersonDemographics());
		}

		if (target.getDemographics() != null) {
			if (source.getDemographics() == null) {
				// TODO Does the PersonDemographic instance have to be deleted
				// too? Or will Hibernate automatic orphan control catch it?
				target.setDemographics(null);
			} else {
				PersonDemographics demo = source.getDemographics();
				target.getDemographics().overwrite(
						demo,
						demo.getMaritalStatus() == null ? null
								: maritalStatusService.get(demo
										.getMaritalStatus().getId()),
						demo.getEthnicity() == null ? null : ethnicityService
								.get(demo.getEthnicity().getId()),
						demo.getCitizenship() == null ? null
								: citizenshipService.get(demo.getCitizenship()
										.getId()),
						demo.getVeteranStatus() == null ? null
								: veteranStatusService.get(demo
										.getVeteranStatus().getId()),
						demo.getCoach() == null ? null : get(demo.getCoach()
								.getId()),
						demo.getChildCareArrangement() == null ? null
								: childCareArrangementService.get(demo
										.getChildCareArrangement().getId()));
			}
		}

		// Education goal
		if ((target.getEducationGoal() == null)
				&& (source.getEducationGoal() != null)) {
			target.setEducationGoal(new PersonEducationGoal());
		}

		if (target.getEducationGoal() != null) {
			if (source.getEducationGoal() == null) {
				// TODO Does the PersonEducationGoal instance have to be deleted
				// too? Or will Hibernate automatic orphan control catch it?
				target.setEducationGoal(null);
			} else {
				target.getEducationGoal().overwrite(source.getEducationGoal());
			}
		}

		// Education plan
		if ((target.getEducationPlan() == null)
				&& (source.getEducationPlan() != null)) {
			target.setEducationPlan(new PersonEducationPlan());
		}

		if (target.getEducationPlan() != null) {
			if (source.getEducationPlan() == null) {
				// TODO Does the PersonEducationPlan instance have to be deleted
				// too? Or will Hibernate automatic orphan control catch it?
				target.setEducationPlan(null);
			} else {
				target.getEducationPlan().overwrite(
						source.getEducationPlan());
			}
		}

		// various sets
		overwriteWithCollectionsEducationLevels(target,
				source.getEducationLevels());
		overwriteWithCollectionsFundingSources(target,
				source.getFundingSources());
		overwriteWithCollectionsChallenges(target, source.getChallenges());
	}

	/**
	 * Overwrites the EducationLevels property.
	 * 
	 * @param target
	 *            Target (original) to overwrite.
	 * @param source
	 *            Source to use for overwrites.
	 * @see #overwrite(Person, Person)
	 */
	@Override
	public void overwriteWithCollectionsEducationLevels(Person target,
			Set<PersonEducationLevel> source) throws ObjectNotFoundException {
		Set<PersonEducationLevel> targetSet = target.getEducationLevels();
		Set<PersonEducationLevel> toRemove = new HashSet<PersonEducationLevel>();

		// iterate through set to overwrite updating matching items
		for (PersonEducationLevel targetItem : targetSet) {
			if (!source.contains(targetItem)) {
				toRemove.add(targetItem);
			}

			for (PersonEducationLevel sourceItem : source) {
				if (sourceItem.equals(targetItem)) {
					targetItem.overwrite(sourceItem);
				}
			}
		}

		// remove all items not in new set from the current set
		targetSet.removeAll(toRemove);

		// find all items that need added
		for (PersonEducationLevel sourceItem : source) {
			if (!targetSet.contains(sourceItem)) {
				PersonEducationLevel newItem = new PersonEducationLevel();
				// initialize the EducationLevel
				newItem.setPerson(target);
				newItem.setEducationLevel(educationLevelService.get(sourceItem
						.getEducationLevel().getId()));
				newItem.overwrite(sourceItem);
				targetSet.add(newItem);
			}
		}
	}

	/**
	 * Overwrites the FundingSources property.
	 * 
	 * @param target
	 *            Target (original) to overwrite.
	 * @param source
	 *            Source to use for overwrites.
	 * @see #overwrite(Person, Person)
	 */

	@Override
	public void overwriteWithCollectionsFundingSources(Person target,
			Set<PersonFundingSource> source) throws ObjectNotFoundException {
		Set<PersonFundingSource> targetSet = target.getFundingSources();
		Set<PersonFundingSource> toRemove = new HashSet<PersonFundingSource>();

		// iterate through set to overwrite updating matching items
		for (PersonFundingSource targetItem : targetSet) {
			if (!source.contains(targetItem)) {
				toRemove.add(targetItem);
			}

			for (PersonFundingSource sourceItem : source) {
				if (sourceItem.equals(targetItem)) {
					targetItem.overwrite(sourceItem);
				}
			}
		}

		// remove all items not in new set from the current set
		targetSet.removeAll(toRemove);

		// find all items that need added
		for (PersonFundingSource sourceItem : source) {
			if (!targetSet.contains(sourceItem)) {
				PersonFundingSource newItem = new PersonFundingSource();
				// initialize the FundingSource
				newItem.setPerson(target);
				newItem.setFundingSource(fundingSourceService.get(sourceItem
						.getFundingSource().getId()));
				newItem.overwrite(sourceItem);
				targetSet.add(newItem);
			}
		}
	}

	/**
	 * Overwrites the Challenges property.
	 * 
	 * @param target
	 *            Target (original) to overwrite.
	 * @param source
	 *            Source to use for overwrites.
	 * @see #overwrite(Person, Person)
	 */
	@Override
	public void overwriteWithCollectionsChallenges(Person target,
			Set<PersonChallenge> source) throws ObjectNotFoundException {
		Set<PersonChallenge> targetSet = target.getChallenges();
		Set<PersonChallenge> toRemove = new HashSet<PersonChallenge>();

		// iterate through set to overwrite updating matching items
		for (PersonChallenge targetItem : targetSet) {
			if (!source.contains(targetItem)) {
				toRemove.add(targetItem);
			}

			for (PersonChallenge sourceItem : source) {
				if (sourceItem.equals(targetItem)) {
					targetItem.overwrite(sourceItem);
				}
			}
		}

		// remove all items not in new set from the current set
		targetSet.removeAll(toRemove);

		// find all items that need added
		for (PersonChallenge sourceItem : source) {
			if (!targetSet.contains(sourceItem)) {
				PersonChallenge newItem = new PersonChallenge();
				// initialize the Challenge
				newItem.setPerson(target);
				newItem.setChallenge(sourceItem.getChallenge() == null ? null
						: challengeService.get(sourceItem.getChallenge()
								.getId()));
				newItem.overwrite(sourceItem);
				targetSet.add(newItem);
			}
		}
	}

	@Override
	public Person personFromUserId(String userId)
			throws ObjectNotFoundException {
		return dao.fromUserId(userId);
	}

	@Override
	public List<Person> peopleFromListOfIds(List<UUID> personIds,
			SortingAndPaging sAndP) {
		return dao.getPeopleInList(personIds, sAndP);
	}
}
