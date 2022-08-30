package com.healthmonitor.monitor.application.commandservice;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.command.GeneratePlanCommand;
import com.healthmonitor.monitor.domain.command.PerformTaskCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.domain.model.entity.HealthPlanProfile;
import com.healthmonitor.domain.model.entity.HealthTaskProfile;
import com.healthmonitor.monitor.domain.respository.HealthMonitorRepository;
import com.healthmonitor.monitor.integration.acl.AclHealthPlanService;
import com.healthmonitor.monitor.integration.acl.AclHealthTaskService;

@Service
public class HealthMonitorCommandService {

	private HealthMonitorRepository healthMonitorRepository;
	private AclHealthPlanService aclHealthPlanService;
	private AclHealthTaskService aclHealthTaskService;

	public HealthMonitorCommandService(HealthMonitorRepository healthMonitorRepository,
			AclHealthPlanService aclHealthPlanService, AclHealthTaskService aclHealthTaskService) {

		this.healthMonitorRepository = healthMonitorRepository;
		this.aclHealthPlanService = aclHealthPlanService;
		this.aclHealthTaskService = aclHealthTaskService;
	}

	public MonitorId handleHealthMonitorApplication(ApplyMonitorCommand applyMonitorCommand) {
		// 生成MonitorId
		String monitorId = "Monitor" + UUID.randomUUID().toString().toUpperCase();
		applyMonitorCommand.setMonitorId(monitorId);
		// 创建HealthMonitor
		HealthMonitor healthMonitor = new HealthMonitor(applyMonitorCommand);
		// 通过资源库持久化HealthMonitor
		healthMonitorRepository.save(healthMonitor);
		// 返回HealthMonitor的聚合标识符
		return healthMonitor.getMonitorId();
	}

	public void handleHealthPlanGeneration(GeneratePlanCommand generatePlanCommand) {
		// 根据MonitorId获取HealthMonitor
		HealthMonitor healthMonitor = healthMonitorRepository.findByMonitorId(generatePlanCommand.getMonitorId());

		// 根据healthMonitor调用Plan界限上下文获取HealthPlan并填充CreatePlanCommand	
		HealthPlanProfile healthPlanProfile = aclHealthPlanService.fetchHealthPlan(healthMonitor.getOrder());
		generatePlanCommand.setHealthPlanProfile(healthPlanProfile);
		
		// 针对HealthMonitor创建HealthPlan
		healthMonitor.generateHealthPlan(generatePlanCommand);
		// 通过资源库持久化HealthMonitor
		healthMonitorRepository.updateHealthMonitor(healthMonitor);
	}
	
	public void handleHealthTaskPerforming(PerformTaskCommand performTaskCommand) {
		// 根据MonitorId获取HealthMonitor
		HealthMonitor healthMonitor = healthMonitorRepository.findByMonitorId(performTaskCommand.getMonitorId());

		// 根据healthMonitor调用Task界限上下文获取HealthTask并填充PerformTaskCommand	
		HealthTaskProfile healthTaskProfile = aclHealthTaskService.fetchHealthTask(performTaskCommand.getTaskId());
		performTaskCommand.setHealthTaskProfile(healthTaskProfile);
		
		// 针对HealthMonitor执行HealthTask
		healthMonitor.performHealthTask(performTaskCommand);
		// 通过资源库持久化HealthMonitor
		healthMonitorRepository.updateHealthMonitor(healthMonitor);
	}
}
