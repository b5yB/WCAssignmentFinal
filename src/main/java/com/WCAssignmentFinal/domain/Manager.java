package com.WCAssignmentFinal.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Manager {

	private Long managerId;
	private String credential;
	private String username;
	private String password;
	private String name;
	private List<Tenant> tenants;
	private List<Unit> units;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
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
	@OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	public List<Tenant> getTenants() {
		return tenants;
	}
	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}
	@OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	public List<Unit> getUnits() {
		return units;
	}
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", credential=" + credential + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", tenants=" + tenants + ", units=" + units + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(credential, managerId, name, password, tenants, units, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(credential, other.credential) && Objects.equals(managerId, other.managerId)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(tenants, other.tenants) && Objects.equals(units, other.units)
				&& Objects.equals(username, other.username);
	}
	
}
