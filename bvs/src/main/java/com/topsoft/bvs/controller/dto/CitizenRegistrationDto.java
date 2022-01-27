package com.topsoft.bvs.controller.dto;


import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;




public class CitizenRegistrationDto {
	private String firstName;
	private String lastName;
	private String sex;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String email;
	private String occupation;
	private String mobile;
	private String address;
	private String state;
	private String lga;
	private String ward;

	
	

	public CitizenRegistrationDto() {
		super();
	}

	public CitizenRegistrationDto(String firstName, String lastName, String sex, Date dob, String email,
			String occupation, String mobile, String address, String state, String lga, String ward) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.dob = dob;
		this.email = email;
		this.occupation = occupation;
		this.mobile = mobile;
		this.address = address;
		this.state = state;
		this.lga = lga;
		this.ward = ward;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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



}
