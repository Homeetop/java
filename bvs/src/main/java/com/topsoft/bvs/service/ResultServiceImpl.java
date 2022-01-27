package com.topsoft.bvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.entity.Result;
import com.topsoft.bvs.respository.ResultRepository;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultRepository rRepo;

	@Override
	public void save(Result result) {
		rRepo.save(result);

	}

}
