CREATE TABLE `product` (
  `id` int(11) unsigned  NOT NULL AUTO_INCREMENT COMMENT '商品Id',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '商品名称',
  `description` varchar(120) DEFAULT '' COMMENT '商品描述',
  `price` double NOT NULL COMMENT '商品单价',
  `inventoryId` int(11) DEFAULT NULL COMMENT '库存Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `inventory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL COMMENT '可用库存',
  `lockedCount` int(11) DEFAULT NULL COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;