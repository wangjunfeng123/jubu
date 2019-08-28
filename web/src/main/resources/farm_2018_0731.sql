/*
Navicat MySQL Data Transfer

Source Server         : nxin
Source Server Version : 50722
Source Host           : 47.95.8.251:3306
Source Database       : farm

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-31 09:49:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auth`
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `auth_type` int(11) DEFAULT NULL COMMENT '权限类型',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth
-- ----------------------------

-- ----------------------------
-- Table structure for `base_constraint`
-- ----------------------------
DROP TABLE IF EXISTS `base_constraint`;
CREATE TABLE `base_constraint` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `object_id` int(11) NOT NULL COMMENT '约束对象id',
  `constraint_min_value` double(10,2) DEFAULT NULL COMMENT '约束最小值',
  `constraint_max_value` double(10,2) DEFAULT NULL COMMENT '约束最大值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='约束对象';

-- ----------------------------
-- Records of base_constraint
-- ----------------------------
INSERT INTO `base_constraint` VALUES ('1', '1', '3.00', '99.00');

-- ----------------------------
-- Table structure for `org`
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of org
-- ----------------------------

-- ----------------------------
-- Table structure for `plan_param`
-- ----------------------------
DROP TABLE IF EXISTS `plan_param`;
CREATE TABLE `plan_param` (
  `id` int(11) NOT NULL COMMENT '主键',
  `plant_id` int(11) NOT NULL COMMENT '农作物id',
  `plan_param_rate` double(10,4) DEFAULT NULL COMMENT '比例值',
  `is_choose` tinyint(4) DEFAULT NULL COMMENT '是否选择',
  `seed_value` double(10,2) DEFAULT NULL COMMENT '籽实价值',
  `straw_value` double(10,2) DEFAULT NULL COMMENT '秸秆价值',
  `grass_value` double(10,2) DEFAULT NULL COMMENT '牧草价值',
  `total_vlaue` double(10,2) DEFAULT NULL COMMENT '种植全价值',
  `plant_profit` double(10,2) DEFAULT NULL COMMENT '种植利润',
  `sale_profit` double(10,2) DEFAULT NULL COMMENT '销售利润',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规划参数';

-- ----------------------------
-- Records of plan_param
-- ----------------------------

-- ----------------------------
-- Table structure for `plant_properties`
-- ----------------------------
DROP TABLE IF EXISTS `plant_properties`;
CREATE TABLE `plant_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plant_name` varchar(255) NOT NULL COMMENT '农作物名称',
  `seed_acre_yield` double(10,1) NOT NULL COMMENT '籽实亩产量',
  `machine_cost` double(10,1) NOT NULL COMMENT '农机使用费',
  `seed_fee` double(10,1) NOT NULL COMMENT '种子费',
  `fertilizer_cost` double(10,1) NOT NULL COMMENT '化肥费',
  `water_fertilizer` double(10,1) NOT NULL COMMENT '浇水费',
  `people_cost` double(10,1) NOT NULL COMMENT '人工花费',
  `plant_needs_N` double(10,1) NOT NULL COMMENT '植物需氮\r\n            植物需氮\r\n            植物需氮',
  `plant_needs_P` double(10,1) NOT NULL COMMENT '植物需磷',
  `plant_needs_K` double(10,1) NOT NULL COMMENT '植物需钾',
  `straw_quantity` double(10,1) DEFAULT NULL COMMENT '秆草亩产量',
  `local_value` double(10,2) NOT NULL COMMENT '当地价值',
  `straw_values` double(10,2) NOT NULL COMMENT '秸秆价值',
  `acre_cost` double(7,1) NOT NULL COMMENT '亩成本',
  `nitrogen_fixing_capacity` double(5,1) DEFAULT NULL COMMENT '固氮能力',
  `is_intercrop` smallint(4) NOT NULL COMMENT '间作特征',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：例如1标示草，2标示农作物',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `mod_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`id`,`local_value`),
  UNIQUE KEY `idx_plant_name` (`plant_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COMMENT='农作物系数';

-- ----------------------------
-- Records of plant_properties
-- ----------------------------
INSERT INTO `plant_properties` VALUES ('1', '间作沙打旺', '600.0', '120.0', '13.8', '90.0', '15.0', '80.0', '3.0', '7.0', '9.0', null, '1.60', '1.80', '318.8', '4.5', '1', null, null, null, '0');
INSERT INTO `plant_properties` VALUES ('2', '间作白三叶', '450.0', '100.0', '13.8', '60.0', '18.0', '60.0', '3.0', '6.0', '7.0', null, '1.60', '1.80', '251.8', '7.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('3', '间作黑麦草', '400.0', '130.0', '15.0', '110.0', '18.0', '80.0', '10.0', '5.0', '11.0', null, '1.60', '1.60', '353.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('4', '坡地平播沙打旺', '1100.0', '150.0', '13.8', '130.0', '0.0', '110.0', '7.0', '11.0', '14.0', null, '1.60', '1.80', '403.8', '9.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('5', '坡地平播黑麦草', '1000.0', '150.0', '12.5', '150.0', '0.0', '110.0', '16.0', '9.0', '15.0', null, '1.40', '1.60', '422.5', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('6', '坡地平播苏丹草', '1200.0', '170.0', '15.0', '120.0', '0.0', '110.0', '16.0', '9.0', '15.0', null, '1.20', '1.40', '415.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('7', '坡地平播玉米草', '1500.0', '170.0', '15.0', '130.0', '0.0', '110.0', '16.0', '9.0', '15.0', null, '1.20', '1.40', '425.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('8', '坡地平播皇竹草', '1500.0', '170.0', '11.5', '130.0', '0.0', '110.0', '16.0', '9.0', '15.0', null, '1.20', '1.40', '421.5', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('9', '树下覆盖沙打旺', '850.0', '120.0', '11.3', '110.0', '50.0', '100.0', '7.0', '11.0', '14.0', null, '1.60', '1.60', '391.3', '6.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('10', '树下覆盖白三叶', '750.0', '110.0', '11.3', '110.0', '50.0', '100.0', '7.0', '11.0', '14.0', null, '1.60', '1.80', '381.3', '7.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('11', '树下覆盖黑麦草', '800.0', '120.0', '16.3', '100.0', '60.0', '100.0', '12.0', '7.0', '11.0', null, '1.40', '1.60', '396.3', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('12', '基本耕地平播玉米', '510.0', '200.0', '35.0', '115.0', '65.0', '78.0', '17.5', '6.5', '22.5', '459.0', '1.85', '0.40', '493.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('14', '基本耕地间作玉米', '459.0', '160.0', '33.0', '105.0', '65.0', '65.0', '15.8', '5.8', '20.2', '413.1', '1.85', '0.40', '428.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('15', '坡地平播玉米', '390.0', '160.0', '40.0', '140.0', '0.0', '85.0', '15.4', '5.0', '17.2', '351.0', '1.80', '0.40', '425.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('16', '基本耕地平播谷子', '350.0', '205.0', '65.0', '150.0', '68.0', '120.0', '10.5', '5.2', '9.1', '315.0', '2.80', '0.60', '608.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('17', '基本耕地间作谷子', '315.0', '153.8', '48.8', '112.5', '51.0', '110.0', '8.4', '4.2', '7.3', '283.5', '2.80', '0.60', '476.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('18', '基本耕地平播大豆', '280.0', '150.0', '69.0', '115.0', '60.0', '95.0', '23.1', '4.9', '10.1', '252.0', '3.00', '0.55', '489.0', '7.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('19', '坡地平播大豆', '200.0', '150.0', '60.0', '115.0', '0.0', '88.0', '19.0', '4.0', '8.3', '180.0', '3.00', '0.55', '413.0', '7.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('20', '基本耕地花生含壳', '330.0', '150.0', '85.0', '120.0', '60.0', '120.0', '17.2', '3.3', '7.9', '297.0', '3.30', '0.70', '535.0', '8.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('21', '山地平播花生含壳', '280.0', '150.0', '69.0', '120.0', '0.0', '100.0', '20.0', '4.0', '10.0', '252.0', '3.30', '0.70', '439.0', '8.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('22', '基本耕地平播高粱', '330.0', '200.0', '60.0', '140.0', '65.0', '98.0', '15.0', '6.5', '18.0', '660.0', '2.80', '0.45', '563.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('23', '基本耕地间作高粱', '250.0', '160.0', '48.0', '125.0', '40.0', '88.0', '11.4', '4.9', '13.6', '500.0', '2.80', '0.45', '461.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('24', '坡地平播高粱', '240.0', '160.0', '55.0', '140.0', '0.0', '80.0', '10.9', '4.7', '13.1', '480.0', '2.80', '0.45', '435.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('25', '基本耕地种植红薯', '1800.0', '160.0', '660.0', '150.0', '55.0', '155.0', '9.9', '5.0', '18.9', '288.0', '1.20', '0.45', '1180.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('26', '坡地种植红薯', '1500.0', '160.0', '660.0', '120.0', '0.0', '155.0', '8.2', '4.2', '15.7', '240.0', '1.40', '0.45', '1095.0', '0.0', '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('27', '基本耕地种植土豆', '1800.0', '160.0', '650.0', '150.0', '55.0', '155.0', '9.9', '5.0', '18.9', '288.0', '1.20', '0.45', '1170.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('28', '坡地种植土豆', '1200.0', '160.0', '650.0', '120.0', '0.0', '155.0', '9.9', '5.0', '18.9', '192.0', '1.20', '0.45', '1085.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('29', '基本耕地种植水稻', '600.0', '180.0', '90.0', '185.0', '30.0', '185.0', '13.2', '7.2', '19.2', '540.0', '2.30', '0.40', '670.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('30', '基本耕地平播小麦', '410.0', '140.0', '80.0', '150.0', '70.0', '80.0', '12.3', '5.1', '10.2', '369.0', '2.20', '0.40', '520.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('31', '基本耕地间作小麦', '369.0', '120.0', '60.0', '125.0', '70.0', '70.0', '11.1', '4.6', '9.2', '332.1', '2.20', '0.40', '445.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('34', '坡地平播小麦', '330.0', '140.0', '65.0', '160.0', '0.0', '90.0', '11.4', '4.1', '8.2', '297.0', '2.20', '0.40', '455.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('35', '基本耕地种植豌豆', '320.0', '150.0', '80.0', '115.0', '65.0', '105.0', '23.1', '4.9', '10.1', '288.0', '3.30', '0.65', '515.0', '8.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('36', '坡地种植豌豆', '260.0', '120.0', '80.0', '115.0', '0.0', '92.0', '18.8', '4.0', '8.2', '234.0', '3.30', '0.65', '407.0', '8.0', '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('37', '基本耕地平播燕麦', '300.0', '140.0', '70.0', '150.0', '65.0', '80.0', '12.0', '8.0', '12.0', '270.0', '3.00', '0.55', '505.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('38', '基本耕地间作燕麦', '270.0', '130.0', '60.0', '135.0', '55.0', '70.0', '9.6', '6.4', '9.6', '243.0', '3.00', '0.55', '450.0', null, '1', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('39', '坡地平播燕麦', '240.0', '130.0', '65.0', '150.0', '0.0', '90.0', '9.6', '6.4', '9.6', '270.0', '3.00', '0.50', '435.0', null, '0', null, null, null, '1');
INSERT INTO `plant_properties` VALUES ('41', '基本耕地平播油菜', '200.0', '150.0', '95.0', '200.0', '85.0', '110.0', '19.7', '7.2', '17.8', '240.0', '4.00', '0.50', '640.0', null, '1', null, null, null, '1');

-- ----------------------------
-- Table structure for `plant_straw_param`
-- ----------------------------
DROP TABLE IF EXISTS `plant_straw_param`;
CREATE TABLE `plant_straw_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plant_id` int(11) DEFAULT NULL COMMENT '农作物id',
  `dry_ratio_straw` double(10,4) DEFAULT NULL COMMENT '秆草干物质比',
  `straw_coarse_protein` double(10,2) DEFAULT NULL COMMENT '秆草粗蛋白\r\n            秆草粗蛋白\r\n            ',
  `digestive_energy` double(7,2) DEFAULT NULL COMMENT '消化能',
  `comprehensive_energy` double(10,2) DEFAULT NULL COMMENT '综合净能',
  `RND` double(5,2) DEFAULT NULL COMMENT 'RND',
  `Ca` double(7,2) DEFAULT NULL COMMENT '钙',
  `P` double(10,2) DEFAULT NULL COMMENT '磷',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：1标示可用，0标示不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='甘草参数';

-- ----------------------------
-- Records of plant_straw_param
-- ----------------------------
INSERT INTO `plant_straw_param` VALUES ('1', '1', '0.9000', '15.30', '10.00', '4.65', '0.60', '0.36', '0.18', '0');
INSERT INTO `plant_straw_param` VALUES ('2', '2', '0.9000', '16.50', '8.60', '4.00', '0.42', '0.30', '0.18', '1');

-- ----------------------------
-- Table structure for `resource_category`
-- ----------------------------
DROP TABLE IF EXISTS `resource_category`;
CREATE TABLE `resource_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `object_name` varchar(255) NOT NULL COMMENT 'objectId',
  `level` varchar(255) DEFAULT NULL COMMENT '什么级别的品类',
  `pid` int(11) DEFAULT NULL COMMENT '父类id',
  `is_show` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=622236 DEFAULT CHARSET=utf8mb4 COMMENT='资源分类';

-- ----------------------------
-- Records of resource_category
-- ----------------------------
INSERT INTO `resource_category` VALUES ('1', '基本约束', '1', '-1', '1');
INSERT INTO `resource_category` VALUES ('2', '化肥约束', '1', '-1', '1');
INSERT INTO `resource_category` VALUES ('3', '资源约束', '1', '-1', '1');
INSERT INTO `resource_category` VALUES ('4', '耕地分类', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('5', '夏季作物', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('6', '养殖', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('7', '坡地多年生牧草', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('8', '冬季作物', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('9', '树下覆盖牧草', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('10', '牧草间作', '2', '1', '1');
INSERT INTO `resource_category` VALUES ('11', '基本旱耕地', '3', '4', '1');
INSERT INTO `resource_category` VALUES ('12', '坡地', '3', '4', '1');
INSERT INTO `resource_category` VALUES ('13', '水田', '3', '4', '1');
INSERT INTO `resource_category` VALUES ('14', '果园', '3', '4', '1');
INSERT INTO `resource_category` VALUES ('15', '蔬菜用地', '3', '4', '1');
INSERT INTO `resource_category` VALUES ('16', '玉米', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('17', '谷子', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('18', '大豆', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('19', '花生', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('20', '高粱', '3', '9', '1');
INSERT INTO `resource_category` VALUES ('21', '红薯', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('22', '土豆', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('23', '水稻', '3', '5', '1');
INSERT INTO `resource_category` VALUES ('24', '坡地平播玉米草', '3', '7', '1');
INSERT INTO `resource_category` VALUES ('25', '坡地平播皇竹草', '3', '7', '1');
INSERT INTO `resource_category` VALUES ('26', '坡地平播沙打旺', '3', '7', '1');
INSERT INTO `resource_category` VALUES ('27', '坡地平播黑麦草', '3', '7', '1');
INSERT INTO `resource_category` VALUES ('28', '坡地平播苏丹草', '3', '7', '1');
INSERT INTO `resource_category` VALUES ('29', '小麦', '3', '8', '1');
INSERT INTO `resource_category` VALUES ('30', '豌豆', '3', '8', '1');
INSERT INTO `resource_category` VALUES ('31', '燕麦', '3', '8', '1');
INSERT INTO `resource_category` VALUES ('32', '油菜', '3', '8', '1');
INSERT INTO `resource_category` VALUES ('33', '树下覆盖沙打旺\r\n树下覆盖沙打旺', '3', '9', '1');
INSERT INTO `resource_category` VALUES ('34', '树下覆盖白三叶', '3', '9', '1');
INSERT INTO `resource_category` VALUES ('35', '树下覆盖黑麦草', '3', '9', '1');
INSERT INTO `resource_category` VALUES ('36', '间作沙打旺', '3', '10', '1');
INSERT INTO `resource_category` VALUES ('37', '间作白三叶', '3', '10', '1');
INSERT INTO `resource_category` VALUES ('38', '间作黑麦草', '3', '10', '1');
INSERT INTO `resource_category` VALUES ('39', '奶牛', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('40', '肉牛', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('41', '肉羊', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('42', '奶羊', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('43', '普通肉猪', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('44', '黑山猪', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('45', '肉驴', '2', '6', '1');
INSERT INTO `resource_category` VALUES ('46', '耕地畜粪承载', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('47', '土地安全氮载(公斤/亩)', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('48', '土地安全磷载(公斤/亩)', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('49', '如种植禾科作物可带走氮\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('50', '如种植豆科作物可带走氮\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('51', '如种植禾科作物可带走磷\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('52', '如种植豆科作物可带走磷\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('53', '种植禾科作物耕地氮承载\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('54', '种植豆科作物耕地氮承载\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('55', '种植禾科作物耕地磷承载\r\n种植禾科作物耕地磷承载\r\n种植禾科作物耕地磷承载\r\n种植禾科作物耕地磷承载', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('56', '种植豆科作物耕地磷承载\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('57', '总氮需要量中无机氮限制比\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('58', '总磷需要量中无机磷限制比\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('59', '总钾需要量中无机钾限制比\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('60', '每亩耕地畜粪承载量\r\n', '2', '2', '1');
INSERT INTO `resource_category` VALUES ('61', '主耕地可用量(亩)\r\n', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('62', '每亩耕地粪载上限量(吨)', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('63', '每亩耕地氮载上限量(公斤)', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('64', '每亩耕地磷载上限量(公斤)', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('65', '用水量上限(吨)', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('66', '牧草间作比例', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('67', '当地种植活动用工 人/年薪水(万元/人.年)', '2', '3', '1');
INSERT INTO `resource_category` VALUES ('68', '当地养殖活动用工 人/年薪水(万元/人.年)', '2', '3', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `describle` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_auth`
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for `role_org`
-- ----------------------------
DROP TABLE IF EXISTS `role_org`;
CREATE TABLE `role_org` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_org
-- ----------------------------

-- ----------------------------
-- Table structure for `role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL,
  `role_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色ids',
  `url_pattern` varchar(200) DEFAULT NULL,
  `url_description` varchar(200) DEFAULT NULL,
  `is_menu` int(11) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_num` int(11) NOT NULL COMMENT '用户编号',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone_num` varchar(255) NOT NULL COMMENT '电话号码',
  `department` smallint(4) DEFAULT NULL COMMENT '部门',
  `gender` tinyint(4) NOT NULL COMMENT '性别',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `join_date` datetime DEFAULT NULL,
  `leave_date` datetime DEFAULT NULL,
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'zhangsan', '123456', '15650735910', '1', '1', '1', '2018-07-12 16:44:02', null, '1', null, null, null, '11596223@qq.com');
INSERT INTO `user` VALUES ('2', '4', 'lilei', '3c246e781a1e7ed3d6bd43d47226b11d', '15657984565', '1', '1', '1', '2018-07-27 18:00:42', '2018-07-27 18:00:42', '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-27 18:05:32', '9f5da5c7-6703-11e7-809d-9801a79f70e5', '2018-07-27 18:05:32', '1159622@1qq.com');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `add_user` char(36) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `mod_user` char(36) DEFAULT NULL,
  `mod_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
