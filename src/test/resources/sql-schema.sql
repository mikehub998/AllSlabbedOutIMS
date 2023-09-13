drop schema all_slabbed_out_db;

CREATE SCHEMA IF NOT EXISTS all_slabbed_out_db;

USE all_slabbed_out_db;

CREATE TABLE IF NOT EXISTS all_slabbed_out_db.customers (
    customer_id INT(10) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    surname VARCHAR(40) NOT NULL,
    PRIMARY KEY (customer_id)
    );