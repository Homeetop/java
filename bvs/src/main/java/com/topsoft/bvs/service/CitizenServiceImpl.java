package com.topsoft.bvs.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.topsoft.bvs.controller.dto.CitizenRegistrationDto;
import com.topsoft.bvs.entity.Citizen;
import com.topsoft.bvs.entity.Delimitation;
import com.topsoft.bvs.respository.CitizenRepository;

@Service
public class CitizenServiceImpl implements CitizenService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DelimService delimService;
	
	
	private CitizenRepository  citizenRepository;
	public CitizenServiceImpl(CitizenRepository citizenRepository) {
		super();
		this.citizenRepository = citizenRepository;
	}
	@Override
	public void save(CitizenRegistrationDto registrationDto, byte[] image) {
		Citizen citizen = new Citizen();
		Delimitation delim = delimService.findByLgaAndWard(registrationDto.getLga().toUpperCase(),
				registrationDto.getWard().toUpperCase());
		citizen.setFirstname(registrationDto.getFirstName());
		citizen.setLastname(registrationDto.getLastName());
		citizen.setSex(registrationDto.getSex()); 
		citizen.setDob(registrationDto.getDob());
		citizen.setEmail(registrationDto.getEmail());
		citizen.setOccupation(registrationDto.getOccupation());
		citizen.setMobile(registrationDto.getMobile()); 
		citizen.setAddress(registrationDto.getAddress());
		citizen.setUsername(registrationDto.getLastName());
		citizen.setPassword(passwordEncoder.encode(registrationDto.getFirstName()));
		citizen.setImage(image);
		delim.add(citizen);
		
		citizenRepository.save(citizen);
	}
	@Override
	public Citizen findCitizenById(Long id) {
		Optional<Citizen> response = citizenRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		return null;
	}
	@Override
	public List<Citizen> findAll() {
		return citizenRepository.findAll();
	}
	@Override
	public void deleteCitizen(Long id) {
		citizenRepository.deleteById(id);
		
	}
	@Override
	public Citizen findCitizenNamesById(Long id) {
		Citizen citizen = new Citizen();
		Optional<Citizen> response = citizenRepository.findById(id);
		if(response.isPresent()) {
			citizen.setId(id);
			citizen.setFirstname(response.get().getFirstname());
			citizen.setLastname(response.get().getLastname());
		}		
				return citizen;
	}
	@Override
	public List<Citizen> findCitizensByDelim(Long id) {
		
		return citizenRepository.findByDelim(id);
	}
	@Override
	public List<Citizen> findByState(String state) {
		
		return citizenRepository.findByDelimState(state);
	}
	@Override
	public Page<Citizen> findPagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return citizenRepository.findAll(pageable);
	}
	

}
