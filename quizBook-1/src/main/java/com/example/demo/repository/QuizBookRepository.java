package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.QuizBookEntity;

@Repository
public interface QuizBookRepository extends JpaRepository<QuizBookEntity,Integer> {
	
	@Query("SELECT q FROM QuizBookEntity q ORDER BY RANDOM() limit 1")
	Optional<QuizBookEntity> findByRandomId();
	
	@Query("SELECT q FROM QuizBookEntity q ORDER BY insertTime DESC")
	Iterable<QuizBookEntity> findAllDescInsertTime();

}
