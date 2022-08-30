package com.healthmonitor.monitor.infrastructure.repository.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthmonitor.monitor.infrastructure.repository.po.HealthMonitorPO;


@Repository
public interface HealthMonitorMapper extends JpaRepository<HealthMonitorPO, Long>  {

	HealthMonitorPO findByMonitorId(String monitorId);

	List<String> findAllMonitorIds();
	
	@Query("select h from HealthMonitorPO h where h.account = ?1")
	HealthMonitorPO findByUserAccount(String account);	
}
