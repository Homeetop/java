package com.topsoft.bvs.service;






import java.util.List;

import org.springframework.data.domain.Page;

import com.topsoft.bvs.controller.dto.CitizenRegistrationDto;
import com.topsoft.bvs.entity.Citizen;



public interface CitizenService {
	void save(CitizenRegistrationDto registrationDto,byte[] image);
	Citizen findCitizenById(Long id);
	List<Citizen> findAll();
	void deleteCitizen(Long id);
	Citizen findCitizenNamesById(Long id);
	List<Citizen> findCitizensByDelim(Long id);
	List<Citizen> findByState(String state);
	Page<Citizen> findPagination(int pageNo, int pageSize);
}
