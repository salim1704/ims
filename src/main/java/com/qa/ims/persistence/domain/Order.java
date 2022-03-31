package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private Customer customer_id;
	private Long id;
	private double totalprice;
	private String datePlaced;
	private List<Item> OrderItems = new ArrayList<>();

	public Order(Customer customer_id, Long id, double totalprice, String datePlaced) {
		super();
		this.customer_id = customer_id;
		this.id = id;
		this.totalprice = totalprice;
		this.datePlaced = datePlaced;
		

	}
	public Order( Long id, Customer customer_id, double totalprice, String datePlaced) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.totalprice = totalprice;
		this.datePlaced = datePlaced;
	}
	public Customer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getdatePlaced() {
		return datePlaced;
	}

	public void setdatePlaced(String datePlaced) {
		this.datePlaced = datePlaced;
	}

	public List<Item> getOrderItems() {
		return OrderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		OrderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [customer_id=" + customer_id + ", id=" + id + ", totalprice=" + totalprice + ", datePlaced=" + datePlaced
				+ ", OrderItems=" + OrderItems + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(OrderItems, customer_id, datePlaced, id, totalprice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(OrderItems, other.OrderItems) && Objects.equals(customer_id, other.customer_id)
				&& Objects.equals(datePlaced, other.datePlaced) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(totalprice) == Double.doubleToLongBits(other.totalprice);
	}

}