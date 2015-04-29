<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
html, body {
height:100%;
margin:0;
} 
.middle {
	position:relative;
	width:7px;
	height:100%;
	background:url('<%=request.getContextPath()%>/images/bg_center.gif') repeat-y 0 0;
}
.middle a.btnleft, .middle a.btnright {
	position:absolute;
	top:50%;
	display:block;
	width:7px;
	height:48px;
	background:url(<%=request.getContextPath()%>/images/icon_arrow.gif) 0 0 no-repeat;
}
.middle a.btnright {
	background:url(<%=request.getContextPath()%>/images/icon_arrow.gif) -21px 0 no-repeat;
}
.aftlogin .middle {
	background:url(<%=request.getContextPath()%>/images/bg_center.gif) -7px 0 repeat-y;
}
</style>
<script>
	function setBg(){
		document.body.setAttribute("class","aftlogin");
		document.body.setAttribute("className","aftlogin"); //照顾IE	
	}
	
	function resizeFrame(){
		parent.resizeFrame();
	}
</script>
</head>
<body onload="setBg()">
<div class="middle">
	<a href="#" id="mid_resize" class="btnleft" onclick="resizeFrame()"></a>
</div>
</body>
</html>
