package com.WCAssignmentFinal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Manager;
import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.UserDTO;
import com.WCAssignmentFinal.exception.UserAlreadyExistsException;
import com.WCAssignmentFinal.service.ManagerService;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.TicketService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class LoginController {

	@Autowired
	private TenantService tServ;
	
	@Autowired 
	private ManagerService mServ;
	
	@Autowired
	private UnitService uServ;
	
	@Autowired
	private TicketService ticketServ;
	
	@GetMapping("/")
	public String redirectToLogin () {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String getUser (ModelMap model) {
		mServ.createAdmin();
		//tServ.testTenant();
		//uServ.testUnit();
		//ticketServ.testTickets();
		
		UserDTO userDTO = new UserDTO();
		model.put("userDTO", userDTO);
		return "login";
	}
	
	@PostMapping("/login")
	public String postUser (ModelMap model, UserDTO user) {
		System.out.println(user);
		if (user.getCredential().matches("tenant")) {
			Tenant tenant = tServ.findByUsername(user.getUsername());
			if(tenant == null) {
				return "loginError";
			}
			else {
				System.out.println("Logging in: " + tenant);
				return "redirect:/tenant/"+tenant.getTenantId();
			}
		}
		else if(user.getCredential().matches("manager")) {
			Manager manager = mServ.findByUsername(user.getUsername());
			if (manager == null) {
				return "loginError";
			}
			else {
				System.out.println("Logging in: " + manager);
				return "redirect:/manager/"+manager.getManagerId();
			}
		}
		else {
			System.out.println("No credential selected.");
			return "loginError";
		}
	}
	
	@GetMapping("/register")
	public String getNewUser (ModelMap model) {
		model.put("userDTO", new UserDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String postNewUser (ModelMap model, UserDTO user) throws UserAlreadyExistsException {
		Tenant t = new Tenant();
		t.setCredential("tenant");
		t.setUsername(user.getUsername());
		t.setPassword(user.getPassword());
		t.setName(user.getName());
		t.setManager(mServ.findById(1L));
		try {	
			if (tServ.findByUsername(user.getUsername())!=null) {
				throw new UserAlreadyExistsException();
			}
			else {
				tServ.saveTenant(t);
				//model.put("tenant", t);
			}
		}
		catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
		System.out.println(t);
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logoutUser (ModelMap model) {
		model.clear();
		System.out.println("User logged out...");
		return "redirect:/login";
	}
	
	
}
