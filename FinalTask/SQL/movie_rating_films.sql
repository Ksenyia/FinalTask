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
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `films` (
  `id_film` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `realese_year` year(4) DEFAULT NULL COMMENT 'Год выход на экран',
  `films_rating` decimal(10,1) DEFAULT NULL COMMENT 'Рейтинг фильма (средний из поставленных пользавателями) \nНе создан индекс к рейтингу ( хотя по нему часто ищут) т.к. рейтинг часто меняют',
  `description` text COMMENT 'Краткое описание ',
  PRIMARY KEY (`id_film`),
  KEY `year` (`realese_year`) COMMENT 'Фильмы могут искать по году\nгод не меняется'
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='Фильмы';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (1,1999,7.2,NULL),(2,2002,7.7,NULL),(3,2005,9.0,NULL),(4,1977,NULL,NULL),(5,1980,9.5,NULL),(6,1983,8.0,NULL),(7,2001,NULL,NULL),(8,2002,NULL,NULL),(9,2003,NULL,NULL),(10,NULL,6.5,NULL),(11,1999,9.0,NULL),(12,1999,NULL,NULL),(13,2002,NULL,NULL),(14,2005,NULL,NULL),(15,1977,NULL,NULL),(16,1980,NULL,NULL),(17,1983,NULL,NULL),(18,2001,NULL,NULL),(19,2002,NULL,NULL),(20,2003,NULL,NULL),(21,NULL,NULL,NULL),(22,1999,NULL,NULL);
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 21:53:36
