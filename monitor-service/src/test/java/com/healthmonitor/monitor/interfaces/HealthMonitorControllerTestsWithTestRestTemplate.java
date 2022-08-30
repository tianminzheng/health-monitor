package com.healthmonitor.monitor.interfaces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.healthmonitor.monitor.application.commandservice.HealthMonitorCommandService;
import com.healthmonitor.monitor.application.queryservice.HealthMonitorQueryService;
import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.interfaces.rest.dto.ApplyMonitorDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthMonitorControllerTestsWithTestRestTemplate {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@MockBean 
	private HealthMonitorCommandService healthMonitorCommandService;	

	@MockBean 
	private HealthMonitorQueryService healthMonitorQueryService;
	
	@Test
	public void testApplyMonitor() throws Exception {
		
		ApplyMonitorDTO applyMonitorDTO = buildApplyMonitorDTO();
		
		String monitorId = testRestTemplate.postForObject("/monitors/application", applyMonitorDTO, String.class);
		assertThat(monitorId).isNotNull();
		System.out.print(monitorId);
	}
	
	@Test
	public void testGetHealthMonitorById() throws Exception {

		HealthMonitor healthMonitor = initHealthMonitor();
		
		String monitorId = "monitorId";
		
		given(this.healthMonitorQueryService.findByMonitorId(monitorId)).willReturn(healthMonitor);
	
		HealthMonitor actual = testRestTemplate.getForObject("/monitors/" + monitorId, HealthMonitor.class);
		assertThat(actual.getMonitorId().getMonitorId()).isEqualTo(monitorId);
	}
	
	private ApplyMonitorDTO buildApplyMonitorDTO() {

		ApplyMonitorDTO applyMonitorDTO = new ApplyMonitorDTO(
				"account",
				"allergy",
				"injection",
				"surgery",
				"symptomDescription",
				"bodyPart",
				"timeDuration"
				);
		
		return applyMonitorDTO;
	}
	
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
