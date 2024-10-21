package com.example.demo.form;

import lombok.Data;

@Data
public class OptionForm {

	private Integer id;

	private String optionText;

	private boolean isCorrect;

	private QuizBookForm quizBookForm;

}
