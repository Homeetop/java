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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	private String name;
	private String state;
	private String zone;
	private String lga;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name ="post_delimitation",
	joinColumns=@JoinColumn(name="postId"),
	inverseJoinColumns = @JoinColumn(name="delimId"))
	private List<Delimitation> delims;
	
	@OneToMany(mappedBy = "post",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Candidate> cands;
	
	@OneToMany(mappedBy = "post",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Election> elections;
	
	public void addElection(Election election) {
		if (elections ==null) {
			elections = new ArrayList<>();
		}
		elections.add(election);
		election.setPost(this);
	}
	public void addCandidate(Candidate cand) {
		if (cands ==null) {
			cands = new ArrayList<>();
		}
		cands.add(cand);
		cand.setPost(this);
	}
	public void addDelimitation(Delimitation delim) {
		if (delims ==null) {
			delims = new ArrayList<>();
		}
		delims.add(delim);
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public List<Delimitation> getDelims() {
		return delims;
	}
	public void setDelims(List<Delimitation> delims) {
		this.delims = delims;
	}
	public List<Candidate> getCands() {
		return cands;
	}
	public void setCands(List<Candidate> cands) {
		this.cands = cands;
	}
	public List<Election> getElections() {
		return elections;
	}
	public void setElections(List<Election> elections) {
		this.elections = elections;
	}
	public String getLga() {
		return lga;
	}
	public void setLga(String lga) {
		this.lga = lga;
	}
	
	
	
	

}
