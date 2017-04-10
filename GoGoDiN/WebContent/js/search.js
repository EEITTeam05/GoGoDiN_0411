		var xhr;
		var btn = document.getElementById("buttonLoad");
		btn.addEventListener("click", callback);
		function load() {
			//呼叫LoadRSS Servlet
			xhr = new XMLHttpRequest();
			if(xhr!=null){
				xhr.open('get','Search.do');
				xhr.send();
				xhr.addEventListener('load',callback);
			}else{
				alert("你的瀏覽器不支援ajax功能!!");
			}
		}
		
		var myDiv = document.getElementById('div1');
		function callback(){
			if(xhr.status==200){
				while(myDiv.hasChildNodes()){
 			    	myDiv.removeChild(myDiv.lastChild);
 			    }
				var doc = xhr.responseXML;
				var items = doc.getElementsByTagName("item");
				for(var i = 0;i<items.length;i++){
					var itemtitle = items[i].childNodes[0].firstChild.nodeValue;
					var itemdesc = items[i].childNodes[1].firstChild.nodeValue;
					var itemlink = items[i].childNodes[2].firstChild.nodeValue;
					var itemtime = items[i].childNodes[3].firstChild.nodeValue;

					
					var eleDiv1 = document.createElement("div");

					var eleH3 = document.createElement("a");
					eleH3.className = 'lead';
					eleH3.href=itemlink;
					var txtH3 = document.createTextNode(itemtitle);
					eleH3.appendChild(txtH3);

				
					
					var hr1 = document.createElement("hr");
					eleH3.appendChild(hr1);

					var eletime = document.createElement('small');
					var txttime = document.createTextNode(formatdate(itemtime));
					eletime.id='txttimeid';
					eletime.appendChild(txttime);

					var imglink = document.createElement("a");
					imglink.href=itemlink;
					var greenarrow =document.createElement('img');
					greenarrow.src='http://i.imgur.com/SIh1ymH.png';
					greenarrow.title = '移至完整文章';
					imglink.appendChild(greenarrow);
					
					
					var eledesc = document.createElement('p');
					eledesc.id='txtdescId';
					var txtdesc = document.createTextNode(itemdesc);
					
					eledesc.appendChild(txtdesc);

					myDiv.appendChild(eleH3);
					myDiv.appendChild(eletime);
					myDiv.appendChild(imglink);
					myDiv.appendChild(eledesc);
					myDiv.appendChild(document.createElement('br'));
				}
			}
		}
		function formatdate(time2){
			var date = new Date(time2);
			var year1 = date.getFullYear();
			var month1 = date.getMonth()+1;
			var date1 = date.getDate();
			var hour1 = ('0'+ date.getHours()).substr(-2);
			
			if(date.getHours() > 12){
				hour1 = ('0' + (date.getHours()-12)).substr(-2);
			}
			
			var min1 = ('0' + date.getMinutes()).substr(-2);
			var sec1 = ('0' + date.getSeconds()).substr(-2);
			var str = year1+'年'+ month1 +'月' + date1 +'日, 下午 '+hour1+":"+min1+":"+sec1;

			if(date.getHours()>=12){
			   	 	return str;
				}else{
					return str.replace('下午','上午');
			}
		}