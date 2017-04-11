package com.login_Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.member.Model.MemberListVO;
import com.member.Model.MemberService;
import com.shop.Model.ShopService;
import com.shop.Model.ShopVO;

public class loginService implements login_interface {
	private static Map<String, MemberListVO> membermap = new HashMap<>();
	private static Map<String, ShopVO> shopmap = new HashMap<>();
	// 建構子
	public loginService() throws SQLException {
		if (membermap.isEmpty()) {
			if(shopmap.isEmpty()){
				this.populateShopList();
			}
			this.populateMemberList();
		}
	}

	@Override
	public void populateMemberList() throws SQLException {
		MemberService dao = new MemberService();
		for(MemberListVO mbvo : dao.getAll()){
			membermap.put(mbvo.getMemAccount().toLowerCase(), mbvo);
		}
	}

	public void populateShopList() throws SQLException {
		ShopService dao = new ShopService();
		for(ShopVO spvo : dao.getAll()){
			shopmap.put(spvo.getShopAccount().toLowerCase(), spvo);
		}
	}

	@Override
	public MemberListVO checkIDPassword(String userId, String password) {
		if(membermap.containsKey(userId.toLowerCase())
			&& password.equals(membermap.get(userId).getMemPswd())){
				return membermap.get(userId.toLowerCase());
			}
		return null;
	}

	public ShopVO checkShopIDPassword(String userId, String password) {
		if(shopmap.containsKey(userId.toLowerCase()) 
			&& password.equals(shopmap.get(userId.toLowerCase()).getShopPswd())){
				return shopmap.get(userId.toLowerCase());
		}
		return null;
	}

	@Override
	public Map<String,MemberListVO> getMemberList() {
		return membermap;
	}

	public Map<String,ShopVO> getShopList() {
		return shopmap;
	}

	@Override
	public void addNewMember(MemberListVO mb) {
		membermap.put(mb.getMemAccount().toLowerCase(), mb);
	}

	public void addNewShop(ShopVO sb) {
		shopmap.put(sb.getShopAccount().toLowerCase(), sb);
	}
	
	public void updateMember(MemberListVO mb){
		membermap.replace(mb.getMemAccount().toLowerCase(), mb);
	}
	
	public void updateShop(ShopVO sb){
		shopmap.replace(sb.getShopAccount().toLowerCase(), sb);
	}

}
