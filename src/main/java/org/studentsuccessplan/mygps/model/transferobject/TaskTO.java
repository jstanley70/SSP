package org.studentsuccessplan.mygps.model.transferobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.studentsuccessplan.ssp.model.AbstractTask;
import org.studentsuccessplan.ssp.model.CustomTask;
import org.studentsuccessplan.ssp.model.Task;

public class TaskTO {
	public static final String TASKTO_ID_PREFIX_DELIMITER = ":";
	public static final String TASKTO_ID_PREFIX_ACTION_PLAN_TASK = "ACT";
	public static final String TASKTO_ID_PREFIX_CUSTOM_ACTION_PLAN_TASK = "CUS";
	public static final String TASKTO_ID_PREFIX_SSP_ACTION_PLAN_TASK = "SSP";

	private String id;
	private String type;
	private String name;
	private String description;
	private String details;
	private Date dueDate;
	private boolean completed;
	private boolean deletable;
	private UUID challengeId;
	private UUID challengeReferralId;

	/**
	 * Empty constructor
	 */
	public TaskTO() {
	}

	public TaskTO(Task task) {
		setChallengeId(task.getChallenge().getId());
		setChallengeReferralId(task.getChallengeReferral().getId());
		setCompleted((task.getCompletedDate() != null) ? true : false);
		setDeletable(true);

		if (task.getChallengeReferral().getPublicDescription() != null) {
			setDescription(task.getChallengeReferral().getPublicDescription()
					.replaceAll("\\<.*?>", ""));
		}

		setDetails(task.getChallengeReferral().getPublicDescription());
		setDueDate(null);
		// :TODO how do determine between ACTION_PLAN_TASK and
		// SSP_ACTION_PLAN_TASK
		setId(TASKTO_ID_PREFIX_ACTION_PLAN_TASK + TASKTO_ID_PREFIX_DELIMITER
				+ task.getId());
		setId(TASKTO_ID_PREFIX_SSP_ACTION_PLAN_TASK
				+ TASKTO_ID_PREFIX_DELIMITER + task.getId());

		setName(task.getChallengeReferral().getName());

		// :TODO how do determine between ACTION_PLAN_TASK and
		// SSP_ACTION_PLAN_TASK
		setType(AbstractTask.ACTION_PLAN_TASK);
		// this.setType(AbstractTask.SSP_ACTION_PLAN_TASK);
	}

	public TaskTO(CustomTask customTask) {
		setChallengeId(null);
		setChallengeReferralId(null);
		setCompleted((customTask.getCompletedDate() != null) ? true : false);
		setDeletable(true);

		if (customTask.getDescription() != null) {
			setDescription(customTask.getDescription()
					.replaceAll("\\<.*?>", ""));
		}

		setDetails(customTask.getDescription());
		setDueDate(customTask.getDueDate());
		setId(TASKTO_ID_PREFIX_CUSTOM_ACTION_PLAN_TASK
				+ TASKTO_ID_PREFIX_DELIMITER + customTask.getId());
		setName(customTask.getName());
		setType(AbstractTask.CUSTOM_ACTION_PLAN_TASK);
	}

	public static List<TaskTO> tasksToTaskTOs(List<Task> tasks) {
		List<TaskTO> taskTOs = new ArrayList<TaskTO>();

		if (tasks != null && !tasks.isEmpty()) {
			for (Task task : tasks) {
				taskTOs.add(new TaskTO(task));
			}
		}

		return taskTOs;
	}

	public static List<TaskTO> customTasksToTaskTOs(List<CustomTask> customTasks) {
		List<TaskTO> taskTOs = new ArrayList<TaskTO>();

		if (customTasks != null && !customTasks.isEmpty()) {
			for (CustomTask customTask : customTasks) {
				taskTOs.add(new TaskTO(customTask));
			}
		}

		return taskTOs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getDueDate() {
		return dueDate == null ? null : new Date(dueDate.getTime());
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate == null ? null : new Date(dueDate.getTime());
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public UUID getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(UUID challengeId) {
		this.challengeId = challengeId;
	}

	public UUID getChallengeReferralId() {
		return challengeReferralId;
	}

	public void setChallengeReferralId(UUID challengeReferralId) {
		this.challengeReferralId = challengeReferralId;
	}
}