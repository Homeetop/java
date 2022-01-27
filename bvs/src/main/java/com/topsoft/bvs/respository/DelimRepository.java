package com.topsoft.bvs.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Delimitation;

@Repository
public interface DelimRepository  extends JpaRepository<Delimitation, Long>{
	@Query("select d.lga from Delimitation d where d.state = ?1")
	  List<String>findLgaByState(String state);
	@Query("select d.ward from Delimitation d where d.lga = ?1")
	  List<String>findWardByLga(String lga);
	  Delimitation findByLgaAndWard(String lga,String ward);
	List<Delimitation> findByState(String string);
	 
	  
	
}
