CREATE TABLE `product` (
  `id` int(11) unsigned  NOT NULL AUTO_INCREMENT COMMENT '��ƷId',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '��Ʒ����',
  `description` varchar(120) DEFAULT '' COMMENT '��Ʒ����',
  `price` double NOT NULL COMMENT '��Ʒ����',
  `inventoryId` int(11) DEFAULT NULL COMMENT '���Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `inventory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL COMMENT '���ÿ��',
  `lockedCount` int(11) DEFAULT NULL COMMENT '�������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;