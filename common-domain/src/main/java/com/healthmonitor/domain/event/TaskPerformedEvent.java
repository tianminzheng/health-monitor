package com.healthmonitor.domain.event;

public class TaskPerformedEvent extends MonitorEvent {
	
	private String taskId;
	
	public TaskPerformedEvent(String account, String taskId, int healthScore) {
		super(account, healthScore);
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}
}
