package cn.virtualspider.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

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
	@Insert("insert into article_dtl(id,content) values(#{id},#{content})")
	Long saveArticleDetail(ArticleDetail articleDetail);

	@Update("update article_dtl set content=#{content} where id=#{id}")
	Long update(ArticleDetail articleDetail);

}
