package org.studentsuccessplan.ssp.web.api.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.studentsuccessplan.ssp.model.reference.VeteranStatus;
import org.studentsuccessplan.ssp.service.reference.VeteranStatusService;
import org.studentsuccessplan.ssp.transferobject.reference.VeteranStatusTO;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/reference/veteranStatus")
public class VeteranStatusController extends
		AbstractAuditableReferenceController<VeteranStatus, VeteranStatusTO> {

	@Autowired
	protected VeteranStatusController(VeteranStatusService service) {
		super(service, VeteranStatus.class, VeteranStatusTO.class);
	}
}