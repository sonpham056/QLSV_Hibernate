CREATE DATABASE  IF NOT EXISTS `qlsv` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlsv`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qlsv
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bangdiem`
--

DROP TABLE IF EXISTS `bangdiem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangdiem` (
  `maSinhVien_CT` varchar(10) NOT NULL,
  `maMonHoc_CT` varchar(10) NOT NULL,
  `GK` float DEFAULT NULL,
  `CK` float DEFAULT NULL,
  `khac` float DEFAULT NULL,
  `tong` float DEFAULT NULL,
  PRIMARY KEY (`maSinhVien_CT`,`maMonHoc_CT`),
  KEY `maMonHoc_CT` (`maMonHoc_CT`),
  CONSTRAINT `bangdiem_ibfk_1` FOREIGN KEY (`maSinhVien_CT`) REFERENCES `sinhvien` (`maSinhVien`),
  CONSTRAINT `bangdiem_ibfk_2` FOREIGN KEY (`maMonHoc_CT`) REFERENCES `monhoc` (`maMonHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangdiem`
--

LOCK TABLES `bangdiem` WRITE;
/*!40000 ALTER TABLE `bangdiem` DISABLE KEYS */;
INSERT INTO `bangdiem` VALUES ('1742001','CTT011',0,0,0,0),('1742001','CTT012',0,0,0,0),('1742002','CTT011',0,0,0,0),('1742002','CTT012',0,0,0,0),('1742003','CTT011',0,0,0,0),('1742003','CTT012',0,0,0,0),('1742004','CTT011',0,0,0,0),('1742004','CTT012',0,0,0,0),('1742005','CTT001',9,9,9,9),('1742005','CTT011',0,0,0,0),('1742005','CTT012',0,0,0,0),('1811063163','CTT001',10,10,10,10),('1842001','CTT001',0,0,0,0),('1842001','CTT002',0,0,0,0),('1842002','CTT001',4,5,6,5),('1842002','CTT002',0,0,0,0),('1842003','CTT001',7,8,9,8.5),('1842003','CTT002',0,0,0,0),('1842004','CTT001',2,4,6,4.5),('1842004','CTT002',0,0,0,0),('1842005','CTT001',8,10,2,9.5),('1842005','CTT002',0,0,0,0);
/*!40000 ALTER TABLE `bangdiem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctmonhoc`
--

DROP TABLE IF EXISTS `ctmonhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctmonhoc` (
  `maMonHoc_CT` varchar(10) NOT NULL,
  `maLop_CT` varchar(10) NOT NULL,
  `phongHoc` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`maMonHoc_CT`,`maLop_CT`),
  KEY `maLop_CT` (`maLop_CT`),
  CONSTRAINT `ctmonhoc_ibfk_1` FOREIGN KEY (`maMonHoc_CT`) REFERENCES `monhoc` (`maMonHoc`),
  CONSTRAINT `ctmonhoc_ibfk_2` FOREIGN KEY (`maLop_CT`) REFERENCES `lop` (`maLop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctmonhoc`
--

LOCK TABLES `ctmonhoc` WRITE;
/*!40000 ALTER TABLE `ctmonhoc` DISABLE KEYS */;
INSERT INTO `ctmonhoc` VALUES ('CTT001','18HCB','C31'),('CTT002','18HCB','C31'),('CTT011','17HCB','C32'),('CTT012','17HCB','C32'),('Toan1','18DATA1','p1');
/*!40000 ALTER TABLE `ctmonhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `maLop` varchar(10) NOT NULL,
  PRIMARY KEY (`maLop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES ('17HCB'),('18DATA1'),('18DTHD1'),('18HCB');
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monhoc`
--

DROP TABLE IF EXISTS `monhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhoc` (
  `maMonHoc` varchar(10) NOT NULL,
  `tenMonHoc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`maMonHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhoc`
--

LOCK TABLES `monhoc` WRITE;
/*!40000 ALTER TABLE `monhoc` DISABLE KEYS */;
INSERT INTO `monhoc` VALUES ('CTT001','Lập trình ứng dụng Java'),('CTT002','Mạng máy tính'),('CTT011','Thiết kế giao diện'),('CTT012','Kiểm chứng phần mềm'),('Toan1','Toan tuyen tinh');
/*!40000 ALTER TABLE `monhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien` (
  `maSinhVien` varchar(10) NOT NULL,
  `hoVaTen` varchar(100) DEFAULT NULL,
  `gioiTinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cmnd` varchar(9) DEFAULT NULL,
  `maLop_SinhVien` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`maSinhVien`),
  KEY `maLop_SinhVien` (`maLop_SinhVien`),
  CONSTRAINT `sinhvien_ibfk_1` FOREIGN KEY (`maLop_SinhVien`) REFERENCES `lop` (`maLop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` VALUES ('1742001','Nguyễn Văn A','nam','123456789','17HCB'),('1742002','Trần Văn B','nam','234567891','17HCB'),('1742003','Huỳnh Thị C','Nữ','345678912','17HCB'),('1742004','Mai Văn D','Nam','456789123','17HCB'),('1742005','Hồ Thị E','Nữ','567891234','17HCB'),('1811063113','Phạm Nguyên Quốc Sơn','Nam','212583301','18DTHD1'),('1811063123','Trương Quốc Phòng','Nam','123123123','18DATA1'),('1811063163','Nguyễn Thị Quỳnh Thư','Nữ','212586123','18DTHD1'),('1842001','Lý Văn F','Nam','678912345','18HCB'),('1842002','Chiêu Văn G','Nam','789123456','18HCB'),('1842003','Trần Thị H','Nữ','891234567','18HCB'),('1842004','Mạc Văn I','Nam','912345678','18HCB'),('1842005','Văn Thị J','Nữ','987654321','18HCB'),('giaovu','Giáo Vụ',NULL,NULL,NULL);
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `taiKhoan` varchar(10) NOT NULL,
  `matKhau` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`taiKhoan`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`taiKhoan`) REFERENCES `sinhvien` (`maSinhVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('1742001','1742001'),('1742002','1742002'),('1742003','1742003'),('1742004','1742004'),('1742005','1742005'),('1811063113','123'),('1811063163','321'),('1842001','1842001'),('1842002','1842002'),('1842003','1842003'),('1842004','1842004'),('1842005','1842005'),('giaovu','giaovu');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qlsv'
--

--
-- Dumping routines for database 'qlsv'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-05 14:37:51
