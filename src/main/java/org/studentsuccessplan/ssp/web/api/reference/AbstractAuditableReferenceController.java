package org.studentsuccessplan.ssp.web.api.reference;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.studentsuccessplan.ssp.factory.TransferObjectListFactory;
import org.studentsuccessplan.ssp.model.ObjectStatus;
import org.studentsuccessplan.ssp.model.reference.AbstractReference;
import org.studentsuccessplan.ssp.service.AuditableCrudService;
import org.studentsuccessplan.ssp.transferobject.ServiceResponse;
import org.studentsuccessplan.ssp.transferobject.reference.AbstractReferenceTO;
import org.studentsuccessplan.ssp.web.api.RestController;
import org.studentsuccessplan.ssp.web.api.validation.ValidationException;

/**
 * Basic REST command implementation to responds with standard transfer objects
 * in JSON format.
 * 
 * @author jon.adams
 * 
 * @param <T>
 *            Model type
 * @param <TO>
 *            Transfer object type that handles the model type T.
 */
@Controller
public abstract class AbstractAuditableReferenceController<T extends AbstractReference, TO extends AbstractReferenceTO<T>>
		extends RestController<TO> {

	/**
	 * Logger
	 */
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractAuditableReferenceController.class);

	/**
	 * Service that handles the business logic for the implementing type for T.
	 */
	protected AuditableCrudService<T> service;

	/**
	 * Transfer object factory to create new instances of the specific TO for
	 * extending classes.
	 */
	private final TransferObjectListFactory<TO, T> listFactory;

	/**
	 * Model class type
	 */
	protected Class<T> persistentClass;

	/**
	 * Transfer object class type
	 */
	protected Class<TO> transferObjectClass;

	/**
	 * Construct a controller with the specified specific service and types.
	 * 
	 * @param service
	 *            Service that handles the business logic for the implementing
	 *            type for T.
	 * @param persistentClass
	 *            Model class type
	 * @param transferObjectClass
	 *            Transfer object class type
	 */
	protected AbstractAuditableReferenceController(
			AuditableCrudService<T> service, Class<T> persistentClass,
			Class<TO> transferObjectClass) {
		this.service = service;
		this.persistentClass = persistentClass;
		this.transferObjectClass = transferObjectClass;
		this.listFactory =
				TransferObjectListFactory.newFactory(transferObjectClass);
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	List<TO> getAll(@RequestParam(required = false) ObjectStatus status,
			@RequestParam(required = false) Integer start,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortDirection)
			throws Exception {

		return listFactory.toTOList(service.getAll(
				status == null ? ObjectStatus.ACTIVE : status, start, limit,
				sort, sortDirection));
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	TO get(@PathVariable UUID id) throws Exception {
		T model = service.get(id);
		if (model != null) {
			TO out = this.transferObjectClass.newInstance();
			out.fromModel(model);
			return out;
		} else {
			return null;
		}
	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody
	TO create(@Valid @RequestBody TO obj) throws Exception {
		if (obj.getId() != null) {
			throw new ValidationException(
					"It is invalid to send a reference entity with an ID to the create method. Did you mean to use the save method instead?");
		}

		T model = obj.asModel();

		if (null != model) {
			T createdModel = service.create(model);
			if (null != createdModel) {
				TO out = this.transferObjectClass.newInstance();
				out.fromModel(createdModel);
				return out;
			}
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	TO save(@PathVariable UUID id, @Valid @RequestBody TO obj) throws Exception {
		if (id == null) {
			throw new ValidationException(
					"You submitted a citizenship without an id to the save method.  Did you mean to create?");
		}

		T model = obj.asModel();
		model.setId(id);

		T savedT = service.save(model);
		if (null != savedT) {
			TO out = this.transferObjectClass.newInstance();
			out.fromModel(savedT);
			return out;
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
		LOGGER.error("Error: ", e);
		return new ServiceResponse(false, e.getMessage());
	}
}