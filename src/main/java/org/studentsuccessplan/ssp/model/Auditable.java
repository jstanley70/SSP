package org.studentsuccessplan.ssp.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * <p>
 * A base class that most entities in the system inherit, that allow the system
 * to track the most recent action to occur on an object. Auditable objects are
 * always persisted, so they additionally have an id property.
 * </p>
 * 
 * <p>
 * Common fields include a primary key identifier, creation/modification author
 * and timestamps, and object status.
 * </p>
 * 
 * <p>
 * {@link org.studentsuccessplan.ssp.dao.AuditableEntityInterceptor} will automatically
 * fill the creation/modification stamps as appropriate in the persistence
 * layer.
 * </p>
 * 
 * @author daniel.bower
 */
@MappedSuperclass
public class Auditable {
	@Id
	@Type(type = "pg-uuid")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2", parameters = { @Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
	private UUID id;

	/**
	 * Entity creation time stamp.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	/**
	 * Person that created this entity.
	 * 
	 * Set to load eagerly because
	 * {@link org.studentsuccessplan.ssp.transferobject.AuditableTO#fromModel(org.studentsuccessplan.ssp.model.Auditable)}
	 * pulls the ID from the full Person object and would require an extra
	 * lookup for every entity sent through the Controllers (or anything that
	 * uses {@link Auditable} transfer objects).
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, updatable = false)
	private Person createdBy;

	/**
	 * Most recent modification time stamp.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date modifiedDate;

	/**
	 * Last person to modify this entity.
	 * 
	 * Set to load eagerly because
	 * {@link org.studentsuccessplan.ssp.transferobject.AuditableTO#fromModel(org.studentsuccessplan.ssp.model.Auditable)}
	 * pulls the ID from the full Person object and would require an extra
	 * lookup for every entity sent through the Controllers (or anything that
	 * uses Auditable transfer objects).
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Person modifiedBy;

	/**
	 * Entity status.
	 * 
	 * Most commonly {@link ObjectStatus#ACTIVE} or {@link ObjectStatus#DELETED}
	 * , but other enum values possible.
	 */
	@Enumerated
	@Column(nullable = false)
	private ObjectStatus objectStatus;

	/**
	 * Empty constructor.
	 */
	public Auditable() {
	}

	public Auditable(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : new Date(createdDate.getTime());
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate == null ? null : new Date(
				createdDate.getTime());
	}

	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final Person createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : new Date(modifiedDate.getTime());
	}

	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : new Date(
				modifiedDate.getTime());
	}

	public Person getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(final Person modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ObjectStatus getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(final ObjectStatus objectStatus) {
		this.objectStatus = objectStatus;
	}

	@Override
	public String toString() {
		return id == null ? super.toString() : id.toString();
	}
}