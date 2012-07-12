-- phpMyAdmin SQL Dump
-- version 3.2.2.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2012 年 07 月 12 日 13:39
-- 服务器版本: 5.0.67
-- PHP 版本: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `sisys`
--

-- --------------------------------------------------------

--
-- 表的结构 `batch`
--

CREATE TABLE IF NOT EXISTS `batch` (
  `Id` int(11) NOT NULL auto_increment,
  `batchNo` varchar(20) default NULL,
  `flowId` int(11) default NULL,
  `proId` int(11) default NULL,
  `workTabId` int(11) default NULL,
  `status` int(11) default NULL,
  `startTime` date default NULL,
  `endTime` date default NULL,
  `disqNum` int(11) default NULL,
  `disqPercent` int(11) default NULL,
  `totalNum` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_12` (`workTabId`),
  KEY `FK_Reference_5` (`flowId`),
  KEY `FK_Reference_6` (`proId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `batch`
--


-- --------------------------------------------------------

--
-- 表的结构 `dailystaffdisq`
--

CREATE TABLE IF NOT EXISTS `dailystaffdisq` (
  `Id` int(11) NOT NULL auto_increment,
  `disqdeId` int(11) default NULL,
  `staffId` int(11) default NULL,
  `totalNum` int(11) default NULL,
  `time` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_20` (`disqdeId`),
  KEY `FK_Reference_21` (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `dailystaffdisq`
--


-- --------------------------------------------------------

--
-- 表的结构 `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `Id` int(11) NOT NULL auto_increment,
  `deptNo` int(11) default NULL,
  `deptName` varchar(20) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `department`
--


-- --------------------------------------------------------

--
-- 表的结构 `disqdetail`
--

CREATE TABLE IF NOT EXISTS `disqdetail` (
  `Id` int(11) NOT NULL auto_increment,
  `disKId` int(11) default NULL,
  `nextId` int(11) default NULL,
  `num` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_2` (`disKId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `disqdetail`
--


-- --------------------------------------------------------

--
-- 表的结构 `disqkind`
--

CREATE TABLE IF NOT EXISTS `disqkind` (
  `Id` int(11) NOT NULL auto_increment,
  `disDesc` varchar(30) default NULL,
  `kind` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `disqkind`
--


-- --------------------------------------------------------

--
-- 表的结构 `flowpath`
--

CREATE TABLE IF NOT EXISTS `flowpath` (
  `Id` int(11) NOT NULL auto_increment,
  `sequence` varchar(20) default NULL,
  `proId` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_10` (`proId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `flowpath`
--


-- --------------------------------------------------------

--
-- 表的结构 `process`
--

CREATE TABLE IF NOT EXISTS `process` (
  `Id` int(11) NOT NULL auto_increment,
  `procName` varchar(20) default NULL,
  `colorNo` varchar(10) default NULL,
  `procNo` varchar(20) default NULL,
  `unitOutput` int(11) default NULL,
  `unitCost` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `process`
--


-- --------------------------------------------------------

--
-- 表的结构 `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `Id` int(11) NOT NULL auto_increment,
  `deptId` int(11) default NULL,
  `prolineId` int(11) default NULL,
  `proNo` varchar(20) default NULL,
  `proName` varchar(20) default NULL,
  `time` date default NULL,
  `disqNum` int(11) default NULL,
  `disqPerc` double default NULL,
  `totalNum` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_14` (`deptId`),
  KEY `FK_Reference_22` (`prolineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `product`
--


-- --------------------------------------------------------

--
-- 表的结构 `productline`
--

CREATE TABLE IF NOT EXISTS `productline` (
  `Id` int(11) NOT NULL auto_increment,
  `lineNo` int(11) default NULL,
  `lineDesc` varchar(20) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `productline`
--


-- --------------------------------------------------------

--
-- 表的结构 `scheduletab`
--

CREATE TABLE IF NOT EXISTS `scheduletab` (
  `Id` int(11) NOT NULL auto_increment,
  `batchId` int(11) default NULL,
  `time` date default NULL,
  `colorNo` varchar(10) default NULL,
  `num` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_16` (`batchId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `scheduletab`
--


-- --------------------------------------------------------

--
-- 表的结构 `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `Id` int(11) NOT NULL auto_increment,
  `deptId` int(11) default NULL,
  `kind` varchar(20) default NULL,
  `staName` varchar(20) default NULL,
  `staNo` varchar(20) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `AK_Key_2` (`Id`),
  KEY `FK_Reference_15` (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `staff`
--


-- --------------------------------------------------------

--
-- 表的结构 `staffdetail`
--

CREATE TABLE IF NOT EXISTS `staffdetail` (
  `Id` int(11) NOT NULL auto_increment,
  `staffId` int(11) default NULL,
  `proName` varchar(20) default NULL,
  `proNo` varchar(20) default NULL,
  `procName` varchar(10) default NULL,
  `quaNum` int(11) default NULL,
  `gWaste` int(11) default NULL,
  `lWaste` int(11) default NULL,
  `workHours` double default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_18` (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `staffdetail`
--


-- --------------------------------------------------------

--
-- 表的结构 `staffkind`
--

CREATE TABLE IF NOT EXISTS `staffkind` (
  `Id` int(11) NOT NULL auto_increment,
  `kindDesc` varchar(20) default NULL,
  `isDelete` int(11) default NULL,
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `staffkind`
--


-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(20) default NULL,
  `level` int(11) default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `user`
--


-- --------------------------------------------------------

--
-- 表的结构 `workform`
--

CREATE TABLE IF NOT EXISTS `workform` (
  `Id` int(11) NOT NULL auto_increment,
  `staId` int(11) default NULL,
  `procId` int(11) default NULL,
  `batchId` int(11) default NULL,
  `proId` int(11) default NULL,
  `quaNum` int(11) default NULL,
  `disDetail` varchar(0) default NULL,
  `time` date default NULL,
  `isDelete` int(11) default '0',
  `deleteTime` date default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_11` (`proId`),
  KEY `FK_Reference_7` (`batchId`),
  KEY `FK_Reference_8` (`staId`),
  KEY `FK_Reference_9` (`procId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `workform`
--


-- --------------------------------------------------------

--
-- 表的结构 `workhourstab`
--

CREATE TABLE IF NOT EXISTS `workhourstab` (
  `Id` int(11) NOT NULL auto_increment,
  `staId` int(11) default NULL,
  `time` date default NULL,
  `workHours` double default NULL,
  `salary` double default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_17` (`staId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `workhourstab`
--


-- --------------------------------------------------------

--
-- 表的结构 `worktab`
--

CREATE TABLE IF NOT EXISTS `worktab` (
  `Id` int(11) NOT NULL auto_increment,
  `procId` int(11) default NULL,
  `quNum` int(11) default NULL,
  `disqNum` int(11) default NULL,
  `isOver` int(11) default NULL,
  `overTime` date default NULL,
  `isEnd` int(11) default NULL,
  PRIMARY KEY  (`Id`),
  KEY `FK_Reference_13` (`procId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `worktab`
--


--
-- 限制导出的表
--

--
-- 限制表 `batch`
--
ALTER TABLE `batch`
  ADD CONSTRAINT `FK_Reference_12` FOREIGN KEY (`workTabId`) REFERENCES `worktab` (`Id`),
  ADD CONSTRAINT `FK_Reference_5` FOREIGN KEY (`flowId`) REFERENCES `flowpath` (`Id`),
  ADD CONSTRAINT `FK_Reference_6` FOREIGN KEY (`proId`) REFERENCES `product` (`Id`);

--
-- 限制表 `dailystaffdisq`
--
ALTER TABLE `dailystaffdisq`
  ADD CONSTRAINT `FK_Reference_20` FOREIGN KEY (`disqdeId`) REFERENCES `disqdetail` (`Id`),
  ADD CONSTRAINT `FK_Reference_21` FOREIGN KEY (`staffId`) REFERENCES `staff` (`Id`);

--
-- 限制表 `disqdetail`
--
ALTER TABLE `disqdetail`
  ADD CONSTRAINT `FK_Reference_2` FOREIGN KEY (`disKId`) REFERENCES `disqkind` (`Id`);

--
-- 限制表 `flowpath`
--
ALTER TABLE `flowpath`
  ADD CONSTRAINT `FK_Reference_10` FOREIGN KEY (`proId`) REFERENCES `product` (`Id`);

--
-- 限制表 `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_Reference_14` FOREIGN KEY (`deptId`) REFERENCES `department` (`Id`),
  ADD CONSTRAINT `FK_Reference_22` FOREIGN KEY (`prolineId`) REFERENCES `productline` (`Id`);

--
-- 限制表 `scheduletab`
--
ALTER TABLE `scheduletab`
  ADD CONSTRAINT `FK_Reference_16` FOREIGN KEY (`batchId`) REFERENCES `batch` (`Id`);

--
-- 限制表 `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FK_Reference_15` FOREIGN KEY (`deptId`) REFERENCES `department` (`Id`);

--
-- 限制表 `staffdetail`
--
ALTER TABLE `staffdetail`
  ADD CONSTRAINT `FK_Reference_18` FOREIGN KEY (`staffId`) REFERENCES `staff` (`Id`);

--
-- 限制表 `workform`
--
ALTER TABLE `workform`
  ADD CONSTRAINT `FK_Reference_11` FOREIGN KEY (`proId`) REFERENCES `product` (`Id`),
  ADD CONSTRAINT `FK_Reference_7` FOREIGN KEY (`batchId`) REFERENCES `batch` (`Id`),
  ADD CONSTRAINT `FK_Reference_8` FOREIGN KEY (`staId`) REFERENCES `staff` (`Id`),
  ADD CONSTRAINT `FK_Reference_9` FOREIGN KEY (`procId`) REFERENCES `process` (`Id`);

--
-- 限制表 `workhourstab`
--
ALTER TABLE `workhourstab`
  ADD CONSTRAINT `FK_Reference_17` FOREIGN KEY (`staId`) REFERENCES `staff` (`Id`);

--
-- 限制表 `worktab`
--
ALTER TABLE `worktab`
  ADD CONSTRAINT `FK_Reference_13` FOREIGN KEY (`procId`) REFERENCES `process` (`Id`);
