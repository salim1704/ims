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
	public void FirstTest() {
		Item item = new Item("PS5", 500, 3L);
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3, item.getStock(), 1);

	}

	@Test
	public void SecondTest() {
		Item item = new Item(1L, "PS5", 500, 3L);
		assertEquals(Long.valueOf("1"), item.getId());
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3, item.getStock(), 1);
	}

	@Test
	public void ThirdTest() {
		Item item = new Item("PS5", 500, 3L, 1L);
		assertEquals(Long.valueOf(1), item.getId());
		assertEquals("PS5", item.getName());
		assertEquals(500, item.getPrice(), 0.02);
		assertEquals(3, item.getStock(), 1);
	}

	@Test
	public void setIdTEST() {
		Item item = new Item(1L, "hat", 100, 3L);
		item.setId(2L);
		assertEquals(Long.valueOf(2), item.getId());

	}

	@Test
	public void setNameTEST() {
		Item item = new Item(1L, "ball", 100, 2L);
		item.setName("ball");
		assertEquals("ball", item.getName());

	}

	@Test
	public void setPriceTEST() {
		Item item = new Item(1L, "tv", 100, 3L);
		item.setPrice(200);
		assertEquals(200, item.getPrice(), 0.1);

	}

	@Test
	public void toStringTEST() {
		Item item = new Item(1L, "PS5", 500D, 3L);
		String expected = "Item [id=1, name=PS5, price=500.0, stock=3]";
		assertEquals(expected, item.toString());
	}
}
