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
<title>产品信息一览</title>
<script type="text/javascript">
	$(function() {
	});
	
	function queryList() {
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../warehouse/queryWarehouseDetailPopupAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../warehouse/turnWarehouseDetailPopupAction.action';
		document.mainform.submit();
	}
	
	function showPurchase(id){
		var url = '<%=request.getContextPath()%>/warehouse/showProductPurchasePage.action';
		//strFlag=1采购单，strFlag=2销售单
		url += "?strProdoctid=" + id + "&strFlag=1" + "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:400px;dialogwidth:600px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function showSales(id){
		var url = '<%=request.getContextPath()%>/warehouse/showProductSalesPage.action';
		//strFlag=1采购单，strFlag=2销售单
		url += "?strProdoctid=" + id + "&strFlag=2" + "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:400px;dialogwidth:600px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
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
	<s:hidden name="startIndex" id="startIndex"/>
	<s:hidden name="intPageSize" id="intPageSize"/>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="searchbox">
			<div class="box1">
				<label class="pdf10">类型</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<select name="strTheme" id="strTheme" style="width: 150px;">
						<option value="" selected="selected">请选择</option>
						<s:iterator value="goodsList" id="goodsList" status="st1">
							<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == strTheme}">selected</s:if>><s:property value="fieldname"/></option>
						</s:iterator>
					</select>
				</div>
			</div>
			<div class="box1">
				<label class="pdf10">关键字</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strKeyword" id="strKeyword" cssClass="input80" maxlength="16" theme="simple"></s:textfield>
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
						<td width="40">序号</td>
						<td width="60">主题</td>
						<td width="120">品名</td>
						<td width="120">规格</td>
						<td width="60">颜色</td>
						<td width="60">包装</td>
						<td width="60">形式</td>
						<td width="80">订单数量</td>
						<td width="80">库存数量</td>
						<td width="80">差数</td>
						<td width="80">采购数量</td>
						<td width="80">预计交货期</td>
					</tr>
					<s:iterator id="warehouseDetailList" value="warehouseDetailList" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg">
						</s:if>
						<s:else>
							<tr>
						</s:else>
							<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
							<td>
								<s:iterator id="goodsList" value="goodsList" status="st3">
									<s:if test="%{goodsList[#st3.index].code == warehouseDetailList[#st1.index].fieldno}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
							<td><s:property value="tradename"/></td>
							<td><s:property value="typeno"/></td>
							<td>
								<s:iterator id="colorList" value="colorList" status="st3">
									<s:if test="%{colorList[#st3.index].code == warehouseDetailList[#st1.index].color}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
							<td><s:property value="item01"/></td>
							<td>
								<s:if test='%{warehouseDetailList[#st1.index].packaging == "0"}'>整箱</s:if>
								<s:elseif test='%{warehouseDetailList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
								<s:else>
									<s:property value="packaging"/>
								</s:else>
							</td>
							<td align="right"><a href="#" onclick="showSales('<s:property value="id"/>');"><u><s:property value="quantitys"/></u></a></td>
							<td align="right"><s:property value="quantity"/></td>
							<td align="right">
								<s:if test="%{warehouseDetailList[#st1.index].diffquantity <= 0}">
									<span style="color: red"><s:property value="diffquantity"/></span>
								</s:if>
								<s:else>
									<s:property value="diffquantity"/>
								</s:else>
							</td>
							<td align="right"><a href="#" onclick="showPurchase('<s:property value="id"/>');"><u><s:property value="quantityp"/></u></a></td>
							<td><s:property value="plandate"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="pages">
				<ul>
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
