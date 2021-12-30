package com.praise.michiko.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "praise_word")
public class PraiseWord {

	@Id
	@Column(name = "praise_word_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long praiseWordId;

	@Column(name = "word")
	public String word;
}
