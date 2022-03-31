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
		Long customer_id = resultSet.getLong("Customer_id");
		double totalprice = calculateTotalOrderCost(id);
		String datePlaced = resultSet.getString("datePlaced");
		Customer customer = customerDAO.read(customer_id);
		return new Order(id, customer, totalprice, datePlaced);
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");){
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
						"INSERT INTO orders(customers_id, total_price, datePlaced) VALUES (?, ?, ?)");) {
			statement.setLong(1, order.getCustomer_id().getId());
			statement.setDouble(2, order.getTotalprice());
			statement.setString(3, order.getdatePlaced());
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
						.prepareStatement("SELECT * FROM orders_items WHERE orders_id = ?");) {
			statement.setLong(1, orderId);
			ResultSet resultSet = statement.executeQuery();
			double totalOrderCost = 0;
			while (resultSet.next()) {
				totalOrderCost += itemDAO.read(resultSet.getLong("items_id")).getPrice();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from items where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
