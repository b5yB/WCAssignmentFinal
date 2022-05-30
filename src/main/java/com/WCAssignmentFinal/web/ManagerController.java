package com.WCAssignmentFinal.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Manager;
import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.TenantDTO;
import com.WCAssignmentFinal.domain.Ticket;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.ManagerService;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.TicketService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class ManagerController {
	
	@Autowired
	private TenantService tServ;
	
	@Autowired
	private ManagerService mServ;
	
	@Autowired
	private UnitService uServ;
	
	@Autowired
	private TicketService ticketService;

	@GetMapping("/manager/{managerId}")
	public String getManagerAccount (ModelMap model, @PathVariable Long managerId) {
		Manager manager = mServ.findById(managerId);
		List<Tenant> tenants = tServ.findAllTenants();
		List<Unit> units = uServ.findAllUnits();
		List<Ticket> tickets = ticketService.findAllTickets();
		//System.out.println(manager);
		//System.out.println(tenants);
		//System.out.println(units);
		model.put("manager", manager);
		model.put("tenants", tenants);
		model.put("units", units);
		model.put("tickets", tickets);
		return "managerAccount";
	}
	
	@PostMapping("/manager/{managerId}")
	public String postManagerAccount (ModelMap model, @PathVariable Long managerId, Manager newManager) {
		Manager manager = mServ.findById(managerId);
		//System.out.println(manager);
		//System.out.println(tenants);
		//System.out.println(units);
		manager.setUsername(newManager.getUsername());
		manager.setPassword(newManager.getPassword());
		mServ.saveManager(manager);
		System.out.println("Saved: "+manager);
		//model.put("manager", manager);
		return "redirect:/manager/"+managerId;
	}
	
	@GetMapping("/manager/{managerId}/tenant/{tenantId}")
	public String getTenant (ModelMap model, @PathVariable Long managerId, @PathVariable Long tenantId) {
		Manager manager = mServ.findById(managerId);
		Tenant tenant = tServ.findById(tenantId);
		TenantDTO tenantDTO = new TenantDTO();
		List<Unit> units = uServ.findAllUnits();
		if(tenant.getUnit() != null) {
			Unit unit = tenant.getUnit();
			model.put("unit", unit);
		}
		//System.out.println(tenant);
		//System.out.println(tenantDTO);
		//System.out.println(units);
		model.put("manager", manager);
		model.put("tenant", tenant);
		model.put("tenantDTO", tenantDTO);
		model.put("units", units);
		return "tenant";
	}
	
	@PostMapping("/manager/{managerId}/tenant/{tenantId}")
	public String postTenant (ModelMap model, @PathVariable Long managerId, @PathVariable Long tenantId,
								Tenant newTenant, TenantDTO tenantDTO) {
		//Manager manager = mServ.findById(managerId);
		Tenant tenant = tServ.findById(tenantId);
		
		if (tenantDTO.getUnitId()!=null) {
			Unit unit = uServ.findById(tenantDTO.getUnitId());
			tenant.setUnit(unit);
			unit.setTenant(tenant);
			uServ.saveUnit(unit);
			tenantDTO.setUnitId(null);
		}
		
		tenant.setUsername(newTenant.getUsername());
		tenant.setName(newTenant.getName());
		
		tServ.saveTenant(tenant);
		
		List<Unit> units = uServ.findAllUnits();
		List<Tenant> tenants = tServ.findAllTenants();
		
		System.out.println(tenant);
		System.out.println(tenants);
		
		model.put("tenants", tenants);
		model.put("units", units);
		return "redirect:/manager/" + managerId; 
	}
	
	@PostMapping("/manager/{managerId}/tenant/{tenantId}/delete")
	public String deleteTenant (@PathVariable Long managerId, @PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		if (tenant.getUnit() != null) {
			Unit unit = tenant.getUnit();
			unit.setTenant(null);
			tenant.setUnit(null);
			uServ.saveUnit(unit);
			tServ.saveTenant(tenant);
		}
		tServ.deleteTenant(tenantId);
		return "redirect:/manager/" + managerId;
	}
	
	@PostMapping("/manager/{managerId}/unit/new")
	public String getNewUnit (ModelMap model, @PathVariable Long managerId) {
		Unit unit = new Unit();
		unit.setManager(mServ.findById(managerId));
		uServ.saveUnit(unit);
		model.put("unit", unit);
		return "redirect:/manager/" + managerId + "/unit/" + unit.getUnitId();
	}
	
	@GetMapping("/manager/{managerId}/unit/{unitId}")
	public String getUnit (ModelMap model, @PathVariable Long managerId, @PathVariable Long unitId) {
		Unit unit = uServ.findById(unitId);
		Manager manager = mServ.findById(managerId);
		model.put("manager", manager);
		model.put("unit", unit);
		return "managerUnit";
	}
	
	@PostMapping("/manager/{managerId}/unit/{unitId}")
	public String postUnit (@PathVariable Long managerId, @PathVariable Long unitId, Unit unit) {
		uServ.saveUnit(unit);
		return "redirect:/manager/" + managerId;
	}
	
	@PostMapping("/manager/{managerId}/unit/{unitId}/delete")
	public String deleteUnit (@PathVariable Long managerId, @PathVariable Long unitId) {
		Unit unit = uServ.findById(unitId);
		if (unit.getTenant()!=null) {
			Tenant tenant = unit.getTenant();
			unit.setTenant(null);
			tenant.setUnit(null);
			uServ.saveUnit(unit);
			tServ.saveTenant(tenant);
		}
		uServ.deleteUnit(unitId);
		return "redirect:/manager/" + managerId;
	}
	
//	@GetMapping("/manager/tickets")
//	public String getManagerTickets (ModelMap model) {
//		List<Ticket> tickets = ticketService.findAllTickets();
//		model.put("tickets", tickets);
//		return "managerTickets";
//	}
	
	@GetMapping("/manager/{managerId}/ticket/{ticketId}")
	public String getManagerTicket (ModelMap model, @PathVariable Long ticketId, @PathVariable Long managerId) {
		Ticket ticket = ticketService.findById(ticketId);
		Manager manager = mServ.findById(managerId);
		Unit unit = ticket.getUnit();
		model.put("unit", unit);
		model.put("manager", manager);
		model.put("ticket", ticket);
		return "managerTicket";
	}
	
	@PostMapping("/manager/{managerId}/ticket/{ticketId}")
	public String postManagerTicket (ModelMap model, @PathVariable Long ticketId, @PathVariable Long managerId, 
										Ticket ticket) {
		ticketService.saveTicket(ticket);
		return "redirect:/manager/" + managerId;	
	}
	
	@PostMapping("/manager/{managerId}/back")
	public String backButton (ModelMap model, @PathVariable Long managerId) {
		return "redirect:/manager/" + managerId;
	}
	
	
}
