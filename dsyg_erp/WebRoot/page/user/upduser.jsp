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
<title>用户信息编辑</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = "../user/updUserAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//var userid = $("#userid").val().trim();
		var username = $("#username").val().trim();
		var rolecode = $("#rolecode").val().trim();
		var status = $("#status").val().trim();
		var tempNote = $("#tempNote").val().trim();
		
		if(username == "") {
			alert("用户名不能为空！");
			$("#username").focus();
			return;
		}
		if(rolecode == "") {
			alert("用户角色不能为空！");
			$("#rolecode").focus();
			return;
		}
		if(status == "") {
			alert("请选择用户状态！");
			$("#status").focus();
			return;
		}
		if(tempNote.length > 128) {
			alert("备注不能超过128个字！");
			$("#tempNote").focus();
			return false;
		}
		//备注
		$("#note").val($("#tempNote").val());
		return true;
	}
	
	function goUserList() {
		window.location.href = "../user/queryUserManageAction.action";
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<div class="tittle">
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					用户信息编辑
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="updUserDto.note" id="note"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>用户ID</label>
							</td>
							<td>
								<label class="pdf10"><s:property value="updUserDto.userid"/></label>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>用户名</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updUserDto.username" id="username" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>角色</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updUserDto.rolecode" id="rolecode" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="roleList" id="roleList" status="st1">
											<option value="<s:property value="rolecode"/>" <s:if test="%{roleList[#st1.index].rolecode == updUserDto.rolecode}">selected</s:if>><s:property value="rolename"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>状态</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updUserDto.status" id="status" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:if test="updUserDto.status == 1">
											<option value="1" selected="selected">有效</option>
											<option value="2">无效</option>
										</s:if>
										<s:elseif test="updUserDto.status == 2">
											<option value="1">有效</option>
											<option value="2" selected="selected">无效</option>
										</s:elseif>
										<s:else>
											<option value="1">有效</option>
											<option value="2">无效</option>
										</s:else>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<hidden name="updUserDto.belongto" id="belongto" value="0"></hidden>
							<!-- <td align="right">
								所属地
								<select name="updUserDto.belongto" id="belongto" style="width: 100px;">
									<s:if test="updUserDto.belongto == 0">
										<option value="0" selected="selected">上海</option>
										<option value="1" >深圳</option>
										<option value="99" >全部</option>
									</s:if>
									<s:elseif test="updUserDto.belongto == 1">
										<option value="0" >上海</option>
										<option value="1" selected="selected">深圳</option>
										<option value="99" >全部</option>
									</s:elseif>
									<s:elseif test="updUserDto.belongto == 99">
										<option value="0" >上海</option>
										<option value="1" >深圳</option>
										<option value="99" selected="selected">全部</option>
									</s:elseif>
									<s:else>
									</s:else>
								</select>
							</td> -->						
							<td align="right"></td>
							<td align="right"></td>
							<td align="right"></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="updUserDto.note"/></textarea>
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
										<input class="input80" type="button" value="修改" onclick="upd();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="返回" onclick="goUserList();"/>
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
