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
package org.jasig.ssp.web.api; // NOPMD

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.jasig.ssp.dao.MessageDao;
import org.jasig.ssp.model.ObjectStatus;
import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.TemplateCourse;
import org.jasig.ssp.model.reference.ConfidentialityLevel;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.PersonService;
import org.jasig.ssp.service.impl.SecurityServiceInTestEnvironment;
import org.jasig.ssp.transferobject.PagedResponse;
import org.jasig.ssp.transferobject.PersonTO;
import org.jasig.ssp.transferobject.TemplateCourseTO;
import org.jasig.ssp.transferobject.TemplateLiteTO;
import org.jasig.ssp.transferobject.TemplateTO;
import org.jasig.ssp.transferobject.ServiceResponse;
import org.jasig.ssp.transferobject.TaskTO;
import org.jasig.ssp.transferobject.form.EmailPersonTasksForm;
import org.jasig.ssp.transferobject.reference.ConfidentialityLevelLiteTO;
import org.jasig.ssp.web.api.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * {@link PersonTaskController} tests
 * 
 * @author jon.adams
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../ControllerIntegrationTests-context.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class TemplateControllerIntegrationTest {

	@Autowired
	private transient TemplateController controller;

	@Autowired
	protected transient SessionFactory sessionFactory;

	@Autowired
	protected transient PersonService personService;

	private static final UUID PERSON_ID = UUID
			.fromString("f549ecab-5110-4cc1-b2bb-369cac854dea");


	@Autowired
	private transient SecurityServiceInTestEnvironment securityService;


	/**
	 * Setup the security service with the administrator user.
	 */
	@Before
	public void setUp() {
		securityService.setCurrent(new Person(Person.SYSTEM_ADMINISTRATOR_ID),
				"ROLE_PERSON_TASK_READ",
				"ROLE_PERSON_TASK_WRITE",
				"ROLE_PERSON_TASK_DELETE");
	}

	/**
	 * Test the {@link PersonController#get(UUID)} action.
	 * 
	 * @throws ObjectNotFoundException
	 *             If object could not be found.
	 * @throws ValidationException 
	 * @throws CloneNotSupportedException 
	 */
	
	public void testControllerGet() throws ObjectNotFoundException, ValidationException, CloneNotSupportedException {
		assertNotNull(
				"Controller under test was not initialized by the container correctly.",
				controller);

		controller.create(createTemplate());
		PagedResponse<TemplateLiteTO> result = controller.getSummary(null, null, null, null,null);

		assertNotNull(
				"Returned PersonTO from the controller should not have been null.",
				result);
		assertTrue(result.getRows().size() > 0);

	}
	
//	/**
//	 * Test the {@link PersonController#get(UUID)} action.
//	 * 
//	 * @throws ObjectNotFoundException
//	 *             If object could not be found.
//	 * @throws ValidationException 
//	 * @throws CloneNotSupportedException 
//	 */
//	
//	public void testControllerGetSummary() throws ObjectNotFoundException, ValidationException, CloneNotSupportedException {
//		assertNotNull(
//				"Controller under test was not initialized by the container correctly.",
//				controller);
//
//		controller.create(createTemplate());
//		PagedResponse<TemplateTO> result = controller.getSummary(PERSON_ID, ObjectStatus.ACTIVE, null, null);
//
//		assertNotNull(
//				"Returned PersonTO from the controller should not have been null.",
//				result);
//		assertTrue(result.getRows().size() > 0);
//		
//		assertTrue(result.getRows().iterator().next().getPersonId() != null);
//
//
//	}	
	
	
	public void testControllerCreate() throws ObjectNotFoundException, ValidationException, CloneNotSupportedException {
		assertNotNull(
				"Controller under test was not initialized by the container correctly.",
				controller);

		TemplateTO result = controller.create(createTemplate());
		
		assertNotNull(result.getId());

		assertNotNull(
				"Returned PersonTO from the controller should not have been null.",
				result);

	}	

	
	public void testControllerSave() throws ObjectNotFoundException, ValidationException, CloneNotSupportedException {
		assertNotNull(
				"Controller under test was not initialized by the container correctly.",
				controller);

		TemplateTO result = controller.create(createTemplate());
		
		assertNotNull(result.getId());
		
		sessionFactory.getCurrentSession().flush();
		
		controller.getTemplate(result.getId());

		assertNotNull(
				"Returned PersonTO from the controller should not have been null.",
				result);

	}
	
	public static TemplateTO createTemplate() {
		final TemplateTO obj = new TemplateTO();
		obj.setObjectStatus(ObjectStatus.ACTIVE);
		obj.setName("Some Name");
		obj.setOwnerId(PERSON_ID.toString());
		obj.setIsPrivate(false);
		TemplateCourseTO course = new TemplateCourseTO();
		course.setCourseCode("MAT");
		course.setCourseDescription("TEST");
		course.setCourseTitle("TEST");
		course.setFormattedCourse("TEST");
		course.setOrderInTerm(new Integer(1));
		course.setIsDev(true);
		course.setCreditHours(new BigDecimal(3.0));
		obj.getTemplateCourses().add(course);
		return obj;
	}

	/**
	 * Test that getLogger() returns the matching log class name for the current
	 * class under test.
	 */
	
	public void testLogger() {
		final Logger logger = controller.getLogger();
		assertEquals("Log class name did not match.", controller.getClass()
				.getName(), logger.getName());
	}
}