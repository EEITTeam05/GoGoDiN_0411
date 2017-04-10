package com.member.Model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;

import com.login_Model.loginService;

public class MemberService {

	private Member_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	// 1.新增
	public MemberListVO addMember(String MemAccount, String MemPswd, String MemName, int Sex, Date Birthday,
			String Email, String MlineId, String MemTel, String MemAddr, InputStream is, long sizeInBytes,
			String fileName) throws SQLException {

		MemberListVO memVO = new MemberListVO();

		Blob photo = Hibernate.createBlob(is, sizeInBytes);

		memVO.setMemAccount(MemAccount);
		memVO.setMemPswd(MemPswd);
		memVO.setMemName(MemName);
		memVO.setSex(Sex);
		memVO.setBirthday(Birthday);
		memVO.setEmail(Email);
		memVO.setMlineId(MlineId);
		memVO.setMemTel(MemTel);
		memVO.setMemAddr(MemAddr);
		memVO.setMemberImage(photo);
		memVO.setFileName(fileName);
		dao.insert(memVO);

		loginService logsvc = new loginService();
		logsvc.addNewMember(memVO);

		return memVO;
	}

	// 2.修改
	public MemberListVO update(Integer MemId, String MemAccount, String MemPswd, String MemName, int Sex, Date Birthday,
			String Email, String MlineId, String MemTel, String MemAddr, Blob photo, String fileName) throws SQLException {

		MemberListVO memVO = new MemberListVO();
		
		

		memVO.setMemId(MemId);
		memVO.setMemAccount(MemAccount);
		memVO.setMemPswd(MemPswd);
		memVO.setMemName(MemName);
		memVO.setSex(Sex);
		memVO.setBirthday(Birthday);
		memVO.setEmail(Email);
		memVO.setMlineId(MlineId);
		memVO.setMemTel(MemTel);
		memVO.setMemAddr(MemAddr);
		memVO.setMemberImage(photo);
		memVO.setFileName(fileName);
		dao.update(memVO);

		return dao.findByPrimaryKey(MemId);
	}

	// 3.刪除
	public void delete(Integer MemId) {
		dao.delete(MemId);
	}

	// 4.單一查詢
	public MemberListVO getOneEmp(Integer MemId) {
		return dao.findByPrimaryKey(MemId);
	}

	// 5.全部查詢
	public List<MemberListVO> getAll() {
		return dao.selectAll();
	}
}
