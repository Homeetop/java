package com.topsoft.bvs.controller.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CandidateRegDto {
	
	private Long id;
	private String qualification;
	private String post;
	private String party;
	
	
	
	public CandidateRegDto() {
		super();

	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}



	public String getPost() {
		return post;
	}



	public void setPost(String post) {
		this.post = post;
	}



	public String getParty() {
		return party;
	}



	public void setParty(String party) {
		this.party = party;
	}







	



	

}
