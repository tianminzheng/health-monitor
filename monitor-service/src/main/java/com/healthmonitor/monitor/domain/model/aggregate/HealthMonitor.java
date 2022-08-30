package com.healthmonitor.monitor.domain.model.aggregate;

import java.util.UUID;

import com.healthmonitor.domain.model.valueobject.Anamnesis;
import com.healthmonitor.domain.model.valueobject.Symptom;
import com.healthmonitor.monitor.domain.command.ApplyMonitorCommand;
import com.healthmonitor.monitor.domain.command.GeneratePlanCommand;
import com.healthmonitor.monitor.domain.command.PerformTaskCommand;
import com.healthmonitor.domain.model.entity.HealthPlanProfile;
import com.healthmonitor.monitor.domain.model.entity.HealthTestOrder;
import com.healthmonitor.monitor.domain.model.valueobject.HealthScore;
import com.healthmonitor.monitor.domain.model.valueobject.MonitorStatus;

/**
 * HealthMonitor聚合
 */
public class HealthMonitor{

	private MonitorId monitorId;//聚合标识符
	private HealthTestOrder order;	
	private HealthPlanProfile plan;	
	private HealthScore score;
	private MonitorStatus status;
	
	public HealthMonitor() {
		
	}
	
	public HealthMonitor(ApplyMonitorCommand applyMonitorCommand) {
		//设置聚合标识符
		this.monitorId = new MonitorId(applyMonitorCommand.getMonitorId());
		
		//构建HealthTestOrder
		Anamnesis anamnesis = new Anamnesis(applyMonitorCommand.getAllergy(),applyMonitorCommand.getInjection(),applyMonitorCommand.getSurgery());
		Symptom symptom = new Symptom(applyMonitorCommand.getSymptomDescription(), applyMonitorCommand.getBodyPart(), applyMonitorCommand.getTimeDuration());
		String orderNumber =  "Order" + UUID.randomUUID().toString().toUpperCase();
		HealthTestOrder order = new HealthTestOrder(orderNumber, applyMonitorCommand.getAccount(), anamnesis, symptom);
		
		//初始化聚合状态
		this.order = order;
		this.status = MonitorStatus.INITIALIZED;
		this.score = new HealthScore(0);
	}


	public MonitorStatus getStatus() {
		return status;
	}
	
	public void generateHealthPlan(GeneratePlanCommand createPlanCommand) {
		
		//验证monitorId是否对当前HealthMonitor对象是否有效
		String monitorId = createPlanCommand.getMonitorId();
		if(!monitorId.equals(this.monitorId.getMonitorId())) {
			return;
		}		
		
		this.plan = createPlanCommand.getHealthPlanProfile();
	}

	public void performHealthTask(PerformTaskCommand performTaskCommand) {
		
		int taskScore = performTaskCommand.getHealthTaskProfile().getTaskScore();
		this.score.plusScore(taskScore); 
	}

	public MonitorId getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(MonitorId monitorId) {
		this.monitorId = monitorId;
	}

	public HealthTestOrder getOrder() {
		return order;
	}

	public void setOrder(HealthTestOrder order) {
		this.order = order;
	}

	public HealthPlanProfile getPlan() {
		return plan;
	}

	public void setPlan(HealthPlanProfile plan) {
		this.plan = plan;
	}

	public HealthScore getScore() {
		return score;
	}

	public void setScore(HealthScore score) {
		this.score = score;
	}

	public void setStatus(MonitorStatus status) {
		this.status = status;
	}
}
