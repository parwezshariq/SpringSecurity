package com.shariqparwez.security.service;

import com.shariqparwez.security.model.ListTransactionsDto;
import com.shariqparwez.security.model.PortfolioPositionsDto;

public interface PortfolioQueryService {

	PortfolioPositionsDto getPortfolioPositions();
	ListTransactionsDto getPortfolioTransactions();
	
}
