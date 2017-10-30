CREATE DATABASE IF NOT EXISTS `BarberShopDB`;
USE `BarberSHopDB`;

CREATE TABLE IF NOT EXISTS `employee` (
`employee_id` int(3) NOT NULL AUTO_INCREMENT,
`first_name` varchar(35) NOT NULL,
`last_name` varchar(35) NOT NULL,
`phone_number` int(10) NOT NULL,
`email` varchar(35) NOT NULL,
`address` varchar(50) NOT NULL,
`experience` int(2) NOT NULL,
`age` int(2) NOT NULL,
`gender` varchar(1) NOT NULL,
PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `customer` (
`customer_id` int(12) NOT NULL,
`first_name` varchar(35) NOT NULL,
`last_name` varchar(35) NOT NULL,
`phone_number` int(10) NOT NULL,
PRIMARY KEY(`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `appointment` (
`appointment_id` int(12) NOT NULL AUTO_INCREMENT,
`employee_id` int(3) NOT NULL,
`customer_id` int(12) NOT NULL,
`app_time` TIME NOT NULL,
`app_date` DATE NOT NULL,
`price` DECIMAL(4,2),
`status` varchar(12),
PRIMARY KEY(`appointment_id`),
FOREIGN KEY(`employee_id`) REFERENCES `employee` (`employee_id`),
FOREIGN KEY(`customer_id`) REFERENCES `customer` (`customer_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;