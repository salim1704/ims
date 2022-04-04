package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the ID of the Customer placing the order.");
		Long customer_id = utils.getLong();
		CustomerDAO customerDAO = new CustomerDAO();
		Customer newCustomer = customerDAO.read(customer_id);
		Order order = orderDAO.create(new Order(newCustomer));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		Order order = new Order();
		LOGGER.info("Please enter the id of the order you would like to update.");
		Long order_id = utils.getLong();
		LOGGER.info("Would you like to ADD or REMOVE an item? Please enter in all capitals.");
		String choice = utils.getString();
		LOGGER.info("Please enter the ID of the item you would like to choose.");
		Long item_id = utils.getLong();
		if (choice.equals("ADD")) {
			order = orderDAO.addToOrder_NewUpdate(order_id, item_id);
			LOGGER.info("The item has been added to the order. Thank you!");
		} else if (choice.equals("REMOVE")) {
			order = orderDAO.removeFromOrder_NewUpdate(order_id, item_id);
			LOGGER.info("The item has been removed from order, Thank you!");
		}

		LOGGER.info("Order updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		LOGGER.info("Order deleted");
		return orderDAO.delete(id);
	}
}
