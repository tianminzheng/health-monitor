package com.healthmonitor.monitor.infrastructure.repository.factory;

import org.springframework.stereotype.Service;

import com.healthmonitor.domain.model.valueobject.Anamnesis;
import com.healthmonitor.domain.model.valueobject.Symptom;
import com.healthmonitor.monitor.domain.model.aggregate.HealthMonitor;
import com.healthmonitor.monitor.domain.model.aggregate.MonitorId;
import com.healthmonitor.domain.model.entity.HealthPlanProfile;
import com.healthmonitor.monitor.domain.model.entity.HealthTestOrder;
import com.healthmonitor.monitor.domain.model.valueobject.HealthScore;
import com.healthmonitor.monitor.infrastructure.repository.po.HealthMonitorPO;

@Service
public class HealthMonitorFactory {

	 public HealthMonitorPO creatHealthMonitorPO(HealthMonitor healthMonitor){
		 HealthMonitorPO healthMonitorPO = new HealthMonitorPO(
				 healthMonitor.getMonitorId().getMonitorId(),
				 healthMonitor.getStatus(),
				 healthMonitor.getScore().getScore(),
				 
				 healthMonitor.getOrder().getOrderNumber(),
				 healthMonitor.getOrder().getAnamnesis().getAllergy(),
				 healthMonitor.getOrder().getAnamnesis().getInjection(),
				 healthMonitor.getOrder().getAnamnesis().getSurgery(),
				 healthMonitor.getOrder().getSymptom().getSymptomDescription(),
				 healthMonitor.getOrder().getSymptom().getBodyPart(),
				 healthMonitor.getOrder().getSymptom().getTimeDuration(),
				 healthMonitor.getOrder().getOrderStatus(),
				 healthMonitor.getOrder().getAccount()
		 );
			 
		 //HealthPlanProfile
		 if(healthMonitor.getPlan() != null) {
			 healthMonitorPO.setPlanId(healthMonitor.getPlan().getPlanId());
			 healthMonitorPO.setDoctor(healthMonitor.getPlan().getDoctor());
			 healthMonitorPO.setDescription(healthMonitor.getPlan().getDescription());
		 }		 
		 		 
		 return healthMonitorPO;
	 }
	 
	 public HealthMonitor creatHealthMonitor(HealthMonitorPO healthMonitorPO){
		 HealthMonitor healthMonitor = new HealthMonitor();
		 healthMonitor.setMonitorId(new MonitorId(healthMonitorPO.getMonitorId()));
		 healthMonitor.setStatus(healthMonitorPO.getStatus());
		 healthMonitor.setScore(new HealthScore(healthMonitorPO.getScore()));
		 
		 //HealthTestOrder
		 Anamnesis anamnesis = new Anamnesis(healthMonitorPO.getAllergy(), healthMonitorPO.getInjection(), healthMonitorPO.getSurgery());
		 Symptom symptom = new Symptom(healthMonitorPO.getSymptomDescription(), healthMonitorPO.getBodyPart(), healthMonitorPO.getTimeDuration());
		 HealthTestOrder order = new HealthTestOrder(healthMonitorPO.getOrderNumber(), healthMonitorPO.getAccount(), anamnesis, symptom);
		 order.setOrderStatus(healthMonitorPO.getOrderStatus());
		 healthMonitor.setOrder(order);
		 
		 //HealthPlanProfile
		 HealthPlanProfile plan = new HealthPlanProfile();
		 plan.setPlanId(healthMonitorPO.getPlanId());
		 plan.setAccount(healthMonitorPO.getAccount());
		 plan.setDoctor(healthMonitorPO.getDoctor());
		 plan.setDescription(healthMonitorPO.getDescription());
		 healthMonitor.setPlan(plan);

		 return healthMonitor;
	 }
}
