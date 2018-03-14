/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : graduation_design

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-03-14 20:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_shares
-- ----------------------------
DROP TABLE IF EXISTS `tb_shares`;
CREATE TABLE `tb_shares` (
  `id` varchar(36) NOT NULL COMMENT '唯一标识',
  `shares_num` varchar(36) NOT NULL COMMENT '股票代码',
  `shares_href` varchar(36) NOT NULL COMMENT '股票网址',
  `shares_name` varchar(36) NOT NULL COMMENT '股票名字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_shares_detailed
-- ----------------------------
DROP TABLE IF EXISTS `tb_shares_detailed`;
CREATE TABLE `tb_shares_detailed` (
  `id` varchar(36) NOT NULL COMMENT '唯一标识',
  `shares_id` varchar(36) NOT NULL COMMENT '股票外键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `open_price` int(11) NOT NULL COMMENT '开盘价',
  `close_price` int(11) NOT NULL COMMENT '收盘价',
  `ceilling_price` int(11) NOT NULL COMMENT '最高价',
  `floor_price` int(11) NOT NULL COMMENT '最低价',
  `rise_and_fall_range` int(11) NOT NULL COMMENT '涨跌幅（%）',
  `rise_and_fall_quota` int(11) NOT NULL COMMENT '涨跌额',
  `volume` int(11) NOT NULL COMMENT '成交量(手)',
  `turn_volume` int(11) NOT NULL COMMENT '成交额（万）',
  `turnover_rate` int(11) NOT NULL COMMENT '换手率（%）',
  `amplitude` int(11) NOT NULL COMMENT '振幅',
  `p_e_ratio` int(11) NOT NULL COMMENT '市盈率',
  `state` tinyint(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(36) NOT NULL,
  `open_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `phone` varchar(13) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `account` varchar(60) DEFAULT NULL COMMENT '帐号',
  `is_binding` tinyint(4) NOT NULL COMMENT '是否注册了(是:1,否:0)',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(已关注:1,未关注:0)',
  `create_time` datetime NOT NULL COMMENT '关注时间',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `unregister_time` datetime DEFAULT NULL COMMENT '取消关注时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
