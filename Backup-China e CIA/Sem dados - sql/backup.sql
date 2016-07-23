CREATE DATABASE  IF NOT EXISTS `dbchinaecia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbchinaecia`;
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `cod_mesa` int(11) NOT NULL AUTO_INCREMENT,
  `situacao` varchar(15) NOT NULL,
  PRIMARY KEY (`cod_mesa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `cod_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `valor` double(7,2) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  PRIMARY KEY (`cod_produto`),
  KEY `cod_categoria` (`cod_categoria`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categoria` (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-29 18:17:14
