//package com.qa.ims.persistence.dao;
//
//import com.qa.ims.persistence.domain.Product;
//
//import java.util.logging.LogManager;
//
//public class ProductDAO implements Dao<Product> {
//    public static final Logger Logger = LogManager.getlogger();
//}
package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Dao<Product> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Product modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long productId = resultSet.getLong("product_id");
        String productName = resultSet.getString("product_name");
        Long stockQuantity = resultSet.getLong("stock_quantity");
        double price = resultSet.getDouble("price");
        return new Product(productId, productName, stockQuantity, price);
    }

    /**
     * Read all products within the database
     *
     * @return A list of products
     */
    @Override
    public List<Product> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products");) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(modelFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Product readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates an item within the database
     *
     * @param product - takes in a customer object. id will be ignored
     */
    @Override
    public Product create(Product product) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO products(product_name, stock_quantity, price) VALUES (?, ?, ?)");) {
            statement.setString(1, product.getProductName());
            statement.setLong(2, product.getStockQuantity());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE product_id = ?");) {
            statement.setLong(1, id);
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
     * Updates a product in the database
     *
     * @param product - takes in a product object, the product_id field will be used to
     *                 update that product within the database
     * @return
     */
    @Override
    public Product update(Product product) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE customers SET product_name = ?, stock_Quantity = ?, price = ?, WHERE product_id = ?");) {
            statement.setString(1, product.getProductName());
            statement.setLong(2, product.getStockQuantity());
            statement.setDouble(2, product.getStockQuantity());
            statement.setLong(3, product.getProductId());
            statement.executeUpdate();
            return read(product.getProductId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a product in the database
     *
     * @param productId - id of the customer
     */
    @Override
    public int delete(long productId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE product_id = ?");) {
            statement.setLong(1, productId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}