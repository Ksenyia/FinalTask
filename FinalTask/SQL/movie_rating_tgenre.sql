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
-- Table structure for table `tgenre`
--

DROP TABLE IF EXISTS `tgenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tgenre` (
  `id_tgenre` int(11) NOT NULL AUTO_INCREMENT,
  `genre_id_genre` int(11) NOT NULL COMMENT 'Ключ жанра',
  `tgerne` varchar(45) DEFAULT NULL COMMENT 'Переведенный жанр',
  `lang_id_lang` char(2) NOT NULL,
  PRIMARY KEY (`id_tgenre`,`lang_id_lang`,`genre_id_genre`),
  KEY `fk_tgenre_genre1_idx` (`genre_id_genre`),
  KEY `fk_tgenre_lang1_idx` (`lang_id_lang`),
  CONSTRAINT `fk_tgenre_lang1` FOREIGN KEY (`lang_id_lang`) REFERENCES `lang` (`id_lang`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Локализация жанров';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tgenre`
--

LOCK TABLES `tgenre` WRITE;
/*!40000 ALTER TABLE `tgenre` DISABLE KEYS */;
INSERT INTO `tgenre` VALUES (1,1,'фантастика','ru'),(2,2,'приключения','ru'),(3,3,'космическая опера','ru'),(4,4,'научная фантастика','ru'),(5,5,'фэнтези','ru'),(6,6,'драма','ru'),(7,7,'комедия','ru'),(8,8,'детектив','ru'),(9,9,'триллер','ru'),(10,10,'фильм ужасов','ru'),(11,1,'Speculative fiction','en'),(12,2,'Adventure','en'),(13,3,'Space opera','en'),(14,4,'Science fiction','en'),(15,5,'Fantacy','en'),(16,6,'Drama','en'),(17,7,'Comedy','en'),(18,8,'Detective','en'),(19,9,'Thriller','en'),(20,10,'Horror','en');
/*!40000 ALTER TABLE `tgenre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 21:53:30
