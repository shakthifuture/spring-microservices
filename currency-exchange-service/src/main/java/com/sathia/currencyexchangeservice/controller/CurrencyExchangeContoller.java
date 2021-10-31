package com.sathia.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sathia.currencyexchangeservice.bean.CurrencyExchange;
import com.sathia.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeContoller {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		String port = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find currency exchange from "+from+" to "+to);
		}
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}

}
