package br.com.ivogoncalves.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivogoncalves.models.CurrencyExchange;



public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	
	Optional<CurrencyExchange> findByFromAndTo(String from, String to); 
}
