package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
			model.addAttribute("msg", "一問一答モードがうまく起動しませんでした。");
			return "javaSilver/java-silver";
		}
		QuizBookEntity entity = OptEntity.get();
		//選ばれた問題の正解の数を調べる
		Integer correctCount = 0;
		for (OptionEntity option : entity.getOptions()) {
			if (option.isCorrect()) {
				correctCount++;
			}
		}
		model.addAttribute("correctCount", correctCount);
		model.addAttribute("title", "JavaSilver一問一答");
		model.addAttribute("entity", entity);
		return "javaSilver/play/play";
	}

	@PostMapping(value = "/twenty-test")
	public String twentyTest(Model model) {
		model.addAttribute("title", "20問連続モード");
		return "javaSilver/twentyTest/twenty-test";
	}

	@PostMapping("/twenty-test-play")
	public String twentyTestPlay(Model model,@RequestParam String numberOfQuizOnes
				,@RequestParam String numberOfQuizTens
				,@RequestParam String numberOfQuizHunds
				,@RequestParam String testTimeOnes
				,@RequestParam String testTimeTens
				,@RequestParam String testTimeHuns) {
		Integer limit = 0;
		Integer limitTime = 0;
		//100の位10の位1の位を連結してint型にする。
		String quizCount = numberOfQuizHunds + numberOfQuizTens + numberOfQuizOnes;
		String timeCount = testTimeHuns +testTimeTens + testTimeOnes;
		limit = Integer.parseInt(quizCount);
		limitTime = Integer.parseInt(timeCount);
		Iterable<QuizBookEntity> list = quizBookService.selectByLimit(limit);
		model.addAttribute("list",list);
		model.addAttribute("limitTime",limitTime);
		model.addAttribute("limit",limit);
		model.addAttribute("title","JavaSilverテスト");
		return "javaSilver/twentyTest/twenty-test-play";
	}
	

	@GetMapping("/search-quiz")
	public String searchQuiz(@RequestParam String searchWord, Model model) {
		Iterable<QuizBookEntity> list = quizBookService.selectSearchAllDescInsertTime(searchWord);
		model.addAttribute("list", list);
		model.addAttribute("title", "JavaSilver問題集");
		return "javaSilver/java-silver";
	}

	@PostMapping("/check-quiz")
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
		model.addAttribute("mySelectOptionAll", mySelectOptionAll);
		//全ての選択肢を調べて正誤判定
		for (OptionEntity myOption : mySelectOptionAll) {
			if (myOption.isCorrect()) {
				model.addAttribute("answer", "正解です！！");
			} else {
				model.addAttribute("answer", "不正解です！！");
				break;
			}
		}
		return "javaSilver/play/answer-page";
	}

}
