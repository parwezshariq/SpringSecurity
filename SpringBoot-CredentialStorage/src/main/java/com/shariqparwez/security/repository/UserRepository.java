package com.shariqparwez.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shariqparwez.security.entity.CryptoUser;

public interface UserRepository extends MongoRepository<CryptoUser, String> {

	CryptoUser findByUsername(String username);
	CryptoUser findByEmail(String email);
	
}
