package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

	@Test
	public void firstConstructorTEST() {
		Customer customer = new Customer("Peter", "Parker");
		assertEquals("Peter", customer.getFirstName());
		assertEquals("Parker", customer.getSurname());
 
	}

	@Test
	public void secondConstructorTEST() {
		Customer customer = new Customer(1L, "Tony", "Stark");
		assertEquals(Long.valueOf("1"), customer.getId());
		assertEquals("Tony", customer.getFirstName());
		assertEquals("Stark", customer.getSurname());

	}
	
	@Test
	public void toStringTEST() {
		Customer customer = new Customer(1L, "Peter", "Parker");
		String expected = "id:1 first name:Peter surname:Parker";
		assertEquals(expected, customer.toString());
	}

}
