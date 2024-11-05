package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.QuizBookEntity;
import com.example.demo.repository.QuizBookRepository;

@ExtendWith(MockitoExtension.class)
public  class QuizBookServiceImplTest {
	
	@Mock
	QuizBookRepository quizBookRepository;
	
	@InjectMocks
	QuizBookServiceImpl quizBookServiceImpl;

	@Test
	@DisplayName("データベースからentityが一つ取得できるか確認")
	public void selectOneByIdTest() {
		
		QuizBookEntity entity = new QuizBookEntity();
		entity.setId(1);
		Optional<QuizBookEntity> expected = Optional.of(entity);
		
		when(quizBookServiceImpl.selectOneById(1)).thenReturn(expected);
		var actual = quizBookServiceImpl.selectOneById(1);
		assertTrue(actual.isPresent());
		assertEquals(expected.get(),actual.get());
		verify(quizBookRepository,times(1)).findById(anyInt());
		
		//引数の中を取り出して確認している
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		verify(quizBookRepository).findById(captor.capture());
		Integer id = captor.getValue();
		assertEquals(1,id);
	}

}
