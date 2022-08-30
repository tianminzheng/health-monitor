package com.healthmonitor.monitor.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.healthmonitor.monitor.domain.model.valueobject.MonitorStatus;
import com.healthmonitor.monitor.domain.model.valueobject.OrderStatus;
import com.healthmonitor.monitor.infrastructure.repository.mapper.HealthMonitorMapper;
import com.healthmonitor.monitor.infrastructure.repository.po.HealthMonitorPO;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HealthMonitorMapperTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private HealthMonitorMapper healthMonitorMapper;
	
	@Test
	public void testFindByMonitorId() throws Exception {
		
		HealthMonitorPO healthMonitorPO = buildHealthMonitorPO();
		this.entityManager.persist(healthMonitorPO);
		
		HealthMonitorPO target = this.healthMonitorMapper.findByMonitorId("monitorId1");
		assertThat(target).isNotNull();
		assertThat(target.getMonitorId()).isEqualTo("monitorId1");
	}
	
	private HealthMonitorPO buildHealthMonitorPO() {
		
		HealthMonitorPO healthMonitorPO = new HealthMonitorPO(
				 "monitorId1",
				 MonitorStatus.INITIALIZED,
				 0,				 
				 "orderNumber1",
				 "牛奶过敏",
				 "青霉素注射",
				 "腿部手术",
				 "阵痛",
				 "腰部",
				 "2天",
				 OrderStatus.CREATED,
				 "tianyalan"
		 );
		
		return healthMonitorPO;
	}
}
