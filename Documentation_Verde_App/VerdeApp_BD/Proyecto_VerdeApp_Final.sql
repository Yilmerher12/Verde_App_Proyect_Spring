CREATE DATABASE  IF NOT EXISTS `verdeapp_2026` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `verdeapp_2026`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: verdeapp_2026
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `afiliaciones_recicladores`
--

DROP TABLE IF EXISTS `afiliaciones_recicladores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `afiliaciones_recicladores` (
  `afiliacion_id` int NOT NULL AUTO_INCREMENT,
  `reciclador_id` int DEFAULT NULL,
  `conjunto_id` int DEFAULT NULL,
  `estado_verificacion` tinyint(1) DEFAULT '0',
  `fecha_afiliacion` date DEFAULT NULL,
  PRIMARY KEY (`afiliacion_id`),
  KEY `reciclador_id` (`reciclador_id`),
  KEY `conjunto_id` (`conjunto_id`),
  CONSTRAINT `afiliaciones_recicladores_ibfk_1` FOREIGN KEY (`reciclador_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `afiliaciones_recicladores_ibfk_2` FOREIGN KEY (`conjunto_id`) REFERENCES `conjuntos` (`conjunto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afiliaciones_recicladores`
--

LOCK TABLES `afiliaciones_recicladores` WRITE;
/*!40000 ALTER TABLE `afiliaciones_recicladores` DISABLE KEYS */;
INSERT INTO `afiliaciones_recicladores` VALUES (1,3,1,1,'2026-01-10'),(2,4,1,1,'2026-01-11'),(3,9,2,1,'2026-01-12'),(4,3,3,1,'2026-01-13'),(5,4,4,1,'2026-01-14'),(6,9,5,0,'2026-01-15'),(7,3,6,1,'2026-01-16'),(8,4,7,1,'2026-01-17'),(9,9,8,1,'2026-01-18'),(10,3,9,1,'2026-01-19');
/*!40000 ALTER TABLE `afiliaciones_recicladores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conjuntos`
--

DROP TABLE IF EXISTS `conjuntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conjuntos` (
  `conjunto_id` int NOT NULL AUTO_INCREMENT,
  `nombre_conjunto` varchar(100) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nit` varchar(20) DEFAULT NULL,
  `qr_maestro_id` varchar(50) NOT NULL,
  `suscripcion_activa` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`conjunto_id`),
  UNIQUE KEY `qr_maestro_id` (`qr_maestro_id`),
  UNIQUE KEY `nit` (`nit`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conjuntos`
--

LOCK TABLES `conjuntos` WRITE;
/*!40000 ALTER TABLE `conjuntos` DISABLE KEYS */;
INSERT INTO `conjuntos` VALUES (1,'Torres del Bosque','Calle 1','NIT01','QR-M001',1),(2,'Portal Verde','Calle 2','NIT02','QR-M002',1),(3,'Reserva Central','Calle 3','NIT03','QR-M003',1),(4,'Altos del Sol','Calle 4','NIT04','QR-M004',1),(5,'Pinares','Calle 5','NIT05','QR-M005',1),(6,'La Arboleda','Calle 6','NIT06','QR-M006',1),(7,'Senderos','Calle 7','NIT07','QR-M007',1),(8,'Mirador','Calle 8','NIT08','QR-M008',1),(9,'Parque Real','Calle 9','NIT09','QR-M009',1),(10,'Horizonte','Calle 10','NIT10','QR-M010',1);
/*!40000 ALTER TABLE `conjuntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios_especiales`
--

DROP TABLE IF EXISTS `servicios_especiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios_especiales` (
  `servicio_id` int NOT NULL AUTO_INCREMENT,
  `unidad_id` int DEFAULT NULL,
  `reciclador_id` int DEFAULT NULL,
  `tipo_aparato` varchar(50) DEFAULT NULL,
  `tarifa_total` decimal(10,2) DEFAULT NULL,
  `comision_app` decimal(10,2) DEFAULT NULL,
  `pago_reciclador` decimal(10,2) DEFAULT NULL,
  `estado_pago` enum('PENDIENTE','PAGADO') DEFAULT 'PENDIENTE',
  PRIMARY KEY (`servicio_id`),
  KEY `unidad_id` (`unidad_id`),
  KEY `reciclador_id` (`reciclador_id`),
  CONSTRAINT `servicios_especiales_ibfk_1` FOREIGN KEY (`unidad_id`) REFERENCES `unidades` (`unidad_id`),
  CONSTRAINT `servicios_especiales_ibfk_2` FOREIGN KEY (`reciclador_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios_especiales`
--

LOCK TABLES `servicios_especiales` WRITE;
/*!40000 ALTER TABLE `servicios_especiales` DISABLE KEYS */;
INSERT INTO `servicios_especiales` VALUES (1,1,3,'Nevera',50000.00,10000.00,40000.00,'PAGADO'),(2,2,4,'Estufa',30000.00,6000.00,24000.00,'PENDIENTE'),(3,3,9,'Lavadora',60000.00,12000.00,48000.00,'PAGADO'),(4,4,3,'Microondas',15000.00,3000.00,12000.00,'PAGADO'),(5,5,4,'Secadora',40000.00,8000.00,32000.00,'PENDIENTE'),(6,6,9,'Televisor',25000.00,5000.00,20000.00,'PAGADO'),(7,7,3,'Calentador',35000.00,7000.00,28000.00,'PAGADO'),(8,8,4,'Equipo Sonido',20000.00,4000.00,16000.00,'PENDIENTE'),(9,9,9,'Aspiradora',10000.00,2000.00,8000.00,'PAGADO'),(10,10,3,'Ventilador',8000.00,1600.00,6400.00,'PAGADO');
/*!40000 ALTER TABLE `servicios_especiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacciones_reciclaje`
--

DROP TABLE IF EXISTS `transacciones_reciclaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacciones_reciclaje` (
  `transaccion_id` int NOT NULL AUTO_INCREMENT,
  `unidad_id` int DEFAULT NULL,
  `qr_bolsa_id` varchar(50) NOT NULL,
  `tipo_registro` enum('ESTANDAR','GRAN_FORMATO') NOT NULL,
  `tamaño_bolsa` enum('S','M','L','NA') DEFAULT 'NA',
  `descripcion_material` text,
  `puntos_provisionales` int DEFAULT '0',
  `estado_ruta` enum('INICIO','EN_TRANSITO','RECOLECTADO','RECHAZADO') DEFAULT 'INICIO',
  `fecha_creacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`transaccion_id`),
  UNIQUE KEY `qr_bolsa_id` (`qr_bolsa_id`),
  KEY `unidad_id` (`unidad_id`),
  CONSTRAINT `transacciones_reciclaje_ibfk_1` FOREIGN KEY (`unidad_id`) REFERENCES `unidades` (`unidad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacciones_reciclaje`
--

LOCK TABLES `transacciones_reciclaje` WRITE;
/*!40000 ALTER TABLE `transacciones_reciclaje` DISABLE KEYS */;
INSERT INTO `transacciones_reciclaje` VALUES (1,1,'BOL-001','ESTANDAR','M','2kg Plástico',25,'RECOLECTADO','2026-03-10 20:21:24'),(2,2,'BOL-002','ESTANDAR','S','1kg Cartón',10,'RECOLECTADO','2026-03-10 20:21:24'),(3,3,'BOL-003','GRAN_FORMATO','NA','Mueble viejo',0,'INICIO','2026-03-10 20:21:24'),(4,4,'BOL-004','ESTANDAR','L','5kg Vidrio',50,'EN_TRANSITO','2026-03-10 20:21:24'),(5,5,'BOL-005','ESTANDAR','M','Papel archivo',25,'RECOLECTADO','2026-03-10 20:21:24'),(6,6,'BOL-006','ESTANDAR','S','Latas',10,'RECHAZADO','2026-03-10 20:21:24'),(7,7,'BOL-007','ESTANDAR','L','Mezcla',50,'RECOLECTADO','2026-03-10 20:21:24'),(8,8,'BOL-008','GRAN_FORMATO','NA','Colchón',0,'INICIO','2026-03-10 20:21:24'),(9,9,'BOL-009','ESTANDAR','M','PET',25,'RECOLECTADO','2026-03-10 20:21:24'),(10,10,'BOL-010','ESTANDAR','S','Tetrapak',10,'EN_TRANSITO','2026-03-10 20:21:24');
/*!40000 ALTER TABLE `transacciones_reciclaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidades`
--

DROP TABLE IF EXISTS `unidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidades` (
  `unidad_id` int NOT NULL AUTO_INCREMENT,
  `conjunto_id` int DEFAULT NULL,
  `torre` varchar(10) NOT NULL,
  `apartamento` varchar(10) NOT NULL,
  `residente_principal_id` int DEFAULT NULL,
  PRIMARY KEY (`unidad_id`),
  KEY `conjunto_id` (`conjunto_id`),
  KEY `residente_principal_id` (`residente_principal_id`),
  CONSTRAINT `unidades_ibfk_1` FOREIGN KEY (`conjunto_id`) REFERENCES `conjuntos` (`conjunto_id`),
  CONSTRAINT `unidades_ibfk_2` FOREIGN KEY (`residente_principal_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidades`
--

LOCK TABLES `unidades` WRITE;
/*!40000 ALTER TABLE `unidades` DISABLE KEYS */;
INSERT INTO `unidades` VALUES (1,1,'A','101',1),(2,1,'A','102',2),(3,2,'1','201',7),(4,3,'B','404',8),(5,4,'1','101',1),(6,5,'C','502',2),(7,6,'2','101',7),(8,7,'A','202',8),(9,8,'3','303',1),(10,9,'D','601',2);
/*!40000 ALTER TABLE `unidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `nombre_completo` varchar(100) NOT NULL,
  `documento_identidad` varchar(20) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `rol` enum('RESIDENTE','RECICLADOR','ADMINISTRADOR','PUNTO_ACOPIO') NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password_hash` varchar(255) NOT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `documento_identidad` (`documento_identidad`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan Pérez','1001','3001','RESIDENTE','juan@mail.com','hash1','2026-03-10 20:21:24'),(2,'Ana Gómez','1002','3002','RESIDENTE','ana@mail.com','hash2','2026-03-10 20:21:24'),(3,'Carlos Ruiz','1003','3003','RECICLADOR','carlos@mail.com','hash3','2026-03-10 20:21:24'),(4,'Marta López','1004','3004','RECICLADOR','marta@mail.com','hash4','2026-03-10 20:21:24'),(5,'Luis Díaz','1005','3005','ADMINISTRADOR','luis@mail.com','hash5','2026-03-10 20:21:24'),(6,'Sofía Páez','1006','3006','PUNTO_ACOPIO','sofia@mail.com','hash6','2026-03-10 20:21:24'),(7,'Pedro Serna','1007','3007','RESIDENTE','pedro@mail.com','hash7','2026-03-10 20:21:24'),(8,'Lucía Villa','1008','3008','RESIDENTE','lucia@mail.com','hash8','2026-03-10 20:21:24'),(9,'Diego Mesa','1009','3009','RECICLADOR','diego@mail.com','hash9','2026-03-10 20:21:24'),(10,'Elena Cano','1010','3010','PUNTO_ACOPIO','elena@mail.com','hash10','2026-03-10 20:21:24');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validaciones`
--

DROP TABLE IF EXISTS `validaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validaciones` (
  `validacion_id` int NOT NULL AUTO_INCREMENT,
  `transaccion_id` int DEFAULT NULL,
  `reciclador_id` int DEFAULT NULL,
  `calificacion_semaforo` enum('VERDE','ROJO') NOT NULL,
  `novedad_comentario` text,
  `fecha_validacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`validacion_id`),
  KEY `transaccion_id` (`transaccion_id`),
  KEY `reciclador_id` (`reciclador_id`),
  CONSTRAINT `validaciones_ibfk_1` FOREIGN KEY (`transaccion_id`) REFERENCES `transacciones_reciclaje` (`transaccion_id`),
  CONSTRAINT `validaciones_ibfk_2` FOREIGN KEY (`reciclador_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validaciones`
--

LOCK TABLES `validaciones` WRITE;
/*!40000 ALTER TABLE `validaciones` DISABLE KEYS */;
INSERT INTO `validaciones` VALUES (1,1,3,'VERDE','Todo limpio','2026-03-10 20:21:24'),(2,2,3,'VERDE','Ok','2026-03-10 20:21:24'),(3,5,4,'ROJO','Material sucio','2026-03-10 20:21:24'),(4,7,9,'VERDE','Excelente','2026-03-10 20:21:24'),(5,9,4,'VERDE','Bien separado','2026-03-10 20:21:24'),(6,1,9,'VERDE','Sin novedad','2026-03-10 20:21:24'),(7,2,4,'VERDE','Completo','2026-03-10 20:21:24'),(8,5,3,'VERDE','Correcto','2026-03-10 20:21:24'),(9,7,4,'VERDE','Sin problemas','2026-03-10 20:21:24'),(10,9,3,'VERDE','Muy bien','2026-03-10 20:21:24');
/*!40000 ALTER TABLE `validaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas_puntos_acopio`
--

DROP TABLE IF EXISTS `ventas_puntos_acopio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas_puntos_acopio` (
  `venta_id` int NOT NULL AUTO_INCREMENT,
  `reciclador_id` int DEFAULT NULL,
  `punto_acopio_id` int DEFAULT NULL,
  `foto_recibo_url` varchar(255) DEFAULT NULL,
  `monto_recibido` decimal(10,2) DEFAULT NULL,
  `fecha_venta` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`venta_id`),
  KEY `reciclador_id` (`reciclador_id`),
  KEY `punto_acopio_id` (`punto_acopio_id`),
  CONSTRAINT `ventas_puntos_acopio_ibfk_1` FOREIGN KEY (`reciclador_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `ventas_puntos_acopio_ibfk_2` FOREIGN KEY (`punto_acopio_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas_puntos_acopio`
--

LOCK TABLES `ventas_puntos_acopio` WRITE;
/*!40000 ALTER TABLE `ventas_puntos_acopio` DISABLE KEYS */;
INSERT INTO `ventas_puntos_acopio` VALUES (1,3,6,'url1.jpg',150000.00,'2026-03-10 20:21:24'),(2,4,10,'url2.jpg',120000.00,'2026-03-10 20:21:24'),(3,9,6,'url3.jpg',90000.00,'2026-03-10 20:21:24'),(4,3,10,'url4.jpg',200000.00,'2026-03-10 20:21:24'),(5,4,6,'url5.jpg',85000.00,'2026-03-10 20:21:24'),(6,9,10,'url6.jpg',110000.00,'2026-03-10 20:21:24'),(7,3,6,'url7.jpg',50000.00,'2026-03-10 20:21:24'),(8,4,10,'url8.jpg',130000.00,'2026-03-10 20:21:24'),(9,9,6,'url9.jpg',75000.00,'2026-03-10 20:21:24'),(10,3,10,'url10.jpg',95000.00,'2026-03-10 20:21:24');
/*!40000 ALTER TABLE `ventas_puntos_acopio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-10 15:33:27
