package com.WCAssignmentFinal.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Unit {
	
	private Long unitId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private Tenant tenant;
	private Manager manager;
	private List<Ticket> tickets;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="manager_id")
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	@OneToMany(mappedBy = "unit", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	@Override
	public String toString() {
		//+ ", tenantId=" + tenant.getTenantId()
		//+ ", manager=" + manager.getManagerId()
		return "Unit [unitId=" + unitId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", tickets=" + tickets + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, manager, state, tenant, tickets, unitId, zipCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(manager, other.manager)
				&& Objects.equals(state, other.state) && Objects.equals(tenant, other.tenant)
				&& Objects.equals(tickets, other.tickets) && Objects.equals(unitId, other.unitId)
				&& Objects.equals(zipCode, other.zipCode);
	}
	
}
