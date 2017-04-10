<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberList</title>
</head>
<body>
<a href="index.jsp">回首頁</a>
<hr>

	<h1>登入成功!</h1>
	<%-- 	<c:if test="${! empty LoginOK }"> --%>
	<!-- 		<img height='40px' width='30px' -->
	<%-- 			src='${pageContext.servletContext.contextPath}/init/getImage?id=${LoginOK.memId}&type=Member'> --%>
	<%-- 	</c:if> --%>

	<c:if test="${! empty LoginOK }">
		<table border='1' bordercolor='#CCCCFF' width='800'>
			<tr>
				<th>會員編號</th>
				<th>會員帳號</th>
				<th>會員姓名</th>
				<th>性別</th>
				<th>生日</th>
				<th>信箱</th>
				<th>Line帳號</th>
				<th>電話</th>
				<th>地址</th>
				<th>照片</th>
			</tr>
			<tr align='center' valign='middle'>
				<td>${LoginOK.memId}</td>
				<td>${LoginOK.memAccount}</td>
				<td>${LoginOK.memName}</td>
				<td>${LoginOK.sex}</td>
				<td>${LoginOK.birthday}</td>
				<td>${LoginOK.email}</td>
				<td>${LoginOK.mlineId}</td>
				<td>${LoginOK.memTel}</td>
				<td>${LoginOK.memAddr}</td>
				<td><img height='40px' width='30px'
					src='${pageContext.servletContext.contextPath}/init/getImage?id=${LoginOK.memId}&type=Member'>
				</td>
				
				<td>
					<form method="post" action="MemberServlet">
						<input type="submit" value="修改" >
						<input type="hidden" name="memId" value="${LoginOK.memId }" >
						<input type="hidden" name="action" value="getOneUpdate" >
					</form>
				</td>

			</tr>

		</table>
	</c:if>



</body>
</html>