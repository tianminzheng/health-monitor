package com.healthmonitor.domain.event;

public abstract class MonitorEvent extends DomainEvent {

	private String account;
	private int healthScore;
	
	public MonitorEvent(String account, int healthScore) {
		super();
		this.account = account;
		this.healthScore = healthScore;
	}

	public String getAccount() {
		return account;
	}

	public int getHealthScore() {
		return healthScore;
	}
}
