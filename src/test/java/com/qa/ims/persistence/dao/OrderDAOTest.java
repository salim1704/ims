package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO DAO = new OrderDAO(new CustomerDAO(), new ItemDAO());
	private Order testOrder;
	private Item testItem;

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		List<Item> Itemlist = new ArrayList<>();
		testItem = new Item(1L, "PS5", 500D, 2L);
		Itemlist.add(testItem);
		Customer customer = new Customer(1L, "jordan", "harrison");
		testOrder = new Order(1L, customer, Itemlist, 500D);
	}

	@Test
	public void deleteTest() {
		assertEquals(0, DAO.deleteOrderItems(1L));
		assertEquals(0, DAO.delete(1L));
	}

	@Test
	public void createTest() {
		final Customer customer = new Customer(1L, "jordan", "harrison");
		final List<Item> Itemlist = new ArrayList<>();
		final Order newOrder = new Order(1L, customer, Itemlist, 0.0);
		assertEquals(newOrder, DAO.create(newOrder));
	}

	@Test
	public void readTest() {
		assertEquals(testOrder, DAO.read(1L));
	}

	@Test
	public void readLatestTest() {
		assertEquals(testOrder, DAO.readLatest());
		
	}
	@Test
	public void readAllTest() {
			List<Order> expected = new ArrayList<>();
			expected.add(testOrder);
			assertEquals(expected, DAO.readAll());
	}

	@Test
	public void updateTEST() {
		assertEquals(null, DAO.update(null));
	}

	@Test
	public void removeFromOrder_NewUpdateTEST() {
		testOrder.getOrderItems().remove(testItem);
		assertEquals(testOrder, DAO.removeFromOrder_NewUpdate(testOrder.getId(), testItem.getId()));
	}

}
