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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `display_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (_binary '\ƒj\Â_\n@8áØÜ¶æu\‹','2026-02-08 15:32:01.960913','josemaria@email.com',NULL,_binary '','Mar√≠a Garc√≠a','$2a$10$qv1hkd92QBGCU8uUpDN1DeyXuju07TqWCOmz6GijmLlAnJlh3Le1C','scrum.maria.garcia@email.com',NULL,NULL),(_binary 'N\∆\Ë˜cM›â^π\0ö\Ï1£','2026-01-23 09:05:58.331499','maria@example.com','2026-02-23 08:31:48.811930',_binary '','Jose Maria','$2a$10$YWVw3bvA1/Yq66hs.EbN5uOR1A0faCWhIo3Jm.jC.AzgXGEyzudg2','josemaria@email.com',NULL,NULL),(_binary '*Å˛l˛H@ãÒÉI\ÕBÆü','2026-02-08 15:30:33.944472','josemaria@email.com',NULL,_binary '','Alex Morgan','$2a$10$R.ps2EocZhs2r4kf5qeeyuK5p9yScnhdpDZ2xPkAZdP7kJk/oU./W','admin.alex.morgan@email.com',NULL,NULL),(_binary ']í∆Ñ\…\ÈD|™\„˛fÜY\∆','2026-02-08 15:31:34.421182','josemaria@email.com',NULL,_binary '','Ana Silva','$2a$10$emsIZopnX/vtlbk6OfXLgeEenGmg4HQNWzFvBqN3I.8HQx8u78TVi','developer.ana.silva@email.com',NULL,NULL),(_binary 'n\\	kWºKyï`4ÛD\«','2026-02-08 15:31:17.921350','josemaria@email.com',NULL,_binary '','Jos√© Mart√≠nez','$2a$10$Bm6t5sf3D6hI1lfDnt/RIeeHCjmvy.nUTOilc6mDwqes1ub8Ypuwq','developer.jose.martinez@email.com',NULL,NULL),(_binary 'nq<0 \ÊJtä^DX™','2026-02-08 15:32:13.266096','josemaria@email.com',NULL,_binary '','Carlos P√©rez','$2a$10$0umlHAuPWL3BOPdq35mUG.MGvUgXzC.Z8dFM5scYaenUE5baqSfsm','product_owner.carlos.perez@email.com',NULL,NULL),(_binary '¨:\ÍΩl^F|£ïzÖúW<','2026-02-08 15:32:31.156771','josemaria@email.com',NULL,_binary '','Sofia Khan','$2a$10$EsWLmI2kJIW7a/K8DVvWjOn.F8TxfIIoUsUtQWAt6jn/duqDsqTru','developer_scrum.sofia.khan@email.com',NULL,NULL),(_binary 'Øe∫ÿôlDíóG≠®,\À','2026-02-08 15:30:54.270171','josemaria@email.com',NULL,_binary '','Luc√≠a Fern√°ndez','$2a$10$z8BIYpRK08C/RQRIDb/5BOk8FV/M0xgr5Ffmekkw.hcjAd5WnQ9mO','user.lucia.fernandez@email.com',NULL,NULL),(_binary 'øãîõ-®Jä†\ﬂhnug ','2026-02-08 15:31:49.980140','josemaria@email.com',NULL,_binary '','Li Wei','$2a$10$RYKJtO/GmKlVXfyWl36dI.QogyUM0aGPS1cJMfr5giVZs79O35Vc6','developer.li.wei@email.com',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
