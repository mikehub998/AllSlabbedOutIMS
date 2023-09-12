drop schema all_slabbed_out_db;

CREATE SCHEMA IF NOT EXISTS all_slabbed_out_db;

USE all_slabbed_out_db;

CREATE TABLE IF NOT EXISTS all_slabbed_out.customers (
    id INT(10) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) DEFAULT NULL,
    surname VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (customer_id)
    );

CREATE TABLE If NOT EXISTS all_slabbed_out.products (
    product_id INT(20) NOT NULL,
    product_name VARCHAR(40) NOT NULL,
    stock_quantity INT(300) NOT NULL,
    price DECIMAL(6,2) DEFAULT NULL
    PRIMARY KEY (product_id)
    );

CREATE TABLE IF NOT EXISTS all_slabbed_out.orders (
    order_id INT(20) NOT NULL,
    customer_id INT(20) NOT NULL,
    product_id INT(20) NOT NULL,
    date_placed date NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
    );
