package com.shariqparwez.security.service;

import java.util.List;

import com.shariqparwez.security.entity.CryptoCurrency;
import com.shariqparwez.security.entity.Portfolio;
import com.shariqparwez.security.model.AddTransactionToPortfolioDto;
import com.shariqparwez.security.model.ListTransactionsDto;
import com.shariqparwez.security.model.PortfolioPositionsDto;

public interface PortfolioService {

	List<CryptoCurrency> getSupportedCryptoCurrencies();
	Portfolio getPortfolioForUsername(String username);
	PortfolioPositionsDto getPortfolioPositions(String username);
	void addTransactionToPortfolio(AddTransactionToPortfolioDto request);
	ListTransactionsDto getPortfolioTransactions(String username);
	void removeTransactionFromPortfolio(String username, String transactionId);
	
}
