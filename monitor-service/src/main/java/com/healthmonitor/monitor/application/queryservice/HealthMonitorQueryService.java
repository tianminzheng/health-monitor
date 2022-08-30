package com.healthmonitor.monitor.application.queryservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.monitor.domain.query.HealthMonitorSummary;
import com.healthmonitor.monitor.domain.query.transformer.HealthMonitorSummaryTransformer;
import com.healthmonitor.monitor.domain.respository.HealthMonitorRepository;

@Service
public class HealthMonitorQueryService {

	private HealthMonitorRepository healthMonitorRepository;

	public HealthMonitorQueryService(HealthMonitorRepository healthMonitorRepository) {

		this.healthMonitorRepository = healthMonitorRepository;
	}

	public HealthMonitor findByMonitorId(String monitorId) {
		return healthMonitorRepository.findByMonitorId(monitorId);
	}
	
	public HealthMonitorSummary findSummaryByMonitorId(String monitorId) {
		HealthMonitor healthMonitor = healthMonitorRepository.findByMonitorId(monitorId);
		
		return HealthMonitorSummaryTransformer.toHealthMonitorSummary(healthMonitor);
	}

	public List<HealthMonitor> findAll() {
		return healthMonitorRepository.findAll();
	}

	public List<MonitorId> findAllMonitorIds() {
		return healthMonitorRepository.findAllMonitorIds();
	}

	public HealthMonitor findByUserAccount(String account) {
		return healthMonitorRepository.findByUserAccount(account);
	}	
}
