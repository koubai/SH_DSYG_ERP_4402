<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Calendar</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/fullcalendar.css">
<link rel="stylesheet" type="text/css" href="../css/fancybox.css">
<style type="text/css">
#calendar{width:800px; margin:20px auto 10px auto}
.fancy{width:450px; height:auto}
.fancy h3{height:30px; line-height:30px; border-bottom:1px solid #d3d3d3; font-size:14px}
.fancy form{padding:10px}
.fancy p{height:28px; line-height:28px; padding:4px; color:#999}
.input{height:20px; line-height:20px; padding:2px; border:1px solid #d3d3d3; width:100px}
.btn{-webkit-border-radius: 3px;-moz-border-radius:3px;padding:5px 12px; cursor:pointer}
.btn_ok{background: #360;border: 1px solid #390;color:#fff}
.btn_cancel{background:#f0f0f0;border: 1px solid #d3d3d3; color:#666 }
.btn_del{background:#f90;border: 1px solid #f80; color:#fff }
.sub_btn{height:32px; line-height:32px; padding-top:6px; border-top:1px solid #f0f0f0; text-align:right; position:relative}
.sub_btn .del{position:absolute; left:2px}
</style>
<script src='http://code.jquery.com/jquery-1.9.1.js'></script>
<script src='http://code.jquery.com/ui/1.10.3/jquery-ui.js'></script>
<script src='<%=request.getContextPath()%>/js/fullcalendar.min.js'></script>
<script src='<%=request.getContextPath()%>/js/jquery.fancybox-1.3.1.pack.js'></script>

<!--
	说明：需要整合农历节气和节日，引入fullcalendar.js fullcalendar2.css
	不需要则引入：fullcalendar.min.js fullcalendar.css
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<% 
	String userId = (String)session.getAttribute("user_id");	
	String userColor = (String)session.getAttribute("user_color");
%>

<script type="text/javascript">
$(function() {	
//    $('#color1').colorPicker();

	//页面加载完初始化日历 
	$('#calendar').fullCalendar({
		//设置日历头部信息
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		firstDay:1,//每行第一天为周一 
        editable: true,//启用拖动 
		events: '${pageContext.request.contextPath}/JsonServlet.servlet',
		//点击某一天时促发
		dayClick: function(date, allDay, jsEvent, view) {
			var selDate =$.fullCalendar.formatDate(date,'yyyy-MM-dd');
			$.fancybox({
				'type':'ajax',
				'href':'${pageContext.request.contextPath}/event.jsp?action=add&date='+selDate
						//+ '&userName=' + userName + '&userColor=' + userColor
			});
    	},
		//单击事件项时触发 
        eventClick: function(calEvent, jsEvent, view) { 
            $.fancybox({ 
                'type':'ajax', 
                'href':'${pageContext.request.contextPath}/event.jsp?action=edit&id='+calEvent.id
                		//+ '&userName=' + userName + '&userColor=' + userColor
            }); 
        },
		
		//拖动事件 
		eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) { 
        	$.post("${pageContext.request.contextPath}/EventdoServlet.servlet?action=drag",{id:event.id,daydiff:dayDelta, minudiff:minuteDelta,allday:allDay},function(msg){ 
            	if(msg!=1){ 
                	alert(msg); 
                	revertFunc(); //恢复原状 
            	} 
        	}); 
    	},
		
		//日程事件的缩放
		eventResize: function(event,dayDelta,minuteDelta,revertFunc) { 
    		$.post("${pageContext.request.contextPath}/EventdoServlet.servlet?action=resize",{id:event.id,daydiff:dayDelta,minudiff:minuteDelta},function(msg){ 
        		if(msg!=1){ 
            		alert(msg); 
            		revertFunc(); 
        		} 
    		}); 
		},
		
		selectable: true, //允许用户拖动 
		select: function( startDate, endDate, allDay, jsEvent, view ){ 
	    	var start =$.fullCalendar.formatDate(startDate,'yyyy-MM-dd'); 
	    	var end =$.fullCalendar.formatDate(endDate,'yyyy-MM-dd'); 
	    	$.fancybox({ 
	        	'type':'ajax', 
	        	'href':'${pageContext.request.contextPath}/event.jsp?action=add&date='+start+'&end='+end
	        			//+ '&userName=' + userName + '&userColor=' + userColor
	    	}); 
		}
	});
});

</script>
</head>

<body>
<jsp:include page="./page/info2.jsp" flush="true" />
	<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
<div id="main" style="width:1060px">
   <h2 class="top_title"><br><br><a>欢迎使用DSYG ERP系统。(深圳)</a></h2>
   <div id='calendar'></div>
   <input type="hidden" name="userId" value="<%=userId%>">
   <input type="hidden" name="userColor" value="<%=userColor%>">
</div>
<p id="stat"><script type="text/javascript" src="/js/tongji.js"></script></p>
</body>
</html>