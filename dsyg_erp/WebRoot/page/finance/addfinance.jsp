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
<title>账目信息输入</title>
<script type="text/javascript">
	function add() {
		if(checkItem()) {
			if(confirm("确定追加吗？")) {
				document.mainform.action = "../finance/addFinanceAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//账目编号
		var receiptid = $("#receiptid").val().trim();
		//经手人
		var handler = $("#handler").val().trim();
		//关联单据编号
		var invoiceid = $("#invoiceid").val().trim();
		//单据日期
		var tmpReceiptdate = $("#tmpReceiptdate").val().trim();
		//主题
		var theme = $("#theme").val().trim();
		//方式
		var mode = $("#mode").val().trim();
		//对象
		var customername = $("#customername").val().trim();
		//联系人
		var customermanager = $("#customermanager").val().trim();
		//金额合计
		var amount = $("#amount").val().trim();
		//结算日期
		var tmpAccountdate = $("#tmpAccountdate").val().trim();
		//收款状态
		var status1 = $("#status1").val().trim();
		//付款状态
		var status2 = $("#status2").val().trim();
		
		if(receiptid == "") {
			//alert("账目编号不能为空！");
			//$("#receiptid").focus();
			//return;
		}
		if(handler == "") {
			alert("经手人不能为空！");
			$("#handler").focus();
			return;
		}
		if(invoiceid == "") {
			//alert("关联单据编号不能为空！");
			//$("#invoiceid").focus();
			//return;
		}
		if(tmpReceiptdate == "") {
			//alert("单据日期不能为空！");
			//$("#tmpReceiptdate").focus();
			//return;
		}
		if(theme == "") {
			alert("请选择主题！");
			$("#theme").focus();
			return;
		}
		if(mode == "") {
			//alert("请选择方式！");
			//$("#mode").focus();
			//return;
		}
		if(customername == "") {
			//alert("对象不能为空！");
			//$("#customername").focus();
			//return;
		}
		if(customermanager == "") {
			//alert("联系人不能为空！");
			//$("#customermanager").focus();
			//return;
		}
		
		if(amount == "") {
			alert("金额合计不能为空！");
			$("#amount").focus();
			return;
		}
		if(!isReal(amount)) {
			alert("金额合计必须为大于0的实数！");
			$("#amount").focus();
			return;
		}
		if(tmpAccountdate == "") {
			alert("结算日期不能为空！");
			$("#tmpAccountdate").focus();
			return;
		}
		if(mode == "1") {
			//收款
			if(status1 == "") {
				alert("请选择状态！");
				$("#status1").focus();
				return;
			}
			$("#status").val($("#status1").val());
		} else {
			//付款
			if(status2 == "") {
				alert("请选择状态！");
				$("#status2").focus();
				return;
			}
			$("#status").val($("#status2").val());
		}
		$("#receiptdate").val($("#tmpReceiptdate").val());
		$("#accountdate").val($("#tmpAccountdate").val());
		return true;
	}
	
	function changeMode() {
		var mode = $("#mode").val().trim();
		if(mode == "1") {
			//收款
			$("#status1").show();
			$("#status2").hide();
		} else if(mode == "2") {
			//付款
			$("#status2").show();
			$("#status1").hide();
		} else {
		}
	}
	
	//用户
	function selectUser() {
		var url = "../user/showSelectUserAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goBack() {
		window.location.href = "../finance/queryFinanceAction.action";
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
					账目信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="addFinanceDto.receiptdate" id="receiptdate"></s:hidden>
				<s:hidden name="addFinanceDto.accountdate" id="accountdate"></s:hidden>
				<s:hidden name="addFinanceDto.financetype" id="financetype"></s:hidden>
				<s:hidden name="addFinanceDto.status" id="status"></s:hidden>
				
				<s:hidden name="addFinanceDto.handler" id="handler"></s:hidden>
				<s:hidden name="addFinanceDto.handlername" id="handlername"></s:hidden>
				
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
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>经手人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmphandlername" disabled="disabled" style="width:285px;" value="<s:property value="addFinanceDto.handlername"/>" />
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectUser();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>关联单据编号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.invoiceid" id="invoiceid" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
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
								<label class="pdf10"><font color="red">*</font>主题</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addFinanceDto.theme" id="theme" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="financeDictList" id="financeDictList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{financeDictList[#st1.index].code == addFinanceDto.theme}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>方式</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addFinanceDto.mode" id="mode" style="width: 300px;" onchange="changeMode();">
										<s:if test='%{addFinanceDto.mode == "1"}'>
											<option value="">请选择</option>
											<option value="1" selected="selected">收款</option>
											<option value="2">付款</option>
										</s:if>
										<s:elseif test='%{addFinanceDto.mode == "2"}'>
											<option value="">请选择</option>
											<option value="1">收款</option>
											<option value="2" selected="selected">付款</option>
										</s:elseif>
										<s:else>
											<option value="" selected="selected">请选择</option>
											<option value="1">收款</option>
											<option value="2">付款</option>
										</s:else>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>对象</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customername" id="customername" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red"></font>联系人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.customermanager" id="customermanager" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>金额合计</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addFinanceDto.amount" id="amount" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>结算日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpAccountdate" disabled="disabled" style="width:285px;" value="<s:property value="addFinanceDto.showAccountdate"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpAccountdate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>状态</label>
							</td>
							<td colspan="3">
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:if test='%{addFinanceDto.mode == "2"}'>
										<select id="status1" style="width: 300px;display: none;"></select>
									</s:if>
									<s:else>
										<select id="status2" style="width: 300px;">
										<s:if test="%{addFinanceDto.status == 1}">
											<option value="">请选择</option>
											<option value="1" selected="selected">未对帐</option>
											<option value="10">已对帐, 未开票</option>
											<option value="20">已开票, 未收款</option>
											<option value="99">已开票, 已收款</option>
										</s:if>
										<s:elseif test="%{addFinanceDto.status == 10}">
											<option value="">请选择</option>
											<option value="1">未对帐</option>
											<option value="10" selected="selected">已对帐, 未开票</option>
											<option value="20">已开票, 未收款</option>
											<option value="99">已开票, 已收款</option>
										</s:elseif>
										<s:elseif test="%{addFinanceDto.status == 20}">
											<option value="">请选择</option>
											<option value="1">未对帐</option>
											<option value="10">已对帐, 未开票</option>
											<option value="20" selected="selected">已开票, 未收款</option>
											<option value="99">已开票, 已收款</option>
										</s:elseif>
										<s:elseif test="%{addFinanceDto.status == 99}">
											<option value="">请选择</option>
											<option value="1">未对帐</option>
											<option value="10">已对帐, 未开票</option>
											<option value="20">已开票, 未收款</option>
											<option value="99" selected="selected">已开票, 已收款</option>
										</s:elseif>
										<s:else>
											<option value="" selected="selected">请选择</option>
											<option value="1">未对帐</option>
											<option value="10">已对帐, 未开票</option>
											<option value="20">已开票, 未收款</option>
											<option value="99">已开票, 已收款</option>
										</s:else>
										</select>
									</s:else>
									<s:if test='%{addFinanceDto.mode == "1"}'>
										<select id="status1" style="width: 300px;">
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
									</s:if>
									<s:else>
										<select id="status2" style="width: 300px;display: none;"></select>
									</s:else>
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
