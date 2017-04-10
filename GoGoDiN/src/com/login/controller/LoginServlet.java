package com.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;
import com.shop.Model.ShopVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static Random random = new Random();

	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();// 準備一個字串記憶體來放隨機產生的字元(char)
		for (int i = 0; i < 6; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]).toString();// 產生六個隨機字元並放置buffer裡
		}
		return buffer.toString();
	}

	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));// 隨機產生RGB顏色
	}

	public static Color getRandomColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());// 產生相反的顏色
	}

	static String randomString;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		HttpSession session = request.getSession();

		randomString = getRandomString();// 取得隨機字串;
		System.out.println(randomString);
		session.setAttribute("randomString", randomString);

		int width = 100;
		int height = 50;

		Color color = getRandomColor();
		Color reverse = getRandomColor(color);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// 準備一個圖片記憶體空間
		Graphics2D g = bi.createGraphics();// 在記憶體空間內創建繪圖物件
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));// 詭異的設定字體大小方法...
		g.setColor(color);// 設定顏色(隨機產生的)
		g.fillRect(0, 0, width, height);// 設定背景大小(X座標,Y座標,寬度,高度)
		g.setColor(reverse);// 設定相反顏色
		g.drawString(randomString, 10, 30);// 在圖片上設定字串(x,y)座標位置
		for (int i = 0, n = random.nextInt(70); i <= n; i++) {
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1); // 亂數產生0~70個干擾小點點
		}

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);// 王者歸來的範例用的code已經無法使用....方法已下架
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);

		String userId = request.getParameter("userId");
		String pswd = request.getParameter("pswd");
		String word = request.getParameter("word").toUpperCase();
		String re = request.getParameter("rememberMe");

		if (userId == null || userId.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		if (pswd == null || pswd.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		if (word == null || word.trim().length() == 0) {
			errorMsgMap.put("WordEmptyError", "驗證碼必須輸入");
		}
		if (!word.equals(randomString) && word.trim().length() != 0) {
			errorMsgMap.put("WordEnterError", "驗證碼輸入錯誤");
		}
		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		Cookie username = null;
		Cookie password = null;
		Cookie remberme = null;
		if (re != null) {
			username = new Cookie("username", userId);
			username.setMaxAge(30 * 30 * 60);
			username.setPath(request.getContextPath());

			password = new Cookie("pswd", pswd);
			password.setMaxAge(30 * 30 * 60);
			password.setPath(request.getContextPath());

			remberme = new Cookie("re", "true");
			remberme.setMaxAge(30 * 30 * 60);
			remberme.setPath(request.getContextPath());

		} else {
			username = new Cookie("username", userId);
			username.setMaxAge(0);
			username.setPath(request.getContextPath());

			password = new Cookie("pswd", pswd);
			password.setMaxAge(0);
			password.setPath(request.getContextPath());

			remberme = new Cookie("re", "false");
			remberme.setMaxAge(30 * 60 * 60);
			remberme.setPath(request.getContextPath());
		}
		response.addCookie(username);
		response.addCookie(password);
		response.addCookie(remberme);
		
		
		loginService logs;
		MemberListVO mb = null;
		ShopVO sb = null;

		try {
			logs = new loginService();
			mb = logs.checkIDPassword(userId, pswd);
			sb = logs.checkShopIDPassword(userId, pswd);
			if (mb != null) {
				session.setAttribute("LoginOK", mb);
			} else if (sb != null) {
				session.setAttribute("ShopLoginOK", sb);
			} else {
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = null;

		// 如果errorMsgMap不是空的，表示有錯誤，交棒給index.jsp
		if (!errorMsgMap.isEmpty()) {
			rd = request.getRequestDispatcher("index.jsp");
		} else if (mb != null) {
			rd = request.getRequestDispatcher("/Member/MemberList.jsp");
		} else if (sb != null) {
			rd = request.getRequestDispatcher("/Shop/ShopList.jsp");
		}
		rd.forward(request, response);
		return;
	}

}
