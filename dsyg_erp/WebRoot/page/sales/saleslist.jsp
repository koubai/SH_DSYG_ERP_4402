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
<title>销售信息一览</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	function showDetail() {
		var id = getSelectedID();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			var url = "<%=request.getContextPath()%>/sales/showUpdSalesitemAction.action?updSalesId=" + id + "&date=" + new Date();
			window.showModalDialog(url, window, "dialogheight:680px;dialogwidth:1200px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no;scrollbars=yes;");
			//document.mainform.action = "../sales/showUpdSalesitemAction.action?updSalesId=" + id;
			//document.mainform.submit();
		}
	}
	
	function add() {
		document.mainform.action = "../sales/showAddSalesAction.action";
		document.mainform.submit();
	}
	
	function upd() {
		var id = getSelectedID();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			document.mainform.action = "../sales/showUpdSalesAction.action?updSalesId=" + id;
			document.mainform.submit();
		}
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
	
	function del() {
		var id = getSelectedID();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			if(confirm("确定删除吗？")) {
				document.mainform.action = "../sales/delSalesAction.action?delSalesId=" + id;
				document.mainform.submit();
			}
		}
	}
	
	//查询日期赋值
	function setQueryDate() {
		var type = "";
		var list = document.getElementsByName("tmpType");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				type = list[i].value;
				break;
			}
		}
		$("#strType").attr("value", type);
		$("#strSalesdateLow").attr("value", $("#salesDateLow").val());
		$("#strSalesdateHigh").attr("value", $("#salesDateHigh").val());
	}

	// Search Product
	function srhProduct() {
		//主题
		var theme1 = "";//$("#theme1").val().trim();
		
		//这里需要查询库存数据
		//url += "?strFieldno=" + theme1 + "&strCustomerId=" + customerid + "&date=" + new Date();
		var url = '<%=request.getContextPath()%>/product/showProductidSelectPage.action';
		url += "?strFieldno=" + theme1 + "&date=" + new Date();
		
		//window.open(url);
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	// clear Product
	function clrProduct() {
		$("#productinfo").attr("value", "");
		$("#productid").attr("value", "");
	}
	
	//查询数据
	function queryList() {
		setQueryDate();
		document.mainform.action = '../sales/querySalesAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../sales/querySalesAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		setQueryDate();
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../sales/turnSalesAction.action';
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
					销售信息一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="strSalesdateLow" id="strSalesdateLow"/>
				<s:hidden name="strSalesdateHigh" id="strSalesdateHigh"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<s:hidden name="strType" id="strType"/>
				<s:hidden name="productid" id="productid"/>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">销售订单号</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<s:textfield name="strTheme2" cssStyle="width:200px;" maxlength="32" id="strTheme2" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-left: 15px;">
						<label class="pdf10">销售日期</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="salesDateLow" value="<s:property value="strSalesdateLow"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('salesDateLow'));"></a>
						</div>
						<div class="box1_right"></div>
						<label>-</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<input type="text" disabled="disabled" style="width: 105px;" id="salesDateHigh" value="<s:property value="strSalesdateHigh"/>" maxlength="10" />
							<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('salesDateHigh'));"></a>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1">
						<label class="pdf10">状态</label>
						<div class="box1_left"></div>
						<div class="box1_center date_input">
							<select id="strStatus" name="strStatus" style="width: 120px;">
								<s:if test='strStatus == "10"'>
									<option value="">请选择</option>
									<option value="10" selected="selected">未发货</option>
									<option value="15">部分发货</option>
									<option value="20">发货完成</option>
								</s:if>
								<s:elseif test='strStatus == "15"'>
									<option value="">请选择</option>
									<option value="10">未发货</option>
									<option value="15" selected="selected">部分发货</option>
									<option value="20">发货完成</option>
								</s:elseif>
								<s:elseif test='strStatus == "20"'>
									<option value="">请选择</option>
									<option value="10">未发货</option>
									<option value="15">部分发货</option>
									<option value="20" selected="selected">发货完成</option>
								</s:elseif>
								<s:else>
									<option value="" selected="selected">请选择</option>
									<option value="10">未发货</option>
									<option value="15">部分发货</option>
									<option value="20">发货完成</option>
								</s:else>
							</select>
						</div>
						<div class="box1_right"></div>
					</div>
				</div>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">　客户名称</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strCustomername" cssStyle="width:200px;" maxlength="32" id="strCustomername" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top: 6px; margin-left: 50px;">
						<s:if test='strType == "1"'>
							<input name="tmpType" type="radio" value="0"/>订单 <input name="tmpType" checked="checked" type="radio" value="1"/>询价
						</s:if>
						<s:else>
							<input name="tmpType" type="radio" checked="checked" value="0"/>订单 <input name="tmpType" type="radio" value="1"/>询价
						</s:else>
					</div>
					<div class="box1" style="margin-top: 6px; margin-left: 50px;">
						<div class="box1_left">
							<s:textfield name="productinfo" id="productinfo" size="30" maxlength="150" theme="simple" disabled="disabled"></s:textfield>
						</div>
						<div class="btn" style="margin-left: 230px;">
							<div class="box1_left"></div>							
							<div class="box1_center">
								<input class="input40" type="button" onclick="clrProduct();" value="清除" />							
							</div>
							<div class="box1_right"></div>
						</div>
						<div class="btn" style="margin-left: 10px;">
							<div class="box1_left"></div>							
							<div class="box1_center">
								<input class="input40" type="button" onclick="srhProduct();" value="产品" />							
							</div>
							<div class="box1_right"></div>
						</div>
					</div>
					<div class="btn" style="margin-top: 6px; margin-left: 50px;">
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
								<td width="20"></td>
								<td width="30">序号</td>
								<s:if test='strType == ""'>
									<td width="125">销售订单号</td>
									<td width="60">销售方式</td>
									<td width="60">支付方式</td>
									<td width="40">仓库</td>
									<td width="120">客户</td>
									<td width="60">经手人</td>
									<td width="75">销售日期</td>
									<td width="110">销售金额（不含税）</td>
									<td width="100">销售金额（含税）</td>
									<td width="100">已付金额（含税）</td>
								</s:if>
								<s:elseif test='strType == "1"'>
									<td width="125">询价单号</td>
									<td width="60">询价方式</td>
									<td width="60">支付方式</td>
									<td width="40">仓库</td>
									<td width="120">客户</td>
									<td width="60">经手人</td>
									<td width="75">询价日期</td>
									<td width="110">询价金额（不含税）</td>
									<td width="100">询价金额（含税）</td>
									<td width="100">已付金额（含税）</td>
								</s:elseif>
								<s:else >
									<td width="125">销售订单号</td>
									<td width="60">销售方式</td>
									<td width="60">支付方式</td>
									<td width="40">仓库</td>
									<td width="120">客户</td>
									<td width="60">经手人</td>
									<td width="75">销售日期</td>
									<td width="110">销售金额（不含税）</td>
									<td width="100">销售金额（含税）</td>
									<td width="100">已付金额（含税）</td>
								</s:else>
								<td width="60">数量</td>
								<td width="60">状态</td>
								<td width="80">备注</td>
							</tr>
							<s:iterator id="salesList" value="salesList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg" onclick="checkRadioTr(this, event);">
								</s:if>
								<s:else>
									<tr onclick="checkRadioTr(this, event);">
								</s:else>
									<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									
									<s:if test='%{salesList[#st1.index].rptno != null && salesList[#st1.index].rptno != ""}'>
										<td title="<s:property value="rptno"/>">
											<s:property value="theme2"/><font color="red">*</font>
										</td>
									</s:if>
									<s:else>
										<td>
											<s:property value="theme2"/>
										</td>
									</s:else>
									<!--
									<td>
										<s:iterator id="goodsList" value="goodsList" status="st3">
											<s:if test="%{goodsList[#st3.index].code == salesList[#st1.index].theme1}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									-->
									<td>
										<s:if test='%{res02 == "0"}'>
											订单
										</s:if>
										<s:elseif test='%{res02 == "1"}'>
											询价
										</s:elseif>
										<s:elseif test='%{res02 == "2"}'>
											询样
										</s:elseif>
										<s:else>
											<s:property value="res02"/>
										</s:else>
									</td>
									<td>
										<s:iterator id="payTypeList" value="payTypeList" status="st3">
											<s:if test="%{payTypeList[#st3.index].code == salesList[#st1.index].res01}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td><s:property value="warehouse"/></td>
									<td><s:property value="customername"/></td>
									<td><s:property value="handlername"/></td>
									<td><s:property value="showBookdate"/></td>
									<td align="right"><s:property value="amount"/></td>
									<td align="right"><s:property value="taxamount"/></td>
									<td align="right"><s:property value="paidamount"/></td>
									<td align="right"><s:property value="quantity"/></td>
									<td>
										<s:if test="%{status == 10}">
											未发货
										</s:if>
										<s:elseif test="%{status == 15}">
											部分发货
										</s:elseif>
										<s:elseif test="%{status == 20}">
											发货完成
										</s:elseif>
										<s:else>
											<s:property value="status"/>
										</s:else>
									</td>
									<td><s:property value="note"/></td>
								</tr>
							</s:iterator>
						</table>
						<s:if test='%{salesList.size > 0 && productid != ""}'>
							<div style="text-align:right; margin-top:10px; margin-right:180px">订单数量合计：<s:property value="salesList[0].allquantity"/></div>
						</s:if>
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
				<div class="btns" style="margin-top:40px; margin-left:-90px;">
					<table border="0" style="margin:0 auto;">
						<tr>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="详细" onclick="showDetail();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
