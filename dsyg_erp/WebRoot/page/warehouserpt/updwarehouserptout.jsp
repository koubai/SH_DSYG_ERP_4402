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
<title>发货单编辑</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定提交吗？")) {
				document.mainform.action = "../warehouserpt/updWarehouserptOutAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//快递ID
		var expressid = $("#expressid").val().trim();
		//快单号
		var expressno = $("#expressno").val().trim();
		//快递名
		var expressname = $("#expressname").val().trim();
		//快递地址
		var expressaddress = $("#expressaddress").val().trim();
		//转运费用合计
		var expresstaxamount = $("#expresstaxamount").val().trim();
		//快递联系人
		var expressmanager = $("#expressmanager").val().trim();
		//快递联系人电话
		var expresstel = $("#expresstel").val().trim();
		//快递联系人传真
		var expressfax = $("#expressfax").val().trim();
		//发货日期
		var tmpWarehousedate = $("#tmpWarehousedate").val().trim();
		//信箱
		var expressmail = $("#expressmail").val().trim();
		//单据日期
		var tmpReceiptdate = $("#tmpReceiptdate").val().trim();
		//备注
		var tempNote = $("#tempNote").val().trim();
		if(expressid == "") {
			alert("请选择快递！");
			$("#expressid").focus();
			return;
		}
		if(expressno == "") {
			alert("快递单号不能为空！");
			$("#expressno").focus();
			return;
		}
		if(expressname == "") {
			alert("快递名称不能为空！");
			$("#expressname").focus();
			return;
		}
/*		if(expressaddress == "") {
			alert("快递地址不能为空！");
			$("#expressaddress").focus();
			return;
		}
*/		
		if(expresstaxamount == "") {
			alert("转运费用合计不能为空！");
			$("#expresstaxamount").focus();
			return;
		}
		if(!isReal(expresstaxamount)) {
			alert("转运费用合计必须为大于0的实数！");
			$("#expresstaxamount").focus();
			return;
		}
		if(expressmanager == "") {
			alert("快递联系人不能为空！");
			$("#expressmanager").focus();
			return;
		}
/*		if(expresstel == "") {
			alert("快递联系人电话不能为空！");
			$("#expresstel").focus();
			return;
		}
*/
/*		if(expressfax == "") {
			alert("快递联系人传真不能为空！");
			$("#expressfax").focus();
			return;
		}
*/
		if(tmpReceiptdate == "") {
			alert("单据日期不能为空！");
			$("#tmpReceiptdate").focus();
			return;
		}

		if(tmpWarehousedate == "") {
			alert("发货日期不能为空！");
			$("#tmpWarehousedate").focus();
			return;
		}
/*		if(expressmail == "") {
			alert("信箱不能为空！");
			$("#expressmail").focus();
			return;
		}
*/		
		var b = false;
		//验证退换货
		var list = document.getElementsByName("tmpHasbroken");
		for(var i = 0; i < list.length; i++) {
			var key = list[i].alt;
			var key1 = key.replace("brokennum", "hasbroken");
			if(list[i].checked) {
				var keynum = key.replace("brokennum", "num");
				var brokennum = $("#" + key).val();
				var num = $("#" + keynum).val();
				if(brokennum == "") {
					alert("请输入退换货数量！");
					$("#" + key).focus();
					return;
				}
				if(!isNumber(brokennum)) {
					alert("退换货数量必须是大于0的数字！");
					$("#" + key).focus();
					return;
				}
				if(parseInt(brokennum) > parseInt(num)) {
					alert("退换货数量不能大于总数量！");
					$("#" + key).focus();
					return;
				}
				$("#" + key1).val("1");
				b = true;
			} else {
				$("#" + key1).val("0");
			}
		}
		if(b) {
			//需要退换货，则备注不能为空
			if(tempNote == "") {
				alert("备注不能为空！");
				$("#tempNote").focus();
				return false;
			}
			if(tempNote.length > 2500) {
				alert("备注不能超过250个字！");
				$("#tempNote").focus();
				return false;
			}
		}
		
		$("#warehousedate").val($("#tmpWarehousedate").val());
		$("#receiptdate").val($("#tmpReceiptdate").val());
		//备注
		$("#note").val($("#tempNote").val());
		return true;
	}
	
	function exportData(isInter) {
		var id = ${updWarehouserptId};
		var exportunitprice = $("#exportunitprice").val().trim();
		window.location.href = "../warehouserpt/exportWarehouserptOutDetailAction.action?strExportDetailId=" + id 
				+ "&strInter=" + isInter + "&exportunitprice=" + exportunitprice;
	}
	
	//快递
	function selectDelivery() {
		var url = "../delivery/showSelectDeliveryAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goBack() {
		window.location.href = "../warehouserpt/queryWarehouserptOutAction.action";
	}
	
	function checkPrice(obj) {
		if(obj.checked) {
			document.getElementById("exportunitprice").value = 1;
		} else {
			document.getElementById("exportunitprice").value = 0;
		}
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
					发货单编辑
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="updWarehouserptDto.hasbroken" id="hasbroken"></s:hidden>
				<s:hidden name="updWarehouserptDto.note" id="note"></s:hidden>
				<s:hidden name="updWarehouserptDto.expressid" id="expressid"></s:hidden>
				<s:hidden name="updWarehouserptDto.warehousedate" id="warehousedate"></s:hidden>
				<s:hidden name="updWarehouserptDto.receiptdate" id="receiptdate"></s:hidden>
				
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">出库单号</label>
							</td>
							<td colspan="3">
								<label class="pdf10"><s:property value="updWarehouserptDto.warehouseno"/></label>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">客户</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.suppliername" disabled="true" id="suppliername" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">快递</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressname" id="expressname" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectDelivery();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td align="right">
								<label class="pdf10">快递单号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressno" id="expressno" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">客户地址</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.supplieraddress" disabled="true" id="supplieraddress" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">快递公司地址</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressaddress" id="expressaddress" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">转运费用合计</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expresstaxamount" id="expresstaxamount" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.suppliermanager" disabled="true" id="suppliermanager" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">联系人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressmanager" id="expressmanager" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red"></font>单据日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpReceiptdate" disabled="disabled" style="width:105px;" value="<s:property value="updWarehouserptDto.res01"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpReceiptdate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>							
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.suppliertel" disabled="true" id="suppliertel" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">联系人电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expresstel" id="expresstel" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人传真</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.supplierfax" disabled="true" id="supplierfax" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">联系人传真</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressfax" id="expressfax" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">发货日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpWarehousedate" disabled="disabled" style="width:105px;" value="<s:property value="updWarehouserptDto.showWarehousedate"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpWarehousedate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">联系人信箱</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.suppliermail" disabled="true" id="suppliermail" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">联系人信箱</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updWarehouserptDto.expressmail" id="expressmail" cssStyle="width:120px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="5">
								<textarea rows="5" cols="150" style="width:900px;" id="tempNote"><s:property value="updWarehouserptDto.note"/></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="info">
					<table width="100%" border="0">
						<tr>
							<td>
								<div class="tab_content" style="height: 44px;">
									<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
										<tr>
											<td colspan="11" align="center" class="tittle"><strong>产品信息</strong></td>
										</tr>
									</table>
								</div>
								<div class="tab_content" style="height: 175px;">
									<table id="productTable" class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
										<tr style="background:#eee; border-top:black solid 1px;">
											<td width="40">序号</td>
											<td width="40">类型</td>
											<td width="100">品名</td>
											<td width="90">规格</td>
											<td width="40">颜色</td>
											<td width="40">单位</td>
											<td width="40">包装</td>
											<td width="60">产地</td>
											<td width="80">数量</td>
											<td width="80">含税金额</td>
											<td width="90" style="display: none;">退货OR损毁</td>
											<td width="90" style="display: none;">退货数量</td>
										</tr>
										<s:iterator id="updWarehouserptDto.listProduct" value="updWarehouserptDto.listProduct" status="st1">
											<s:if test="#st1.odd==true">
												<tr class="tr_bg">
											</s:if>
											<s:else>
												<tr>
											</s:else>
												<td><s:property value="#st1.index + 1"/></td>
												<td>
													<s:iterator id="goodsList" value="goodsList" status="st3">
														<s:if test="%{goodsList[#st3.index].code == updWarehouserptDto.listProduct[#st1.index].fieldno}">
															<s:property value="fieldname"/>
														</s:if>
													</s:iterator>
												</td>
												<td><s:property value="tradename"/></td>
												<td><s:property value="typeno"/></td>
												<td>
													<s:iterator id="colorList" value="colorList" status="st3">
														<s:if test="%{colorList[#st3.index].code == updWarehouserptDto.listProduct[#st1.index].color}">
															<s:property value="fieldname"/>
														</s:if>
													</s:iterator>
												</td>
												<td>
													<s:iterator id="unitList" value="unitList" status="st3">
														<s:if test="%{unitList[#st3.index].code == updWarehouserptDto.listProduct[#st1.index].unit}">
															<s:property value="fieldname"/>
														</s:if>
													</s:iterator>
												</td>
												<td>
													<s:if test='%{updWarehouserptDto.listProduct[#st1.index].packaging == "0"}'>整箱</s:if>
													<s:elseif test='%{updWarehouserptDto.listProduct[#st1.index].packaging == "1"}'>乱尺</s:elseif>
													<s:else>
														<s:property value="packaging"/>
													</s:else>
												</td>
												<td>
													<s:iterator id="makeareaList" value="makeareaList" status="st3">
														<s:if test="%{makeareaList[#st3.index].code == updWarehouserptDto.listProduct[#st1.index].makearea}">
															<s:property value="fieldname"/>
														</s:if>
													</s:iterator>
												</td>
												<td align="right">
													<s:if test="num < 0">
														<s:property value="numabs"/>
													</s:if>
													<s:else>
														<s:property value="num"/>
													</s:else>
												</td>
												<td align="right">
													<s:property value="amount"/>
												</td>
												<td style="display: none;">
													<s:if test='%{updWarehouserptDto.listProduct[#st1.index].hasbroken == "1"}'>
														<input type="checkbox" alt="brokennum<s:property value="#st1.index"/>" name="tmpHasbroken" id="tmpHasbroken" checked="checked"/>
													</s:if>
													<s:else>
														<input type="checkbox" alt="brokennum<s:property value="#st1.index"/>" name="tmpHasbroken" id="tmpHasbroken"/>
													</s:else>
													<input type="hidden" name="updWarehouserptDto.listProduct[<s:property value="#st1.index"/>].hasbroken" id="hasbroken<s:property value="#st1.index"/>" value="<s:property value="hasbroken"/>"/>
													<input type="hidden" id="num<s:property value="#st1.index"/>" value="<s:property value="num"/>"/>
												</td>
												<td style="display: none;">
													<input type="text" name="updWarehouserptDto.listProduct[<s:property value="#st1.index"/>].brokennum" id="brokennum<s:property value="#st1.index"/>" maxlength="16" value="<s:property value="brokennum"/>" style="width: 80px;"/>
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
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
										<input class="input80" type="button" value="更新" onclick="upd();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input120" type="button" value="出库配货单导出" onclick="exportData(1);"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input120" type="button" style="width:120px" value="出货明细单导出" onclick="exportData(2);"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="返回" onclick="goBack();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<input id="exportunitprice" type="checkbox" onclick="checkPrice(this)" value="0"/>导出含税单价
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
