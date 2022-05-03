package com.WCAssignmentFinal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

	@GetMapping("/manager")
	public String backButton (ModelMap model) {
		return "managerAccount";
	}
	
	
}
