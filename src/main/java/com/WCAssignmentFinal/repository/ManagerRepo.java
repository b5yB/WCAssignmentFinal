package com.WCAssignmentFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.WCAssignmentFinal.domain.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Long>{

	Manager findByUsername (String username);
	
}
