package com.in28minutes.microservices.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currency_conversion_service.entity.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class CurrencyConversionController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		HashMap<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion responseBody = responseEntity.getBody();

		CurrencyConversion currencyConversion = new CurrencyConversion(10001L, from, to,
				responseBody.getConversionMultiple(), quantity, quantity.multiply(responseBody.getConversionMultiple()),
				responseBody.getEnvironment());

		return currencyConversion;
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionUsingFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion responseBody = currencyExchangeProxy.retrieveExchangeValue(from, to);

		CurrencyConversion currencyConversion = new CurrencyConversion(10001L, from, to,
				responseBody.getConversionMultiple(), quantity, quantity.multiply(responseBody.getConversionMultiple()),
				responseBody.getEnvironment());

		return currencyConversion;
	}
}
