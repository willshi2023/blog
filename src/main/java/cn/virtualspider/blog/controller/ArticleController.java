package cn.virtualspider.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.ArticleDao;
import cn.virtualspider.blog.entity.Article;
import cn.virtualspider.blog.entity.ArticleDetail;
import cn.virtualspider.blog.entity.Result;
import cn.virtualspider.blog.myenum.PromptMessageEnum;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleDao articleDao;
	
	@RequestMapping(value="/getArticles",method=RequestMethod.POST)
	@ResponseBody
	public Result getArticles() {
		List<Article> list = articleDao.getArticles();
		Result result = new Result(PromptMessageEnum.GET_ARTICLE_LIST_SUCCESS);
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
		Result result = new Result(PromptMessageEnum.GET_ARTICLE_DETAIL_SUCCESS);
		
		List<String> tags = articleDao.getTagsByArticleId(articleId);
		articleDetail.setTags(tags);
		result.setData(articleDetail);
		return result;
	}
}
