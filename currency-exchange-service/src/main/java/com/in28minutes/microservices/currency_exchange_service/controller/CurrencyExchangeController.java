package com.in28minutes.microservices.currency_exchange_service.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currency_exchange_service.model.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange = currencyExchangeService.retrieveExchangeValue(from, to);
		
		if (Objects.isNull(currencyExchange)) {
			throw new RuntimeException("Unable to find the data from " + from + " to " + to);
		}

		String property = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(property);
		return currencyExchange;
	}
}
