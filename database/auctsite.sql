CREATE DATABASE auctsite;

USE auctsite;

CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` varchar(30) NOT NULL UNIQUE,
	`password` varchar(255) NOT NULL,
	`name` varchar(20) NOT NULL,
	`surname` varchar(20) NOT NULL,
	`phone_number` varchar(12),
	`city` varchar(20) NOT NULL,
	`role` varchar(20) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `announcement` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(50) NOT NULL,
	`description` varchar(1000) NOT NULL,
	`price` DECIMAL(10,2) NOT NULL,
	`user_id` INT NOT NULL,
	`status` varchar(20) NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`category_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `image` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`image` MEDIUMTEXT NOT NULL,
	`announcement_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `category` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`category_name` varchar(30) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `message` (
	`id` INT NOT NULL auto_increment,
	`announcement_id` INT NOT NULL,
	`owner_id` INT NOT NULL,
	`client_id` INT NOT NULL,
	`message` varchar(1000) NOT NULL,
    primary key (`id`)
);

ALTER TABLE `announcement` ADD CONSTRAINT `announcement_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

ALTER TABLE `announcement` ADD CONSTRAINT `announcement_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);

ALTER TABLE `image` ADD CONSTRAINT `image_fk0` FOREIGN KEY (`announcement_id`) REFERENCES `announcement`(`id`);

ALTER TABLE `message` ADD CONSTRAINT `message_fk0` FOREIGN KEY (`announcement_id`) REFERENCES `announcement`(`id`);

ALTER TABLE `message` ADD CONSTRAINT `message_fk1` FOREIGN KEY (`owner_id`) REFERENCES `user`(`id`);

ALTER TABLE `message` ADD CONSTRAINT `message_fk2` FOREIGN KEY (`client_id`) REFERENCES `user`(`id`);

INSERT INTO category VALUES ("Electronics", "Automotive", "Fashion",  "House & Garden", "Sport", "Music", "Education", "Real estate", "Kids", "Pets");
