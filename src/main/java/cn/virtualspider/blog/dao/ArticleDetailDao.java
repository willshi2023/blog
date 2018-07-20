package cn.virtualspider.blog.dao;

import org.apache.ibatis.annotations.Insert;

import cn.virtualspider.blog.entity.ArticleDetail;

/**
 * 文章详情类的dao
 * @author Administrator
 *
 */
public interface ArticleDetailDao {
	
	/**
	 * 保存文章详情到article_dtl表
	 * @param articleDetail
	 */
	@Insert("insert into article_dtl(article_id,content) values(#{articleId},#{content})")
	void saveArticleDetail(ArticleDetail articleDetail);

}
