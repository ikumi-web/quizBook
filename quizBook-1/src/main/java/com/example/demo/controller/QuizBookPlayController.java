package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
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
		if (!OptEntity.isPresent()) {
			model.addAttribute("msg", "一問一答モードがうまく起動しませんでした。てへっ");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = OptEntity.get();
		model.addAttribute("title", "JavaSilver一問一答");
		model.addAttribute("entity", entity);
		return "javaSilver/play/play";
	}

	@PostMapping("/check-quiz")
	public String checkQuiz(@RequestParam Integer id, @RequestParam Integer optionId, Model model) {
		Optional<QuizBookEntity> optEntity = quizBookService.selectOneById(id);
		Optional<OptionEntity> optOptionEntity = optionService.selectOneById(optionId);
		if ((!optOptionEntity.isPresent()) || (!optEntity.isPresent())) {
			model.addAttribute("msg", "選択肢の正誤判定にエラーが生じました。");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = optEntity.get();
		OptionEntity mySelectOption = optOptionEntity.get();
		if (!mySelectOption.isCorrect()) {
			model.addAttribute("answer", "不正解です！！");
		} else {
			model.addAttribute("answer", "正解です！！");
		}
		model.addAttribute("entity", entity);
		model.addAttribute("mySelectOption", mySelectOption);
		return "javaSilver/play/answer-page";
	}

	@PostMapping("/check-some-quiz")
	public String checkSomeQuiz(@RequestParam Integer id, @RequestParam List<Integer> optionId, Model model) {
		List<OptionEntity> mySelectOptionAll = new ArrayList<>();
		Optional<QuizBookEntity> optEntity = quizBookService.selectOneById(id);
		if (!optEntity.isPresent()) {
			model.addAttribute("msg", "選択肢の正誤判定にエラーが生じました。");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = optEntity.get();
		model.addAttribute("entity", entity);
		//以下から多肢選択の処理
		for (Integer optionid : optionId) {
			Optional<OptionEntity> optOptionEntity = optionService.selectOneById(optionid);
			if (!optOptionEntity.isPresent()) {
				model.addAttribute("msg", "選択肢の正誤判定にエラーが生じました。");
				return "javaSilver/java-silver";
			}
			OptionEntity mySelectOption = optOptionEntity.get();
			mySelectOptionAll.add(mySelectOption);
		}
		model.addAttribute("mySelectOptionAll",mySelectOptionAll);
		//全ての選択肢を調べて正誤判定
		for(OptionEntity myOption : mySelectOptionAll) {
			if(myOption.isCorrect()) {
				model.addAttribute("answer", "正解です！！");
			}else {
				model.addAttribute("answer", "不正解です！！");
				break;
			}
		}
		return "javaSilver/play/answerSample";
	}

}
