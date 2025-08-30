-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `products_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `description` text,
  `category` varchar(50) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  PRIMARY KEY (`products_id`),
  KEY `fk_seller` (`seller_id`),
  CONSTRAINT `fk_seller` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Wireless Earbuds Pro',499000,'Bluetooth earbuds with noise cancellation.','Electronics','img/Earbuds1.jpg',2),(2,'Smartphone Case - Transparent',99000,'Durable case for smartphones.','Accessories','img/Case1.jpg',3),(3,'Men\'s Casual T-Shirt',129000,'Cotton t-shirt in various colors.','Fashion','img/Tee1.jpg',4),(4,'Women\'s Handbag - Leather',359000,'Elegant leather handbag.','Fashion','img/LeatherBag1.jpg',5),(5,'LED Desk Lamp',189000,'Touch control lamp with USB port.','Home & Living','img/Lamp1.jpg',6),(6,'Power Bank 10000mAh',249000,'Fast-charging power bank.','Electronics','img/Powerbank1.jpg',7),(7,'Facial Cleanser Foam',79000,'Gentle foam for all skin types.','Beauty','img/Soap1.jpg',8),(8,'Wireless Mouse - Ergonomic',159000,'Ergonomic mouse for daily use.','Electronics','img/Ergomouse1.jpg',9),(9,'Stainless Steel Water Bottle',119000,'Keeps drinks hot or cold.','Home & Living','img/stainlesssteelbottle1.jpg',10),(10,'Bluetooth Speaker Mini',299000,'Compact speaker with deep bass.','Electronics','img/bluetoothspeakermini1.jpg',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-30 22:43:17
