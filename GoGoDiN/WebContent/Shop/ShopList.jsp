<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShopList</title>
</head>
<body>
<h1>登入成功!</h1>
	<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
	<%-- 	<c:if test="${! empty LoginOK }"> --%>
	<!-- 		<img height='40px' width='30px' -->
	<%-- 			src='${pageContext.servletContext.contextPath}/init/getImage?id=${LoginOK.memId}&type=Member'> --%>
	<%-- 	</c:if> --%>

	<c:if test="${! empty ShopLoginOK }">
		<table border='1' bordercolor='#CCCCFF' width='800'>
			<tr>
				<th>業主編號</th>
				<th>業主帳號</th>
				<th>業主姓名</th>
				<th>身分證字號</th>
				<th>電話</th>
				<th>信箱</th>
				<th>Line帳號</th>
			</tr>
			<tr align='center' valign='middle'>
				<td>${ShopLoginOK.shopId}</td>
				<td>${ShopLoginOK.shopAccount}</td>
				<td>${ShopLoginOK.shopName}</td>
				<td>${ShopLoginOK.shopIdd}</td>
				<td>${ShopLoginOK.shopTel}</td>
				<td>${ShopLoginOK.shopEmail}</td>
				<td>${ShopLoginOK.slineId}</td>
				
				<td>
					<form method="post" action="ShopServlet">
						<input type="submit" value="修改" >
						<input type="hidden" name="shopId" value="${ShopLoginOK.shopId }" >
						<input type="hidden" name="action" value="getOneUpdate" >
					</form>
				</td>

			</tr>

		</table>
	</c:if>
</body>
</html>