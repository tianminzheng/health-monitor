package com.healthmonitor.monitor.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.command.PerformTaskCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.domain.model.entity.HealthTaskProfile;
import com.healthmonitor.monitor.domain.model.valueobject.MonitorStatus;

@ExtendWith(SpringExtension.class)
public class HealthMonitorTests {

	@Test
	public void testHealthMonitorCreation() throws Exception {		
		
		HealthMonitor healthMonitor = initHealthMonitor();

		assertThat(healthMonitor.getMonitorId().getMonitorId()).isEqualTo("monitorId");
		assertThat(healthMonitor.getStatus()).isEqualTo(MonitorStatus.INITIALIZED);
		assertThat(healthMonitor.getScore().getScore()).isEqualTo(0);
	}	

	@Test
	public void testHealthTaskPerforming() throws Exception {
		
		HealthMonitor healthMonitor = initHealthMonitor();
		
		PerformTaskCommand performTaskCommand = new PerformTaskCommand("monitorId", "taskId");
		HealthTaskProfile healthTaskProfile = new HealthTaskProfile("taskId", "taskName", "description", 100);
		performTaskCommand.setHealthTaskProfile(healthTaskProfile);
	
		//第1次执行HealthTask
		healthMonitor.performHealthTask(performTaskCommand);
		assertThat(healthMonitor.getScore().getScore()).isEqualTo(100);	
		
		PerformTaskCommand performTaskCommand2 = new PerformTaskCommand("monitorId", "taskId2");
		HealthTaskProfile healthTaskProfile2 = new HealthTaskProfile("taskId2", "taskName", "description", 50);
		performTaskCommand2.setHealthTaskProfile(healthTaskProfile2);

		//第2次执行HealthTask
		healthMonitor.performHealthTask(performTaskCommand2);
		assertThat(healthMonitor.getScore().getScore()).isEqualTo(150);	
	}
	
	//初始化一个HealthMonitor
	private HealthMonitor initHealthMonitor() {
		
		ApplyMonitorCommand applyMonitorCommand = new ApplyMonitorCommand(
				"account",
				"allergy",
				"injection",
				"surgery",
				"symptomDescription",
				"bodyPart",
				"timeDuration"
				);
		applyMonitorCommand.setMonitorId("monitorId");
		
		HealthMonitor healthMonitor = new HealthMonitor(applyMonitorCommand);
		
		return healthMonitor;
	}
}
