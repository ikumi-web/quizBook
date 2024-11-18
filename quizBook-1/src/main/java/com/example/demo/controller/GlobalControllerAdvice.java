package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllerAdvice {
	
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatExceptionHandler(NumberFormatException e,Model model) {
		model.addAttribute("error","内部サーバーエラー：GrobalCotrollerAdvice");
		model.addAttribute("message","NumberFormatExceptionが発生しました。【数字】を入力してください");
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		return "error/error";
	}
	
	//このメソッドのコメントを解除すると404だろうが、こいつが拾ってerrorページに遷移させられる
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e,Model model) {
//		//例外クラスのメッセージをModelに登録
//		model.addAttribute("error","内部サーバーエラー：GrobalCotrollerAdvice");
//		
//		//例外クラスをModelに登録
//		model.addAttribute("message","Exceptionが発生しました");
//		
//		//HTTPエラーコード（500）をModelに登録
//		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
//		
//		return "error/error";
//	}
	

}
