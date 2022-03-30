package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private Customer customer_id;
	private Long id;
	private double totalprice;
	private String date;

	public Order(Customer customer_id, Long id, double totalprice, String date) {
		super();
		this.customer_id = customer_id;
		this.id = id;
		this.totalprice = totalprice;
		this.date = date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [customer_id=" + customer_id + ", id=" + id + ", totalprice=" + totalprice + ", date=" + date
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer_id, date, id, totalprice);
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
		return Objects.equals(customer_id, other.customer_id) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id)
				&& Double.doubleToLongBits(totalprice) == Double.doubleToLongBits(other.totalprice);
	}

}