package com.login_Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.Model.MemberListVO;
import com.member.Model.MemberService;
import com.shop.Model.ShopService;
import com.shop.Model.ShopVO;

public class loginService implements login_interface {

	private static List<MemberListVO> memberlist = new ArrayList<>();
	private static List<ShopVO> shoplist = new ArrayList<>();

	// 建構子
	public loginService() throws SQLException {
		if (memberlist.isEmpty()) {
			if(shoplist.isEmpty()){
				this.populateShopList();
			}
			this.populateMemberList();
		}
	}

	@Override
	public void populateMemberList() throws SQLException {
		MemberService dao = new MemberService();
		memberlist = dao.getAll();
	}

	public void populateShopList() throws SQLException {
		ShopService dao = new ShopService();
		shoplist = dao.getAll();
	}

	@Override
	public MemberListVO checkIDPassword(String userId, String password) {
		for (MemberListVO mb : memberlist) {
			if (mb.getMemAccount().trim().equals(userId) && mb.getMemPswd().trim().equals(password)) {
				return mb;
			}
		}
		return null;
	}

	public ShopVO checkShopIDPassword(String userId, String password) {
		for (ShopVO sb : shoplist) {
			if (sb.getShopAccount().trim().equals(userId) && sb.getShopPswd().trim().equals(password)) {
				return sb;
			}
		}
		return null;
	}

	@Override
	public List<MemberListVO> getMemberList() {
		return memberlist;
	}

	public List<ShopVO> getShopList() {
		return shoplist;
	}

	@Override
	public void addNewMember(MemberListVO mb) {
		memberlist.add(mb);
	}

	public void addNewShop(ShopVO sb) {
		shoplist.add(sb);
	}

}
