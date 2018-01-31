package cn.virtualspider.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.virtualspider.blog.entity.Article;

@Mapper
public interface ArticleDao {
	@Insert("insert into article(title,content) values (#{title},#{content})")
	void saveOne(Article article);

	@Select("select * from article")
	List<Article> findAll();
	
}
