package com.member.Model;

import java.io.InputStream;
import java.util.List;

public interface Member_interface {
	
	 public void insert(MemberListVO memVO);
	 public void update(MemberListVO memVO);
     public void delete(Integer memId);
     public MemberListVO findByPrimaryKey(Integer memId);
	 public List<MemberListVO> selectAll();
	
	 
	

}
