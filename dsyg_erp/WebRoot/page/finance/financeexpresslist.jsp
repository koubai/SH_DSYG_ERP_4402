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
<title>快递单管理</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	function add() {
		document.mainform.action = "../finance/showAddFinanceExpressAction.action";
		document.mainform.submit();
	}
	
	function upd() {
		var id = getSelectedID();
		var alt = getSelectedAlt();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			if(alt != "") {
				alert("该记录有关联单据号，不能修改！");
				return;
			} else {
				document.mainform.action = "../finance/showUpdFinanceExpressAction.action?updFinanceId=" + id;
				document.mainform.submit();
			}
		}
	}
	
	//删除
	function del() {
		var id = getSelectedID();
		var alt = getSelectedAlt();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			if(alt != "") {
				alert("该记录有关联单据号，不能删除！");
				return;
			} else {
				if(confirm("确定删除吗？")) {
					document.mainform.action = "../finance/delFinanceExpressAction.action?delFinanceId=" + id;
					document.mainform.submit();
				}
			}
		}
	}
	
	function getSelectedAlt() {
		var alt = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				alt = list[i].alt;
				break;
			}
		}
		return alt;
	}
	
	function getSelectedID() {
		var id = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				break;
			}
		}
		return id;
	}
	
	//查询日期赋值
	function setQueryDate() {
		$("#strReceiptdateLow").attr("value", $("#receiptdateLow").val());
		$("#strReceiptdateHigh").attr("value", $("#receiptdateHigh").val());
	}

	//查询数据
	function queryList() {
		setQueryDate();
		document.mainform.action = '../finance/queryFinanceExpressAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../finance/queryFinanceExpressAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		setQueryDate();
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../finance/turnFinanceExpressAction.action';
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
					快递单管理
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="strReceiptdateLow" id="strReceiptdateLow"/>
				<s:hidden name="strReceiptdateHigh" id="strReceiptdateHigh"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">单据日期</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="receiptdateLow" value="<s:property value="strReceiptdateLow"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('receiptdateLow'));"></a>
						</div>
						<div class="box1_right"></div>
						<label>-</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="receiptdateHigh" value="<s:property value="strReceiptdateHigh"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('receiptdateHigh'));"></a>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" >
						<label class="pdf10" style="margin-left: 5px; width: 55px">客户名称</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strCustomerName" id="strCustomerName" cssStyle="width:120px;" maxlength="50" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" >
						<label class="pdf10" style="margin-left: 5px; width: 55px">快递单号</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strExpressno" id="strExpressno" cssStyle="width:120px;" maxlength="50" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" >
						<label class="pdf10" style="margin-left: 5px; width: 35px">主题</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strExpressName" id="strExpressName" cssStyle="width:120px;" maxlength="50" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 20px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-40px; margin-left: -550px; color: red;">
						<s:actionmessage />
					</div>
					<div class="icons thums">
						<a class="add" onclick="add();">增加</a>
						<a class="edit" onclick="upd();">修改</a>
						<a class="delete" onclick="del();">删除</a>
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="intPageSize * 35"/>px;">
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="10"></td>
								<td width="40">序号</td>
								<td width="80">主题</td>
								<td width="80">快递单号</td>
								<td width="50">经手人</td>
								<td width="80">单据日期</td>
								<td width="150">客户名称</td>
								<td width="80">金额（含税）</td>
								<td width="110">状态</td>
								<td width="120">备注</td>
							</tr>
							<s:iterator id="financeList" value="financeList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg" onclick="checkRadioTr(this, event);">
								</s:if>
								<s:else>
									<tr onclick="checkRadioTr(this, event);">
								</s:else>
									<td><input name="radioKey" type="radio" alt="<s:property value="invoiceid"/>" value="<s:property value="id"/>"/></td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<!-- <td><s:property value="receiptid"/></td>
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
												<s:if test="%{financeDictList[#st3.index].code == financeList[#st1.index].theme}">
													<s:property value="fieldname"/>
												</s:if>
											</s:iterator>
										</s:elseif>
										<s:else>
											<s:property value="financetype"/>
										</s:else>
									</td> -->
									<td><s:property value="customername"/></td>
									<td><s:property value="res08"/></td>
									<td><s:property value="handlername"/></td>
									<td><s:property value="showReceiptdate"/></td>
									<td><s:property value="res02"/></td>
									<td align="right"><s:property value="amount"/></td>
									<td>
										<s:if test="financetype == 2">
											<s:if test="%{status == 1}">
												未对帐
											</s:if>
											<s:elseif test="%{status == 10}">
												已对帐, 未开票
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
											<s:elseif test="%{status == 99}">
												收到发票, 已付款
											</s:elseif>
										</s:else>
									</td>
									<td><s:property value="note"/></td>
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
