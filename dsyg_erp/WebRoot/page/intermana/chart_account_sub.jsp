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
			getAccountSubData3M();
		});	     	    

		function queryUnDeliProductData(iotype) {
			$("#iotype").val(iotype);
			document.mainform.action = '<%=request.getContextPath()%>/chart/queryUnDeliProductDataAction.action';
			document.mainform.submit();
		}
		
		function viewData(X_data, datao, type) {
			var jsonobj=eval(datao);  

    		if (type == '0'){    			
				var newLine = $("#planTable").length;
				var d=document.getElementById('planTable').deleteTHead();
				var x=document.getElementById('planTable').createTHead();
			}else{
				var newLine = $("#planTableB").length;
				var d=document.getElementById('planTableB').deleteTHead();
				var x=document.getElementById('planTableB').createTHead();
			}		
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

        		if (type == '0'){    			
	                d=document.getElementById('planTable').deleteTFoot();
					x=document.getElementById('planTable').createTFoot();
	    		}else{
	                d=document.getElementById('planTableB').deleteTFoot();
					x=document.getElementById('planTableB').createTFoot();
				}				
				var high_profits =  new Array(X_data.length);
				var sum_high_profits;

				var row_no=0;
		        $.each(jsonobj, function(i, u){	         				
	   	             var sum = 0;
	 				 sum_high_profits = 0;
		    		 var row = x.insertRow(i);
		    		 row_no = i;
		    		 var col = row.insertCell(0);                
		             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
		             if (i==0){
			             for (var m=0; m< u.data.length; m++) {
			                high_profits[m]= 0;
			             }		            	 
		             }
		             for (var w=0; w< u.data.length; w++) {
					 	col = row.insertCell(w+1);   
			            col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.data[w]+"</strong1>";
		                sum = sum + Number(u.data[w]);

		                high_profits[w]=high_profits[w]+Number(u.data[w]);
			            sum_high_profits = sum_high_profits + Number(high_profits[w]);
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
	    		if (type == '0')    			
		            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>销售总计:</strong2>";
		        else 
		            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>利润总计:</strong2>";
	            for (var j=0; j< high_profits.length; j++) {
	    		    col = row.insertCell(j+1);   
	                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+high_profits[j].toFixed(2).toString()+"</strong2>";
	            }             
    		    col = row.insertCell(j+1);   
                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_high_profits.toFixed(2).toString()+"</strong2>";
			}
	    };  

		function viewWarehouseCostData(datao) {
			var jsonobj=eval(datao);  
			var newLine = $("#planTableC").length;
			var d=document.getElementById('planTableC').deleteTHead();
			var x=document.getElementById('planTableC').createTHead();
			var row = x.insertRow(0);   
            var col = row.insertCell(0);         
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"产品种类"+"</strong>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"库存成本"+"</strong>";
   		    col = row.insertCell(2);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"剩余数量"+"</strong>";
            d=document.getElementById('planTableC').deleteTFoot();
			x=document.getElementById('planTableC').createTFoot();
			var sum_warehousecost=0;

			var row_no=0;
	        $.each(jsonobj, function(i, u){	         				
	    		 var row = x.insertRow(i);
	    		 row_no = i;
	    		 var col = row.insertCell(0);                
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
				 col = row.insertCell(1);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[0]).toFixed(2).toString()+"</strong1>";
				 col = row.insertCell(2);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[1]).toFixed(2).toString()+"</strong1>";
	             sum_warehousecost = sum_warehousecost + Number(u.data[0]);
	        });		        
			row = x.insertRow(row_no+1);
    		col = row.insertCell(0);                
            col.innerHTML = "<style>strong1{float: right;}</style><strong1>  </strong1>";
			row_no++;
			row = x.insertRow(row_no+1); 
    		col = row.insertCell(0);    
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+"库存成本总计:"+"</strong2>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_warehousecost.toFixed(2).toString()+"</strong2>";
	    };  

	    
		function viewUnOutWarehouseCostData(datao) {
			var jsonobj=eval(datao);  
			var newLine = $("#planTableD").length;
			var d=document.getElementById('planTableD').deleteTHead();
			var x=document.getElementById('planTableD').createTHead();
			var row = x.insertRow(0);   
            var col = row.insertCell(0);         
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"产品种类"+"</strong>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"订单未发货金额"+"</strong>";
   		    col = row.insertCell(2);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"订单未发货数量"+"</strong>";
            d=document.getElementById('planTableD').deleteTFoot();
			x=document.getElementById('planTableD').createTFoot();
			var sum_warehousecost=0;

			var row_no=0;
	        $.each(jsonobj, function(i, u){	         				
	    		 var row = x.insertRow(i);
	    		 row_no = i;
	    		 var col = row.insertCell(0);                
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
				 col = row.insertCell(1);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[0]).toFixed(2).toString()+"</strong1>";
				 col = row.insertCell(2);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[1]).toFixed(2).toString()+"</strong1>";
	             sum_warehousecost = sum_warehousecost + Number(u.data[0]);
	        });		        
			row = x.insertRow(row_no+1);
    		col = row.insertCell(0);                
            col.innerHTML = "<style>strong1{float: right;}</style><strong1>  </strong1>";
			row_no++;
			row = x.insertRow(row_no+1); 
    		col = row.insertCell(0);    
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+"订单未发货金额总计:"+"</strong2>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_warehousecost.toFixed(2).toString()+"</strong2>";
	    };  

		function viewUnInWarehouseCostData(datao) {
			var jsonobj=eval(datao);  
			var newLine = $("#planTableE").length;
			var d=document.getElementById('planTableE').deleteTHead();
			var x=document.getElementById('planTableE').createTHead();
			var row = x.insertRow(0);   
            var col = row.insertCell(0);         
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"产品种类"+"</strong>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"采购单未入库金额"+"</strong>";
   		    col = row.insertCell(2);   
            col.innerHTML = "<style>strong{background:#59c9ff}</style><strong>"+"采购单未入库数量"+"</strong>";
            d=document.getElementById('planTableE').deleteTFoot();
			x=document.getElementById('planTableE').createTFoot();
			var sum_warehousecost=0;

			var row_no=0;
	        $.each(jsonobj, function(i, u){	         				
	    		 var row = x.insertRow(i);
	    		 row_no = i;
	    		 var col = row.insertCell(0);                
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.name+"</strong1>";
				 col = row.insertCell(1);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[0]).toFixed(2).toString()+"</strong1>";
				 col = row.insertCell(2);   
	             col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+Number(u.data[1]).toFixed(2).toString()+"</strong1>";
	             sum_warehousecost = sum_warehousecost + Number(u.data[0]);
	        });		        
			row = x.insertRow(row_no+1);
    		col = row.insertCell(0);                
            col.innerHTML = "<style>strong1{float: right;}</style><strong1>  </strong1>";
			row_no++;
			row = x.insertRow(row_no+1); 
    		col = row.insertCell(0);    
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+"采购单未入库金额总计:"+"</strong2>";
   		    col = row.insertCell(1);   
            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_warehousecost.toFixed(2).toString()+"</strong2>";
	    };  	    
	    
	    function getAccountSubData3M() {
//     		alert("会计3");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-3);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountSubData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "");
	           	}
		   	}
		};
		function getAccountSubData6M() {
//     		alert("会计6");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-6);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountSubData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "");
	           	}
		   	}
		};
		function getAccountSubData12M() {
//     		alert("会计12");
		   	var rds = document.getElementsByName("mtype");
	   		var fromDate = new Date();
	   		var toDate = new Date();
			fromDate.setMonth(toDate.getMonth()+1-12);
		   	for(var i=0;i<rds.length;i++){
	           	if(rds[i].checked){
	   				ajaxRequestData("getAccountSubData", fromDate.format("yyyy-MM-dd"), toDate.format("yyyy-MM-dd"), rds[i].value, "");
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
			   			ajaxRequestData("getAccountSubData", fromDate, toDate, rds[i].value, "会计");
	           }
		    }
		}

		function drawChartA(d1, d2, dur_type, chart_data, tit) {			
     		$(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'containerA',  
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
		                x: 100,  
		                y: 20,  
		                floating: true,  
		                shadow: true  
		            },  
		            tooltip: {  
		                formatter: function() {  
		                    return ''+  
		                        this.x +': '+ this.y +' ';  
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
	    
		function drawChartB(d1, d2, dur_type, chart_data, tit) {			
     		$(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'containerB',  
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
		                x: 100,  
		                y: 20,  
		                floating: true,  
		                shadow: true  
		            },  
		            tooltip: {  
		                formatter: function() {  
		                    return ''+  
		                        this.x +': '+ this.y +' ';  
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
	    
		function drawPieA(pie_data, tit) {
			var strTitle = tit + "分布";
			var strSubTitle= "占有比例";
		    $(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container2A',  
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
		
		function drawPieB(pie_data, tit) {
			var strTitle = tit + "分布";
			var strSubTitle= "占有比例";
		    $(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container2B',  
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

		function ajaxRequestData(act, fromDate, toDate, dur_type, tit){
			ajaxRequestDataA("getAccountSubDataA", fromDate, toDate, dur_type, "销售");
			ajaxRequestDataB("getAccountSubDataB", fromDate, toDate, dur_type, "利润");
			ajaxRequestDataC("getWarehouseCostData");
			ajaxRequestDataD("getUnOutWarehouseCostData");
			ajaxRequestDataE("getUnInWarehouseCostData");
		}
		
		function ajaxRequestDataA(act, fromDate, toDate, dur_type, tit){
			var o_data="";
			var X_data = new Array();
			X_data = get_X_Data(fromDate, toDate, dur_type);			
//			alert("type: " + dur_type + " from_date:"+ fromDate+ " to_date:"+ toDate);		
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
	       			var pie_data = getPieData(data, tit);
        			drawPieA(pie_data, tit);
        			o_data = getChartData(data);
        			drawChartA(fromDate, toDate, dur_type, o_data, tit);
        			viewData(X_data, o_data, '0');
                }
            });
            return o_data;
        }	

		function ajaxRequestDataB(act, fromDate, toDate, dur_type, tit){
			var o_data="";
			var X_data = new Array();
			X_data = get_X_Data(fromDate, toDate, dur_type);			
//			alert("type: " + dur_type + " from_date:"+ fromDate+ " to_date:"+ toDate);		
			
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
	       			var pie_data = getPieData(data, tit);
        			drawPieB(pie_data, tit);
        			o_data = getChartData(data);
        			drawChartB(fromDate, toDate, dur_type, o_data, tit);
        			viewData(X_data, o_data, '1');
                }
            });
            return o_data;
        }	
		function ajaxRequestDataC(act){
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act,
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
        			viewWarehouseCostData(data);
                }
            });
		}
		function ajaxRequestDataD(act){
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act,
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
                	viewUnOutWarehouseCostData(data);
                }
            });
		}
		function ajaxRequestDataE(act){
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act,
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
                	viewUnInWarehouseCostData(data);
                }
            });
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
			<div class="tittle_center">销售产品查询
			</div>
			<div class="tittle_right">
			</div>
		</div>
		<div class="tab_content2" >	
		<!-- 3. Add the container -->
		<br><br>
		<s:form id="mainform" name="mainform" method="POST">
			<input type="hidden" id="h1" value="<s:property value="str" />" />
			<input type="hidden" id="h2" value="<s:property value="series" />" />
			<input type="hidden" id="h3" value="<s:property value="series_X" />" />
			<input type="hidden" id="periodtype" value="<s:property value="periodtype" />" />
			<input type="hidden" name="handerList" id="handerList" value="" />
			<input type="hidden" name="fieldno" id="fieldno" value="01" />
			<input type="hidden" name="iotype" id="iotype" value="" />
			<table width="50%" border="0" cellpadding="5" cellspacing="0">
				<tr>
				</tr>
				<tr>		
					<td>期间类型</td>
					<td>
						<div class="box1_left"></div>
						<div class="box1_center date_input">				
							<input type="text" name="fromDate" id="fromDate" value=#fromDate />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('fromDate'));"></a>						
						</div>
						<div class="box1_right">&nbsp&nbsp-</div>
						<div class="box1_left" style="margin-left: 30px;"></div>
						<div class="box1_center date_input">				
							<input type="text" name="toDate" id="toDate" value=#toDate />
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
								<input type="button" value="销售产品查询" onclick="ck();" />
							</div>
							<div class="box1_right"></div>
						</div>
					</td>
				</tr>
			</table>
		</s:form>	
			<table>
			<tr>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
				           <Input id="btn1" type=button value="销售产品 近3个月" onClick="javascripts:getAccountSubData3M();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
				           <Input id="btn2" type=button value="销售产品 近6个月" onClick="javascripts:getAccountSubData6M();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
				           <Input id="btn3" type=button value="销售产品 近12个月" onClick="javascripts:getAccountSubData12M();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			</table>			
			<br><br>
			<div id="dateMessage">   
			<!-- <table id="planTable" border:1px solid #000 style="border-collapse:collapse;"> -->
			<table id="planTable" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;">
			</table>			
			<br><br>
			<table id="planTableB" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;">
			</table>			
			<br><br>
			<table id="dataM">
			<tr>
				<td>
					<table id="planTableC" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;" width="400">
					</table>			
					<br><br>
				</td>
		    </tr>
		    <tr>
				<td>
					<table id="planTableD" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;" width="400">
					</table>			
					<br><br>
				</td>
				<td>
					<div class="btn" style="margin-right: 200px; float: right;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input120" value="销售未发货产品信息检索" onclick="queryUnDeliProductData(2);"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
		    </tr>
		    <tr>
				<td>
					<table id="planTableE" border:1px  cellpadding="3" cellspacing="1" style="background-color: #b9d8f3;" width="400">
					</table>			
					<br><br>
				</td>
				<td>
					<div class="btn" style="margin-right: 200px; float: right;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input120" value="采购未进货产品信息检索" onclick="queryUnDeliProductData(1);"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
		    </tr>
			</table>
		    </div>
			<br><br><br>
			<table>
			<tr>
			<td>
			<div id="containerA" style="width: 600px; height: 400px; margin: 2 "></div>
			</td>
			<td>
			</td>
			<td>
			<div id="container2A" style="width: 400px; height: 400px; margin: 2 "></div>
			</td>
			</tr>
			<tr>
			<td>
			<div id="containerB" style="width: 600px; height: 400px; margin: 2 "></div>
			</td>
			<td>
			</td>
			<td>
			<div id="container2B" style="width: 400px; height: 400px; margin: 2 "></div>
			</td>
			</tr>
			</table>
	    </div>
	    </div>
	</body>
</html>
