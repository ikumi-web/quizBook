package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quizbook")
public class QuizBookController {
	@GetMapping("/entry")
	public String entry(Model model) {
		model.addAttribute("title","問題集");
		return "entry";
	}
}
