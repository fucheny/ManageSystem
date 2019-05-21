-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.40


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema st2015
--

CREATE DATABASE IF NOT EXISTS st2015;
USE st2015;

--
-- Definition of table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `clazzNo` varchar(11) NOT NULL,
  `clazzName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`clazzNo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clazz`
--

/*!40000 ALTER TABLE `clazz` DISABLE KEYS */;
INSERT INTO `clazz` (`clazzNo`,`clazzName`) VALUES 
 ('01','计算机16-1'),
 ('03','计算机16-3'),
 ('04','计算机16-4'),
 ('08','计算机15-4'),
 ('10','计算机17-2'),
 ('09','计算机17-1'),
 ('07','计算机15-3'),
 ('05','计算机15-1');
/*!40000 ALTER TABLE `clazz` ENABLE KEYS */;


--
-- Definition of table `loginfo`
--

DROP TABLE IF EXISTS `loginfo`;
CREATE TABLE `loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loginfo`
--

/*!40000 ALTER TABLE `loginfo` DISABLE KEYS */;
INSERT INTO `loginfo` (`id`,`userid`,`logintime`) VALUES 
 (1,'admin','2015-11-18 18:11:06'),
 (2,'admin','2015-11-18 18:11:32'),
 (3,'admin','2015-11-18 20:43:34'),
 (4,'admin','2015-11-18 20:52:48'),
 (5,'admin','2019-05-04 10:25:24'),
 (6,'admin','2019-05-04 10:34:32');
/*!40000 ALTER TABLE `loginfo` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `no` varchar(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `clazzNo` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`no`,`name`,`age`,`birthday`,`clazzNo`) VALUES 
 ('20154091001','li',20,'1997-09-01','01'),
 ('20154091002','zhang',20,'1997-09-01','01'),
 ('20154091003','li',20,'1997-09-01','01'),
 ('20154091004','zhang',20,'1997-09-01','03'),
 ('20154091005','li',20,'1997-09-01','03'),
 ('20154091006','zhang',20,'1997-09-01','03'),
 ('20154091007','li',20,'1997-09-01','03'),
 ('20144091008','zhang',20,'1997-09-01','02'),
 ('20164091006','zhang',20,'1997-09-01','01'),
 ('20164091007','li',20,'1997-09-01','01'),
 ('20164091001','li',20,'1997-09-01','01'),
 ('20164091002','zhang',20,'1997-09-01','01'),
 ('20164091003','li',20,'1997-09-01','01'),
 ('20164091004','zhang',20,'1997-09-01','01'),
 ('20164091005','li',20,'1997-09-01','01'),
 ('20164091010','zhang',20,'1997-09-01','02'),
 ('20164091011','li',20,'1997-09-01','02'),
 ('20164091012','li',20,'1997-09-01','02'),
 ('20164091013','li',20,'1997-09-01','02'),
 ('20164091014','li',20,'1997-09-01','02'),
 ('20164091015','li',20,'1997-09-01','01'),
 ('20164091016','li',20,'1997-09-01','01');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` varchar(20) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `userType` char(2) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `ClazzNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`,`userName`,`password`,`email`,`userType`,`photo`,`ClazzNo`) VALUES 
 ('admin','张三','c4ca4238a0b923820dcc509a6f75849b','3@126.com','01',NULL,NULL),
 ('1','cde','c4ca4238a0b923820dcc509a6f75849b','1@163.com','02','20121015194055.JPG','01'),
 ('20074071003','ert','c4ca4238a0b923820dcc509a6f75849b','1@163.com','02',NULL,'02'),
 ('4','ghi','c4ca4238a0b923820dcc509a6f75849b','2@tom.com','02',NULL,'02'),
 ('5','ijk','c4ca4238a0b923820dcc509a6f75849b','3@126.com','02',NULL,'02'),
 ('6','abc','c4ca4238a0b923820dcc509a6f75849b','1@163.com','02',NULL,'02'),
 ('g001','abc','c4ca4238a0b923820dcc509a6f75849b','1@163.com','02',NULL,'02');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
