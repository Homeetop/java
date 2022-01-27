package com.topsoft.bvs.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.topsoft.bvs.controller.dto.ElectionDto;
import com.topsoft.bvs.entity.Election;

public interface ElectionService {
	void createElection(ElectionDto eDto);
	List<Election> findAll();
	Election findElectionByPost(String post);
	Election findById(Long id);
	Page<Election> findPagination(int pageNo, int pageSize);
	void deleteDelim(Long id);

}
