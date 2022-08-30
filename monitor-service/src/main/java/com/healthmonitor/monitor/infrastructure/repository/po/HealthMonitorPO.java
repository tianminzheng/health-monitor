package com.healthmonitor.monitor.infrastructure.repository.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.healthmonitor.domain.event.MonitorInitializedEvent;
import com.healthmonitor.monitor.domain.model.valueobject.MonitorStatus;
import com.healthmonitor.monitor.domain.model.valueobject.OrderStatus;

@Entity
@Table(name = "health_monitor")
@NamedQueries({    
    @NamedQuery(name = "HealthMonitorPO.findAllMonitorIds",
            query = "Select h.monitorId from HealthMonitorPO h") })
public class HealthMonitorPO extends AbstractAggregateRoot<HealthMonitorPO> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//HealthMonitor
	@Column(name = "monitor_id")
	private String monitorId;
	@Enumerated(EnumType.STRING)
	private MonitorStatus status;
	private int score;
	
	//HealthTestOrder
	private String orderNumber;//检测单编号
	private String allergy; //过敏史
	private String injection; //预防注射史
	private String surgery; //外科手术史
	private String symptomDescription;//症状描述
	private String bodyPart;//身体部位
	private String timeDuration;//持续时间	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;//检测单状态	
	
	//HealthPlanProfile
	private String planId;
	private String doctor;
	private String description;
	private String tasks;
	
	//公共
	private String account;//用户账户
	
	public HealthMonitorPO() {
		super();
	}
	
	public HealthMonitorPO(String monitorId, MonitorStatus status, int score, String orderNumber,
			String allergy, String injection, String surgery, String symptomDescription, String bodyPart,
			String timeDuration, OrderStatus orderStatus, String account) {
		super();
		this.monitorId = monitorId;
		this.status = status;
		this.score = score;
		this.orderNumber = orderNumber;
		this.allergy = allergy;
		this.injection = injection;
		this.surgery = surgery;
		this.symptomDescription = symptomDescription;
		this.bodyPart = bodyPart;
		this.timeDuration = timeDuration;
		this.orderStatus = orderStatus;
		this.account = account;
		
		//发送领域事件
		MonitorInitializedEvent monitorInitializedEvent = 
				new MonitorInitializedEvent(this.account, this.monitorId, this.score);
		this.registerEvent(monitorInitializedEvent);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}

	public MonitorStatus getStatus() {
		return status;
	}

	public void setStatus(MonitorStatus status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getInjection() {
		return injection;
	}

	public void setInjection(String injection) {
		this.injection = injection;
	}

	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getSymptomDescription() {
		return symptomDescription;
	}

	public void setSymptomDescription(String symptomDescription) {
		this.symptomDescription = symptomDescription;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(String tasks) {
		this.tasks = tasks;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
		
}
