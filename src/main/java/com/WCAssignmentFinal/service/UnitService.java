package com.WCAssignmentFinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.repository.UnitRepo;

@Service
public class UnitService {

	@Autowired
	private UnitRepo uRepo;
	
	public Unit saveUnit (Unit u) {
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
	
}
