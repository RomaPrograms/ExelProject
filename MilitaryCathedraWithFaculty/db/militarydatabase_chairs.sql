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
-- Table structure for table `chairs`
--

DROP TABLE IF EXISTS `chairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chairs` (
  `idchair` int(11) NOT NULL AUTO_INCREMENT,
  `chairNumber` varchar(45) DEFAULT '0',
  `chairYear` int(11) DEFAULT NULL,
  `chairName` varchar(45) DEFAULT NULL,
  `chairNpp` varchar(45) DEFAULT '0',
  `chairschool` varchar(45) DEFAULT '0',
  `chairMethodic` varchar(45) DEFAULT '0',
  `chairscience` varchar(45) DEFAULT '0',
  `mathtechBase` varchar(45) DEFAULT '0',
  `rateOfCathedrs` varchar(45) DEFAULT '0',
  `addedFilesID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idchair`),
  UNIQUE KEY `idchair_UNIQUE` (`idchair`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chairs`
--

LOCK TABLES `chairs` WRITE;
/*!40000 ALTER TABLE `chairs` DISABLE KEYS */;
INSERT INTO `chairs` VALUES (1,'52',2018,'Тактики и вооружения ЗРВ','68.88','93.16','79.07','42.85','0.0','0.0',2),(2,'53',1899,'ВАРБ-ВПВО','78.94','92.84','85.76','52.13','0.0','0.0',3);
/*!40000 ALTER TABLE `chairs` ENABLE KEYS */;
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
