package br.com.ivogoncalves.controllers;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivogoncalves.models.CurrencyExchange;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	private final AtomicLong count = new AtomicLong();
	private final String template = "PORT: { %s }";

	@GetMapping(value="/{amount}/{from}/{to}")
	public CurrencyExchange getCurrencyExchange(
							@PathVariable BigDecimal amount,
							@PathVariable String from,
							@PathVariable String to) {
		return new CurrencyExchange(count.getAndIncrement(), from, to, 
									BigDecimal.ONE, BigDecimal.ONE, 
									String.format(template, environment.getProperty("local.server.port")));
	}
}
