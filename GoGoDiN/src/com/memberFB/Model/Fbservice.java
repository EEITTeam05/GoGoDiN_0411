package com.memberFB.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.member.Model.MemberListVO;

public class Fbservice {
	private FbDAO dao;

	public Fbservice() {
		dao = new FbDAO();
	}

	public MemberFBListVO addEmp(String Email, String ACCOUNT, String FIRSTNAME, String GENDER, String PASSWORD,
			String LASTNAME, String PICTURE, String PSWD) {

		MemberFBListVO empVO = new MemberFBListVO();

		empVO.setEmail(Email);
		empVO.setAccount(ACCOUNT);
		empVO.setFirstname(FIRSTNAME);
		empVO.setPicture(PICTURE);
		empVO.setPswd(PSWD);
		empVO.setGender(GENDER);
		empVO.setLastname(LASTNAME);
		empVO.setPassword(PASSWORD);
		dao.insert(empVO);

		return empVO;
	}

	synchronized public  MemberFBListVO idExists(String email) throws IOException, SQLException {
		FbDAO da = new FbDAO();
	
		List<MemberFBListVO> list = da.selectAll();
		for (MemberFBListVO me : list) {
			if (me.getEmail().equals(email.trim())) {
				
				return me;				
			}
		}

		return null;
	}
	
	
}