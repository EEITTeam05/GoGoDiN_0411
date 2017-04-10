<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>業主註冊</title>
</head>
<body>
<form ENCTYPE="multipart/form-data" method="POST" action="shopregister.do"  id="shopregister.do" >
      <label class="fontSize" >業主帳號：</label>
      <input type="text" name="Account" value="${param.Account}" class="fieldWidth" style="width: 180px;">
      <font size="-1" color="#FF0000">${MsgMap.errorIDEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >業主密碼：</label>
      <input type="password" name="password" value="${param.password}" class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPasswordEmpty}</font>      
      <br/>
      <label class="fontSize" >密碼確認：</label>
      <input type="password" name="password2" value="${param.password2}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorPassword2Empty}</font>            
      <br/>
      <label class="fontSize" >業主姓名：</label>
      <input type="text" name="name" value="${param.name}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorName}</font>
      <br/>
       <label class="fontSize" >業主電話：</label>
      <input type="text"  name="tel" value="${param.tel}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorTel}</font>
      <br/>
      <label class="fontSize" >身分證號：</label>
      <input type="text"  name="Idd" value="${param.Idd}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorIdd} </font><font color="green" size="-1"></font>
      <br/>
      <label class="fontSize" >電子郵件：</label>
          <input type="text"  name="eMail" value="${param.eMail}"   class="fieldWidth" style="width: 200px;">
          <font color="red" size="-1">${MsgMap.errorEmail}</font>
      <br/>
      <label class="fontSize" >LineID：</label>
          <input type="text"  name="SLineID" value="${param.SLineID}"   class="fieldWidth" style="width: 200px;">
          <font color="red" size="-1"></font>
      <br/>
      <label class="fontSize" >業主地址：</label>
      <input type="text" name="address" value="${param.address}"  class="fieldWidth" style="width: 320px;">
      <font color="red" size="-1">${MsgMap.errorAddr}</font>
      <br/> 
      <div id="btnArea" align="left">
         <input type="submit" name="submit" id="submit" value="儲存"/>
         <input type="reset" name="cancel" id="cancel" value="重填">
      </div>
      <br/>
</form>
</body>
</html>