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
  `status` varchar(20) NOT NULL DEFAULT '可用' COMMENT '文章状态，可用和不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`summary`,`show_pictrue`,`create_time`,`read_count`,`status`) values (1,'测试20180831','<p># 简介&nbsp;&nbsp;</p><p>这个个人博客是我利用业余时间','','2018-08-31 23:10:01',1,'不可用'),(2,'测试20180831_1','<p>这个是今天晚上的第二个测试，用来测试文章编辑的</p>','','2018-08-31 23:12:05',29,'可用'),(3,'测试20180831_2','<p>测试文章回显11111</p><p><br/></p>','','2018-08-31 23:34:05',22,'可用');

/*Table structure for table `article_dtl` */

DROP TABLE IF EXISTS `article_dtl`;

CREATE TABLE `article_dtl` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '文章详情id',
  `content` text COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `article_dtl` */

insert  into `article_dtl`(`id`,`content`) values (1,'<p># 简介&nbsp;&nbsp;</p><p>这个个人博客是我利用业余时间写出来的一个全栈项目，是第一个项目级别的作品，还在开发中，不足之处请多指教&nbsp;&nbsp;</p><p>### 说明&nbsp;&nbsp;</p><p>为了节省开发时间，部分前端内容来之网上的网页模板&nbsp;&nbsp;</p><p>项目持续维护中，暂时有很多不妥的地方，比如密码加密，用户权限控制等等，后面会持续改进&nbsp;&nbsp;</p><p>### 项目用到的框架，技术等&nbsp;&nbsp;</p><p>工具：eclipse,hbuilder,sqlyog,gitdesktop&nbsp;&nbsp;</p><p>后端框架和技术：java,springboot,mybatis,thymeleaf&nbsp;&nbsp;</p><p>前端框架和技术：css,js,jquery&nbsp;&nbsp;</p><p>其他：mysql&nbsp;&nbsp;</p><p># 预览&nbsp;&nbsp;</p><p>首页（未登陆）&nbsp;&nbsp;</p><p>![首页1](other/image/首页1.png)&nbsp;&nbsp;</p><p>首页（已登陆）&nbsp;&nbsp;</p><p>![首页2](other/image/首页2.png)&nbsp;&nbsp;</p><p>文章页&nbsp;&nbsp;</p><p>![文章页](other/image/文章页.png)&nbsp;&nbsp;</p><p>登陆页&nbsp;&nbsp;</p><p>![登录页](other/image/登录页.png)&nbsp;&nbsp;</p><p>注册页&nbsp;&nbsp;</p><p>![注册页](other/image/注册页.png)&nbsp;&nbsp;</p><p># 接口&nbsp;&nbsp;</p><p>### 文章模块&nbsp;&nbsp;</p><p>获取文章信息列表{&quot;post&quot;,&quot;/article/getArticles&quot;,&quot;&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>跳转到文章详情页面{&quot;get&quot;,&quot;/article/detail/${articleId}&quot;,&quot;&quot;,&quot;/article/detail&quot;}&nbsp;&nbsp;</p><p>获取文章详情{&quot;post&quot;,&quot;/article/detail/${articleId}&quot;,&quot;&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>跳转到写文章页面{&quot;get&quot;,&quot;/article/write&quot;,&quot;&quot;,&quot;/article/write&quot;}&nbsp;&nbsp;</p><p>保存写好的文章{&quot;post&quot;,&quot;/article/write&quot;,&quot;$content,$title,$showPictrue&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>删除文章{&quot;post&quot;,&quot;/article/delete&quot;,&quot;$articleId&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>### 用户模块&nbsp;&nbsp;</p><p>获取用户介绍{&quot;post&quot;,&quot;/user/getUserIntroduce&quot;,&quot;&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>跳转到登陆页面{&quot;get&quot;,&quot;/user/login&quot;,&quot;&quot;,&quot;/user/login&quot;}&nbsp;&nbsp;</p><p>跳转到注册页面{&quot;get&quot;,&quot;/user/regist&quot;,&quot;&quot;,&quot;/user/regist&quot;}&nbsp;&nbsp;</p><p>执行登陆验证{&quot;post&quot;,&quot;/user/login&quot;,&quot;$username,$password&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>执行注册验证{&quot;post&quot;,&quot;/user/regist&quot;,&quot;$username,$password,$rePassword&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>获取当前用户{&quot;post&quot;,&quot;/user/getUser&quot;,&quot;&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p>注销当前登录{&quot;post&quot;,&quot;/user/logout&quot;,&quot;&quot;,&quot;$Result&quot;}&nbsp;&nbsp;</p><p># 数据库字段&nbsp;&nbsp;</p><p>article_dtl文章详情表&nbsp;&nbsp;</p><p>![article_dtl](other/image/article_dtl.png)&nbsp;&nbsp;</p><p>article文章表&nbsp;&nbsp;</p><p>![article](other/image/article.png)&nbsp;&nbsp;</p><p>tag_article标签文章中间表&nbsp;&nbsp;</p><p>![tag_article](other/image/tag_article.png)&nbsp;&nbsp;</p><p>tag标签表&nbsp;&nbsp;</p><p>![tag](other/image/tag.png)&nbsp;&nbsp;</p><p>user_article用户文章表&nbsp;&nbsp;</p><p>![user_article](other/image/user_article.png)&nbsp;&nbsp;</p><p>user_dtl用户详情表&nbsp;&nbsp;</p><p>![user_dtl](other/image/user_dtl.png)&nbsp;&nbsp;</p><p>user用户表&nbsp;&nbsp;</p><p>![user](other/image/user.png)&nbsp;&nbsp;</p><p># 细节&nbsp;&nbsp;</p><p>### 代码和数据库命名转换&nbsp;&nbsp;</p><p>开启驼峰命名转换，将java中的驼峰命名和数据库中的xx_xx字段自动进行转换&nbsp;&nbsp;</p><p>mybatis.configuration.map-underscore-to-camel-case: true&nbsp;&nbsp;</p><p>### 前台页面显示时间为时间戳的解决办法&nbsp;&nbsp;</p><p>后台查出的时间默在前台用时间戳显示，通过前台调用js方法，将时间戳改变成YYYY-MM-dd HH:mm:ss的显示方式&nbsp;&nbsp;</p><p>![前台转换时间戳为正常显示的时间](other/image/前台转换时间戳为正常显示的时间.png)&nbsp;&nbsp;</p><p>### 获取插入时的自增主键&nbsp;&nbsp;</p><p>在插入时获取自增主键的id&nbsp;&nbsp;</p><p>在dao的方法上加上一句这样的注解<span style=\"white-space:pre\">	</span>@Options(useGeneratedKeys=true)//添加该行，product中的id将被自动添加&nbsp;&nbsp;</p><p>然后程序就会在运行的时候获取到主键，主键是封装在实体类内容的，可以直接获取&nbsp;&nbsp;</p><p>另一方面直接返回的是修改的栏目条数，并不是我们需要的主键&nbsp; &nbsp;&nbsp;</p><p>![获取自增主键的id1](other/image/获取自增主键的id1.png)&nbsp;&nbsp;</p><p>![获取自增主键的id2](other/image/获取自增主键的id2.png)&nbsp;&nbsp;</p><p># 版本和需求&nbsp;&nbsp;</p><p>##### v1.0.20180829.001&nbsp;&nbsp;</p><p>用户写完文章后，直接跳转到首页&nbsp;&nbsp;</p><p>##### v1.0.20180831.001&nbsp;&nbsp;</p><p>进入文章后，可以让用户重新编辑文章和删除文章&nbsp;&nbsp;</p><p><br/></p>'),(2,'<p>这个是今天晚上的第二个测试，用来测试文章编辑的</p>'),(3,'<p>测试文章回显11111</p><p><br/></p>');

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(20) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

/*Table structure for table `tag_article` */

DROP TABLE IF EXISTS `tag_article`;

CREATE TABLE `tag_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL COMMENT '标签id',
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tag_article` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_article` */

insert  into `user_article`(`id`,`user_id`,`article_id`) values (1,1,1),(2,1,2),(3,1,3);

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
