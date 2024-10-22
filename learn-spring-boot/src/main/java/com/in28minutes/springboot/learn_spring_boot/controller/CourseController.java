package com.in28minutes.springboot.learn_spring_boot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.learn_spring_boot.dto.CourseDto;

@RestController
public class CourseController {

	@GetMapping(path = "/courses", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<CourseDto> retrieveAllCourses() {
		return Arrays.asList(
				new CourseDto(1, "Learn AWS", "In28Minutes"),
				new CourseDto(1, "Learn DevOps", "In28Minutes"),
				new CourseDto(1, "Learn Azure", "In28Minutes"),
				new CourseDto(1, "Learn GCP", "In28Minutes")
				);
	}
}
