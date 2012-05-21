package org.jasig.ssp.web.api.reference;

import java.util.UUID;

import org.jasig.ssp.factory.TOFactory;
import org.jasig.ssp.factory.reference.JournalStepTOFactory;
import org.jasig.ssp.factory.reference.JournalTrackTOFactory;
import org.jasig.ssp.model.ObjectStatus;
import org.jasig.ssp.model.reference.JournalStep;
import org.jasig.ssp.model.reference.JournalTrack;
import org.jasig.ssp.service.AuditableCrudService;
import org.jasig.ssp.service.reference.JournalStepService;
import org.jasig.ssp.service.reference.JournalTrackService;
import org.jasig.ssp.transferobject.PagingTO;
import org.jasig.ssp.transferobject.reference.JournalStepTO;
import org.jasig.ssp.transferobject.reference.JournalTrackTO;
import org.jasig.ssp.util.sort.PagingWrapper;
import org.jasig.ssp.util.sort.SortingAndPaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/1/reference/journalTrack")
public class JournalTrackController
		extends
		AbstractAuditableReferenceController<JournalTrack, JournalTrackTO> {

	@Autowired
	protected transient JournalTrackService service;

	@Autowired
	protected transient JournalStepService journalStepService;

	@Override
	protected AuditableCrudService<JournalTrack> getService() {
		return service;
	}

	@Autowired
	protected transient JournalTrackTOFactory factory;

	@Autowired
	protected transient JournalStepTOFactory journalStepFactory;

	@Override
	protected TOFactory<JournalTrackTO, JournalTrack> getFactory() {
		return factory;
	}

	protected JournalTrackController() {
		super(JournalTrack.class, JournalTrackTO.class);
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JournalTrackController.class);

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/{id}/journalSteps/", method = RequestMethod.GET)
	public @ResponseBody
	PagingTO<JournalStepTO, JournalStep> getAllForJournalTrack(
			final @PathVariable UUID journalTrackId,
			final @RequestParam(required = false) ObjectStatus status,
			final @RequestParam(required = false) Integer start,
			final @RequestParam(required = false) Integer limit,
			final @RequestParam(required = false) String sort,
			final @RequestParam(required = false) String sortDirection)
			throws Exception {

		final JournalTrack journalTrack = getService()
				.get(journalTrackId);

		final PagingWrapper<JournalStep> data = journalStepService
				.getAllForJournalTrack(journalTrack,
						SortingAndPaging.createForSingleSort(status, start,
								limit, sort, sortDirection, "sortOrder"));

		return new PagingTO<JournalStepTO, JournalStep>(true,
				data.getResults(), journalStepFactory
						.asTOList(data.getRows()));
	}
}