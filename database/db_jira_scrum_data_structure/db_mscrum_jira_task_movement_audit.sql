CREATE DATABASE  IF NOT EXISTS `db_mscrum_jira` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_mscrum_jira`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_mscrum_jira
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `task_movement_audit`
--

DROP TABLE IF EXISTS `task_movement_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_movement_audit` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `audit_task_state` enum('ARCHIVED','BLOCKED','CANCELED','DONE','IN_PROGRESS','MOVE_TO_PRODUCT','MOVE_TO_SPRINT','ON_HOLD','REVIEW') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `task_number` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_movement_audit`
--

LOCK TABLES `task_movement_audit` WRITE;
/*!40000 ALTER TABLE `task_movement_audit` DISABLE KEYS */;
INSERT INTO `task_movement_audit` VALUES (_binary '	=\Í\r¨ONÂ°|!ûnzó¿','2026-02-09 15:23:15.528916','josemaria@email.com','MOVE_TO_SPRINT',1),(_binary '{±—IŽœú<´’\\F','2026-02-09 15:20:57.578817','josemaria@email.com','MOVE_TO_SPRINT',1),(_binary '[\Zº\r~bNYk†¸IIš\Z','2026-02-09 15:31:51.180388','josemaria@email.com','IN_PROGRESS',4),(_binary 'c/÷»°L\àú\Â\Zw›','2026-02-09 15:22:57.793335','josemaria@email.com','MOVE_TO_PRODUCT',1),(_binary '\Ïv\Ï\Û\å›Ej·nˆULAK','2026-02-09 15:25:27.800435','josemaria@email.com','ARCHIVED',1),(_binary '\í\ìX|\ìD2‰¨\ç&Uø\Ì','2026-02-09 15:31:43.600860','josemaria@email.com','MOVE_TO_SPRINT',4);
/*!40000 ALTER TABLE `task_movement_audit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-24 10:23:53
