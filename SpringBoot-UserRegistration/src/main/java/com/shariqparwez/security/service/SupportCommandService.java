package com.shariqparwez.security.service;

import com.shariqparwez.security.entity.PostDto;
import com.shariqparwez.security.model.CreateSupportQueryDto;

public interface SupportCommandService {

	void createQuery(CreateSupportQueryDto query);
	void postToQuery(PostDto supportQueryPostModel);
	void resolveQuery(String id);
	
}
