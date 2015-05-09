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
		    var chart;  
		    var chart1;
		    var d = $("#h1").val();  
		    var a = eval(d);  
		    var dd = $("#h2").val();  
		    var aaa = eval(dd);  
		    $(document).ready(function() {  
		        chart = new Highcharts.Chart({  
		            chart: {  
		                renderTo: 'container',  
		            },  
		            title: {  
		                text: '销售曲线'  
		            },  
		            subtitle: {  
		                text: '概况'  
		            },  
		            xAxis: {  
		                categories: [  
		                    '1月',  
		                    '2月',  
		                    '3月',  
		                    '4月',  
		                    '5月',  
		                    '6月',  
		                    '7月',  
		                    '8月',  
		                    '9月',  
		                    '10月',  
		                    '11月',  
		                    '12月'  
		                ]  
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
	                series: aaa
		        });  
		        chart1 = new Highcharts.Chart({  
		            chart: {  
		                renderTo: 'container1',
		                type: 'pie' 
		            },  
		            title: {  
		                text: '曲线'  
		            },  
		            subtitle: {  
		                text: '概况'  
		            },  
		            xAxis: {  
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
	                series: [{
	                	name: 'DDD',
	                	data: a
	                }]
		        });  
		    });  
        });  
		</script>
		<script src="${pageContext.request.contextPath}/js/themes/gray.js"></script>
	</head>
	<body>
		<!-- 3. Add the container -->
		<input type="hidden" id="h1" value="<s:property value="str" />" />
		<input type="hidden" id="h2" value="<s:property value="series" />" />
		<table>
		<tr>
		<td>
		<div id="container1" style="width: 300px; height:300px; margin: 10 "></div>
		</td>
		<td>
		<div id="container" style="width: 500px; height: 300px; margin: 10 "></div>
		</td>
		</tr>
		</table>
		AAAABCDE
	</body>
</html>
