package com.healthmonitor.monitor.applicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.healthmonitor.monitor.application.commandservice.HealthMonitorCommandService;
import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.command.PerformTaskCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.domain.model.entity.HealthTaskProfile;
import com.healthmonitor.monitor.domain.respository.HealthMonitorRepository;
import com.healthmonitor.monitor.integration.acl.AclHealthPlanService;
import com.healthmonitor.monitor.integration.acl.AclHealthTaskService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HealthMonitorCommandServiceTests {

	@MockBean
	private HealthMonitorRepository healthMonitorRepository;

	@MockBean
	private AclHealthPlanService aclHealthPlanService;

	@MockBean
	private AclHealthTaskService aclHealthTaskService;

	@Autowired
	private HealthMonitorCommandService healthMonitorCommandService;

	@Test
	public void testHandleHealthMonitorApplication() throws Exception {
		ApplyMonitorCommand applyMonitorCommand = createApplyMonitorCommand();

		MonitorId monitorId = healthMonitorCommandService.handleHealthMonitorApplication(applyMonitorCommand);

		assertThat(monitorId.getMonitorId()).isNotNull();
	}

	@Test
	public void testHandleHealthTaskPerforming() throws Exception {
		PerformTaskCommand performTaskCommand = new PerformTaskCommand("monitorId", "taskId");
		HealthTaskProfile healthTaskProfile = new HealthTaskProfile("taskId", "taskName", "description", 100);
		performTaskCommand.setHealthTaskProfile(healthTaskProfile);

		HealthMonitor healthMonitor = targetHealthMonitor();
		
		//模拟HealthMonitorRepository
		Mockito.when(healthMonitorRepository.findByMonitorId("monitorId")).thenReturn(healthMonitor);
		//模拟AclHealthTaskService
		Mockito.when(aclHealthTaskService.fetchHealthTask("taskId")).thenReturn(healthTaskProfile);

		healthMonitorCommandService.handleHealthTaskPerforming(performTaskCommand);

		assertThat(healthMonitor.getScore().getScore()).isEqualTo(100);
	}

	private ApplyMonitorCommand createApplyMonitorCommand() {

		ApplyMonitorCommand applyMonitorCommand = new ApplyMonitorCommand("account", "allergy", "injection", "surgery",
				"symptomDescription", "bodyPart", "timeDuration");

		return applyMonitorCommand;
	}

	private HealthMonitor targetHealthMonitor() {

		ApplyMonitorCommand applyMonitorCommand = new ApplyMonitorCommand("account", "allergy", "injection", "surgery",
				"symptomDescription", "bodyPart", "timeDuration");
		applyMonitorCommand.setMonitorId("monitorId");

		HealthMonitor healthMonitor = new HealthMonitor(applyMonitorCommand);

		return healthMonitor;
	}

}
