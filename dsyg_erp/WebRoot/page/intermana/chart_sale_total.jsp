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
			getSaleDataTD();
		});	     	    

	    function getSaleDataTD() {
//     		alert("销售TD");
	   		var fromDate = new Date();
	   		var toDate = new Date();
//     		alert(toDate.format("yyyy-MM-dd"));
			fromDate.setDate(toDate.getDate());
//     		alert(fromDate.format("yyyy-MM-dd"));
 			ajaxRequestData("getSaleTotalData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), 0, "当天销售额");
		}

	    function getSaleDataTM() {
//     		alert("销售TM");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setDate(1);
	   		ajaxRequestData("getSaleTotalData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), 0, "当月销售额");
		}
	    
		function getSaleDataTY() {
//     		alert("销售TY");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(0);
			fromDate.setDate(1);
	   		ajaxRequestData("getSaleTotalData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), 0, "当年销售额");
		}
		
		function ck(){
		   	var fromDate = document.getElementById("fromDate").value;
			var today = new Date();
		   	if (fromDate == null || fromDate ==""){
		   		fromDate ="2015-01-01";
		   	}
		   	var toDate = document.getElementById("toDate").value;
		   	if (toDate == null || toDate ==""){
		   		toDate = today.format("yyyy-MM-dd");		
		   	}
	   		if (IsDate(fromDate) && IsDate(toDate))
	   			ajaxRequestData("getSaleTotalData", fromDate, toDate, 0, "区间销售额");
		}
		
//		当天销售额,当天快递额,当天出差费,及占总销售额比
//		当月销售额,当月快递额,当月出差费,及占总销售额比
		function ajaxRequestData(act, fromDate, toDate, dur_type, tit){
			var o_data="";
			var X_data = new Array();
			X_data = get_X_Data(fromDate, toDate, dur_type);			
//			alert("type: " + dur_type + " from_date:"+ fromDate+ " to_date:"+ toDate);		
			
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act+'&from_date='+fromDate+'&to_date='+toDate+'&dur_type=0',
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
	       			var pie_data = getPieData(data, tit);
//	       			alert("pie_data:"+pie_data);
        			drawPie(pie_data, tit);
        			viewData(new Array("销售额","快递额","出差费"), data);
                }
            });
            return o_data;
        }	

		function getPieData(data, tit) {

			var jsonobj=eval(data);  
			var total_data = new Number(0);
//			alert("data: " + data);		

			var o_data = "[{type: 'pie', name:'"+tit+"分布',data:[";
//			alert("jsonobj.length: " + jsonobj.length);		
//			alert("jsonobj[0].data[0]: " + jsonobj[0].data[0]);		
			if (jsonobj.length >0){
				total_data = jsonobj[0].data[0]; 
				str_name = new Array("净销售额","快递额","出差费");
				for(var k=0;k<3;k++){  
					if (k == 0){
						o_data = o_data + "[";
					}else{
						o_data = o_data + ",[";
					}
					o_data = o_data  + "'" + str_name[k] + "',"+ jsonobj[0].data[k] *100/total_data;
					o_data = o_data  + "]";
//					alert("str_name[k] +jsonobj[0].data[k] : " + str_name[k]+";"+jsonobj[0].data[k]  );		
				}
			}
			o_data = o_data + "]}]";
			return o_data.replace(/\"/g,"");			
   	    };
   	    
		function viewData(X_data, data) {
			var jsonobj=eval(data.replace(/\"/g,""));  
//			alert("data2: " + data);		

			var newLine = $("#planTable").length;
			var d=document.getElementById('planTable').deleteTHead();
			var x=document.getElementById('planTable').createTHead();
			var ud = 0;
			
			if (X_data.length>0){
				var row = x.insertRow(0);   
	            var col = row.insertCell(0);         
                col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+""+"</strong>";
	            for (var z=0; z< X_data.length; z++) {
	    		    col = row.insertCell(z+1);   
	                col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+X_data[z]+"</strong>";
	            }             
				d=document.getElementById('planTable').deleteTFoot();
				x=document.getElementById('planTable').createTFoot();
		        $.each(jsonobj, function(i, u){	         				
		    		 var row = x.insertRow(i); 
		    		 var col = row.insertCell(0);                
//		             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
			                
		             for (var w=0; w< u.data.length; w++) {
					 	col = row.insertCell(w+1);  
					 	if (u.data[w] != null )
					 		ud = u.data[w];
			            col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+ud.toFixed(2).toString() +"</strong1>";
		             }
		        });				
			}
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
			<div class="tittle_center">销售额查询
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
				<input type="hidden" name="handerList" id="handerList" value="" />
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
						<input type="text" name="toDate" id="toDate" value="#toDate"  />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('toDate'));"></a>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" value="销售额查询" onclick="ck();" />
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
			           <Input id="btn1" type=button value="销售额 当天" onClick="javascripts:getSaleDataTD();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn2" type=button value="销售额 本月" onClick="javascripts:getSaleDataTM();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn3" type=button value="销售额 本年" onClick="javascripts:getSaleDataTY();" />
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
