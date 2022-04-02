package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private CustomerDAO customerDAO;

	public OrderDAO(CustomerDAO customerDAO, ItemDAO itemDAO) {
		super();
		this.itemDAO = itemDAO;
		this.customerDAO = customerDAO;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_id = resultSet.getLong("customer_id");
		List<Item> itemList = getItemsInOrder(id);
		double totalprice = calculateTotalOrderCost(id);
		Customer customer = customerDAO.read(customer_id);
		return new Order(id, customer, itemList, totalprice);
	}

	public List<Item> getItemsInOrder(Long orderId) {
		List<Item> ListOfItems = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ListOfItems.add(itemDAO.read(resultSet.getLong("item_id")));
			}
			return ListOfItems;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return ListOfItems;
	}

	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	private Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orders(customer_id, totalprice) VALUES (?, 0.0)");) {
			statement.setLong(1, order.getCustomer_id().getId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public double calculateTotalOrderCost(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			double totalOrderCost = 0;
			while (resultSet.next()) {
				totalOrderCost += itemDAO.read(resultSet.getLong("item_id")).getPrice();
			}
			return totalOrderCost;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0.0;
	}

	@Override
	public Order update(Order order) {
		return null;
	}

	public Order addToOrder_NewUpdate(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(order_id, item_id) VALUES (?, ?)");) {
			statement.setLong(1, orderId);
			statement.setLong(2, itemId);
			statement.executeUpdate();

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return read(orderId);

	}

	public Order removeFromOrder_NewUpdate(Long orderId, Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM order_items WHERE (order_id = ? AND item_id = ?)");

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return read(orderId);
	}

	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from items where id = " + orderId);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public int deleteOrderItems(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM order_items WHERE order_id = " + orderId);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order read(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders where id = ?");) {
			statement.setLong(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
