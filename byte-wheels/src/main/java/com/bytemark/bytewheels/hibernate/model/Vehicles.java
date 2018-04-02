package com.bytemark.bytewheels.hibernate.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicles {
	public Vehicles() {
	}
	
	public Vehicles(Integer id, String model, Integer dailyPrice, String category, Integer quantity) {
		super();
		this.id = id;
		this.model = model;
		this.dailyPrice = dailyPrice;
		this.category = category;
		this.quantity = quantity;
	}

	@Id
	@Column(name="vehicle_id")
	private Integer id;
	private String model;
	@Column(name="daily_price")
	private Integer dailyPrice;
	private String category;
	private Integer quantity;
	@OneToMany(fetch = FetchType.LAZY,mappedBy="vehicles")
    private Set<Orders> orders;
	public Set<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
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
	public int getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(Integer dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", dailyPrice=" + dailyPrice + ", category=" + category
				+ ", quantity=" + quantity + ", orders=" + orders + "]";
	}
	
}
