package com.WCAssignmentFinal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Ticket;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketServ;
	
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets (ModelMap model) {
		return ticketServ.findAllTickets();
	}
	
	@PostMapping("/ticket/new")
	public String getNewTicket (ModelMap model) {
		Ticket ticket= new Ticket();
		ticket.setStatus("open");
		//unit.setManager(mServ.findById(1L));
		ticketServ.saveTicket(ticket);
		//model.put("ticket", ticket);
		return "redirect:/ticket/"+ticket.getTicketId();
	}
	
	@GetMapping("/ticket/{ticketId}")
	public String getTicket (ModelMap model, @PathVariable Long ticketId) {
		Ticket ticket = ticketServ.findById(ticketId);
		model.put("ticket", ticket);
		return "ticket";
	}
	
	@PostMapping("/ticket/{ticketId}")
	public String postTicket (@PathVariable Long ticketId, Ticket ticket) {
		ticketServ.saveTicket(ticket);
		return "redirect:/manager/"+1L;
	}
	
	@PostMapping("/ticket/{ticketId}/delete")
	public String deleteTicket (@PathVariable Long ticketId) {
		ticketServ.deleteTicket(ticketId);
		return "redirect:/manager/"+1L;
	}
	
}
