package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.OptionEntity;
import com.example.demo.entity.QuizBookEntity;
import com.example.demo.form.OptionForm;
import com.example.demo.form.QuizBookForm;
import com.example.demo.service.OptionService;
import com.example.demo.service.QuizBookService;

@Controller
@RequestMapping("/quizbook")
public class QuizBookController {

	@Autowired
	QuizBookService quizBookService;
	@Autowired
	OptionService optionService;

	@GetMapping("/entry")
	public String entry(Model model) {
		model.addAttribute("title", "問題集");
		return "login/entry";
	}

	@GetMapping("/java-silver")
	public String toSilver(Model model) {
		Iterable<QuizBookEntity> list = quizBookService.selectAllDescInsertTime();
		model.addAttribute("list", list);
		model.addAttribute("title", "JavaSilver問題集");
		return "javaSilver/java-silver";
	}

	@GetMapping("/quiz-register")
	public String toRegister(QuizBookForm quizBookForm, Model model) {
		quizBookForm.setNewQuiz(true);
		model.addAttribute("quizBookForm", quizBookForm);
		model.addAttribute("title", "登録用フォーム");
		return "javaSilver/quiz-register";
	}

	@PostMapping("/back")
	public String backButton() {
		return "login/entry";

	}

	@PostMapping("/insert")
	public String insert(@Validated QuizBookForm form, BindingResult bindingResult, Model model) {
		QuizBookEntity entity = new QuizBookEntity();
		entity.setQuestion(form.getQuestion());
		entity.setCode(form.getCode());
		entity.setExplain(form.getExplain());
		entity.setInsertTime(new Timestamp(System.currentTimeMillis()));
		for (OptionForm optionForm : form.getOptions()) {
			OptionEntity optionEntity = new OptionEntity();
			optionEntity.setOptionText(optionForm.getOptionText());
			optionEntity.setCorrect(optionForm.isCorrect());
			entity.addOption(optionEntity);
		}
		if (bindingResult.hasErrors()) {
			return toRegister(form, model);
		}
		quizBookService.insert(entity);
		model.addAttribute("msg", "登録完了しました");
		model.addAttribute("entity", entity);
		return "javaSilver/confirm-page";
	}
	//＠PathVariableでidを受け取るパターン
	//	@GetMapping("/update-page/{id}")
	//	public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
	//		Optional<QuizBookEntity> OptEntity = quizBookService.selectOneById(id);
	//		if (OptEntity.isPresent()) {
	//			QuizBookEntity entity = OptEntity.get();
	//			QuizBookForm form = makeForm(entity);
	//			form.setNewQuiz(false);
	//			model.addAttribute("quizBookForm", form);
	//			model.addAttribute("title", "更新用フォーム");
	//			return "quiz-register";
	//		}
	//		model.addAttribute("msg", "クイズのIDが存在しませんでした。");
	//		return "javaSilver/confirm-page";
	//
	//	}

	@PostMapping("/update-page")
	public String toUpdatePage(@RequestParam Integer id,@RequestParam(required = false) Integer listId, Model model) {
		Optional<QuizBookEntity> OptEntity = quizBookService.selectOneById(id);
		if (OptEntity.isPresent()) {
			QuizBookEntity entity = OptEntity.get();
			QuizBookForm form = makeForm(entity);
			form.setNewQuiz(false);
			model.addAttribute("listId", listId);
			model.addAttribute("quizBookForm", form);
			model.addAttribute("title", "更新用フォーム");
			return "javaSilver/quiz-register";
		}
		model.addAttribute("msg", "クイズのIDが存在しませんでした。");
		return "javaSilver/confirm-page";

	}
	//Validation用の更新ページ作成メソッド
	public String toUpdatePageRedirect(QuizBookForm form, @RequestParam Integer id,
			@RequestParam(required = false) Integer listId, Model model) {
			form.setNewQuiz(false);
			model.addAttribute("listId", listId);
			model.addAttribute("quizBookForm", form);
			model.addAttribute("title", "更新用フォーム");
			return "javaSilver/quiz-register";
	}

	@PostMapping("/update")
	public String update(@Validated QuizBookForm form, BindingResult bindingResult,
			@RequestParam(required = false) Integer listId, Model model) {
		QuizBookEntity entity = makeEntity(form);
		entity.setInsertTime(new Timestamp(System.currentTimeMillis()));
		if (bindingResult.hasErrors()) {
			return toUpdatePageRedirect(form, form.getId(), listId, model);
		}
		quizBookService.update(entity);
		model.addAttribute("listId", listId);
		model.addAttribute("entity", entity);
		model.addAttribute("msg", "更新完了しました。");
		return "javaSilver/confirm-page";

	}

	@PostMapping("/confirm-page")
	public String confirm(@RequestParam Integer id, @RequestParam(required = false) Integer listId, Model model) {
		Optional<QuizBookEntity> OptEntity = quizBookService.selectOneById(id);
		if (!OptEntity.isPresent()) {
			return "forward:/quizbook/java-silver";
		}
		QuizBookEntity entity = OptEntity.get();
		model.addAttribute("entity", entity);
		model.addAttribute("listId", listId);
		return "javaSilver/confirm-page";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		quizBookService.deleteQuizById(id);
		return "forward:/quizbook/java-silver";

	}

	//▼▼▼▼▼▼▼▼EntityからFormへの変換▼▼▼▼▼▼▼▼
	public QuizBookForm makeForm(QuizBookEntity entity) {
		QuizBookForm form = new QuizBookForm();
		form.setId(entity.getId());
		form.setQuestion(entity.getQuestion());
		form.setCode(entity.getCode());
		form.setExplain(entity.getExplain());
		for (OptionEntity optionEntity : entity.getOptions()) {
			OptionForm optionForm = new OptionForm();
			optionForm.setId(optionEntity.getId());
			optionForm.setOptionText(optionEntity.getOptionText());
			optionForm.setCorrect(optionEntity.isCorrect());
			form.addOption(optionForm);
		}
		return form;

	}

	//▼▼▼▼▼▼▼▼FormからEntityへの変換▼▼▼▼▼▼▼▼
	public QuizBookEntity makeEntity(QuizBookForm form) {
		QuizBookEntity entity = new QuizBookEntity();
		entity.setId(form.getId());
		entity.setQuestion(form.getQuestion());
		entity.setCode(form.getCode());
		entity.setExplain(form.getExplain());
		for (OptionForm optionForm : form.getOptions()) {
			OptionEntity optionEntity = new OptionEntity();
			optionEntity.setId(optionForm.getId());
			optionEntity.setOptionText(optionForm.getOptionText());
			optionEntity.setCorrect(optionForm.isCorrect());
			entity.addOption(optionEntity);
		}
		return entity;

	}

}
