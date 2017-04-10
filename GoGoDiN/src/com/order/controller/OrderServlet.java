package com.order.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.Model.OrderService;
import com.order.Model.OrderVO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (session.getAttribute("LoginOK") != null) {
//System.out.println("Success!");
			
			Map<String, String> errorMsgs = new HashMap<String, String>();
			
			try {
				Integer memId = new Integer(request.getParameter("memId").trim());
				Integer restId = new Integer(request.getParameter("restId").trim());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp orderTime = Timestamp.valueOf(request.getParameter("orderTime"));
//System.out.println(orderTime);
				Integer pNum = new Integer(request.getParameter("pNum").trim());
				java.util.Calendar bidTime = Calendar.getInstance();
				
				OrderService oSvc = new OrderService();
				OrderVO orderVO = oSvc.addOrder(memId, restId, orderTime, pNum, bidTime);
				
				session.setAttribute("OrderOK", orderVO);
				String url = "/Order/OrderOK.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.put("下訂失敗", e.getMessage());
//System.out.println("下訂失敗");
//e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/Order/Order.jsp");
				rd.forward(request, response);
			}
		} else {
//			response.sendRedirect("index.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
