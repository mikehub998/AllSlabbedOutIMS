
package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderlineDAO implements Dao<Orderline> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Orderline modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderlineId = resultSet.getLong("orderline_id");
        Long productId = resultSet.getLong("product_id");
        float amount = resultSet.getFloat("amount");
        double orderlineTotal = resultSet.getDouble("orderline_total");
        return new Orderline(orderlineId, productId, amount, orderlineTotal);
    }

    /**
     * Read all orderlines within the database
     *
     * @return list of orderlines
     */
    @Override
    public List<Orderline> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.orderlines");) {
            List<Orderline> orderlines = new ArrayList<>();
            while (resultSet.next()) {
                orderlines.add(modelFromResultSet(resultSet));
            }
            return orderlines;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Orderline readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM all_slabbed_out_db.orderlines ORDER BY orderline_id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates an orderline within the database
     *
     * @param orderline - takes in an orderline object. orderline_id will be ignored
     */
    @Override
    public Orderline create(Orderline orderline) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO all_slabbed_out_db.orderlines(product_id, amount, orderline_total) VALUES (?, ?, ?)");) {
            statement.setLong(1, orderline.getProductId());
            statement.setFloat(2, orderline.getAmount());
            statement.setDouble(3, orderline.getOrderlineTotal());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Orderline read(Long productID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM all_slabbed_out_db.orderlines WHERE orderline_id = ?");) {
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
     * Updates an orderline in the database
     *
     * @param orderline - takes in an orderline object, the orderline_id field will be used to
     *                 update that orderline within the database
     * @return
     */
    @Override
    public Orderline update(Orderline orderline) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE all_slabbed_out_db.orderlines SET product_id = ?, amount = ?, orderline_total = ? WHERE orderline_id = ?");) {
            statement.setLong(1, orderline.getProductId());
            statement.setFloat(2, orderline.getAmount());
            statement.setDouble(3, orderline.getOrderlineTotal());
            statement.setLong(4, orderline.getOrderlineId());
            statement.executeUpdate();
            return read(orderline.getOrderlineId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes an orderline from the database
     *
     * @param orderlineId - orderline_id of the product
     */
    @Override
    public int delete(long orderlineId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM all_slabbed_out_db.orderlines WHERE orderline_id = ?");) {
            statement.setLong(1, orderlineId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}