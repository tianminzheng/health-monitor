package com.healthmonitor.monitor.interfaces.rest.assembler;

import com.healthmonitor.monitor.domain.command.GeneratePlanCommand;
import com.healthmonitor.monitor.interfaces.rest.dto.GeneratePlanDTO;

public class GeneratePlanCommandDTOAssembler {

	public static GeneratePlanCommand toCommandFromDTO(GeneratePlanDTO generatePlanDTO) {
		
		return new GeneratePlanCommand(generatePlanDTO.getMonitorId());
	}
}
