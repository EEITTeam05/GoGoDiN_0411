package com.shop.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import hibernate.util.BigImportentHibernate;

public class ShopDAO implements ShopDAO_interface {

	@Override
	public void insert(ShopVO shopVO) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();

		try {
			
			session.beginTransaction();
			session.saveOrUpdate(shopVO);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void update(ShopVO shopVO) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(shopVO);
			session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void delete(Integer shopId) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("DELETE ShopVO WHERE shopId = ?");
			query.setParameter(0, shopId);
			System.out.println("刪除 = " + query.executeUpdate() + "筆");
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public ShopVO findByPrimaryKey(Integer shopId) {
		ShopVO shopVO = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		
		try {
				session.beginTransaction();
				shopVO = (ShopVO) session.get(ShopVO.class, shopId);
				session.getTransaction().commit();
			
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return shopVO;
	}

	@Override
	public List<ShopVO> getAll() {
		List<ShopVO> list = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM ShopVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

}
