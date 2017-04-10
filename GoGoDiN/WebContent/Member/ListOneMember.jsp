<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<body>
	<form enctype="multipart/form-data" method="POST" action="MemberServlet">
	<table border='1' bordercolor='#CCCCFF' width='800'>
	    <tr align='center' valign='middle'>
	        <th>個人照片：</th>
	        <td><img height='160px' width='160px'
					src='${pageContext.servletContext.contextPath}/init/getImage?id=${LoginOK.memId}&type=Member'>
		    </td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>會員編號：</th>
			<td>${LoginOK.memId}</td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>會員帳號：</th>
			<td>${LoginOK.memAccount}</td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>會員密碼：</th>
			<td><input type="text" name="memPswd" size="50" value="${LoginOK.memPswd}" /></td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>會員姓名：</th>
			<td><input type="text" name="memName" size="50" value="${LoginOK.memName}" /></td>
		</tr>
		
		<tr align='center' valign='middle'>
            <th>性別：</th>
		      <c:choose> 
                 <c:when test="${LoginOK.sex == 1 }">                 
                    <td><input type="radio" name="sex" value="1" CHECKED>男 
                        <input type="radio" name="sex" value="0"        >女 </td>                
                 </c:when>             
                 <c:otherwise>                
                    <td><input type="radio" name="sex" value="1"        >男
                        <input type="radio" name="sex" value="0" CHECKED>女 </td>                 
                 </c:otherwise> 
              </c:choose> 
        </tr>          
		
		<tr align='center' valign='middle'>
			<th>生日：</th>
			<td><input type="date" name="birthday" size="50" value="${LoginOK.birthday}" /></td>
		</tr>
				
		<tr align='center' valign='middle'>
			<th>電話：</th>
			<td><input type="text" name="memTel" size="50" value="${LoginOK.memTel}" /></td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>信箱：</th>
			<td><input type="text" name="email" size="50" value="${LoginOK.email}" /></td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>Line：</th>
			<td><input type="text" name="mlineId" size="50" value="${LoginOK.mlineId}" /></td>
		</tr>
		
		<tr align='center' valign='middle'>
			<th>地址：</th>
			<td><input type="text" name="memAddr" size="50" value="${LoginOK.memAddr}" /></td>
		</tr>	
		
		<tr align='center' valign='middle'>
		    <th>更換照片：</th>
		    <td><Input type="file" size="40" name="file1" style="width:300px;"></td>
		</tr>
	</table>
	
	<input type="submit" value="確認送出">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="memId" value="${LoginOK.memId}">
	<input type="hidden" name="memAccount" value="${LoginOK.memAccount}">
	<input type="hidden" name="fileName" value="${LoginOK.fileName}">
	<input type="hidden" name="fileName" value="${LoginOK.fileName}">			
	
	</form>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function (){
    function preview(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('img').attr({'src':e.target.result,'id':'change'});
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("body").on("change", "input[name='file1']", function (){
        preview(this);
    })
    var image1 = $('img');
	if(image1.attr('id')!='change'){
	   	image1.hover(function(){
	   		alert(123);
	   	},function(){
	   		alert(143);
	   	})
	}
})
</script>
</html>