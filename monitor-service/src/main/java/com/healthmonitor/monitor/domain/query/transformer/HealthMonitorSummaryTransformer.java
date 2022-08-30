package com.healthmonitor.monitor.domain.query.transformer;

import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.query.HealthMonitorSummary;

public class HealthMonitorSummaryTransformer {

	public static HealthMonitorSummary toHealthMonitorSummary(HealthMonitor healthMonitor) {
		HealthMonitorSummary healthMonitorSummary = new HealthMonitorSummary();
		healthMonitorSummary.setMonitorId(healthMonitor.getMonitorId().getMonitorId());
		healthMonitorSummary.setStatus(healthMonitor.getStatus().toString());
		healthMonitorSummary.setOrderNumber(healthMonitor.getOrder().getOrderNumber());
		healthMonitorSummary.setAccount(healthMonitor.getOrder().getAccount());
		if(healthMonitor.getPlan() != null) {
			healthMonitorSummary.setDoctor(healthMonitor.getPlan().getDoctor());
			healthMonitorSummary.setPlanId(healthMonitor.getPlan().getPlanId());
			healthMonitorSummary.setScore(healthMonitor.getScore().getScore());
		}
		
		return healthMonitorSummary;
	}
}
