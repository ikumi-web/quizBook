package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.QuizBookEntity;


public interface QuizBookService {
	
	/** クイズ情報を全件取得します　*/
	Iterable<QuizBookEntity> selectAll();
	/** クイズ情報をidをキーに取得します　*/
	Optional<QuizBookEntity> selectOneById(Integer id);
	/** クイズ情報をランダムで1件取得します　*/
	Optional<QuizBookEntity> selectOneRamdomQuiz();
	
	
	/** クイズの正解不正解を判定します */
	Boolean checkQuiz(Integer id,Boolean myAnswer);
	/**クイズを登録します*/
	void insert(QuizBookEntity quiz);
	/** クイズを更新します */
	void update(QuizBookEntity quiz);
	/** クイズを削除します */
	void deleteQuizById(Integer id);

}
