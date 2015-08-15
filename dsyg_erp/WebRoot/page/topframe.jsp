<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/css/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
	<tr>
		<!--
		<td width="24%" height="64"><img src="<%=request.getContextPath()%>/images/logo1.gif" width="262" height="64"></td>
		-->
		<td width="61%" height="64"><div style="height: 50px; line-height: 25px;"><font size="4" style="font-weight:bold;" color="white">东升盈港ERP</font></div></td>
		<td width="39%" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="74%" height="40" class="admin_txt">　</td>
					<td width="22%">
						<div style="margin-right: 15px;">
							<a href="<c:url value="/login/logout.action"></c:url>" target=_parent><img src="<%=request.getContextPath()%>/images/out.gif" alt="安全退出" width="46" height="20" border="0"></a>
						</div>
					</td>
				</tr>
				<tr>
					<td height="17" colspan="2" align="right" style="font-size:12px">
						<div style="margin-right: 15px;">
							当前用户<img border="0" src="<%=request.getContextPath()%>/images/user.gif" width="13" height="14">：<b><%=session.getAttribute("USERID")%></b>&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>