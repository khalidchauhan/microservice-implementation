package com.home.learning.springboot.learn_jpa_and_hibernate.springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.learning.springboot.learn_jpa_and_hibernate.entity.Course;

@Repository
public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {

	List<Course> findByAuthor(String author);
	
	List<Course> findByName(String name);
}
