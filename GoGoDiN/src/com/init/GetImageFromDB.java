package com.init;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.member.Model.MemberListVO;

import hibernate.util.BigImportentHibernate;

@WebServlet("/init/getImage")
public class GetImageFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = BigImportentHibernate.getSessionFactory().getCurrentSession();
		OutputStream os = null;
		InputStream is = null;

		try {
			session.beginTransaction();

			String id = request.getParameter("id");
			String type = request.getParameter("type");
			Context context = new InitialContext();
			Query query = null;

			if (type.equalsIgnoreCase("Member")) {
				query = session.createQuery(" from MemberListVO where MemId =" + id);
			}
			List<MemberListVO> list = query.list();

			//https://openhome.cc/Gossip/HibernateGossip/BlobClob.html
			for (MemberListVO aEmp : list) {
				String fileName = aEmp.getFileName();
				is = aEmp.getMemberImage().getBinaryStream();//				
				os = response.getOutputStream();
				int count = 0;
				byte[] bytes = new byte[8192];
				while ((count = is.read(bytes)) != -1) {
					os.write(bytes, 0, count);
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}
