package com.shariqparwez.security;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.shariqparwez.security.entity.CryptoCurrency;
import com.shariqparwez.security.entity.Portfolio;
import com.shariqparwez.security.repository.CryptoCurrencyRepository;
import com.shariqparwez.security.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class WebApplication {

	private final PortfolioRepository portfolioRepository;
	private final CryptoCurrencyRepository cryptoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}	
	
	@EventListener(ApplicationReadyEvent.class)
	public void intializeUserData() {
				CryptoCurrency bitcoin = new CryptoCurrency("BTC", "Bitcoin");
		CryptoCurrency litecoin = new CryptoCurrency("LTC", "Litecoin");
		cryptoRepository.save(bitcoin);
		cryptoRepository.save(litecoin);
		portfolioRepository.save(new Portfolio("user", new ArrayList<>()));
	}
	
}