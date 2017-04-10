package com.shop.Model;

import java.sql.SQLException;
import java.util.List;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;

public class ShopService {
	private ShopDAO_interface dao;
	
	public ShopService() {
		dao = new ShopDAO();
	}
	
	//新增
	public ShopVO addShop(String ShopAccount, String ShopPswd, String ShopName,
			String ShopIdd, String ShopTel, String ShopEmail, String slineId) throws SQLException {
		ShopVO shopVO = new ShopVO();
		
		shopVO.setShopAccount(ShopAccount);
		shopVO.setShopPswd(ShopPswd);
		shopVO.setShopName(ShopName);
		shopVO.setShopIdd(ShopIdd);
		shopVO.setShopTel(ShopTel);
		shopVO.setShopEmail(ShopEmail);
		shopVO.setSlineId(slineId);
		dao.insert(shopVO);
		loginService ls = new loginService();
		ls.addNewShop(shopVO);
		return shopVO;
	}
	
	//修改
	public ShopVO update(Integer ShopId, String ShopAccount, String ShopPswd, String ShopName, String ShopIdd, String ShopTel, String ShopEmail, String slineId) {
		ShopVO shopVO = new ShopVO();
		
		
		shopVO.setShopId(ShopId);
		shopVO.setShopAccount(ShopAccount);
		shopVO.setShopPswd(ShopPswd);
		shopVO.setShopName(ShopName);
		shopVO.setShopIdd(ShopIdd);
		shopVO.setShopTel(ShopTel);
		shopVO.setShopEmail(ShopEmail);
		shopVO.setSlineId(slineId);
		dao.update(shopVO);
		
		return dao.findByPrimaryKey(ShopId);
		
	}
	
	//刪除
	public void delete(Integer ShopId) {
		dao.delete(ShopId);
	}
	
	//單一查詢
	public ShopVO getoneshop(Integer ShopId) {
		return dao.findByPrimaryKey(ShopId);
	}
	
	//查全部
	public List<ShopVO> getAll() {
		return dao.getAll();
	}
	
	//以下測試用
//	public static void main(String args[]) throws SQLException {
		//新增
//		ShopService dao = new ShopService();
//		dao.addShop("Test2", "123456", "SFLAI", "1234567890", "987654", "AAA@yahoo.com.tw", "WUGOD");
		
		//刪除
//		dao.delete(4);
		
//		ShopVO shopVO = dao.getoneshop(3);
//		System.out.println(shopVO.getShopId());
		
		//更新
//		dao.update(3, "ttt429", "ttt446", "測試2", "0123456789", "23618524", "AAA@yahoo.com.tw", "cba");
		
		
		
//		List<ShopVO> list = dao.getAll();
//		for (ShopVO aEmp : list) {
//
//			System.out.print(aEmp.getShopId() + ",");
//			System.out.print(aEmp.getShopAccount() + ",");
//			System.out.print(aEmp.getShopPswd() + ",");
//			System.out.print(aEmp.getShopName() + ",");
//			System.out.print(aEmp.getShopIdd() + ",");
//			System.out.print(aEmp.getShopTel() + ",");
//			System.out.print(aEmp.getShopEmail() + ",");
//			System.out.print(aEmp.getSlineId() + ",");
//			System.out.println();
//		}
		
//	}
	
}
