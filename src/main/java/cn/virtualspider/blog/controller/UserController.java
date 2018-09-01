package cn.virtualspider.blog.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.virtualspider.blog.dao.UserDao;
import cn.virtualspider.blog.dao.UserDetailDao;
import cn.virtualspider.blog.entity.Result;
import cn.virtualspider.blog.entity.User;
import cn.virtualspider.blog.entity.UserDetail;
import cn.virtualspider.blog.myenum.PromptMessage;

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
	
	@Autowired
	private UserDao userDao;
	
//	获取用户介绍{"post","/user/getUserIntroduce","","$Result"}
	@RequestMapping(value="/getUserIntroduce",method=RequestMethod.POST)
	@ResponseBody
	public Result getUserIntroduce(HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			Long userId = user.getId();
			UserDetail userDetail = userDetailDao.getUserIntroduceByUserId(userId);
			Result result = new Result(PromptMessage.GET_USER_DETAIL_SUCCESS);
			result.setData(userDetail);
			return result;
		} catch (Exception e) {
			Result result = new Result(PromptMessage.GET_USER_DETAIL_FAILURE);
			return result;
		}
	}
	
//	跳转登陆页面{"get","/user/login,"","/user/login"}  
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
//	跳转注册页面{"get","/user/regist,"","/user/regist"}  
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public String regist() {
		return "/user/regist";
	}
	
	/**
	 * 根据用户名和密码去数据库里面查找用户信息
	 * 		如果查找到用户，就放入session中，并返回成功信息
	 * 		如果没有查找到用户，就返回失败信息
	 * @param request
	 * @param session
	 * @return
	 */
//	执行登陆验证{"post","/user/login","$username,$password","$Result"}  
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result loginp(HttpServletRequest request,HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findUserByUserNameAndPassword(username, password);
		Result result;
		if(user!=null) {
			session.setAttribute("user", user);
			result = new Result(PromptMessage.LOGIN_USER_SUCCESS);
			return result;
		}else {
			result = new Result(PromptMessage.LOGIN_USER_FAILURE);
			return result;
		}
	}
	
	/**
	 * 执行注册验证
	 * 		首先验证密码和重复密码是否一致
	 * 		其次去数据库中查找是否有这个用户名
	 * 			有的话返回失败信息
	 * 			没有就保存用户信息，返回成功信息
	 * @param request
	 * @param session
	 * @return
	 */
//	执行注册验证{"post","/user/regist","$username,$password,$rePassword","$Result"}  
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	@ResponseBody
	public Result registp(HttpServletRequest request,HttpSession session) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		Result result;
		if(password.equals(rePassword)) {
			User user = userDao.findUserByUserName(username);
			if(user!=null) {
				result = new Result(PromptMessage.REGIST_USER_FAILURE_USER_ALREADY_EXIST);
			}else {
				Date date = new Date();
				user = new User();
				user.setLastLoginTime(date);
				user.setPassword(password);
				user.setRegistTime(date);
				user.setUsername(username);
				userDao.InsertUser(user);
				session.setAttribute("user", user);
				result = new Result(PromptMessage.REGIST_USER_SUCCESS);
			}
		}else {
			result = new Result(PromptMessage.REGIST_USER_FAILURE_PASSWORD_NOT_EQUAL);
		}
		return result;
	}
	
	/**
	 * 从session中获取当前用户
	 * @param session
	 * @return
	 */
//	获取当前用户{"post","/user/getUser","","$Result"} 
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	@ResponseBody
	public Result getUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Result result;
		if(user==null) {
			result = new Result(PromptMessage.SESSION_DOSE_NOT_HAS_USER);
		}else {
			result=  new Result(PromptMessage.SESSION_HAS_USER);
			result.setData(user);
		}
		return result;
	}
	
	/**
	 * 注销掉session即可退出
	 * @param session
	 * @return
	 */
//	注销当前登录{"post","/user/logout","","$Result"}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	@ResponseBody
	public Result logout(HttpSession session) {
		session.invalidate();
		Result result = new Result(PromptMessage.LOGOUT_SUCCESS);
		return result;
	}
}
