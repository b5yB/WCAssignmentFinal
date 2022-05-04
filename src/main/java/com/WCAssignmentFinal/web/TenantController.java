package com.WCAssignmentFinal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class TenantController {
	
	@Autowired
	private TenantService tServ;
	
	@Autowired
	private UnitService uServ;
	
	@GetMapping("/tenant/{tenantId}")
	public String getTenant (ModelMap model, @PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		//System.out.println(tenant);
		model.put("tenant", tenant);
		return "tenantAccount";
	}
	
	@PostMapping("/tenant/{tenantId}")
	public String postTenant (ModelMap model, @PathVariable Long tenantId, Tenant tenantDTO) {
		Tenant tenant = tServ.findById(tenantId);
		tenant.setUsername(tenantDTO.getUsername());
		tenant.setPassword(tenantDTO.getPassword());
		tenant.setName(tenantDTO.getName());
		tServ.saveTenant(tenant);
		//System.out.println(tenant);
		model.put("tenant", tenant);
		return "redirect:/tenant/"+tenantId; 
	}
	
	@GetMapping("/tenant/{tenantId}/unit/{unitId}")
	public String getTenantAccountUnit (ModelMap model, @PathVariable Long unitId) {
		Unit unit = uServ.findById(unitId);
		//System.out.println(unit);
		model.put("unit", unit);
		return "tenantAccountUnit";
	}
}
