package com.home.learning.springboot.learn_jpa_and_hibernate.springdatajpa.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.home.learning.springboot.learn_jpa_and_hibernate.entity.Course;
import com.home.learning.springboot.learn_jpa_and_hibernate.springdatajpa.repository.CourseSpringDataJpaRepository;

@Component
public class CourseSpringDataJpaCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseSpringDataJpaRepository courseJdbcRepository;

	@Override
	public void run(String... args) throws Exception {
		courseJdbcRepository.save(new Course(2, "Learn Azure", "Ranga"));
		courseJdbcRepository.save(new Course(3, "Learn GCP", "Ranga"));
		courseJdbcRepository.save(new Course(4, "Spring", "Ranga"));
		courseJdbcRepository.save(new Course(5, "Microservices", "Ranga"));

		courseJdbcRepository.deleteById(4L);

		System.out.println(courseJdbcRepository.findById(5L));

		System.out.println(courseJdbcRepository.findAll());
		
		System.out.println(courseJdbcRepository.count());
		
		System.out.println(courseJdbcRepository.findByAuthor("Ranga"));
		
		System.out.println(courseJdbcRepository.findByName("Learn Azure"));
	}
}
