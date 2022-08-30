package com.healthmonitor.monitor.domain.respository;

import java.util.List;

import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;

public interface HealthMonitorRepository {
	
	void save(HealthMonitor healthMonitor);
	
	HealthMonitor findByMonitorId(String monitorId);
	
	List<HealthMonitor> findAll();
	
	List<MonitorId> findAllMonitorIds();
	
	HealthMonitor findByUserAccount(String account);
	
	void updateHealthMonitor(HealthMonitor healthMonitor);

}
