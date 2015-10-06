<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript">
	function logout() {
		if(confirm("确定退出吗？")) {
			window.parent.location.href = '<c:url value="/login/logoutAction.action"></c:url>';
		}
	}
	
	function goHome() {
		var urlList = parent.window.frames['leftFrame'].document.getElementsByName("urlList");
		for(var i = 0; i < urlList.length; i++) {
			urlList[i].style.color = "";
		}
		parent.window.frames['mainFrame'].location = '<%=request.getContextPath()%>/intermana/showCalendarAction.action';
		//window.parent.location.href = '<c:url value="/home/showManageHomeAction.action"></c:url>';
	}
	
	$(function() {
		var rank = "${session.user_rank}";
		if(rank != "" && parseInt(rank) >= 80) {
			//经理级以上用
			setInterval("queryQa()", 60000);
		}
	});
	
	function queryQa() {
		$.ajax({
			type: "POST",
	 		url:  '${pageContext.request.contextPath}/qa/queryQaCountAction.action?time=' + new Date(),
	 		data: "",
	   		dataType:"json",
			success: function(data) {
				if(data == "0") {
					//没有新的Q/A数据
					hideQa();
				//} else if(data == "user is not login.") {
				//	alert("user is not login.");
				} else {
					//有新的Q/A数据
					showQa();
				}
			},
			error:function(data){
				//alert("data1=" + data);
			}
		});
	}
	
	function showQa() {
		$("#qaDiv").css("display", "block");
	}
	
	function hideQa() {
		$("#qaDiv").css("display", "none");
	}
	
	function goQa() {
		//$("#qaDiv").css("display", "none");
		//显示Q/A页面
		parent.window.frames['mainFrame'].location = '<%=request.getContextPath()%>/qa/queryQaAction.action';
	}
</script>
<div class="user">
	<div id="qaDiv" style="position:absolute; margin-top:-3px; margin-right: 165px; display: none;"><img width="19" height="19" style="cursor: pointer;" onclick="goQa();" alt="" src="<%=request.getContextPath()%>/images/mail.jpg" /></div>
	<span>用户：<%=session.getAttribute("user_name")%></span><span>登录时间：<%=session.getAttribute("login_time")%></span>
</div>