package org.jasig.ssp.transferobject.reference;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.jasig.ssp.model.Auditable;
import org.jasig.ssp.model.reference.AbstractReference;
import org.jasig.ssp.transferobject.AbstractAuditableTO;
import org.jasig.ssp.transferobject.NamedTO;

/**
 * Transfer object with basic properties for all reference types.
 * 
 * @param <T>
 *            Any {@link Auditable} model type.
 */
public abstract class AbstractReferenceTO<T extends AbstractReference>
		extends AbstractAuditableTO<T> implements NamedTO {

	@NotNull
	@NotEmpty
	private String name;

	private String description;

	/**
	 * Empty constructor
	 */
	public AbstractReferenceTO() {
		super();
	}

	/**
	 * Construct a fully initialized instance.
	 * 
	 * @param id
	 *            the identifier
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public AbstractReferenceTO(final UUID id, final String name,
			final String description) {
		super(id);
		this.name = name;
		this.description = description;
	}

	@Override
	public void from(final T model) {
		if (model == null) {
			throw new IllegalArgumentException("Model can not be null.");
		}

		super.from(model);

		name = model.getName();
		description = model.getDescription();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * 
	 * @param description
	 *            the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
}