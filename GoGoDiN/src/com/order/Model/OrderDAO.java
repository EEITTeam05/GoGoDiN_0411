package com.order.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.member.Model.MemberListVO;

import hibernate.util.BigImportentHibernate;

public class OrderDAO implements OrderDAO_interface{
	
	
	
	@Override
	public void insert(OrderVO orderVO) {
		
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		
		try{
			session.beginTransaction();
			session.saveOrUpdate(orderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void update(OrderVO orderVO) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(orderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(Integer orderNum) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 【此時多方(宜)可採用HQL刪除】-我比較喜歡的方法XD
			Query query = session.createQuery("delete OrderVO where orderNum=?");
			query.setParameter(0, orderNum);
			System.out.println("刪除的筆數=" + query.executeUpdate());

			// 【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			// MemberListVO memVO = new MemberListVO();
			// memVO.setMemId(memId);
			// session.delete(memVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public OrderVO findByPrimaryKey(Integer orderNum) {
		OrderVO odrVO = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			odrVO = (OrderVO) session.get(OrderVO.class, orderNum);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return odrVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from OrderVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
}
