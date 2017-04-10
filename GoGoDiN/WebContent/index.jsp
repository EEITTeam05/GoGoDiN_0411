<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
  <meta property="og:url"           content="http://localhost:8081/GoGoDiN/Shop/Publish.jsp" />
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="Your Website Title" />
  <meta property="og:description"   content="Your description" />
  <meta property="og:image"         content="http://www.your-domain.com/path/image.jpg" />
<script type="text/javascript">
	function reloadImage() {
		document.getElementById('btn').disabled = true;
		document.getElementById('identity').src = 'login.do?ts='
				+ new Date().getTime();
	}
//=====================================================================================	
	
	function statusChangeCallback(response) {
	    console.log('statusChangeCallback');
	    console.log(response);	  
	    if (response.status === 'connected') {
	    	
	    	//window.location= "https://graph.facebook.com/me?fields=id,name,email&access_token="+response.authResponse.accessToken
	    	
	      testAPI();
	    } else if (response.status === 'not_authorized') {
	     
	      document.getElementById('status').innerHTML = 'Please log ' +
	        'into this app.';
	    } else {
	      
	      document.getElementById('status').innerHTML = 'Please log ' +
	        'into Facebook.';
	    }
	  }	  
	  function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	      statusChangeCallback(response);             	    	      
	    },true);
	  }
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '220210138456016',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v2.8'
	    });
	    FB.AppEvents.logPageView();
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "//connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));
	  
	  var count=0;
	  function testAPI() {		    
		    FB.api('/me', {"fields":"email,last_name,first_name,id,gender,picture,name"},function(response) {
		    	$('#status').text('登入者lastＮame ：'+response.last_name);		      	
				console.log('Successful login for: '+ response.name);
				console.log(JSON.stringify(response));		   
				document.getElementById('status').innerHTML = '歡迎您的登入'+response.first_name+ response.last_name + '!';
				
				var datas = JSON.stringify(response) ;
				var info = JSON.parse(datas);
				console.log(info);
				
				 ajaxPost(response.email,response.last_name,response.first_name,response.gender,response.picture.data.url);					
					$('#img').prop("src",response.picture.data.url);
					
					
			});
		    function ajaxPost(email,lastname,firstname,gender,picture){
		  	 $.ajax({
		    		type:'post',
		         	url:'FbRegister',
                data:{'EMAIL':email ,'ACCOUNT':'1','PASSWORD':'PASSWORD','LASTNAME':lastname,'FIRSTNAME':firstname,'PSWD':'PSWD','GENDER':gender,'PICTURE':picture},
	    	   success:function(data){
	    			alert('登入成功');
	    			 window.location.href = "Search.jsp";
	    			}
		    	});
		    }
	  	
// 		    $.post("FbRegister",{"EMAIL":email},
//    	    		 function(data){
//    	   			 alert('個資已記錄2');
//    	    		 });
   
		   
   (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

   $('#fb-share-button').click(function() {
	    FB.ui({
	          method: 'share',
	          link: "http://localhost:8081/GoGoDiN/Shop/Publish.jsp", 
	          picture: 'The picture url',
	          name: "The name who will be displayed on the post",
	          description: "The description who will be displayed"
	        }, function(response){
	            console.log(response);
	        }
	    );
	});
		    	   
			     	
		     
		          
		      
<%-- 		    <%    --%>
// 		    request.getSession(); 
// 		    session.setAttribute("fbok", a); 		    
<%-- 		    %> --%>
// 		    window.location="http://localhost:8081/GoGoDiN/Shop/ShopList.jsp";	
// 		    });
		  }
	 
	
	</script>
	

<title>登入</title>
</head>
<body>
	<a href='Member/register.jsp'>Register</a>
	<a href='Shop/register.jsp'>Shop Register</a>
	<a href='Shop/Publish.jsp'>刊登</a>
	<a href='Search.jsp'>Search</a>
	<hr>
	<form action="login.do" method="post" name="loginForm">
		<div>
			<input type="text" placeholder="account" size="14" name="userId" value="${cookie['username'].value}">
			  <small>
			     <Font color='red'size='-3'>${ErrorMsgKey.AccountEmptyError}</Font>
			  </small>			  
			  <br> 			  
			  <input type="password" placeholder="password" size="14" name="pswd" value="${cookie['pswd'].value}">			  
			  <small>
			     <font color='red' size='-3'>${ErrorMsgKey.PasswordEmptyError}</font>
			  </small>			  
			  <br> 		
			  	  
			  <input type="checkbox" name="rememberMe"
			     <c:if test="${cookie['re'].value==true}">
			     
			     checked='checked'
			     
			     </c:if>
			  value="true">
			  			  
			  <small>記住密碼</small>			  
			  <br>			  
			  <input type="submit" value="Login" style="width: 100px">			  
			  <small>
			     <font color='red' size='-1'>${ErrorMsgKey.LoginError}</font>
			  </small>			
		</div>
		
		<input type="text" placeholder="驗證碼" size="14" name="word" style="margin-top:5px" value='${param.word}'>
		<small>
		   <font color='red' size='-3'>${ErrorMsgKey.WordEmptyError}</font>
		</small>	  
	</form>
	
	<img src="login.do" id='identity' onload="btn.disabled=false;" style="margin-top:5px" />	
	<input type=button value="換個圖片" id="btn" onclick="reloadImage()">		
    <small>
		   <font color='red' size='-3'>${ErrorMsgKey.WordEnterError}</font>
    </small>	
    
    <div id="fb-root"></div>
<div class="fb-share-button" data-href="http://localhost:8081/GoGoDiN/Shop/Publish.jsp" data-layout="box_count" data-size="small" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8081%2FGoGoDiN%2Findex.jsp&amp;src=sdkpreparse">分享</a></div>

<fb:login-button  size="xlarge" data-auto-logout-link="false" data-max-rows scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>



<img id="img" src=""></img>

<div id="status">
</div>
</body>
</html>