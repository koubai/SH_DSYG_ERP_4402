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
   	    
		function viewData(data) {
			var jsonobj=eval(data);  

			var newLine = $("#planTable").length;
			var d=document.getElementById('planTable').deleteTHead();
			var x=document.getElementById('planTable').createTHead();
			var row = x.insertRow(0);   
            var col = row.insertCell(0);                
			 col.innerHTML="Name";
             col = row.insertCell(1);   
             col.innerHTML =  "Data1";   
             col = row.insertCell(2);   
             col.innerHTML =  "Data2";;   
             col = row.insertCell(3);   
             col.innerHTML =  "Data3";;   
             col = row.insertCell(4);   
             col.innerHTML =  "Data4";;   
             col = row.insertCell(5);   
             col.innerHTML =  "Data5";;   
             col = row.insertCell(6);   
             col.innerHTML =  "Data6";;   
             col = row.insertCell(7);   
             col.innerHTML =  "Data7";;   
             col = row.insertCell(8);   
             col.innerHTML =  "Data8";;   
             col = row.insertCell(9);   
             col.innerHTML =  "Data19";;   
             col = row.insertCell(10);   
             col.innerHTML =  "Data10";;   
             col = row.insertCell(11);   
             col.innerHTML =  "Data11";;   
             col = row.insertCell(12);   
             col.innerHTML =  "Data12";;   
             
			d=document.getElementById('planTable').deleteTFoot();
			x=document.getElementById('planTable').createTFoot();
	        $.each(jsonobj, function(i, u){	         				
//				planTable.deleteRow(newLine);
	    		 var row = x.insertRow(i); 
	             var col = row.insertCell(0);                
	             col.innerHTML = u.name;
	                
	             col = row.insertCell(1);   
	             col.innerHTML =  u.data[0];   
	             col = row.insertCell(2);   
	             col.innerHTML =  u.data[1];   
	             col = row.insertCell(3);   
	             col.innerHTML =  u.data[2];   
	             col = row.insertCell(4);   
	             col.innerHTML =  u.data[3];   
	             col = row.insertCell(5);   
	             col.innerHTML =  u.data[4];   
	             col = row.insertCell(6);   
	             col.innerHTML =  u.data[5];   
	             col = row.insertCell(7);   
	             col.innerHTML =  u.data[6];   
	             col = row.insertCell(8);   
	             col.innerHTML =  u.data[7];   
	             col = row.insertCell(9);   
	             col.innerHTML =  u.data[8];   
	             col = row.insertCell(10);   
	             col.innerHTML =  u.data[9];   
	             col = row.insertCell(11);   
	             col.innerHTML =  u.data[10];   
	             col = row.insertCell(12);   
	             col.innerHTML =  u.data[11];   
	        });
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
//			alert("mth: "+mth+ " from_date:"+ from_date+ " to_date:"+ to_date);		
			
            $.ajax({
				url: 'http://localhost:8080/dsyg_erp/ChartServlet.servlet?action='+act+'&from_date='+from_date+'&to_date='+to_date,
                type: "POST",
                dataType: "text",
                success: function (data) {
                	var str_data= JSON.stringify(eval(data));
                	var str_data2= str_data.replace(/\"/g,"");
                   	o_data=str_data2;            
//        			alert("org_data:"+str_data2);
	       			var pie_data = getPieData(data, tit);
//       			alert("pie_data:"+pie_data);
        			drawPie(pie_data, tit);
        			drawChart(mth, o_data, tit);
        			viewData(o_data);
                }
            });
//     		alert(o_data);
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
		                x: 5,  
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

	    function getSaleData3M() {
			ajaxRequestData("getSaleData", -3, "销售");
		};
		function getSaleData6M() {
			ajaxRequestData("getSaleData", -6, "销售");
		};
		function getSaleData12M() {
			ajaxRequestData("getSaleData", -12, "销售");
		};
		
		</script>
		<script src="${pageContext.request.contextPath}/js/themes/gray.js"></script>
	</head>
	<body>
		<!-- 3. Add the container -->
		<input type="hidden" id="h1" value="<s:property value="str" />" />
		<input type="hidden" id="h2" value="<s:property value="series" />" />
		<input type="hidden" id="h3" value="<s:property value="series_X" />" />
           <Input id="btn1" type=button value="3 Month" onClick="javascripts:getSaleData3M();">
           <Input id="btn2" type=button value="6 Month" onClick="javascripts:getSaleData6M();">
           <Input id="btn3" type=button value="12 Month" onClick="javascripts:getSaleData12M();">
		<table>
		<tr>
		<td>
		<div id="container" style="width: 600px; height: 300px; margin: 2 "></div>
		<div id="container2" style="width: 300px; height: 300px; margin: 2 "></div>
		</td>
		</tr>
		</table>
		<div id="dateMessage">   
        <table id="planTable" border="1">   
        </table>   
    </div>
	</body>
</html>
