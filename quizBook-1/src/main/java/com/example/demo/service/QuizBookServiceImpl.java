package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.QuizBookEntity;
import com.example.demo.repository.QuizBookRepository;

@Service
public class QuizBookServiceImpl implements QuizBookService{
	
	@Autowired
	private QuizBookRepository quizBookRepository;

	@Override
	public Iterable<QuizBookEntity> selectAll() {
		return quizBookRepository.findAll();
	}

	@Override
	public Optional<QuizBookEntity> selectOneById(Integer id) {
		return quizBookRepository.findById(id);
	}

	@Override
	public Optional<QuizBookEntity> selectOneRamdomQuiz() {
		//★実装してません。
		return Optional.empty();
	}

	@Override
	public Boolean checkQuiz(Integer id, Boolean myAnswer) {
		//★実装してません。
		return null;
	}
	@Override
	public void insertQuiz(QuizBookEntity quiz) {
		quizBookRepository.save(quiz);
		
	}
	@Override
	public void updateQuiz(QuizBookEntity quiz) {
		quizBookRepository.save(quiz);
	}
	@Override
	public void deleteQuizById(Integer id) {
		quizBookRepository.deleteById(id);		
	}

}