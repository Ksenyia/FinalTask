-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_rating
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tcountry`
--

DROP TABLE IF EXISTS `tcountry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcountry` (
  `id_tcountry` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `country_id_country` int(11) NOT NULL COMMENT 'Ключ страны',
  `tname_country` varchar(45) DEFAULT NULL COMMENT 'Переведенное название страны\n',
  `lang_id_lang` char(2) NOT NULL,
  PRIMARY KEY (`id_tcountry`,`country_id_country`,`lang_id_lang`),
  KEY `fk_tcountry_lang1_idx` (`lang_id_lang`),
  CONSTRAINT `fk_tcountry_lang1` FOREIGN KEY (`lang_id_lang`) REFERENCES `lang` (`id_lang`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Локализация для стран';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tcountry`
--

LOCK TABLES `tcountry` WRITE;
/*!40000 ALTER TABLE `tcountry` DISABLE KEYS */;
INSERT INTO `tcountry` VALUES (1,1,'США','ru'),(2,2,'Новая Зеландия','ru'),(3,3,'Великобритания','ru'),(4,4,'Германия','ru'),(5,1,'USA','en'),(6,2,'New Zealand','en'),(7,3,'United Kingdom','en'),(8,4,'Germany','en');
/*!40000 ALTER TABLE `tcountry` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 21:53:20
