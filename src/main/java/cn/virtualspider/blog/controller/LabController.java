package cn.virtualspider.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lab")
public class LabController {
	@RequestMapping(value= {"","/"},method=RequestMethod.GET)
	public String lab() {
		return "lab/index";
	}
	/**
	 * 解忧杂货铺入口
	 * @return
	 */
	@RequestMapping(value="/warrygrocer",method=RequestMethod.GET)
	public String warrygrocer() {
		return "lab/warrygrocer";
	}
}
