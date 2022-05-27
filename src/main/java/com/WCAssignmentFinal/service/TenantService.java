package com.WCAssignmentFinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.repository.ManagerRepo;
import com.WCAssignmentFinal.repository.TenantRepo;

@Service
public class TenantService {
	
	@Autowired
	private TenantRepo tRepo;
	
	@Autowired
	private ManagerRepo mRepo;
	
	public Tenant saveNewTenant (Tenant t) {
		t.setManager(mRepo.findByUsername("admin"));
		return tRepo.save(t);
	}

	public Tenant saveTenant (Tenant t) {
		return tRepo.save(t);
	}
	
	public List<Tenant> findAllTenants () {
		return tRepo.findAll();
	}
	
	public Tenant findById (Long tenantId) {
		return tRepo.findById(tenantId).orElse(new Tenant());
	}
	
	public Tenant findByUsername (String username) {
		Tenant t = tRepo.findByUsername(username);
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

	public Tenant testTenant() {
		if (tRepo.findAll().size()!=0) {
			Tenant tenant = tRepo.findByUsername("test");
			return tenant;
		}
		else {
			Tenant test = new Tenant();
			test.setCredential("tenant");
			test.setUsername("test");
			test.setPassword("pass");
			test.setName("test");
			test.setManager(mRepo.findByUsername("admin"));
			tRepo.save(test);
			
			System.out.println(test);
	
			return test;
		}
	}
	
	
}
