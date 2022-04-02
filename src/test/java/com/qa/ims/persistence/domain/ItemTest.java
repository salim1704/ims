package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}

	@Test
	public void FirstConstructorTest() {
		Item item = new Item("PS5", 500, 3L);
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3,item.getStock(), 1);
	
	}

	@Test
	public void SecondConstructorTest() {
		Item item = new Item(1L, "PS5", 500, 3L);
		assertEquals(Long.valueOf("1"), item.getId());
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3, item.getStock(),1);
	}


	@Test
	public void toStringTEST() {
		Item item = new Item(1L, "PS5", 500, 3L);
		String expected = "id=1 name= PS5 price= 500 stock=3";
		assertEquals(expected, item.toString());
	}
}
