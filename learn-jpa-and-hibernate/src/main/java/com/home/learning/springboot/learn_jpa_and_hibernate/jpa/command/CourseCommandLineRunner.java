package com.home.learning.springboot.learn_jpa_and_hibernate.jpa.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.home.learning.springboot.learn_jpa_and_hibernate.jpa.repository.CourseJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJpaRepository courseJpaRepository;

	@Override
	public void run(String... args) throws Exception {
		// courseJpaRepository.insert(new Course(6, "Docker", "Ranga"));
		// courseJpaRepository.insert(new Course(7, "Kubernetes", "Ranga"));
		// courseJpaRepository.insert(new Course(8, "Kafka", "Ranga"));

		// System.err.println(courseJpaRepository.findById(7));

		// courseJpaRepository.deleteById(8);
	}
}
