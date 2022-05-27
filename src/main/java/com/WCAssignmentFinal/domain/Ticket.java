package com.WCAssignmentFinal.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {

	private Long ticketId;
	private String type; //damage, repair, misc
	private String description;
	private String status;
	private Tenant tenant;
	private Unit unit;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id")
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id")
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", type=" + type + ", description=" + description + ", status=" + status
				+ ", tenant=" + tenant.getTenantId() + ", unit=" + unit.getUnitId() + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, status, tenant, ticketId, type, unit);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(description, other.description) && Objects.equals(status, other.status)
				&& Objects.equals(tenant, other.tenant) && Objects.equals(ticketId, other.ticketId)
				&& Objects.equals(type, other.type) && Objects.equals(unit, other.unit);
	}
	
}
