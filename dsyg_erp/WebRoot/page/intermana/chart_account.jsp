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
			getAccountData3M();
		});	     	    

		function viewData(X_data, data) {
			var jsonobj=eval(data);  

			var newLine = $("#planTable").length;
			var d=document.getElementById('planTable').deleteTHead();
			var x=document.getElementById('planTable').createTHead();
			if (X_data.length>0){
				var row = x.insertRow(0);   
	            var col = row.insertCell(0);         
                col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+""+"</strong>";
	            for (var z=0; z< X_data.length; z++) {
	    		    col = row.insertCell(z+1);   
	                col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+X_data[z]+"</strong>";
	            }             
    		    col = row.insertCell(z+1);   
                col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>合计</strong>";

                d=document.getElementById('planTable').deleteTFoot();
				x=document.getElementById('planTable').createTFoot();
				
				var high_profits =  new Array(X_data.length);
				var detail_profits =  new Array(X_data.length);
				var sum_high_profits;
				var sum_detail_profits;

				var row_no=0;
		        $.each(jsonobj, function(i, u){	         				
	   	             var sum = 0;
	 				 sum_high_profits = 0;
					 sum_detail_profits = 0;
		    		 var row = x.insertRow(i);
		    		 row_no = i;
		    		 var col = row.insertCell(0);                
		             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
		             if (i==0){
			             for (var m=0; m< u.data.length; m++) {
			                high_profits[m]= 0;
			                detail_profits[m]= 0;			                
			             }		            	 
		             }
		             for (var w=0; w< u.data.length; w++) {
					 	col = row.insertCell(w+1);   
			            col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.data[w]+"</strong1>";
		                sum = sum + Number(u.data[w]);

		                if (i == 1){
			                high_profits[w]=high_profits[w]+Number(u.data[w]);
			                detail_profits[w]=detail_profits[w]+Number(u.data[w]);
		                } else {
		                	if (i == 0){		                		
				                high_profits[w]=high_profits[w]-Number(u.data[w]);		                	
		                	}
			                detail_profits[w]=detail_profits[w]-Number(u.data[w]);
		                }		                	
			            sum_high_profits = sum_high_profits + Number(high_profits[w]);
			            sum_detail_profits = sum_detail_profits + Number(detail_profits[w]);
//		                alert("sum_high_profits:" + sum_high_profits);
		             }
	    		    col = row.insertCell(w+1);   
	                col.innerHTML = "<style>strong{float: right;}</style><strong1>"+ sum.toFixed(2).toString() +"</strong1>";
		        });		        
				row = x.insertRow(row_no+1);
	    		col = row.insertCell(0);                
	            col.innerHTML = "<style>strong1{float: right;}</style><strong1>  </strong1>";
				row_no++;
				row = x.insertRow(row_no+1); 
	    		col = row.insertCell(0);                
	            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>毛利</strong2>";
	            for (var j=0; j< high_profits.length; j++) {
	    		    col = row.insertCell(j+1);   
	                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+high_profits[j].toFixed(2).toString()+"</strong2>";
	            }             
    		    col = row.insertCell(j+1);   
                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_high_profits.toFixed(2).toString()+"</strong2>";
				row_no++;
				row = x.insertRow(row_no+1); 
	    		col = row.insertCell(0);                
	            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>净利</strong2>";
	            for (var v=0; v< detail_profits.length; v++) {
	    		    col = row.insertCell(v+1);   
	                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+detail_profits[v].toFixed(2).toString()+"</strong2>";
	            }             
    		    col = row.insertCell(j+1);   
                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_detail_profits.toFixed(2).toString()+"</strong2>";

			}
	    };  

	    function getAccountData3M() {
//     		alert("会计3");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-3);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "会计");
	           	}
		   	}
		};
		function getAccountData6M() {
//     		alert("会计6");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-6);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "会计");
	           	}
		   	}
		};
		function getAccountData12M() {
//     		alert("会计12");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-12);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "会计");
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
			   			ajaxRequestData("getAccountData", fromDate, toDate, rds[i].value, "会计");
	           }
		    }
		}

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
			<div class="tittle_center">会计查询
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
			<input type="hidden" name="handerList" id="handerList" value="" />
			<table width="50%" border="0" cellpadding="5" cellspacing="0">
				<tr>
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
								<input type="button" value="财务查询" onclick="ck();" />
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
				           <Input id="btn1" type=button value="财务 近3个月" onClick="javascripts:getAccountData3M();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
				           <Input id="btn2" type=button value="财务 近6个月" onClick="javascripts:getAccountData6M();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
				           <Input id="btn3" type=button value="财务 近12个月" onClick="javascripts:getAccountData12M();" />
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
