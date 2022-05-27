package com.WCAssignmentFinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
	
	//@Query("select * from Ticket t" + " where t.tenant.tenant_id = :tenantId")
	Optional<List<Ticket>> findByTenant(Tenant tenant);
	
	

}
