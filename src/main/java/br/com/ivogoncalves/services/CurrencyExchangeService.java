package br.com.ivogoncalves.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ivogoncalves.dtos.CurrencyExchangeDTO;
import br.com.ivogoncalves.repositories.CurrencyExchangeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyExchangeService {

	private final String template = "PORT: { %s }";
	
	@Autowired
	private Environment environment;
	@Autowired
	public CurrencyExchangeRepository repository;
	@Autowired
	public ModelMapper mapper;
	
	public CurrencyExchangeDTO getCurrencyExchange(String from, String to, BigDecimal amount) {
		log.info("Find Currency Exchange by from: {} and to: {} having with amount: {}", from, to, amount);
		var entity = repository.findByFromAndTo(from, to).orElseThrow(
				() -> new RuntimeException("Currency Exchange not found! From: " + from + " To: " + to));
		BigDecimal conversionFactor = entity.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		entity.setEnvironment(String.format(template, environment.getProperty("local.server.port")));
		entity.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		return mapper.map(entity, CurrencyExchangeDTO.class);
	}
	
}
