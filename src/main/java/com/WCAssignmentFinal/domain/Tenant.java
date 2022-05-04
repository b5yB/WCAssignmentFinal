package com.WCAssignmentFinal.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Tenant {

	private Long tenantId;
	private String credential;
	private String username;
	private String password;
	private String name;
	private Manager manager;
	private Unit unit;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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
	@ManyToOne
	@JoinColumn(name="manager_id")
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	@OneToOne(mappedBy="tenant", cascade = CascadeType.PERSIST)
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", credential=" + credential + ", username=" + username + ", password="
				+ password + ", name=" + name + ", manager=" + manager + ", unit=" + unit + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(credential, manager, password, tenantId, unit, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenant other = (Tenant) obj;
		return Objects.equals(credential, other.credential) && Objects.equals(manager, other.manager)
				&& Objects.equals(password, other.password) && Objects.equals(tenantId, other.tenantId)
				&& Objects.equals(unit, other.unit) && Objects.equals(username, other.username);
	}
	
	
}
