package com.shariqparwez.security.service;

import java.math.BigDecimal;

public interface PricingService {

	BigDecimal getCurrentPriceForCrypto(String symbol);
	
}