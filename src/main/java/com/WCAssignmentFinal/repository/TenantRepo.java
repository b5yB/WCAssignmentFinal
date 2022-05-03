package com.WCAssignmentFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WCAssignmentFinal.domain.Tenant;

public interface TenantRepo extends JpaRepository<Tenant, Long>{
	
	Tenant findByUsername (String username);

}
