package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.OptionEntity;

public interface OptionService {
	/** クイズ情報を全件取得します　*/
	Iterable<OptionEntity> selectAll();
	/** クイズ情報をidをキーに取得します　*/
	Optional<OptionEntity> selectOneById(Integer id);
	/** クイズ情報をランダムで1件取得します　*/
	Optional<OptionEntity> selectOneRamdomQuiz();
	
	
	/** クイズの正解不正解を判定します */
	Boolean checkQuiz(Integer id,Boolean myAnswer);
	/**クイズを登録します*/
	void insert(OptionEntity quiz);
	/** クイズを更新します */
	void update(OptionEntity quiz);
	/** クイズを削除します */
	void deleteQuizById(Integer id);

}
