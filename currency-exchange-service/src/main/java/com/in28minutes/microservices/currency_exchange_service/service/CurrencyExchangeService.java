package com.in28minutes.microservices.currency_exchange_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.microservices.currency_exchange_service.model.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	public CurrencyExchange retrieveExchangeValue(String fromCurrency, String toCurrency) {
		return currencyExchangeRepository.findByFromAndTo(fromCurrency, toCurrency);
	}
}
