package com.WCAssignmentFinal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.ManagerService;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class UnitController {

	@Autowired
	private UnitService uServ;
	
	@Autowired
	private TenantService tServ;
	
	@Autowired
	private ManagerService mServ;
	
	@GetMapping("/units")
	public List<Unit> getAllUnits (ModelMap model) {
		return uServ.findAllUnits();
	}
	
	@PostMapping("/unit/new")
	public String getNewUnit (ModelMap model) {
		Unit unit = new Unit();
		//unit.setManager(mServ.findById(1L));
		uServ.saveUnit(unit);
		model.put("unit", unit);
		return "redirect:/unit/"+unit.getUnitId();
	}
	
	@GetMapping("/unit/{unitId}")
	public String getUnit (ModelMap model, @PathVariable Long unitId) {
		Unit unit = uServ.findById(unitId);
		model.put("unit", unit);
		return "unit";
	}
	
	@PostMapping("/unit/{unitId}")
	public String postUnit (@PathVariable Long unitId, Unit unit) {
		uServ.saveUnit(unit);
		return "redirect:/manager/"+1;
	}
	
	
	
	
}
