package com.topsoft.bvs.service;

import java.util.List;


import com.topsoft.bvs.controller.dto.PartyRegDto;
import com.topsoft.bvs.entity.Party;


public interface PartyService {
	void saveParty(PartyRegDto party, byte[] image);
	List<Party> findAll();
	Party findPartyById(Long id);
	void deleteParty(Long id);
	List<String> setPartyName();
	Party findPartyByAbbreviation(String abbr);
}
