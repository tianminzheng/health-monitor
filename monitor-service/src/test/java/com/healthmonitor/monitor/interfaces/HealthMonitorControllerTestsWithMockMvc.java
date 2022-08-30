package com.healthmonitor.monitor.interfaces;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.healthmonitor.monitor.application.commandservice.HealthMonitorCommandService;
import com.healthmonitor.monitor.application.queryservice.HealthMonitorQueryService;
import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.interfaces.rest.HealthMonitorController;
import com.healthmonitor.monitor.interfaces.rest.dto.ApplyMonitorDTO;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthMonitorController.class)
public class HealthMonitorControllerTestsWithMockMvc {

	@Autowired
	private MockMvc mvc;

	@MockBean 
	private HealthMonitorCommandService healthMonitorCommandService;	

	@MockBean 
	private HealthMonitorQueryService healthMonitorQueryService;
	
	
	@Test
	public void testApplyMonitor() throws Exception {
		
		ApplyMonitorDTO applyMonitorDTO = buildApplyMonitorDTO();
		
		ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(applyMonitorDTO);
        
		//TODO
		this.mvc.perform(post("/monitors/application").content(requestJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetHealthMonitorById() throws Exception {

		HealthMonitor healthMonitor = initHealthMonitor();
		
		String monitorId = "monitorId";
		
		given(this.healthMonitorQueryService.findByMonitorId(monitorId)).willReturn(healthMonitor);
	
		this.mvc.perform(get("/monitors/" + monitorId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
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
