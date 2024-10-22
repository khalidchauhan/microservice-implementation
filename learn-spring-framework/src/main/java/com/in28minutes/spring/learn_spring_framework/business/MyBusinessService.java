package com.in28minutes.spring.learn_spring_framework.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.spring.learn_spring_framework.data.DataService;

@Service
public class MyBusinessService {

	@Autowired
	private DataService dataService;
	
	public MyBusinessService(DataService dataService) {
		this.dataService = dataService;
	}

	public long calculateSum() {
		return dataService.getData().stream().reduce(Integer::sum).get();
	}
}
