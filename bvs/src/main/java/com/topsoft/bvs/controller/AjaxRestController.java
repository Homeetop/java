package com.topsoft.bvs.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.topsoft.bvs.entity.Citizen;
import com.topsoft.bvs.fingerprintevents.FPEventsPublisher;
import com.topsoft.bvs.fingerprintevents.FPReaderStatusEvent;
import com.topsoft.bvs.fingerprintevents.FPReaderStatusEventAdapter;
import com.topsoft.bvs.service.CitizenService;
import com.topsoft.bvs.service.DelimService;

import com.topsoft.bvs.service.PartyService;
import com.topsoft.bvs.service.PostService;


@RestController
@RequestMapping("api/ajaxrest")
public class AjaxRestController {
	
	@Autowired
	private DelimService delimService;
	@Autowired 
	private PartyService partyService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private PostService postService;
	@Autowired
	private FPEventsPublisher publisher;
	
	
	
	@GetMapping(
			value ="status", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>status(){
		
		try {
			List<String>status = new ArrayList<>();
			DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
			capturer.addReaderStatusListener(new FPReaderStatusEventAdapter() {
				int lastStatus = DPFPReaderStatusEvent.READER_CONNECTED;
				public void readerConnected(DPFPReaderStatusEvent e) {
					if (lastStatus != e.getReaderStatus())
						status.add("Reader is connected");
					lastStatus = e.getReaderStatus();
					publisher.publishEvent(e);
				}
				public void readerDisconnected(DPFPReaderStatusEvent e) {
					if (lastStatus != e.getReaderStatus())
						status.add("Reader is disconnected");
					lastStatus = e.getReaderStatus();
					publisher.publishEvent(e);
				}
            	
			});
			
				return	new ResponseEntity<>(status,HttpStatus.OK);
	
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="post_name", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>post(){
		try {
			
			
				return	new ResponseEntity<>(postService.setPostName(),HttpStatus.OK);
	
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="party_name", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>party(){
		try {
			
			
				return	new ResponseEntity<>(partyService.setPartyName(),HttpStatus.OK);
	
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="citizen/{id}", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<Citizen>fetch(@PathVariable ("id") Long id){
		try {
			
			
					return new ResponseEntity<>(citizenService.findCitizenNamesById(id),HttpStatus.OK);
		
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="state", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>state(){
		try {
			
			
			return new ResponseEntity<>(delimService.setState(),HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="state/{state}", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>lga(@PathVariable ("state") String state){
		try {
			
		
					return new ResponseEntity<>(delimService.setLga(state.toUpperCase()),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(
			value ="state/{state}/{lga}", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<String>>ward(@PathVariable ("lga") String lga){
		try {
			
		
					return new ResponseEntity<>(delimService.setWard(lga.toUpperCase()),HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


}
