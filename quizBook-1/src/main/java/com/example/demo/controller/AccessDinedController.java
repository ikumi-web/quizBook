package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDinedController {
	
	//getかpostかわからないためrequestMappingを使用
	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}

}
