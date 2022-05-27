package com.WCAssignmentFinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WCAssignmentFinal.domain.Manager;
import com.WCAssignmentFinal.domain.UserDTO;
import com.WCAssignmentFinal.repository.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepo mRepo;
	
	public List<Manager> getAllManagers (){
		return mRepo.findAll();
	}
	
	public Manager createAdmin() {
		if (mRepo.findAll().size()!=0) {
			return mRepo.findByUsername("admin");
		}
		else {
			Manager admin = new Manager();
			admin.setCredential("manager");
			admin.setUsername("admin");
			admin.setPassword("pass");
			admin.setName("admin");
			mRepo.save(admin);
			System.out.println(admin);
			return admin;
			
		}
	}
	
	public Manager saveManager (Manager m) {
		return mRepo.save(m);
	}
	
	public Manager findById (Long managerId) {
		return mRepo.findById(managerId).orElse(new Manager());
	}
	
	public Manager findByUsername (String username) {
		Manager m = mRepo.findByUsername(username);
		if(m != null) {
			return m;
		}
		else {
			return null;
		}
	}
	
	public void deleteManager (Long managerId) {
		mRepo.deleteById(managerId);
	}
	
}
