package org.jasig.ssp.model.reference;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jasig.ssp.model.Auditable;

/**
 * ChallengeCategory links a challenge and a category.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ChallengeCategory extends Auditable implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	@JoinColumn(name = "challenge_id", nullable = false)
	private Challenge challenge;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Override
	protected int hashPrime() {
		return 53;
	}

	@Override
	final public int hashCode() {
		int result = hashPrime();

		// Auditable properties
		result *= getId() == null ? "id".hashCode() : getId().hashCode();
		result *= getObjectStatus() == null ? hashPrime() : getObjectStatus()
				.hashCode();

		result *= (challenge == null) || (null == challenge.getId()) ? "challenge"
				.hashCode()
				: challenge.getId().hashCode();
		result *= (category == null) || (null == category.getId()) ? "category"
				.hashCode() : category.getId()
				.hashCode();

		return result;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(final Challenge challenge) {
		this.challenge = challenge;
	};
}