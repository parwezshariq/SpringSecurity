package com.shariqparwez.security.service;

import java.util.List;

import com.shariqparwez.security.model.CryptoCurrencyDto;

public interface CurrencyQueryService {

	List<CryptoCurrencyDto> getSupportedCryptoCurrencies();
	CryptoCurrencyDto getCryptoCurrency(String symbol);
}
