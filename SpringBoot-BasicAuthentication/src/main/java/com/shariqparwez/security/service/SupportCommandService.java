package com.shariqparwez.security.service;

import com.shariqparwez.security.model.CreateSupportQueryDto;
import com.shariqparwez.security.model.PostDto;

public interface SupportCommandService {

	void createQuery(CreateSupportQueryDto query);
	void postToQuery(PostDto supportQueryPostModel);
	void resolveQuery(String id);
	
}
