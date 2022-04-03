INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `price`, `stock`) VALUES ('PS5', 500, 2);
INSERT INTO `orders` (`customers_id`) VALUES (1);
INSERT INTO `orders_items` (`orders_id`, `items_id`) VALUES (1, 1);