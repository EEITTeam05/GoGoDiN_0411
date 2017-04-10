package com.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Hibernate;

import com.login_Model.loginService;
import com.member.Model.MemberListVO;
import com.member.Model.MemberService;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, // 超過file-size-threshold的檔案request將會以臨時暫存的方式存到硬碟中，預設為0
		maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {	
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	Integer MemId;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		if ("getOneUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求
				MemId = new Integer(request.getParameter("memId"));

				// 開始查詢資料
				MemberService msc = new MemberService();
				MemberListVO memVO = msc.getOneEmp(MemId);

				// 查詢完成，準備轉交
				session.setAttribute("LoginOK", memVO);
				String url = "/Member/ListOneMember.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/Member/MemberList.jsp");
				rd.forward(request, response);
			}
		}

///////////////////////// 按下修改畫面導入ListOneMember.jsp後把資料改一改後再按下送出就會網下跑啦!///////////////////////////////////////////

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			String fileName = "";
			long sizeInBytes = 0;
			InputStream is = null;

			Collection<Part> parts = request.getParts();
			if (parts != null) { // parts裡有東西
				for (Part p : parts) {
					if (p.getContentType() != null) {
						fileName = getFileName(p);
						if (fileName != null && fileName.trim().length() > 0) {// 判斷檔案名稱
							sizeInBytes = p.getSize();// 能執行到這邊代表要寫入的資料就是圖片,為了取出圖片大小,故上面先宣告一個sizeInBytes放著
							is = p.getInputStream();// 能執行到這邊代表要寫入的資料就是圖片,為了準備寫入資料庫,故上面先宣告一個is放著
						} else {
							MemberService msc = new MemberService();
							MemberListVO memVO = msc.getOneEmp(MemId);
							fileName = memVO.getFileName();
							try {
								sizeInBytes = memVO.getMemberImage().length();
								is = memVO.getMemberImage().getBinaryStream();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}

			try {
				Integer memId = new Integer(request.getParameter("memId").trim());
				String memAccount = request.getParameter("memAccount").trim();
				String memPswd = request.getParameter("memPswd").trim();
				String memName = request.getParameter("memName").trim();
				Integer sex = new Integer(request.getParameter("sex").trim());

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(request.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				String memTel = request.getParameter("memTel").trim();
				String email = request.getParameter("email").trim();
				String mlineId = request.getParameter("mlineId").trim();
				String memAddr = request.getParameter("memAddr").trim();

				@SuppressWarnings("deprecation")
				Blob photo = Hibernate.createBlob(is, sizeInBytes);

				MemberListVO memVO = new MemberListVO();
				memVO.setMemId(memId);
				memVO.setMemAccount(memAccount);
				memVO.setMemPswd(memPswd);
				memVO.setMemName(memName);
				memVO.setSex(sex);
				memVO.setBirthday(birthday);
				memVO.setMemTel(memTel);
				memVO.setEmail(email);
				memVO.setMlineId(mlineId);
				memVO.setMemAddr(memAddr);
				memVO.setMemberImage(photo);
				memVO.setFileName(fileName);

				if (!errorMsgs.isEmpty()) {
					session.setAttribute("LoginOK", memVO);
					RequestDispatcher rd = request.getRequestDispatcher("/Member/MemberList.jsp");
					rd.forward(request, response);
					return;
				}

				MemberService msc = new MemberService();				

				memVO = msc.update(memId, memAccount, memPswd, memName, sex, birthday, email, mlineId, memTel, memAddr,
						photo, fileName);			

				// 修改完成，準備轉交
				session.setAttribute("LoginOK", memVO);
				loginService lsc = new loginService();
				lsc.populateMemberList();
				String url = "/Member/MemberList.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);

			}catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/Member/ListOneMember.jsp");
				rd.forward(request, response);
			}
		}
	}
}
