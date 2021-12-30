package com.praise.michiko.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.praise.michiko.entity.PraiseWord;
import com.praise.michiko.entity.PraiseWordRepository;

@RequestMapping("/praise")
@RestController
@Transactional
@CrossOrigin
public class PraiseController {

	@Autowired
	private PraiseWordRepository repository;

	@GetMapping("/random")
	public PraiseWord random() {
		List<PraiseWord> entities = repository.findAll();

		return entities.stream().collect(Collectors.toList()).get(new Random().nextInt(entities.size()));
	}

	@GetMapping("/list")
	@CrossOrigin
	public List<PraiseWord> list() {
		return repository.findAll();
	}

	@PostMapping("/")
	@CrossOrigin
	public EmptyResponse create(@RequestBody PraiseWordCreateBody body) {
		PraiseWord entity = new PraiseWord();
		entity.word = body.word;
		repository.save(entity);

		return EmptyResponse.instance();
	}
}
