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
<title>固定资产信息输入</title>
<script type="text/javascript">
	function add() {
		var id = $("#assetsno").val().trim();
		var assetsname = $("#assetsname").val().trim();
		var tmpnote = $("#tmpnote").val();
		$("#note").attr("value", tmpnote);
		if(id == "") {
			alert("资产编号不能为空！");
			$("#id").focus();
			return;
		}
		if(assetsname == "") {
			alert("资产名不能为空！");
			$("#assetsname").focus();
			return;
		}
		
		var registerdate = $("#registerdate").val();
		if(registerdate.trim() == "") {
			alert("登记日期不能为空！");
			$("#registerdate").focus();
			return;
		}
		
		var handler = $("#handler").val();
		if(handler.trim() == "") {
			alert("负责人不能为空！");
			$("#handler").focus();
			return;
		}
		if(tmpnote.length > 500) {
			alert("备注不能超过500个字！");
			$("#tmpnote").focus();
			return;
		}
		document.mainform.action = '<c:url value="/assets/addEtbAssetsAction.action"></c:url>';
		document.mainform.submit();
	}
	
	//页面关闭响应
	window.onunload = function() {
		//刷新父页面
		window.dialogArguments.document.mainform.action = '<c:url value="/assets/queryEtbAssetsList.action"></c:url>';
		window.dialogArguments.document.mainform.submit();
	};

</script>
<base target="_self"/>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:scroll;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="addAssetsDto.note" id="note"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					固定资产信息输入
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
				<td width="130"><font color="red">*</font>资产编号</td>
				<td width="500">
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addAssetsDto.assetsno" id="assetsno" cssStyle="width:350px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>资产名</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addAssetsDto.assetsname" id="assetsname" cssStyle="width:350px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>用途</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addAssetsDto.purpose" id="purpose" cssStyle="width:350px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>负责人</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addAssetsDto.handler" id="handler" cssStyle="width:350px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>登记日期(yyyy-mm-dd)</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addAssetsDto.registerdate" id="registerdate" cssStyle="width:350px;" maxlength="10" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<textarea name="" id="tmpnote" cols="" rows="5" style="width:350px;"><s:property value="addAssetsDto.note"/></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
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
