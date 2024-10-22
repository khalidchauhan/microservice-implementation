package com.in28minutes.spring.learn_spring_framework.game;

import org.springframework.stereotype.Component;

@Component("superContra")
public class SuperContra implements GamingConsole {
	
	@Override
	public void up() {
		System.out.println("SuperContra Jump");
	}

	@Override
	public void down() {
		System.out.println("SuperContra Go down into a hole");
	}

	@Override
	public void left() {
		System.out.println("SuperContra Stop");
	}

	@Override
	public void right() {
		System.out.println("SuperContra Accelerate");
	}

}
