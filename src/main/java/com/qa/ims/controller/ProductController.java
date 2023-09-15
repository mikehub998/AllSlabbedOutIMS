package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Takes in product details for CRUD functionality
 *
 */
public class ProductController implements CrudController<Product> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ProductDAO productDAO;
    private Utils utils;

    public ProductController(ProductDAO productDAO, Utils utils) {
        super();
        this.productDAO = productDAO;
        this.utils = utils;
    }

    /**
     * Reads all products to the logger
     */
    @Override
    public List<Product> readAll() {
        List<Product> products = productDAO.readAll();
        for (Product product : products) {
            LOGGER.info(product);
        }
        return products;

    }


    /**
     * Creates a product from user input
     */
    @Override
    public Product create() {
        LOGGER.info("Please enter which product you wish to create:");
        String productName = utils.getString();
        LOGGER.info("Please enter the amount needed in stock:");
        Long stockQuantity = utils.getLong();
        LOGGER.info("Please enter the price of the unit:");
        Double price = utils.getDouble();
        Product product = productDAO.create(new Product(productName, stockQuantity, price));
        LOGGER.info("Product created:");
        return product;
    }

    /**
     * Updates an existing product by taking in user input
     */
    @Override
    public Product update() {
        LOGGER.info("Please enter the product id of the product that requires updating:");
        Long productId = utils.getLong();
        LOGGER.info("Please enter the product name:");
        String productName = utils.getString();
        LOGGER.info("Please enter the required stock quantity:");
        Long stockQuantity = utils.getLong();
        LOGGER.info("Please enter price of the unit:");
        Double price = utils.getDouble();
        Product product = productDAO.update(new Product(productId, productName, stockQuantity, price));
        LOGGER.info("Product Updated:");
        return product;
    }

    /**
     * Deletes an existing product by the product_id
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the product you would like to delete:");
        Long productId = utils.getLong();
        return productDAO.delete(productId);
    }

}