package com.in28minutes.spring.learn_spring_framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.in28minutes.spring.learn_spring_framework.web.MyWebController;

@SpringBootApplication
@ComponentScan(basePackages = "com.in28minutes.spring.learn_spring_framework")
public class LearnSpringFrameworkApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringFrameworkApplication.class, args);

		// GameRunner gameRunner = (GameRunner) context.getBean(GameRunner.class);
		// gameRunner.run();

		// GamingConsole console = new PacMan();
		// GameRunner runner = new GameRunner(console);

		MyWebController controller = (MyWebController) context.getBean(MyWebController.class);
		System.out.println(controller.returnValueFromBusinessService());
	}

}
