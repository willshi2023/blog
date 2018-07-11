package cn.virtualspider.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.virtualspider.blog.dao.UserDao;
import cn.virtualspider.blog.entity.User;
import cn.virtualspider.blog.util.Md5Util;
import cn.virtualspider.blog.util.StringUtil;

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist() {
		return "regist";
	}

	/**
	 * 注册 用户名，密码，确认密码不准为空 用户名不能重名 密码确认密码必须保持一致
	 * 
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registP(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("password2") String password2, Model model,HttpSession session) {
		// 用户名，密码，确认密码不准为空
		boolean uf = StringUtil.CheckEmpty(username);
		boolean pf = StringUtil.CheckEmpty(password);
		boolean p2f = StringUtil.CheckEmpty(password2);
		if (uf || pf || p2f) {
			model.addAttribute("msg", "请将注册信息填写完整！");
			return "regist";
		}
		// 用户名不能重名
		User user = userDao.findUserByUserName(username);
		if (user != null) {
			model.addAttribute("msg", "对不起，该用户已被注册！");
			return "regist";
		}
		// 密码确认密码必须保持一致
		if (!password.equals(password2)) {
			model.addAttribute("msg", "密码和确认密码必须保持一致");
			return "regist";
		}
		User user2 = new User();
		user2.setUsername(username);
		user2.setPassword(Md5Util.codePassword(password));
		userDao.InsertUser(user2);
		session.setAttribute("user", user2);
		return "index";
	}

	/**
	 * 登录
	 * 		用户名，密码不能为空
	 * 		用户名密码都要正确
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginP(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model,HttpSession session) {
		// 用户名，密码不能为空
		boolean uf = StringUtil.CheckEmpty(username);
		boolean pf = StringUtil.CheckEmpty(password);
		if (uf || pf) {
			model.addAttribute("msg", "请将注册信息填写完整！");
			return "login";
		}
		// 用户名不能重名
		User user = userDao.findUserByUserNameAndPassword(username, Md5Util.codePassword(password));
		if(user==null) {
			User user1 = userDao.findUserByUserName(username);
			if (user1 == null) {
				model.addAttribute("msg", "对不起，该用户不存在！");
				return "login";
			}else {
				model.addAttribute("msg", "对不起，密码错误！");
				return "login";
			}
		}
		session.setAttribute("user", user);
		return "index";
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
