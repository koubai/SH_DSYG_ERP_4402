<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>员工档案信息输入</title>
<script type="text/javascript">
	function add() {
		var id = $("#userno").val().trim();
		var username = $("#username").val().trim();
		var tmpnote = $("#tmpnote").val();
		$("#note").attr("value", tmpnote);
		var tmppersonaldesc = $("#tmppersonaldesc").val();
		$("#personaldesc").attr("value", tmppersonaldesc);
		if(id == "") {
			alert("员工编号不能为空！");
			$("#userno").focus();
			return;
		}
		if(username == "") {
			alert("员工名不能为空！");
			$("#username").focus();
			return;
		}
		
		var registdate = $("#registdate").val();
		if(registdate.trim() == "") {
			alert("登记时间不能为空！");
			$("#registdate").focus();
			return;
		}
		
		var employeddate = $("#employeddate").val();
		if(employeddate.trim() == "") {
			alert("入职时间不能为空！");
			$("#employeddate").focus();
			return;
		}
		if(tmpnote.length > 500) {
			alert("备注不能超过500个字！");
			$("#tmpnote").focus();
			return;
		}
		if(tmppersonaldesc.length > 500) {
			alert("档案不能超过500个字！");
			$("#tmppersonaldesc").focus();
			return;
		}
		document.mainform.action = '<c:url value="/personal/addEtbPersonalAction.action"></c:url>';
		document.mainform.submit();
	}
	
	//页面关闭响应
	window.onunload = function() {
		//刷新父页面
		window.dialogArguments.document.mainform.action = '<c:url value="/personal/queryEtbPersonalList.action"></c:url>';
		window.dialogArguments.document.mainform.submit();
	};

</script>
<base target="_self"/>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:scroll;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="addPersonalDto.note" id="note"></s:hidden>
	<s:hidden name="addPersonalDto.personaldesc" id="personaldesc"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					员工档案信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
		</div>
		<div style="position:absolute; margin-left: 150px; margin-top: 10px; text-align: center; color: red;">
			<s:actionmessage />
		</div>
		<table style="margin-left: 50px; margin-top: 30px;" border="0" cellspacing="15" cellpadding="0">
			<tr>
				<td width="100"><font color="red">*</font>员工编号</td>
				<td width="200">
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.userno" id="userno" cssStyle="width:100px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td width="100"></td>
				<td width="200"></td>
			</tr>
			<tr>
				<td><font color="red">*</font>员工姓名</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.username" id="username" cssStyle="width:100px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>性别</td>
				<td>
					<s:radio list="#{'0':'女','1':'男'}" name="addPersonalDto.sex" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<td>岗位</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.post" id="post" cssStyle="width:100px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>直接领导</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.superior" id="superior" cssStyle="width:100px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>手机</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.phone" id="phone" cssStyle="width:100px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>电话</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.tell" id="tell" cssStyle="width:100px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>地址</td>
				<td colspan="3">
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.address" id="address" cssStyle="width:430px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>入职日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.employeddate" id="employeddate" cssStyle="width:100px;" maxlength="10" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td>离职日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.retiredate" id="retiredate" cssStyle="width:100px;" maxlength="10" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>登记日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addPersonalDto.registdate" id="registdate" cssStyle="width:100px;" maxlength="10" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
				<td colspan="2">日期格式为：yyyy-mm-dd</td>
				<td></td>
			</tr>
			<tr>
				<td>档案</td>
				<td colspan="3">
					<textarea name="" id="tmppersonaldesc" cols="" rows="5" style="width:430px;"><s:property value="addPersonalDto.personaldesc"/></textarea>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="3">
					<textarea name="" id="tmpnote" cols="" rows="5" style="width:430px;"><s:property value="addPersonalDto.note"/></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="追加" onclick="add();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="关闭" onclick="window.close();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</s:form>
</body>
</html>
