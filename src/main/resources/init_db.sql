-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema internet_shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema internet_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `internet_shop` DEFAULT CHARACTER SET utf8 ;
USE `internet_shop` ;

-- -----------------------------------------------------
-- Table `internet_shop`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `login` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `Login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 89
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`orders` (
  `user_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE,
  INDEX `fk_orders_users_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`products` (
  `product_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `id_UNIQUE` (`product_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 131
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`orders_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`orders_products` (
  `product_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  INDEX `fk_orders_products_products1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_orders_products_orders1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_products_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`roles` (
  `role_id` BIGINT NOT NULL,
  `role_name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_id_UNIQUE` (`role_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`shopping_carts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`shopping_carts` (
  `user_id` BIGINT NOT NULL,
  `shopping_cart_id` BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`shopping_cart_id`),
  INDEX `fk_shopping_carts_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_shopping_carts_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`shopping_carts_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`shopping_carts_products` (
  `shopping_cart_id` BIGINT NOT NULL,
  `product_id` BIGINT NULL DEFAULT NULL,
  INDEX `fk_shopping_carts_products_products1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_shopping_carts_products_shopping_carts1_idx` (`shopping_cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_shopping_carts_products_shopping_carts1`
    FOREIGN KEY (`shopping_cart_id`)
    REFERENCES `internet_shop`.`shopping_carts` (`shopping_cart_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `internet_shop`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `internet_shop`.`user_roles` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  INDEX `fk_user_roles_users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_user_roles_roles_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_roles_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `internet_shop`.`roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_roles_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
