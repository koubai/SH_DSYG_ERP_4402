<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盛大数据服务平台</title>
<script>
	
	function resizeFrame(){
		var cols =parent.info_frame.cols;
		if(cols=="182,7,*"){
			parent.info_frame.cols="0,7,*";
			document.frames["middleFrame"].document.getElementById("mid_resize").setAttribute("class","btnright");
			document.frames["middleFrame"].document.getElementById("mid_resize").setAttribute("className","btnright");
		}
		else{
			parent.info_frame.cols="182,7,*";
			document.frames["middleFrame"].document.getElementById("mid_resize").setAttribute("class","btnleft");
			document.frames["middleFrame"].document.getElementById("mid_resize").setAttribute("className","btnleft");
		}
	}

</script>
</head>
<frameset rows="10,*">
	<frame src="${pageContext.request.contextPath}/frame/showTopAction.action" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" name="topFrame" id="topFrame" title="topFrame"/>
	<frameset cols="182,90,*" id="info_frame">
		<frame src="${pageContext.request.contextPath}/frame/showLeftAction.action" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" name="leftFrame" id="leftFrame" title="leftFrame" />
		<frame src="${pageContext.request.contextPath}/page/middle.htm" name="middleFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" />
		<frame src="${pageContext.request.contextPath}/frame/showMainFrameAction.action" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
		<frame src="${pageContext.request.contextPath}/page/middle.htm" name="middleFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" />
</frameset>
</html>
