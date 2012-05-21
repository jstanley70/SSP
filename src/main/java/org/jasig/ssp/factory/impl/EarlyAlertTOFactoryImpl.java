package org.jasig.ssp.factory.impl;

import java.util.HashSet;

import org.jasig.ssp.dao.EarlyAlertDao;
import org.jasig.ssp.factory.AbstractAuditableTOFactory;
import org.jasig.ssp.factory.EarlyAlertTOFactory;
import org.jasig.ssp.model.EarlyAlert;
import org.jasig.ssp.model.reference.EarlyAlertReason;
import org.jasig.ssp.model.reference.EarlyAlertSuggestion;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.PersonService;
import org.jasig.ssp.service.reference.CampusService;
import org.jasig.ssp.service.reference.EarlyAlertReasonService;
import org.jasig.ssp.service.reference.EarlyAlertSuggestionService;
import org.jasig.ssp.transferobject.EarlyAlertTO;
import org.jasig.ssp.transferobject.reference.EarlyAlertReasonTO;
import org.jasig.ssp.transferobject.reference.EarlyAlertSuggestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * EarlyAlert transfer object factory
 * 
 * @author jon.adams
 * 
 */
@Service
@Transactional(readOnly = true)
public class EarlyAlertTOFactoryImpl extends
		AbstractAuditableTOFactory<EarlyAlertTO, EarlyAlert>
		implements EarlyAlertTOFactory {

	/**
	 * Construct an EarlyAlert transfer object factory with the specific class
	 * types for use by the super class methods.
	 */
	public EarlyAlertTOFactoryImpl() {
		super(EarlyAlertTO.class, EarlyAlert.class);
	}

	@Autowired
	private transient EarlyAlertDao dao;

	@Autowired
	private transient CampusService campusService;

	@Autowired
	private transient PersonService personService;

	@Autowired
	private transient EarlyAlertReasonService earlyAlertReasonService;

	@Autowired
	private transient EarlyAlertSuggestionService earlyAlertSuggestionService;

	@Override
	protected EarlyAlertDao getDao() {
		return dao;
	}

	@Override
	public EarlyAlert from(final EarlyAlertTO tObject)
			throws ObjectNotFoundException {
		final EarlyAlert model = super.from(tObject);

		model.setCourseName(tObject.getCourseName());
		model.setCourseTitle(tObject.getCourseTitle());
		model.setEmailCC(tObject.getEmailCC());
		model.setCampus(tObject.getCampusId() == null ? null : campusService
				.get(tObject.getCampusId()));
		model.setEarlyAlertReasonOtherDescription(tObject
				.getEarlyAlertSuggestionOtherDescription());
		model.setComment(tObject.getComment());
		model.setClosedDate(tObject.getClosedDate());
		model.setClosedById(tObject.getClosedById());

		if (tObject.getPersonId() != null) {
			model.setPerson(personService.get(tObject.getPersonId()));
		}

		model.setEarlyAlertReasonIds(new HashSet<EarlyAlertReason>());
		if (tObject.getEarlyAlertReasonIds() != null) {
			for (EarlyAlertReasonTO obj : tObject.getEarlyAlertReasonIds()) {
				model.getEarlyAlertReasonIds().add(
						earlyAlertReasonService.load(obj.getId()));
			}
		}

		model.setEarlyAlertSuggestionIds(new HashSet<EarlyAlertSuggestion>());
		if (tObject.getEarlyAlertSuggestionIds() != null) {
			for (EarlyAlertSuggestionTO obj : tObject
					.getEarlyAlertSuggestionIds()) {
				model.getEarlyAlertSuggestionIds().add(
						earlyAlertSuggestionService.load(obj.getId()));
			}
		}

		return model;
	}
}