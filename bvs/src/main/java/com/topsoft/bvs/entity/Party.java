package com.topsoft.bvs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.ToString;



@ToString
@Entity
public class Party {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String abbreviation;
	private String name;
	
	@Lob
	@Column(name="logo",length = Integer.MAX_VALUE, nullable = true)
	private byte[] logo;
	
	@OneToMany(mappedBy = "party",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Candidate> candidates;
	
	
	
	public Party() {
		super();
	
	}

	public Party(String abbreviation, String name, byte[] logo) {
		super();
		this.abbreviation = abbreviation.toUpperCase();
		this.name = name;
		this.logo = logo;
	}

	public void add(Candidate tempcand) {
		if (candidates ==null) {
			candidates = new ArrayList<>();
		}
		candidates.add(tempcand);
		tempcand.setParty(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	

}
