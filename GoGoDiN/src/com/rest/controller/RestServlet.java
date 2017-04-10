package com.rest.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.rest.Model.RestService;
import com.rest.Model.RestVO;

import register.controller.RegisterServlet;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, // 超過file-size-threshold的檔案request將會以臨時暫存的方式存到硬碟中，預設為0
maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)

@WebServlet("/Shop/publish.do")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsg = new HashMap<String, String>();

		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = request.getSession();

		request.setAttribute("MsgMap", errorMsg);

		session.setAttribute("MsgOK", msgOK);
		
		String RestName = "";
		String RestTel = "";
		String RestAddr = "";
		Integer Ein = 0;
		Date RestStartDay = null;
		Date RestEndDay = null;
		Integer RestNum = 0;
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;
		
		Collection<Part> parts = request.getParts();
		if(parts!=null){
			for(Part p :parts){
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if(p.getContentType() == null){
					if(fldName.equals("RestName")){
						RestName = value;
					}else if(fldName.equals("RestTel")){
						RestTel  = value;
					}else if(fldName.equals("RestAddr")){
						RestAddr = value;
					}else if(fldName.equals("Ein")){
						try{
							Ein = Integer.parseInt(value);
						}catch (Exception e) {
							errorMsg.put("errorEinEmpty", "統一編號欄格式錯誤");
						}
					}else if(fldName.equals("StartDay")){
						try{
							RestStartDay = Date.valueOf(value);
						}catch (Exception e) {
							errorMsg.put("errorStartDay", "刊登日期欄格式錯誤");
						}
					}else if(fldName.equals("EndDay")){
						try{
						RestEndDay = Date.valueOf(value);
						}catch (Exception e) {
							errorMsg.put("errorEndDay", "截止日期欄格式錯誤");
						}
					}else if(fldName.equals("RestNum")){
						try{
						RestNum = Integer.parseInt(value);
						}catch (Exception e) {
							errorMsg.put("errorRestNum", "刊登人數欄格式錯誤");
						}
					}
				}else{
					fileName = RegisterServlet.getFileName(p);
					if(fileName !=null && fileName.trim().length() > 0){
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}else{
						errorMsg.put("errPicture", "必須挑選圖片檔");// 顯示要使用者挑選圖片檔
					}
					
				}
			}
			if(RestName == null || RestName.trim().length()==0)
				errorMsg.put("errorRestNameEmpty", "餐廳名稱欄必須輸入");
			if(RestTel==null||RestTel.trim().length()==0)
				errorMsg.put("errorRestTel", "餐廳電話欄必須輸入");
			if(RestAddr==null||RestAddr.trim().length()==0)
				errorMsg.put("errorRestAddr", "餐廳地址欄必須輸入");
			if(!errorMsg.isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("Publish.jsp");
				rd.forward(request, response);
				return;
			}
			
		}
		try{
			RestService rserv = new RestService();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    int length = 0 ;
		    byte buffer[] = new byte[512];
		    while( (length = is.read(buffer)) != -1){
	            //從緩衝區讀取buffer裡面0~length-1的位置
	            baos.write(buffer, 0, length);
		    }
		    byte[] data = baos.toByteArray();
			RestVO rv = rserv.addRest(RestName, RestTel, RestAddr, Ein, RestStartDay, RestEndDay, 80, RestNum, data , fileName);
			response.sendRedirect("../index.jsp");// 並請導向首頁!
			session.setAttribute("LoginOK", rv);
			return;
		}catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

}
