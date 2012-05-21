package org.jasig.ssp.model.reference;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasig.ssp.model.Auditable;
import org.jasig.ssp.model.ObjectStatus;

/**
 * Reference entities must all share this abstract class, so they inherit the
 * identifier, name, and description properties.
 */
@MappedSuperclass
public abstract class AbstractReference extends Auditable {

	/**
	 * Name
	 * 
	 * Required, not null, max length 80 characters.
	 */
	@Column(name = "name", nullable = false, length = 80)
	@NotNull
	@NotEmpty
	@Size(max = 80)
	private String name;

	/**
	 * Description
	 * 
	 * Optional, null allowed, max length 64000 characters.
	 */
	@Column(nullable = true, length = 64000)
	@Size(max = 64000)
	private String description;

	/**
	 * Constructor
	 */
	public AbstractReference() {
		super();
		super.setObjectStatus(ObjectStatus.ACTIVE);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 */
	public AbstractReference(@NotNull final UUID id) {
		super();
		super.setId(id);
		super.setObjectStatus(ObjectStatus.ACTIVE);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 * @param name
	 *            Name; required; max 80 characters
	 */
	public AbstractReference(@NotNull final UUID id, @NotNull final String name) {
		super();
		super.setId(id);
		super.setObjectStatus(ObjectStatus.ACTIVE);
		this.name = name;
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 * @param name
	 *            Name; required; max 80 characters
	 * @param description
	 *            Description; max 64000 characters
	 */
	public AbstractReference(@NotNull final UUID id,
			@NotNull final String name, final String description) {
		super();
		super.setId(id);
		super.setObjectStatus(ObjectStatus.ACTIVE);
		this.name = name;
		this.description = description;
	}

	/**
	 * Gets the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            Name; required; max 80 characters
	 */
	public void setName(@NotNull final String name) {
		this.name = name;
	}

	/**
	 * Gets the public description
	 * 
	 * @return The public description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the public description
	 * 
	 * @param description
	 *            Public description; null allowed; max 64000 characters
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public int hashCode() { // NOPMD by jon.adams on 5/3/12 11:48 AM
		int result = hashPrime();

		// Auditable properties
		result *= getId() == null ? "id".hashCode() : getId().hashCode();
		result *= getObjectStatus() == null ? hashPrime() : getObjectStatus()
				.hashCode();

		// AbstractReference
		result *= StringUtils.isEmpty(name) ? "name".hashCode() : name
				.hashCode();
		result *= StringUtils.isEmpty(description) ? "description".hashCode()
				: description.hashCode();

		return result;
	}
}