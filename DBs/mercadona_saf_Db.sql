-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: 10.171.78.80    Database: mercadona_saf
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `messaging_module_error`
--

DROP TABLE IF EXISTS `messaging_module_error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaging_module_error` (
  `ID` int(11) NOT NULL,
  `MESSAGE_ID` varchar(10) DEFAULT NULL,
  `MESSAGE_TYPE` int(11) DEFAULT NULL,
  `MESSAGE_DATA` blob,
  `ERROR_TYPE` varchar(50) DEFAULT NULL,
  `ERROR_DATA` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaging_module_error`
--

LOCK TABLES `messaging_module_error` WRITE;
/*!40000 ALTER TABLE `messaging_module_error` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaging_module_error` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messaging_module_statistics`
--

DROP TABLE IF EXISTS `messaging_module_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaging_module_statistics` (
  `ID` int(11) NOT NULL,
  `POS_ID` varchar(45) DEFAULT NULL,
  `SESSION_ID` varchar(10) DEFAULT NULL,
  `STARTING_TIME` datetime DEFAULT NULL,
  `MESSAGE_TOTAL` int(11) DEFAULT NULL,
  `MESSAGES_SENT` int(11) DEFAULT NULL,
  `MESSAGES_PENDING` int(11) DEFAULT NULL,
  `MESSAGES_ERROR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaging_module_statistics`
--

LOCK TABLES `messaging_module_statistics` WRITE;
/*!40000 ALTER TABLE `messaging_module_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaging_module_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messaging_module_store`
--

DROP TABLE IF EXISTS `messaging_module_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaging_module_store` (
  `ID` int(11) NOT NULL,
  `MESSAGE_ID` varchar(10) DEFAULT NULL,
  `MESSAGE_TYPE` varchar(45) DEFAULT NULL,
  `TIME_STAMP` datetime DEFAULT NULL,
  `MESSAGE_DATA` blob,
  `PRIORITY` int(11) DEFAULT NULL,
  `RETRY_COUNT` int(11) DEFAULT NULL,
  `TTL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaging_module_store`
--

LOCK TABLES `messaging_module_store` WRITE;
/*!40000 ALTER TABLE `messaging_module_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaging_module_store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-19  8:48:06
