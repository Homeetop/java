package com.topsoft.bvs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.PostRegDto;
import com.topsoft.bvs.entity.Post;
import com.topsoft.bvs.respository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private DelimService delimService;

	@Override
	public List<Post> findAll() {
		
		return postRepo.findAll();
	}

	@Override
	public Post findPostById(Long id) {
		Optional<Post> postResponse =postRepo.findById(id);
		if(postResponse.isPresent()) {
			return postResponse.get();
		}
		return null;
	}

	@Override
	public void deletePost(Long id) {
		postRepo.deleteById(id);
		
	}

	@Override
	public void save(PostRegDto postRegDto) {
		Post post = new Post();
		
			post.setName(postRegDto.getName());
			post.setState(postRegDto.getState());
			post.setZone(postRegDto.getZone());
			post.setLga(postRegDto.getLga());
			
			if(postRegDto.getName().equalsIgnoreCase("President")) {
				post.setDelims(delimService.findall());
			}
			else{
				post.setDelims(delimService.findByState(postRegDto.getState()));
			}
			postRepo.save(post);
		
	}

	@Override
	public List<String> setPostName() {
		List<String> posts = new ArrayList<>();
		List<Post> postss = postRepo.findAll();
		for(Post post: postss) {
		if(post.getName().equalsIgnoreCase("President")) {
			posts.add(post.getName());
		}else {
			posts.add(post.getState() +"-"+ post.getName());
		}}
		
		Collections.sort(posts);
		return posts;
	}

	@Override
	public Post findByName(String string) {
		Optional<Post> postResponse =postRepo.findByName(string);
		if(postResponse.isPresent()) {
			return postResponse.get();
		}
		return null;
	}

	@Override
	public Post findByNameAndState(String name, String state) {
		return postRepo.findByNameAndState(name, state);
	}
	

}
