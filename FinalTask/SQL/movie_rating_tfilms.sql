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
-- Table structure for table `tfilms`
--

DROP TABLE IF EXISTS `tfilms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfilms` (
  `id_tfilm` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ключ переводимого фильма',
  `films_id_film` int(11) NOT NULL COMMENT 'Язык, на который переводим',
  `lang_id_lang` char(2) NOT NULL,
  `ttitle` varchar(200) DEFAULT NULL COMMENT 'Переведенное название фильма\n',
  `ttype` varchar(45) DEFAULT NULL COMMENT 'Переведенный тип фильма (полнометражный, односерийный и т.д.)\n',
  `tdirector` varchar(45) DEFAULT NULL COMMENT 'Переведенное имя режиссера',
  `tdescription` varchar(45) DEFAULT NULL COMMENT 'Описние фильма на языке локали',
  PRIMARY KEY (`id_tfilm`,`lang_id_lang`,`films_id_film`),
  KEY `fk_tfilms_lang1_idx` (`lang_id_lang`),
  KEY `fk_tfilms_films1_idx` (`films_id_film`),
  KEY `titlt` (`ttitle`) COMMENT 'Фильмы могут искать по названию\nназвание не меняется',
  KEY `type` (`ttype`) COMMENT 'Фильмы могут искать по типу\nрежиссер не тип',
  KEY `director` (`tdirector`) COMMENT 'Фильмы могут искать по режиссеру\nрежиссер не меняется',
  KEY `fk_tfilms_films_idx1` (`films_id_film`),
  CONSTRAINT `fk_tfilms_films` FOREIGN KEY (`films_id_film`) REFERENCES `films` (`id_film`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tfilms_lang1` FOREIGN KEY (`lang_id_lang`) REFERENCES `lang` (`id_lang`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Локализация таблицы films';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfilms`
--

LOCK TABLES `tfilms` WRITE;
/*!40000 ALTER TABLE `tfilms` DISABLE KEYS */;
INSERT INTO `tfilms` VALUES (1,1,'ru','Звёздные войны. Эпизод I: Скрытая угроза','Полнометражный','Джордж Лукас',NULL),(2,2,'ru','Звёздные войны. Эпизод II: Атака клонов','Полнометражный','Джордж Лукас',NULL),(3,4,'ru','Звёздные войны. Эпизод IV: Новая надежда','Полнометражный','Джордж Лукас',NULL),(4,5,'ru','Звёздные войны. Эпизод V: Империя наносит ответный удар','Полнометражный','Джордж Лукас',NULL),(5,6,'ru','Звёздные войны. Эпизод VI: Возвращение джедая','Полнометражный','Джордж Лукас',NULL),(6,7,'ru','Властели́н коле́ц: Бра́тство Кольца́','Полнометражный','Питер Джексон',NULL),(7,8,'ru','Властели́н коле́ц: Две кре́пости','Полнометражный','Питер Джексон',NULL),(8,9,'ru','Властели́н коле́ц: Возвраще́ние короля́','Полнометражный','Питер Джексон',NULL),(9,10,'ru','Секре́тные материа́лы','Сериал','Роб Боумен',NULL),(10,11,'ru','Десятое королевство','Мини-сериал','Саймон Мур',NULL),(11,3,'ru','Звёздные войны. Эпизод III: Месть си́тхов','Полнометражный','Джордж Лукас',NULL),(12,1,'en','Star Wars: Episode I – The Phantom Menace','Full-length','George Lucas',NULL),(13,2,'en','Star Wars: Episode II – Attack of the Clones','Full-length','George Lucas',NULL),(14,3,'en','Star Wars: Episode III – Revenge of the Sith','Full-length','George Lucas',NULL),(15,4,'en','Star Wars: Episode IV – A New Hope','Full-length','George Lucas',NULL),(16,5,'en','Star Wars: Episode V – The Empire Strikes Back ','Full-length','George Lucas',NULL),(17,6,'en','Star Wars: Episode VI – Return of the Jedi','Full-length','George Lucas',NULL);
/*!40000 ALTER TABLE `tfilms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 21:53:27
