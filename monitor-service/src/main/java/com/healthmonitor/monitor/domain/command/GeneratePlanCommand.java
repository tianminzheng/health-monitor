package com.healthmonitor.monitor.domain.command;

import com.healthmonitor.domain.model.entity.HealthPlanProfile;

public class GeneratePlanCommand {

	private String monitorId;//健康监控编号	

	private HealthPlanProfile healthPlanProfile;//健康计划
		
	public GeneratePlanCommand(String monitorId) {
		super();
		this.monitorId = monitorId;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public HealthPlanProfile getHealthPlanProfile() {
		return healthPlanProfile;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public void setHealthPlanProfile(HealthPlanProfile healthPlanProfile) {
		this.healthPlanProfile = healthPlanProfile;
	}		
}



//public class CreatePlanCommand {
//
//	private String monitorId;//健康监控编号	
//
//	private String planId;
//	private String account;
//	private String doctor;
//	private String description;
//	private List<String> tasks;
//	
//	public CreatePlanCommand(String monitorId, String planId, String account, String doctor, String description,
//			List<String> tasks) {
//		super();
//		this.monitorId = monitorId;
//		this.planId = planId;
//		this.account = account;
//		this.doctor = doctor;
//		this.description = description;
//		this.tasks = tasks;
//	}
//
//	public String getMonitorId() {
//		return monitorId;
//	}
//
//	public String getPlanId() {
//		return planId;
//	}
//
//	public String getAccount() {
//		return account;
//	}
//
//	public String getDoctor() {
//		return doctor;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public List<String> getTasks() {
//		return tasks;
//	}
//}
