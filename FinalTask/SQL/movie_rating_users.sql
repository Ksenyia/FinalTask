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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_users` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Первичный ключ',
  `login` varchar(45) NOT NULL COMMENT 'login',
  `email` varchar(45) NOT NULL COMMENT 'e-mail',
  `password` varchar(170) NOT NULL COMMENT 'Пароль\n',
  `admin_flag` tinyint(4) DEFAULT '0' COMMENT 'Администратор или обычный пользаватель\n',
  `status` int(11) DEFAULT '0' COMMENT 'Статус пользавателя\nНачальный статус - 0\nЕсли оценка близка к средней +1\nЕсли далека  -1\nМожет быть отрицательной',
  `access` tinyint(4) DEFAULT '1' COMMENT 'Забанен пользаватель или нет',
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_users`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `e-mail_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Пользователи (обычные и админестраторы)\nНет соответствующей таблицы с локализацией, т.к. нет полей, нуждающихся в ней';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'pasha','p@gmail.com','123456',1,0,1,NULL,NULL),(2,'masha','masha@mail.ru','5678',0,0,0,NULL,NULL),(3,'supercat','cat@tut.by','8907',0,1,1,NULL,NULL),(4,'vasya','vasya@tut.by','qwerty',0,4,1,NULL,NULL),(5,'login','email@gm.com','d6c6721a269bf1723425d0dbfde3849de5cfe1cd38e9a94e2434ec16b82',1,1,1,NULL,NULL),(6,'Ksenia','Ks@gmail.com','1343a654a018c5cffd7a87559554d65ef277b8e39b4282dfd8a536bb56fde4ae',1,0,1,NULL,NULL),(7,'xcvbn','cfvgbhnjm@gh.com','ba4e523c6851c5962bde397f1adc261f90ab33e34a777bea9a5b708dc25f',0,0,1,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-19 21:53:24
