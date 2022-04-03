package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void FirstTest() {
		Customer customer = new Customer(2L, "Peter", "Parker");
		Order order = new Order(1L, customer);
		assertEquals(Long.valueOf(1), order.getId());
		assertEquals(customer, order.getCustomer_id());

	}

	@Test
	public void SecondTest() {
		Customer customer = new Customer(1L, "Mohammed", "Ali");
		Item Gloves = new Item("Gloves", 4.99, 3L);
		Item Hat = new Item("Hat", 3, 2L);
		List<Item> Itemlist = new ArrayList<>();
		Itemlist.add(Gloves);
		Itemlist.add(Hat);
		double totalPrice = Gloves.getPrice() + Hat.getPrice();
		Order order = new Order(3L, customer, Itemlist, totalPrice);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalprice(), 0.01);
		assertEquals(Itemlist, order.getOrderItems());
		assertEquals(customer, order.getCustomer_id());
	}

	@Test
	public void ThirdTest() {
		Customer customer = new Customer(1L, "Mohammed", "Ali");
		Item Gloves = new Item("Gloves", 4.99, 3L);
		Item Hat = new Item("Hat", 3, 2L);
		List<Item> Itemlist = new ArrayList<>();
		Itemlist.add(Gloves);
		Itemlist.add(Hat);
		double totalPrice = Gloves.getPrice() + Hat.getPrice();
		Order order = new Order(customer, 3L, totalPrice, Itemlist);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalprice(), 0.01);
		assertEquals(Itemlist, order.getOrderItems());
		assertEquals(customer, order.getCustomer_id());
	}

	@Test
	public void FourthTest() {
		Customer customer = new Customer(1L, "Mohammed", "Ali");
		Item Gloves = new Item("Gloves", 4.99, 3L);
		Item Hat = new Item("Hat", 3, 2L);
		double totalPrice = Gloves.getPrice() + Hat.getPrice();
		Order order = new Order(3L, customer, totalPrice);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalprice(), 0.01);
		assertEquals(customer, order.getCustomer_id());
	}

	@Test
	public void FifthTest() {
		Customer customer = new Customer(3L, "Peter", "Parker");
		Order order = new Order(customer);
		assertEquals(customer, order.getCustomer_id());
	}

	@Test
	public void SixthTest() {
		Order order = new Order();
		assertNotNull(order);
	}
	@Test
	public void EighthTest() {
		Order order = new Order();
		assertNotNull(order);
	}
	

	@Test
	public void SeventhTest() {
		Item Gloves = new Item("Gloves", 4.99, 3L);
		Item Hat = new Item("Hat", 3, 2L);
		List<Item> Itemlist = new ArrayList<>();
		Itemlist.add(Gloves);
		Itemlist.add(Hat);
		Order order = new Order(3L, Itemlist);
		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(Itemlist, order.getOrderItems());
	}

	@Test
	public void toStringTEST() {
		Customer customer = new Customer(3L, "Johnny", "Cash");
		Item Gloves = new Item(2L, "Gloves", 4.99, 3L);
		Item Hat = new Item(3L, "Hat", 3, 2L);
		List<Item> Itemlist = new ArrayList<>();
		Itemlist.add(Gloves);
		Itemlist.add(Hat);
		double totalPrice = Gloves.getPrice() + Hat.getPrice();
		Order order = new Order(3L, customer, Itemlist, totalPrice);

		assertEquals(
				"Order [id=3, customer_id=id:3 first name:Johnny surname:Cash, totalprice=7.99, OrderItems=[Item [id=2, name=Gloves, price=4.99, stock=3], Item [id=3, name=Hat, price=3.0, stock=2]]]",
				order.toString());
	}
}
