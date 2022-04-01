DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL,
    `price` decimal (4,2) NOT NULL,
    `stock` BIGINT,
    PRIMARY KEY (`id`)
);
    CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` bigint(11) NOT NULL AUTO_INCREMENT,
    `customer_id` bigint(11) NOT NULL,
    `totalprice` decimal (6,2) NOT NULL,
    `dateplaced` date not null,
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