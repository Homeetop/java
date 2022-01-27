package com.topsoft.bvs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.CandidateRegDto;
import com.topsoft.bvs.entity.Candidate;
import com.topsoft.bvs.entity.Citizen;
import com.topsoft.bvs.entity.Party;
import com.topsoft.bvs.entity.Post;
import com.topsoft.bvs.respository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private PartyService service;
	@Autowired
	private CitizenService cService;
	@Autowired
	private PostService postService;
	
	
	@Override
	public void saveCandidate(CandidateRegDto candReg) {
		Candidate cand = new Candidate();
		
		Citizen citizen = cService.findCitizenById(candReg.getId());
		Party party = service.findPartyByAbbreviation(candReg.getParty());
		cand.setFirstname(citizen.getFirstname());
		cand.setLastname(citizen.getLastname());
		cand.setDelim(citizen.getDelim());
		cand.setImage(citizen.getImage());
		cand.setUsername(citizen.getUsername()+"c");
		cand.setPassword(citizen.getPassword());
		cand.setAddress(citizen.getAddress());
		cand.setSex(citizen.getSex());
		cand.setDob(citizen.getDob());
		cand.setEmail(citizen.getEmail());
		cand.setMobile(citizen.getMobile());
		cand.setOccupation(citizen.getOccupation());
		cand.setQualification(candReg.getQualification());
		party.add(cand);
		if(candReg.getPost().equalsIgnoreCase("President")) {
			Post post = postService.findByName(candReg.getPost());
			post.addCandidate(cand);
		}
		else {
			String[] stringslice = candReg.getPost().split("-");
			//set post
			Post post = postService.findByName(stringslice[1]);
			post.addCandidate(cand);}
		candidateRepository.save(cand);
		
		//cService.deleteCitizen(candReg.getId());
	}

	@Override
	public Candidate findCandidateById(Long id) {
		Optional<Candidate> response = candidateRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}

	@Override
	public List<Candidate> findall() {
	
		return candidateRepository.findAll();
	}
}
