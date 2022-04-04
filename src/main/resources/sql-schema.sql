drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

use  `ims`;

DROP TABLE  order_items;
DROP TABLE  orders;
DROP TABLE  items;
DROP TABLE  customers;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(64) DEFAULT NULL,
`surname` VARCHAR(64) DEFAULT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(64) NOT NULL,
`price` DOUBLE NOT NULL,
`stock` BIGINT(11) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT NOT NULL,
`totalprice` DOUBLE NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`customer_id`) REFERENCES customers(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
`order_id` INT NOT NULL,
`item_id` INT NOT NULL,
FOREIGN KEY (`order_id`) REFERENCES orders(`id`) ON DELETE CASCADE,
FOREIGN KEY (`item_id`) REFERENCES items(`id`) ON DELETE CASCADE
);