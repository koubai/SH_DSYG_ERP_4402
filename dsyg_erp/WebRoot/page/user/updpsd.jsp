<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>修改密码</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = "../user/updUserPsdAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//旧密码
		var oldpassword = $("#oldpassword").val().trim();
		//新密码
		var password = $("#password").val().trim();
		//确认新密码
		var repassword = $("#repassword").val().trim();
		if(oldpassword == "") {
			alert("原始密码不能为空！");
			$("#oldpassword").focus();
			return false;
		}
		if(password == "") {
			alert("请输入新密码！");
			$("#password").focus();
			return false;
		}
		if(password != repassword) {
			alert("两次密码不一致！");
			$("#repassword").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<jsp:include page="../info.jsp" flush="true" />
			<div class="tittle">
				<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					修改密码
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" height="60" colspan="3"></td>
						</tr>
						<tr>
							<td class="red" align="center" colspan="3"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right" width="270">
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>原始密码</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:password name="updUserPsdDto.oldpassword" placeholder="请输入旧登录密码" id="oldpassword" cssStyle="width:300px;" maxlength="64" theme="simple"></s:password>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>新密码</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:password name="updUserPsdDto.password" placeholder="请输入新登录密码" id="password" cssStyle="width:300px;" maxlength="64" theme="simple"></s:password>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>确定新密码</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:password name="updUserPsdDto.repassword" placeholder="确认新密码" id="repassword" cssStyle="width:300px;" maxlength="64" theme="simple"></s:password>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
					</table>
				</div>
				<div class="trade">
					<table cellpadding="10" style="margin:0 auto;">
						<tr>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="更新" onclick="upd();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
					</table>
					<div style="height:225px;"></div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
