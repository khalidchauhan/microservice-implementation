package com.in28minutes.springboot.learn_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.learn_spring_boot.configuration.CurrencyServiceConfiguration;

@RestController
public class CurrencyConfigurationController {
	
	@Autowired
	private CurrencyServiceConfiguration configuration;

	@GetMapping(path = "/currency-configuration", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public CurrencyServiceConfiguration retrieveCurrencyServiceConfiguration() {
		return configuration;
	}
}
