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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Calendar3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.js"></script>
<title>采购单明细</title>
<script type="text/javascript">
	function upd() {
		if($("#status").val() == "20") {
			if(!$("#tmpRefund").attr("checked")) {
				alert("该数据不可以修改！");
				return;
			}
		}
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = '../purchase/updPurchaseitemAction.action';
				document.mainform.submit();
			}
		}
	}
	
	var checkflag = false;
	
	function calcAmount(obj, type) {
		if(checkflag) {
			return;
		}
		var tr = obj.parentNode.parentNode;
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		
		//已付金额tmp
		var paidamount = $("#tmpPaidamount").val();
		
		var rate = parseFloat($("#common_rate").val());
		if(type == "1") {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("采购金额（未税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
			//计算未税金额
			var purchaseAmount = tds[16].getElementsByTagName("input")[0].value.trim();
			if(purchaseAmount == "") {
				purchaseAmount = 0;
			}
			var taxamount = parseFloat(purchaseAmount) * (1 + parseFloat(rate));
			//计算含税金额
			tds[17].getElementsByTagName("input")[0].value = taxamount.toFixed(2);
			//隐藏域
			//采购金额未税
			inputs[12].value = purchaseAmount;
			inputs[13].value = taxamount.toFixed(2);
		} else {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("采购金额（含税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
			//采购金额已税
			var purchaseTaxamount = tds[17].getElementsByTagName("input")[0].value.trim();
			if(purchaseTaxamount == "") {
				purchaseTaxamount = 0;
			}
			var amount = parseFloat(purchaseTaxamount) / (1 + parseFloat(rate));
			//计算未税金额
			tds[16].getElementsByTagName("input")[0].value = amount.toFixed(2);
			
			//隐藏域
			//采购金额未税
			inputs[12].value = amount.toFixed(2);
			inputs[13].value = purchaseTaxamount;
		}
		
		//采购金额未税
		var calcAmount = 0;
		//已付金额（默认为0）
		var calcPaidamount = 0;
		//采购金额含税
		var calcTaxamount = 0;
		
		var rows = document.getElementById("productData").rows;
		for(var i = 0; i < rows.length; i++) {
			var childs = rows[i].cells[0].getElementsByTagName("input");
			if(childs[12].value.trim() != "") {
				calcAmount += parseFloat(childs[12].value.trim());
			}
			if(childs[13].value.trim() != "") {
				calcTaxamount += parseFloat(childs[13].value.trim());
			}
		}
		
		//采购金额不含税
		$("#totalamount").val(calcAmount.toFixed(2));
		$("#tmpTotalamount").val(calcAmount.toFixed(2));
		
		//采购金额含税
		$("#taxamount").val(calcTaxamount.toFixed(2));
		$("#tmpTaxamount").val(calcTaxamount.toFixed(2));
		
		//已付金额
		if(paidamount == "") {
			$("#paidamount").val(calcPaidamount.toFixed(2));
			$("#tmpPaidamount").val(calcPaidamount.toFixed(2));
		}
	}
	
	//计算采购数量及金额
	function calcquantity(obj, type) {
		if(checkflag) {
			return;
		}
		if(type == "1") {
			//是否大于0的数字check
			if(!isReal(obj.value)) {
				alert("采购数量格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		} else if(type == "2") {
			//是否整数字check
			if(!isAllReal(obj.value)) {
				alert("预入库数格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		} else if(type == "4") {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("未税单价格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		} else if(type == "6") {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("含税单价格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		} else if(type == "5") {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("采购金额（未税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		} else if(type == "9") {
			//备注
		} else {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("采购金额（含税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		}
		var tr = obj.parentNode.parentNode;
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		
		var inputPurchaseQuantitys = tds[10].getElementsByTagName("input");
		var beforeQuantitys = tds[11].getElementsByTagName("input");
		//采购单货物数量
		var purchaseQuantity = inputPurchaseQuantitys[0].value;
		//采购金额已税
		var purchaseTaxamount = tds[17].getElementsByTagName("input")[0].value;
		//预入库数量
		var beforeQuantity = beforeQuantitys[0].value;
		
		//已付金额tmp
		var paidamount = $("#tmpPaidamount").val();
		
		//备注
		var res09 = tds[19].getElementsByTagName("input")[0].value.trim();
		
		//单价
		var prices = tds[14].getElementsByTagName("input");
		//含税单价
		var taxprices = tds[15].getElementsByTagName("input")[0].value.trim();
		if(taxprices == "") {
			taxprices = 0;
		}
		
		//rate为税率
		var rate = parseFloat($("#common_rate").val());
		//var price = tds[13].innerHTML;
		var price = prices[0].value.trim();
		if(price == "") {
			price = 0;
		}
		
		if(type == "6") {
			//计算未税单价
			price = parseFloat(taxprices) / (1 + rate);
			tds[14].getElementsByTagName("input")[0].value = price.toFixed(6);
		}
		if(type == "4") {
			//计算已税单价
			taxprices = parseFloat(price) * (1 + rate);
			tds[15].getElementsByTagName("input")[0].value = taxprices.toFixed(6);
		}
		
		if(purchaseQuantity == "") {
			purchaseQuantity = 0;
		} else {
			purchaseQuantity = parseFloat(purchaseQuantity).toFixed(2);
		}
		if(beforeQuantity == "") {
			beforeQuantity = 0;
		} else {
			beforeQuantity = parseFloat(beforeQuantity).toFixed(2);
		}
		//已入库数量
		var inquantity = inputs[14].value;
		if(inquantity == "") {
			inquantity = 0;
		} else {
			inquantity = parseFloat(inquantity).toFixed(2);
		}
		
		//逻辑check
		var tmp1 = parseFloat(inquantity) + parseFloat(beforeQuantity);
		if(parseFloat(beforeQuantity) > parseFloat(purchaseQuantity) || tmp1 < 0 || tmp1 > purchaseQuantity) {
			alert("预入库数不在正确范围！");
			checkflag = ture;
			obj.focus();
			checkflag = false;
			return;
		}
			
		//未入库数量=采购数量-预入库数量-已入库数量
		var remain = purchaseQuantity - beforeQuantity;
		remain = remain.toFixed(2);
		remain = remain - inquantity;
		remain = remain.toFixed(2);
		
		tds[13].innerHTML = remain;
		
		//补充隐藏TD中的数据内容
		//===============================================
		//入库数量
		inputs[9].value = purchaseQuantity;
		//预入库数量
		inputs[10].value = beforeQuantity;
		//未入库数量
		inputs[11].value = remain;
	}
	
	function changeTheme() {
		//清空采购单货物列表
		$("#productData").empty();
		//清空ID
		$("#productlist").val("");
	}
	
	//验证数据格式
	function checkItem() {
		//采购单号
		//var purchaseno = $("#purchaseno").val().trim();
		//采购日期
		var tmpPurchasedate = $("#tmpPurchasedate").val().trim();
		//支付方式
		var res01 = $("#res01").val().trim();
		
		//交货期
		var res03 = $("#res03").val().trim();
		//报价有效期
		var res04 = $("#res04").val().trim();
		
		//采购订单号
		var theme2 = $("#theme2").val().trim();
		//经手人
		//var handler = $("#handler").val().trim();
		//采购主题
		//var theme1 = $("#theme1").val().trim();
		//仓库
		//var warehouse = $("#warehouse").val().trim();
		
		//采购金额合计
		var tmpTotalamount = $("#tmpTotalamount").val().trim();
		//采购金额合计（含税）
		var tmpTaxamount = $("#tmpTaxamount").val().trim();
		//已付金额
		var tmpPaidamount = $("#tmpPaidamount").val().trim();
		
		//供应商ID
		var supplierid = $("#supplierid").val().trim();
		//供应商名
		var suppliername = $("#suppliername").val().trim();
		//供应商地址
		//var supplieraddr = $("#supplieraddr").val().trim();
		//联系人
		var suppliermanager = $("#suppliermanager").val().trim();
		//联系人地址
		var suppliermanageraddr = $("#suppliermanageraddr").val().trim();
		//联系人电话
		var suppliertel = $("#suppliertel").val().trim();
		//联系人传真
		var supplierfax = $("#supplierfax").val().trim();
		//联系人信箱
		var suppliermail = $("#suppliermail").val().trim();
		
		//预入库时间
		var tmpPlandate = $("#tmpPlandate").val().trim();
		
		/*
		if(theme2 == "") {
			alert("采购订单号不能为空！");
			$("#theme2").focus();
			return;
		}//*/
		
		if(tmpPurchasedate == "") {
			alert("采购日期不能为空！");
			$("#tmpPurchasedate").focus();
			return;
		}
		if(res01 == "") {
			alert("请选择支付方式！");
			$("#res01").focus();
			return;
		}
		if(tmpPlandate == "") {
			alert("预入库时间不能为空！");
			$("#tmpPlandate").focus();
			return;
		}
		/*
		if(handler == "") {
			alert("经手人不能为空！");
			$("#handler").focus();
			return;
		}
		if(theme1 == "") {
			alert("请选择采购主题！");
			$("#handler").focus();
			return;
		}
		if(warehouse == "") {
			alert("仓库不能为空！");
			$("#warehouse").focus();
			return;
		}
		//*/
		if(supplierid == "") {
			alert("请选择供应商！");
			$("#supplierid").focus();
			return;
		}
		if(suppliername == "") {
			alert("供应商名称不能为空！");
			$("#suppliername").focus();
			return;
		}
		if(suppliermanager == "") {
			alert("联系人不能为空！");
			$("#suppliermanager").focus();
			return;
		}
		if(suppliermanageraddr == "") {
			alert("联系人地址不能为空！");
			$("#suppliermanageraddr").focus();
			return;
		}
		if(suppliertel == "") {
			alert("联系人电话不能为空！");
			$("#suppliertel").focus();
			return;
		}
/*		if(supplierfax == "") {
			alert("联系人传真不能为空！");
			$("#supplierfax").focus();
			return;
		}
		if(suppliermail == "") {
			alert("联系人信箱不能为空！");
			$("#suppliermail").focus();
			return;
		}
*/

		if(tmpTotalamount != "") {
			if(!isReal(tmpTotalamount)) {
				alert("采购金额（不含税）格式不正确！");
				$("#tmpTotalamount").focus();
				return;
			}
		} else {
			$("#tmpTotalamount").val("0");
		}
		if(tmpPaidamount != "") {
			if(!isReal(tmpPaidamount)) {
				alert("已付金额格式不正确！");
				$("#tmpPaidamount").focus();
				return;
			}
		} else {
			$("#tmpPaidamount").val("0");
		}
		if(tmpTaxamount != "") {
			if(!isReal(tmpTaxamount)) {
				alert("采购金额（含税）格式不正确！");
				$("#tmpTaxamount").focus();
				return;
			}
		} else {
			$("#tmpTaxamount").val("0");
		}
		if(parseFloat(tmpTotalamount) > parseFloat(tmpTaxamount)) {
			alert("采购金额（不含税）不能大于采购金额（含税）！");
				$("#tmpTaxamount").focus();
				return;
		}
		
		$("#totalamount").val($("#tmpTotalamount").val());
		$("#taxamount").val($("#tmpTaxamount").val());
		$("#paidamount").val($("#tmpPaidamount").val());
		
		$("#purchasedate").val($("#tmpPurchasedate").val());
		$("#plandate").val($("#tmpPlandate").val());
		
		//退换货标识
		if($("#tmpRefund").attr("checked")) {
			$("#refundflag").val("1");
		} else {
			$("#refundflag").val("0");
		}
		
/*
		if(res03 != "") {
			if(!isNumber(res03)) {
				alert("交货期格式不正确！");
				$("#res03").focus();
				return;
			}
		}
		if(res04 != "") {
			if(!isNumber(res04)) {
				alert("报价有效期格式不正确！");
				$("#res04").focus();
				return;
			}
		}
*/

		//备注
		var tmpNote = $("#tmpNote").val();
		if(tmpNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tmpNote").focus();
			return false;
		}
		$("#note").val(tmpNote);
		
		if(!setPurchaseItemList()) {
			return false;
		}
		return true;
	}
	
	//采购货物列表
	function setPurchaseItemList() {
		$("#purchaseItemTable").empty();
		var rows = document.getElementById("productData").rows;
		for(var i = 0; i < rows.length; i++) {
			var childs = rows[i].cells[0].getElementsByTagName("input");
			var id = childs[0].value;
			var productid = childs[1].value;
			var theme1 = childs[2].value;
			var tradename = childs[3].value;
			var typeno = childs[4].value;
			var color = childs[5].value;
			var unit = childs[6].value;
			var packaging = childs[7].value;
			var unitprice = childs[8].value;
			
			var quantity = childs[9].value;
			//预入库数
			var beforequantity = childs[10].value;
			var remainquantity = childs[11].value;
			var amount = childs[12].value;
			var taxamount = childs[13].value;
			//已入库数
			var inquantity = childs[14].value;
			
			//备注
			var res09 = childs[15].value;
			
			//含税单价
			var taxunitprice = childs[16].value;
			
			//产地
			var makearea = childs[17].value;
			
			var tr = document.createElement("tr");
			//采购货物列表
			var td = document.createElement("td");
			
			//货物数据check
			if(quantity == "") {
				alert("采购数量不能为空！");
				$("#" + childs[9].alt).focus();
				return false;
			}
			
			td.appendChild(createInput("updPurchaseItemList[" + i + "].id", id));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].productid", productid));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].theme1", theme1));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].tradename", tradename));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].typeno", typeno));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].color", color));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].unit", unit));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].packaging", packaging));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].unitprice", unitprice));
			
			td.appendChild(createInput("updPurchaseItemList[" + i + "].taxunitprice", taxunitprice));
			
			td.appendChild(createInput("updPurchaseItemList[" + i + "].quantity", quantity));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].beforequantity", beforequantity));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].inquantity", inquantity));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].remainquantity", remainquantity));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].amount", amount));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].taxamount", taxamount));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].res09", res09));
			td.appendChild(createInput("updPurchaseItemList[" + i + "].makearea", makearea));
			
			tr.appendChild(td);
			document.getElementById("purchaseItemTable").appendChild(tr);
		}
		return true;
	}
	
	function createInput(id, value) {
		var input = document.createElement("input");
		input.type = "text";
		input.name = id;
		input.value = value;
		return input;
	}
	
	//对数字类型的，为空时设为0
	function setDefaultValue(id) {
		if($("#" + id).val() == "") {
			$("#" + id).attr("value", "0");
		}
	}
	
	function addProduct() {
		//采购主题
		var theme1 = "";//$("#theme1").val().trim();
		var supplierid = $("#supplierid").val().trim();
		//if(theme1 == "") {
		//	alert("请选择采购主题！");
		//	$("#theme1").focus();
		//	return;
		//}
		//if(supplierid == "") {
		//	alert("请选择供应商！");
		//	$("#supplierid").focus();
		//	return;
		//}
		var rows = document.getElementById("productData").rows;
		var seq = rows.length + 1;
		var url = '<%=request.getContextPath()%>/product/showProductSelectPage.action';
		//strFlag=1采购单，strFlag=2销售单
		url += "?strFieldno=" + theme1 + "&strSeq=" + seq + "&strSupplierId=" + supplierid + "&strFlag=1" + "&date=" + new Date();
		
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function updProduct() {
	}
	
	function delProduct() {
		//获得选择的投标公司
		var list = document.getElementsByName("itemRadio");
		var currentProduct = "";
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				if(confirm("确定删除该记录吗？")) {
					var tr = list[i].parentNode.parentNode;
					//取得产品ID
					var tds = tr.getElementsByTagName("td");
					var inputs = tds[0].getElementsByTagName("input");
					currentProduct = inputs[1].value;
					var tbody = list[i].parentNode.parentNode.parentNode;
					tbody.removeChild(tr);
					break;
				} else {
					return;
				}
			}
		}
		//刷新productlist值
		var productlist = $("#productlist").val();
		var ll = productlist.split(",");
		var newProductlist = "";
		for(var j = 0; j < ll.length; j++) {
			if(ll[j] != "" && ll[j] != currentProduct) {
				newProductlist += ll[j] + ",";
			}
		}
		$("#productlist").val(newProductlist);
		refreshItemData();
	}
	
	//刷新序号和斑马线
	function refreshItemData() {
		var rows = document.getElementById("productData").rows;
		for(var i = 0; i < rows.length; i++) {
			var num = i + 1;
			rows[i].cells[2].innerHTML = num;
			//斑马线
			var cls = "";
			if(i % 2 == 0) {
				cls = "tr_bg";
			} else {
				cls = "";
			}
			rows[i].className = cls;
		}
	}
	
	//用户
	function selectUser() {
		var url = "../user/showSelectUserAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//供应商
	function selectSupplier() {
		var url = "../supplier/showSelectSupplierAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goPurchaseList() {
		window.location.href = "../purchase/queryPurchaseAction.action";
	}

	function exportData(flag) {
		window.location.href = '../purchase/exportPurchaseAction.action?updPurchaseId=' + $("#id").val() + '&exporttype=' + flag;
	}
	
	function productCompare() {
		var url = "../warehouse/showWarehouseDetailPopupAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:1000px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<!--
			<div class="tittle">
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					采购单明细
				</div>
				<div class="tittle_right">
				</div>
			</div>
			-->
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="common_rate" id="common_rate"></s:hidden>
				
				<s:hidden name="updPurchaseDto.id" id="id"></s:hidden>
				
				<s:hidden name="updPurchaseDto.supplierid" id="supplierid"></s:hidden>
				<s:hidden name="updPurchaseDto.purchasedate" id="purchasedate"></s:hidden>
				<s:hidden name="updPurchaseDto.productlist" id="productlist"></s:hidden>
				
				<s:hidden name="updPurchaseDto.taxamount" id="taxamount"></s:hidden>
				<s:hidden name="updPurchaseDto.paidamount" id="paidamount"></s:hidden>
				<s:hidden name="updPurchaseDto.totalamount" id="totalamount"></s:hidden>
				<s:hidden name="updPurchaseDto.status" id="status"></s:hidden>
				
				<s:hidden name="updPurchaseDto.plandate" id="plandate"></s:hidden>
				
				<s:hidden name="updPurchaseDto.handler" id="handler"></s:hidden>
				<s:hidden name="updPurchaseDto.handlername" id="handlername"></s:hidden>
				
				<s:hidden name="updPurchaseDto.note" id="note"></s:hidden>
				<s:hidden name="updPurchaseDto.refundflag" id="refundflag"></s:hidden>
				
				<div class="searchbox update" style="height:0px;">
					<table id="purchaseItemTable" style="display: none;">
					</table>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购订单号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.theme2" disabled="true" id="theme2" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpPurchasedate" disabled="disabled" style="width:285px;" value="<s:property value="updPurchaseDto.showPurchasedate"/>" />
									<a class="date" href="javascript:;" onclick=""></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>支付方式</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updPurchaseDto.res01" disabled="disabled" id="res01" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="payTypeList" id="payTypeList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{payTypeList[#st1.index].code == updPurchaseDto.res01}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>预入库日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpPlandate" disabled="disabled" style="width:285px;" value="<s:property value="updPurchaseDto.plandate"/>" />
									<a class="date" href="javascript:;" onclick=""></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<!--
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>仓库</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.warehouse" id="warehouse" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>经手人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmphandlername" disabled="disabled" style="width:285px;" value="<s:property value="updPurchaseDto.handlername"/>" />
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectUser();" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购主题</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updPurchaseDto.theme1" id="theme1" style="width: 300px;" disabled="disabled" onchange="changeTheme();">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == updPurchaseDto.theme1}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						-->
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>供应商</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.suppliername" disabled="true" id="suppliername" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input id="agentCompBtn" class="input40" type="button" value="检索" onclick="" />
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>联系人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.suppliermanager" disabled="true" id="suppliermanager" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<!--
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>供应商地址</label>
							</td>
							<td colspan="3">
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.supplieraddr" id="supplieraddr" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						-->
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>联系人地址</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.suppliermanageraddr" disabled="true" id="suppliermanageraddr" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>联系人电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.suppliertel" disabled="true" id="suppliertel" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">&nbsp;联系人传真</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.supplierfax" disabled="true" id="supplierfax" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">&nbsp;联系人信箱</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.suppliermail" disabled="true" id="suppliermail" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpTotalamount" disabled="disabled" maxlength="12" style="width:300px;" value="<s:property value="updPurchaseDto.totalamount"/>"/>
								</div>
								<div class="box1_right"></div>
								<div style="margin-top: 9px;"><label>（不含税）</label></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpTaxamount" disabled="disabled" maxlength="12" style="width:300px;" value="<s:property value="updPurchaseDto.taxamount"/>"/>
								</div>
								<div class="box1_right"></div>
								<div style="margin-top: 9px;"><label>（含税）</label></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>已付金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpPaidamount" disabled="disabled" maxlength="12" style="width:300px;" value="<s:property value="updPurchaseDto.paidamount"/>"/>
								</div>
								<div class="box1_right"></div>
								<div style="margin-top: 9px;"><label>（含税）</label></div>
							</td>
							<td align="right">
								<label class="pdf10">退换货标识</label>
							</td>
							<td>
								<s:if test='updPurchaseDto.refundflag == "1"'>
									<input id="tmpRefund" type="checkbox" onclick="changeBackcolor(this);" checked="checked" value="1"/>
								</s:if>
								<s:else>
									<input id="tmpRefund" type="checkbox" onclick="changeBackcolor(this);" value="1"/>
								</s:else>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">交货期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.res03" disabled="true" id="res03" maxlength="10" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">报价有效期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updPurchaseDto.res04" disabled="true" id="res04" maxlength="10" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea id="tmpNote" rows="3" cols="" style="width: 886px;"><s:property value="updPurchaseDto.note"/></textarea>
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
								<div class="tab_content" style="height: 305px;">
									<table id="productTable" class="info_tab" width="140%" border="1" cellpadding="5" cellspacing="0">
										<tr style="background:#eee; border-top:black solid 1px;">
											<td style="width: 0px; display: none"></td>
											<td width="30"></td>
											<td width="35">序号</td>
											<td width="100">类型</td>
											<td width="100">品名</td>
											<td width="90">规格</td>
											<td width="35">颜色</td>
											<td width="35">单位</td>
											<td width="35">形式</td>
											<td width="60">产地</td>
											<td width="85">采购数量</td>
											<td width="85">预入库数</td>
											<td width="70">已入库数</td>
											<td width="70">未入库数</td>
											<td width="90">未税单价</td>
											<td width="90" style="background:#86e657;">含税单价</td>
											<td width="110">采购金额（未税）</td>
											<td width="110" style="background:#86e657;">采购金额（含税）</td>
											<td width="110">包装</td>
											<td width="150">备注</td>
										</tr>
										<tbody id="productData">
											<s:iterator id="updPurchaseItemList" value="updPurchaseItemList" status="st1">
												<s:if test="#st1.odd==true">
													<tr class="tr_bg">
												</s:if>
												<s:else>
													<tr>
												</s:else>
													<td style="width: 0px; display: none;">
														<input type="hidden" value="<s:property value="id"/>" />
														<input type="hidden" value="<s:property value="productid"/>" />
														<input type="hidden" value="<s:property value="theme1"/>" />
														<input type="hidden" value="<s:property value="tradename"/>" />
														<input type="hidden" value="<s:property value="typeno"/>" />
														<input type="hidden" value="<s:property value="color"/>" />
														<input type="hidden" value="<s:property value="unit"/>" />
														<input type="hidden" value="<s:property value="packaging"/>" />
														<input type="hidden" value="<s:property value="unitprice"/>" />
														
														<input type="hidden" alt="tmpQuantity_<s:property value="productid"/>" value="<s:property value="quantity"/>" />
														<input type="hidden" alt="tmpBeforeQuantity_<s:property value="productid"/>" value="<s:property value="beforequantity"/>" />
														<input type="hidden" value="<s:property value="remainquantity"/>" />
														<input type="hidden" value="<s:property value="amount"/>" />
														<input type="hidden" alt="tmpTaxamount_<s:property value="productid"/>" value="<s:property value="taxamount"/>" />
														<input type="hidden" value="<s:property value="inquantity"/>" />
														<input type="hidden" value="<s:property value="res09"/>" />
														<input type="hidden" value="<s:property value="taxunitprice"/>" />
														<input type="hidden" value="<s:property value="makearea"/>" />
													</td>
													<td><input name="itemRadio" type="radio" /></td>
													<td><s:property value="#st1.index + 1"/></td>
													<td>
														<s:iterator id="goodsList" value="goodsList" status="st3">
															<s:if test="%{goodsList[#st3.index].code == updPurchaseItemList[#st1.index].theme1}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td><s:property value="tradename"/></td>
													<td><s:property value="typeno"/></td>
													<td>
														<s:iterator id="colorList" value="colorList" status="st3">
															<s:if test="%{colorList[#st3.index].code == updPurchaseItemList[#st1.index].color}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td>
														<s:iterator id="unitList" value="unitList" status="st3">
															<s:if test="%{unitList[#st3.index].code == updPurchaseItemList[#st1.index].unit}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td>
														<s:if test='%{updPurchaseItemList[#st1.index].packaging == "0"}'>整箱</s:if>
														<s:elseif test='%{updPurchaseItemList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
														<s:else>
															<s:property value="packaging"/>
														</s:else>
													</td>
													<td>
														<s:iterator id="makeareaList" value="makeareaList" status="st3">
															<s:if test="%{makeareaList[#st3.index].code == updPurchaseItemList[#st1.index].makearea}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 80px;" id="tmpQuantity_<s:property value="productid"/>" onblur="calcquantity(this, '1');" maxlength="11" value="<s:property value="quantity"/>"/>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpBeforeQuantity_<s:property value="productid"/>" onblur="calcquantity(this, '2');" maxlength="11" value="<s:property value="beforequantity"/>"/>
													</td>
													<td align="right"><s:property value="inquantity"/></td>
													<td align="right"><s:property value="remainquantity"/></td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 80px;" id="tmpUnitprice_<s:property value="productid"/>" onblur="calcquantity(this, '4');" maxlength="17" value="<s:property value="unitprice"/>"/>
													</td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 80px;" id="tmpTaxUnitprice_<s:property value="productid"/>" onblur="calcquantity(this, '6');" maxlength="17" value="<s:property value="taxunitprice"/>"/>
													</td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 80px;" id="tmpAmount_<s:property value="productid"/>" onblur="calcAmount(this, '1');" maxlength="13" value="<s:property value="amount"/>"/>
													</td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 80px;" id="tmpTaxamount_<s:property value="productid"/>" onblur="calcAmount(this, '2');" maxlength="13" value="<s:property value="taxamount"/>"/>
													</td>
													<td>
														<s:property value="item01"/>
													</td>
													<td align="right">
														<input type="text" disabled="disabled" style="width: 130px;" id="tmpRes09_<s:property value="productid"/>" onblur="calcquantity(this, '9');" maxlength="32" value="<s:property value="res09"/>"/>
													</td>
												</tr>
											</s:iterator>
										</tbody>
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
										<input class="input120" type="button" value="电线订单导出" onclick="exportData();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input120" type="button" value="套管订单导出" onclick="exportData('sumitube');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input120" type="button" value="FFC订单导出" onclick="exportData('sumicard');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input120" type="button" value="其他订单导出" onclick="exportData('html');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="提交" onclick="upd();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<!--
										<input class="input80" type="button" value="返回" onclick="goPurchaseList();"/>
										-->
										<input class="input80" type="button" value="关闭" onclick="window.close();"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="产品对照" onclick="productCompare();"/>
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
