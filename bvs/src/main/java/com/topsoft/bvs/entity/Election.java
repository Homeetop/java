package com.topsoft.bvs.entity;

import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name ="election")
public class Election {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	private int numOfRegisteredVoters = 0;
	private int numOfvoters = 0;
	private  int numOfcandidates=0;
	private LocalDate created;
	private String postName;
	private boolean status = false;
	
	@ManyToOne(
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="post_id")
	private Post post;

	@ManyToMany
	@JoinTable(name = "citizen_election",
	joinColumns=@JoinColumn(name="electionId"),
	inverseJoinColumns = @JoinColumn(name="citizenId"))
	private List<Citizen> citizens ;
	
	@OneToMany(mappedBy = "election",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE})
	private List<Vote> vote;
	
	@OneToOne(fetch=FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinColumn(name ="result_id")
	private Result result;
	
	//Constructor
	public Election() {
		super();
		this.created = LocalDate.now();
		result = new Result();
		this.setResult(result);
		
	}
	
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	//add citizen and citizen getter and setter
	public void add(Citizen citizen) {
		if (citizens ==null) {
			citizens = new ArrayList<>();
		}
		citizens.add(citizen);
		
	}
	public List<Citizen> getCitizens() {
		return citizens;
	}
	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	
	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}

	public int getNumOfRegisteredVoters() {
		return numOfRegisteredVoters;
	}
	public void setNumOfRegisteredVoters(int numOfRegisteredVoters) {
		this.numOfRegisteredVoters = numOfRegisteredVoters;
	}
	public int getNumOfvoters() {
		return numOfvoters;
	}
	public void setNumOfvoters(int numOfvoters) {
		this.numOfvoters = numOfvoters;
	}
	
	public int getNumOfcandidates() {
		return numOfcandidates;
	}
	public void setNumOfcandidates(int numOfcandidates) {
		this.numOfcandidates = numOfcandidates;
		
	}
	
	public LocalDate getCreated() {
		return created;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void updateList(Citizen citizen) {
		List<Citizen> citizs = this.getCitizens();
		citizs.remove(citizen);
		this.setCitizens(citizs);
	}
	
}
