package org.jasig.ssp.web.api.reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.util.service.stub.Stubs;
import org.jasig.ssp.util.service.stub.Stubs.PersonFixture;
import org.jasig.ssp.util.service.stub.Stubs.ProgramStatusFixture;
import org.jasig.ssp.util.service.stub.Stubs.StudentTypeFixture;
import org.jasig.ssp.util.service.stub.Stubs.TermFixture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class EarlyAlertStudentOutcomeReportControllerIntegrationTest extends
		AbstractReportControllerIntegrationTest {



	@Autowired
	private transient EarlyAlertStudentOutcomeReportController controller;


	@Test
	public void testGetEarlyAlertStudentOutcomeReportWithFilters()
			throws IOException, ObjectNotFoundException, JRException {
		final MockHttpServletResponse response = new MockHttpServletResponse();
		
		controller.getEarlyAlertStudentOutcomeReport(response, 
				null, 
				PersonFixture.COACH_1.id(), 
				Lists.newArrayList(StudentTypeFixture.ILP.id()),
				ProgramStatusFixture.ACTIVE.id(), 
				Lists.newArrayList(Stubs.SpecialServiceGroupFixture.TEST_SSG.id()), 
				TermFixture.FALL_2012.code(), 
				null, 
				null,
				"csv");

		// "body" is the actual results and the header that describes its columns.
		// This is as opposed to rows which precede the header, which describe
		// the filtering criteria
		final List<String> expectedReportBodyLines = new ArrayList<String>(4);
		expectedReportBodyLines.add("FIRST,MIDDLE,LAST,STUDENT ID,EMAIL(SCHOOL),RESPONSES,NO RESPONSE,WAITING ,COUNSELOR");
		expectedReportBodyLines.add(",,,,,,,,");
		expectReportBodyLines(expectedReportBodyLines, response, null);
	}

	@Test
	public void testGetEarlyAlertStudentOutcomeReportWithNoFilter()
			throws IOException, ObjectNotFoundException, JRException {

		sessionFactory.getCurrentSession().flush();

		final MockHttpServletResponse response = new MockHttpServletResponse();

		controller.getEarlyAlertStudentOutcomeReport(response, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null,
				null,
				"csv");
		final List<String> expectedReportBodyLines = new ArrayList<String>(4);
		//TODO Understand why no filters does not bring back a result!
		expectedReportBodyLines.add("FIRST,MIDDLE,LAST,STUDENT ID,EMAIL(SCHOOL),RESPONSES,NO RESPONSE,WAITING ,COUNSELOR");
		expectedReportBodyLines.add("James,A,Gosling,student0,test@sinclair.edu,1,0,1,Alan Turing");
		expectedReportBodyLines.add("test,Mumford,coach1student0,coach1student0,coach1student0@unicon.net,1,0,1,test coach1");
		expectedReportBodyLines.add("test,Mumford,coach1student1,coach1student1,coach1student1@unicon.net,2,0,2,test coach1");
		expectedReportBodyLines.add("test,Mumford,coach1student4,coach1student4,coach1student4@unicon.net,5,0,4,test coach1");

		expectReportBodyLines(expectedReportBodyLines, response, null);
	}
	
	@Override
	protected Predicate<String> afterHeader() {
		return afterLineContaining("Early Alert Student Outcome Report");
	}

}