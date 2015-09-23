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
<title>客户信息一览</title>
<script type="text/javascript">
	$(function() {
	});
	
	//给父页面添加记录
	function addCustomer() {
		var obj = null;
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				obj = list[i];
				break;
			}
		}
		if(obj == null) {
			alert("请选择一条记录！");
			return;
		}
		//将客户数据添加到父页面中
		var tr = obj.parentNode.parentNode;
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		var id = inputs[0].value;
		var customername = inputs[1].value;
		var customeraddress = inputs[2].value;
		var customermanager = inputs[3].value;
		var customertel = inputs[4].value;
		var customerfax = inputs[5].value;
		var customermail = inputs[6].value;
		
		getOpener().document.getElementById("customerid").value = id;
		getOpener().document.getElementById("customername").value = customername;
		getOpener().document.getElementById("customermanager").value = customermanager;
		getOpener().document.getElementById("customeraddress").value = customeraddress;
		getOpener().document.getElementById("customertel").value = customertel;
		getOpener().document.getElementById("customerfax").value = customerfax;
		getOpener().document.getElementById("customermail").value = customermail;
		
		window.close();
	}
	
	function getSelectedID() {
		var obj = null;
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				obj = list[i];
				break;
			}
		}
		return obj;
	}
	
	function queryList() {
		$("#selectStartIndex").attr("value", "0");
		document.mainform.action = '../customer/turnSelectCustomerAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intSelectPageSize").attr("value", pagesize);
		$("#selectStartIndex").attr("value", "0");
		document.mainform.action = '../customer/querySelectCustomerAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#selectStartIndex").attr("value", pageNum);
		document.mainform.action = '../customer/turnSelectCustomerAction.action';
		document.mainform.submit();
	}

	//页跳转
	function turnPage() {
		var totalPage = "${selectPage.totalPage}";
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
<body style="background: url(''); overflow-x:hidden;overflow-y:hidden;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="selectStartIndex" id="selectStartIndex"/>
	<s:hidden name="intSelectPageSize" id="intSelectPageSize"/>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="searchbox">
			<div class="box1">
				<label class="pdf10">客户名：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strSelectCustomerName" id="strSelectCustomerName" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
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
						<td style="display: none;"></td>
						<td width="5%"></td>
						<td width="10%">序号</td>
						<td width="15%">客户名称</td>
						<td width="15%">客户地址</td>
						<td width="10%">联系人</td>
						<td width="10%">电话</td>
						<td width="10%">传真</td>
						<td width="10%">邮箱</td>
						<td width="15%">备注</td>
					</tr>
					<s:iterator id="listSelectCustomer" value="listSelectCustomer" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg" onclick="checkRadioCuTr(this, event, 1, 0);">
						</s:if>
						<s:else>
							<tr onclick="checkRadioCuTr(this, event, 1, 0);">
						</s:else>
							<td style="display: none;">
								<input type="hidden" value="<s:property value="id"/>"/>
								<input type="hidden" value="<s:property value="customername"/>"/>
								<input type="hidden" value="<s:property value="customeraddress1"/>"/>
								<input type="hidden" value="<s:property value="customermanager1"/>"/>
								<input type="hidden" value="<s:property value="customertel1"/>"/>
								<input type="hidden" value="<s:property value="customerfax1"/>"/>
								<input type="hidden" value="<s:property value="customermail1"/>"/>
							</td>
							<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>
							<td><s:property value="selectPage.pageSize * (selectPage.nextIndex - 1) + #st1.index + 1"/></td>
							<td>
								<div noWrap style="text-overflow:ellipsis;overflow:hidden">
									<s:property value="customername"/>
								</div>
							</td>
							<td><s:property value="customeraddress1"/></td>
							<td><s:property value="customermanager1"/></td>
							<td><s:property value="customertel1"/></td>
							<td><s:property value="customerfax1"/></td>
							<td><s:property value="customermail1"/></td>
							<td>
								<div noWrap style="width:150px;text-overflow:ellipsis;overflow:hidden">
									<s:property value="note"/>
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="pages">
				<ul>
					<li style="width: 180px;">
						<s:if test="intSelectPageSize != null && intSelectPageSize == 20">
							显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
							<input name="tmpPagesize" type="radio" value="20" checked="checked" onclick="changepagesize('20')"/>20 
							<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
						</s:if>
						<s:elseif test="intSelectPageSize != null && intSelectPageSize == 30">
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
					<li>第<strong>${selectPage.startIndex + 1}</strong>页/共<strong>${selectPage.totalPage==0?1:selectPage.totalPage}</strong>页/共<strong>${selectPage.totalCount}</strong>条记录</li>
					<li class="mgl15">跳转到
						<input type="text" id="pagenum" class="text" maxlength="4" size="4"/>
						<input type="button" value="GO" onclick="javascript:turnPage();"/>
					</li>
					<li class="mgl15">
						<a class="first" href="#" onclick="changePage(0);">首页</a>
					</li>
					<li>
						<s:if test="%{selectPage.startIndex <= 0}">
							<a class="last" href="#">上一页</a>
						</s:if>
						<s:else>
							<a class="next" href="#" onclick="changePage('${selectPage.previousIndex}');">上一页</a>
						</s:else>
					</li>
					<li>
						<s:if test="%{selectPage.nextIndex > selectPage.totalPage - 1}">
							<a class="last" href="#">下一页</a>
						</s:if>
						<s:else>
							<a class="next" href="#" onclick="changePage('${selectPage.nextIndex}');">下一页</a>
						</s:else>
					</li>
					<li>
						<a class="next" href="#" onclick="changePage('${selectPage.totalPage - 1}');">末页</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="btns" style="margin-top:40px; margin-left: 0px;">
			<table border="0" style="margin:0 auto;">
				<tr>
					<td>
						<div class="btn">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input type="button" class="input80" value="确定" onclick="addCustomer();"/>
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
