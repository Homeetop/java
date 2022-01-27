package com.topsoft.bvs.service;

import java.util.List;

import com.topsoft.bvs.controller.dto.PostRegDto;
import com.topsoft.bvs.entity.Post;

public interface PostService {

	List<Post> findAll();

	Post findPostById(Long id);

	void deletePost(Long id);

	void save(PostRegDto postRegDto);
	List<String> setPostName();

	Post findByName(String string);
	Post findByNameAndState(String name, String state);



	

}
