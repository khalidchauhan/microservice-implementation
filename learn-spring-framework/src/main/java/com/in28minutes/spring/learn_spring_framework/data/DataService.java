package com.in28minutes.spring.learn_spring_framework.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DataService {

	public List<Integer> getData() {
		return Arrays.asList(10, 20, 30, 40);
	}
}
