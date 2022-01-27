package com.topsoft.bvs.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
	

}
