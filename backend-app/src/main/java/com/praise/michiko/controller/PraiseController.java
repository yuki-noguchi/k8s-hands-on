package com.praise.michiko.controller;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/praise")
@RestController
public class PraiseController {

	private static final List<String> PRAISE_WORDS = List.of("最高だぜ！！！", //
			"あんたにはかなわねえよ、", //
			"あんがナンバーワンだ・・・", //
			"こいつ、戦いの中で成長してやがる・・・！なんてやつだ、", //
			"べ、別に今回はわざと負けてあげただけなんだからね！", //
			"俊足の", //
			"あたしの人生の師匠、", //
			"クールだ・・・", //
			"ほう・・・関心ですねえ・・・", //
			"あたしの憧れ！！", //
			"そう・・・とどのつまり天才・・・その名も・・・", //
			"悪魔的・・・あまりに悪魔的・・・", //
			"ナウでヤングな");

	@PostMapping("/")
	@CrossOrigin
	public String praise(@RequestBody PraiseBody body) {
		return PRAISE_WORDS.get(new Random().nextInt(PRAISE_WORDS.size())) + body.name + "・・・！！";
	}
}
