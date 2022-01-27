/**
 * 
 */
package com.topsoft.bvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.VoteDto;
import com.topsoft.bvs.entity.Candidate;
import com.topsoft.bvs.entity.Citizen;
import com.topsoft.bvs.entity.Election;
import com.topsoft.bvs.entity.Vote;
import com.topsoft.bvs.respository.VoteRepository;

/**
 * @author user-pc
 *
 */
@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteRepository voteRepo;
	@Autowired
	ElectionService eService;
	@Autowired
	CandidateService candService;
	@Autowired
	CitizenService cService;

	@Override
	public void addVote(VoteDto vote) {
		Election election = eService.findById(vote.getElectionId());
		Candidate cand =  candService.findCandidateById(vote.getCandidateId());
		Citizen citizen = cService.findCitizenById(vote.getCitizenId());
		election.updateList(citizen);
		Vote newVote = new Vote();
		newVote.setCitizen(citizen);
		newVote.setCandidate(cand);
		newVote.setElection(election);
		voteRepo.save(newVote);
		
		
	}

}
