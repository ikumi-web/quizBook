package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.QuizBookEntity;
import com.example.demo.service.QuizBookService;

@Controller
@RequestMapping("/quizbook")
public class QuizBookController {
	
	@Autowired
	QuizBookService quizBookService;
	
	@GetMapping("/entry")
	public String entry(Model model) {
		model.addAttribute("title","問題集");
		return "entry";
	}
	@GetMapping("/java-silver")
	public String toSilver(Model model) {
		Iterable<QuizBookEntity> list = quizBookService.selectAll();
		model.addAttribute("list",list);
		model.addAttribute("title","JavaSilver問題集");
		return "java-silver";
	}
}
