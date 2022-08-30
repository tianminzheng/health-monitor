package com.healthmonitor.domain.event;

public class MonitorInitializedEvent extends MonitorEvent {

	private String monitorId;
	
	public MonitorInitializedEvent(String account, String monitorId, int healthScore) {
		super(account, healthScore);
		this.monitorId = monitorId;
	}

	public String getMonitorId() {
		return monitorId;
	}
}
