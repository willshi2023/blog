package cn.virtualspider.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cn.virtualspider.blog.entity.Blog;

public interface BlogDao {
	@Insert("insert into blog(username,title,content,summary,time,read_count) values "
			+ "(#{username},#{title},#{content},#{summary},#{time},#{read_count})")
	void insertBlog(Blog blog);
	
	
	@Select("select * from blog")
	List<Blog> showBlogList();
}
