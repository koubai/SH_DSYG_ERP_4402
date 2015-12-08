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
<title>预出库确认</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	var b = true;
	//库存确认
	function createrpt() {
		var ids = getSelectedID();
		if(ids == "") {
			alert("请选择一条记录！");
			return;
		}
		$("#strOkIds").val(ids);
		if(b) {
			if(confirm("确认生成出库单吗？")) {
				b = false;
				document.mainform.action = '../warehouse/warehouseOutOkAction.action';
				document.mainform.submit();
			}
		}
	}
	
	function getSelectedID() {
		var ids = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				ids += list[i].value + ",";
			}
		}
		return ids;
	}
	
	//查询数据
	function queryList() {
		document.mainform.action = '../warehouse/queryWarehouseOutOkAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../warehouse/queryWarehouseOutOkAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../warehouse/turnWarehouseOutOkAction.action';
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
					预出库确认信息一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<s:hidden name="strOkIds" id="strOkIds"/>
				<div class="searchbox">
					<!--
					<div class="box1">
						<label class="pdf10">采购日期</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strPurchaseno" id="strPurchaseno" maxlength="32" cssStyle="width:150px;" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					-->
					<div class="box1">
						<label class="pdf10">客户名</label>
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
					<div class="box1" style="margin-top:-3px; margin-left: 240px; color: red;">
						<s:actionmessage />
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
								<td width="40"></td>
								<td width="40">序号</td>
								<td width="140">客户名称</td>
								<td width="60">仓库</td>
								<td width="140">订单号</td>
								<td width="120">品名</td>
								<td width="120">规格</td>
								<td width="60">颜色</td>
								<td width="60">包装</td>
								<td width="80">预出库数量</td>
								<td width="120">预入库时间</td>
								<!--
								<td width="60"></td>
								-->
							</tr>
							<s:iterator id="warehouseOutOkList" value="warehouseOutOkList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg" onclick="checkCheckboxTr(this, event);">
								</s:if>
								<s:else>
									<tr onclick="checkCheckboxTr(this, event);">
								</s:else>
									<td>
										<input name="radioKey" type="checkbox" value="<s:property value="ids"/>"/>
									</td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td><s:property value="suppliername"/></td>
									<td><s:property value="warehousename"/></td>
									<td><s:property value="theme2"/></td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == warehouseOutOkList[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{warehouseOutOkList[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{warehouseOutOkList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td><s:property value="showQuantity"/></td>
									<td><s:property value="plandate"/></td>
									<!--
									<td width="60">
										<input type="button" value="确认" onclick="warehouseOk('<s:property value="productid"/>', '<s:property value="supplierid"/>', '<s:property value="warehousename"/>')"/>
									</td>
									-->
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="pages">
						<ul>
							<li style="width: 300px;">
								<s:if test="intPageSize != null && intPageSize == 20">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" checked="checked" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
									<input name="tmpPagesize" type="radio" value="50" onclick="changepagesize('50')"/>50
									<input name="tmpPagesize" type="radio" value="100" onclick="changepagesize('100')"/>100
								</s:if>
								<s:elseif test="intPageSize != null && intPageSize == 30">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" checked="checked" onclick="changepagesize('30')"/>30
									<input name="tmpPagesize" type="radio" value="50" onclick="changepagesize('50')"/>50
									<input name="tmpPagesize" type="radio" value="100" onclick="changepagesize('100')"/>100
								</s:elseif>
								<s:elseif test="intPageSize != null && intPageSize == 50">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
									<input name="tmpPagesize" type="radio" value="50" checked="checked" onclick="changepagesize('50')"/>50
									<input name="tmpPagesize" type="radio" value="100" onclick="changepagesize('100')"/>100
								</s:elseif>
								<s:elseif test="intPageSize != null && intPageSize == 100">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
									<input name="tmpPagesize" type="radio" value="50" onclick="changepagesize('50')"/>50
									<input name="tmpPagesize" type="radio" value="100" checked="checked" onclick="changepagesize('100')"/>100
								</s:elseif>
								<s:else>
									显示：<input name="tmpPagesize" type="radio" value="10" checked="checked" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
									<input name="tmpPagesize" type="radio" value="50" onclick="changepagesize('50')"/>50
									<input name="tmpPagesize" type="radio" value="100" onclick="changepagesize('100')"/>100
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
										<input class="input80" type="button" value="出库单生成" onclick="createrpt();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="导出" onclick="" />
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
