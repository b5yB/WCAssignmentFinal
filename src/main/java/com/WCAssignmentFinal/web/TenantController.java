package com.WCAssignmentFinal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.service.TenantService;

@Controller
public class TenantController {
	
	@Autowired
	private TenantService tServ;
	
	@GetMapping("/tenant/{tenantId}")
	public String getTenant (ModelMap model, @PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		model.put("tenant", tenant);
		return "tenant";
	}
	
	@PostMapping("/tenant/{tenantId}")
	public String postTenant (ModelMap model, @PathVariable Long tenantId, Tenant tenantDTO) {
		Tenant tenant = tServ.saveTenant(tenantDTO);
		model.put("tenant", tenant);
		return "redirect:/tenant/"+tenantId; 
	}
}
