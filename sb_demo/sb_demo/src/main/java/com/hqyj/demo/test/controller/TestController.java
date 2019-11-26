package com.hqyj.demo.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@ResponseBody
	@RequestMapping("/test")
	public String  welcomPage() {
		return "welcome to this page,and you will see this word:sb";
		
	}
}
