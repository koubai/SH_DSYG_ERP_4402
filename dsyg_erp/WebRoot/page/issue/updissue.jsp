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
<title>紧急事件对应修改</title>
<script type="text/javascript">
	function upd() {
		var tmpissuename = $("#tmpissuename").val().trim();
		$("#issuename").attr("value", tmpissuename);
		var tmpIssuedate = $("#tmpIssuedate").val().trim();
		$("#issuedate").attr("value", tmpIssuedate);
		var tmpnote = $("#tmpnote").val().trim();
		$("#note").attr("value", tmpnote);
		var tmpresult = $("#tmpresult").val().trim();
		$("#result").attr("value", tmpresult);
		if(tmpissuename == "") {
			alert("事件名称不能为空！");
			$("#tmpissuename").focus();
			return;
		}
		
		if(tmpissuename.length > 300) {
			alert("事件名称不能超过300个字！");
			$("#tmpissuename").focus();
			return;
		}
		
		if(tmpnote.length > 500) {
			alert("备注不能超过500个字！");
			$("#tmpnote").focus();
			return;
		}
		if(tmpresult.length > 300) {
			alert("处理结果不能超过300个字！");
			$("#tmpresult").focus();
			return;
		}
		document.mainform.action = '<c:url value="/issue/updIssueAction.action"></c:url>';
		document.mainform.submit();
	}
	
	function closepage() {
		document.mainform.action = '<c:url value="/issue/showIssueAction.action"></c:url>';
		document.mainform.submit();
	}
	
	//产品
	function selectProduct() {
		var url = "../product/showProductidSelectPage.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//客户
	function selectCustomer() {
		var type = $("#customertype").val().trim();
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
		if(customertype == "") {
			$("#customerid").val("");
			$("#customername").val("");
			$("#customername").attr("disabled", true);
			$("#typeButton").attr("disabled", true);
		} else {
			$("#customerid").val("");
			$("#customername").val("");
			$("#customername").attr("disabled", false);
			$("#typeButton").attr("disabled", false);
		}
	}

</script>
<base target="_self"/>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:scroll;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="updateIssueDto.issuename" id="issuename"></s:hidden>
	<s:hidden name="updateIssueDto.note" id="note"></s:hidden>
	<s:hidden name="updateIssueDto.result" id="result"></s:hidden>
	<s:hidden name="updateIssueDto.issuedate" id="issuedate"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					紧急事件对应修改
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
				<td><font color="red">*</font>事件名称</td>
				<td>
					<textarea name="" id="tmpissuename" cols="" rows="3" style="width:425px;"><s:property value="updateIssueDto.issuename"/></textarea>
				</td>
			</tr>
			<tr>
				<td>事件日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center date_input">
						<input type="text" id="tmpIssuedate" disabled="disabled" style="width:405px;" value="<s:property value="updateIssueDto.showIssuedate"/>" />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpIssuedate'));"></a>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<%-- <tr>
				<td>产品</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<input type="hidden" name="updateIssueDto.productid" id="productid" value="<s:property value="updateIssueDto.productid"/>"/>
						<s:textfield name="productinfo" id="productinfo" maxlength="20" cssStyle="width:360px;" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input40" type="button" value="检索" onclick="selectProduct();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr> --%>
			<tr>
				<td>客户类型</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="updateIssueDto.customertype" id="customertype" style="width: 425px;" onchange="selectCustomerType();">
							<s:if test='updateIssueDto.customertype == 1'>
								<option value="0">请选择</option>
								<option value="1" selected="selected">供应商</option>
								<option value="2">客户</option>
							</s:if>
							<s:elseif test='updateIssueDto.customertype == 2'>
								<option value="0">请选择</option>
								<option value="1">供应商</option>
								<option value="2" selected="selected">客户</option>
							</s:elseif>
							<s:else>
								<option value="0" selected="selected">请选择</option>
								<option value="1">供应商</option>
								<option value="2">客户</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>客户</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<input type="hidden" id="suppliermanager"/>
						<input type="hidden" id="suppliermanageraddr"/>
						<input type="hidden" id="suppliertel"/>
						<input type="hidden" id="supplierfax"/>
						<input type="hidden" id="suppliermail"/>
						<input type="hidden" id="supplierid"/>
						<input type="hidden" id="suppliername"/>
						
						<input type="hidden" id="customermanager"/>
						<input type="hidden" id="customeraddress"/>
						<input type="hidden" id="customertel"/>
						<input type="hidden" id="customerfax"/>
						<input type="hidden" id="customermail"/>
						<input type="hidden" name="updateIssueDto.customerid" id="customerid" value="<s:property value="updateIssueDto.customerid"/>"/>
						<s:if test='updateIssueDto.customertype == 1 || updateIssueDto.customertype == 2'>
							<s:textfield name="updateIssueDto.customername" id="customername" maxlength="64" cssStyle="width:425px;" theme="simple"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield name="updateIssueDto.customername" id="customername" disabled="true" maxlength="64" cssStyle="width:425px;" theme="simple"></s:textfield>
						</s:else>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:if test='updateIssueDto.customertype == 1 || updateIssueDto.customertype == 2'>
								<input id="typeButton" class="input40" type="button" value="检索" onclick="selectCustomer();" />
							</s:if>
							<s:else>
								<input id="typeButton" class="input40" type="button" value="检索" disabled="disabled" onclick="selectCustomer();" />
							</s:else>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>担当者</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<input type="hidden" name="updateIssueDto.handlerid" id="handlerid" value="<s:property value="updateIssueDto.handlerid"/>"/>
						<s:textfield name="updateIssueDto.handlername" id="handlername" maxlength="32" cssStyle="width:425px;" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input40" type="button" value="检索" onclick="selectUser('handlername');" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>状态</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="updateIssueDto.status" id="status" style="width: 425px;">
							<s:if test='updateIssueDto.status == 2'>
								<option value="1">请选择</option>
								<option value="2" selected="selected">未对应</option>
								<option value="3">对应中</option>
								<option value="4">完了</option>
							</s:if>
							<s:elseif test='updateIssueDto.status == 3'>
								<option value="1">请选择</option>
								<option value="2">未对应</option>
								<option value="3" selected="selected">对应中</option>
								<option value="4">完了</option>
							</s:elseif>
							<s:elseif test='updateIssueDto.status == 4'>
								<option value="1">请选择</option>
								<option value="2">未对应</option>
								<option value="3">对应中</option>
								<option value="4" selected="selected">完了</option>
							</s:elseif>
							<s:else>
								<option value="1" selected="selected">请选择</option>
								<option value="2">未对应</option>
								<option value="3">对应中</option>
								<option value="4">完了</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>处理结果</td>
				<td>
					<textarea name="" id="tmpresult" cols="" rows="3" style="width:425px;"><s:property value="updateIssueDto.result"/></textarea>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<textarea name="" id="tmpnote" cols="" rows="3" style="width:425px;"><s:property value="updateIssueDto.note"/></textarea>
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
</body>
</html>
