package com.topsoft.bvs.entity;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import lombok.ToString;


@ToString
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", 
discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("norm")
public class Citizen extends User{
	
	
	@Column(name ="vin", nullable =true)
	private String voterId;
	@Column(name="sex", nullable =true)
	private String sex;
	@Column(name="dob", nullable=true)
	private Date dob;
	@Column(name="email", nullable = true)
	private String email;
	@Column(name="occupation", nullable = true)
	private String occupation;
	@Column(name="mobile",nullable =true)
	private String mobile;
	@Column(name="address", nullable=true)
	private String address;
	
	@ManyToOne(
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="delimitation_id")
	private Delimitation delim;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name ="citizen_election",
	joinColumns=@JoinColumn(name="citizenId"),
	inverseJoinColumns = @JoinColumn(name="electionId"))
	private List<Election> elections;
	
	@OneToMany(mappedBy = "citizen",
			fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
			CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Vote> vote;
	
	public Citizen() {
		super();
		this.setRole(Roles.USER.toString());
	}
	



	public List<Vote> getVote() {
		return vote;
	}




	public void setVote(List<Vote> vote) {
		this.vote = vote;
	}




	public Delimitation getDelim() {
		return delim;
	}



	public void setDelim(Delimitation delim) {
		this.delim = delim;
	}



	@Override
	public void setRole(String role) {
		
		super.setRole(role);
	}



	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Election> getElections() {
		return elections;
	}

	public void setElections(List<Election> elections) {
		this.elections = elections;
	}

	

	

	
	



	
}
