package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.OptionEntity;
import com.example.demo.entity.QuizBookEntity;
import com.example.demo.service.OptionService;
import com.example.demo.service.QuizBookService;

@Controller
@RequestMapping("/quizbook")
public class QuizBookPlayController {
	
	@Autowired
	private QuizBookService quizBookService;
	@Autowired
	private OptionService optionService;
	
	@PostMapping("/play")
	public String play(Model model) {
		Optional<QuizBookEntity> OptEntity = quizBookService.selectRamdomQuiz();
		if(!OptEntity.isPresent()) {
			model.addAttribute("msg","一問一答モードがうまく起動しませんでした。てへっ");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = OptEntity.get();
		model.addAttribute("title","一問一答モード");
		model.addAttribute("entity",entity);
		return "javaSilver/play/play";
	}
	
	@PostMapping("/check-quiz")
	public String checkQuiz(@RequestParam Integer id,@RequestParam Integer optionId,Model model) {
		Optional<QuizBookEntity> optEntity = quizBookService.selectOneById(id);
		Optional<OptionEntity> optOptionEntity = optionService.selectOneById(optionId);
		if( (!optOptionEntity.isPresent()) || (!optEntity.isPresent()) ) {
			model.addAttribute("msg","選択肢の正誤判定にエラーが生じました。");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = optEntity.get();
		OptionEntity mySelectOption = optOptionEntity.get();
		if(!mySelectOption.isCorrect()) {
			model.addAttribute("answer","不正解です！！");
		}else {
			model.addAttribute("answer","正解です！！");
		}
		model.addAttribute("entity",entity);
		model.addAttribute("mySelectOption",mySelectOption);
		return "javaSilver/play/answer-page";
	}

}
