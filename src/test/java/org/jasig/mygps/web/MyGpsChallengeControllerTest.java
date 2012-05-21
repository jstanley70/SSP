package org.jasig.mygps.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.jasig.ssp.model.reference.Challenge;
import org.jasig.ssp.service.reference.ChallengeService;
import org.jasig.ssp.transferobject.reference.ChallengeTO;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class MyGpsChallengeControllerTest {

	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(MyGpsChallengeControllerTest.class);

	private MyGpsChallengeController controller;
	private ChallengeService manager;

	@Before
	public void setUp() {
		manager = createMock(ChallengeService.class);
		controller = new MyGpsChallengeController(manager);
	}

	@Test
	public void search() {
		String query = "ABCDEFG";
		List<Challenge> searchResults = Lists.newArrayList();
		List<ChallengeTO> searchResultsTO = Lists.newArrayList();
		expect(manager.challengeSearch(query)).andReturn(searchResults);

		replay(manager);

		try {
			List<ChallengeTO> response = controller.search(query);

			verify(manager);
			assertEquals(searchResultsTO, response);
		} catch (Exception e) {
			fail("controller error");
		}
	}
}