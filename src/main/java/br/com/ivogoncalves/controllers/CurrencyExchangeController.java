package br.com.ivogoncalves.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivogoncalves.dtos.CurrencyExchangeDTO;
import br.com.ivogoncalves.services.CurrencyExchangeService;

@RestController
@RequestMapping("/api/currency-exchange")
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeService service;

	@GetMapping(value="/{amount}/{from}/{to}")
	public ResponseEntity<CurrencyExchangeDTO> getCurrencyExchange(
							@PathVariable BigDecimal amount,
							@PathVariable String from,
							@PathVariable String to) {
		CurrencyExchangeDTO dto = service.getCurrencyExchange(from, to, amount);
		return ResponseEntity.ok(dto);
	}
}
