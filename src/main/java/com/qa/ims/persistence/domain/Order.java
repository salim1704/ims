package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private Long id;
	private Customer customer_id;
	private double totalprice;
	private List<Item> OrderItems = new ArrayList<>();

	public Order() {
	}
	
	public Order(Long id, Customer customer_id, List<Item> orderItems, double totalPrice) {
		this.setId(id);
		this.setCustomer_id(customer_id);
		this.setOrderItems(orderItems);
		this.setTotalprice(totalPrice);
	}

	public Order(Customer customer_id, Long id, double totalprice, List<Item> orderItems) {
		this.setCustomer_id(customer_id);
		this.setId(id);
		this.setTotalprice(totalprice);
		this.setOrderItems(orderItems);

	}

	public Order(Long id, Customer customer_id, double totalprice) {
		this.setId(id);
		this.setCustomer_id(customer_id);
		this.setTotalprice(totalprice);
	}

	public Order(Customer customer_id) {
		this.setCustomer_id(customer_id);
	}

	public Order(Long id, Customer customer_id) {
		this.setId(id);
		this.setCustomer_id(customer_id);
	}

	public Order(Long id, List<Item> orderItems) {
		super();
		this.id = id;
		this.OrderItems = orderItems;
	}

	public Order(long id, long customer_id, double totalprice) {
	}

	public Order(Long customer_id) {
		// TODO Auto-generated constructor stub
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

	public List<Item> getOrderItems() {
		return OrderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		OrderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", totalprice=" + totalprice + ", OrderItems="
				+ OrderItems + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(OrderItems, customer_id, id, totalprice);
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
				&& Objects.equals(id, other.id)
				&& Double.doubleToLongBits(totalprice) == Double.doubleToLongBits(other.totalprice);
	}

}
