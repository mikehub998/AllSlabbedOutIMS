
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
        Double price = resultSet.getDouble("price");
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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.products");) {
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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.products ORDER BY product_id DESC LIMIT 1");) {
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
     * @param product - takes in a product object. product_id will be ignored
     */
    @Override
    public Product create(Product product) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO all_slabbed_out_db.products(product_name, stock_quantity, price) VALUES (?, ?, ?)");) {
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
    public Product read(Long productID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM all_slabbed_out_db.products WHERE product_id = ?");) {
            statement.setLong(1, productID);
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
                     .prepareStatement("UPDATE all_slabbed_out_db.products SET product_name = ?, stock_Quantity = ?, price = ? WHERE product_id = ?");) {
            statement.setString(1, product.getProductName());
            statement.setLong(2, product.getStockQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setLong(4, product.getProductId());
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
     * @param productId - product_id of the product
     */
    @Override
    public int delete(long productId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM all_slabbed_out_db.products WHERE product_id = ?");) {
            statement.setLong(1, productId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}