package com.topsoft.bvs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Result {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "result")
	private Election election;
	
	@OneToMany(
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name ="result_id")
	private List<Candidate> cands;
	private String winner;
	private Long totalNumOfVotes; 

	
	
	//Getter and Setter
	
	public Long getId() {
		return id;
	}
	

	
	public List<Candidate> getCands() {
		return cands;
	}

	public void setCands(List<Candidate> cands) {
		this.cands = cands;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Election getElection() {
		return election;
	}



	public void setElection(Election election) {
		this.election = election;
	}



	public String getWinner() {
		return winner;
	}



	public void setWinner(String winner) {
		this.winner = winner;
	}



	public Long getTotalNumOfVotes() {
		return totalNumOfVotes;
	}



	public void setTotalNumOfVotes(Long totalNumOfVotes) {
		this.totalNumOfVotes = totalNumOfVotes;
	}

}
