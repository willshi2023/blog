package cn.virtualspider.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.BlogDao;
import cn.virtualspider.blog.entity.Blog;

/**
 * 写博客相关的类
 * @author virtualspider
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogDao	blogDao;
	
	@RequestMapping(value="/writeBlog",method=RequestMethod.GET)
	public String writeBlog() {
		return "blog/writeBlog";
	}
	
	/**
	 * 向数据库中写入博客
	 * @param title
	 * @param content
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/writeBlog",method=RequestMethod.POST)
	public String writeBlogP(@RequestParam("title")String title,@RequestParam("content")String content,
			@RequestParam("username")String username) {
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setContent(content);
		String summary=content.length()>40?content.substring(0, 40):content;//设置文章摘要，长度不超过40
		blog.setSummary(summary);
		blog.setUsername(username);
		blogDao.insertBlog(blog);
		return "redirect:/";
	}
	
	/**
	 * 显示博客列表
	 * @return
	 */
	@RequestMapping(value="/showBlogList",method=RequestMethod.GET)
	@ResponseBody
	public List<Blog> showBlogList() {
		List<Blog> list = blogDao.showBlogList();
		return list;
	}
	
	
}
