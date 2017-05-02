-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: cyberedge
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `candidates_record`
--

DROP TABLE IF EXISTS `candidates_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidates_record` (
  `candidate_id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Position` varchar(45) NOT NULL,
  `Client` varchar(45) DEFAULT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `Contact` varchar(15) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Experience` int(11) DEFAULT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates_record`
--

LOCK TABLES `candidates_record` WRITE;
/*!40000 ALTER TABLE `candidates_record` DISABLE KEYS */;
INSERT INTO `candidates_record` VALUES (1,'Michael Hudson','IT security Consultant','Deloitte','Sacramento,CA','4085561313','mike.hudson@gmail.com',15,'Placed'),(2,'Vijay','Technical Recruiter','Cyberedge','Ujjain,MP','8827315020','vijay.daswani@gmail.com',1,'..'),(3,'Thomas Clark','Sr. Solution Architect (Param)','Huawei','Atlanta-GA','4155964182','peakstarservices@yahoo.com',30,'Submitted'),(4,'Paul Sung','Sr. Solution Architect','Huawei','Atlanta,GA','4084550264','paul.m.sung@gmail.com',8,'Pending'),(5,'ED PEERAN','Sr. Solution Architect','Huawei','Atlanta,GA','9098166708','ed_peeran@hotmail.com',35,'Pending'),(6,'Fernando Saldana','Sr. Solution Architect','Huawei','Atlanta,GA','4086606090','fersaldana@gmail.com',18,'Pending'),(7,'Erica L sweet','Sr. Test Engineer','Delta Airlines','Atlanta,GA','6782348602','misssweet2u@yahoo.com',10,'Pending'),(8,'david lee','Sr. Software Engineer','Delta Dental','San Francisco','(408) 3140005','dklee59@hotmail.com',20,'Pending'),(9,'Venkat Seelam','Devops Engineer','TCS','Menlo Park,CA','214-843-0609','venkat.dev56@gmail.com',7,'Submitted'),(10,'Sirish Nukala','Sr. Software Engineer','Delta Dental','San Fransisco','9259220110','sirish.k.nukala@gmail.com',10,'Already Applied');
/*!40000 ALTER TABLE `candidates_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-16 12:43:09
