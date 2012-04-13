package org.studentsuccessplan.ssp.service.reference.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.studentsuccessplan.ssp.dao.reference.EducationGoalDao;
import org.studentsuccessplan.ssp.model.ObjectStatus;
import org.studentsuccessplan.ssp.model.reference.EducationGoal;
import org.studentsuccessplan.ssp.service.ObjectNotFoundException;
import org.studentsuccessplan.ssp.service.reference.EducationGoalService;

@Service
@Transactional
public class EducationGoalServiceImpl implements EducationGoalService {

	@Autowired
	private EducationGoalDao dao;

	@Override
	public List<EducationGoal> getAll(ObjectStatus status, Integer firstResult,
			Integer maxResults, String sort, String sortDirection) {
		return dao.getAll(status, firstResult, maxResults, sort, sortDirection);
	}

	@Override
	public EducationGoal get(UUID id) throws ObjectNotFoundException {
		EducationGoal obj = dao.get(id);
		if (null == obj) {
			throw new ObjectNotFoundException(id, "EducationGoal");
		}

		return obj;
	}

	@Override
	public EducationGoal create(EducationGoal obj) {
		return dao.save(obj);
	}

	@Override
	public EducationGoal save(EducationGoal obj) throws ObjectNotFoundException {
		EducationGoal current = get(obj.getId());

		current.setName(obj.getName());
		current.setDescription(obj.getDescription());
		current.setObjectStatus(obj.getObjectStatus());

		return dao.save(current);
	}

	@Override
	public void delete(UUID id) throws ObjectNotFoundException {
		EducationGoal current = get(id);

		if (null != current) {
			current.setObjectStatus(ObjectStatus.DELETED);
			save(current);
		}
	}

	protected void setDao(EducationGoalDao dao) {
		this.dao = dao;
	}
}