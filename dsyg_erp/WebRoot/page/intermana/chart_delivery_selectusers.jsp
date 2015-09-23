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
<title>用户信息一览</title>
<script type="text/javascript">
	$(function() {
	});
	
	function queryList() {
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../chart/turnDeliverySelectPage.action';
		document.mainform.submit();
		strUserIdFrom="";
		strUserIdTo="";
	}
	
	//给父页面添加记录
	function addUserList() {
		var id = "";
		var userIdList = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				//添加父页面记录
				if (userIdList == "")
					userIdList = userIdList + id;
				else
					userIdList = userIdList + "," + id;
			}
		}
		getOpener().document.getElementById("handerList").value=userIdList; 
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
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../chart/queryDeliverySelectPage.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../chart/turnDeliverySelectPage.action';
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
<body style="background: url(''); overflow-x:hidden;overflow-y:hidden;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="common_rate" id="common_rate"></s:hidden>
	<s:hidden name="startIndex" id="startIndex"/>
	<s:hidden name="intPageSize" id="intPageSize"/>
<!--	
	<s:hidden name="strUserIdFrom" id="strUserIdFrom"/>
	<s:hidden name="strUserIdTo" id="strUserIdTo"/>
-->
	<s:hidden name="strKeyword" id="strKeyword"/>
	<s:hidden name="strFlag" id="strFlag"/>
	<s:hidden name="strSeq" id="strSeq"/>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="searchbox">
			<div class="box1">
				<label class="pdf10">用户FROM：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strUserIdFrom" id="strUserIdFrom" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="box1">
				<label class="pdf10">用户TO：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strUserIdTo" id="strUserIdTo" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
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
						<td width="20"></td>
						<td width="20">序号</td>
						<td width="60">用户ID</td>
						<td width="60">用户名</td>
					</tr>
					<s:iterator id="deliveryList" value="deliveryList" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg" onclick="checkCheckboxTr1(this, event)">
						</s:if>
						<s:else>
							<tr onclick="checkCheckboxTr1(this, event)">
						</s:else>
							<td style="display: none;">
								<input type="hidden" value="<s:property value="tmp_id"/>"/>
								<input type="hidden" value="<s:property value="id"/>"/>
								<input type="hidden" value="<s:property value="deliveryname"/>"/>
							</td>
							<!-- <td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td> -->
							<td><input name="radioKey" type="checkbox" value="<s:property value="id"/>"/></td>
							<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
							<td><s:property value="id"/></td>
							<td><s:property value="deliveryname"/></td>
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
		<div class="btns" style="margin-top:40px; margin-left: 0px;">
			<table border="0" style="margin:0 auto;">
				<tr>
					<td>
						<div class="btn">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input type="button" class="input80" value="追加" onclick="addUserList();"/>
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
