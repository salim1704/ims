INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `price`, `stock`) VALUES ('PS5', 500, 2);
INSERT INTO `items` (`name`, `price`, `stock`) VALUES ('blu-ray', 10.99, 6);
INSERT INTO `orders` (`customer_id`) VALUES (1);
INSERT INTO `order_items` (`order_id`, `item_id`) VALUES (1, 1);