package com.topsoft.bvs.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.topsoft.bvs.controller.dto.DelimRegDto;
import com.topsoft.bvs.entity.Delimitation;

public interface DelimService {
	void saveDelim(DelimRegDto delim);
	List<Delimitation> findall();
	List<String> setState();
	List<String> setLga(String State);
	List<String> setWard(String lga);
	Delimitation findByLgaAndWard(String lga,String ward);
	Delimitation findPartyById(Long id);
	void deleteDelim(Long id);
	List<Delimitation> findByState(String string);
	Page<Delimitation> findPagination(int pageNo, int pageSize);

	
}
