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
<title>预入库确认</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	//预入库确认
	function warehouseOk() {
		
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
	
	//查询数据
	function queryList() {
		document.mainform.action = '../warehouserpt/showWarehouseOkAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../warehouserpt/queryWarehouseOkAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../warehouserpt/turnWarehouseOkAction.action';
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
					预入库确认信息一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">采购日期</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strPurchaseno" id="strPurchaseno" maxlength="32" cssStyle="width:150px;" theme="simple"></s:textfield>
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
						<a class="add" onclick="">确认</a>
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
								<td width="80">采购单号</td>
								<td width="60">主题</td>
								<td width="60">品名</td>
								<td width="60">规格</td>
								<td width="60">颜色</td>
								<td width="60">包装</td>
								<td width="120">供应商</td>
								<td width="60">预入库数量</td>
								<td width="60"></td>
							</tr>
							<s:iterator id="purchaseItemList" value="purchaseItemList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td><s:property value="purchaseno"/></td>
									<td>
										<s:iterator id="goodsList" value="goodsList" status="st3">
											<s:if test="%{goodsList[#st3.index].code == purchaseItemList[#st1.index].theme1}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == purchaseItemList[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{purchaseItemList[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{purchaseItemList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td><s:property value="supplierid"/></td>
									<td align="right"><s:property value="inquantity"/></td>
									<td width="60">
										<input type="button" onclick="warehouseOk()"/>
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
				<div class="btns" style="margin-top:40px; margin-left:-90px;">
					<table border="0" style="margin:0 auto;">
						<tr>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="详细" onclick="showBidDetail();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="履历" onclick="" />
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
