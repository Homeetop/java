package com.topsoft.bvs.respository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
	List<Citizen> findByDelim(Long id);

	List<Citizen>findByDelimState(String state);
}
