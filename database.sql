-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.16 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных mobile_banking
CREATE DATABASE IF NOT EXISTS `mobile_banking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mobile_banking`;

-- Дамп структуры для таблица mobile_banking.client_accounts
CREATE TABLE IF NOT EXISTS `client_accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iban` varchar(13) NOT NULL,
  `balance` int(11) NOT NULL DEFAULT '0',
  `currency` varchar(5) NOT NULL,
  `last_operation_time` varchar(35) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iban_index` (`iban`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile_banking.client_accounts: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `client_accounts` DISABLE KEYS */;
INSERT INTO `client_accounts` (`id`, `iban`, `balance`, `currency`, `last_operation_time`) VALUES
	(1, 'MD77RS0056145', 17520, 'USD', '14.10.2021-12:10:45-GTM+3'),
	(2, 'MD77RS1059148', 90000, 'MDL', '13.08.2021-22:15:35-GTM+3'),
	(3, 'MD77RS2561133', 6700, 'EUR', '10.12.2020-15:20:05-GTM+3'),
	(5, 'MD77RS2536455', 10250, 'GBP', '12.10.2021-14.27.33-GTM+3'),
	(6, 'MD77RS5632880', 11600, 'MDL', '14.09.2021-17.27.00-GTM+3');
/*!40000 ALTER TABLE `client_accounts` ENABLE KEYS */;

-- Дамп структуры для процедура mobile_banking.sp_GetAccounts
DELIMITER //
CREATE PROCEDURE `sp_GetAccounts`()
BEGIN
    SELECT * FROM client_accounts;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
