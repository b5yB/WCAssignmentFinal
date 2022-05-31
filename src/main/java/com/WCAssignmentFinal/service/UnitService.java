package com.WCAssignmentFinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.repository.ManagerRepo;
import com.WCAssignmentFinal.repository.TenantRepo;
import com.WCAssignmentFinal.repository.UnitRepo;

@Service
public class UnitService {

	@Autowired
	private UnitRepo uRepo;
	
	@Autowired
	private TenantRepo tRepo;
	
	@Autowired
	private ManagerRepo mRepo;
	
//	public Unit saveNewUnit (Unit u) {
//		//u.setManager(mRepo.findByUsername("admin"));
//		//u.setTenant(tRepo.findByUsername("test"));
//		return uRepo.save(u);
//	}
	
	public Unit saveUnit (Unit u) {
		System.out.println("Saving: " + u);
		return uRepo.save(u);
	}
	
	public Unit findById (Long unitId) {
		return uRepo.findById(unitId).orElse(new Unit());
	}
	
	public List<Unit> findAllUnits () {
		return uRepo.findAll();
	}
	
	public void deleteUnit (Long unitId) {
		uRepo.deleteById(unitId);
	}
	
	public Unit testUnit() {
		if (uRepo.findAll().size()!=0) {
			return uRepo.getById(1L);
		}
		else {
			Unit test = new Unit();
			test.setAddressLine1("test");
			test.setTenant(tRepo.findByUsername("test"));
			test.setManager(mRepo.getById(1L));
			uRepo.save(test);
			
			System.out.println(test);
	
			return test;
		}
	}
	
}
