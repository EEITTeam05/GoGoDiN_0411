package com.rest.Model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class RestVO implements Serializable{
	private Integer RestId;
	private String RestName;
	private String RestTel;
	private String Restaddr;
	private Integer Ein;
	private Date RestStart;
	private Date RestEnd;
	private Integer RestRate;
	private Integer RestNum;
	private byte[] RestImage;
	private String fileName;
	public Integer getRestId() {
		return RestId;
	}
	public void setRestId(Integer restId) {
		RestId = restId;
	}
	public String getRestName() {
		return RestName;
	}
	public void setRestName(String restName) {
		RestName = restName;
	}
	public String getRestTel() {
		return RestTel;
	}
	public void setRestTel(String restTel) {
		RestTel = restTel;
	}
	public String getRestaddr() {
		return Restaddr;
	}
	public void setRestaddr(String restaddr) {
		Restaddr = restaddr;
	}
	public Integer getEin() {
		return Ein;
	}
	public void setEin(Integer ein) {
		Ein = ein;
	}
	public Date getRestStart() {
		return RestStart;
	}
	public void setRestStart(Date restStart) {
		RestStart = restStart;
	}
	public Date getRestEnd() {
		return RestEnd;
	}
	public void setRestEnd(Date restEnd) {
		RestEnd = restEnd;
	}
	public Integer getRestRate() {
		return RestRate;
	}
	public void setRestRate(Integer restRate) {
		RestRate = restRate;
	}
	public Integer getRestNum() {
		return RestNum;
	}
	public void setRestNum(Integer restNum) {
		RestNum = restNum;
	}
	public byte[] getRestImage() {
		return RestImage;
	}
	public void setRestImage(byte[] restImage) {
		RestImage = restImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
