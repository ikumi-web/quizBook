package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;


@Data
public class QuizBookForm {
	
	private Integer id;
	private String question;
	private String code;
	private String explain;
	@Valid
	private List<OptionForm> options = new ArrayList<>();
	private boolean newQuiz;
	
	public void addOption(OptionForm optionForm) {
		options.add(optionForm);
		optionForm.setQuizBookForm(this);
	}

}
