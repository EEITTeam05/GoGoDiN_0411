package register.controller;

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

import com.member.Model.MemberListVO;
import com.member.Model.MemberService;

import register.model.RegisterServiceDAO;
import register.model.RegisterService_interface;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, // 超過file-size-threshold的檔案request將會以臨時暫存的方式存到硬碟中，預設為0
		maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/Member/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> errorMsg = new HashMap<String, String>();

		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = request.getSession();

		request.setAttribute("MsgMap", errorMsg);

		session.setAttribute("MsgOK", msgOK);

		String Account = "";
		String password = "";
		String password2 = "";
		String name = "";
		int sex = 1;
		Date bday = null;
		String address = "";
		String tel = "";
		String email = "";
		String LineID = "";
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;

		Collection<Part> parts = request.getParts();
		if (parts != null) { // parts裡有東西
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				if (p.getContentType() == null) {
					if (fldName.equals("Account")) {// 如果從parts裡取出的name等於"mid"->(register.jsp)
						Account = value;// 就讓上面宣告的memberID變數等於使用者輸入的內容,下面的道理一樣唷~舉一反三吧!
						// equals是要完全一樣唷,equalsIgnoreCase忽略大小寫,只要同個英文字就好!
					} else if (fldName.equals("password")) {
						password = value;
					} else if (fldName.equalsIgnoreCase("password2")) {
						password2 = value;
					} else if (fldName.equalsIgnoreCase("name")) {
						name = value;
					} else if (fldName.equalsIgnoreCase("sex")) {
						sex = Integer.parseInt(value);
					} else if (fldName.equalsIgnoreCase("bday")) {
						bday = Date.valueOf(value);
					} else if (fldName.equalsIgnoreCase("address")) {
						address = value;
					} else if (fldName.equalsIgnoreCase("tel")) {
						tel = value;
					} else if (fldName.equalsIgnoreCase("email")) {
						email = value;
					} else if (fldName.equalsIgnoreCase("mlineid")) {
						LineID = value;
					}
				} else {// 代表不是null就是使用者要輸入欄位的類型是檔案,不是一般輸入的文字數字唷!
					fileName = getFileName(p);
					
					if (fileName != null && fileName.trim().length() > 0) {// 判斷檔案名稱
						sizeInBytes = p.getSize();// 能執行到這邊代表要寫入的資料就是圖片,為了取出圖片大小,故上面先宣告一個sizeInBytes放著
						is = p.getInputStream();// 能執行到這邊代表要寫入的資料就是圖片,為了準備寫入資料庫,故上面先宣告一個is放著
					} else {// 代表檔案名稱檢查完得到的不是一個圖片檔,就會跑來這邊
						errorMsg.put("errPicture", "必須挑選圖片檔");// 顯示要使用者挑選圖片檔
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
			if (name == null || name.trim().length() == 0) {
				errorMsg.put("errorName", "姓名欄必須輸入");
			}
			if (address == null || address.trim().length() == 0) {
				errorMsg.put("errorAddr", "地址欄必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			}
			if (tel == null || tel.trim().length() == 0) {
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			}
			if (bday == null) {
				errorMsg.put("errorbday", "生日欄必須輸入");
			}
		}
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
				MemberService mb = new MemberService();
				MemberListVO ml = mb.addMember(Account, password, name, sex, bday, email, LineID, tel, address, is,
						sizeInBytes, fileName);
				msgOK.put("InsertOK", "<Font color='red'>新增成功,請開始使用本系統</Font>");
				response.sendRedirect("../index.jsp");// 並請導向首頁!
				session.setAttribute("LoginOK", ml);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}

	}

	public static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
