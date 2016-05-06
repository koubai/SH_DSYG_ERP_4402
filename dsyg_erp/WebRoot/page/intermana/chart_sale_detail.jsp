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
			getSaleData3M();
		});
	     	    
		function get_X_Data(d1, d2, type) {
			var m1;
			var m2;
			var duration;
	   		var dicArray = new Array();
	   		var tmp_date = new Date();
			if (type == "1"){
				// Monthly
	            m1 = parseInt(d1.split("-")[1].replace(/^0+/, "")) + parseInt(d1.split("-")[0]) * 12;
	            m2 = parseInt(d2.split("-")[1].replace(/^0+/, "")) + parseInt(d2.split("-")[0]) * 12;
	            duration = m2 - m1;
				tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2]));
				for(var i = 0; i < duration + 1; i++) {
					tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1 + i,parseInt(d1.split("-")[2]));
					dicArray[i] = tmp_date.Format("yyyy-MM");
//					alert("dicArray[i]="+ dicArray[i]);		
				}
			} else if (type == "2"){
				// Quarter
	            m1 = parseInt(d1.split("-")[1].replace(/^0+/, "")) + parseInt(d1.split("-")[0]) * 12;
	            m2 = parseInt(d2.split("-")[1].replace(/^0+/, "")) + parseInt(d2.split("-")[0]) * 12;
	            duration = m2 - m1;
				tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2]));
				for(var j = 0; j < (duration + 1)/3; j++) {
					tmp_date.setFullYear(parseInt(d1.split("-")[0])+(parseInt((d1.split("-")[1])) + j * 3)/12,(parseInt((d1.split("-")[1])-1)/3 + j%4)%4,1);
					dicArray[j] = tmp_date.Format("yyyy-MM")+"季";
//					alert("dicArray[j]="+ dicArray[j]);		
				}				
			} else if (type == "3"){
				// Year
	            m1 = parseInt(d1.split("-")[0]);
	            m2 = parseInt(d2.split("-")[0]);
	            duration = m2 - m1;
				tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,1);
				for(var k = 0; k < duration + 1; k++) {
					tmp_date.setFullYear(parseInt(d1.split("-")[0])+k,parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2]));
					dicArray[k] = tmp_date.Format("yyyy");
//					alert("dicArray[k]="+ dicArray[k]);		
				}
			}
			return dicArray;
		}
   	    
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
				d=document.getElementById('planTable').deleteTFoot();
				x=document.getElementById('planTable').createTFoot();
		        $.each(jsonobj, function(i, u){	         				
		    		 var row = x.insertRow(i); 
		    		 var col = row.insertCell(0);                
		             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
			                
		             for (var w=0; w< u.data.length; w++) {
					 	col = row.insertCell(w+1);   
			            col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.data[w]+"</strong1>";
		             }
		        });				
			}
	    }  
   	    
		function ajaxRequestData(act, fromDate, toDate, dur_type, tit){
			var o_data="";
			var X_data = new Array();
			X_data = get_X_Data(fromDate, toDate, dur_type);			
//			alert("type: " + dur_type + " from_date:"+ fromDate+ " to_date:"+ toDate);		
			
//			var handerList=document.getElementById('handerList').value;
			var handerList = $("#handerList").val().trim();

			if( handerList== null) {
				handerList="";
			}
//			alert("handerList: "+handerList);		
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act+'&from_date='+fromDate+'&to_date='+toDate+'&dur_type='+dur_type+'&handerList='+handerList,
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
//        			alert("org_data:"+str_data2);
	       			var pie_data = getPieData(data, tit);
//	       			alert("pie_data:"+pie_data);
        			drawPie(pie_data, tit);
        			o_data = getChartData(data);
        			drawChart(fromDate, toDate, dur_type, o_data, tit);
        			viewData(X_data, o_data);
                }
            });
            return o_data;
        }	
		
		function getChartData(data) {
			var jsonobj=eval(data);  
			var sum_data = new Array();
			var total_data = new Number(0);
			if (jsonobj.length >0){
				for(var k=0;k<jsonobj.length;k++){  
					jsonobj[k].name= "'"+ jsonobj[k].name+"'";
				}
			}
        	var str_data= JSON.stringify(jsonobj);
        	var o_data= str_data.replace(/\"/g,"");
//   			alert("o_data:"+o_data);
           	return o_data;            
		}
		
		function getPieData(data, tit) {
//			var	data=[{name:0001,data:[11700.00,23400.00,11700.00,11700.00,11700.00,23400.00,11700.00]},{name:0003,data:[0.00,21700.00,21700.00,0.00,0.00,0.00,0.00]}];

			var jsonobj=eval(data);  
			var sum_data = new Array();
			var total_data = new Number(0);

			if (jsonobj.length >0){
				for(var i=0;i<jsonobj.length;i++){  
//				    alert(jsonobj[i].name);  
//				    alert(jsonobj[i].data);  
					if (jsonobj[i].data.length >0){
						var sum_d = new Number(0);
						for(var j=0;j<jsonobj[i].data.length;j++){  							
							var ind_data = new Number(0);
							ind_data = parseFloat(jsonobj[i].data[j]);  
							sum_d = sum_d + ind_data; 
							sum_data[i] = sum_d; 
						}
						total_data = total_data + sum_data[i]; 
					}
				}
			}
			var o_data = "[{type: 'pie', name:'"+tit+"分布',data:[";
			if (jsonobj.length >0){
				for(var k=0;k<jsonobj.length;k++){  
					if (k == 0){
						o_data = o_data + "[";
					}else{
						o_data = o_data + ",[";
					}
					o_data = o_data  + "'" + jsonobj[k].name + "',"+ sum_data[k]*100/total_data;
					o_data = o_data  + "]";
				}
			}
			o_data = o_data + "]}]";				
			return o_data;			
   	    };
   	    
		function drawPie(pie_data, tit) {
			var strTitle = tit + "分布";
			var strSubTitle= "占有比例";
		    $(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container2',  
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					
					credits: { 
			               enabled: false   //右下角不显示LOGO 
			           },
			           
					title: {
						text: strTitle
					},

					subtitle: {
						text: strSubTitle,
						x: -20
					},

					tooltip: {
						 pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
					},

					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: '#000000',
								connectorColor: '#000000',
								format: '<b>{point.name}</b>: {point.percentage:.1f} %'
							}
						}
					},
					series: eval(pie_data)
				};
               	var chart = new Highcharts.Chart(options);
			});
		};
		
		function drawChart(d1, d2, dur_type, chart_data, tit) {
     		$(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container',  
		            },		            
		            credits: { 
		                enabled: false   //右下角不显示LOGO 
		            },
		            title: {  
		                text: tit + "曲线",
		            },  
		            xAxis: {  
		                categories: get_X_Data(d1, d2, dur_type),
		            },  
		            yAxis: {  
		                min: 0,  
		                title: {  
		                    text: '金额 (元)'  
		                }  
		            },  
		            legend: {  
		                layout: 'vertical',  
		                backgroundColor: '#FFFFFF',  
		                align: 'left',  
		                verticalAlign: 'top',  
		                x: 450,  
		                y: 1,  
		                floating: true,  
		                shadow: true  
		            },  
		            tooltip: {  
		                formatter: function() {  
		                    return ''+  
		                        this.x +': '+ this.y +' ￥';  
		                }  
		            },  
		            plotOptions: {  
		                column: {  
		                    pointPadding: 0.2,  
		                    borderWidth: 0  
		                }  
		            },  
	                series: eval(chart_data)		         
		    	};
	            var chart = new Highcharts.Chart(options);
	        });
	    };  

	    function getSaleData3M() {
//     		alert("销售3");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-3);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getSaleDetailData", fromDate.Format("yyyy-MM-dd"), toDate.Format("yyyy-MM-dd"), rds[i].value, "销售");
	           	}
		   	}
		}

	    function getSaleData6M() {
//     		alert("销售6");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-6);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getSaleDetailData", fromDate.Format("yyyy-MM-dd"), toDate.Format("yyyy-MM-dd"), rds[i].value, "销售");
	           	}
		   	}
		}
	    
		function getSaleData12M() {
//     		alert("销售12");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-12);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getSaleDetailData", fromDate.Format("yyyy-MM-dd"), toDate.Format("yyyy-MM-dd"), rds[i].value, "销售");
	           	}
		   	}
		}
		
		function ck(){
		   	var rds = document.getElementsByName("mtype");
		   	var fromDate = document.getElementById("fromDate").value;
			var today = new Date();
		   	if (fromDate == null || fromDate ==""){
		   		fromDate ="1900-01-01";
		   	}
		   	var toDate = document.getElementById("toDate").value;
		   	if (toDate == null || toDate ==""){
		   		toDate = today.Format("yyyy-MM-dd");		
		   	}
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
//	        		alert("fromDate:" +fromDate +" toDate:"+toDate + " type:" + rds[i].value);
			   		if (IsDate(fromDate) && IsDate(toDate))
			   			ajaxRequestData("getSaleDetailData", fromDate, toDate, rds[i].value, "销售");
	           }
		    }
		}
		
		function addUserList() {
			var theme1 = "";
			var url = '<%=request.getContextPath()%>/chart/showUserSelectPage.action';
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
			<div class="tittle_center">详细销售查询
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
				<td>销售ID</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
					<input type="text" name="handerList" id="handerList" value="" />
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" onclick="addUserList();" value="销售员检索" />
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
							<input type="button" value="详细销售查询" onclick="ck();" />
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
			           <Input id="btn1" type=button value="销售 近3个月" onClick="javascripts:getSaleData3M();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn2" type=button value="销售 近6个月" onClick="javascripts:getSaleData6M();" />
					</div>
					<div class="box1_right"></div>
				</div>
			</td>
			<td>
				<div class="btn">
					<div class="box1_left"></div>
					<div class="box1_center">
			           <Input id="btn3" type=button value="销售 近12个月" onClick="javascripts:getSaleData12M();" />
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
