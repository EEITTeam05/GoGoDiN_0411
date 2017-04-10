package com.shop.Model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("getOneUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//接收請求
				Integer shopId = new Integer(request.getParameter("shopId"));
				
				//開始查詢資料
				ShopService sSvc = new ShopService();
				ShopVO shopVO = sSvc.getoneshop(shopId);
				
				//查詢完成，準備轉交
				request.setAttribute("shopVO", shopVO);
				String url = "/Shop/ListOneShop.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/Shop/ShopList.jsp");
				rd.forward(request, response);
			}
		}
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer shopId = new Integer(request.getParameter("shopId").trim());
				String shopAcc = request.getParameter("shopAcc").trim();
				String shopPswd = request.getParameter("shopPswd").trim();
				String shopName = request.getParameter("shopName").trim();
				String shopIdd = request.getParameter("shopIdd").trim();
				String shopTel = request.getParameter("shopTel").trim();
				String shopMail = request.getParameter("shopMail").trim();
				String shopLine = request.getParameter("shopLine").trim();
				System.out.println(shopId);
				
				ShopVO shopVO = new ShopVO();
				shopVO.setShopId(shopId);
				shopVO.setShopAccount(shopAcc);
				shopVO.setShopPswd(shopPswd);
				shopVO.setShopName(shopName);
				shopVO.setShopIdd(shopIdd);
				shopVO.setShopTel(shopTel);
				shopVO.setShopEmail(shopMail);
				shopVO.setSlineId(shopLine);
				
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("shopVO", shopVO);
					RequestDispatcher rd = request.getRequestDispatcher("/Shop/ShopList.jsp");
					rd.forward(request, response);
					return;
				}
				ShopService sSvc = new ShopService();
				shopVO = sSvc.update(shopId, shopAcc, shopPswd, shopName, shopIdd, shopTel, shopMail, shopLine);
				
				//修改完成，準備轉交
				request.setAttribute("shopVO", shopVO);
				String url = "/Shop/ListOneShop.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/Shop/ListOneShop.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
