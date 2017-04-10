package com.init;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import com.rest.Model.RestVO;

@WebServlet("/Search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		Connection con =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String url = "jdbc:sqlserver://192.168.1.43:1433;DatabaseName=GoGoDiN";
		String query = "select * from RestTable where RestName like ? "
			   + "union select * from RestTable where Restaddr like ? order by RestId desc";
		String search = request.getParameter("search");
		if(!search.trim().isEmpty())
			search = "%" + search + "%";
		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			con = DriverManager.getConnection(url,"sa","sa123456");
			pstm = con.prepareStatement(query);
			pstm.setString(1, search);
			pstm.setString(2, search);
			rs = pstm.executeQuery();
				List<Map<String,String>> list1 = new LinkedList<>();
				while(rs.next()) {
					Map<String, String> m1 = new HashMap<>();
					m1.put("RestId", rs.getString(1));
					m1.put("RestName", rs.getString(2));
					m1.put("RestTel", rs.getString(3));
					m1.put("RestAddr", rs.getString(4));
					m1.put("RestRate", rs.getString(8));
					m1.put("RestNum", rs.getString(9));
					m1.put("RestImg", Base64.getEncoder().encodeToString(rs.getBytes(10)));
					list1.add(m1);
				}
			String jsonString = JSONValue.toJSONString(list1);
			out.print(jsonString);
		}catch (Exception e) {
			out.println("Error:" + e.getMessage());
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
