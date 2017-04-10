package com.member.Model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import hibernate.util.BigImportentHibernate;

public class MemberDAO implements Member_interface {

	FileInputStream fileInputStream = null;

	@Override
	public void insert(MemberListVO memVO) {

		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(memVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<MemberListVO> selectAll() {

		List<MemberListVO> list = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from MemberListVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public void update(MemberListVO memVO) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(memVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer memId) {
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 【此時多方(宜)可採用HQL刪除】-我比較喜歡的方法XD
			Query query = session.createQuery("delete MemberListVO where memId=?");
			query.setParameter(0, memId);
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
	public MemberListVO findByPrimaryKey(Integer memId) {
		MemberListVO memVO = null;
		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			memVO = (MemberListVO) session.get(MemberListVO.class, memId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return memVO;
	}
}
