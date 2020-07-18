package com.shariqparwez.security.service;

import com.shariqparwez.security.model.AddTransactionToPortfolioDto;

public interface PortfolioCommandService {

	void addTransactionToPortfolio(AddTransactionToPortfolioDto request);
	void removeTransactionFromPortfolio(String transactionId);
	
}
