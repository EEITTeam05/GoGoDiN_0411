package register.controller;

import java.io.IOException;
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

import com.shop.Model.ShopService;

import register.model.RegisterServiceDAO;
import register.model.RegisterService_interface;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, // 超過file-size-threshold的檔案request將會以臨時暫存的方式存到硬碟中，預設為0
		maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/Shop/shopregister.do")
public class ShopRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsg = new HashMap<>();
		Map<String, String> msgOK = new HashMap<>();
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg);
		session.setAttribute("MsgOK", msgOK);
		String Account = "";
		String password = "";
		String password2 = "";
		String name = "";
		String tel = "";
		String Idd = "";
		String email = "";
		String LineID = "";
		String address = "";

		Collection<Part> parts = request.getParts();
		if (parts != null) { // parts裡有東西
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if (p.getContentType() == null) {
					if ("Account".equalsIgnoreCase(fldName)) {
						Account = value;
					} else if ("password".equalsIgnoreCase(fldName)) {
						password = value;
					} else if ("password2".equalsIgnoreCase(fldName)) {
						password2 = value;
					} else if ("name".equalsIgnoreCase(fldName)) {
						name = value;
					} else if ("idd".equalsIgnoreCase(fldName)) {
						Idd = value.toUpperCase();
					} else if ("tel".equalsIgnoreCase(fldName)) {
						tel = value;
					} else if ("eMail".equalsIgnoreCase(fldName)) {
						email = value;
					} else if ("SLineID".equalsIgnoreCase(fldName)) {
						LineID = value;
					} else if ("address".equalsIgnoreCase(fldName)) {
						address = value;
					}
				}
			}
			if (Account == null || Account.trim().length() == 0)
				errorMsg.put("errorIDEmpty", "帳號欄必須輸入");
			if (password == null || password.trim().length() == 0)
				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
			if (password2 == null || password2.trim().length() == 0)
				errorMsg.put("errorPassword2Empty", "密碼確認欄必須輸入");
			if (password.trim().length() > 0 && password2.trim().length() > 0) {
				if (!password.trim().equals(password2)) {
					errorMsg.put("errorPassword2Empty", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}
			if(Idd==null||Idd.trim().length()==0)
				errorMsg.put("errorIdd", "請輸入身分證字號");
			else if(Idd.length()!=10 && Idd.length()>0)
				errorMsg.put("errorIdd", "身份證字數錯誤");
			  else if(Idd.length()!=0){				  
				  for(int i=0;i<=25;i++){						
						if(Idd.charAt(0)!=((char)(65+i)))
							errorMsg.put("errorIdd", "格式錯誤");
						 break;
				  }
				  
			}         				
			int count=0; int[]abc=new int[10]; 
			  if(Idd.length()==10){
				  int i,j;   char num='1',num2='2'; 
							  for(i=1;i<=9;i++){
									for(j=0;j<=9;j++){			
										
								     if(Idd.substring(i, i+1).equals(String.valueOf(j)))
								    	 if(Idd.charAt(1)==num||Idd.charAt(1)==num2)
								     { 
								     count++;
								     break;}
								     else
									 errorMsg.put("errorIdd","錯了");								     
						             }									
				             }
							 if(count!=9)
							 { errorMsg.put("errorIdd","錯了");}
			                  }  
			  //if(Idd.charAt(0)>='A'&& Idd.charAt(1)>=1&&Idd.charAt(1)>=2) 
			  int[] rdd={10,11,12,13,14,15,16,17,34,18,19,20,21,22,35,23,24,25,26,27,28,29,32,30,31,33};
			  int sum=0;
			if(count==9){
			
				  for(int i=1;i<=9;i++){
					 abc[i]=(Idd.charAt(i)-'0');
				  }
				     abc[0]=rdd[Idd.charAt(0)-'A'];
				     sum=((int)abc[0]/10+(abc[0]%10)*9);
				    
				   for(int i=0;i<8;i++){
					   sum+=abc[i+1]*(8-i);
				   } 
				   if(10-sum%10==abc[9])
					   System.out.println("身分證格式沒問題唷^^");			   
				   else
					   errorMsg.put("errorIdd","檢查碼錯誤");
				  }			
			
			
			
			if (name == null || name.trim().length() == 0)
				errorMsg.put("errorName", "姓名欄必須輸入");
			if (address == null || address.trim().length() == 0)
				errorMsg.put("errorAddr", "地址欄必須輸入");
			if (email == null || email.trim().length() == 0)
				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			if (tel == null || tel.trim().length() == 0)
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			
			// 如果有錯誤
			if (!errorMsg.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				return;
			}
			try {
				RegisterService_interface rs = new RegisterServiceDAO();
				if (rs.idExists(Account)) {
					errorMsg.put("errorIDDup", "此帳號已存在，請選擇新帳號");
				} else {
					ShopService shopServ = new ShopService();
					shopServ.addShop(Account, password, name,Idd, tel,  email, LineID);
					response.sendRedirect("../index.jsp");
					session.setAttribute("LoginOK", shopServ);
					return;
				}
				if (!errorMsg.isEmpty()) {
					// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("errorIDDup", e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			}
			
		}
	}
}
