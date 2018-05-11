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
  `count` int(11) DEFAULT 0 COMMENT '可用库存',
  `lockedCount` int(11) DEFAULT 0 COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `orderInfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `totalPrice` double ,
  `status` varchar(20) COLLATE utf8mb4_unicode_ci,
  `createTime` varchar(40),
  `finishTime` varchar(40) ,
  `paidTime` varchar(40),
  `withdrawnTime` varchar(40),
  `userId` int(11) NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;

CREATE TABLE `logistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logisticsStatus` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `outboundTime` VARCHAR(40),
  `signedTime` VARCHAR(40),
  `deliveryMan` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `orderId` INT ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `productShoot` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `productName` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `productDescription` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `purchasePrice` double NOT NULL,
  `purchaseCount` int(11) NOT NULL DEFAULT '1',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

