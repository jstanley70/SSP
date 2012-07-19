package org.jasig.ssp.dao.external;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.jasig.ssp.model.ObjectStatus;
import org.jasig.ssp.model.external.Term;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.util.sort.SortingAndPaging;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Term DAO
 * 
 * @author jon.adams
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../dao-testConfig.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class TermDaoTest {

	@Autowired
	private transient TermDao dao;

	@Test
	public void getAll() throws ObjectNotFoundException {
		final Collection<Term> all = dao.getAll(
				new SortingAndPaging(ObjectStatus.ACTIVE)).getRows();
		assertFalse("GetAll should have returned some data.", all.isEmpty());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetWithNull() throws ObjectNotFoundException {
		final Term term = dao.getByCode(null);
		assertNull(
				"Invalid identifier passed to get() should have returned null.",
				term);
	}

	@Ignore
	// otherwise would need to adjust this every term...
	@Test
	public void getCurrentTerm() {
		final Term term = dao.getCurrentTerm();
		assertEquals("expected Fall2012", "FA12", term.getCode());
	}
}