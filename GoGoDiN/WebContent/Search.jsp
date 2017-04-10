<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Base64"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GoGoDiN</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-beta.20/css/uikit.min.css" />
<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="css/asPieProgress.min.css">
</head>
<script type="text/javascript">
	function Enter(event){
		if(event.keyCode==13){
			geneator();
			$('#txtSearch').blur();
			return false;
		}
	}
</script>
<body>
<div style= "float:right;padding:70px"><img src="${fb.picture}"/><p>${fb.firstname}<br/>歡迎來到GoGoDiN</div>
	<div class="container">
		<!-- Jumbotron -->
		<div class="jumbotron">
			<div class="page-header">
				<h3>
					搜尋餐廳 <small>AutoComplete</small>
				</h3>
			</div>
			<!-- 每頁不同的內容從這裡開始 -->

			<form name="myData" method="get">
				<div class="input-group">
					<input type="text" class="form-control" id="txtSearch" onkeydown="return Enter(event)" name="keyword"
					 autocomplete="off" placeholder="搜尋想找的餐廳   例如:牛排">
      				<span class="input-group-btn">
        			<button class="btn btn-default" type="button" id="buttonLoad" name="action" value="findRest">Go!</button>
      			</span>
				</div>
				
			</form>
				<div id="div1"></div>


			<!-- 每頁不同的內容到這裡結束 -->
				</div>
			<div id="div2" class="row">
			
			</div>
	
		<!-- Site footer -->
		<footer class="footer">
			<p>&copy; GoGoDiN</p>
		</footer>

	</div>
	<!-- /container -->


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-beta.20/js/uikit.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
<script src="js/jquery-mark-min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA86fMRXUcXBspzmQgFZ0QTrxumNTvAz-I" async defer></script>
<script src="js/jquery.tinyMap.min.js"></script>
<script src="js/jquery-asPieProgress.min.js"></script>
<script type="text/javascript" src="js/jquery.googlemap.js"></script>


	<script>
		var show;
		$(function(){
			txt = $('#txtSearch');
			txt.on('keyup',getData);
			show = $('#div1');
			$('#buttonLoad').on('click',geneator);
		})

		function getData() {
			$.getJSON('Search.do',{'search':txt.val()},function(datas){
				show.css('display',"block");
				if(datas.length>0){	
					var eleDiv = $('<div></div>');
				if (show.children().length > 0) {
					show.children().remove();
				}	
				var docFrag = $(document.createDocumentFragment());
					for (var j = 0, max = datas.length; j < max; j++) {
							var txth4 = $('<h5></h5>').addClass('list-group-item-heading');	 //搜尋店名
							var str = $('<strong></strong>').text(datas[j].RestName).appendTo(txth4);
						    txth4.mark(txt.val());			  
							var addp = $('<span></span>').text(datas[j].RestAddr)
														.addClass('list-group-item-text');//搜尋-地址
							//var mark = $('<mark></mark>');
							addp.mark(txt.val());

							var badgeRate = $('<span></span>').text("評分：" + datas[j].RestRate)
														      .addClass('badge')//搜尋-評分			
														      
							var eleBtn = $('<button></button').addClass('list-group-item')
															  .attr('type','button')
															  .append(txth4)
															  .append(addp)
															  .append(badgeRate);
														
							eleBtn.on('click',function() {
									txt.val($(this).children('h5').text());
									show.css('display','none');
									geneator();
							});
							
							docFrag.append(eleBtn);
						}
						show.append(docFrag);
				}
				if (datas.length == 0&&show.children().length > 0) {
						show.children().remove();
				}
				
			})
		}
		//var myDiv = document.getElementById('div2');
		var myDiv = $('#div2')
		
		function geneator(){
			$.getJSON('Search.do',{'search':txt.val()},function(list){
						if (show.children().length > 0) {
							show.children().remove();
						}
						while (myDiv.children().length > 0) {
							myDiv.children().remove();
						}
					var docFrag = $(document.createDocumentFragment());
					for(var j=0,max=list.length;j<max;j++){
							var eleDiv00 = $('<div></div>')
										   .addClass('col-sm-3 col-md-4')
										   .css({'width':'390px','height':'462px'});
							var eleDivH = $('<div></div>').addClass('uk-box-shadow-small uk-box-shadow-hover-large').appendTo(eleDiv00);
							var eleDiv0 = $('<a></a>').addClass('thumbnail').attr('data-id',list[j].RestId);
							var photo = $('<img></img>').attr({
											'src':"data:image/jpeg;base64," + list[j].RestImg ,
											'alt':list[j].RestName
										}).appendTo(eleDiv0);
							var eleDiv1 = $('<div></div>').addClass('caption');
							var eleH3 = $('<h3></h3>').text(list[j].RestName).appendTo(eleDiv1);
							$('<small></small>').text(" 評分：" + list[j].RestRate).appendTo(eleH3);					
							$('<p></p>').text(list[j].RestAddr).appendTo(eleDiv1);
							var elep2 = $('<p></p>').appendTo(eleDiv1);
							var space = $('')
							$('<a></a>').addClass('btn btn-primary')
												 .attr({
													'href':'#',
													'role':'button'
												 }).text("詳情").appendTo(elep2);
							elep2.html(elep2.html()+"&nbsp");
							var heart = $('<span></span>').addClass('glyphicon glyphicon-heart')
							$('<a></a>').addClass('btn btn-default')
												 .attr({
													 'href':'#',
													 'role':'button'
												 }).text("收藏").prepend(heart).appendTo(elep2);
							eleDiv0.append(eleDiv1).appendTo(eleDivH);
							docFrag.append(eleDiv00);
							}
							myDiv.append(docFrag);
						})
					};
		myDiv.on('click','a.thumbnail',function(){
				var dataid = $(this).attr('data-id');
				
				$.getJSON('Search2.do',{'search': dataid },function(datas){
						myDiv.children().remove();
						var mdDiv = $('<div></div>').addClass('class="col-12 col-md-auto offset-md-2 bd-content').appendTo(myDiv);
						var imgDiv = $('<div></div>').addClass('card mb-3').appendTo(mdDiv);
						$('<img />').addClass('card-img-top')
										.attr({
											'src':"data:image/jpeg;base64," + datas.RestImg,
											'alt':datas.RestName,
											'width':'832px'
										}).appendTo(imgDiv);
						//$('#bigDiv').css('margin-botton',0);
						var blockDiv = $('<div></div>').addClass('card-block').appendTo(imgDiv);
						$('<span></span>').addClass('uk-text-lead').text(datas.RestName).appendTo(blockDiv);

						var ratepie = $('<div></div>').addClass('pie_progress').attr({
							'role':'progressbar',
							'data-goal':datas.RestRate
						}).appendTo(blockDiv);
						$('<div></div>').addClass('pie_progress__number').text(datas.RestRate + "%").appendTo(ratepie);
						$('<div></div>').addClass('pie_progress__label').text("評分").appendTo(ratepie);
					
						
						$('<address></address>').text(datas.RestAddr).appendTo(blockDiv);
						$('<input />').attr({'type':'button'}).addClass('btn btn-primary float-right').val('我要預訂').appendTo(blockDiv);
						$('<a></a>').attr({'id':'back'}).addClass('uk-button uk-button-text').text('回上頁').appendTo(blockDiv);
						var first = $('<div></div>').addClass('panel panel-default').appendTo(mdDiv);
						$('<div></div>').addClass('panel-heading').text("訂位專區").appendTo(first);
						$('<div></div>').addClass('panel-body').text("一般訂位").appendTo(first);
						$('<div></div>').addClass('panel-heading').text("餐廳地址").appendTo(first);
						var MapAddr = $('<div></div>').addClass('panel-body').text(datas.RestAddr).appendTo(first);
						$('<div></div>').attr("id","map").css({'width':'800px','height':'300px'})
						.tinyMap({
							'center':datas.RestAddr,
							'zoom':17,
							'marker':[{
								'addr':datas.RestAddr
							}]
						}).appendTo(MapAddr);
						$('#back').click(function(){
							geneator();
						})
				})
			})
			$(document).on('keydown',function (e) {
						if(e.which==8 && !$(e.target).is('input')){
							geneator();
							return false
						}
			});
    </script>
    
</body>

</html>