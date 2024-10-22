package com.in28minutes.spring.learn_spring_framework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("mario")
@Primary
public class Mario implements GamingConsole {
	
	@Override
	public void up() {
		System.out.println("Mario Jump");
	}

	@Override
	public void down() {
		System.out.println("Mario Go down into a hole");
	}

	@Override
	public void left() {
		System.out.println("Mario Stop");
	}

	@Override
	public void right() {
		System.out.println("Mario Accelerate");
	}

}
