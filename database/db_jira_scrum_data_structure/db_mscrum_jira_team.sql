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
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `team_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK15aonk88odrwfd1mwhxohkt6j` (`team_key`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (_binary '\n»\Å\îF£MÖªÅ¾*Ÿ™Kq','2026-02-08 15:52:33.797291','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','user.lucia.fernandez@email.com',_binary ''),(_binary 'H\Ñ^)­L\æ‘\Ëú\ã‹Ñœ','2026-02-08 15:56:29.713562','josemaria@email.com',NULL,NULL,'TEAM_ALPHA2','developer_scrum.sofia.khan@email.com',_binary '\0'),(_binary 'zÄŸXm<@g•]ŸõY\0s\Z','2026-02-08 15:54:22.306913','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','scrum.maria.garcia@email.com',_binary ''),(_binary 'Š °°KöG4¿ù³ÿ%©z','2026-02-08 15:56:46.913475','josemaria@email.com',NULL,NULL,'TEAM_ALPHA2','product_owner.carlos.perez@email.com',_binary '\0'),(_binary 'ù\ÑÁKrA­½¯búxü«','2026-02-08 15:52:54.172281','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','developer.jose.martinez@email.com',_binary ''),(_binary '“Ãª	\Ô\àBŠˆ\\\ä3Ï°•±','2026-02-08 15:53:41.568020','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','developer.ana.silva@email.com',_binary ''),(_binary '¹T(…\å*Go¼I£\Æ,T\r°','2026-02-08 15:54:07.193074','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','developer.li.wei@email.com',_binary ''),(_binary '\íD\ÍAñ÷O\â³Yi“\á','2026-02-08 15:54:37.863426','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','product_owner.carlos.perez@email.com',_binary ''),(_binary '÷þøb´”C€©¡\íaOh€','2026-02-08 15:55:01.291296','josemaria@email.com',NULL,NULL,'TEAM_ALPHA','developer_scrum.sofia.khan@email.com',_binary '');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
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
