package com.topsoft.bvs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.DelimRegDto;
import com.topsoft.bvs.entity.Delimitation;
import com.topsoft.bvs.respository.DelimRepository;


@Service
public class DelimServiceImpl implements DelimService {

	@Autowired
	private DelimRepository delimRepo;
	
	
	@Override
	public void saveDelim(DelimRegDto delim) {
		Delimitation newdelim = new Delimitation();
		newdelim.setId(delim.getId());
		newdelim.setWard(delim.getWard());
		newdelim.setLga(delim.getLga());
		newdelim.setState(delim.getState());
		
		delimRepo.save(newdelim);
	}
	@Override
	public List<Delimitation> findall() {
		
		return delimRepo.findAll();
	}
	@Override
	public List<String> setState() {
		List<String> states = new ArrayList<>();
		List<Delimitation>delims=delimRepo.findAll();
		for(Delimitation delim: delims) {
			states.add(delim.getState());
		}
		Set<String> set = new HashSet<>(states);
		states.clear();
		Iterator<String> itObjectForSet = set.iterator();
		while (itObjectForSet.hasNext()) {
			states.add(itObjectForSet.next());
		}
		Collections.sort(states);
		return states;
	}
	@Override
	public List<String> setLga(String state) {
		List<String> lgas = new ArrayList<>();
	  Set<String> set = new HashSet<>(delimRepo.findLgaByState(state));
	  Iterator<String> itObjectForSet = set.iterator();
		while (itObjectForSet.hasNext()) {
			lgas.add(itObjectForSet.next());
		}
		Collections.sort(lgas);
		return lgas;
	}
	@Override
	public List<String> setWard(String lga) {
		List<String> ward = new ArrayList<>();
		  Set<String> set = new HashSet<>(delimRepo.findWardByLga(lga));
		  Iterator<String> itObjectForSet = set.iterator();
			while (itObjectForSet.hasNext()) {
				ward.add(itObjectForSet.next());
			}
			Collections.sort(ward);
		  
			return ward;
	}
	@Override
	public Delimitation findByLgaAndWard(String lga, String ward) {

		return delimRepo.findByLgaAndWard(lga, ward);
	}
	@Override
	public Delimitation findPartyById(Long id) {
		Optional<Delimitation> response =delimRepo.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}
	@Override
	public void deleteDelim(Long id) {
		delimRepo.deleteById(id);
		
	}
	@Override
	public List<Delimitation> findByState(String string) {
		return delimRepo.findByState(string);
	}
	@Override
	public Page<Delimitation> findPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return delimRepo.findAll(pageable);
	}
	
	

	
	
}
