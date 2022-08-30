package com.healthmonitor.monitor.integration.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.healthmonitor.domain.event.MonitorInitializedEvent;
import com.healthmonitor.domain.event.PlanGeneratedEvent;
import com.healthmonitor.domain.event.TaskPerformedEvent;
import com.healthmonitor.monitor.infrastructure.messaging.HealthMonitorSource;

@Service
@EnableBinding(HealthMonitorSource.class)
public class MonitorEventPublisherService{

	private HealthMonitorSource healthMonitorSource;

    public MonitorEventPublisherService(HealthMonitorSource healthMonitorSource){
        this.healthMonitorSource = healthMonitorSource;
    }

    @TransactionalEventListener
    public void handleMonitorInitializedEvent(MonitorInitializedEvent monitorInitializedEvent){
    	healthMonitorSource.monitorApplication().send(MessageBuilder.withPayload(monitorInitializedEvent).build());
    }

    @TransactionalEventListener
    public void handlePlanGeneratedEvent(PlanGeneratedEvent planGeneratedEvent){
    	healthMonitorSource.planGeneration().send(MessageBuilder.withPayload(planGeneratedEvent).build());
    }
    
    @TransactionalEventListener
    public void handleTaskPerformedEvent(TaskPerformedEvent taskPerformedEvent){
    	healthMonitorSource.taskPerforming().send(MessageBuilder.withPayload(taskPerformedEvent).build());
    }
}
