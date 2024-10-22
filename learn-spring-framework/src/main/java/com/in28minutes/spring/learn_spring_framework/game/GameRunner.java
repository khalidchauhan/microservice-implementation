package com.in28minutes.spring.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {

//	@Qualifier(value = "superContra")
	@Autowired
	private GamingConsole game;
	
	public void run() {
		game.up();
		game.down();
		game.left();
		game.right();
	}

}
