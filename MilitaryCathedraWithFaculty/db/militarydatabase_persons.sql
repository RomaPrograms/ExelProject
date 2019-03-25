-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: militarydatabase
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `persons` (
  `idpersons` int(11) NOT NULL AUTO_INCREMENT,
  `pchair` varchar(45) NOT NULL DEFAULT '0',
  `pyear` int(5) DEFAULT NULL,
  `prank` varchar(30) DEFAULT '0',
  `pname` varchar(45) DEFAULT '0',
  `pcategory` varchar(30) DEFAULT '0',
  `prate` double DEFAULT '0',
  `addedFiles` int(11) NOT NULL,
  PRIMARY KEY (`idpersons`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'ВАРБ-Тактики ивооружения РТВ',2018,'полковник','Лысый А.Н.','нач. кафедры',79.02,1),(2,'ВАРБ-Тактики ивооружения РТВ',2018,NULL,'Сергейчик Д.М.','ст. преподаватель',70.71,1),(3,'ВАРБ-Тактики ивооружения РТВ',2018,NULL,'Мазго А.В.','ст. преподаватель',57.63,1),(4,'ВАРБ-Тактики ивооружения РТВ',2018,NULL,'Кардаков В.И.','профессор',49.51,1),(5,'ВАРБ-Тактики ивооружения РТВ',2018,NULL,'Осипов В.Т.','профессор',38.58,1),(6,'ВАРБ-Тактики ивооружения РТВ',2018,NULL,'Юрас С.А.','доцент',65.45,1),(7,'Тактики и вооружения ЗРВ',2018,'п-к','Тимченко А.А.','нач. кафедры',35.66,2),(8,'Тактики и вооружения ЗРВ',2018,'п/п-к','Нефёдов Д.С.','зам. нач. кафедры',56.6,2),(9,'Тактики и вооружения ЗРВ',2018,'п/п-к','Трушков Ю.Л.','нач. цикла',45.38,2),(10,'Тактики и вооружения ЗРВ',2018,NULL,'Мокринский В.В.','нач. цикла',64.18,2),(11,'Тактики и вооружения ЗРВ',2018,'п/п-к','Комар Н.Н.','нач. цикла',42.05,2),(12,'Тактики и вооружения ЗРВ',2018,NULL,'Казарин А.В.','профессор',40.57,2),(13,'Тактики и вооружения ЗРВ',2018,NULL,'Денисенко И.Г.','профессор',44.23,2),(14,'Тактики и вооружения ЗРВ',2018,NULL,'Захаров И.Я.','профессор',67.35,2),(15,'Тактики и вооружения ЗРВ',2018,NULL,'Козловский А.Е.','доцент',74.73,2),(16,'Тактики и вооружения ЗРВ',2018,'п/п-к','Лапицкий И.Л.','ст. преподаватель',53.53,2),(17,'Тактики и вооружения ЗРВ',2018,'м-р','Свириденко','ст. преподаватель',52.45,2),(18,'Тактики и вооружения ЗРВ',2018,'п/п-к','Иванов Н.А.','ст. преподаватель',49.03,2),(19,'Тактики и вооружения ЗРВ',2018,NULL,'Чупанов Р.А.','ст. преподаватель',77.11,2),(20,'Тактики и вооружения ЗРВ',2018,NULL,'Резников И.И.','ст. преподаватель',49.55,2),(21,'Тактики и вооружения ЗРВ',2018,'п/п-к','Толкачев Р.В.','преподаватель',61.3,2),(22,'ВАРБ-ВПВО',1899,'п-к','Драгун В.Р.','нач. кафедры',58.35,3),(23,'ВАРБ-ВПВО',1899,'п/п-к','Стройкин А.П.','нач. цикла',64.84,3),(24,'ВАРБ-ВПВО',1899,NULL,'Федоров А.И.','нач. цикла',79.9,3),(25,'ВАРБ-ВПВО',1899,'п/п-к','Сидорович О.В.','нач. цикла',62.4,3),(26,'ВАРБ-ВПВО',1899,NULL,'Мисько В.А.','профессор',62.1,3),(27,'ВАРБ-ВПВО',1899,NULL,'Лагутин В.М.','доцент',57.25,3),(28,'ВАРБ-ВПВО',1899,NULL,'Микитенко В.М.','ст. преподаватель',67.84,3),(29,'ВАРБ-ВПВО',1899,NULL,'Цыбулько В.В.','ст. преподаватель',90.98,3),(30,'ВАРБ-ВПВО',1899,NULL,'Бутенко В.Г.\n','ст. преподаватель',83.54,3),(31,'ВАРБ-ВПВО',1899,NULL,'Овчаров А.В.\n','ст. преподаватель',73.63,3),(32,'ВАРБ-ВПВО',1899,NULL,'Кочетков П.Ф.\n','ст. преподаватель',94.81,3),(33,'ВАРБ-ВПВО',1899,NULL,'Онищук Р.С.\n','преподаватель',69.01,3),(34,'ВАРБ-ВПВО',1899,NULL,'Аникеев С.В.\n','преподаватель',62.82,3),(35,'ВАРБ-ВПВО',1899,NULL,'Колодяжный В.В.\n','профессор',51.21,3);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-16 15:55:24
