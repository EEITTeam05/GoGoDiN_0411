package com.member.Model;

import java.io.Serializable;
import java.sql.Blob;

public class MemberListVO implements Serializable{
	
	private Integer MemId ;
	private String  MemAccount;
	private String  MemPswd;
	private String  MemName;
	private Integer Sex;
	private java.sql.Date Birthday;
	private String  Email;
	private String  MlineId;
	private String  MemTel;
	private String  MemAddr;
	private Blob MemberImage;
	private String fileName;

	
	
	public Integer getMemId() {
		return MemId;
	}
	public void setMemId(Integer memId) {
		MemId = memId;
	}
	public String getMemAccount() {
		return MemAccount;
	}
	public void setMemAccount(String memAccount) {
		MemAccount = memAccount;
	}
	public String getMemPswd() {
		return MemPswd;
	}
	public void setMemPswd(String memPswd) {
		MemPswd = memPswd;
	}
	public String getMemName() {
		return MemName;
	}
	public void setMemName(String memName) {
		MemName = memName;
	}
	public Integer getSex() {
		return Sex;
	}
	public void setSex(Integer sex) {
		Sex = sex;
	}
	public java.sql.Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(java.sql.Date birthday) {
		Birthday = birthday;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}	
	public String getMlineId() {
		return MlineId;
	}
	public void setMlineId(String mlineId) {
		MlineId = mlineId;
	}
	public String getMemTel() {
		return MemTel;
	}
	public void setMemTel(String memTel) {
		MemTel = memTel;
	}
	public String getMemAddr() {
		return MemAddr;
	}
	public void setMemAddr(String memAddr) {
		MemAddr = memAddr;
	}
	public Blob getMemberImage() {
		return MemberImage;
	}
	public void setMemberImage(Blob memberImage) {
		MemberImage = memberImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	

}
