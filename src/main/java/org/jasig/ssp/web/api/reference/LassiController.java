package org.jasig.ssp.web.api.reference;

import org.jasig.ssp.factory.TOFactory;
import org.jasig.ssp.factory.reference.LassiTOFactory;
import org.jasig.ssp.model.reference.Lassi;
import org.jasig.ssp.service.AuditableCrudService;
import org.jasig.ssp.service.reference.LassiService;
import org.jasig.ssp.transferobject.reference.LassiTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/1/reference/lassi")
public class LassiController
		extends
		AbstractAuditableReferenceController<Lassi, LassiTO> {

	@Autowired
	protected transient LassiService service;

	@Override
	protected AuditableCrudService<Lassi> getService() {
		return service;
	}

	@Autowired
	protected transient LassiTOFactory factory;

	@Override
	protected TOFactory<LassiTO, Lassi> getFactory() {
		return factory;
	}

	protected LassiController() {
		super(Lassi.class, LassiTO.class);
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LassiController.class);

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
}