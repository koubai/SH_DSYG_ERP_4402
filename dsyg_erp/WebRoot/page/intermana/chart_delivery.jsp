<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>DSYG ERP CHART</title>
		
		<!-- 1. Add these JavaScript inclusions in the head of your page -->
		<!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
		<jsp:include  page="chart_common.jsp"/> 
		
		<!-- 2. Add the JavaScript to initialize the chart on document ready -->
		<script type="text/javascript">
		Date.prototype.Format = function(fmt)   
		{ 
		  var o = {
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		}

		$(function () {  
			$("#fromDate").val("2015-08-01");
			$("#toDate").val(new Date().Format("yyyy-MM-dd"));
			getDeliveryData3M();
		});	     	    
		
	    function getDeliveryData3M() {
//     		alert("快递3");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-3);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getDeliveryData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "快递");
	           	}
		   	}
		};
		function getDeliveryData6M() {
//     		alert("快递6");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-6);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getDeliveryData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "快递");
	           	}
		   	}
		};
		function getDeliveryData12M() {
//     		alert("快递12");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-12);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getDeliveryData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "快递");
	           	}
		   	}
		};
		function ck(){
		   	var rds = document.getElementsByName("mtype");
		   	var fromDate = document.getElementById("fromDate").value;
			var today = new Date();
		   	if (fromDate == null || fromDate ==""){
		   		fromDate ="1900-01-01";
		   	}
		   	var toDate = document.getElementById("toDate").value;
		   	if (toDate == null || toDate ==""){
		   		toDate = today.format("yyyy-MM-dd");		
		   	}
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
//	        		alert("fromDate:" +fromDate +" toDate:"+toDate + " type:" + rds[i].value);
			   		if (IsDate(fromDate) && IsDate(toDate))
			   			ajaxRequestData("getDeliveryData", fromDate, toDate, rds[i].value, "快递");
	           }
		    }
		}

		function addUserList() {
			var theme1 = "";
			var url = '<%=request.getContextPath()%>/chart/showDeliverySelectPage.action';
			//strFlag=1采购单，strFlag=2销售单
			url += "?strFieldno=" + theme1 + "&date=" + new Date();
			
			window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
		};

		</script>
		<!-- <script src="${pageContext.request.contextPath}/js/themes/gray.js"></script> -->
	</head>
	<body>
		<div class="containerchart">
		<jsp:include page="../info.jsp" flush="true" />
		<div class="tittle">
			<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
			<div class="tittle_left">
			</div>
			<div class="tittle_center">快递查询
			</div>
			<div class="tittle_right">
			</div>
		</div>
		<div class="tab_content2" >	
		<!-- 3. Add the container -->
		<br><br>
		<input type="hidden" id="h1" value="<s:property value="str" />" />
		<input type="hidden" id="h2" value="<s:property value="series" />" />
		<input type="hidden" id="h3" value="<s:property value="series_X" />" />
		<input type="hidden" id="periodtype" value="<s:property value="periodtype" />" />
				<table width="50%" border="0" cellpadding="5" cellspacing="0">
			<tr>
				<td>快递ID</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
					<input type="text" name="handerList" id="handerList" value="" />
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" onclick="addUserList();" value="快递检索" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
				<td></td>
			</tr>
			<tr>		
				<td>期间类型</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center date_input">				
						<input type="text" name="fromDate" id="fromDate" value="#fromDate" />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('fromDate'));"></a>
					</div>
					<div class="box1_right">&nbsp&nbsp-</div>
					<div class="box1_left" style="margin-left: 30px;"></div>
					<div class="box1_center date_input">				
						<input type="text" name="toDate" id="toDate" value="#toDate" />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('toDate'));"></a>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>
					<div style = "float:left">
						<input name="mtype" type="radio" id="radio1" value="1" checked>月</input>
						<input name="mtype" type="radio" id="radio2" value="2" >季</input>
						<input name="mtype" type="radio" id="radio3" value="3" >年</input>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" value="快递信息查询" onclick="ck();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
		</table>
		<table>
		<tr>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn1" type=button value="快递 近3个月" onClick="javascripts:getDeliveryData3M();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn2" type=button value="快递 近6个月" onClick="javascripts:getDeliveryData6M();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn3" type=button value="快递 近12个月" onClick="javascripts:getDeliveryData12M();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
		</tr>
		</table>						
		<br><br><br>
		<table>
		<tr>
		<td>
		<div id="container" style="width: 600px; height: 400px; margin: 2 "></div>
		</td>
		<td>
		</td>
		<td>
		<div id="container2" style="width: 400px; height: 400px; margin: 2 "></div>
		</td>
		</tr>
		</table>
		<br><br><br>
		<div id="dateMessage">   
		<!-- <table id="planTable" border:1px solid #000 style="border-collapse:collapse;"> -->
		<table id="planTable" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;">
		</table>			
	    </div>
	    </div>
	    </div>
	</body>
</html>
