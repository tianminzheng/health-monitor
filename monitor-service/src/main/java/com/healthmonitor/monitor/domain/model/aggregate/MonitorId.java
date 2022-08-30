package com.healthmonitor.monitor.domain.model.aggregate;

/**
 * HealthMonitor聚合的聚合标识符
 */
public class MonitorId {

	private String monitorId;	
	
	public MonitorId() {
		super();
	}

	public MonitorId(String monitorId) {
		super();
		this.monitorId = monitorId;
	}

	public String getMonitorId() {
		return monitorId;
	}		
}
