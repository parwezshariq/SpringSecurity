package com.shariqparwez.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.shariqparwez.security.entity.PostDto;
import com.shariqparwez.security.entity.SupportQuery;
import com.shariqparwez.security.model.SupportQueryDto;
import com.shariqparwez.security.repository.SupportQueryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SupportQueryServiceNoSql implements SupportQueryService {

	private final SupportQueryRepository supportRepository;

	@Override
	public List<SupportQueryDto> getSupportQueriesForUser() {
		System.out.println(getUsername());
		List<SupportQuery> supportQueries = supportRepository.findByUsername(getUsername());
		return mapEntityToModel(supportQueries);
	}

	@Override
	public SupportQueryDto getSupportQueryById(String id) {
		return mapEntityToModel(this.supportRepository.findById(id).get());
	}
	
	@Override
	public List<SupportQueryDto> getSupportQueriesForAllUsers() {
		List<SupportQuery> supportQueries = this.supportRepository.findAll();
		return mapEntityToModel(supportQueries);
	}
	
	private SupportQueryDto mapEntityToModel(SupportQuery supportQuery) {
		List<PostDto> posts = supportQuery.getPosts().stream().map(post -> {
			return new PostDto(post.getId(), post.getContent(),post.getUsername(),supportQuery.isResolved());
		}).collect(Collectors.toList());
		return new SupportQueryDto(supportQuery.getId(), supportQuery.getSubject(), supportQuery.getCreated(),
				supportQuery.getUsername(), supportQuery.isResolved(), posts);

	}
	
	private String getUsername() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((User)principle).getUsername();
	}
	
	private List<SupportQueryDto> mapEntityToModel(List<SupportQuery> supportQueries) {
		return supportQueries.stream().map(query -> {
			return mapEntityToModel(query);
		}).collect(Collectors.toList());
	}

}