<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>库存修正信息编辑</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = "../warehouse/updWarehouseRefundAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//库存编号
		var warehouseno = $("#warehouseno").val().trim();
		//类型
		var warehousetype = $("#warehousetype").val().trim();
		//主题
		var theme1 = $("#theme1").val().trim();
		//产品ID
		var productid = $("#productid").val().trim();
		//数量
		var quantity = $("#quantity").val().trim();
		//备注
		var tempNote = $("#tempNote").val().trim();
		
		if(warehousetype == "") {
			alert("请选择类型！");
			$("#warehousetype").focus();
			return;
		}
		//if(theme1 == "") {
		//	alert("请选择主题！");
		//	$("#theme1").focus();
		//	return;
		//}
		if(productid == "") {
			alert("请选择一个产品！");
			$("#productname").focus();
			return;
		}
		if(quantity == "") {
			alert("数量不能为空！");
			$("#quantity").focus();
			return;
		}
		if(quantity == "") {
			alert("数量不能为空！");
			$("#quantity").focus();
			return;
		}
		if(!isAllReal(quantity)) {
			alert("数量格式不正确！");
			obj.focus();
			return;
		}
		if(tempNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tempNote").focus();
			return false;
		}
		//备注
		$("#note").val($("#tempNote").val());
		return true;
	}
	
	function changeTheme() {
		$("#productname").val("");
		$("#productid").val("");
	}
	
	function selectProduct() {
		var strFieldno = $("#theme1").val();
		var url = "../product/showProductSingleSelectPage.action?strFieldno=" + strFieldno;
		url += "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goList() {
		window.location.href = "../warehouse/queryWarehouseRefundAction.action";
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<div class="tittle">
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					退换货信息编辑
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="updWarehouseDto.productid" id="productid"></s:hidden>
				<s:hidden name="updWarehouseDto.note" id="note"></s:hidden>
				<s:hidden name="updWarehouseDto.productname" id="productname"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>库存编号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouseDto.warehouseno" id="warehouseno" disabled="true" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>类型</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updWarehouseDto.warehousetype" id="warehousetype" style="width: 300px;">
										<option value="6">库存修正</option>
										<!--
										<s:if test="%{updWarehouseDto.warehousetype == 3}">
											<option value="">请选择</option>
											<option value="3" selected="selected">退货</option>
											<option value="5">损毁</option>
											<option value="6">库存修正</option>
										</s:if>
										<s:elseif test="%{updWarehouseDto.warehousetype == 5}">
											<option value="">请选择</option>
											<option value="3">退货</option>
											<option value="5" selected="selected">损毁</option>
											<option value="6">库存修正</option>
										</s:elseif>
										<s:elseif test="%{updWarehouseDto.warehousetype == 6}">
											<option value="">请选择</option>
											<option value="3">退货</option>
											<option value="5">损毁</option>
											<option value="6" selected="selected">库存修正</option>
										</s:elseif>
										<s:else>
											<option value="" selected="selected">请选择</option>
											<option value="3">退货</option>
											<option value="5">损毁</option>
											<option value="6">库存修正</option>
										</s:else>
										-->
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr style="display: none;">
							<td align="right">
								<label class="pdf10"><font color="red">*</font>主题</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updWarehouseDto.theme1" id="theme1" style="width: 300px;" onchange="changeTheme();">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == updWarehouseDto.theme1}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>产品</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" name="tmpProductname" id="tmpProductname" maxlength="64" disabled="disabled" style="width:300px;" value="<s:property value="updWarehouseDto.productname"/>"/>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectProduct();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>数量</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouseDto.quantity" id="quantity" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<!--
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>金额（含税）</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouseDto.taxamount" id="taxamount" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>确认者</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouseDto.approverid" id="approverid" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						-->
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="updWarehouseDto.note"/></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="trade">
					<table cellpadding="10" style="margin:0 auto;">
						<tr>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="修改" onclick="upd();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="返回" onclick="goList();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
					</table>
					<div style="height:225px;"></div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
