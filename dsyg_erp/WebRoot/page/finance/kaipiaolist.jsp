<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>财务信息一览</title>
<script type="text/javascript">
	$(function() {
	});
	
	function queryList() {
		document.mainform.action = "../finance/queryKaiPiaoAction.action";
		document.mainform.submit();
	}
	
	function checkCheckboxTr(tr, evt, id, amount) {
		var total = parseFloat($("#totalAmount").text());
		var ids = $("#strIds").val();
		if(ids == "") {
			ids = ",";
		}
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		if(inputs[0].checked) {
			inputs[0].checked = false;
			total = total - parseFloat(amount);
			ids = ids.replace("," + id + ",", ",");
		} else {
			inputs[0].checked = true;
			total = total + parseFloat(amount);
			ids = ids + id + ",";
		}
		total = total.toFixed(2);
		$("#strIds").val(ids);
		$("#totalAmount").text(total);
	}
	
	function kaipiao() {
		if($("#strIds").val() == "" || $("#strIds").val() == ",") {
			alert("请选择要开票的记录！");
			return;
		}
		if($("#strFaPiaoNo").val() == "") {
			alert("发票号不能为空！");
			$("#strFaPiaoNo").focus();
			return;
		}
		var total = parseFloat($("#totalAmount").text());
		var strFaPiaoAmount = parseFloat($("#strFaPiaoAmount").val());
		
		if($("#strFaPiaoAmount").val() == "") {
			alert("发票金额不能为空！");
			$("#strFaPiaoAmount").focus();
			return;
		}
		if($("#strFaPiaoAmount").val() != "" && !isReal($("#strFaPiaoAmount").val())) {
			alert("发票金额必须为大于0的实数！");
			$("#purchaseprice").focus();
			return;
		}
		if(total != strFaPiaoAmount) {
			alert("输入的发票金额和合计金额不符！");
			$("#strFaPiaoAmount").focus();
			return;
		}
		document.mainform.action = "../finance/kaiPiaoAction.action";
		document.mainform.submit();
	}
</script>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:hidden;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="strIds" id="strIds"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="searchbox">
			<div class="box1">
				<label class="pdf10">发票号：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<select name="strRes10" id="strRes10" style="width: 180px;">
						<s:if test='%{strRes10 == "1"}'>
							<option value="">所有数据</option>
							<option value="1" selected="selected">不为空</option>
							<option value="2">为空</option>
						</s:if>
						<s:elseif test='%{strRes10 == "2"}'>
							<option value="">所有数据</option>
							<option value="1">不为空</option>
							<option value="2" selected="selected">为空</option>
						</s:elseif>
						<s:else>
							<option value="" selected="selected">所有数据</option>
							<option value="1">不为空</option>
							<option value="2">为空</option>
						</s:else>
					</select>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="box1">
				<label class="pdf10">客户名：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strFaPiaoCustomername" id="strFaPiaoCustomername" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="box1">
				<label class="pdf10">入出库单号：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strFaPiaoInvoiceid" id="strFaPiaoInvoiceid" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="btn" style="margin-left: 0px;">
				<div class="box1_left"></div>
				<div class="box1_center">
					<input type="button" class="input40" value="检索" onclick="queryList();"/>
				</div>
				<div class="box1_right"></div>
			</div>
		</div>
		<div class="data_table" style="padding:0px;">
			<div class="tab_tittle">
				<table width="100%" border="1" cellpadding="5" cellspacing="0">
				</table>
			</div>
			<div class="tab_content">
				<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
					<tr class="tittle">
						<td width="30"></td>
						<td width="40">序号</td>
						<td width="110">账目编号</td>
						<td width="110">关联单据编号</td>
						<td width="80">主题</td>
						<td width="110">对象</td>
						<td width="60">联系人</td>
						<td width="60">经手人</td>
						<td width="90">单据日期</td>
						<td width="100">金额（含税）</td>
						<td width="90">结算日期</td>
						<td width="120">发票号</td>
						<td width="160">状态</td>
					</tr>
					<s:iterator id="kaipiaoList" value="kaipiaoList" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg" onclick="checkCheckboxTr(this, event, '<s:property value="id"/>', '<s:property value="amount"/>');">
						</s:if>
						<s:else>
							<tr onclick="checkCheckboxTr(this, event, '<s:property value="id"/>', '<s:property value="amount"/>');">
						</s:else>
							<td><input name="radioKey" type="checkbox" alt="<s:property value="invoiceid"/>" value="<s:property value="id"/>"/></td>
							<td><s:property value="#st1.index + 1"/></td>
							<td><s:property value="receiptid"/></td>
							<td><s:property value="invoiceid"/></td>
							<td>
								<s:if test="financetype == 1">
									采购
								</s:if>
								<s:elseif test="financetype == 2">
									订单
								</s:elseif>
								<s:elseif test="financetype == 3">
									物流
								</s:elseif>
								<s:elseif test="financetype == 4">
									<s:iterator id="financeDictList" value="financeDictList" status="st3">
										<s:if test="%{financeDictList[#st3.index].code == kaipiaoList[#st1.index].theme}">
											<s:property value="fieldname"/>
										</s:if>
									</s:iterator>
								</s:elseif>
								<s:else>
									<s:property value="financetype"/>
								</s:else>
							</td>
							<td><s:property value="customername"/></td>
							<td><s:property value="customermanager"/></td>
							<td><s:property value="handlername"/></td>
							<td><s:property value="showReceiptdate"/></td>
							<td align="right"><s:property value="amount"/></td>
							<td><s:property value="showAccountdate"/></td>
							<td>
								<div noWrap title="<s:property value="res10"/>" style="width:105px;text-overflow:ellipsis;overflow:hidden">
									<s:property value="res10"/>
								</div>
							</td>
							<td>
								<s:if test='mode == "1"'>
									<s:if test="%{status == 1}">
										未对帐
									</s:if>
									<s:elseif test="%{status == 10}">
										已对帐, 未开票
									</s:elseif>
									<s:elseif test="%{status == 15}">
										已收款, 未对账
									</s:elseif>
									<s:elseif test="%{status == 20}">
										已开票, 未收款
									</s:elseif>
									<s:elseif test="%{status == 99}">
										已开票, 已收款
									</s:elseif>
								</s:if>
								<s:else>
									<s:if test="%{status == 1}">
										未收到发票, 未付款
									</s:if>
									<s:elseif test="%{status == 10}">
										收到发票, 安排付款
									</s:elseif>
									<s:elseif test="%{status == 15}">
										未收到发票, 已付款
									</s:elseif>
									<s:elseif test="%{status == 99}">
										收到发票, 已付款
									</s:elseif>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="btns" style="margin-top:40px; margin-left: 0px;">
			<table border="0" style="margin:0 auto;">
				<tr>
					<td width="850" align="right">所选单据合计金额：</td>
					<td align="right" width="100">
						<span style="float: right; margin-right: 50px;">
							<span id="totalAmount">0.00</span>
						</span>
					</td>
				</tr>
			</table>
			<table border="0" style="margin:0 auto;">
				<tr>
					<td width="60">发票号</td>
					<td align="left" width="1000">
						<s:textfield name="strFaPiaoNo" id="strFaPiaoNo" maxlength="32" cssStyle="width:150px;" theme="simple"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>发票金额</td>
					<td align="left">
						<s:textfield name="strFaPiaoAmount" id="strFaPiaoAmount" maxlength="32" cssStyle="width:150px;" theme="simple"></s:textfield>
					</td>
				</tr>
			</table>
			<table border="0" style="margin:0 auto;">
				<tr>
					<td align="center">
						<div class="btn">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input type="button" class="input80" value="追加" onclick="kaipiao();"/>
							</div>
							<div class="box1_right"></div>
						</div>
						<div class="btn">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input type="button" class="input80" value="关闭" onclick="window.close();"/>
							</div>
							<div class="box1_right"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>
