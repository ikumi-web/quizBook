package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.OptionEntity;
import com.example.demo.entity.QuizBookEntity;
import com.example.demo.service.OptionService;
import com.example.demo.service.QuizBookService;

@WebMvcTest(QuizBookPlayController.class)
public class QuizBookPlayControllerTest {
	
	@Autowired
	MockMvc mvc;
	@MockBean
	QuizBookService quizBookservice;
	@MockBean
	OptionService optionService;

	@Test
	public void クイズを取得し空じゃない場合に画面遷移がうまくいくか確認() throws Exception{
		
		QuizBookEntity entity = new QuizBookEntity();
	    entity.setOptions(List.of(new OptionEntity(), new OptionEntity())); // 空のOptionEntityを2つ追加
	    when(quizBookservice.selectRamdomQuiz()).thenReturn(Optional.of(entity));
	    
	    //MockMvcRequestBuildersをstaticインポートすることでandExpectの引数にいちいち書かなくて良くなった
	    //MockMvcResultMatchersをstaticインポートすることでandExpectの引数にいちいち書かなくて良くなった
		mvc.perform(post("/quizbook/play")).
		andExpect(status().isOk()).
		andExpect(view().name("javaSilver/play/play")).
		andExpect(model().attributeExists("correctCount")).
		andExpect(model().attributeExists("title")).
		andExpect(model().attributeExists("entity"));
		
	}
	@Test
	public void クイズを取得し空だった場合に画面遷移がうまくいくか確認() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/quizbook/play")).
		andExpect(MockMvcResultMatchers.status().isOk()).
		andExpect(MockMvcResultMatchers.view().name("javaSilver/java-silver")).
		andExpect(MockMvcResultMatchers.model().attributeExists("msg"));
	}

}
