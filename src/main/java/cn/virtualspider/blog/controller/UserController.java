package cn.virtualspider.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.UserDetailDao;
import cn.virtualspider.blog.entity.Result;
import cn.virtualspider.blog.entity.User;
import cn.virtualspider.blog.entity.UserDetail;
import cn.virtualspider.blog.myenum.PromptMessageEnum;

/**
 * 用户相关的控制器
 * @author virtualspider
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDetailDao userDetailDao;
	
//	获取用户介绍{"post","/user/getUserIntroduce","","$Result"}
	@RequestMapping(value="/getUserIntroduce",method=RequestMethod.POST)
	@ResponseBody
	public Result getUserIntroduce(HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			Long userId = user.getId();
			UserDetail userDetail = userDetailDao.getUserIntroduceByUserId(userId);
			Result result = new Result(PromptMessageEnum.GET_USER_DETAIL_SUCCESS);
			result.setData(userDetail);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result result = new Result(PromptMessageEnum.GET_USER_DETAIL_FAILURE);
			return result;
		}
	}
}
