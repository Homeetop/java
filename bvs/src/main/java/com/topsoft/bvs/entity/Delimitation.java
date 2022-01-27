package com.topsoft.bvs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.ToString;

@ToString
@Entity
public class Delimitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String state;
	private String lga;
	private String ward;
	
	@OneToMany(mappedBy = "delim",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Citizen> citizens;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name ="post_delimitation",
	joinColumns=@JoinColumn(name="delimId"),
	inverseJoinColumns = @JoinColumn(name="postId"))
	private List<Post> posts;
	
	
	public Delimitation() {
		super();

	}
	
	public void add(Citizen tempcitizen) {
		if (citizens ==null) {
			citizens = new ArrayList<>();
		}
		citizens.add(tempcitizen);
		tempcitizen.setDelim(this);
	}
	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List<Citizen> getCitizens() {
		return citizens;
	}
	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}
	
	

}
