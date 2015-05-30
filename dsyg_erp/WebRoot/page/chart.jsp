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
			var to_date = new Date();
			var from_date = new Date();
			var dicArray = new Array();
	    	
			for(var i=0; i<= -period_month; i++) {
				var tmp_date = new Date();
				tmp_date.setMonth(today.getMonth()+ period_month +i);
				dicArray[i] = tmp_date.format("yyyy-MM");
			}
//			alert(str);		
			return dicArray;
   	    };

   	    
		function ajaxRequestData(){
			var o_data="";
            $.ajax({
				url: 'http://localhost:8080/dsyg_erp/ChartServlet.servlet?month=3&action=BBB',
                type: "POST",
                dataType: "text",
                success: function (data) {
                	var str_data= JSON.stringify(eval(data));
                	var str_data2= str_data.replace(/\"/g,"");
                   	o_data=str_data2;            
        			alert("org_data:"+str_data2);
	       			var pie_data = getPieData(data);
//       			alert("pie_data:"+pie_data);
        			drawPie(pie_data);
        			getData(-3, o_data);
                }
            });
//     		alert(o_data);
            return o_data;
        }		

		function getPieData(data) {
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
			var o_data = "[{type: 'pie', name:'销售分布',data:[";
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
		
   	    
		function drawPie(pie_data) {
			var strTitle = "aaa";
			var strSubTitle= "bbb";
//			document.write(pie_data);
//			var ss =ajaxRequestData();
//     		alert("kk"+ss);
		    $(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container2',  
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
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
/*
	            $.ajax({
					url: 'http://localhost:8080/dsyg_erp/ChartServlet.servlet?month=3&action=BBB',
	                type: "POST",
	                dataType: "text",
	                success: function (data) {
	                	var str_data= JSON.stringify(eval(data));
	                	var str_data2= str_data.replace(/\"/g,"");
						
						str_data2="[{type: 'pie', name: 'Browser share', data: [['0001', 36.2], ['0003', 63.8]]}]";
						options.series=eval(str_data2);
	                	var chart = new Highcharts.Chart(options);
	                }
				});		            
				*/
			});
		};
		
		function getData3M() {
			getData(-3);
		};
		function getData6M() {
			getData(-6);
		};
		function getData12M() {
			getData(-12);
		};
		
		function getData(mth, chart_data) {

     		$(document).ready(function() {  
		    	options = {  
		            chart: {  
		                renderTo: 'container',  
		            },  
		            title: {  
		                text: '销售曲线'  
		            },  
		            xAxis: {  
		                categories: get_X_Data(mth),
		            },  
		            yAxis: {  
		                min: 0,  
		                title: {  
		                    text: '金额 (万元)'  
		                }  
		            },  
		            legend: {  
		                layout: 'vertical',  
		                backgroundColor: '#FFFFFF',  
		                align: 'left',  
		                verticalAlign: 'top',  
		                x: 100,  
		                y: 70,  
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
		</script>
		<script src="${pageContext.request.contextPath}/js/themes/gray.js"></script>
	</head>
	<body>
		<!-- 3. Add the container -->
		<input type="hidden" id="h1" value="<s:property value="str" />" />
		<input type="hidden" id="h2" value="<s:property value="series" />" />
		<input type="hidden" id="h3" value="<s:property value="series_X" />" />
           <Input id="btn1" type=button value="3 Month" onClick="javascripts:getData3M();">
           <Input id="btn2" type=button value="6 Month" onClick="javascripts:getData6M();">
           <Input id="btn3" type=button value="12 Month" onClick="javascripts:getData12M();">
           <Input id="btn4" type=button value="1M" onClick="javascripts:m3Data();">
           <Input id="btn5" type=button value="Pie" onClick="javascripts:ajaxRequestData();">
		<table>
		<tr>
		<td>
		<div id="container" style="width: 480px; height: 250px; margin: 5 "></div>
		<div id="container2" style="width: 250px; height: 250px; margin: 5 "></div>
		</td>
		</tr>
		</table>
		AAAABCDE
	</body>
</html>
