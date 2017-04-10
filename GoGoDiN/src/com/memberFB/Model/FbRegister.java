package com.memberFB.Model;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import org.hibernate.Hibernate;
import org.json.simple.parser.JSONParser;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;
import com.member.Model.MemberService;
import com.sun.org.apache.regexp.internal.RE;

import register.model.RegisterServiceDAO;
import register.model.RegisterService_interface;

/**
 * Servlet implementation class FbRegister
 */
@WebServlet("/FbRegister")
public class FbRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FbRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Map<String, String> msgOK = new HashMap<String, String>();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("loginOK", msgOK);
		try {
			// 讀取使用者所輸入，由瀏覽器送來的 EMAIL 欄位內的資料
			String email = request.getParameter("EMAIL");
			String ACCOUNT = request.getParameter("ACCOUNT");
			String PASSWORD = request.getParameter("PASSWORD");
			String LASTNAME = request.getParameter("LASTNAME");
			String FIRSTNAME = request.getParameter("FIRSTNAME");
			String PSWD = request.getParameter("PSWD");
			String GENDER = request.getParameter("GENDER");
			String PICTURE = request.getParameter("PICTURE");
			// 來自addEmp.jsp的請求
			if (GENDER.equals("male")) 
				GENDER = "男";
		    else 
				GENDER = "女";
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
		

			/*************************** 2.開始新增資料 ***************************************/
			Fbservice empSvc = new Fbservice();
			if (empSvc.idExists(email)!=null) {
				MemberFBListVO empVO=empSvc.idExists(email);
				session.setAttribute("fb", empVO);
				RequestDispatcher rd= request.getRequestDispatcher("Search.jsp");
                rd.forward(request, response);
                 return;
			}
			
			
			else if (empSvc.idExists(email)==null){
			MemberFBListVO empVO = new MemberFBListVO();
		
			empVO = empSvc.addEmp(email, ACCOUNT, FIRSTNAME, GENDER, PASSWORD, LASTNAME, PICTURE, PSWD);
             session.setAttribute("fb", empVO);
			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
          
             
 				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
            RequestDispatcher rd= request.getRequestDispatcher("Search.jsp");
                  rd.forward(request, response);
            return;
			}
		 }
			
             
		 catch (Exception e) {
			e.getMessage();
		}
		
		/*************************** 其他可能的錯誤處理 **********************************/
		
		response.getWriter().append(request.getParameter("EMAIL"));
		response.getWriter().append("Served at: ").append(request.getContextPath());

		
		}
}
