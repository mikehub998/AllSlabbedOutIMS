package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;

    public OrderController(OrderDAO orderDAO, Utils utils) {
        super();
        this.orderDAO = orderDAO;
        this.utils = utils;
    }

    /**
     * Reads all orders to the logger
     */
    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            LOGGER.info(order);
        }
        return orders;

    }


    /**
     * Creates an order from user input
     */
    @Override
    public Order create() {
        LOGGER.info("Please enter the customer id to associate with the order:");
        Long customerId = utils.getLong();
        LOGGER.info("Please enter the orderline id number to add to the order:");
        Long orderlineId = utils.getLong();
        LOGGER.info("Please enter the date order was placed:");
        String datePlaced = utils.getString();
        LOGGER.info("Order total:");
        Double orderTotal = utils.getDouble();
        Order order = orderDAO.create(new Order(orderlineId, customerId, datePlaced, orderTotal));
        LOGGER.info("Order created:");
        return order;
    }

    /**
     * Updates an existing order from user input
     */
    @Override
    public Order update() {
        LOGGER.info("Please enter the order id of the order that requires updating:");
        Long orderId = utils.getLong();
        LOGGER.info("Please enter the orderline id number:");
        Long orderlineId = utils.getLong();
        LOGGER.info("Please enter the customer Id number:");
        Long customerId = utils.getLong();
        LOGGER.info("Please enter the date the order was placed:");
        String datePlaced = utils.getString();
//        possibly a string here
        Double orderTotal = utils.getDouble();
        Order order = orderDAO.update(new Order(orderId, orderlineId,customerId, datePlaced, orderTotal));
        LOGGER.info("Order Updated:");
        return order;
    }

    /**
     * Deletes an existing order by the order_id
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete:");
        Long orderId = utils.getLong();
        return orderDAO.delete(orderId);
    }

}