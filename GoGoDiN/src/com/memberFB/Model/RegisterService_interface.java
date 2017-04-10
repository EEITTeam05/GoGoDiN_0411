package com.memberFB.Model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

import com.member.Model.MemberListVO;
import com.member.Model.MemberService;

public interface RegisterService_interface {	
	public boolean idExists(String id) throws IOException, SQLException;
	

}
