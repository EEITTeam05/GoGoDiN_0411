package com.memberFB.Model;

import java.io.Serializable;
import java.sql.Blob;

public class MemberFBListVO implements Serializable{
	private String MemId;
	
	private String account ;
	private String password;
	private String email;
	private String lastname ;
	private String firstname;
	private String pswd;
	private String gender;
	private String picture;
	
	
	
	
	
	public String getMemId() {
		return MemId;
	}
	public void setMemId(String memId) {
		MemId = memId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
	

}
