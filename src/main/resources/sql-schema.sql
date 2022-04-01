drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL,
    `price` decimal (6,2) NOT NULL,
    `stock` bigint(11) not null,
    PRIMARY KEY (`id`)
);

    CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT,
    `customer_id` bigint(11) NOT NULL,
    `totalprice` decimal (6,2) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES customers(`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
    `order_id` BIGINT NOT NULL,
    `item_id` BIGINT NOT NULL,
    `quantity` INT DEFAULT 1,
    PRIMARY KEY (`order_id`, `item_id`),
    FOREIGN KEY (`order_id`) REFERENCES orders(`id`),
    FOREIGN KEY (`item_id`) REFERENCES items(`id`)
);

