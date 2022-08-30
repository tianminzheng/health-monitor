package com.healthmonitor.monitor.applicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.valueobject.MonitorStatus;
import com.healthmonitor.monitor.domain.query.HealthMonitorSummary;
import com.healthmonitor.monitor.domain.respository.HealthMonitorRepository;
import com.healthmonitor.monitor.application.queryservice.HealthMonitorQueryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HealthMonitorQueryServiceTests {

	@MockBean
	private HealthMonitorRepository healthMonitorRepository;

	@Autowired
	private HealthMonitorQueryService HealthMonitorQueryService;
	
	
	@Test
	public void testFindByMonitorId() throws Exception {
		HealthMonitor healthMonitor = initHealthMonitor();
		
		Mockito.when(healthMonitorRepository.findByMonitorId("monitorId")).thenReturn(healthMonitor);
	
		HealthMonitor actual = HealthMonitorQueryService.findByMonitorId("monitorId");
		
		assertThat(actual.getMonitorId().getMonitorId()).isEqualTo("monitorId");
		assertThat(actual.getStatus()).isEqualTo(MonitorStatus.INITIALIZED);
		assertThat(actual.getScore().getScore()).isEqualTo(0);
	
	}
	
	@Test
	public void testFindSummaryByMonitorId() throws Exception {
		HealthMonitor healthMonitor = initHealthMonitor();
		
		Mockito.when(healthMonitorRepository.findByMonitorId("monitorId")).thenReturn(healthMonitor);
	
		HealthMonitorSummary actual = HealthMonitorQueryService.findSummaryByMonitorId("monitorId");
		
		assertThat(actual.getMonitorId()).isEqualTo("monitorId");
		assertThat(actual.getStatus()).isEqualTo(MonitorStatus.INITIALIZED.toString());
		assertThat(actual.getScore()).isEqualTo(0);
	
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
