package com.home.learning.springboot.learn_jpa_and_hibernate.jdbc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.home.learning.springboot.learn_jpa_and_hibernate.entity.Course;

@Repository
public class CourseJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String INSERT_QUERY = """
				INSERT INTO COURSE(id, name, author)
				VALUES(?, ?, ?);
			""";

	private static String DELETE_QUERY = """
				DELETE FROM COURSE WHERE id=?;
			""";
	
	private static String SELECT_COURSE_QUERY = """
			SELECT * FROM COURSE WHERE id=?;
		""";
	
	private static String SELECT_ALL_COURSES_QUERY = """
			SELECT * FROM COURSE;
		""";

	public void insert() {
		jdbcTemplate.update(INSERT_QUERY);
	}

	public void insert(Course course) {
		jdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
	}
	
	public Course retrieveCourse(long id) {
		return jdbcTemplate.queryForObject(SELECT_COURSE_QUERY, 
				new BeanPropertyRowMapper<>(Course.class), id);
	}

	public void deleteById(long id) {
		jdbcTemplate.update(DELETE_QUERY, id);
	}
	
	public List<Map<String, Object>> retrieveAllCourses() {
		return jdbcTemplate.queryForList(SELECT_ALL_COURSES_QUERY, 
				new BeanPropertyRowMapper<>(Course.class));
	}
}
