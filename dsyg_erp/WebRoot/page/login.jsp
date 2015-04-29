<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>登录</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = document.documentElement.clientHeight;
		$("#container").css("height", h);
	});

	function login() {
		document.mainform.action = '../login/loginAction.action';
		document.mainform.submit();
	}
	
	/**
	 * 获取验证码
	 * @param obj
	 * @return
	 */
	function changeValidateCode(obj) {
		//获取当前的时间作为参数，无具体意义
	    var timenow = new Date().getTime();
	    //每次请求需要一个不同的参数，否则可能会返回同样的验证码
	    //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
	    obj.src='../index/rand.action?d='+timenow;
	    return false;
	}

	/**
	 * 获取验证码
	 * @param obj
	 * @return
	 */
	function changeValidate(id) {
		var timenow = new Date().getTime();
		if(document.getElementById(id) != null) {
			document.getElementById(id).src='../index/rand.action?d='+timenow;
		}
	}
</script>
</head>
<body>
	<div id="container">
		<div id="top" style="height:200px;">
			<div class="logobox" style="margin-top:60px;">
				<div class="logo_tittle"></div>
				<div class="logo"></div>
			</div>
		</div>
		<div class="content">
		<div style="position:absolute; margin-left: 430px; margin-top: 1px; text-align: center; color: red;">
			<s:actionmessage />
		</div>
			<s:form id="mainform" name="mainform" method="POST">
				<table class="login_tab" border="0" cellpadding="0" cellspacing="15">
					<tr>
						<td align="right">用户名</td>
						<td>
							<div class="box1">
								<div class="box1_left"></div>
								<div class="box1_center">
									<div>
										
									</div>
									<input name="userDto.userid" type="text" style="width:200px;" maxlength="8" id="userid" value="<s:property value="userDto.userid"/>"/>
								</div>
								<div class="box1_right"></div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">登录密码</td>
						<td>
							<div class="box1">
								<div class="box1_left"></div>
								<div class="box1_center">
									<input name="userDto.password" style="width:200px;" maxlength="16" type="password" id="password" />
								</div>
								<div class="box1_right"></div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">验证码</td>
						<td>
							<div class="box1">
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" name="userDto.verificationcode" style="width:130px;" id="verificationcode" maxlength="4" value="<s:property value="userDto.verificationcode"/>"/>
								</div>
								<div class="box1_right"></div>
							</div>
							<img title="看不清点我" id="randomImg" name="random" src="../index/rand.action" onclick="changeValidateCode(this)" class="yzhm" />
						</td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td>
							<div class="btn" style="margin-left:0;">
								<div class="box1_left"></div>
								<div class="box1_center">
									<input name="提交" type="submit" class="input80" value="登录" onclick="login();" />
								</div>
								<div class="box1_right"></div>
							</div>
							<div class="btn">
								<div class="box1_left"></div>
								<div class="box1_center">
									<input name="重置" type="button" class="input80" value="重置" onclick="reset();" />
								</div>
								<div class="box1_right"></div>
							</div>
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
