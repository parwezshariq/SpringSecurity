package com.shariqparwez.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shariqparwez.security.entity.Portfolio;

public interface PortfolioRepository extends MongoRepository<Portfolio, String> {
	
	Portfolio findByUsername(String username);
}
