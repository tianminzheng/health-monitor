package com.healthmonitor.monitor.interfaces.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthmonitor.monitor.application.commandservice.HealthMonitorCommandService;
import com.healthmonitor.monitor.application.queryservice.HealthMonitorQueryService;
import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.command.GeneratePlanCommand;
import com.healthmonitor.monitor.domain.command.PerformTaskCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.monitor.domain.query.HealthMonitorSummary;
import com.healthmonitor.monitor.interfaces.rest.assembler.ApplyMonitorCommandDTOAssembler;
import com.healthmonitor.monitor.interfaces.rest.assembler.GeneratePlanCommandDTOAssembler;
import com.healthmonitor.monitor.interfaces.rest.assembler.PerformTaskCommandDTOAssembler;
import com.healthmonitor.monitor.interfaces.rest.dto.ApplyMonitorDTO;
import com.healthmonitor.monitor.interfaces.rest.dto.GeneratePlanDTO;
import com.healthmonitor.monitor.interfaces.rest.dto.PerformTaskDTO;

@RestController
@RequestMapping(value = "monitors")
public class HealthMonitorController {

	private HealthMonitorCommandService healthMonitorCommandService;
	private HealthMonitorQueryService healthMonitorQueryService;

	public HealthMonitorController(HealthMonitorCommandService healthMonitorCommandService,
			HealthMonitorQueryService healthMonitorQueryService) {
		this.healthMonitorCommandService = healthMonitorCommandService;
		this.healthMonitorQueryService = healthMonitorQueryService;
	}

	@GetMapping(value = "/")
	public  List<HealthMonitor> getAllHealthMonitor() {
		 List<HealthMonitor> healthMonitors = healthMonitorQueryService.findAll();
		return healthMonitors;
	}
	
	@GetMapping(value = "/{monitorId}")
	public HealthMonitor getHealthMonitorById(@PathVariable String monitorId) {
		HealthMonitor healthMonitor = healthMonitorQueryService.findByMonitorId(monitorId);
		return healthMonitor;
	}
	
	@GetMapping(value = "/summary/{monitorId}")
	public HealthMonitorSummary getHealthMonitorSummaryById(@PathVariable String monitorId) {
		HealthMonitorSummary healthMonitorSummary = healthMonitorQueryService.findSummaryByMonitorId(monitorId);
		return healthMonitorSummary;
	}

	@PostMapping(value = "/application")
	public MonitorId applyMonitor(@RequestBody ApplyMonitorDTO applyMonitorDTO) {
		ApplyMonitorCommand applyMonitorCommand = ApplyMonitorCommandDTOAssembler.toCommandFromDTO(applyMonitorDTO);
		MonitorId monitorId = healthMonitorCommandService.handleHealthMonitorApplication(applyMonitorCommand);
		return monitorId;
	}

	@PostMapping(value = "/plan")
	public void createPlan(@RequestBody GeneratePlanDTO createPlanDTO) {
		GeneratePlanCommand createPlanCommand = GeneratePlanCommandDTOAssembler.toCommandFromDTO(createPlanDTO);
		healthMonitorCommandService.handleHealthPlanGeneration(createPlanCommand);
	}
	
	@PostMapping(value = "/task")
	public void performPlan(@RequestBody PerformTaskDTO performTaskDTO) {
		PerformTaskCommand performTaskCommand = PerformTaskCommandDTOAssembler.toCommandFromDTO(performTaskDTO);
		healthMonitorCommandService.handleHealthTaskPerforming(performTaskCommand);
	}
}
