package com.topsoft.bvs.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DelimRegDto {
	
	private String state;
	private String lga;
	private String ward;
	private Long id;
	
	public DelimRegDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DelimRegDto(String state, String lga, String ward) {
		super();
		this.state = state;
		this.lga = lga;
		this.ward = ward;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	


}
