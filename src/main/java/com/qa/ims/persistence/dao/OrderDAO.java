package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long customerId = resultSet.getLong("customer_id");
        Long productId = resultSet.getLong("product_id");
        String datePlaced = resultSet.getString("date_placed");
        Long orderTotal = resultSet.getLong("total_order");
        return new Order(orderId, customerId, productId, datePlaced, orderTotal);
    }

    /**
     * Read all orders within the database
     *
     * @return A list of orders
     */
    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.orders");) {
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

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.orders ORDER BY order_id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates an order within the database
     *
     * @param order - takes in an order object. order_id will be ignored
     */
    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO all_slabbed_out_db.orders(customer_id, product_id, date_placed, total_order) VALUES (?, ?, ?, ?)");) {
            statement.setLong(1, order.getCustomerId());
            statement.setLong(2, order.getProductId());
            statement.setString(3, order.getDatePlaced());
            statement.setLong(4,order.getOrderTotal());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order read(Long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM all_slabbed_out_db.orders WHERE order_id = ?");) {
            statement.setLong(1, orderId);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Updates an order within the database
     *
     * @param order - takes in an order object, the order_id field will be used to
     *                 update that product within the database
     * @return
     */
    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE all_slabbed_out_db.orders SET customer_id = ?, product_id = ?, date_placed =?, total_order = ? WHERE order_id = ?");) {
            statement.setLong(1, order.getCustomerId());
            statement.setLong(2, order.getProductId());
            statement.setString(3, order.getDatePlaced());
            statement.setLong(4, order.getOrderTotal());
            statement.setLong(5, order.getOrderId());
            statement.executeUpdate();
            return read(order.getOrderId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes an order in the database
     *
     * @param orderId - order_id of the product
     */
    @Override
    public int delete(long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM all_slabbed_out_db.orders WHERE order_id = ?");) {
            statement.setLong(1, orderId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}