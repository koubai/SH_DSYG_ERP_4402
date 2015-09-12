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
<title>样品信息更新</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = "../sample/updSampleAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//产品ID
		var productid = $("#productid").val().trim();
		//品名
		var tradename = $("#tradename").val().trim();
		var quantity = $("#quantity").val().trim();
		var tempNote = $("#tempNote").val().trim();
		
		if(productid == "") {
			alert("请选择产品！");
			$("#tradename").focus();
			return;
		}
		if(tradename == "") {
			alert("请选择产品！");
			$("#tradename").focus();
			return;
		}
		if(quantity == "") {
			alert("数量不能为空！");
			$("#quantity").focus();
			return;
		}
		if(!isNumber(quantity)) {
			alert("数量必须是大于0的数字！");
			$("#quantity").focus();
			return;
		}
		if(tempNote ==  "") {
			alert("备注不能为空！");
			$("#tempNote").focus();
			return false;
		}
		if(tempNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tempNote").focus();
			return false;
		}
		//备注
		$("#note").val($("#tempNote").val());
		return true;
	}
	
	function selectProduct() {
		var url = '<%=request.getContextPath()%>/product/showSampleProductSelectPage.action';
		url += "?strFieldno=&date=" + new Date();
		
		//window.open(url);
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goBack() {
		window.location.href = "../sample/querySampleAction.action";
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
					样品信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data">
				<s:hidden name="updSampleDto.note" id="note"></s:hidden>
				<s:hidden name="updSampleDto.productid" id="productid"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>产品</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSampleDto.tradename" disabled="true" id="tradename" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>数量</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSampleDto.quantity" id="quantity" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="updSampleDto.note"/></textarea>
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
										<input class="input80" type="button" value="返回" onclick="goBack();"/>
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
