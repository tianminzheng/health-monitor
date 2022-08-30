package com.healthmonitor.monitor.domain.query;

public class HealthMonitorSummary {

	private String monitorId;
	private String orderNumber;//检测单编号
	private String account;//用户账户
	private String planId;//计划编号
	private String doctor;//医生
	private String status;//监控状态
	private int score;//健康分
	
	public HealthMonitorSummary() {
	}
	
	public HealthMonitorSummary(String monitorId, String orderNumber, String account, String planId, String doctor,
			String status, int score) {
		super();
		this.monitorId = monitorId;
		this.orderNumber = orderNumber;
		this.account = account;
		this.planId = planId;
		this.doctor = doctor;
		this.status = status;
		this.score = score;
	}

	public String getMonitorId() {
		return monitorId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public String getAccount() {
		return account;
	}
	public String getPlanId() {
		return planId;
	}
	public String getDoctor() {
		return doctor;
	}
	public String getStatus() {
		return status;
	}
	public int getScore() {
		return score;
	}
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
