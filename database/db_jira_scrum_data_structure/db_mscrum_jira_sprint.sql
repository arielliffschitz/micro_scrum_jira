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
-- Table structure for table `sprint`
--

DROP TABLE IF EXISTS `sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprint` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `end_date` datetime(6) NOT NULL,
  `sprint_key` int NOT NULL AUTO_INCREMENT,
  `start_date` datetime(6) NOT NULL,
  `sprint_state` enum('ACTIVE','ARCHIVED','CANCELED','COMPLETED','PLANNED') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `team_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `project_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sprint_key_UNIQUE` (`sprint_key`),
  KEY `FKerwve0blrvfhqm1coxo69f0xr` (`project_id`),
  CONSTRAINT `FKerwve0blrvfhqm1coxo69f0xr` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint`
--

LOCK TABLES `sprint` WRITE;
/*!40000 ALTER TABLE `sprint` DISABLE KEYS */;
INSERT INTO `sprint` VALUES (_binary '\'»™CpC ™\â·\ë–','2026-02-09 14:39:40.128749','josemaria@email.com',NULL,NULL,'2026-02-24 17:00:00.000000',1,'2026-02-10 08:00:00.000000','PLANNED','TEAM_ALPHA',_binary '[¿–º13L\á¤`~?ü\Ç['),(_binary '~Ö—‘\ì@ü–‘¾M\Ã\ßZ','2026-02-09 14:58:53.256931','josemaria@email.com',NULL,NULL,'2026-03-30 16:00:00.000000',5,'2026-03-16 08:00:00.000000','PLANNED','TEAM_ALPHA',_binary '©ƒ\î-~OX¥3ûw{l'),(_binary '\Ö¸\Ð@8DW›œ;\ÊO-\î\å','2026-02-09 14:49:29.038204','josemaria@email.com',NULL,NULL,'2026-03-15 17:00:00.000000',2,'2026-03-01 08:00:00.000000','PLANNED','TEAM_ALPHA',_binary '­\Ï\é¾\"\ÏD}¢7Bºr\Ø\ãþ'),(_binary 'õÀ\ÞÍ¿hA«š?\Ï\"2Â“','2026-02-09 14:58:08.746193','josemaria@email.com',NULL,NULL,'2026-04-30 16:00:00.000000',3,'2026-04-16 07:00:00.000000','PLANNED','TEAM_ALPHA',_binary '[¿–º13L\á¤`~?ü\Ç[');
/*!40000 ALTER TABLE `sprint` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-24 10:23:52
