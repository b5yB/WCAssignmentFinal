package com.WCAssignmentFinal.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Ticket;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.TicketService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class TenantController {
	
	@Autowired
	private TenantService tServ;
	
	@Autowired
	private UnitService uServ;
	
	@Autowired
	private TicketService ticketServ;
	
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
	
	@PostMapping("/tenant/{tenantId}/delete")
	public String deleteTenant (@PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		if (tenant.getUnit() != null) {
			Unit unit = tenant.getUnit();
			unit.setTenant(null);
			tenant.setUnit(null);
			uServ.saveUnit(unit);
			tServ.saveTenant(tenant);
		}
		tServ.deleteTenant(tenantId);
		return "redirect:/login";
	}
	
	@GetMapping("/tenant/{tenantId}/unit/{unitId}")
	public String getTenantAccountUnit (ModelMap model, @PathVariable Long tenantId, @PathVariable Long unitId) {
		Tenant tenant = tServ.findById(tenantId);
		Unit unit = uServ.findById(unitId);
		//System.out.println(unit);
		model.put("tenant", tenant);
		model.put("unit", unit);
		return "unit";
	}
	
	@PostMapping("/tenant/{tenantId}/ticket/new")
	public String getNewTicket (ModelMap model, @PathVariable Long tenantId) {
		Tenant tenant = tServ.findById(tenantId);
		//List<Ticket> tickets = tenant.getTickets();
		if (tenant.getUnit()!=null) {
			Unit unit = tenant.getUnit();
			model.put("unit", unit);
		}
		else {
			Unit newUnit = new Unit();
			newUnit.setAddressLine1("NoUnitFound");
			newUnit.setManager(tenant.getManager());
			newUnit.setTenant(tenant);
			uServ.saveUnit(newUnit);
			model.put("unit", newUnit);
		}
		Ticket ticket= new Ticket();
		ticket.setTenant(tServ.findById(tenantId));
		ticket.setUnit(tenant.getUnit());
		//unit.setManager(mServ.findById(1L));
		ticketServ.saveNewTicket(ticket);
		//tickets.add(ticket);
		//tenant.setTickets(tickets);
		//tServ.saveTenant(tenant);
		//model.put("ticket", ticket);
		return "redirect:/tenant/" + tenantId + "/ticket/" + ticket.getTicketId();
	}
	
	@GetMapping("/tenant/{tenantId}/ticket/{ticketId}")
	public String getTenantTickets (ModelMap model, @PathVariable Long tenantId, @PathVariable Long ticketId) {
		Tenant tenant = tServ.findById(tenantId);
		Ticket ticket = ticketServ.findById(ticketId);
		model.put("tenant", tenant);
		model.put("ticket", ticket);
		return "tenantTicket";
	}
	
	@PostMapping("/tenant/{tenantId}/ticket/{ticketId}")
	public String postTicket (@PathVariable Long tenantId, @PathVariable Long ticketId, Ticket ticket) {
		ticketServ.saveTicket(ticket);
		return "redirect:/tenant/" + tenantId;
	}
	
	@PostMapping("/tenant/{tenantId}/ticket/{ticketId}/delete")
	public String deleteTicket (@PathVariable Long tenantId, @PathVariable Long ticketId) {
		ticketServ.deleteTicket(ticketId);
		return "redirect:/tenant/" + tenantId; 
	}

}
