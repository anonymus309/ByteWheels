package com.bytemark.bytewheels.rs.model;

public class Vehicle {
	private Integer id;
	private String model;
	private String category;
	private Integer dailyPrice;
	private Integer quantity;
	public Vehicle() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(Integer i) {
		this.dailyPrice = i;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
