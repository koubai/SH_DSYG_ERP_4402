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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<title>客户跟踪信息修改</title>
<script type="text/javascript">
	function upd() {
		//初期化邮箱
		var mail_pr = $("#mail_pr1").val();
		var mail_suffix = $("#mail_suffix1").val();
		if(mail_pr != "" || mail_suffix != "") {
			$("#customermail1").attr("value", mail_pr + "@" + mail_suffix);
		} else {
			$("#customermail1").attr("value", "");
		}

		
		var customername = $("#customername").val().trim();
		var product = $("#product").val().trim();
		var tmpnote = $("#tmpnote").val().trim();
		$("#note").attr("value", tmpnote);
		var tmpproductinfo = $("#tmpproductinfo").val().trim();
		$("#productinfo").attr("value", tmpproductinfo);
		
		var tmpReceivedate = $("#tmpReceivedate").val().trim();
		$("#receivedate").attr("value", tmpReceivedate);
		
		if(customername == "") {
			alert("客户名称不能为空！");
			$("#customername").focus();
			return;
		}
		
		if(customername.length > 64) {
			alert("客户名称不能超过64个字！");
			$("#customername").focus();
			return;
		}
		
		var customermanager1 = $("#customermanager1").val();
		if(customermanager1.trim() == "") {
			alert("联系人不能为空！");
			$("#customermanager1").focus();
			return;
		}
		
		var customertel1 = $("#customertel1").val();
		if(customertel1.trim() == "") {
			alert("联系人电话不能为空！");
			$("#customertel1").focus();
			return;
		}
		
		var customeraddress1 = $("#customeraddress1").val();
		if(customeraddress1.trim() == "") {
			alert("联系人地址不能为空！");
			$("#customeraddress1").focus();
			return;
		}
		
		var customermail1 = $("#customermail1").val();
		if(customermail1 != "" && !isMail(customermail1)) {
			alert("联系人邮箱格式不正确！");
			$("#mail_pr1").focus();
			return;
		}
		
		if(tmpnote.length > 256) {
			alert("跟踪记录不能超过256个字！");
			$("#tmpnote").focus();
			return;
		}
		if(product.length > 300) {
			alert("产品ID一览不能超过300个字！");
			$("#productinfo").focus();
			return;
		}
		document.mainform.action = '<c:url value="/customertrack/updateCustomerTrackAction.action"></c:url>';
		document.mainform.submit();
	}
	
	function closepage() {
		document.mainform.action = '<c:url value="/customertrack/queryCustomerTrackList.action"></c:url>';
		document.mainform.submit();
	}
	
	//产品
	function selectProduct() {
		var url = "../product/showTrackProductSelectPage.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function clearProduct() {
		$("#tmpproductinfo").attr("value", "");
		$("#product").attr("value", "");
	}
	
	//客户
	function selectCustomer() {
		//var type = $("#customertype").val().trim();
		var type = "2";
		if(type == "1"){
			var url = "../supplier/showSelectSupplierAction.action";
			url += "?date=" + new Date();
			window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
			
			var supplierid = $("#supplierid").val().trim();
			$("#customerid").attr("value", supplierid);
			var suppliername = $("#suppliername").val().trim();
			$("#customername").attr("value", suppliername);
		}
		else if(type == "2") {
			var url = "../customer/showSelectCustomerAction.action";
			url += "?date=" + new Date();
			window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");

			var customermanager = $("#customermanager").val().trim();
			$("#customermanager1").attr("value", customermanager);
			var customeraddress = $("#customeraddress").val().trim();
			$("#customeraddress1").attr("value", customeraddress);
			var customertel = $("#customertel").val().trim();
			$("#customertel1").attr("value", customertel);
			var customerfax = $("#customerfax").val().trim();
			$("#customerfax1").attr("value", customerfax);
			var customermail = $("#customermail").val().trim();
			var index = customermail.indexOf('@');
			var mail_pr1 = customermail.substring(0, index);
			var mail_suffix1 = customermail.substring(index+1, customermail.length);
			$("#mail_pr1").attr("value", mail_pr1);
			$("#mail_suffix1").attr("value", mail_suffix1);
		}
		else {
			alert("请选择客户类型！");
			$("#customertype").focus();
		}
	}
	
	//用户
	function selectUser(id) {
		var theme1 = "";
		//strFlag=1采购单，strFlag=2销售单
		var url = "../customer/showUserSelectPage.action";
		url += "?strFieldno=" + theme1 + "&customerindex=" + id + "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function selectCustomerType() {
		var customertype = $("#customertype").val();
		if(customertype == 0) {
			$("#handlerid").val("");
			$("#handlerid").attr("disabled", true);
			$("#handleridButton").attr("disabled", true);
		} else {
			$("#handlerid").val("");
			$("#handlerid").attr("disabled", false);
			$("#handleridButton").attr("disabled", false);
		}
	}

</script>
<base target="_self"/>
</head>
<body>
<div id="containermain">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="updateCustomerTrackDto.product" id="product"></s:hidden>
	<s:hidden name="updateCustomerTrackDto.productinfo" id="productinfo"></s:hidden>
	<s:hidden name="updateCustomerTrackDto.note" id="note"></s:hidden>
	<s:hidden name="updateCustomerTrackDto.customermail1" id="customermail1"></s:hidden>
	<s:hidden name="updateCustomerTrackDto.receivedate" id="receivedate"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					客户跟踪信息修改
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
				<td width="150"><font color="red">*</font>客户名称</td>
				<td width="550">
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="updateCustomerTrackDto.customername" id="customername" cssStyle="width:425px;" maxlength="64" theme="simple"></s:textfield>
						<s:hidden name="customerid" id="customerid"></s:hidden>
						<s:hidden name="customermanager" id="customermanager"></s:hidden>
						<s:hidden name="customeraddress" id="customeraddress"></s:hidden>
						<s:hidden name="customertel" id="customertel"></s:hidden>
						<s:hidden name="customerfax" id="customerfax"></s:hidden>
						<s:hidden name="customermail" id="customermail"></s:hidden>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="customerButton" class="input40" type="button" value="检索" onclick="selectCustomer();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>客户来源</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="updateCustomerTrackDto.source" id="source" cssStyle="width:425px;" maxlength="16" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="85%" border="0">
							<tr>
								<td align="left" width="15%"><strong>联系人</strong></td>
								<td><font color="red">*</font>联系人姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerTrackDto.customermanager1" id="customermanager1" cssStyle="width:250px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerTrackDto.customertel1" id="customertel1" cssStyle="width:250px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerTrackDto.mail_pr1" id="mail_pr1" cssStyle="width:116px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerTrackDto.mail_suffix1" id="mail_suffix1" cssStyle="width:116px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerTrackDto.customeraddress1" id="customeraddress1" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerTrackDto.customerfax1" id="customerfax1" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>产品一览</td>
				<td>
					<div style="float:left;">
						<textarea name="" id="tmpproductinfo" cols="" rows="3" disabled="disabled" style="width:425px;"><s:property value="updateCustomerTrackDto.productinfo"/></textarea>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="productButton" class="input40" type="button" value="产品" onclick="selectProduct();" />
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-top: 5px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="productButton" class="input40" type="button" value="清除" onclick="clearProduct();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr><%-- 
			<tr>
				<td>客户类型</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="updateCustomerTrackDto.customertype" id="customertype" style="width: 120px;" onchange="selectCustomerType();">
							<s:if test='updateCustomerTrackDto.customertype == 0'>
								<option value="0" selected="selected">公司开拓</option>
								<option value="1">个人开拓</option>
							</s:if>
							<s:else>
								<option value="0">公司开拓</option>
								<option value="1" selected="selected">个人开拓</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr> --%>
			<tr>
				<td>担当者</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<%-- <s:if test='updateCustomerTrackDto.customertype == 0'>
							<s:textfield name="updateCustomerTrackDto.handlerid" id="handlerid" disabled="true" maxlength="32" cssStyle="width:360px;" theme="simple"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield name="updateCustomerTrackDto.handlerid" id="handlerid" maxlength="32" cssStyle="width:360px;" theme="simple"></s:textfield>
						</s:else> --%>
						<input type="hidden" name="updateCustomerTrackDto.handlerid" id="handlerid" value="<s:property value="updateCustomerTrackDto.handlerid"/>"/>
						<s:textfield name="updateCustomerTrackDto.handlername" id="handlername" maxlength="32" cssStyle="width:425px;" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<%-- <s:if test='updateCustomerTrackDto.customertype == 0'>
								<input id="handleridButton" class="input40" type="button" value="检索" disabled="disabled" onclick="selectUser('handlerid');" />
							</s:if>
							<s:else>
								<input id="handleridButton" class="input40" type="button" value="检索" onclick="selectUser('handlerid');" />
							</s:else> --%>
							<input id="handleridButton" class="input40" type="button" value="检索" onclick="selectUser('handlername');" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>接待日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center date_input">
						<input type="text" id="tmpReceivedate" disabled="disabled" style="width:405px;" value="<s:property value="updateCustomerTrackDto.showReceivedate"/>" />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceivedate'));"></a>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>状态</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="updateCustomerTrackDto.status" id="status" style="width: 425px;">
							<s:if test='updateCustomerTrackDto.status == 2'>
								<option value="1">请选择</option>
								<option value="2" selected="selected">成功</option>
								<option value="3">失败</option>
								<option value="4">进行中</option>
							</s:if>
							<s:elseif test='updateCustomerTrackDto.status == 3'>
								<option value="1">请选择</option>
								<option value="2">成功</option>
								<option value="3" selected="selected">失败</option>
								<option value="4">进行中</option>
							</s:elseif>
							<s:elseif test='updateCustomerTrackDto.status == 4'>
								<option value="1">请选择</option>
								<option value="2">成功</option>
								<option value="3">失败</option>
								<option value="4" selected="selected">进行中</option>
							</s:elseif>
							<s:else>
								<option value="1" selected="selected">请选择</option>
								<option value="2">成功</option>
								<option value="3">失败</option>
								<option value="4">进行中</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>跟踪记录</td>
				<td>
					<textarea name="" id="tmpnote" cols="" rows="3" style="width:430px;"><s:property value="updateCustomerTrackDto.note"/></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="修改" onclick="upd();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="关闭" onclick="closepage();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</s:form>
</div>
</body>
</html>
