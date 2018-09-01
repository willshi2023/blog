package cn.virtualspider.blog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.ArticleDao;
import cn.virtualspider.blog.dao.ArticleDetailDao;
import cn.virtualspider.blog.dao.UserArticleDao;
import cn.virtualspider.blog.entity.Article;
import cn.virtualspider.blog.entity.ArticleDetail;
import cn.virtualspider.blog.entity.Result;
import cn.virtualspider.blog.entity.User;
import cn.virtualspider.blog.myenum.PromptMessage;
import cn.virtualspider.blog.util.StringUtil;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private UserArticleDao userArticleDao;
	
	@Autowired
	private ArticleDetailDao articleDetailDao;
	
	@RequestMapping(value="/getArticles",method=RequestMethod.POST)
	@ResponseBody
	public Result getArticles() {
		List<Article> list = articleDao.getArticles();
		Result result = new Result(PromptMessage.GET_ARTICLE_LIST_SUCCESS);
		result.setData(list);
		return result;
	}

	//跳转到文章详情{"get","/article/detail/${articleId},"","/article/detail"}
	@RequestMapping(value="/detail/{articleId}",method=RequestMethod.GET)
	public String detail(@PathVariable Long articleId) {
		return "/article/detail";
	}
	
	/**
	 * 查询文章的详细信息
	 * 		先将基本的文章信息封装好
	 * 		首先通过文章表的id 查出文章的id,标题，摘要，创建时间，阅读数
	 * 		然后通过用户文章关联表的文章id 查询出用户的id
	 * 		然后通过文章详情表的文章id 查询出文章内容
	 * 		然后通过用户表的id（前面已经获取了） 查询出作者
	 * 
	 * 		上述查询完之后，因为文章和标签可能是多对多的关系，单独再查出tags然后放到对象中
	 * @param articleId
	 * @return
	 */
	//获取文章详情{"post","/article/detail/${articleId},"","$Result"}
	@RequestMapping(value="/detail/{articleId}",method=RequestMethod.POST)
	@ResponseBody
	public Result detailP(@PathVariable Long articleId) {
		articleDao.addArticleReadCount(articleId);//用户阅读时，修改数据库的阅读数加1
		
		ArticleDetail articleDetail = articleDao.getArticleDetailByArticleId(articleId);
		Result result = new Result(PromptMessage.GET_ARTICLE_DETAIL_SUCCESS);
		
//		List<String> tags = articleDao.getTagsByArticleId(articleId);
//		articleDetail.setTags(tags);
		result.setData(articleDetail);
		return result;
	}
	
//	跳转到写文章页面{"get","/article/write","","/article/write"}  
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		return "/article/write";
	}
	
	/**
	 * 保存写好的文章
	 * 		文章标题，摘要（从内容中提取一些40个字符串），展示图片，创建时间（新建一个date）保存在article表中
	 * 		文章id，文章内容保存在article_dtl表中
	 * 		用户文章中间表中插入文章和用户的id
	 * @param request
	 * @return
	 */
//	保存写好的文章{"post","/article/write","$content,$title,$showPictrue","$Result"}  
	@RequestMapping(value="/write",method=RequestMethod.POST)
	@ResponseBody
	public Result writeP(HttpServletRequest request,HttpSession session) {
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String showPictrue = request.getParameter("showPictrue");
		showPictrue = StringUtil.CheckEmpty(showPictrue)?"":showPictrue;
		String summary = content.length()>40?content.substring(0, 40):content;//摘要从内容中取40个文字
		Date date = new Date();
		
		//保存文章到article表
		Article article = new Article();
		article.setTitle(title);
		article.setSummary(summary);
		article.setShowPictrue(showPictrue);
		article.setCreateTime(date);
		//saveArticleItem是改变的条目数，并不是主键的id，主键的id是封装在article中的
		@SuppressWarnings("unused")
		Long saveArticleItem = articleDao.saveArticle(article);
		Long articleId = article.getId();
		
		//保存文章详情到article_dtl表
		ArticleDetail articleDetail = new ArticleDetail();
		articleDetail.setContent(content);
		articleDetail.setId(articleId);
		articleDetailDao.saveArticleDetail(articleDetail);
		
		//将用户的id和文章的id插入到user_article表中
		User user = (User) session.getAttribute("user");
		Long userId = user.getId();
		userArticleDao.save(userId,articleId);
		
		Result result = new Result(PromptMessage.SAVE_ARTICLE_SUCCESS);
		return result;
	}
	
	/*
	 * 将文章的状态改为：不可用
	 */
//	删除文章{"post","/article/delete","$articleId","$Result"}  
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(HttpServletRequest request) {
		String articleId = request.getParameter("articleId");
		int id = Integer.valueOf(articleId);
		articleDao.deleteArticleById(id);
		return new Result(PromptMessage.DELETE_ARTICLE_SUCCESS);
	}
	
	
//	跳转到修改文章页面{"get","/article/update/${articleId}","","/article/update"}  
	@RequestMapping(value="/update/{articleId}",method=RequestMethod.GET)
	public String update(@PathVariable Long articleId,Model model) {
		model.addAttribute("articleId", articleId);
		return "/article/update";
	}
	
//	修改文章{"post","/article/update","$articleId,$content,$title,$showPictrue","$Result"}  
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result updateP(HttpServletRequest request) {
		String sArticleId = request.getParameter("articleId");
		Long articleId = Long.valueOf(sArticleId);
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String showPictrue = request.getParameter("showPictrue");
		showPictrue = StringUtil.CheckEmpty(showPictrue)?"":showPictrue;
		
		//修改文章信息
		Article article = new Article();
		article.setId(articleId);
		article.setShowPictrue(showPictrue);
		String summary = content.length()>40?content.substring(0, 40):content;//摘要从内容中取40个文字
		article.setSummary(summary);
		article.setTitle(title);
		articleDao.update(article);
		
		//修改文章详情
		ArticleDetail articleDetail = new ArticleDetail();
		articleDetail.setContent(content);
		articleDetail.setId(articleId);
		articleDetailDao.update(articleDetail);
		
		return new Result(PromptMessage.UPDATE_ARTICLE_SUCCESS);
	}
}
