<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>页面未找到</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h=document.documentElement.clientHeight;
		$("#containermain").css("height",h);
	});
</script>
</head>
<body bgcolor="#ffffff">
	<div id="containermain">
		<div class=content>
			<p class="session_txt">页面未找到。</p>
		</div>
	</div>
</body>
</html>