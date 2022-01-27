package com.topsoft.bvs.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.ElectionDto;
import com.topsoft.bvs.entity.Citizen;

import com.topsoft.bvs.entity.Election;
import com.topsoft.bvs.entity.Post;
import com.topsoft.bvs.entity.Result;
import com.topsoft.bvs.respository.ElectionRepository;

@Service
public class ElectionServiceImpl implements ElectionService {
	
	
	@Autowired
	private ElectionRepository electionRepo;
	@Autowired
	private PostService postService;
	@Autowired
	private CitizenService cService;
	@Autowired
	private ResultService rservice;

	private final Logger log =LoggerFactory.getLogger(this.getClass());

	@Override
	public void createElection(ElectionDto eDto) {

			Election election = new  Election();
		
			
			String[] stringslice = eDto.getPost().split("-");
			Post post;
			
			if(eDto.getPost().equalsIgnoreCase("President")) {
				post = postService.findByName(eDto.getPost());
				election.setCitizens(cService.findAll());
				
			}
			else {
				//set post
				post = postService.findByNameAndState(stringslice[1], stringslice[0]);
				election.setCitizens(cService.findByState(stringslice[0].toUpperCase()));
			}
			
				
				election.setNumOfcandidates(post.getCands().size());
			
				election.setNumOfRegisteredVoters(election.getCitizens().size()) ;
				election.setPostName(post.getName());
				post.addElection(election);
				electionRepo.save(election);
				
	}
	public List<Citizen> addCitizen(String state){
		List<Citizen> citizens = cService.findAll();
		List<Citizen> citizs = new ArrayList<>();
		for(Citizen citizen : citizens) {
			if(citizen.getDelim().getState().equalsIgnoreCase(state)) {
				citizs.add(citizen);}
	}
		return citizs;}

	@Override
	public List<Election> findAll() {
		return electionRepo.findAll();
	}

	@Override
	public Election findElectionByPost(String post) {
		
		return electionRepo.findByPostName(post);
	}
	@Override
	public Election findById(Long id) {
		
		Optional<Election> response =electionRepo.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}
	@Override
	public Page<Election> findPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return electionRepo.findAll(pageable);
	}
	@Override
	public void deleteDelim(Long id) {
		electionRepo.deleteById(id);
		
	}
	}
	


