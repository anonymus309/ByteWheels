package com.bytemark.bytewheels.rs.model;

import java.util.Date;

public class Order {

	private Integer id;
	private Integer userId;
	private Date pickUpDate;
	private Date dropOffDate;
	private Integer rentCost;
	private Boolean processed;
	private Boolean picked;
	private Boolean returned;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public Date getDropOffDate() {
		return dropOffDate;
	}
	public void setDropOffDate(Date dropOffDate) {
		this.dropOffDate = dropOffDate;
	}
	public Integer getRentCost() {
		return rentCost;
	}
	public void setRentCost(Integer rentCost) {
		this.rentCost = rentCost;
	}
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	public Boolean getPicked() {
		return picked;
	}
	public void setPicked(Boolean picked) {
		this.picked = picked;
	}
	public Boolean getReturned() {
		return returned;
	}
	public void setReturned(Boolean returned) {
		this.returned = returned;
	}
	

}
