-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.36 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- to 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `to` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `to`;

-- 테이블 to.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `iid` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `uid` (`uid`),
  KEY `iid` (`iid`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_cart_users` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.cart:~2 rows (대략적) 내보내기
INSERT IGNORE INTO `cart` (`cid`, `uid`, `iid`, `count`) VALUES
	(1, 'lks', 1, 1),
	(2, 'lks', 3, 3);

-- 테이블 to.item 구조 내보내기
CREATE TABLE IF NOT EXISTS `item` (
  `iid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `content` varchar(600) DEFAULT NULL,
  `price` int NOT NULL,
  `isDeleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.item:~3 rows (대략적) 내보내기
INSERT IGNORE INTO `item` (`iid`, `name`, `content`, `price`, `isDeleted`) VALUES
	(1, '떡볶이', '매콤달콤 떡볶이 입니다.', 8000, 0),
	(2, '우동', '얼큰시원 맛있는 우동', 7000, 0),
	(3, '돈까스', '항상 새 기름으로 튀기는 돈까스', 11000, 0);

-- 테이블 to.order 구조 내보내기
CREATE TABLE IF NOT EXISTS `order` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL,
  `tid` int NOT NULL,
  `totalPrice` int NOT NULL,
  `regDate` datetime NOT NULL DEFAULT (now()),
  `isDeleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  KEY `FK_order_table` (`tid`),
  CONSTRAINT `FK_order_table` FOREIGN KEY (`tid`) REFERENCES `table` (`tid`),
  CONSTRAINT `FK_order_users` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.order:~0 rows (대략적) 내보내기

-- 테이블 to.orderitem 구조 내보내기
CREATE TABLE IF NOT EXISTS `orderitem` (
  `oiid` int NOT NULL AUTO_INCREMENT,
  `oid` int NOT NULL,
  `iid` int NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `count` int NOT NULL,
  `price` int NOT NULL,
  `isDeleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`oiid`),
  KEY `oid` (`oid`),
  KEY `iid` (`iid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.orderitem:~0 rows (대략적) 내보내기

-- 테이블 to.table 구조 내보내기
CREATE TABLE IF NOT EXISTS `table` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL DEFAULT '0',
  `regDate` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.table:~2 rows (대략적) 내보내기
INSERT IGNORE INTO `table` (`tid`, `status`, `regDate`) VALUES
	(1, 0, '2024-07-09 01:55:45'),
	(2, 0, '2024-07-09 01:55:49');

-- 테이블 to.users 구조 내보내기
CREATE TABLE IF NOT EXISTS `users` (
  `uid` varchar(50) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `type` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 테이블 데이터 to.users:~0 rows (대략적) 내보내기
INSERT IGNORE INTO `users` (`uid`, `name`, `pwd`, `type`) VALUES
	('lks', '이강성', '123', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
