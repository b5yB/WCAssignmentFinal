package com.WCAssignmentFinal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.WCAssignmentFinal.domain.Manager;
import com.WCAssignmentFinal.domain.Tenant;
import com.WCAssignmentFinal.domain.Unit;
import com.WCAssignmentFinal.domain.UserDTO;
import com.WCAssignmentFinal.exception.UserAlreadyExistsException;
import com.WCAssignmentFinal.service.ManagerService;
import com.WCAssignmentFinal.service.TenantService;
import com.WCAssignmentFinal.service.UnitService;

@Controller
public class LoginController {

	@Autowired
	private TenantService tServ;
	
	@Autowired 
	private ManagerService mServ;
	
	@Autowired
	private UnitService uServ;
	
	@GetMapping("/")
	public String redirectToLogin () {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String getUser (ModelMap model) {
		mServ.createAdmin();
		model.put("userDTO", new UserDTO());
		return "login";
	}
	
	@PostMapping("/login")
	public String postUser (ModelMap model, UserDTO user) {
		System.out.println(user);
		if (user.getCredential().matches("tenant")) {
			System.out.println("It's a tenant!");
			Tenant tenant = tServ.findByUsername(user);
			model.put("tenant", tenant);
			return "tenantAccount";
		}
		else if(user.getCredential().matches("manager")) {
			System.out.println("It's a manager!");
			Manager manager = mServ.findByUsername(user);
			List<Tenant> tenants = tServ.findAllTenants();
			List<Unit> units = uServ.findAllUnits();
			model.put("manager", manager);
			model.put("tenants", tenants);
			model.put("units", units);
			return "managerAccount";
		}
		else {
			System.out.println("No tenant or manager...");
			return "redirect:/login";
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
		//t.setManager(mServ.findById(1L));
		try {	
			if (tServ.findByUsername(user)!=null) {
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
