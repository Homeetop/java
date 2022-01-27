package com.topsoft.bvs.controller.dto;

import lombok.ToString;

import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@ToString
@Getter
@Setter
public class PartyRegDto {
	private Long id;
	private String name;
	private String abbreviation;
	@Lob
	private byte[] logo;
	public PartyRegDto() {
		super();

	}
	
	public PartyRegDto(String name, String abbr, byte[] logo) {
		super();
		this.name = name;
		this.abbreviation = abbr;
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

}
