package com.topsoft.bvs.service;

import java.util.List;

import com.topsoft.bvs.controller.dto.CandidateRegDto;
import com.topsoft.bvs.entity.Candidate;



public interface CandidateService {
	void saveCandidate(CandidateRegDto candidate);

	Candidate findCandidateById(Long id);

	List<Candidate> findall();
}
