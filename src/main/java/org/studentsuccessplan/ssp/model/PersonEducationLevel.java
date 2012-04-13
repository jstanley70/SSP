package org.studentsuccessplan.ssp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.studentsuccessplan.ssp.model.reference.EducationLevel;

/**
 * Students may have zero or multiple Education Levels.
 * 
 * The PersonEducationLevel entity is an associative mapping between a student
 * (Person) and any Education Level information about them.
 * 
 * Non-student users should never have any assigned Education Levels.
 * 
 * @author jon.adams
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersonEducationLevel extends Auditable implements Serializable {

	private static final long serialVersionUID = -7969723552077396105L;

	@Column(length = 255)
	@Size(max = 255)
	private String description;

	private Integer lastYearAttended;

	private Integer highestGradeCompleted;

	private Integer graduatedYear;

	@Column(length = 128)
	@Size(max = 128)
	private String schoolName;

	/**
	 * Associated person. Changes to this Person are not persisted.
	 */
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "education_level_id", nullable = false, insertable = false, updatable = false)
	private EducationLevel educationLevel;

	public PersonEducationLevel() {
		super();
	}

	public PersonEducationLevel(Person person, EducationLevel educationLevel) {
		super();
		this.person = person;
		this.educationLevel = educationLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLastYearAttended() {
		return lastYearAttended;
	}

	public void setLastYearAttended(Integer lastYearAttended) {
		this.lastYearAttended = lastYearAttended;
	}

	public Integer getHighestGradeCompleted() {
		return highestGradeCompleted;
	}

	public void setHighestGradeCompleted(Integer highestGradeCompleted) {
		this.highestGradeCompleted = highestGradeCompleted;
	}

	public Integer getGraduatedYear() {
		return graduatedYear;
	}

	public void setGraduatedYear(Integer graduatedYear) {
		this.graduatedYear = graduatedYear;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * Overwrites simple properties with the parameter's properties.
	 * 
	 * @param source
	 *            Source to use for overwrites.
	 */
	public void overwrite(PersonEducationLevel source) {
		this.setDescription(source.getDescription());
	}
}