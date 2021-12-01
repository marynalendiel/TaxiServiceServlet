-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema taxiservice
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `taxiservice` ;

-- -----------------------------------------------------
-- Schema taxiservice
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxiservice` DEFAULT CHARACTER SET utf8 ;
USE `taxiservice` ;

-- -----------------------------------------------------
-- Table `mydb`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`language` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `language_code` VARCHAR(10) NOT NULL,
  `language_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `language_code_UNIQUE` (`language_code` ASC) VISIBLE,
  UNIQUE INDEX `language_name_UNIQUE` (`language_name` ASC) VISIBLE)
ENGINE = InnoDB;

USE `taxiservice` ;

-- -----------------------------------------------------
-- Table `taxiservice`.`car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`car` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`car` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `car_number` VARCHAR(45) NOT NULL,
  `number_of_seats` INT NOT NULL,
  `category` ENUM('standard', 'comfort', 'minivan') NOT NULL,
  `status` ENUM('to order', 'on the road', 'inactive') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `car_number_UNIQUE` (`car_number` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taxiservice`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`user` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL DEFAULT '0',
  `discount` INT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taxiservice`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`order` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_point` VARCHAR(200) NOT NULL,
  `finish_point` VARCHAR(200) NOT NULL,
  `distance` DOUBLE NOT NULL,
  `price` DOUBLE NOT NULL,
  `number_of_passengers` INT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `taxiservice`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taxiservice`.`car_has_language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`car_has_language` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`car_has_language` (
  `car_id` INT UNSIGNED NOT NULL,
  `language_id` INT NOT NULL,
  `car_description` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`car_id`, `language_id`),
  INDEX `fk_car_has_language_language1_idx` (`language_id` ASC) VISIBLE,
  INDEX `fk_car_has_language_car1_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_has_language_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxiservice`.`car` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_car_has_language_language1`
    FOREIGN KEY (`language_id`)
    REFERENCES `taxiservice`.`language` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `taxiservice`.`order_has_car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taxiservice`.`order_has_car` ;

CREATE TABLE IF NOT EXISTS `taxiservice`.`order_has_car` (
  `order_id` INT UNSIGNED NOT NULL,
  `car_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `car_id`),
  INDEX `fk_order_has_car_car1_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_order_has_car_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_car_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `taxiservice`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_has_car_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxiservice`.`car` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- ------------------------------------------------------------------------------------------------
-- insert languages
INSERT INTO taxiservice.`language`(`language_code`, `language_name`)
VALUES
('uk', 'Ukrainian'),
('en', 'English');

-- insert cars
INSERT INTO taxiservice.car(`brand`, `model`, `car_number`, `number_of_seats`, `category`, `status`)
VALUES
('Hyundai', 'Accent', 'AA 0000 AA', 4, 'comfort', 'to order'),
('Mercedes', 'A-Class', 'BB 0000 BB', 4, 'comfort', 'to order'),
('Audi', 'A8', 'OO 1111 OO', 4, 'comfort', 'to order'),

('Skoda', 'Octavia', 'KK 0000 KK', 4, 'standard', 'to order'),
('Ford', 'Focus', 'II 0000 II', 4, 'standard', 'to order'),
('Kia', 'Rio', 'XX 0000 XX', 4, 'standard', 'to order'),

('Toyota', 'Sienna', 'MM 0000 MM', 6, 'minivan', 'to order'),
('Kia', 'Carnival', 'PP 0000 PP', 10, 'minivan', 'to order'),
('Honda', 'Odyssey', 'TT 0000 TT', 7, 'minivan', 'to order');

-- insert descriptions
INSERT INTO taxiservice.car_has_language(`car_id`, `language_id`, `car_description`)
VALUES
(1, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(1, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(2, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(2, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(3, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(3, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(4, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(4, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(5, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(5, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(6, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(6, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(7, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(7, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(8, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(8, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car'),

(9, 1, 'Практичність, комфорт і багажник автомобіля забезпечують відмінну поїздку з комфортною їздою. Надійність і безпека забезпечили покращений рівень стандартного комплекту безпеки для автомобіля'),
(9, 2, 'Practicality, comfort and boot space car provides excellent trip with a comfortable ride and clever touches. Reliability and safety has delivered improved levels of standard safety kit for car');