package com.WCAssignmentFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Ticket;
import com.WCAssignmentFinal.repository.TenantRepo;
import com.WCAssignmentFinal.repository.TicketRepo;
import com.WCAssignmentFinal.repository.UnitRepo;

@Service
public class TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private TenantRepo tenantRepo;
	
	@Autowired
	private UnitRepo uRepo;
	
//	public Ticket saveNewTicket(Ticket t) {
//		t.setStatus("open");
//		return ticketRepo.save(t);
//	}

	public Ticket saveTicket(Ticket t) {
		System.out.println("Saving ticket: " + t);
		return ticketRepo.save(t);
	}
	
//	public Optional<List<Ticket>> findTicketsByTenantId (Long tenantId){
//		return ticketRepo.findByTenant(tenantRepo.getById(tenantId));
//	}
	
	public Ticket findById (Long ticketId) {
		return ticketRepo.getById(ticketId);
	}
	
	public List<Ticket> findAllTickets() {
		return ticketRepo.findAll();
	}
	
	public void deleteTicket (Long ticketId) {
		ticketRepo.deleteById(ticketId);
	}

//	public List<Ticket> testTickets() {
//		if (ticketRepo.findAll().size()!=0) {
//			return ticketRepo.findAll();
//		}
//		else {
//			ArrayList<Ticket> testTickets = new ArrayList<Ticket>();
//			Ticket t1 = new Ticket();
//			t1.setType("repair");
//			t1.setStatus("open");
//			t1.setTenant(tenantRepo.getById(1L));
//			t1.setUnit(uRepo.getById(1L));
//			ticketRepo.save(t1);
//			
//			Ticket t2 = new Ticket();
//			t2.setType("misc");
//			t2.setStatus("open");
//			t2.setTenant(tenantRepo.getById(1L));
//			t2.setUnit(uRepo.getById(1L));
//			ticketRepo.save(t2);
//			
//			testTickets.add(t1);
//			testTickets.add(t2);
//			
//			System.out.println(t1);
//			System.out.println(t2);
//			
//			return testTickets;
//		}
//	}
	
	
}
