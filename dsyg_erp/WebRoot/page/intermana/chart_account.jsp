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
		
		<!-- 2. Add the JavaScript to initialize the chart on document ready -->
		<script type="text/javascript">
		$(function () {  
			getAccountData3M();
		});
	     	    
		Date.prototype.format = function(format){ 
			var o = { 
				"M+" : this.getMonth()+1, //month 
				"d+" : this.getDate(), //day 
				"h+" : this.getHours(), //hour 
				"m+" : this.getMinutes(), //minute 
				"s+" : this.getSeconds(), //second 
				"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
				"S" : this.getMilliseconds() //millisecond 
			}

			if(/(y+)/.test(format)) { 
				format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
			} 

			for(var k in o) { 
				if(new RegExp("("+ k +")").test(format)) { 
					format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
				} 
			} 
			return format; 
		}
		
		function get_X_Data(period_month) {
			var today = new Date();
//			var startday = new Date();
			var dicArray = new Array();
			var duration = parseInt(period_month) *(-1);
			
			for(var i = 0; i < parseInt(duration); i++) {
				var tmp_date = new Date();
				tmp_date.setMonth(tmp_date.getMonth() + parseInt(period_month) +i + 1,1);
				dicArray[i] = tmp_date.format("yyyy-MM");
//				alert(tmp_date.format("yyyy-MM-dd"));		
			}
			return dicArray;
   	    };
   	    
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
	                col.innerHTML = "<style>strong{float: right;}</style><strong1>"+ sum.toString() +"</strong1>";
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
	                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+high_profits[j].toString()+"</strong2>";
	            }             
    		    col = row.insertCell(j+1);   
                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_high_profits.toString()+"</strong2>";
				row_no++;
				row = x.insertRow(row_no+1); 
	    		col = row.insertCell(0);                
	            col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>净利</strong2>";
	            for (var v=0; v< detail_profits.length; v++) {
	    		    col = row.insertCell(v+1);   
	                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+detail_profits[v].toString()+"</strong2>";
	            }             
    		    col = row.insertCell(j+1);   
                col.innerHTML = "<style>strong2{float: right;background:#b9ffb9}</style><strong2>"+sum_detail_profits.toString()+"</strong2>";

			}
	    };  
   	    
		function ajaxRequestData(act, mth, tit){
			var o_data="";
			var X_data = new Array();
			X_data = get_X_Data(mth);			
			var from_date=X_data[0]+"-01";
			if (mth <0)
				var to_date=X_data[mth*(-1) - 1]+"-31";
			else
				var to_date=X_data[0]+"-31";
			alert("mth: "+mth+ " from_date:"+ from_date+ " to_date:"+ to_date);		
			
			var handerList=document.getElementById('handerList').value;
			if( handerList== null) {
				handerList="";
			}
			alert("handerList: "+handerList);		
            $.ajax({
				url: '${pageContext.request.contextPath}/ChartServlet.servlet?action='+act+'&from_date='+from_date+'&to_date='+to_date+'&handerList='+handerList,
                type: "POST",
                dataType: "text",
                async: false,
                success: function (data) {
//        			alert("org_data:"+str_data2);
	       			var pie_data = getPieData(data, tit);
//	       			alert("pie_data:"+pie_data);
        			drawPie(pie_data, tit);
        			o_data = getChartData(data);
        			drawChart(mth, o_data, tit);
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
		
		function drawChart(mth, chart_data, tit) {
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
		                categories: get_X_Data(mth),
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
		                x: 500,  
		                y: 5,  
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

	    function getPurchaseData3M() {
     		alert("采购3");
			ajaxRequestData("getPurchaseData", -3, "采购");
		};
		function getPurchaseData6M() {
     		alert("采购6");
			ajaxRequestData("getPurchaseData", -6, "采购");
		};
		function getPurchaseData12M() {
     		alert("采购12");
			ajaxRequestData("getPurchaseData", -12, "采购");
		};
	    function getSaleData3M() {
     		alert("销售3");
			ajaxRequestData("getSaleData", -3, "销售");
		};
		function getSaleData6M() {
     		alert("销售6");
			ajaxRequestData("getSaleData", -6, "销售");
		};
		function getSaleData12M() {
     		alert("销售12");
			ajaxRequestData("getSaleData", -12, "销售");
		};
		
	    function getDeliveryData3M() {
     		alert("快递3");
			ajaxRequestData("getDeliveryData", -3, "快递");
		};
		function getDeliveryData6M() {
     		alert("快递6");
			ajaxRequestData("getDeliveryData", -6, "快递");
		};
		function getDeliveryData12M() {
     		alert("快递12");
			ajaxRequestData("getDeliveryData", -12, "快递");
		};
	    function getAccountData3M() {
     		alert("会计3");
			ajaxRequestData("getAccountData", -3, "会计");
		};
		function getAccountData6M() {
     		alert("会计6");
			ajaxRequestData("getAccountData", -6, "会计");
		};
		function getAccountData12M() {
     		alert("会计12");
			ajaxRequestData("getAccountData", -12, "会计");
		};
		</script>
		<!-- <script src="${pageContext.request.contextPath}/js/themes/gray.js"></script> -->
	</head>
	<body>
		<!-- 3. Add the container -->
		<input type="hidden" id="h1" value="<s:property value="str" />" />
		<input type="hidden" id="h2" value="<s:property value="series" />" />
		<input type="hidden" id="h3" value="<s:property value="series_X" />" />
		<input type="text" name="handerList" id="handerList" value="0001,0002" />
		<table>
		<tr>
		<td>
           <Input id="btn1" type=button value="财务 3 Month" onClick="javascripts:getAccountData3M();" /></td>
		<td>
           <Input id="btn2" type=button value="财务 6 Month" onClick="javascripts:getAccountData6M();" /></td>
		<td>
           <Input id="btn3" type=button value="财务 12 Month" onClick="javascripts:getAccountData12M();" /></td>
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
	</body>
</html>
