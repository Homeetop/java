package com.topsoft.bvs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.PartyRegDto;

import com.topsoft.bvs.entity.Party;
import com.topsoft.bvs.respository.PartyRepository;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired
	PartyRepository partyRepository;

	@Override
	public void saveParty(PartyRegDto party, byte[] image) {
		
	
		Party partyS= new Party();
		partyS.setId(party.getId());
		partyS.setAbbreviation(party.getAbbreviation());
		partyS.setName(party.getName());
		try {
			byte[] oldImage = partyRepository.getById(party.getId()).getLogo();
			if(image ==null) {
				partyS.setLogo(oldImage);
			}else {partyS.setLogo(image);}
		}catch(Exception e) {partyS.setLogo(image);}
		
		
		partyRepository.save(partyS);
	}

	@Override
	public List<Party> findAll() {
		return partyRepository.findAll();
		
	}

	@Override
	public Party findPartyById(Long id) {
		Optional<Party> partyResponse =partyRepository.findById(id);
		if(partyResponse.isPresent()) {
			return partyResponse.get();
		}
		return null;
	}

	@Override
	public void deleteParty(Long id) {
		partyRepository.deleteById(id);
		
	}

	@Override
	public List<String> setPartyName(){
		List<String> parties = new ArrayList<>();
		List<Party>partys=partyRepository.findAll();
		for(Party parti: partys) {
			parties.add(parti.getAbbreviation());
		}
		Set<String> set = new HashSet<>(parties);
		parties.clear();
		Iterator<String> itObjectForSet = set.iterator();
		while (itObjectForSet.hasNext()) {
			parties.add(itObjectForSet.next());
		}
		Collections.sort(parties);
		return parties;
	}

	@Override
	public Party findPartyByAbbreviation(String logo) {
		return partyRepository.findByAbbreviation(logo);
	}

	
	
	

}
