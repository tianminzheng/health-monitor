package com.healthmonitor.monitor.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.monitor.domain.respository.HealthMonitorRepository;
import com.healthmonitor.monitor.infrastructure.repository.factory.HealthMonitorFactory;
import com.healthmonitor.monitor.infrastructure.repository.mapper.HealthMonitorMapper;
import com.healthmonitor.monitor.infrastructure.repository.po.HealthMonitorPO;

@Repository
public class HealthMonitorRepositoryImpl implements HealthMonitorRepository {

	private HealthMonitorMapper healthMonitorMapper;
	private HealthMonitorFactory healthMonitorFactory;

	public HealthMonitorRepositoryImpl(HealthMonitorMapper healthMonitorMapper,
			HealthMonitorFactory healthMonitorFactory) {
		this.healthMonitorMapper = healthMonitorMapper;
		this.healthMonitorFactory = healthMonitorFactory;
	}

	@Override
	public void save(HealthMonitor healthMonitor) {
		HealthMonitorPO healthMonitorPO = healthMonitorFactory.creatHealthMonitorPO(healthMonitor);
		healthMonitorMapper.save(healthMonitorPO);		
	}

	@Override
	public HealthMonitor findByMonitorId(String monitorId) {
		HealthMonitorPO healthMonitorPO = healthMonitorMapper.findByMonitorId(monitorId);
		return healthMonitorFactory.creatHealthMonitor(healthMonitorPO);
	}

	@Override
	public List<HealthMonitor> findAll() {
		List<HealthMonitorPO> healthMonitorPOs = healthMonitorMapper.findAll();
		List<HealthMonitor> healthMonitors = new ArrayList<HealthMonitor>();
		
		healthMonitorPOs.forEach((po) -> {
			HealthMonitor healthMonitor = healthMonitorFactory.creatHealthMonitor(po);
			healthMonitors.add(healthMonitor);
		});
		return healthMonitors;
	}

	@Override
	public List<MonitorId> findAllMonitorIds() {
		List<MonitorId> monitorIds = new ArrayList<MonitorId>();
		healthMonitorMapper.findAllMonitorIds().forEach((id) -> {
			monitorIds.add(new MonitorId(id));
		});
		
		return monitorIds;
	}

	@Override
	public HealthMonitor findByUserAccount(String account) {
		HealthMonitorPO healthMonitorPO = healthMonitorMapper.findByUserAccount(account);
		return healthMonitorFactory.creatHealthMonitor(healthMonitorPO);
	}

	@Override
	public void updateHealthMonitor(HealthMonitor healthMonitor) {
		//查询-设值-保存
		HealthMonitorPO healthMonitorPO = healthMonitorMapper.findByMonitorId(healthMonitor.getMonitorId().getMonitorId());
		if(healthMonitor.getPlan() != null) {
			 healthMonitorPO.setPlanId(healthMonitor.getPlan().getPlanId());
			 healthMonitorPO.setDoctor(healthMonitor.getPlan().getDoctor());
			 healthMonitorPO.setDescription(healthMonitor.getPlan().getDescription());
		}
		healthMonitorPO.setScore(healthMonitor.getScore().getScore());
		
		healthMonitorMapper.save(healthMonitorPO);
	}

}
