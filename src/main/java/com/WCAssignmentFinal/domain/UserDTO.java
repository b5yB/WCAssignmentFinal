package com.WCAssignmentFinal.domain;


public class UserDTO {
	
	private String credential;
	private String username;
	private String password;
	private String name;
	
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserDTO [credential=" + credential + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
