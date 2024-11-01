package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OptionForm {

	private Integer id;
	@NotBlank
	private String optionText;

	private boolean isCorrect;

	private QuizBookForm quizBookForm;

}
