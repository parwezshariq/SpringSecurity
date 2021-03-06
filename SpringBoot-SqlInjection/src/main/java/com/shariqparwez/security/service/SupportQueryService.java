package com.shariqparwez.security.service;

import java.util.List;

import com.shariqparwez.security.model.SupportQueryDto;

public interface SupportQueryService {

	List<SupportQueryDto> getSupportQueriesForUser();
	SupportQueryDto getSupportQueryById(String queryId);
	List<SupportQueryDto> getSupportQueriesForAllUsers();
	
}
