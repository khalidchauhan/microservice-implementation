package com.in28minutes.spring.learn_spring_framework.game;

import org.springframework.stereotype.Component;

@Component("pacMan")
public class PacMan implements GamingConsole {

	@Override
	public void up() {
		System.out.println("PacMan Jump");
	}

	@Override
	public void down() {
		System.out.println("PacMan Go down into a hole");
	}

	@Override
	public void left() {
		System.out.println("PacMan Stop");
	}

	@Override
	public void right() {
		System.out.println("PacMan Accelerate");
	}
}
