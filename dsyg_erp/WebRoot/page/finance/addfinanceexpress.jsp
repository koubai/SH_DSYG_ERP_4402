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
<title>快递单信息输入</title>
<script type="text/javascript">
	function add() {
		if(checkItem()) {
			if(confirm("确定追加吗？")) {
				document.mainform.action = "../finance/addFinanceExpressAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//客户
	function selectCustomer() {
		var url = "../customer/showSelectCustomerAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}

	//验证数据格式
	function checkItem() {
		//账目编号
		var receiptid = $("#receiptid").val().trim();
		//快递ID
		var expressid = $("#expressid").val().trim();
		//客户ID
		var res01 = $("#customerid").val().trim();
		//客户名
		var res02 = $("#customername").val().trim();
		//快递单号
		var res08 = $("#res08").val().trim();
		//快递名
		var expressname = $("#expressname").val().trim();
		//快递地址
		var expressaddress = $("#expressaddress").val().trim();
		//转运费用合计
		var amount = $("#amount").val().trim();
		//快递联系人
		var expressmanager = $("#expressmanager").val().trim();
		//快递联系人电话
		var expresstel = $("#expresstel").val().trim();
		//单据日期
		var tmpReceiptdate = $("#tmpReceiptdate").val().trim();
		//信箱
		var expressmail = $("#expressmail").val().trim();
		var status = $("#status").val().trim();
		//备注
		var tempNote = $("#tempNote").val().trim();
		if(expressid == "") {
			alert("请选择快递！");
			$("#expressid").focus();
			return;
		}
		if(expressname == "") {
			alert("快递名称不能为空！");
			$("#expressname").focus();
			return;
		}
		if(res02 == "") {
			alert("请选择客户！");
			$("#res02").focus();
			return;
		}
		if(res08 == "") {
			alert("快递单号不能为空！");
			$("#res08").focus();
			return;
		}
/*		if(expressaddress == "") {
			alert("快递地址不能为空！");
			$("#expressaddress").focus();
			return;
		}
*/		
		if(amount == "") {
			alert("转运费用合计不能为空！");
			$("#amount").focus();
			return;
		}
		if(!isReal(amount)) {
			alert("转运费用合计必须为大于0的实数！");
			$("#amount").focus();
			return;
		}
		if(expressmanager == "") {
			alert("快递联系人不能为空！");
			$("#expressmanager").focus();
			return;
		}
		if(expresstel == "") {
			alert("快递联系人电话不能为空！");
			$("#expresstel").focus();
			return;
		}
		if(tmpReceiptdate == "") {
			alert("单据日期不能为空！");
			$("#tmpReceiptdate").focus();
			return;
		}
/*		if(expressmail == "") {
			alert("信箱不能为空！");
			$("#expressmail").focus();
			return;
		}
*/
		if(status == "") {
			alert("请选择状态！");
			$("#status").focus();
			return;
		}
		if(tempNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tempNote").focus();
			return false;
		}
		$("#note").val($("#tempNote").val());
		$("#receiptdate").val($("#tmpReceiptdate").val());
		return true;
	}
	
	//快递
	function selectDelivery() {
		var url = "../delivery/showSelectDeliveryAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goBack() {
		window.location.href = "../finance/queryFinanceExpressAction.action";
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
					快递单信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="addFinanceDto.customerid" id="expressid"></s:hidden>
				<s:hidden name="addFinanceDto.receiptdate" id="receiptdate"></s:hidden>
				
				<s:hidden name="addFinanceDto.res01" id="customerid"></s:hidden>
				<s:hidden name="real_customermanager" id="customermanager"></s:hidden>
				<s:hidden name="real_customeraddress" id="customeraddress"></s:hidden>
				<s:hidden name="real_customertel" id="customertel"></s:hidden>
				<s:hidden name="real_customerfax" id="customerfax"></s:hidden>
				<s:hidden name="real_customermail" id="customermail"></s:hidden>
								
				<s:hidden name="addFinanceDto.note" id="note"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>账目编号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.receiptid" disabled="true" id="receiptid" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
									<input type="hidden" id="expressfax"/>
								</div>
								<div class="box1_right"></div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">快递</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customername" id="expressname" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectDelivery();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td align="right">
								<label class="pdf10">快递单号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.res08" id="res08" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">快递公司地址</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customeraddress" id="expressaddress" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">转运费用合计</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.amount" id="amount" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customermanager" id="expressmanager" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customertel" id="expresstel" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red"></font>单据日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpReceiptdate" disabled="disabled" style="width:285px;" value="<s:property value="addFinanceDto.showReceiptdate"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceiptdate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人信箱</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customermail" id="expressmail" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right" style="display: none;">
								<label class="pdf10"><font color="red">*</font>状态</label>
							</td>
							<td style="display: none;">
								<div class="box1_left"></div>
								<div class="box1_center">
									<select id="status" name="addFinanceDto.status" style="width: 300px;">
										<s:if test="%{addFinanceDto.status == 1}">
											<option value="">请选择</option>
											<option value="1" selected="selected">未收到发票, 未付款</option>
											<option value="10">收到发票, 安排付款</option>
											<option value="99">收到发票, 已付款</option>
										</s:if>
										<s:elseif test="%{addFinanceDto.status == 10}">
											<option value="">请选择</option>
											<option value="1">未收到发票, 未付款</option>
											<option value="10" selected="selected">收到发票, 安排付款</option>
											<option value="99">收到发票, 已付款</option>
										</s:elseif>
										<s:elseif test="%{addFinanceDto.status == 99}">
											<option value="">请选择</option>
											<option value="1">未收到发票, 未付款</option>
											<option value="10">收到发票, 安排付款</option>
											<option value="99" selected="selected">收到发票, 已付款</option>
										</s:elseif>
										<s:else>
											<option value="" selected="selected">请选择</option>
											<option value="1">未收到发票, 未付款</option>
											<option value="10">收到发票, 安排付款</option>
											<option value="99">收到发票, 已付款</option>
										</s:else>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>客户</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.res02" id="customername" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectCustomer();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>				
						
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:900px;" id="tempNote"><s:property value="addFinanceDto.note"/></textarea>
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
										<input class="input80" type="button" value="追加" onclick="add();"/>
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
