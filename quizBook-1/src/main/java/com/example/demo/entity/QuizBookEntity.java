package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quizbookdb")
public class QuizBookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "explain")
	private String explain;
	
	@OneToMany(mappedBy = "quizBookEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OptionEntity> options = new ArrayList<>();
	
	public void addOption(OptionEntity optionEntity) {
		options.add(optionEntity);
		optionEntity.setQuizBookEntity(this);
	}

}
