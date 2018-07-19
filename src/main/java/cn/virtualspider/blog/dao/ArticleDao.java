package cn.virtualspider.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.virtualspider.blog.entity.Article;
import cn.virtualspider.blog.entity.ArticleDetail;

/**
 * 文章相关的数据库接口
 * @author Administrator
 *
 */
public interface ArticleDao {
	/**
	 * 获取文章列表
	 * @return
	 */
	@Select("select * from article")
	List<Article> getArticles();

	/**
	 * 获取文章详情
	 * 		首先通过文章表的id 查出文章的id,标题，摘要，创建时间，阅读数
	 * 		然后通过用户文章关联表的文章id 查询出用户的id
	 * 		然后通过文章详情表的文章id 查询出文章内容
	 * 		然后通过用户表的id（前面已经获取了） 查询出作者
	 * @param articleId 
	 * @return
	 */
	@Select("SELECT a.`id`,a.title,a.`summary`,a.`create_time`,a.`read_count`, \r\n" + 
			"u_a.`user_id`,a_d.`content`,u.`username` \r\n" + 
			"FROM article a \r\n" + 
			"LEFT JOIN user_article u_a ON a.`id`=u_a.`article_id` \r\n" + 
			"LEFT JOIN article_dtl a_d ON a.`id`=a_d.`id` \r\n" + 
			"LEFT JOIN USER u ON u.`id`=u_a.`user_id` \r\n" + 
			"WHERE a.`id`=#{articleId}")
	ArticleDetail getArticleDetailByArticleId(Long articleId);

	/**
	 * 通过文章id查找标签列表
	 * @param articleId
	 * @return
	 */
	@Select("SELECT t.name FROM tag_article ta\r\n" + 
			"LEFT JOIN tag t ON t.`id`=ta.`tag_id`\r\n" + 
			"WHERE ta.`article_id`=#{articleId}")
	List<String> getTagsByArticleId(Long articleId);

	/**
	 * 将文章的阅读数加1
	 * @param articleId
	 */
	@Update("update article set read_count=read_count+1 where id=#{articleId}")
	void addArticleReadCount(Long articleId);

}
