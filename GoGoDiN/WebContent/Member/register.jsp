<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form enctype="multipart/form-data" method="POST" action="register.do"  id="register.do" >
      <label class="fontSize" >帳號：</label>
      <input type="text" name="Account" value="${param.Account}" class="fieldWidth" style="width: 180px;">
      <!-- 
         注意value屬性的內容以及顯示錯誤訊息的寫法
      -->
      <font size="-1" color="#FF0000">${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >密碼：</label>
      <input type="password" name="password" value="${param.password}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPasswordEmpty}</font>      
      <br/>
      
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="password2" value="${param.password2}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPassword2Empty}</font>            
      <br/>
      
      <label class="fontSize" >姓名：</label>
      <input type="text" name="name" value="${param.name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>
      <label class="fontSize" >性別：</label>
      <input type="radio" name="sex" value="1" CHECKED>男
      <input type="radio" name="sex" value="0">女
      <font color="red" size="-1">${MsgMap.errorSex}</font>
      <br/>
      <label for="bookdate">生日</label>
      <input type="date" name="bday" id="bookdate" value="2017-03-22">
      <font color="red" size="-1">${MsgMap.errorbday}</font>
      <br/>
      <label class="fontSize" >地址：</label>
      <input type="text" name="address" value="${param.address}"  class="fieldWidth" style="width: 320px;">
      <font color="red" size="-1">${MsgMap.errorAddr}</font>
      <br/>
      
      <label class="fontSize" >電話：</label>
      <input type="text"  name="tel" value="${param.tel}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>
      
      <label class="fontSize" >電子郵件：</label>
          <input type="text"  name="eMail" value="${param.eMail}"   class="fieldWidth" style="width: 200px;">
          <font color="red" size="-1">${MsgMap.errorEmail}</font>
      <br/>
      
       <label class="fontSize" >LineID：</label>
          <input type="text"  name="MLineID" value="${param.MLineID}"   class="fieldWidth" style="width: 200px;">
          <font color="red" size="-1"></font>
      <br/>
      
      <label class="fontSize" >照片：</label>
      <Input type="file" size="40" class="fieldWidth" style="width: 480px;"  name="file1"><BR>
      <font color="red" size="-1">${MsgMap.errPicture}</font>
      <br/>
      <div id="btnArea" align="left">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</body>
</html>