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
<title>入库单一览</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	function upd(id, status, tip) {
		if(confirm(tip)) {
			document.mainform.action = "../finance/updFinanceInStatusAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
			document.mainform.submit();
		}
	}
	
	
	//审核，输入发票号（取消）
	function cancel_auditorbillno() {
		//清空发票输入框
		$("#" + "strBillno1").val("");
		$("#" + "strBillno2").val("");
		$("#" + "strBillno3").val("");
		
		$("#" + "tmpReceiptdate1").val("");
		$("#" + "tmpReceiptdate2").val("");
		$("#" + "tmpReceiptdate3").val("");
		
		$("#" + "strBillamount1").val("");
		$("#" + "strBillamount2").val("");
		$("#" + "strBillamount3").val("");
		
		$("#" + "overlay").hide();
		$("#" + "divbillno").hide();
	}
	
	//审核，输入发票号（确定）
	function auditorbillno() {
		//判断发票号是否为空
		var strBillno1 = $("#" + "strBillno1").val().trim();
		var strBillno2 = $("#" + "strBillno2").val().trim();
		var strBillno3 = $("#" + "strBillno3").val().trim();
		
		var tmpReceiptdate1 = $("#tmpReceiptdate1").val().trim();
		var tmpReceiptdate2 = $("#tmpReceiptdate2").val().trim();
		var tmpReceiptdate3 = $("#tmpReceiptdate3").val().trim();
		
		var strBillamount1 = $("#strBillamount1").val().trim();
		var strBillamount2 = $("#strBillamount2").val().trim();
		var strBillamount3 = $("#strBillamount3").val().trim();
		
		if(strBillno1 == "" && strBillno2 == "" && strBillno3 == "") {
			alert("请输入发票号！");
			$("#strBillno1").focus();
			return;
		}
		
		if(strBillno1 != "") {
			if(strBillamount1 == "") {
				alert("请输入发票金额1！");
				$("#strBillamount1").focus();
				return;
			}
			if(!isReal(strBillamount1)) {
				alert("发票金额1必须为大于0的实数！");
				$("#strBillamount1").focus();
				return;
			}
			if(tmpReceiptdate1 == "") {
				alert("请输入开票日期1！");
				$("#tmpReceiptdate1").focus();
				return;
			}
		}
		if(strBillno2 != "") {
			if(strBillamount2 == "") {
				alert("请输入发票金额2！");
				$("#strBillamount2").focus();
				return;
			}
			if(!isReal(strBillamount2)) {
				alert("发票金额2必须为大于0的实数！");
				$("#strBillamount2").focus();
				return;
			}
			if(tmpReceiptdate2 == "") {
				alert("请输入开票日期2！");
				$("#tmpReceiptdate2").focus();
				return;
			}
		}
		if(strBillno3 != "") {
			if(strBillamount3 == "") {
				alert("请输入发票金额3！");
				$("#strBillamount3").focus();
				return;
			}
			if(!isReal(strBillamount3)) {
				alert("发票金额3必须为大于0的实数！");
				$("#strBillamount3").focus();
				return;
			}
			if(tmpReceiptdate3 == "") {
				alert("请输入开票日期3！");
				$("#tmpReceiptdate3").focus();
				return;
			}
		}
		$("#" + "strReceiptdate1").val(tmpReceiptdate1);
		$("#" + "strReceiptdate2").val(tmpReceiptdate2);
		$("#" + "strReceiptdate3").val(tmpReceiptdate3);
		var id = $("#" + "tmpFinanceId").val();
		var status = $("#" + "tmpFinanceStatus").val();
		if(confirm("确认提交吗？")) {
			document.mainform.action = "../finance/updFinanceInStatusBillnoAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
			document.mainform.submit();
		}
	}
	
	//审核
	function auditor(id, type, billno) {
		var status = $("#" + "statusList_" + id).val();
		if(type == "2") {
			//出库单记录
			if(status == "20" || status == "99") {
				if(billno == "") {
					//对于已开票记录，则弹出输入发票页面
					$("#" + "tmpFinanceId").val(id);
					$("#" + "tmpFinanceStatus").val(status);
					$("#" + "overlay").show();
					$("#" + "divbillno").show();
				} else {
					if(confirm("确认提交吗？")) {
						document.mainform.action = "../finance/updFinanceOutStatusAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
						document.mainform.submit();
					}
				}
			} else {
				if(confirm("确认提交吗？")) {
					document.mainform.action = "../finance/updFinanceOutStatusAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
					document.mainform.submit();
				}
			}
		} else {
			//入库单记录
			if(status == "10" || status == "99") {
				if(billno == "") {
					//对于已开票记录，则弹出输入发票页面
					$("#" + "tmpFinanceId").val(id);
					$("#" + "tmpFinanceStatus").val(status);
					$("#" + "overlay").show();
					$("#" + "divbillno").show();
				} else {
					if(confirm("确认提交吗？")) {
						document.mainform.action = "../finance/updFinanceInStatusAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
						document.mainform.submit();
					}
				}
			} else {
				if(confirm("确认提交吗？")) {
					document.mainform.action = "../finance/updFinanceInStatusAction.action?updWarehouserptId=" + id + "&updWarehouserptStatus=" + status;
					document.mainform.submit();
				}
			}
		}
	}
	
	function showOkBtn(id) {
		$("#" + "okbtn_" + id).show();
	}
	
	
	//查询日期赋值
	function setQueryDate() {
		$("#strWarehousedateLow").attr("value", $("#dateLow").val());
		$("#strWarehousedateHigh").attr("value", $("#dateHigh").val());
	}

	//查询数据
	function queryList() {
		setQueryDate();
		document.mainform.action = '../finance/queryFinanceInAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../finance/queryFinanceInAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		setQueryDate();
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../finance/turnFinanceInAction.action';
		document.mainform.submit();
	}

	//页跳转
	function turnPage() {
		var totalPage = "${page.totalPage}";
		var turnPage = document.getElementById("pagenum").value;
		//判断是否输入页码
		if ('' != turnPage) {
			//判断页码是否是大于0的数字
			if(!iscInteger(turnPage)){
				alert("页码必须是大于0的整数！");
				return;
			}
			turnPage = parseInt(turnPage);
			if(turnPage < 1){
				alert("页码必须是大于0的整数！");
				return;
			}
			//判断页码大小是否正确
			if(turnPage > parseInt(totalPage)){
				alert("页码不能超过最大页数！");
				return;
			}
			//换页
			changePage(turnPage - 1);
		} else {
			alert("页码不能为空！");
			return;
		}	
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<jsp:include page="../info.jsp" flush="true" />		
			<div class="tittle">
				<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center">
					入库单一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="strWarehousedateLow" id="strWarehousedateLow"/>
				<s:hidden name="strWarehousedateHigh" id="strWarehousedateHigh"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">入库日期</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="dateLow" value="<s:property value="strWarehousedateLow"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('dateLow'));"></a>
						</div>
						<div class="box1_right"></div>
						<label>-</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="dateHigh" value="<s:property value="strWarehousedateHigh"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('dateHigh'));"></a>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1">
						<label class="pdf10">供应商名</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<s:textfield name="strSuppliername" cssStyle="width:200px;" id="strSuppliername" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 160px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-3px; margin-left: -240px; color: red;">
						<s:actionmessage />
					</div>
					<div class="icons thums">
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="intPageSize * 35"/>px;">
						<div id="divbillno" style="position: absolute; margin-top: 60px; margin-left: 200px; display: none; z-index:1111;">
							<table style="height: 300px; width: 750px;" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
								<tr>
									<td width="60"><label class="pdf10">发票1</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<input type="text" style="display: none;" id="tmpFinanceId" />
											<input type="text" style="display: none;" id="tmpFinanceStatus" />
											<s:hidden id="strReceiptdate1" name="strReceiptdate1"></s:hidden>
											<s:hidden id="strReceiptdate2" name="strReceiptdate2"></s:hidden>
											<s:hidden id="strReceiptdate3" name="strReceiptdate3"></s:hidden>
											<s:textfield name="strBillno1" id="strBillno1" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td width="70"><label class="pdf10">发票金额1</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="strBillamount1" id="strBillamount1" cssStyle="width:150px;" maxlength="13" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td width="70"><label class="pdf10">开票日期1</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center date_input">
											<input type="text" disabled="disabled" style="width: 135px;" id="tmpReceiptdate1" value="<s:property value="strReceiptdate1"/>" maxlength="10" />
											<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceiptdate1'));"></a>
										</div>
										<div class="box1_right"></div>
									</td>
								</tr>
								<tr>
									<td><label class="pdf10">发票2</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="strBillno2" id="strBillno2" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td><label class="pdf10">发票金额2</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="strBillamount2" id="strBillamount2" cssStyle="width:150px;" maxlength="13" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td><label class="pdf10">开票日期2</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center date_input">
											<input type="text" disabled="disabled" style="width: 135px;" id="tmpReceiptdate2" value="<s:property value="strReceiptdate2"/>" maxlength="10" />
											<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceiptdate2'));"></a>
										</div>
										<div class="box1_right"></div>
									</td>
								</tr>
								<tr>
									<td><label class="pdf10">发票3</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="strBillno3" id="strBillno3" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td><label class="pdf10">发票金额3</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="strBillamount3" id="strBillamount3" cssStyle="width:150px;" maxlength="13" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</td>
									<td><label class="pdf10">开票日期3</label></td>
									<td>
										<div class="box1_left"></div>
										<div class="box1_center date_input">
											<input type="text" disabled="disabled" style="width: 135px;" id="tmpReceiptdate3" value="<s:property value="strReceiptdate3"/>" maxlength="10" />
											<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceiptdate3'));"></a>
										</div>
										<div class="box1_right"></div>
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input type="button" class="input80" value="确定" onclick="auditorbillno();"/>
										<input type="button" class="input80" value="取消" onclick="cancel_auditorbillno();"/>
									</td>
								</tr>
							</table>
						</div>
						<div id="overlay" class="black_overlay"></div>
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="40">序号</td>
								<td width="120">入库单号</td>
								<td width="80">仓库</td>
								<td width="160">供应商</td>
								<td width="100">入库单日期</td>
								<td width="110">金额（含税）</td>
								<td width="150">状态</td>
								<td width="80">操作</td>
							</tr>
							<s:iterator id="warehouserptList" value="warehouserptList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td><s:property value="warehouseno"/></td>
									<td><s:property value="warehousename"/></td>
									<td><s:property value="suppliername"/></td>
									<td><s:property value="showWarehousedate"/></td>
									<td align="right"><s:property value="totaltaxamount"/></td>
									<td>
										<select id="statusList_<s:property value="id"/>" style="width: 150px;" onchange="showOkBtn('<s:property value="id"/>');">
											<s:if test="%{warehousetype == 1}">
												<s:if test="%{status == 1}">
													<option value="1" selected="selected">未收到发票, 未付款</option>
													<option value="10">收到发票, 安排付款</option>
													<option value="15">未收到发票, 已付款</option>
													<option value="99">收到发票, 已付款</option>
												</s:if>
												<s:elseif test="%{status == 10}">
													<option value="1">未收到发票, 未付款</option>
													<option value="10" selected="selected">收到发票, 安排付款</option>
													<option value="15">未收到发票, 已付款</option>
													<option value="99">收到发票, 已付款</option>
												</s:elseif>
												<s:elseif test="%{status == 15}">
													<option value="1">未收到发票, 未付款</option>
													<option value="10">收到发票, 安排付款</option>
													<option value="15" selected="selected">未收到发票, 已付款</option>
													<option value="99">收到发票, 已付款</option>
												</s:elseif>
												<s:elseif test="%{status == 99}">
													<option value="1">未收到发票, 未付款</option>
													<option value="10">收到发票, 安排付款</option>
													<option value="15">未收到发票, 已付款</option>
													<option value="99" selected="selected">收到发票, 已付款</option>
												</s:elseif>
											</s:if>
											<s:elseif test="%{warehousetype == 2}">
												<s:if test="%{status == 1}">
													<option value="1" selected="selected">未对帐</option>
													<option value="10">已对帐, 未开票</option>
													<option value="15">已收款, 未对账</option>
													<option value="20">已开票, 未收款</option>
													<option value="99">已开票, 已收款</option>
												</s:if>
												<s:elseif test="%{status == 10}">
													<option value="1">未对帐</option>
													<option value="10" selected="selected">已对帐, 未开票</option>
													<option value="15">已收款, 未对账</option>
													<option value="20">已开票, 未收款</option>
													<option value="99">已开票, 已收款</option>
												</s:elseif>
												<s:elseif test="%{status == 15}">
													<option value="1">未对帐</option>
													<option value="10">已对帐, 未开票</option>
													<option value="15" selected="selected">已收款, 未对账</option>
													<option value="20">已开票, 未收款</option>
													<option value="99">已开票, 已收款</option>
												</s:elseif>
												<s:elseif test="%{status == 20}">
													<option value="1">未对帐</option>
													<option value="10">已对帐, 未开票</option>
													<option value="15">已收款, 未对账</option>
													<option value="20" selected="selected">已开票, 未收款</option>
													<option value="99">已开票, 已收款</option>
												</s:elseif>
												<s:elseif test="%{status == 99}">
													<option value="1">未对帐</option>
													<option value="10">已对帐, 未开票</option>
													<option value="15">已收款, 未对账</option>
													<option value="20">已开票, 未收款</option>
													<option value="99" selected="selected">已开票, 已收款</option>
												</s:elseif>
											</s:elseif>
										</select>
									</td>
									<td>
										<input id="okbtn_<s:property value="id"/>" style="display: none;" type="button" value="确认" onclick="auditor('<s:property value="id"/>', '<s:property value="warehousetype"/>', '<s:property value="financeBillno"/>')"/>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="pages">
						<ul>
							<li style="width: 180px;">
								<s:if test="intPageSize != null && intPageSize == 20">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" checked="checked" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
								</s:if>
								<s:elseif test="intPageSize != null && intPageSize == 30">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" checked="checked" onclick="changepagesize('30')"/>30
								</s:elseif>
								<s:else>
									显示：<input name="tmpPagesize" type="radio" value="10" checked="checked" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
								</s:else>
							</li>
							<li>第<strong>${page.startIndex + 1}</strong>页/共<strong>${page.totalPage==0?1:page.totalPage}</strong>页/共<strong>${page.totalCount}</strong>条记录</li>
							<li class="mgl15">跳转到
								<input type="text" id="pagenum" class="text" maxlength="4" size="4"/>
								<input type="button" value="GO" onclick="javascript:turnPage();"/>
							</li>
							<li class="mgl15">
								<a class="first" href="#" onclick="changePage(0);">首页</a>
							</li>
							<li>
								<s:if test="%{page.startIndex <= 0}">
									<a class="last" href="#">上一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${page.previousIndex}');">上一页</a>
								</s:else>
							</li>
							<li>
								<s:if test="%{page.nextIndex > page.totalPage - 1}">
									<a class="last" href="#">下一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${page.nextIndex}');">下一页</a>
								</s:else>
							</li>
							<li>
								<a class="next" href="#" onclick="changePage('${page.totalPage - 1}');">末页</a>
							</li>
						</ul>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
