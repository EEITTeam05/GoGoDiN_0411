package com.memberFB.Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.member.Model.MemberListVO;
import com.shop.Model.ShopVO;

import hibernate.util.BigImportentHibernate;

public class FbDAO {
	
	
	
	public void insert(MemberFBListVO empVO) {

		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(empVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} finally {
			sessionFactory.close();
		}

	}
	
	public List<MemberFBListVO> selectAll() {

		List<MemberFBListVO> list = null;
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberFBListVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		finally {
			sessionFactory.close();
		}
		return list;	
	}
	public MemberFBListVO findByPrimaryKey(Integer memId) {
		MemberFBListVO memVO = null;
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			memVO = (MemberFBListVO) session.get(MemberFBListVO.class, memId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		finally {
			sessionFactory.close();
		}
		return memVO;
	}
	
	

	}

