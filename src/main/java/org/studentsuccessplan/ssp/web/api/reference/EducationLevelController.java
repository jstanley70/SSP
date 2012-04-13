package org.studentsuccessplan.ssp.web.api.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.studentsuccessplan.ssp.model.reference.EducationLevel;
import org.studentsuccessplan.ssp.service.reference.EducationLevelService;
import org.studentsuccessplan.ssp.transferobject.reference.EducationLevelTO;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/reference/educationLevel")
public class EducationLevelController extends
		AbstractAuditableReferenceController<EducationLevel, EducationLevelTO> {

	@Autowired
	protected EducationLevelController(EducationLevelService service) {
		super(service, EducationLevel.class, EducationLevelTO.class);
	}
}