package com.login_Model;

import java.sql.SQLException;
import java.util.List;

import com.member.Model.MemberListVO;

public interface login_interface {
	public void populateMemberList() throws SQLException;
	public MemberListVO checkIDPassword(String userId,String password);
	public List<MemberListVO> getMemberList();
	public void addNewMember(MemberListVO mb);
}
