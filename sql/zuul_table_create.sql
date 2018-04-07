USE 'zuul_db';

CREATE TABLE IF NOT EXISTS `zuul_db`.`t_gateway_api_define`(  
  `id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `path_name` varchar(50) NOT NULL  COMMENT '请求别名',  
  `path` varchar(255) NOT NULL  COMMENT '请求路径',
  `service_id` varchar(50) DEFAULT NULL COMMENT '服务id',
  `url` varchar(255) DEFAULT NULL COMMENT '匹配路径',
  `retryable` tinyint(1) DEFAULT 0 COMMENT '重试设置',
  `enabled` tinyint(1) DEFAULT 1 COMMENT '可用标识',
  `strip_prefix` tinyint(1) DEFAULT 1 COMMENT '请求前缀设置，1-true 2-fasle',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;