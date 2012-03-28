package edu.sinclair.ssp.web.api.reference;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.sinclair.ssp.factory.TransferObjectListFactory;
import edu.sinclair.ssp.model.ObjectStatus;
import edu.sinclair.ssp.model.reference.ChallengeReferral;
import edu.sinclair.ssp.service.reference.ChallengeReferralService;
import edu.sinclair.ssp.transferobject.ServiceResponse;
import edu.sinclair.ssp.transferobject.reference.ChallengeReferralTO;
import edu.sinclair.ssp.web.api.RestController;
import edu.sinclair.ssp.web.api.validation.ValidationException;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/reference/challengeReferral")
public class ChallengeReferralController extends
		RestController<ChallengeReferralTO> {

	private static final Logger logger = LoggerFactory
			.getLogger(ChallengeReferralController.class);

	@Autowired
	private ChallengeReferralService service;

	private TransferObjectListFactory<ChallengeReferralTO, ChallengeReferral> listFactory = new TransferObjectListFactory<ChallengeReferralTO, ChallengeReferral>(
			ChallengeReferralTO.class);

	/**
	 * Retrieve every instance in the database filtered by the supplied status.
	 * 
	 * @param status
	 *            Filter by this status.
	 * @exception Exception
	 *                Generic exception thrown if there were any errors.
	 * @return All entities in the database filtered by the supplied status.
	 */
	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	List<ChallengeReferralTO> getAll(
			@RequestParam(required = false) ObjectStatus status)
			throws Exception {
		if (status == null) {
			status = ObjectStatus.ACTIVE;
		}
		return listFactory.toTOList(service.getAll(status));
	}

	/**
	 * Retrieve every instance in the database filtered by the supplied status.
	 * 
	 * @param status
	 *            Filter by this status.
	 * @param firstResult
	 *            First result (0-based index) to return. Parameter must be a
	 *            positive, non-zero integer.
	 * @param maxResults
	 *            Maximum number of results to return. Parameter must be a
	 *            positive, non-zero integer.
	 * @param sortExpression
	 *            Property name and ascending/descending keyword. If null or
	 *            empty string, the default sort order will be used. Example
	 *            sort expression: <code>propertyName ASC</code>
	 * @exception Exception
	 *                Generic exception thrown if there were any errors.
	 * @return All entities in the database filtered by the supplied status.
	 */
	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	List<ChallengeReferralTO> getAll(
			@RequestParam(required = false) ObjectStatus status,
			int firstResult, int maxResults,
			@RequestParam(required = false) String sortExpression)
			throws Exception {
		if (status == null) {
			status = ObjectStatus.ACTIVE;
		}

		return listFactory.toTOList(service.getAll(status, firstResult,
				maxResults, sortExpression));
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ChallengeReferralTO get(@PathVariable UUID id) throws Exception {
		ChallengeReferral model = service.get(id);
		if (model != null) {
			return new ChallengeReferralTO(model);
		} else {
			return null;
		}
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody
	ChallengeReferralTO create(@Valid @RequestBody ChallengeReferralTO obj)
			throws Exception {
		if (obj.getId() != null) {
			throw new ValidationException(
					"You submitted a challengeReferral with an id to the create method.  Did you mean to save?");
		}

		ChallengeReferral model = obj.asModel();

		if (null != model) {
			ChallengeReferral createdModel = service.create(model);
			if (null != createdModel) {
				return new ChallengeReferralTO(createdModel);
			}
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ChallengeReferralTO save(@PathVariable UUID id,
			@Valid @RequestBody ChallengeReferralTO obj)
			throws Exception {
		if (id == null) {
			throw new ValidationException(
					"You submitted a challengeReferral without an id to the save method.  Did you mean to create?");
		}

		ChallengeReferral model = obj.asModel();
		model.setId(id);

		ChallengeReferral savedChallengeReferral = service.save(model);
		if (null != savedChallengeReferral) {
			return new ChallengeReferralTO(savedChallengeReferral);
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ServiceResponse delete(@PathVariable UUID id) throws Exception {
		service.delete(id);
		return new ServiceResponse(true);
	}

	@Override
	@ExceptionHandler(Exception.class)
	public @ResponseBody
	ServiceResponse handle(Exception e) {
		logger.error("Error: ", e);
		return new ServiceResponse(false, e.getMessage());
	}
}
