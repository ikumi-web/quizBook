package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.QuizBookEntity;
import com.example.demo.service.QuizBookService;

@Controller
@RequestMapping("/quizbook")
public class QuizBookPlayController {
	
	@Autowired
	private QuizBookService quizBookService;
	
	@GetMapping("/play")
	public String play(Model model) {
		Optional<QuizBookEntity> OptEntity = quizBookService.selectRamdomQuiz();
		if(!OptEntity.isPresent()) {
			model.addAttribute("msg","一問一答モードがうまく起動しませんでした。てへっ");
			return "java-silver";
		}
		QuizBookEntity entity = OptEntity.get();
		model.addAttribute("title","一問一答モード");
		model.addAttribute("entity",entity);
		return "play";
	}

}
