<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刊登</title>
</head>
<body>
<form enctype="multipart/form-data" method="POST" action="publish.do"  id="publish.do" >
      <label class="fontSize" >餐廳名稱：</label>
      <input type="text" name="RestName" value="${param.RestName}" class="fieldWidth" style="width: 180px;">
      <font size="-1" color="#FF0000">${MsgMap.errorRestNameEmpty}${MsgMap.errorIDDup}</font>
      <br/>
      <label class="fontSize" >餐廳電話：</label>
      <input type="text"  name="RestTel" value="${param.RestTel}"    class="fieldWidth" style="width: 120px;">
      <font color="red" size="-1">${MsgMap.errorRestTel}</font>
      <br/>
  	  <label class="fontSize" >餐廳地址：</label>
      <input type="text" name="RestAddr" value="${param.RestAddr}"  class="fieldWidth" style="width: 320px;">
      <font color="red" size="-1">${MsgMap.errorRestAddr}</font>
      <br/>
      <label class="fontSize" >統一編號：</label>
      <input type="text" name="Ein" value="${param.Ein}"   class="fieldWidth" style="width: 200px;">
      <font color="red" size="-1">${MsgMap.errorEinEmpty}</font>            
      <br/>
      <label for="bookdate">刊登日期：</label>
      <input type="date" name="StartDay" id="StartDay" value="2017-03-22">
      <font color="red" size="-1">${MsgMap.errorStartDay}</font>
      <br/>
      <label for="bookdate">截止日期：</label>
      <input type="date" name="EndDay" id="EndDay" value="2017-03-23">
      <font color="red" size="-1">${MsgMap.errorEndDay}</font>
      <br/>
      <label class="fontSize" >刊登人數：</label>
      <input type="text" name="RestNum" value="${param.RestNum}"  class="fieldWidth" style="width: 180px;">
      <font color="red" size="-1">${MsgMap.errorRestNum}</font>
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