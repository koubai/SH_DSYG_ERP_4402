<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript">	     	    
/*		Date.prototype.format = function(format){ 
			var o = { 
				"M+" : this.getMonth()+1, 
				"d+" : this.getDate(),  
				"h+" : this.getHours(),  
				"m+" : this.getMinutes(),  
				"s+" : this.getSeconds(), 
				"q+" : Math.floor((this.getMonth()+3)/3), 
				"S" : this.getMilliseconds() 
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
*/		
		//+---------------------------------------------------  
		//| 求两个时间的天数差 日期格式为 YYYY-MM-dd   
		//+---------------------------------------------------  
		function daysBetween(DateOne,DateTwo)  
		{   
		    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
		    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
		    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
		  
		    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
		    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
		    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
		  
		    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
		    return Math.abs(cha);  
		}  
		
		function get_X_Data(d1, d2, type) {
			var m1;
			var m2;
			var duration;
	   		var dicArray = new Array();
	   		var tmp_date = new Date();
			if (type == "0"){
				// Daily
	            duration = daysBetween(d1, d2);
				tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2]));
				for(var i = 0; i < duration + 1; i++) {
					tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2])+i);
					dicArray[i] = tmp_date.format("yyyy-MM-dd");
//					alert("dicArray[i]="+ dicArray[i]);		
				}
			} else if (type == "1"){
				// Monthly
	            m1 = parseInt(d1.split("-")[1].replace(/^0+/, "")) + parseInt(d1.split("-")[0]) * 12;
	            m2 = parseInt(d2.split("-")[1].replace(/^0+/, "")) + parseInt(d2.split("-")[0]) * 12;
	            duration = m2 - m1;
				tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1,parseInt(d1.split("-")[2]));
				for(var i = 0; i < duration + 1; i++) {
//					tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1 + i,parseInt(d1.split("-")[2]));
					tmp_date.setFullYear(parseInt(d1.split("-")[0]),parseInt(d1.split("-")[1])-1 + i,1);
					dicArray[i] = tmp_date.format("yyyy-MM");
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
					dicArray[j] = tmp_date.format("yyyy-MM")+"季";
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
					dicArray[k] = tmp_date.format("yyyy");
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
			            col.innerHTML = "<style>strong1{float: right;}</style><strong1>"+u.data[w].toFixed(2).toString()+"</strong1>";
		             }
		        });				
			}
	    };  
   	    
		function ajaxRequestData(act, fromDate, toDate, dur_type, tit){
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
		                x: 500,  
		                y: 1,  
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

	    function IsDate(mystring) {  
	    	var reg = /^(\d{4})-(\d{2})-(\d{2})$/;  
	    	var str = mystring;  
	    	var arr = reg.exec(str);  
	    	if (str=="") return true;  
	    	if (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){  
		    	alert("请输入正确的日期,格式为YYYY-MM-DD");  
		    	return false;  
	    	}  
	    	return true;  
	    }  
	    
	    
</script>
