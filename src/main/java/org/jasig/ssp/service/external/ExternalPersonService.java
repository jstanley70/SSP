package org.jasig.ssp.service.external;

import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.external.ExternalPerson;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.util.sort.SortingAndPaging;

public interface ExternalPersonService extends
		ExternalDataService<ExternalPerson> {

	ExternalPerson getBySchoolId(String schoolId)
			throws ObjectNotFoundException;

	ExternalPerson getByUsername(String username)
			throws ObjectNotFoundException;

	/**
	 * Scheduled Service that synchronizes the Person and ExternalPerson tables
	 */
	void syncWithPerson();

	/**
	 * 
	 * @param sAndP
	 * @return the total number of person records
	 */
	long syncWithPerson(final SortingAndPaging sAndP);

	void updatePersonFromExternalPerson(final Person person,
			final ExternalPerson externalPerson);
}