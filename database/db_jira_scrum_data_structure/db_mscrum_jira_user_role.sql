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
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id_user` binary(16) NOT NULL,
  `id_role` binary(16) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  UNIQUE KEY `UKa1v0n5ie1kgbgohak2m4trk82` (`id_user`,`id_role`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`),
  CONSTRAINT `FK2aam9nt2tv8vcfymi3jo9c314` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (_binary 'N\∆\Ë˜cM›â^π\0ö\Ï1£',_binary 'Q\≈Ú¸\ÂîÇ ÜaUÑòm'),(_binary '*Å˛l˛H@ãÒÉI\ÕBÆü',_binary 'Q\≈Ú¸\ÂîÇ ÜaUÑòm'),(_binary 'N\∆\Ë˜cM›â^π\0ö\Ï1£',_binary 'Q\≈¯Ä\ÂîÇ ÜaUÑòm'),(_binary 'Øe∫ÿôlDíóG≠®,\À',_binary 'Q\≈¯Ä\ÂîÇ ÜaUÑòm'),(_binary 'nq<0 \ÊJtä^DX™',_binary 'S\ÎF	\À6Dó¶8<˚]\È'),(_binary '\ƒj\Â_\n@8áØÜ¶æu\‹',_binary '\‹4±N–ØN\ÂãˇQ\Ë\√ı®'),(_binary '¨:\ÍΩl^F|£ïzÖúW<',_binary '\‹4±N–ØN\ÂãˇQ\Ë\√ı®'),(_binary ']í∆Ñ\…\ÈD|™\„˛fÜY\∆',_binary '˜0¡ÚFà±p‘æëÿóE'),(_binary 'n\\	kWºKyï`4ÛD\«',_binary '˜0¡ÚFà±p‘æëÿóE'),(_binary '¨:\ÍΩl^F|£ïzÖúW<',_binary '˜0¡ÚFà±p‘æëÿóE'),(_binary 'øãîõ-®Jä†\ﬂhnug ',_binary '˜0¡ÚFà±p‘æëÿóE');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
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
