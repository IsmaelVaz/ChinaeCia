-- MySQL dump 10.16  Distrib 10.1.14-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dbchinaecia
-- ------------------------------------------------------
-- Server version	10.1.14-MariaDB-1~xenial

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
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `cod_cargo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cod_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'ADMINISTRADOR'),(2,'FUNCIONARIO');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (3,'LEGUMES');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `rg` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_produto`
--

DROP TABLE IF EXISTS `cliente_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente_produto` (
  `cod_cliente` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  `data` datetime DEFAULT NULL,
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `cliente_produto_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`),
  CONSTRAINT `cliente_produto_ibfk_2` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`cod_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_produto`
--

LOCK TABLES `cliente_produto` WRITE;
/*!40000 ALTER TABLE `cliente_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despesa`
--

DROP TABLE IF EXISTS `despesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `despesa` (
  `cod_despesa` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double(7,2) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `cod_empresa` int(11) DEFAULT NULL,
  `cod_usuario` int(11) NOT NULL,
  `fechado` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_despesa`),
  KEY `cod_empresa` (`cod_empresa`),
  CONSTRAINT `despesa_ibfk_1` FOREIGN KEY (`cod_empresa`) REFERENCES `empresa` (`cod_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despesa`
--

LOCK TABLES `despesa` WRITE;
/*!40000 ALTER TABLE `despesa` DISABLE KEYS */;
INSERT INTO `despesa` VALUES (3,800.00,'2016-06-26 00:00:00',1,0,3),(4,5451.00,'2016-06-26 00:00:00',2,0,3),(5,1212.00,'2016-06-26 00:00:00',2,0,3),(6,878.00,'2016-06-26 00:00:00',2,0,3),(7,545.00,'2016-06-26 00:00:00',3,1,3),(8,500.00,'2016-06-28 17:39:42',2,1,3),(9,1500.00,'2016-06-28 18:45:32',2,1,3),(10,350.00,'2016-06-28 18:45:45',3,1,3),(11,350.00,'2016-06-28 18:46:03',1,1,3),(12,350.00,'2016-06-28 18:46:17',2,1,3),(13,100.00,'2016-06-29 17:06:10',1,1,3),(14,100.00,'2016-06-29 17:33:20',2,1,4),(15,15.00,'2016-06-29 17:48:21',2,1,7),(16,20.00,'2016-06-29 17:56:59',2,1,8),(17,100.00,'2016-06-29 18:04:26',2,1,10);
/*!40000 ALTER TABLE `despesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `despesa_tipo`
--

DROP TABLE IF EXISTS `despesa_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `despesa_tipo` (
  `cod_despesa` int(11) DEFAULT NULL,
  `vlr_metade` float(7,2) DEFAULT NULL,
  `vlr_saida` float(7,2) DEFAULT NULL,
  KEY `cod_despesa` (`cod_despesa`),
  CONSTRAINT `despesa_tipo_ibfk_1` FOREIGN KEY (`cod_despesa`) REFERENCES `despesa` (`cod_despesa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `despesa_tipo`
--

LOCK TABLES `despesa_tipo` WRITE;
/*!40000 ALTER TABLE `despesa_tipo` DISABLE KEYS */;
INSERT INTO `despesa_tipo` VALUES (9,NULL,1500.00),(10,NULL,350.00),(11,300.00,50.00),(12,300.00,50.00);
/*!40000 ALTER TABLE `despesa_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `cod_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(30) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'12376557','SADIA'),(2,'67865567','PERDIGAO'),(3,'1331213131','JOSE');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `cod_produto` int(11) NOT NULL,
  `qtd_min` int(11) NOT NULL,
  `qtd_atual` int(11) NOT NULL,
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `estoque_ibfk_1` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`cod_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fechamento`
--

DROP TABLE IF EXISTS `fechamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fechamento` (
  `cod_fechamento` int(11) NOT NULL AUTO_INCREMENT,
  `total_saida` float DEFAULT NULL,
  `total_despesa` float DEFAULT NULL,
  `fundo` float DEFAULT NULL,
  `verif` int(1) NOT NULL,
  `data` datetime DEFAULT NULL,
  `usr` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_fechamento`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fechamento`
--

LOCK TABLES `fechamento` WRITE;
/*!40000 ALTER TABLE `fechamento` DISABLE KEYS */;
INSERT INTO `fechamento` VALUES (3,170,300,100,1,'2016-06-29 15:40:01',1),(4,170,300,100,1,'2016-06-29 17:29:15',1),(5,170,300,100,1,'2016-06-29 17:39:59',1),(6,170,300,100,1,'2016-06-29 17:40:43',1),(7,0,15,100,1,'2016-06-29 17:48:26',1),(8,150,20,100,1,'2016-06-29 17:57:05',1),(9,1500,0,100,1,'2016-06-29 18:00:37',1),(10,150,100,100,1,'2016-06-29 18:05:17',1),(11,NULL,NULL,15,1,'2016-07-05 17:50:01',NULL);
/*!40000 ALTER TABLE `fechamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_acessado`
--

DROP TABLE IF EXISTS `login_acessado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_acessado` (
  `cod_usuario` int(11) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  KEY `cod_usuario` (`cod_usuario`),
  CONSTRAINT `login_acessado_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_acessado`
--

LOCK TABLES `login_acessado` WRITE;
/*!40000 ALTER TABLE `login_acessado` DISABLE KEYS */;
INSERT INTO `login_acessado` VALUES (1,'2016-06-26 17:01:00'),(1,'2016-06-26 17:05:21'),(1,'2016-06-26 17:06:37'),(1,'2016-06-26 17:09:52'),(1,'2016-06-26 17:33:02'),(1,'2016-06-26 17:35:20'),(1,'2016-06-26 17:36:53'),(1,'2016-06-26 17:37:46'),(1,'2016-06-26 17:40:29'),(1,'2016-06-26 17:40:50'),(1,'2016-06-26 17:50:47'),(1,'2016-06-26 17:51:39'),(1,'2016-06-26 18:02:24'),(1,'2016-06-26 18:02:25'),(1,'2016-06-26 18:04:39'),(1,'2016-06-26 18:05:08'),(1,'2016-06-26 18:27:35'),(1,'2016-06-26 18:28:17'),(1,'2016-06-26 18:28:42'),(1,'2016-06-26 18:31:07'),(1,'2016-06-26 18:31:08'),(1,'2016-06-28 16:16:39'),(1,'2016-06-28 16:16:40'),(1,'2016-06-28 17:34:35'),(1,'2016-06-28 17:34:35'),(1,'2016-06-28 17:39:26'),(1,'2016-06-28 17:39:26'),(1,'2016-06-28 17:40:44'),(1,'2016-06-28 17:40:45'),(1,'2016-06-28 17:46:28'),(1,'2016-06-28 17:46:28'),(1,'2016-06-28 17:48:40'),(1,'2016-06-28 17:48:40'),(1,'2016-06-28 17:50:29'),(1,'2016-06-28 17:50:30'),(1,'2016-06-28 17:52:48'),(1,'2016-06-28 17:52:49'),(1,'2016-06-28 18:01:28'),(1,'2016-06-28 18:01:28'),(1,'2016-06-28 18:45:18'),(1,'2016-06-28 18:47:17'),(1,'2016-06-28 18:47:17'),(1,'2016-06-29 13:58:35'),(1,'2016-06-29 13:59:44'),(1,'2016-06-29 13:59:44'),(1,'2016-06-29 14:03:40'),(1,'2016-06-29 14:03:40'),(1,'2016-06-29 14:05:14'),(1,'2016-06-29 14:09:32'),(1,'2016-06-29 14:09:32'),(1,'2016-06-29 14:12:54'),(1,'2016-06-29 14:12:54'),(1,'2016-06-29 14:14:02'),(1,'2016-06-29 15:16:51'),(1,'2016-06-29 15:18:36'),(1,'2016-06-29 15:18:36'),(1,'2016-06-29 15:19:26'),(1,'2016-06-29 15:19:26'),(1,'2016-06-29 15:19:28'),(1,'2016-06-29 15:21:23'),(1,'2016-06-29 15:22:10'),(1,'2016-06-29 15:23:14'),(1,'2016-06-29 15:24:37'),(1,'2016-06-29 15:25:53'),(1,'2016-06-29 15:27:43'),(1,'2016-06-29 15:28:45'),(1,'2016-06-29 15:37:38'),(1,'2016-06-29 15:37:55'),(1,'2016-06-29 15:39:08'),(1,'2016-06-29 15:39:56'),(1,'2016-06-29 15:40:19'),(1,'2016-06-29 15:46:54'),(1,'2016-06-29 15:49:01'),(1,'2016-06-29 16:14:07'),(1,'2016-06-29 16:25:34'),(1,'2016-06-29 16:28:56'),(1,'2016-06-29 16:44:07'),(1,'2016-06-29 16:44:44'),(1,'2016-06-29 16:46:54'),(1,'2016-06-29 16:48:00'),(1,'2016-06-29 16:49:54'),(1,'2016-06-29 16:52:35'),(1,'2016-06-29 16:58:50'),(1,'2016-06-29 17:00:23'),(1,'2016-06-29 17:00:58'),(1,'2016-06-29 17:02:44'),(1,'2016-06-29 17:05:06'),(1,'2016-06-29 17:12:08'),(1,'2016-06-29 17:22:32'),(1,'2016-06-29 17:23:53'),(1,'2016-06-29 17:29:10'),(1,'2016-06-29 17:31:52'),(1,'2016-06-29 17:39:33'),(1,'2016-06-29 17:47:46'),(1,'2016-06-29 18:04:09'),(1,'2016-07-01 15:47:59'),(1,'2016-07-05 17:49:54'),(1,'2016-07-05 17:49:55'),(1,'2016-07-05 17:51:37'),(1,'2016-07-05 17:52:45'),(1,'2016-07-05 17:52:46'),(1,'2016-07-05 18:08:23'),(1,'2016-07-05 18:08:23'),(1,'2016-07-05 18:14:33'),(1,'2016-07-05 18:14:34'),(1,'2016-07-05 18:15:17'),(1,'2016-07-05 18:15:17'),(1,'2016-07-05 18:17:42'),(1,'2016-07-05 18:17:42'),(1,'2016-07-05 18:24:44'),(1,'2016-07-05 18:24:45');
/*!40000 ALTER TABLE `login_acessado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `cod_mesa` int(11) NOT NULL AUTO_INCREMENT,
  `situacao` varchar(15) NOT NULL,
  PRIMARY KEY (`cod_mesa`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
INSERT INTO `mesa` VALUES (1,'DISPONIVEL'),(2,'DISPONIVEL'),(3,'DISPONIVEL'),(4,'DISPONIVEL'),(5,'DISPONIVEL'),(6,'DISPONIVEL'),(7,'DISPONIVEL'),(8,'DISPONIVEL'),(9,'DISPONIVEL'),(10,'DISPONIVEL'),(11,'DISPONIVEL'),(12,'DISPONIVEL'),(13,'DISPONIVEL'),(14,'DISPONIVEL'),(15,'DISPONIVEL'),(16,'DISPONIVEL'),(17,'DISPONIVEL'),(18,'DISPONIVEL'),(19,'DISPONIVEL'),(20,'DISPONIVEL'),(21,'DISPONIVEL'),(22,'DISPONIVEL'),(23,'DISPONIVEL'),(24,'DISPONIVEL'),(25,'DISPONIVEL'),(26,'DISPONIVEL'),(27,'DISPONIVEL'),(28,'DISPONIVEL'),(29,'DISPONIVEL'),(30,'DISPONIVEL'),(31,'DISPONIVEL'),(32,'DISPONIVEL'),(33,'DISPONIVEL'),(34,'DISPONIVEL'),(35,'DISPONIVEL'),(36,'DISPONIVEL'),(37,'DISPONIVEL'),(38,'DISPONIVEL'),(39,'DISPONIVEL'),(40,'DISPONIVEL'),(41,'DISPONIVEL'),(42,'DISPONIVEL'),(43,'DISPONIVEL'),(44,'DISPONIVEL'),(45,'DISPONIVEL'),(46,'DISPONIVEL'),(47,'DISPONIVEL'),(48,'DISPONIVEL'),(49,'DISPONIVEL'),(50,'DISPONIVEL'),(51,'DISPONIVEL'),(52,'DISPONIVEL'),(53,'DISPONIVEL'),(54,'DISPONIVEL'),(55,'DISPONIVEL'),(56,'DISPONIVEL'),(57,'DISPONIVEL'),(58,'DISPONIVEL'),(59,'DISPONIVEL'),(60,'DISPONIVEL'),(61,'DISPONIVEL');
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa_produto`
--

DROP TABLE IF EXISTS `mesa_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa_produto` (
  `cod_mesa` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  KEY `cod_mesa` (`cod_mesa`),
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `mesa_produto_ibfk_1` FOREIGN KEY (`cod_mesa`) REFERENCES `mesa` (`cod_mesa`),
  CONSTRAINT `mesa_produto_ibfk_2` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`cod_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa_produto`
--

LOCK TABLES `mesa_produto` WRITE;
/*!40000 ALTER TABLE `mesa_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesa_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagamento` (
  `cod_tipopagamento` int(11) NOT NULL,
  `valor` double(7,2) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `cod_pagamento` int(11) NOT NULL AUTO_INCREMENT,
  `fechado` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_pagamento`),
  KEY `cod_tipopagamento` (`cod_tipopagamento`),
  CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`cod_tipopagamento`) REFERENCES `tipo_pagamento` (`cod_tipopagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (2,1500.00,'2016-06-29 18:00:10',3,9),(2,150.00,'2016-06-29 18:05:02',4,10);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `cod_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `valor` double(7,2) NOT NULL,
  `cod_categoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_produto`),
  KEY `produto_ibfk_1` (`cod_categoria`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categoria` (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (3,'NOVO',10.00,3);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_peso`
--

DROP TABLE IF EXISTS `produto_peso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_peso` (
  `cod_produto` int(11) NOT NULL,
  `valor_peso` float(7,2) DEFAULT NULL,
  KEY `cod_produto` (`cod_produto`),
  CONSTRAINT `produto_peso_ibfk_1` FOREIGN KEY (`cod_produto`) REFERENCES `produto` (`cod_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_peso`
--

LOCK TABLES `produto_peso` WRITE;
/*!40000 ALTER TABLE `produto_peso` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto_peso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_vendido`
--

DROP TABLE IF EXISTS `produto_vendido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_vendido` (
  `cod_produto` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `fechado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_vendido`
--

LOCK TABLES `produto_vendido` WRITE;
/*!40000 ALTER TABLE `produto_vendido` DISABLE KEYS */;
INSERT INTO `produto_vendido` VALUES (1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-26 00:00:00',3),(1,'2016-06-01 00:00:00',3),(1,'2016-06-01 00:00:00',3),(1,'2016-06-01 00:00:00',3),(1,'2016-06-01 00:00:00',3),(1,'2016-06-01 00:00:00',3),(1,'2016-07-01 00:00:00',3),(1,'2016-07-01 00:00:00',3),(1,'2016-07-01 00:00:00',3),(1,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-07-01 00:00:00',3),(2,'2016-08-01 00:00:00',3),(2,'2016-08-01 00:00:00',3),(2,'2016-08-01 00:00:00',3),(2,'2016-08-01 00:00:00',3),(2,'2016-09-01 00:00:00',3),(2,'2016-09-01 00:00:00',3),(2,'2016-09-01 00:00:00',3),(2,'2016-09-01 00:00:00',3),(1,'2016-06-28 00:00:00',3),(1,'2016-06-28 00:00:00',3),(1,'2016-06-28 00:00:00',3),(1,'2016-06-28 00:00:00',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-28 18:01:01',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:05:05',3),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:32:32',4),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:48:48',7),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(1,'2016-06-29 17:50:50',8),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 17:59:59',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(2,'2016-06-29 18:00:00',9),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10),(1,'2016-06-29 18:04:04',10);
/*!40000 ALTER TABLE `produto_vendido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pagamento`
--

DROP TABLE IF EXISTS `tipo_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pagamento` (
  `cod_tipopagamento` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_tipopagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pagamento`
--

LOCK TABLES `tipo_pagamento` WRITE;
/*!40000 ALTER TABLE `tipo_pagamento` DISABLE KEYS */;
INSERT INTO `tipo_pagamento` VALUES (1,'CARTAO',0),(2,'DINHEIRO',1),(3,'DÉBITO',0),(4,'CRÉDITO',0),(5,'VOUCHER',0),(6,'VISA-VALE',0);
/*!40000 ALTER TABLE `tipo_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `ultimo_login` datetime DEFAULT NULL,
  `subcargo` varchar(30) DEFAULT NULL,
  `cod_cargo` int(11) DEFAULT NULL,
  `nome_user` varchar(50) DEFAULT NULL,
  `senha` varchar(200) DEFAULT NULL,
  `dt_nasc` date DEFAULT NULL,
  PRIMARY KEY (`cod_usuario`),
  UNIQUE KEY `nome_user` (`nome_user`),
  KEY `cod_cargo` (`cod_cargo`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`cod_cargo`) REFERENCES `cargo` (`cod_cargo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ismael','34655376880','2016-07-05 18:24:45',NULL,1,'isma','7110eda4d09e062aa5e4a390b0a572ac0d2c0220',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-05 18:28:46
