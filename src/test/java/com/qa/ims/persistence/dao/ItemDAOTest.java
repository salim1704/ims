package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(3L, "PS5", 500D, 2L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "PS5", 500D, 2L));
		expected.add(new Item(2L, "Blu-Ray", 10.99, 6L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(2L, "Blu-Ray", 10.99, 6L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long id = 1L;
		assertEquals(new Item (1L, "PS5", 500D, 2L), DAO.read(id));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L,"PS5", 500.0, 2L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}