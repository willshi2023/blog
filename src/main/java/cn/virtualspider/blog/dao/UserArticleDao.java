package cn.virtualspider.blog.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * 用户文章中间表的dao
 * @author Administrator
 *
 */
public interface UserArticleDao {

	/**
	 * 将用户的id和文章的id插入到user_article表中
	 * @param userId
	 * @param articleId
	 */
	@Insert("insert into user_article(user_id,article_id) values (#{param1},#{param2})")
	void save(Long userId, Long articleId);
	
}
