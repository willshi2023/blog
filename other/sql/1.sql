/*
SQLyog Ultimate v11.22 (64 bit)
MySQL - 5.7.22-log : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `summary` varchar(100) DEFAULT NULL COMMENT '摘要',
  `show_pictrue` varchar(100) DEFAULT NULL COMMENT '展示图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `read_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '阅读数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`summary`,`show_pictrue`,`create_time`,`read_count`) values (1,'微服务架构的技术体系——上线啦','花了一些时间对微服务架构体系的理论以及实践进行了总结。一部分课程已经整理完毕上线了。 课程链接：微服务架构的技术体系详解 课程简介： 微服务架构的技术体系、社区目前已经越来越成熟。在最初系统架构的搭建','/blog/images/1.jpg','2018-07-18 21:13:06',17),(2,'JVM性能调优实践——G1 垃圾收集器分','前言 关于G1 GC以及其他垃圾收集器的介绍可以参考前一篇JVM性能调优实践——G1 垃圾收集器介绍篇。了解了G1垃圾收集器的运行机制之后，就可以针对一些GC相关参数来调整内存分配以及运行策略。下文的','/blog/images/1.jpg','2018-07-19 22:27:42',5);

/*Table structure for table `article_dtl` */

DROP TABLE IF EXISTS `article_dtl`;

CREATE TABLE `article_dtl` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '文章详情id',
  `content` text COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `article_dtl` */

insert  into `article_dtl`(`id`,`content`) values (1,'花了一些时间对微服务架构体系的理论以及实践进行了总结。一部分课程已经整理完毕上线了。\r\n\r\n课程链接：微服务架构的技术体系详解\r\n\r\n课程简介：\r\n\r\n微服务架构的技术体系、社区目前已经越来越成熟。在最初系统架构的搭建，或者当现有架构已到达瓶颈需要进行架构演进时，很多架构师、运维工程师会考虑是否需要搭建微服务架构体系。虽然很多文章都说微服务架构是复杂的、会带来很多分布式的问题，但只要我们了解这些问题，并找到解法，就会有种拨开云雾的感觉。\r\n\r\n微服务架构也不是完美的，世上没有完美的架构，微服务架构也是随着业务、团队成长而不断演进的。最开始可能就几个、十几个微服务，每个服务是分库的，通过 API Gateway 并行进行服务数据合并、转发。随着业务扩大、不断地加入搜索引擎、缓存技术、分布式消息队列、数据存储层的数据复制、分区、分表等。\r\n\r\n本课程会一一解开微服务架构下分布式场景的问题，以及通过对于一些分布式技术的原理、模型和算法的介绍，来帮助想要实施微服务架构的工程师们知其然并知其所以然。并且，本课程通过对分布式问题的体系化梳理，结合一些方案的对比选型，可以让工程师们一览微服务的知识图谱。\r\n\r\n注：为了方便初学者理解微服务实践，以及掌握怎样在微服务中使用 DDD（Domain-Driven Design）思想，在本课程第 05 课中讲解了 Demo 示例，该示例是基于 SpringBoot、SpringCloud-Eureka 技术写的。 \r\nGithub 上的源码地址：\r\n\r\nMicroservice\r\nGateway\r\n源码的demo工程中分别有几个预留给读者的问题，开发工程师们可以checkout下来。欢迎在课程的读者圈或者Github上一起探讨。\r\n\r\nGitchat 分享卡'),(2,'/**\r\n	 * 查询文章的详细信息\r\n	 * 		先将基本的文章信息封装好\r\n	 * 		首先通过文章表的id 查出文章的id,标题，摘要，创建时间，阅读数\r\n	 * 		然后通过用户文章关联表的文章id 查询出用户的id\r\n	 * 		然后通过文章详情表的文章id 查询出文章内容\r\n	 * 		然后通过用户表的id（前面已经获取了） 查询出作者\r\n	 * \r\n	 * 		上述查询完之后，因为文章和标签可能是多对多的关系，单独再查出tags然后放到对象中\r\n	 * @param articleId\r\n	 * @return\r\n	 */');

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(20) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`id`,`name`) values (1,'随笔'),(2,'微服务');

/*Table structure for table `tag_article` */

DROP TABLE IF EXISTS `tag_article`;

CREATE TABLE `tag_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL COMMENT '标签id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tag_article` */

insert  into `tag_article`(`id`,`tag_id`,`article_id`) values (1,1,1),(2,2,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(10) NOT NULL COMMENT '用户名称',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `regist_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` varchar(20) NOT NULL DEFAULT '可用' COMMENT '可用，禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`regist_time`,`last_login_time`,`status`) values (1,'张三','123456','2018-07-19 21:31:49','2018-07-19 21:31:51','可用'),(2,'李四','123','2018-07-19 21:38:52','2018-07-19 21:38:52','可用');

/*Table structure for table `user_article` */

DROP TABLE IF EXISTS `user_article`;

CREATE TABLE `user_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user_article` */

insert  into `user_article`(`id`,`user_id`,`article_id`) values (1,1,1),(2,1,2);

/*Table structure for table `user_dtl` */

DROP TABLE IF EXISTS `user_dtl`;

CREATE TABLE `user_dtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `introduce` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `image` varchar(255) DEFAULT NULL COMMENT '个人图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_dtl` */

insert  into `user_dtl`(`id`,`introduce`,`image`) values (1,'一个写代码的','blog/images/introduce.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
