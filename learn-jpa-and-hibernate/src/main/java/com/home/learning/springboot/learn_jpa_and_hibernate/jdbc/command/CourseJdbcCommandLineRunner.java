package com.home.learning.springboot.learn_jpa_and_hibernate.jdbc.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.home.learning.springboot.learn_jpa_and_hibernate.jdbc.repository.CourseJdbcRepository;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJdbcRepository courseJdbcRepository;

	@Override
	public void run(String... args) throws Exception {
		// courseJdbcRepository.insert(new Course(2, "Learn Azure", "Ranga"));
		// courseJdbcRepository.insert(new Course(3, "Learn GCP", "Ranga"));
		// courseJdbcRepository.insert(new Course(4, "Spring", "Ranga"));
		// courseJdbcRepository.insert(new Course(5, "Microservices", "Ranga"));

		// courseJdbcRepository.deleteById(4);

		// System.out.println(courseJdbcRepository.retrieveCourse(5));

		// courseJdbcRepository.deleteById(4);
	}
}
