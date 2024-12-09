package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.QuizBookEntity;

@Repository
public interface QuizBookRepository extends JpaRepository<QuizBookEntity,Integer> {
	
	@Query("SELECT q FROM QuizBookEntity q ORDER BY RANDOM() limit 1")
	Optional<QuizBookEntity> findByRandomId();
	
	@Query("SELECT q FROM QuizBookEntity q ORDER BY insertTime DESC")
	Iterable<QuizBookEntity> findAllDescInsertTime();
	
	@Query("SELECT q FROM QuizBookEntity q WHERE "
			+ "q.question LIKE CONCAT('%',:searchWord,'%') OR "
			+ "q.code LIKE CONCAT('%',:searchWord,'%')  ORDER BY insertTime DESC")
	Iterable<QuizBookEntity> findSearchAllDescInsertTime(@Param("searchWord") String searchWord);
	
	@Query("SELECT q FROM QuizBookEntity q ORDER BY RANDOM() limit :limit")
	Iterable<QuizBookEntity> findByLimit(Integer limit);
	
//	@Query(value = "SELECT * FROM quizbookdb ORDER BY RAND() LIMIT :limit",nativeQuery = true)
//	Page<QuizBookEntity> findByLimitPaging(@Param("limit")Integer limit,Pageable pageable);
	

}
