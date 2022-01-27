package com.topsoft.bvs.entity;



import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.ToString;



@ToString
@Entity
@DiscriminatorColumn(name="type", 
discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("candidate")
public class Candidate extends Citizen{
	
	private String qualification;
	
	@ManyToOne(
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="post_id")
	private Post post;
	
	@ManyToOne(
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="party_id")
	private Party party;
	
	@OneToMany(mappedBy = "candidate",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Vote> vote;
	
	
	private long numOfVote;
	

	public List<Vote> getVote() {
		return vote;
	}

	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public long getNumOfVote() {
		return numOfVote;
	}

	public void setNumOfVote(long numOfVote) {
		this.numOfVote = numOfVote;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}



	
	

}
