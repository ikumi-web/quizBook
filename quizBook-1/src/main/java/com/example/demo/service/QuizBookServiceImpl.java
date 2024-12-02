package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Iterable<QuizBookEntity> selectAllDescInsertTime(){
		return quizBookRepository.findAllDescInsertTime();
	}
	@Override
	public Iterable<QuizBookEntity> selectSearchAllDescInsertTime(String searchWord){
		return quizBookRepository.findSearchAllDescInsertTime(searchWord);
	}
	@Override
	public Iterable<QuizBookEntity> selectByLimit(Integer limit){
		return quizBookRepository.findByLimit(limit);
	}
	@Override
	public Page<QuizBookEntity> selectByLimitPaging(Integer limit){
		List<QuizBookEntity> randomList = new ArrayList<>();
		Iterable<QuizBookEntity> randomEntity = quizBookRepository.findByLimit(limit);
		randomEntity.forEach(randomList::add);
		Pageable pageable = PageRequest.of(0,1);
	    int start = (int) pageable.getOffset(); // ページング開始位置
	    int end = Math.min(start + pageable.getPageSize(), randomList.size());// ページング終了位置
	    List<QuizBookEntity> subList = randomList.subList(start, end);
		Page<QuizBookEntity> pageList = new PageImpl<>(subList,pageable,limit);
		return pageList;
	}

	@Override
	public Optional<QuizBookEntity> selectOneById(Integer id) {
		return quizBookRepository.findById(id);
	}

	@Override
	public Optional<QuizBookEntity> selectRamdomQuiz() {
		return quizBookRepository.findByRandomId();
	}

	@Override
	public Boolean checkQuiz(Integer id, Boolean myAnswer) {
		//★実装してません。
		return null;
	}
	@Override
	public void insert(QuizBookEntity quiz) {
		quizBookRepository.save(quiz);
		
	}
	@Override
	public void update(QuizBookEntity quiz) {
		quizBookRepository.save(quiz);
	}
	@Override
	public void deleteQuizById(Integer id) {
		quizBookRepository.deleteById(id);		
	}

}
