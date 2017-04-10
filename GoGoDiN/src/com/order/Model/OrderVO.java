package com.order.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class OrderVO {
	private Integer OrderNum;
	private Integer MemId;
	private Integer RestId;
	private Timestamp OrderTime;
	private Integer PNum;
	private Calendar BidTime;
	

	public Integer getOrderNum() {
		return OrderNum;
	}
	public void setOrderNum(Integer orderNum) {
		OrderNum = orderNum;
	}
	public Integer getMemId() {
		return MemId;
	}
	public void setMemId(Integer memId) {
		MemId = memId;
	}	
	public Integer getRestId() {
		return RestId;
	}
	public void setRestId(Integer restId) {
		RestId = restId;
	}
	public Timestamp getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		OrderTime = orderTime;
	}
	public Integer getPNum() {
		return PNum;
	}
	public void setPNum(Integer pNum) {
		PNum = pNum;
	}
	public Calendar getBidTime() {
		return BidTime;
	}
	public void setBidTime(Calendar bidTime) {
		BidTime = bidTime;
	}
	
	
}
