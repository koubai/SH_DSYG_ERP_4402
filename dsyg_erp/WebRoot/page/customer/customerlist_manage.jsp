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
<title>客户信息一览</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 60);
	});

	function add() {
		var url = '<c:url value="/customer/showAddEtbCustomerAction.action"></c:url>' + "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:750px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function upd() {
		var sum = $("#checked").val().trim();
		if(parseInt(sum) == 0) {
			alert("请选择一条记录！");
			return;
		} else {
			document.mainform.action = '<c:url value="/customer/updEtbCustomerManageAction.action"></c:url>';
			document.mainform.submit();
		}
	}
	
	function check(obj) {
		var sum = $("#checked").val().trim();
		if(obj.checked){
			var add = parseInt(sum) + 1;
			$("#checked").attr("value", add);
		} else {
			var del = parseInt(sum) - 1;
			$("#checked").attr("value", del);
		}
	}
	
	function del() {
		var id = getSelectedID();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			if(confirm("确定删除该记录吗？")) {
				document.mainform.action = '<c:url value="/customer/delEtbCustomerAction.action"></c:url>' + "?delCustomerNo=" + id;
				document.mainform.submit();
			}
		}
	}
	
	function addUserList(id) {
		var theme1 = "";
		var url = '<%=request.getContextPath()%>/customer/showUserSelectPage.action';
		//strFlag=1采购单，strFlag=2销售单
		url += "?strFieldno=" + theme1 + "&customerindex=" + id + "&date=" + new Date();
		
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	};
	
	function getSelectedID() {
		var id = "";
		var list = document.getElementsByName("checkKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				break;
			}
		}
		return id;
	}
	
	function exportExcel() {
		document.mainform.action = '<c:url value="/customer/exportEtbCustomerAction.action"></c:url>';
		document.mainform.submit();
	}
	
	function queryList() {
		document.mainform.action = '<c:url value="/customer/queryEtbCustomerManageList.action"></c:url>';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		document.getElementById("startIndex").value = pageNum;
		document.mainform.action = '<c:url value="/customer/turnEtbCustomerManagePage.action"></c:url>';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../customer/queryEtbCustomerManageList.action';
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
					客户信息一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<s:hidden name="checked" id="checked" value="0"/>
				<div class="searchbox update">
					<div class="box1">
						<label class="pdf10" style="width:120px">客户编号检索 FROM</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strCustomerNoLow" id="strCustomerNoLow" cssStyle="width:135px;" maxlength="4" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
						<label>TO</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strCustomerNoHigh" id="strCustomerNoHigh" cssStyle="width:135px;" maxlength="4" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
				</div>
				<div class="searchbox update">
					<div class="box1" >
						<label class="pdf10" style="width:120px">客户名称</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strCustomerName" id="strCustomerName" cssStyle="width:135px;" maxlength="4" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 400px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-3px; margin-left: -400px; color: red;">
						<s:actionmessage />
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
								<td width="5%"></td>
								<td width="5%">序号</td>
								<!-- <td width="5%">客户编号</td> -->
								<td width="20%">客户名称</td>
								<td width="25%">客户地址</td>
								<td width="10%">联系人</td>
								<td width="10%">客户类型</td>
								<td width="20%">客户担当</td>
							</tr>
							<s:iterator id="listCustomer" value="listCustomer" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg" onclick="checkRadioCuTr(this, event, 1, 0);">
								</s:if>
								<s:else>
									<tr onclick="checkRadioCuTr(this, event, 1, 0);">
								</s:else>
									<td>
									<input type="checkbox" name="listCustomer[<s:property value="#st1.index"/>].checkKey" onclick="check(this);" /></td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<!-- <td><s:property value="id"/></td> -->
									<td>
										<div noWrap style="text-overflow:ellipsis;overflow:hidden">
											<s:property value="customername"/>
										</div>
									</td>
									<td><s:property value="customeraddress1"/></td>
									<td><s:property value="customermanager1"/></td>
									<td><select type="select" name="listCustomer[<s:property value="#st1.index"/>].customertype">
 									   <option value="0"<c:if test="${customertype==0}">selected</c:if>>
    									 公司开拓
    									</option>
   									   <option value="1" <c:if test="${customertype==1}">selected</c:if>>
    									 个人开拓
    									</option>
									   </select></td>
									<td><input type="text" name="listCustomer[<s:property value="#st1.index"/>].handlerid" id="listCustomer[<s:property value="#st1.index"/>].handlerid" value="<s:property value="handlerid"/>" />
									    <input class="input80" type="button" onclick="addUserList('listCustomer[<s:property value="#st1.index"/>].handlerid');" value="用户检索" />
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
				<br></br>
				<br></br>
				<div class="btn" style="margin-left:300px;">
					<div class="box1_left"></div>
					<div class="box1_center">
						<input class="input80" type="button" value="更新" onclick="upd();"/>
					</div>
					<div class="box1_right"></div>
				</div>				
			</s:form>
		</div>
	</div>
</body>
</html>
