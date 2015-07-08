<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css" />
<script type="text/javascript">
	function logout() {
		window.parent.location.href = '<c:url value="/login/logoutAction.action"></c:url>';
	}
	
	function goHome() {
		window.parent.location.href = '<c:url value="/frame/showManageHomeAction.action"></c:url>';
	}
</script>
<div class="user2"><span>用户：<%=session.getAttribute("user_name")%></span><span>登录时间：<%=session.getAttribute("login_time")%></span></div>