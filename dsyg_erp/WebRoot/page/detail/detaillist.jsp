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
<title>详细查询信息一览</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 60);
	});
	
	function showCustomer() {
		document.getElementById("customer").style.display="block";
		document.getElementById("product").style.display="none";
	}
	
	function showProduct(){
		document.getElementById("customer").style.display="none";
		document.getElementById("product").style.display="block";
	}
	
	function queryProduct() {
		var url = '<%=request.getContextPath()%>/product/showDetailProductSelectPage.action';
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function queryCustomer() {
		var url = '<%=request.getContextPath()%>/customer/showDetailCustomerSelectPage.action';
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function queryCustomerList() {
		document.mainform.action = '<c:url value="/detail/queryDetailCustomerList.action"></c:url>';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		document.getElementById("startIndex").value = pageNum;
		document.mainform.action = '<c:url value="/detail/turnDetailCustomerPage.action"></c:url>';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../detail/queryDetailCustomerList.action';
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
	
	function queryProductList() {
		document.mainformproduct.action = '<c:url value="/detail/queryDetailProductList.action"></c:url>';
		document.mainformproduct.submit();
	}
	
	//产品
	//翻页
	function changePageProduct(pageNum) {
		document.getElementById("startIndexProduct").value = pageNum;
		document.mainformproduct.action = '<c:url value="/detail/turnDetailProductPage.action"></c:url>';
		document.mainformproduct.submit();
	}
	
	//修改pagesize
	function changepagesizeproduct(pagesize) {
		$("#intPageSizeProduct").attr("value", pagesize);
		$("#startIndexProduct").attr("value", "0");
		document.mainformproduct.action = '../detail/queryDetailProductList.action';
		document.mainformproduct.submit();
	}

	//页跳转
	function turnPageProduct() {
		var totalPage = "${pageProduct.totalPage}";
		var turnPage = document.getElementById("productpagenum").value;
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
			changePageProduct(turnPage - 1);
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
				<div id="cus" class="tittle_center">
					<a href="#" onclick="showCustomer();">产品->客户查询</a>
				</div>
				<div class="tittle_right">
				</div>
				<div class="tittle_left">
				</div>
				<div id="pro" class="tittle_center">
					<a href="#" onclick="showProduct();">客户->产品查询</a>
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<div id="customer" align="center">
			<form id="mainform" name="mainform" method="post">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="tab" id="tab"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<s:hidden name="strProductid" id="strProductid"></s:hidden>
				<div class="searchbox update">
					<div class="box1">
						<label class="pdf10" style="width:50px">品名</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="productname" id="productname" cssStyle="width:135px;" maxlength="4" readonly="true" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 60px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="产品" onclick="queryProduct();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</div>
				<div class="searchbox update">
					<div class="box1">
							<s:textfield name="productdetail" id="productdetail"  theme="simple" style="background:#F3F3F3;border:none;width:400px"></s:textfield>
					</div>
					<div class="btn" style="margin-left: 60px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryCustomerList();"/>
						</div>
						<div class="box1_right"></div>
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
								<td width="5%">序号</td>
								<td width="10%">客户姓名</td>
								<td width="25%">客户地址</td>
								<td width="10%">联系人</td>
								<td width="10%">电话</td>
								<td width="10%">传真</td>
								<td width="10%">邮箱</td>
								<td width="15%">备注</td>
							</tr>
							<s:iterator id="listSales" value="listSales" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td>
										<div noWrap style="text-overflow:ellipsis;overflow:hidden">
											<s:property value="customername"/>
										</div>
									</td>
									<td><s:property value="customeraddress"/></td>
									<td><s:property value="customermanager"/></td>
									<td><s:property value="customertel"/></td>
									<td><s:property value="customerfax"/></td>
									<td><s:property value="customermail"/></td>
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
			</form>
			</div>
			
			<div id="product" align="center" style="display:none">
			<form id="mainformproduct" name="mainformproduct" method="post">
				<s:hidden name="startIndexProduct" id="startIndexProduct"/>
				<s:hidden name="intPageSizeProduct" id="intPageSizeProduct"/>
				<s:hidden name="strCustomerid" id="strCustomerid"/>
				<div class="searchbox update">
					<div class="box1">
						<label class="pdf10" style="width:50px">客户</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="customername" id="customername" cssStyle="width:150px;" maxlength="4" readonly="true" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 60px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="客户" onclick="queryCustomer();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 60px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryProductList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="intPageSizeProduct * 35"/>px;">
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="5%">序号</td>
								<td width="15%">类型</td>
								<td width="15%">品名</td>
								<td width="15%">规格</td>
								<td width="10%">颜色</td>
								<td width="10%">形式</td>
								<td width="10%">包装</td>
								<td width="15%">产地</td>
							</tr>
							<s:iterator id="listProduct" value="listProduct" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="pageProduct.pageSize * (pageProduct.nextIndex - 1) + #st1.index + 1"/></td>
									<td>
										<s:iterator id="goodsList" value="goodsList" status="st3">
											<s:if test="%{goodsList[#st3.index].code == listProduct[#st1.index].fieldno}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == listProduct[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{listProduct[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{listProduct[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td><s:property value="item01"/></td>
									<td>
										<s:iterator id="makeareaList" value="makeareaList" status="st3">
											<s:if test="%{makeareaList[#st3.index].code == listProduct[#st1.index].makearea}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="pages">
						<ul>
							<li style="width: 180px;">
								<s:if test="intPageSizeProduct != null && intPageSizeProduct == 20">
									显示：<input name="tmpPagesizeProduct" type="radio" value="10" onclick="changepagesizeproduct('10')"/>10 
									<input name="tmpPagesizeProduct" type="radio" value="20" checked="checked" onclick="changepagesizeproduct('20')"/>20 
									<input name="tmpPagesizeProduct" type="radio" value="30" onclick="changepagesizeproduct('30')"/>30
								</s:if>
								<s:elseif test="intPageSizeProduct != null && intPageSizeProduct == 30">
									显示：<input name="tmpPagesizeProduct" type="radio" value="10" onclick="changepagesizeproduct('10')"/>10 
									<input name="tmpPagesizeProduct" type="radio" value="20" onclick="changepagesizeproduct('20')"/>20 
									<input name="tmpPagesizeProduct" type="radio" value="30" checked="checked" onclick="changepagesizeproduct('30')"/>30
								</s:elseif>
								<s:else>
									显示：<input name="tmpPagesizeProduct" type="radio" value="10" checked="checked" onclick="changepagesizeproduct('10')"/>10 
									<input name="tmpPagesizeProduct" type="radio" value="20" onclick="changepagesizeproduct('20')"/>20 
									<input name="tmpPagesizeProduct" type="radio" value="30" onclick="changepagesizeproduct('30')"/>30
								</s:else>
							</li>
							<li>第<strong>${pageProduct.startIndex + 1}</strong>页/共<strong>${pageProduct.totalPage==0?1:pageProduct.totalPage}</strong>页/共<strong>${pageProduct.totalCount}</strong>条记录</li>
							<li class="mgl15">跳转到
								<input type="text" id="productpagenum" class="text" maxlength="4" size="4"/>
								<input type="button" value="GO" onclick="javascript:turnPageProduct();"/>
							</li>
							<li class="mgl15">
								<a class="first" href="#" onclick="changePageProduct(0);">首页</a>
							</li>
							<li>
								<s:if test="%{pageProduct.startIndex <= 0}">
									<a class="last" href="#">上一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePageProduct('${pageProduct.previousIndex}');">上一页</a>
								</s:else>
							</li>
							<li>
								<s:if test="%{pageProduct.nextIndex > pageProduct.totalPage - 1}">
									<a class="last" href="#">下一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePageProduct('${pageProduct.nextIndex}');">下一页</a>
								</s:else>
							</li>
							<li>
								<a class="next" href="#" onclick="changePageProduct('${pageProduct.totalPage - 1}');">末页</a>
							</li>
						</ul>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
<script type="text/javascript" language="javascript">
var tab = document.getElementById("tab").value;
	if(tab == 0){
		document.getElementById("customer").style.display="block";
		document.getElementById("product").style.display="none";
	} else {
		document.getElementById("customer").style.display="none";
		document.getElementById("product").style.display="block";
	}
</script>
</body>
</html>
