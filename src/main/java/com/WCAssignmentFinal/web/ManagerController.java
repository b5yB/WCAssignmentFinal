package com.WCAssignmentFinal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Manager;
import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.TenantDTO;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.ManagerService;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class ManagerController {
	
	@Autowired
	private TenantService tServ;
	
	@Autowired
	private ManagerService mServ;
	
	@Autowired
	private UnitService uServ;

	@GetMapping("/manager/{managerId}")
	public String getManagerAccount (ModelMap model, @PathVariable Long managerId) {
		Manager manager = mServ.findById(managerId);
		List<Tenant> tenants = tServ.findAllTenants();
		List<Unit> units = uServ.findAllUnits();
		//System.out.println(manager);
		//System.out.println(tenants);
		//System.out.println(units);
		model.put("manager", manager);
		model.put("tenants", tenants);
		model.put("units", units);
		return "managerAccount";
	}
	
	@GetMapping("/manager/tenant/{tenantId}")
	public String getTenant (ModelMap model, @PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		TenantDTO tenantDTO = new TenantDTO();
		List<Unit> units = uServ.findAllUnits();
		//System.out.println(tenant);
		//System.out.println(tenantDTO);
		//System.out.println(units);
		model.put("tenant", tenant);
		model.put("tenantDTO", tenantDTO);
		model.put("units", units);
		return "tenant";
	}
	
	@PostMapping("/manager/tenant/{tenantId}")
	public String postTenant (ModelMap model, @PathVariable Long tenantId, TenantDTO tenantDTO) {
		Tenant tenant = tServ.findById(tenantId);
		Unit unit = uServ.findById(tenantDTO.getUnitId());
		
		tenant.setUsername(tenantDTO.getUsername());
		tenant.setPassword(tenantDTO.getPassword());
		tenant.setName(tenantDTO.getName());
		
		tenant.setUnit(unit);
		//unit.setTenant(tenant);
		
		tServ.saveTenant(tenant);
		uServ.saveUnit(unit);
		
		List<Unit> units = uServ.findAllUnits();
		List<Tenant> tenants = tServ.findAllTenants();

		model.put("tenants", tenants);
		model.put("units", units);
		return "redirect:/manager/"+1; 
	}
	
	@GetMapping("/manager/back")
	public String backButton (ModelMap model) {
		return "managerAccount";
	}
	
	
}
