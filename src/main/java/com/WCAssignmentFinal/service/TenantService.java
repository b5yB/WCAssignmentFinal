package com.WCAssignmentFinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.UserDTO;
import com.WCAssignmentFinal.repository.TenantRepo;

@Service
public class TenantService {
	
	@Autowired
	private TenantRepo tRepo;

	public Tenant saveTenant (Tenant t) {
		return tRepo.save(t);
	}
	
	public List<Tenant> findAllTenants () {
		return tRepo.findAll();
	}
	
	public Tenant findById (Long tenantId) {
		return tRepo.findById(tenantId).orElse(new Tenant());
	}
	
	public Tenant findByUsername (UserDTO user) {
		Tenant t = tRepo.findByUsername(user.getUsername());
		if (t != null) {
			return t;
		}
		else {
			return null;
		}
	}
	
	public void deleteTenant (Long tenantId) {
		tRepo.deleteById(tenantId);
	}
	
	
}
