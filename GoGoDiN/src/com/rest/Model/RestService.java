package com.rest.Model;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


public class RestService {
	private Rest_interface dao;
	
	public RestService(){
		dao = new RestDAO();
	}
	
	public RestVO addRest(String RestName,String RestTel,String RestAddr,Integer Ein,Date RestStart,Date RestEnd,
			Integer RestRate,Integer RestNum, byte[] data,String fileName) throws IOException{
		
		RestVO restVO = new RestVO();
	    restVO.setRestName(RestName);
		restVO.setRestTel(RestTel);
		restVO.setRestaddr(RestAddr);
		restVO.setEin(Ein);
		restVO.setRestStart(RestStart);
		restVO.setRestEnd(RestEnd);
		restVO.setRestRate(RestRate);
		restVO.setRestNum(RestNum);
		restVO.setRestImage(data);
		restVO.setFileName(fileName);
		
		dao.insert(restVO);
		return restVO;
	}
	
	public RestVO update(Integer RestId , String RestName,String RestTel,String RestAddr,Integer Ein,Date RestStart,Date RestEnd,
			Integer RestRate,Integer RestNum,byte[] data,String fileName){
		RestVO restVO = new RestVO();

		restVO.setRestName(RestName);
		restVO.setRestTel(RestTel);
		restVO.setRestaddr(RestAddr);
		restVO.setEin(Ein);
		restVO.setRestStart(RestStart);
		restVO.setRestEnd(RestEnd);
		restVO.setRestRate(RestRate);
		restVO.setRestNum(RestNum);
		restVO.setRestImage(data);
		restVO.setFileName(fileName);
		dao.update(restVO);
		return dao.findByPrimaryKey(RestId);
	}
	
	public void delete(Integer RestId){
		dao.delete(RestId);
	}
	
	public RestVO getOneRest(Integer RestId){
		return dao.findByPrimaryKey(RestId);
	}
	
	public List<RestVO> getAllRest(){
		return dao.selectAll();
	}
}

