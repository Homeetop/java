package com.topsoft.bvs.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {
	
	

}
