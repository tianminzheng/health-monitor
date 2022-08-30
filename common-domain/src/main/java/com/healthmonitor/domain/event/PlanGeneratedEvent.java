package com.healthmonitor.domain.event;

public class PlanGeneratedEvent extends MonitorEvent {

	private String planId;
	
	public PlanGeneratedEvent(String account, String planId, int healthScore) {
		super(account, healthScore);
		this.planId = planId;
	}

	public String getPlanId() {
		return planId;
	}
}
