/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : news

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-12 23:20:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `news_id` int(11) NOT NULL COMMENT '新闻id',
  `comment_time` varchar(50) NOT NULL COMMENT '评论时间',
  `responder` varchar(50) NOT NULL COMMENT '评论者',
  `reviewers` varchar(50) NOT NULL COMMENT '被评论者',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `isRead` int(11) NOT NULL DEFAULT '0' COMMENT '消息是否已读',
  `likeNumber` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`comment_id`),
  KEY `user1` (`responder`),
  KEY `user2` (`reviewers`),
  KEY `news_id1` (`news_id`),
  CONSTRAINT `news_id1` FOREIGN KEY (`news_id`) REFERENCES `news` (`news_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user1` FOREIGN KEY (`responder`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user2` FOREIGN KEY (`reviewers`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '3', '2018-11-15 17:24:45', '李四', '作者3', '朝鲜人真是傻，如果在中国肯定是5A级景区。', '0', '0');
INSERT INTO `comment` VALUES ('2', '1', '2018-11-06 22:16:45', '张三', '王五', '世人该看到的是，中国人民对域外国度的主权的尊重，尤其是域外的国土面积不大的国家，中国人民更是互利合作，不以大国自矜，不以大欺小，信守公道，坚持正常交往，更不会搞拉帮结派，不会为拉帮结派谋取不当得利类行为掣肘，跟域外国度的交往只看其国度当政者的善交意愿和施策和其人民的善交往来，对有些言辞确不屑一顾，', '0', '0');
INSERT INTO `comment` VALUES ('3', '3', '2018-11-15 23:40:45', '张三', '王五', '拆了可惜！改天美国佬来了，再建造就迟了！千万不要相信美国佬说的话！远离美国佬才是正确的！上百年没有与美国佬建交你不是照样活着，跟美国佬建交了就活的累了！', '0', '0');
INSERT INTO `comment` VALUES ('4', '2', '2018-11-21 23:40:48', '李四', '李丽', '幸运这名女记者生活在高科技网络发展时代，如若不然，可能接不到后面的电话了，更发不了微博了！不得不承认，互联网大数据信息是个好东西！它以后将让违法者无处遁逃！', '0', '0');
INSERT INTO `comment` VALUES ('5', '2', '2018-11-21 23:00:45', '张三', '李丽', '记者辛苦了，谢谢你关心泉港碳九问题。希望能有关部门能将此事彻查到底。', '0', '0');
INSERT INTO `comment` VALUES ('6', '5', '2018-11-21 20:23:45', '张三', '作者3', '由心底感谢上帝给美帝送去了一个逗逼总统！特朗普将美国丑恶的一面展现在世界面前，美国以世界为敌终不得人心！', '0', '0');
INSERT INTO `comment` VALUES ('7', '5', '2018-11-21 21:23:45', '用户3', '作者3', '特朗普、普京，俩人名字里都带有个＂普＂字，他们很有可能是异性兄弟啊！', '0', '0');
INSERT INTO `comment` VALUES ('8', '5', '2018-11-21 21:24:45', '用户4', '作者3', '就“通俄门”一事，我坚信特朗普竞选团队没有和俄罗斯勾结干预大选，俄罗斯干预大选应另当别论。', '0', '0');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `news_title` varchar(100) NOT NULL,
  `news_content` text NOT NULL,
  `news_time` varchar(50) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`news_id`),
  KEY `type1` (`type`),
  KEY `username1` (`username`),
  CONSTRAINT `type1` FOREIGN KEY (`type`) REFERENCES `newstype` (`type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '王五', '特朗普欲禁非法入境移民获庇护 联邦法官说“不”', 'xinwen1.txt', '2018-10-31 17:23:45', 'http://localhost:8080/news/img/1.jpg', '军事');
INSERT INTO `news` VALUES ('2', '李丽', '央视网评女记者泉港采访被警察精准查房：既不合理更不合法', 'xinwen2.txt', '2018-11-06  17:23:45', 'http://localhost:8080/news/img/2.jpg', '军事');
INSERT INTO `news` VALUES ('3', '作者3', '全炸了！朝鲜对非军事区10所哨所完成爆破拆除', 'xinwen3.txt', '2018-11-14  17:23:45', 'http://localhost:8080/news/img/3.jpg', '军事');
INSERT INTO `news` VALUES ('4', '作者3', '不幸！周琦出战发展联盟再受伤 左脚踝扭伤提前退赛', 'xinwen4.txt', '2018-11-06  17:23:45', 'http://localhost:8080/news/img/4.jpg', '体育');
INSERT INTO `news` VALUES ('5', '作者3', '国际媒体头条速览：特朗普执意与沙特“在一起”', '<p><span class=\"ql-size-large\" style=\"color: rgb(0, 0, 0);\">特朗普不顾情报机构结论力挺沙特王储；英国媒体聚焦欧洲民粹主义盛行；韩国正式决定关闭“慰安妇”基金会。</span></p><p><br></p><p><span class=\"ql-size-large\" style=\"color: rgb(0, 0, 0);\">美国《纽约时报》</span></p><p><br></p><p><img src=\"http://localhost:8080/news/img/1000.jpg\"></p><p><br></p><p><span style=\"color: rgb(0, 0, 0);\"> </span><span style=\"color: rgb(0, 0, 0);\" class=\"ql-size-large\">对于沙特记者卡舒吉被杀事件，特朗普终于说出心里话了。《纽约时报》头条《尽管有卡舒吉杀害事件，特朗普与沙特站在一起》称，特朗普周二发布了一份声明，称“很有可能沙特王储本人知情——也许他知情，也许不知情!”，还说“我们可能永远不知道卡舒吉先生被杀的真相。但无论如何，我们是在与沙特这个王国构建关系。”总之，特朗普不顾自己国家情报机构的结论和大量证据，公开表示忠于沙特。这与特朗普向来的世界观一致，这就是：把什么都看成交易，毫不考虑盟友，美国利益优先。</span></p><p><span class=\"ql-size-large\" style=\"color: rgb(0, 0, 0);\"> </span><span class=\"ql-size-large\">另一条新闻说“特朗普欲下令司法部起诉科米和希拉里”。报道说，今年春天，特朗普告诉白宫律师，他想要下令司法部起诉自己的两个政治对手：2016年与他对决的总统参选人希拉里和被他开除的前联邦调查局局长科米。律师回应说，他无权下令起诉，如果真要那样做，他会被控滥用权力，有可能因此被选下台。</span></p><p><br></p><p><br></p><p><span class=\"ql-size-large\" style=\"color: rgb(0, 0, 0);\">英国《卫报》</span></p><p><br></p><p><img src=\"http://localhost:8080/news/img/1000 (1).jpg\"></p><p><span class=\"ql-size-large\">《卫报》头条聚焦欧洲民粹主义盛行。该报题为“四分之一欧洲人选民粹者”的独家调查报告称，过去20年来，欧洲的民粹政党支持率增加逾两倍，他们在11个国家将本党领袖送上公职，对欧洲既有的政治秩序构成挑战。从数据上看，至少从1998年起，民粹主义日益抬头。20年前，民粹政党基本上是边缘的力量，仅占到整个欧洲大陆7%的选票;而在最近几次欧洲全国性选举中，民粹政党收获多达四分之一的选票。</span></p><p><span class=\"ql-size-large\">其他报道有《脱欧派哗变失败，受鼓舞的首相前往布鲁塞尔》、《特朗普与沙特“站在一起”，为王储辩护》和《报道称，特朗普曾想起诉希拉里和科米》。</span></p><p><span class=\"ql-size-large\">英国《泰晤士报》</span></p><p><span class=\"ql-size-large\"><img src=\"https://inews.gtimg.com/newsapp_bt/0/6448379823/1000\"></span></p><p><span class=\"ql-size-large\">《泰晤士报》头条称英国要阻止俄罗斯人担任国际刑警组织主席。报道《“克格勃人”要成为国际刑警组织下任主席》称，俄罗斯内务部官员普罗科普丘克目前是国际刑警主席热门人选。有乌克兰议员指责他曾是克格勃间谍。英美使出浑身解数要阻止他当选。</span></p><p><span class=\"ql-size-large\">其他报道有“选民力挺首相，逼宫企图失败”和“特朗普说，哪怕王储知道卡舒吉被杀，我仍与沙特人站在一起”。</span></p><p><span class=\"ql-size-large\">澳大利亚《悉尼先驱晨报》</span></p><p><span class=\"ql-size-large\"><img src=\"https://inews.gtimg.com/newsapp_bt/0/6448394199/1000\"></span></p><p><span class=\"ql-size-large\">《悉尼先驱晨报》今日主要新闻报道为《北岸医院首席执行官辞职》、《沙尘暴笼罩新南威尔士洲内陆，明天或抵达悉尼》和《虐待、骚扰和强奸指控：澳大利亚援助组织道歉》。</span></p><p><span class=\"ql-size-large\">印度《印度斯坦时报》</span></p><p><span class=\"ql-size-large\"><img src=\"https://inews.gtimg.com/newsapp_bt/0/6448408281/1000\"></span></p><p><span class=\"ql-size-large\">《印度斯坦时报》头条与多年前印度国内一起骚乱有关。报道《1984年骚乱案法官说，大规模种族灭绝受害者不应被遗忘》称，德里一家法庭周二审理1984年反锡克教徒骚乱案，其中一人被判死刑，一人终身监禁。法官还说，那次“大规模种族灭绝”事件的受害者不应被遗忘，他们的指控应获得公正审理。34年前，印度总理英迪拉·甘地遭两名锡克教徒警卫刺杀，随后印度国内爆发了针对锡克人的骚乱。此次被判的两人被控当年杀害邻居。</span></p><p><span class=\"ql-size-large\">其他新闻还有“未能通过酒精检测的印度航空资深飞行员回来当常务董事”和“手持‘打碎婆罗门父权制’标语掀起网上风暴，推特首席执行官被迫道歉”。</span></p><p><span class=\"ql-size-large\">韩联社</span></p><p><span class=\"ql-size-large\"><img src=\"https://inews.gtimg.com/newsapp_bt/0/6448418810/1000\"></span></p><p><span class=\"ql-size-large\">韩联社今日主要有两条新闻。</span></p><p><span class=\"ql-size-large\">其一《蓬佩奥说，韩朝合作不应快于朝鲜去核》称，美国国务卿蓬佩奥说，美国想要确保朝鲜去核进展不要被韩朝改善关系超过。他的言论凸显美国担心最近韩朝关系升温过快，破坏对朝施压。</span></p><p><span class=\"ql-size-large\">其二《韩国正式决定关闭“慰安妇”基金会》称，韩国政府一名官员说，政府本周会宣布决定关闭一个有争议的慰安妇基金会。该基金会名为“和解与愈合基金会”，是日本出资的。关闭该基金会被认为首尔抛弃2015年韩日就“慰安妇”问题达成的协议。2015年末，当时的朴槿惠政府签署协议解决慰安妇历史问题，次年启动该基金会，目的是帮助受害者及其家人。日本向基金会捐资10亿日元(约合890万美元)。</span></p><p><span class=\"ql-size-large\"> </span></p><p><br></p><p><br></p><p><br></p>', '2018-11-21  17:23:45', 'http://localhost:8080/news/img/20181128095812798.jpg', '军事');
INSERT INTO `news` VALUES ('9', '王五', '新的新闻测试', '<h1>哈哈哈</h1><p>我是王五</p><p><u>这是我的的新闻我做主</u></p><p><img src=\"http://localhost:8080/news/img/20181127235250767.jpg\"></p><p><span class=\"ql-size-large\">啦啦啦啦啦绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿</span></p>', '2018-11-27 23:53:25', 'http://localhost:8080/news/img/20181127235205819.jpg', '生活');
INSERT INTO `news` VALUES ('10', '王五', '第二测试的；啊', '<h1><em>我是一个测试</em></h1><p>哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或群</p><p><img src=\"http://localhost:8080/news/img/20181127235706636.jpg\"></p>', '2018-11-27 23:57:09', 'http://localhost:8080/news/img/20181127235630011.jpg', '娱乐');
INSERT INTO `news` VALUES ('11', '王五', '测测测', '<p>测试啊</p><p>11111111111111111111111<img src=\"http://localhost:8080/news/img/20181127235941040.jpg\"></p>', '2018-11-27 23:59:43', 'http://localhost:8080/news/img/20181127235931534.jpg', '财经');
INSERT INTO `news` VALUES ('12', '王五', '测试真的', '<p>的点点滴滴多多多多多多多多多多的点点滴滴多多多多多多多多多多<img src=\"http://localhost:8080/news/img/20181128000106991.jpg\"></p>', '2018-11-28 00:01:09', 'http://localhost:8080/news/img/20181128000053403.jpg', '娱乐');
INSERT INTO `news` VALUES ('16', '王五', '测试的反反复复', '<ol><li class=\"ql-indent-1\"><strong><em><u>哈哈哈哈哈哈哈哈哈或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或或群</u></em></strong></li></ol><p><br></p><p><strong><em><u><span class=\"ql-cursor\">﻿</span></u></em></strong><img src=\"http://localhost:8080/news/img/20181128143629800.jpg\"></p>', '2018-11-28 14:36:31', 'http://localhost:8080/news/img/20181128143609823.jpg', '科技');
INSERT INTO `news` VALUES ('17', '作者3', '举例输入', '<h2>哈哈哈哈哈哈</h2><p><img src=\"http://localhost:8080/news/img/20181211173953285.jpg\"></p>', '2018-12-11 17:42:22', 'http://localhost:8080/news/img/20181211173928084.jpg', '科技');

-- ----------------------------
-- Table structure for newstype
-- ----------------------------
DROP TABLE IF EXISTS `newstype`;
CREATE TABLE `newstype` (
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newstype
-- ----------------------------
INSERT INTO `newstype` VALUES ('体育');
INSERT INTO `newstype` VALUES ('军事');
INSERT INTO `newstype` VALUES ('娱乐');
INSERT INTO `newstype` VALUES ('生活');
INSERT INTO `newstype` VALUES ('科技');
INSERT INTO `newstype` VALUES ('财经');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `comment_id` int(11) NOT NULL COMMENT '评论id',
  `reply_time` varchar(50) NOT NULL COMMENT '回复时间',
  `responder` varchar(50) NOT NULL COMMENT '评论者',
  `reviewers` varchar(50) NOT NULL COMMENT '被评论者',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `isRead` int(11) NOT NULL DEFAULT '0' COMMENT '消息是否已读',
  `likeNumber` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`reply_id`),
  KEY `user1` (`responder`),
  KEY `user2` (`reviewers`),
  KEY `news_id1` (`comment_id`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`responder`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_3` FOREIGN KEY (`reviewers`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '2018-11-06 22:19', '张三', '李四', '什么都能扯上中国，你也是可以的。', '0', '0');
INSERT INTO `reply` VALUES ('2', '1', '2018-11-06 22:20', '李四', '张三', '咋了嘛', '0', '0');
INSERT INTO `reply` VALUES ('3', '8', '2018-11-21 20:24:45', '李四', '用户4', '那俄毛总得有动机，没利益干预人家大选干嘛，钱多？', '0', '0');
INSERT INTO `reply` VALUES ('4', '8', '2018-11-21 21:23:45', '用户1', '用户4', '是的', '0', '0');
INSERT INTO `reply` VALUES ('5', '8', '2018-11-21 22:23:45', '用户2', '李四', '不管，与我们无关', '0', '0');
INSERT INTO `reply` VALUES ('6', '8', '2018-11-21 22:25:45', '张三', '李四', '没有内鬼接手的话，外人又怎么能插足进来？', '0', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `
user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `power` int(11) NOT NULL,
  `birthday` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `head_portrait_url` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`
user_id`),
  KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王五', 'e10adc3949ba59abbe56e057f20f883e', '男', '1', '2018-10-01', '12345678900@qq.com', 'http://localhost:8080/news/img/王五.jpg');
INSERT INTO `user` VALUES ('2', '李四', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-10-18', '12345678900@qq.com', 'http://localhost:8080/news/img/李四.gif');
INSERT INTO `user` VALUES ('3', '张三', 'e10adc3949ba59abbe56e057f20f883e', '女', '2', '2018-10-01', '12345678900@qq.com', 'http://localhost:8080/news/img/张三.jpg');
INSERT INTO `user` VALUES ('4', '李丽', 'e10adc3949ba59abbe56e057f20f883e', '女', '1', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/李丽.jpg');
INSERT INTO `user` VALUES ('5', '作者3', 'e10adc3949ba59abbe56e057f20f883e ', '男', '1', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/作者3.jpg');
INSERT INTO `user` VALUES ('6', '用户1', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('7', '用户2', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('8', '用户3', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('9', '用户4', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('10', '用户5', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('11', '用户6', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('12', '用户7', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
INSERT INTO `user` VALUES ('13', '用户8', 'e10adc3949ba59abbe56e057f20f883e', '男', '2', '2018-11-06', '1234567891@qq.com', 'http://localhost:8080/news/img/默认.jpg');
