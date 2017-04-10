package com.order.Model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;

import com.login_Model.loginService;

public class OrderService {

	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderDAO();
	}

	// 1.新增
	public OrderVO addOrder(Integer MemId, Integer RestId, Timestamp OrderTime,Integer PNum,	Calendar BidTime) throws SQLException {

		OrderVO odrVO = new OrderVO();
		
		odrVO.setMemId(MemId);
		odrVO.setRestId(RestId);
		odrVO.setOrderTime(OrderTime);
		odrVO.setPNum(PNum);
		odrVO.setBidTime(BidTime);		
		dao.insert(odrVO);		

		return odrVO;
	}

	// 2.修改
	public OrderVO update(Integer OrderNum, Integer MemId, Integer RestId, Timestamp OrderTime,Integer PNum,	Calendar BidTime) throws SQLException {

		OrderVO odrVO = new OrderVO();		

		odrVO.setOrderNum(OrderNum);
		odrVO.setMemId(MemId);
		odrVO.setRestId(RestId);
		odrVO.setOrderTime(OrderTime);
		odrVO.setPNum(PNum);
		odrVO.setBidTime(BidTime);	
		dao.update(odrVO);

		return dao.findByPrimaryKey(OrderNum);
	}	

	// 3.單一查詢
	public OrderVO getOneEmp(Integer OrderNum) {
		return dao.findByPrimaryKey(OrderNum);
	}

	// 4.全部查詢
	public List<OrderVO> getAll() {
		return dao.getAll();
	}
	
	// 5.刪除
		public void delete(Integer OrderNum) {
			dao.delete(OrderNum);
		}
	
	
	
	//以下測試用
	
	/*public static void main(String args[]) throws SQLException {
		//1.新增
		OrderService dao = new OrderService();					
		
		dao.addOrder(1, 1, java.sql.Date.valueOf("2015-05-20"), 4, Calendar.getInstance());	
		
		//2.修改
		dao.update(1, 2, 2, java.sql.Date.valueOf("1986-03-13"), 2, Calendar.getInstance());
		
		//3.單一查詢
		OrderVO odrVO = dao.getOneEmp(1);
		System.out.println(odrVO.getBidTime().getTime());		
		
		//4.全部查詢
		List<OrderVO> list = dao.getAll();
		for (OrderVO aEmp : list) {

			System.out.print(aEmp.getOrderNum() + ",");
			System.out.print(aEmp.getMemId() + ",");
			System.out.print(aEmp.getRestId() + ",");
			System.out.print(aEmp.getOrderTime() + ",");
			System.out.print(aEmp.getPNum() + ",");
			System.out.print(aEmp.getBidTime().getTime() + ",");			
			System.out.println();
		}
		
		//5.刪除
		dao.delete(1);		
	}*/
}
