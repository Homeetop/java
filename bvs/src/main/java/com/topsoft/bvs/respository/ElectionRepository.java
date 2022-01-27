package com.topsoft.bvs.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.topsoft.bvs.entity.Election;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long>{

	Election findByPostName(String post);
	

}
