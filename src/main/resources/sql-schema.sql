drop schema all_slabbed_out_db;

CREATE SCHEMA IF NOT EXISTS `all_slabbed_out_db`;

USE `all_slabbed_out_db`;

CREATE TABLE IF NOT EXISTS `all_slabbed_out_db`.`customers` (
    `customer_id` INT(10) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`customer_id`)
    );

CREATE TABLE If NOT EXISTS `all_slabbed_out_db`.`products` (
    `product_id` INT(20) NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(40) NOT NULL,
    `stock_quantity` INT(255) NOT NULL,
    `price` DECIMAL(6,2) DEFAULT NULL,
    PRIMARY KEY (`product_id`)
    );

CREATE TABLE IF NOT EXISTS `all_slabbed_out_db`.`orderlines` (
	`orderline_id` INT(20) NOT NULL AUTO_INCREMENT,
    `product_id` INT(20),
    `amount` INT(5),
    `orderline_total` DEC(6,2),
    PRIMARY KEY (`orderline_id`),
    FOREIGN KEY (`product_id`) REFERENCES products(`product_id`)
    );


CREATE TABLE IF NOT EXISTS `all_slabbed_out_db`.`orders` (
    `order_id` INT(20) NOT NULL AUTO_INCREMENT,
    `orderline_id` INT(20) NOT NULL,
    `customer_id` INT(20) NOT NULL,
    `date_placed` date NOT NULL,
    `order_total` DEC(6,2) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`orderline_id`) REFERENCES orderlines(`orderline_id`) ON DELETE CASCADE
    -- FOREIGN KEY (`product_id`) REFERENCES products(`product_id`)
    );
