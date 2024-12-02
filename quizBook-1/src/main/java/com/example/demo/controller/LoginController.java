package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login/loginForm";
	}
	@GetMapping(value = "/login",params = "failure")
	public String loginFailure(Model model) {
		model.addAttribute("failureMessage","ログインが失敗しました");
		return "login/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("logoutMessage","ログアウトしました");
		return "login/loginForm";
	}
	

}
