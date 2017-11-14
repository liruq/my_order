-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.11-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 purchase_orders.my_order 结构
CREATE TABLE IF NOT EXISTS `my_order` (
  `id` varchar(125) NOT NULL,
  `merteriel_no` int(4) NOT NULL,
  `merteriel_name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `count` int(8) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `create_by` varchar(300) DEFAULT NULL,
  `create_on` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购单';

-- 正在导出表  purchase_orders.my_order 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `my_order` DISABLE KEYS */;
INSERT INTO `my_order` (`id`, `merteriel_no`, `merteriel_name`, `price`, `count`, `unit`, `total_price`, `create_by`, `create_on`, `address`) VALUES
	('402881f05fbab715015fbab8c01d0001', 1001, 'milk', 3.00, 90, '?', 270.00, '90', '2017-11-14T00:00:00', 'chinaGuangzhou'),
	('402881f05fbafe66015fbb04d1790000', 1002, 'lantern', 90.00, 10, '个', 900.00, 'rockey', '2017-11-14T00:00:00', '1');
/*!40000 ALTER TABLE `my_order` ENABLE KEYS */;


-- 导出  表 purchase_orders.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(255) NOT NULL,
  `userid` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userCommand` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `statu` int(4) NOT NULL COMMENT '身份描述经理、员工等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  purchase_orders.user 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `userid`, `username`, `userCommand`, `password`, `statu`) VALUES
	(111, 0, 'a', 'e', 'a', 2),
	(222, 0, 'b', 'e', 'a', 2),
	(333, 0, 'CCCC', 'e', '333CCC', 3),
	(444, 0, 'DDDD', 'e', '444DDD', 3),
	(555, 0, 'EEEE', 'e', '555EEE', 3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
