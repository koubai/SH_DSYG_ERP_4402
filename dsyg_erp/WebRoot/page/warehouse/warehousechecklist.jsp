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
<title>库存盘点</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	//查询数据
	function queryList() {
		document.mainform.action = '../warehouse/queryWarehouseCheckAction.action';
		document.mainform.submit();
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#checkIntPageSize").attr("value", pagesize);
		$("#checkStartIndex").attr("value", "0");
		document.mainform.action = '../warehouse/queryWarehouseCheckAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#checkStartIndex").attr("value", pageNum);
		document.mainform.action = '../warehouse/turnWarehouseCheckAction.action';
		document.mainform.submit();
	}

	//页跳转
	function turnPage() {
		var totalPage = "${checkPage.totalPage}";
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
	
	function productCheck(id) {
		//盘点
		var num = $("#" + "num_" + id).val();
		//判断数字是否合法
		if(!isReal(num)) {
			alert("盘点数量必须是大于0的数！");
			$("#" + "num_" + id).focus();
			return;
		}
		var position = $("#" + "position_" + id).val().trim();
		if(position == "") {
			alert("盘点不能为空！");
			$("#" + "position_" + id).focus();
			return;
		}
		$("#strPosition").val(position);
		document.mainform.action = '../warehouse/checkProductQuantity.action?strCheckProductid=' + id + "&strCheckProductNum=" + num;
		document.mainform.submit();
	}
	
	function exportData() {
		document.mainform.action = '../warehouse/exportWarehouserCheckAction.action';
		document.mainform.submit();
	}
	
	function goCollect() {
		document.mainform.action = '../warehouse/queryPositionCollectAction.action';
		document.mainform.submit();
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
					库存盘点
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="checkStartIndex" id="checkStartIndex"/>
				<s:hidden name="checkIntPageSize" id="checkIntPageSize"/>
				<s:hidden name="strPosition" id="strPosition"/>
				<div class="searchbox">
					<div class="box1" style="display: none;">
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
					<div class="box1" style="margin-top:-4px; margin-left: 210px; color: red; font-size: 14px;">
						<s:actionmessage />
					</div>
					<div class="icons thums">
						<div class="btn" style="margin-right: 30px; margin-top: -7px;">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input type="button" class="input40" value="检索" onclick="queryList();"/>
							</div>
							<div class="box1_right"></div>
						</div>
						<div class="btn" style="margin-right: 40px; margin-top: -7px;">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input class="input40" type="button" value="返回" onclick="goCollect();"/>
							</div>
							<div class="box1_right"></div>
						</div>
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="checkIntPageSize * 35"/>px;">
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="40">序号</td>
								<td width="120">品名</td>
								<td width="120">规格</td>
								<td width="60">颜色</td>
								<td width="60">形式</td>
								<td width="60">包装</td>
								<td width="60">单位</td>								
								<td width="60">产地</td>
								<td width="80">库存数量</td>
								<td width="120">库存位置</td>
								<td width="140">盘点数量</td>
							</tr>
							<s:iterator id="warehouseCheckList" value="warehouseCheckList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="checkPage.pageSize * (checkPage.nextIndex - 1) + #st1.index + 1"/></td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == warehouseCheckList[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{warehouseCheckList[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{warehouseCheckList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:elseif test='%{warehouseCheckList[#st1.index].packaging == "2"}'>样品</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td>
										<s:property value="item10"/>
									</td>
									<td>
										<s:iterator id="unitList" value="unitList" status="st3">
											<s:if test="%{unitList[#st3.index].code == warehouseCheckList[#st1.index].unit}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:iterator id="makeareaList" value="makeareaList" status="st3">
											<s:if test="%{makeareaList[#st3.index].code == warehouseCheckList[#st1.index].makearea}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td align="right"><s:property value="warehouseamount"/></td>
									<td>
										<input type="text" style="width: 100px;" maxlength="32" id="position_<s:property value="productid"/>" value="<s:property value="warehouseposition"/>"/>
									</td>
									<td>
										<input type="text" style="width: 100px;" maxlength="10" id="num_<s:property value="productid"/>" value="<s:property value="checkAmount"/>"/><input type="button" value="盘点" onclick="productCheck('<s:property value="productid"/>')"/>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
					<div class="pages">
						<ul>
							<li style="width: 180px;">
								<s:if test="checkIntPageSize != null && checkIntPageSize == 20">
									显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
									<input name="tmpPagesize" type="radio" value="20" checked="checked" onclick="changepagesize('20')"/>20 
									<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
								</s:if>
								<s:elseif test="checkIntPageSize != null && checkIntPageSize == 30">
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
							<li>第<strong>${checkPage.startIndex + 1}</strong>页/共<strong>${checkPage.totalPage==0?1:checkPage.totalPage}</strong>页/共<strong>${checkPage.totalCount}</strong>条记录</li>
							<li class="mgl15">跳转到
								<input type="text" id="pagenum" class="text" maxlength="4" size="4"/>
								<input type="button" value="GO" onclick="javascript:turnPage();"/>
							</li>
							<li class="mgl15">
								<a class="first" href="#" onclick="changePage(0);">首页</a>
							</li>
							<li>
								<s:if test="%{checkPage.startIndex <= 0}">
									<a class="last" href="#">上一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${checkPage.previousIndex}');">上一页</a>
								</s:else>
							</li>
							<li>
								<s:if test="%{checkPage.nextIndex > checkPage.totalPage - 1}">
									<a class="last" href="#">下一页</a>
								</s:if>
								<s:else>
									<a class="next" href="#" onclick="changePage('${checkPage.nextIndex}');">下一页</a>
								</s:else>
							</li>
							<li>
								<a class="next" href="#" onclick="changePage('${checkPage.totalPage - 1}');">末页</a>
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
										<input class="input80" type="button" value="导出" onclick="exportData();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--
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
				-->
			</s:form>
		</div>
	</div>
</body>
</html>
