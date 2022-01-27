package com.topsoft.bvs.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.topsoft.bvs.controller.dto.ElectionDto;
import com.topsoft.bvs.controller.dto.VoteDto;
import com.topsoft.bvs.entity.Citizen;

import com.topsoft.bvs.entity.Election;
import com.topsoft.bvs.entity.Post;
import com.topsoft.bvs.service.CandidateService;
import com.topsoft.bvs.service.CitizenService;
import com.topsoft.bvs.service.ElectionService;
import com.topsoft.bvs.service.PartyService;
import com.topsoft.bvs.service.UserService;
import com.topsoft.bvs.service.VoteService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	@Autowired
	CitizenService citizenService;
	@Autowired
	CandidateService candidateService;
	@Autowired
	ElectionService electionService;
	@Autowired
	PartyService partyService;
	@Autowired
	VoteService voteService;
	
	
	

	@GetMapping("/ballot/{post}")
	public String castVote(@PathVariable(name="post") String post,Principal principal, Model model) {
		
		Election election = electionService.findElectionByPost(post);
		Citizen citizen = (Citizen) service.findByUsername(principal.getName());
		List<Election> elections = citizen.getElections();
	
		
		if (elections != null) {
			
			 model.addAttribute("elections", elections);
			 
		}
		model.addAttribute("vote",new VoteDto());
		model.addAttribute("citizen",citizen);
		model.addAttribute("election", election);
		return "senate";
			
	} 
	@PostMapping("/ballot/{post}")
	public String createVote(@ModelAttribute("vote") VoteDto vDto, @PathVariable(name="post") String post,Principal principal) {
		Long election = electionService.findElectionByPost(post).getId();
		Long citizen = service.findByUsername(principal.getName()).getId();
		vDto.setElectionId(election);
		vDto.setCitizenId(citizen);
		voteService.addVote(vDto);
		return "redirect:/user/dashboard";
	}
	

	@GetMapping("/dashboard")
	public String showDashboard(Principal principal, Model model) {
		Citizen citizen = (Citizen) service.findByUsername(principal.getName());
		List<Election> elections = citizen.getElections();
	
		
		if (elections != null) {
			
			 model.addAttribute("elections", elections);
			 
		}
		model.addAttribute("citizen",citizen);
		
	
		
		return "user-dashboard";
	}
	@GetMapping("/citizenImages/{id}")
	public void getCitizenImage(@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException {
		byte[] bytes =citizenService.findCitizenById(id).getImage();
		InputStream logo = new BufferedInputStream(new ByteArrayInputStream(bytes));
		String mimeType = URLConnection.guessContentTypeFromStream(logo);
		response.setContentType(mimeType);
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	
	} 
	@GetMapping("/candImages/{id}")
	public void getCandidateImage(@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException {
		byte[] bytes =candidateService.findCandidateById(id).getImage();
		InputStream logo = new BufferedInputStream(new ByteArrayInputStream(bytes));
		String mimeType = URLConnection.guessContentTypeFromStream(logo);
		response.setContentType(mimeType);
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	
	} 
	@GetMapping("/partyImages/{id}")
	public void getPartyImage(@PathVariable(name="id") Long id, HttpServletResponse response) throws IOException {
		byte[] bytes =partyService.findPartyById(id).getLogo();
		InputStream logo = new BufferedInputStream(new ByteArrayInputStream(bytes));
		String mimeType = URLConnection.guessContentTypeFromStream(logo);
		response.setContentType(mimeType);
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	
	} 



}
