package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderlineDAO;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Takes in orderline details for CRUD functionality
 *
 */
public class OrderlineController implements CrudController<Orderline> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderlineDAO orderlineDAO;
    private Utils utils;

    public OrderlineController(OrderlineDAO orderlineDAO, Utils utils) {
        super();
        this.orderlineDAO = orderlineDAO;
        this.utils = utils;
    }

    /**
     * Reads all orderlines to the logger
     */
    @Override
    public List<Orderline> readAll() {
        List<Orderline> orderlines = orderlineDAO.readAll();
        for (Orderline orderline: orderlines) {
            LOGGER.info(orderline);
        }
        return orderlines;

    }


    /**
     * Creates an orderline from user input
     */
    @Override
    public Orderline create() {
        ProductDAO productDAO = new ProductDAO();
        LOGGER.info("Please enter the product id to add to your order:");
        Long productId = utils.getLong();
        LOGGER.info("Please enter the amount needed for the order:");
        float amount = utils.getFloat();
        Product product = productDAO.read(productId);
        double orderlineTotal = (product.getPrice()) * amount;
        Orderline orderline = orderlineDAO.create(new Orderline(productId, amount, orderlineTotal));
        LOGGER.info("Orderline created:");
        return orderline;
    }

    /**
     * Updates an existing product by taking in user input
     */
    @Override
    public Orderline update() {
        ProductDAO productDAO = new ProductDAO();
        LOGGER.info("Please enter the orderline id of the orderline that requires updating:");
        Long orderlineId = utils.getLong();
        LOGGER.info("Please enter the product Id that needs updating:");
        Long productId = utils.getLong();
        LOGGER.info("Please enter the required amount:");
        float amount = utils.getFloat();
        Product product = productDAO.read(productId);
        double orderlineTotal = (product.getPrice()) * amount;
        Orderline orderline = orderlineDAO.update(new Orderline(orderlineId,productId, amount, orderlineTotal));
        LOGGER.info("Product Updated:");
        return orderline;
    }

    /**
     * Deletes an existing product by the product_id
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the product you would like to delete:");
        Long orderlineId = utils.getLong();
        return orderlineDAO.delete(orderlineId);
    }

}