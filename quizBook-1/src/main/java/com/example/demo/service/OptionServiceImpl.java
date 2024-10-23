package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OptionEntity;
import com.example.demo.repository.OptionRepository;

@Service
public class OptionServiceImpl implements OptionService{
	@Autowired
	private OptionRepository optionRepository;

	@Override
	public Iterable<OptionEntity> selectAll() {
		return optionRepository.findAll();
	}

	@Override
	public Optional<OptionEntity> selectOneById(Integer id) {
		return optionRepository.findById(id);
	}

	@Override
	public Optional<OptionEntity> selectOneRamdomQuiz() {
		//★実装してません。
		return Optional.empty();
	}

	@Override
	public Boolean checkQuiz(Integer id, Boolean myAnswer) {
		//★実装してません。
		return null;
	}
	@Override
	public void insert(OptionEntity quiz) {
		optionRepository.save(quiz);
		
	}
	@Override
	public void update(OptionEntity quiz) {
		optionRepository.save(quiz);
	}
	@Override
	public void deleteQuizById(Integer id) {
		optionRepository.deleteById(id);		
	}

}
