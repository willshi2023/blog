package cn.virtualspider.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.ArticleDao;
import cn.virtualspider.blog.entity.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleDao articleDao;
	
	/**
	 * 保存文章
	 * @param article
	 * @return
	 */
	@RequestMapping("/saveArticle")
	public String saveArticle(@RequestBody Article article){
		articleDao.saveOne(article);
		return "redirect:articles";
	}
	
	/**
	 * 显示所有文章
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/articles")
	public List<Article> articles(){
		return articleDao.findAll();
	}
}
