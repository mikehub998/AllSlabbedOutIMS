INSERT INTO `all_slabbed_out_db`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');

INSERT INTO `all_slabbed_out_db`.`products` (`product_name`, `stock_quantity`, `price`) VALUES ('pink slate', '300', '18');



--INSERT INTO `all_slabbed_out_db`.`products` (`product_name`, `stock_quantity`, `price`) VALUES ('black granite slab', '600', '19.24');
--INSERT INTO `all_slabbed_out_db`.`products` (`product_name`, `stock_quantity`, `price`) VALUES ('indian sandstone', '700', '13.79');
--INSERT INTO `all_slabbed_out_db`.`products` (`product_name`, `stock_quantity`, `price`) VALUES ('brazilian black slate', '500', '22.09');
--INSERT INTO `all_slabbed_out_db`.`products` (`product_name`, `stock_quantity`, `price`) VALUES ('valencia grey porcelain slab', '200', '11.90');
INSERT INTO `all_slabbed_out_db`.`orderlines` (`product_id`, `amount`, `orderline_total`) VALUES ('1', '200', '2380.00');
INSERT INTO `all_slabbed_out_db`.`orders` (`orderline_id`,`customer_id`, `date_placed`, `order_total`) VALUES ('1', '2', '13.06.23', '19.24');
