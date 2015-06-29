<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>DSYG_ERP</title>
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
			<p class="session_txt">欢迎使用DSYG ERP系统。</p>
		</div>
		<div class=content>
			<a href="${pageContext.request.contextPath}/chart/showSaleInfoMainChartAction.action">销售信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showSaleDetailInfoMainChartAction.action">销售详细信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showPurchaseInfoMainChartAction.action">采购信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showDeliveryInfoMainChartAction.action">物流信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showAccountInfoMainChartAction.action">财务信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showSupplierInfoMainChartAction.action">供应商信息分析</a><br>
			<a href="${pageContext.request.contextPath}/chart/showCustomerInfoMainChartAction.action">客户信息分析</a><br>
		</div>
		<div class=content>
			<a href="${pageContext.request.contextPath}/intermana/showCalendarAction.action">考勤管理一览</a><br>
		</div>		
	</div>
</body>
</html>