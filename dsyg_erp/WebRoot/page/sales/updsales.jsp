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
<title>销售信息编辑</title>
<script type="text/javascript">
	var addflag = false;
	
	function upd() {
		if($("#status").val() == "20") {
			if(!$("#tmpRefund").attr("checked")) {
				alert("该数据不可以修改！");
				return;
			}
		}
		if(checkItem()) {
			tmpCalcAmount();
			addflag = true;
			
			/*
			if(confirm("确定提交吗？")) {
				document.mainform.action = "../sales/updSalesAction.action";
				document.mainform.submit();
			}//*/

			//验证货物数量
			//*
			$.ajax({
				url:"../warehouse/checkProductAmountAction.action?date" + new Date(),
				async:false,
				type:"POST",
				dataType:"json",
				data:{
					"productInfo":$("#productAmountInfo").val()
				},
				success:function(data) {
					if(data.msg != "") {
						alert(data.msg);
					} else {
						if(confirm("确定提交吗？")) {
							document.mainform.action = "../sales/updSalesAction.action";
							document.mainform.submit();
						}
					}
					addflag = false;
				},
				error:function(data) {
					addflag = false;
				}
			});//*/
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
				alert("销售金额（未税）格式不正确！");
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
			//销售金额未税
			inputs[12].value = purchaseAmount;
			inputs[13].value = taxamount.toFixed(2);
		} else {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("销售金额（含税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
			//销售金额已税
			var purchaseTaxamount = tds[17].getElementsByTagName("input")[0].value.trim();
			if(purchaseTaxamount == "") {
				purchaseTaxamount = 0;
			}
			var amount = parseFloat(purchaseTaxamount) / (1 + parseFloat(rate));
			//计算未税金额
			tds[16].getElementsByTagName("input")[0].value = amount.toFixed(2);
			
			//隐藏域
			//销售金额未税
			inputs[12].value = amount.toFixed(2);
			inputs[13].value = purchaseTaxamount;
		}
		
		//销售金额未税
		var calcAmount = 0;
		//已付金额（默认为0）
		var calcPaidamount = 0;
		//销售金额含税
		var calcTaxamount = 0;
		
		var rows = document.getElementById("productData").rows;
		for(var i = 0; i < rows.length; i++) {
			var childs = rows[i].cells[0].getElementsByTagName("input");
			if(childs[12].value != "") {
				calcAmount += parseFloat(childs[12].value);
			}
			if(childs[13].value != "") {
				calcTaxamount += parseFloat(childs[13].value);
			}
		}
		
		//销售金额不含税
		$("#amount").val(calcAmount.toFixed(2));
		$("#tmpAmount").val(calcAmount.toFixed(2));
		
		//销售金额含税
		$("#taxamount").val(calcTaxamount.toFixed(2));
		$("#tmpTaxamount").val(calcTaxamount.toFixed(2));
		
		//已付金额
		if(paidamount == "") {
			$("#paidamount").val(calcPaidamount.toFixed(2));
			$("#tmpPaidamount").val(calcPaidamount.toFixed(2));
		}
	}
	
	//计算销售数量及金额
	function calcquantity(obj, type) {
		if(checkflag) {
			return;
		}
		var res02 = getRadioValue("salesType");	
		if(type == "1") {
			if(res02 == "1") {
				//是否是数字check
				if(!isReal(obj.value)) {
					alert("销售数量格式不正确！");
					checkflag = ture;
					obj.focus();
					checkflag = false;
					return;
				}
			} else {
				//是否大于0的数字check
				if(!isReal(obj.value)) {
					alert("销售数量格式不正确！");
					checkflag = ture;
					obj.focus();
					checkflag = false;
					return;
				}
			}
		} else if(type == "2") {
			//是否整数字check
			if(!isAllReal(obj.value)) {
				alert("预出库数格式不正确！");
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
		} else if(type == "9") {
			//备注
		} else {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("销售金额（含税）格式不正确！");
				checkflag = ture;
				obj.focus();
				checkflag = false;
				return;
			}
		}
		var tr = obj.parentNode.parentNode;
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		
		var inputQuantitys = tds[10].getElementsByTagName("input");
		var beforeQuantitys = tds[11].getElementsByTagName("input");
		//销售单货物数量
		var salesQuantity = inputQuantitys[0].value;
		//销售金额已税
		var salesTaxamount = tds[17].getElementsByTagName("input")[0].value;
		//预出库数量
		var beforeQuantity = beforeQuantitys[0].value;
		
		//已付金额tmp
		var paidamount = $("#tmpPaidamount").val();
		
		//备注
		var res09 = tds[19].getElementsByTagName("input")[0].value.trim();
		
		if(salesQuantity == "") {
			salesQuantity = 0;
		} else {
			salesQuantity = parseFloat(salesQuantity).toFixed(2);
		}
		if(beforeQuantity == "") {
			beforeQuantity = 0;
		} else {
			beforeQuantity = parseFloat(beforeQuantity).toFixed(2);
		}
		
		//rate为税率
		var rate = parseFloat($("#common_rate").val());
		
		//单价
		var prices = tds[14].getElementsByTagName("input");
		//var price = tds[13].innerHTML;
		var price = prices[0].value.trim();
		if(price == "") {
			price = 0;
		}
		
		//含税单价
		var taxprices = tds[15].getElementsByTagName("input")[0].value.trim();
		if(taxprices == "") {
			taxprices = 0;
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
		
		//已出库数量
		var outquantity = inputs[14].value;
		if(outquantity == "") {
			outquantity = 0;
		} else {
			outquantity = parseFloat(outquantity).toFixed(2);
		}
		
		//逻辑check
		var tmp1 = parseFloat(outquantity) + parseFloat(beforeQuantity);
		if(parseFloat(beforeQuantity) > parseFloat(salesQuantity) || tmp1 < 0 || (tmp1 > salesQuantity)) {
			alert("预出库数不在正确范围！");
			checkflag = ture;
			obj.focus();
			checkflag = false;
			return;
		}
			
		//未出库数量=销售数量-预出库数量-已出库数量
		var remain = salesQuantity - beforeQuantity;
		remain = remain.toFixed(2);
		remain = remain - outquantity;
		remain = remain.toFixed(2);
		tds[13].innerHTML = remain;
		
		//销售金额未税
		var amount = salesQuantity * parseFloat(price);
		tds[16].getElementsByTagName("input")[0].value = amount.toFixed(2);
		
		//补充隐藏TD中的数据内容
		//===============================================
		inputs[8].value = price;
		//出库数量
		inputs[9].value = salesQuantity;
		//预出库数量
		inputs[10].value = beforeQuantity;
		//未出库数量
		inputs[11].value = remain;
		//销售金额未税
		inputs[12].value = amount.toFixed(2);
		//备注
		inputs[15].value = res09;
		//含税单价
		inputs[16].value = taxprices;
		
		//销售金额已税
		//销售金额已税=未税金额 * (1 + rate)
		var vv = amount * (1 + rate);
		inputs[13].value = vv.toFixed(2);
		//输入框金额也对应变更
		tds[17].getElementsByTagName("input")[0].value = vv.toFixed(2);
		
		//销售金额未税
		var calcAmount = 0;
		//已付金额（默认为0）
		var calcPaidamount = 0;
		//销售金额含税
		var calcTaxamount = 0;
		
		var rows = document.getElementById("productData").rows;
		for(var i = 0; i < rows.length; i++) {
			var childs = rows[i].cells[0].getElementsByTagName("input");
			if(childs[12].value != "") {
				calcAmount += parseFloat(childs[12].value);
			}
			if(childs[13].value != "") {
				calcTaxamount += parseFloat(childs[13].value);
			}
		}
		
		//销售金额不含税
		$("#amount").val(calcAmount.toFixed(2));
		$("#tmpAmount").val(calcAmount.toFixed(2));
		
		//销售金额含税
		$("#taxamount").val(calcTaxamount.toFixed(2));
		$("#tmpTaxamount").val(calcTaxamount.toFixed(2));
		
		//已付金额
		if(paidamount == "") {
			$("#paidamount").val(calcPaidamount.toFixed(2));
			$("#tmpPaidamount").val(calcPaidamount.toFixed(2));
		}
		/*
		if(salesTaxamount == "") {
			if(amount != "") {
				//销售金额已税=未税金额 * (1 + rate)
				var vv = amount * (1 + rate);
				inputs[13].value = vv.toFixed(2);
				//输入框金额也对应变更
				tds[15].getElementsByTagName("input")[0].value = vv.toFixed(2);
			}
		} else {
			//用户自己输入的金额，则不做任何变更
			inputs[13].value = salesTaxamount;
		}//*/
		
		/*/销售金额已税
		if(salesTaxamount == "") {
			inputs[13].value = "0";
		} else {
			inputs[13].value = salesTaxamount;
		}//*/
		//===============================================
	}
	
	function changeTheme() {
		//清空销售单货物列表
		$("#productData").empty();
		//清空ID
		$("#productlist").val("");
	}
	
	//验证数据格式
	function checkItem() {
		//销售单号
		//var salesno = $("#salesno").val().trim();
		//销售日期
		var tmpBookdate = $("#tmpBookdate").val().trim();
		//支付方式
		var res01 = $("#res01").val().trim();
		
		//交货期
		var res03 = $("#res03").val().trim();
		//报价有效期
		var res04 = $("#res04").val().trim();
		
		//销售订单号
		var theme2 = $("#theme2").val().trim();
		//经手人
		//var handler = $("#handler").val().trim();
		//销售主题
		//var theme1 = $("#theme1").val().trim();
		//仓库
		var warehouse = $("#warehouse").val().trim();
		
		//销售金额（不含税）
		var tmpAmount = $("#tmpAmount").val().trim();
		//销售金额合计（含税）
		var tmpTaxamount = $("#tmpTaxamount").val().trim();
		//已付金额
		var tmpPaidamount = $("#tmpPaidamount").val().trim();
		
		//供应商ID
		var customerid = $("#customerid").val().trim();
		//供应商名
		var customername = $("#customername").val().trim();
		//供应商地址
		//var customeraddr = $("#customeraddr").val().trim();
		//联系人
		var customermanager = $("#customermanager").val().trim();
		//联系人地址
		var customeraddress = $("#customeraddress").val().trim();
		//联系人电话
		var customertel = $("#customertel").val().trim();
		//联系人传真
		var customerfax = $("#customerfax").val().trim();
		//联系人信箱
		var customermail = $("#customermail").val().trim();
		
		//预出库时间
		var tmpPlandate = $("#tmpPlandate").val().trim();
		
		if(theme2 == "") {
			alert("销售订单号不能为空！");
			$("#theme2").focus();
			return;
		}
		
		if(tmpBookdate == "") {
			alert("销售日期不能为空！");
			$("#tmpBookdate").focus();
			return;
		}
		var res02 = getRadioValue("salesType");
		if(res02 == "") {
			alert("请选择销售方式！");
			$("#tmpRes02").focus();
			return;
		}
		if(res01 == "") {
			alert("请选择支付方式！");
			$("#res01").focus();
			return;
		}
		/*
		if(handler == "") {
			alert("经手人不能为空！");
			$("#handler").focus();
			return;
		}
		if(theme1 == "") {
			alert("请选择销售主题！");
			$("#handler").focus();
			return;
		}
		if(warehouse == "") {
			alert("仓库不能为空！");
			$("#warehouse").focus();
			return;
		}//*/
		if(customerid == "") {
			alert("请选择客户！");
		
			$("#customerid").focus();
			return;
		}
		if(customername == "") {
			alert("客户名称不能为空！");
			$("#customername").focus();
			return;
		}
		if(customermanager == "") {
			alert("联系人不能为空！");
			$("#customermanager").focus();
			return;
		}
		if(customeraddress == "") {
			alert("联系人地址不能为空！");
			$("#customeraddress").focus();
			return;
		}
		if(customertel == "") {
			alert("联系人电话不能为空！");
			$("#customertel").focus();
			return;
		}
/*		if(customerfax == "") {
			alert("联系人传真不能为空！");
			$("#customerfax").focus();
			return;
		}
		if(customermail == "") {
			alert("联系人信箱不能为空！");
			$("#customermail").focus();
			return;
		}
*/		
		if(tmpAmount != "") {
			if(!isReal(tmpAmount)) {
				alert("销售金额（不含税）格式不正确！");
				$("#tmpAmount").focus();
				return;
			}
		} else {
			$("#tmpAmount").val("0");
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
				alert("销售金额（含税）格式不正确！");
				$("#tmpTaxamount").focus();
				return;
			}
		} else {
			$("#tmpTaxamount").val("0");
		}
		if(parseFloat(tmpAmount) > parseFloat(tmpTaxamount)) {
			alert("销售金额（不含税）不能大于销售金额（含税）！");
				$("#tmpTaxamount").focus();
				return;
		}
		
		if(tmpPlandate == "") {
			alert("预出库时间不能为空！");
			$("#tmpPlandate").focus();
			return;
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

		$("#res02").val(res02);
		$("#amount").val($("#tmpAmount").val());
		$("#taxamount").val($("#tmpTaxamount").val());
		$("#paidamount").val($("#tmpPaidamount").val());
		
		$("#bookdate").val($("#tmpBookdate").val());
		$("#plandate").val($("#tmpPlandate").val());
		
		//退换货标识
		if($("#tmpRefund").attr("checked")) {
			$("#refundflag").val("1");
		} else {
			$("#refundflag").val("0");
		}
		//备注
		var tmpNote = $("#tmpNote").val();
		if(tmpNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tmpNote").focus();
			return false;
		}
		$("#note").val(tmpNote);
		
		if(!setSalesItemList()) {
			return false;
		}
		return true;
	}
	
	//销售货物列表
	function setSalesItemList() {
		$("#salesItemTable").empty();
		var rows = document.getElementById("productData").rows;
		var productAmountInfo = "";
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
			//预出库数
			var beforequantity = childs[10].value;
			var remainquantity = childs[11].value;
			var amount = childs[12].value;
			var taxamount = childs[13].value;
			//已出库数
			var outquantity = childs[14].value;
			
			//备注
			var res09 = childs[15].value;
			
			//含税单价
			var taxunitprice = childs[16].value;
			//产地
			var makearea = childs[17].value;
			
			var tr = document.createElement("tr");
			//销售货物列表
			var td = document.createElement("td");
			
			productAmountInfo += productid + "," + beforequantity + "#";
			
			//货物数据check
			if(quantity == "") {
				alert("销售数量不能为空！");
				$("#" + childs[9].alt).focus();
				return false;
			}
			
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].id", id));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].productid", productid));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].theme1", theme1));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].tradename", tradename));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].typeno", typeno));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].color", color));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].unit", unit));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].packaging", packaging));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].unitprice", unitprice));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].taxunitprice", taxunitprice));
			
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].quantity", quantity));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].beforequantity", beforequantity));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].outquantity", outquantity));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].remainquantity", remainquantity));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].amount", amount));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].taxamount", taxamount));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].res09", res09));
			td.appendChild(createInput("tmpUpdSalesItemList[" + i + "].makearea", makearea));
			
			tr.appendChild(td);
			document.getElementById("salesItemTable").appendChild(tr);
		}
		$("#productAmountInfo").val(productAmountInfo);
		return true;
	}
	
	//计算总金额（更新的时候删除产品后，总金额未做计算，这里要重新计算）
	function tmpCalcAmount() {
		var rows = document.getElementById("productData").rows;
		//总金额
		var all = 0.00;
		//总金额含税
		var taxall = 0.00;
		for(var i = 0; i < rows.length; i++) {
			var childs = rows[i].cells[0].getElementsByTagName("input");
			var amount = childs[12].value;
			var taxamount = childs[13].value;
			if(amount != "") {
				all += parseFloat(amount);
				taxall += parseFloat(taxamount);
			}
		}
		//销售金额不含税
		$("#amount").val(all.toFixed(2));
		$("#tmpAmount").val(all.toFixed(2));
		
		//销售金额含税
		$("#taxamount").val(taxall.toFixed(2));
		$("#tmpTaxamount").val(taxall.toFixed(2));
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
		//销售主题
		var theme1 = "";//$("#theme1").val().trim();
		//if(theme1 == "") {
		//	alert("请选择销售主题！");
		//	$("#theme1").focus();
		//	return;
		//}
		var rows = document.getElementById("productData").rows;
		var seq = rows.length + 1;
		
		//这里需要查询库存数据
		//var url = '<%=request.getContextPath()%>/warehouse/showWarehouseProductSelectAction.action';
		//url += "?strFieldno=" + theme1 + "&date=" + new Date();
		var url = '<%=request.getContextPath()%>/product/showSalesProductSelectPage.action';
		url += "?strFieldno=" + theme1 + "&date=" + new Date();
		
		//window.open(url);
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function updProduct() {
	}
	
	function delProduct() {
		//获得选择的产品
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
	
	function getRadioValue(name) {
		var id = "";
		var list = document.getElementsByName(name);
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				break;
			}
		}
		return id;
	}
	
	//用户
	function selectUser() {
		var url = "../user/showSelectUserAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//客户
	function selectCustomer() {
		var url = "../customer/showSelectCustomerAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function goList() {
		window.location.href = "../sales/querySalesAction.action";
	}
	
	function exportData(flag) {
		window.location.href = '../sales/exportSalesPriceAction.action?exporttype=' + flag;
		//document.mainform.action = '../sales/exportSalesPriceAction.action';
		//document.mainform.submit();
	}
	
	function productCompare() {
		var url = "../warehouse/showWarehouseDetailPopupAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:1000px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	function changeThis(obj) {
		if(obj.checked) {
			if(confirm("确定终了订单？")) {
				document.mainform.action = "../sales/finishSalesAction.action";
				document.mainform.submit();
			} else {
				obj.checked = false;
			}
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
					销售信息编辑
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="common_rate" id="common_rate"></s:hidden>
				
				
				<s:hidden name="updSalesDto.customerid" id="customerid"></s:hidden>
				<s:hidden name="updSalesDto.bookdate" id="bookdate"></s:hidden>
				<s:hidden name="updSalesDto.productlist" id="productlist"></s:hidden>
				
				<s:hidden name="updSalesDto.taxamount" id="taxamount"></s:hidden>
				<s:hidden name="updSalesDto.paidamount" id="paidamount"></s:hidden>
				<s:hidden name="updSalesDto.amount" id="amount"></s:hidden>
				<s:hidden name="updSalesDto.status" id="status"></s:hidden>
				
				<s:hidden name="updSalesDto.handler" id="handler"></s:hidden>
				<s:hidden name="updSalesDto.handlername" id="handlername"></s:hidden>
				
				<s:hidden name="updSalesDto.res02" id="res02"></s:hidden>
				
				<s:hidden name="updSalesDto.note" id="note"></s:hidden>
				<s:hidden name="updSalesDto.refundflag" id="refundflag"></s:hidden>
				
				<div class="searchbox update" style="height:0px;">
					<table id="salesItemTable" style="display: none;">
					</table>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售订单号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="hidden" id="productAmountInfo"/>
									<s:if test='%{updSalesDto.res02 == "0" || updSalesDto.res02 == "" || updSalesDto.res02 == null}'>
										<s:textfield name="updSalesDto.theme2" id="theme2" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
									</s:if>
									<s:else>
										<s:textfield name="updSalesDto.theme2" disabled="true" id="theme2" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
									</s:else>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpBookdate" disabled="disabled" style="width:285px;" value="<s:property value="updSalesDto.showBookdate"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpBookdate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售方式</label>
							</td>
							<td>
								<s:if test='updSalesDto.res02 == "1"'>
									<input type="radio" disabled="disabled" id="tmpRes02" name="salesType" value="0"/>订单　
									<input type="radio" disabled="disabled" name="salesType" checked="checked" value="1"/>询价　
								</s:if>
								<s:else>
									<input type="radio" disabled="disabled" id="tmpRes02" name="salesType" checked="checked" value="0"/>订单　
									<input type="radio" disabled="disabled" name="salesType" value="1"/>询价　
								</s:else>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>支付方式</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updSalesDto.res01" id="res01" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="payTypeList" id="payTypeList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{payTypeList[#st1.index].code == updSalesDto.res01}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<s:hidden name="updSalesDto.warehouse" id="warehouse"></s:hidden>
						<!--
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>经手人</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmphandlername" disabled="disabled" style="width:285px;" value="<s:property value="updSalesDto.handlername"/>" />
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
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售主题</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updSalesDto.theme1" id="theme1" disabled="disabled" style="width: 300px;" onchange="changeTheme();">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == updSalesDto.theme1}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>仓库</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.warehouse" id="warehouse" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						-->
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>客户</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.customername" id="customername" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input40" type="button" value="检索" onclick="selectCustomer();" />
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
									<s:textfield name="updSalesDto.customermanager" id="customermanager" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
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
									<s:textfield name="updSalesDto.customeraddr" id="customeraddr" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
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
									<s:textfield name="updSalesDto.customeraddress" id="customeraddress" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>联系人电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.customertel" id="customertel" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
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
									<s:textfield name="updSalesDto.customerfax" id="customerfax" maxlength="16" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">&nbsp;联系人信箱</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.customermail" id="customermail" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpAmount" maxlength="12" style="width:300px;" value="<s:property value="updSalesDto.amount"/>"/>
								</div>
								<div class="box1_right"></div>
								<div style="margin-top: 9px;"><label>（不含税）</label></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpTaxamount" maxlength="12" style="width:300px;" value="<s:property value="updSalesDto.taxamount"/>"/>
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
									<input type="text" id="tmpPaidamount" maxlength="12" style="width:300px;" value="<s:property value="updSalesDto.paidamount"/>"/>
								</div>
								<div class="box1_right"></div>
								<div style="margin-top: 9px;"><label>（含税）</label></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>预出库日期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center date_input">
									<input type="text" id="tmpPlandate" disabled="disabled" style="width:285px;" value="<s:property value="updSalesDto.plandate"/>" />
									<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpPlandate'));"></a>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<label class="pdf10">交货期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.res03" id="res03" maxlength="10" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">报价有效期</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updSalesDto.res04" id="res04" maxlength="10" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<label class="pdf10">退换货标识</label>
							</td>
							<td>
								<s:if test='updSalesDto.refundflag == "1"'>
									<input id="tmpRefund" type="checkbox" onclick="changeBackcolor(this);" checked="checked" value="1"/>
								</s:if>
								<s:else>
									<input id="tmpRefund" type="checkbox" onclick="changeBackcolor(this);" value="1"/>
								</s:else>
							</td>
							<td align="right">
								<label class="pdf10">订单终了</label>
							</td>
							<td>
								<input id="finishOrder" type="checkbox" onclick="changeThis(this);"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea id="tmpNote" rows="3" cols="" style="width: 886px;"><s:property value="updSalesDto.note"/></textarea>
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
											<td width="85">销售数量</td>
											<td width="85">预出库数</td>
											<td width="70">已出库数</td>
											<td width="70">未出库数</td>
											<td width="90">未税单价</td>
											<td width="90" style="background:#86e657;">含税单价</td>
											<td width="110">销售金额（未税）</td>
											<td width="110" style="background:#86e657;">销售金额（含税）</td>
											<td width="110">包装</td>
											<td width="150">备注</td>
										</tr>
										<tbody id="productData">
											<s:iterator id="updSalesItemList" value="updSalesItemList" status="st1">
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
														<input type="hidden" value="<s:property value="outquantity"/>" />
														<input type="hidden" value="<s:property value="res09"/>" />
														<input type="hidden" value="<s:property value="taxunitprice"/>" />
														<input type="hidden" value="<s:property value="makearea"/>" />
													</td>
													<td><input name="itemRadio" type="radio" /></td>
													<td><s:property value="#st1.index + 1"/></td>
													<td>
														<s:iterator id="goodsList" value="goodsList" status="st3">
															<s:if test="%{goodsList[#st3.index].code == updSalesItemList[#st1.index].theme1}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td><s:property value="tradename"/></td>
													<td><s:property value="typeno"/></td>
													<td>
														<s:iterator id="colorList" value="colorList" status="st3">
															<s:if test="%{colorList[#st3.index].code == updSalesItemList[#st1.index].color}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td>
														<s:iterator id="unitList" value="unitList" status="st3">
															<s:if test="%{unitList[#st3.index].code == updSalesItemList[#st1.index].unit}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td>
														<s:if test='%{updSalesItemList[#st1.index].packaging == "0"}'>整箱</s:if>
														<s:elseif test='%{updSalesItemList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
														<s:elseif test='%{updSalesItemList[#st1.index].packaging == "2"}'>样品</s:elseif>
														<s:else>
															<s:property value="packaging"/>
														</s:else>
													</td>
													<td>
														<s:iterator id="makeareaList" value="makeareaList" status="st3">
															<s:if test="%{makeareaList[#st3.index].code == updSalesItemList[#st1.index].makearea}">
																<s:property value="fieldname"/>
															</s:if>
														</s:iterator>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpQuantity_<s:property value="productid"/>" onblur="calcquantity(this, '1');" maxlength="11" value="<s:property value="quantity"/>"/>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpBeforeQuantity_<s:property value="productid"/>" onblur="calcquantity(this, '2');" maxlength="11" value="<s:property value="beforequantity"/>"/>
													</td>
													<td align="right"><s:property value="outquantity"/></td>
													<td align="right"><s:property value="remainquantity"/></td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpUnitprice_<s:property value="productid"/>" onblur="calcquantity(this, '4');" maxlength="17" value="<s:property value="unitprice"/>"/>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpTaxUnitprice_<s:property value="productid"/>" onblur="calcquantity(this, '6');" maxlength="17" value="<s:property value="taxunitprice"/>"/>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpAmount_<s:property value="productid"/>" onblur="calcAmount(this, '1');" maxlength="13" value="<s:property value="amount"/>"/>
													</td>
													<td align="right">
														<input type="text" style="width: 80px;" id="tmpTaxamount_<s:property value="productid"/>" onblur="calcAmount(this, '2');" maxlength="13" value="<s:property value="taxamount"/>"/>
													</td>
													<td>
														<s:property value="item01"/>
													</td>
													<td align="right">
														<input type="text" style="width: 130px;" id="tmpRes09_<s:property value="productid"/>" onblur="calcquantity(this, '9');" maxlength="32" value="<s:property value="res09"/>"/>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<table cellpadding="10" style="margin:0 auto;">
									<tr>
										<td>
											<div class="btn1">
												<div class="btn1_left"></div>
												<div class="btn1_center">
													<input class="input80" type="button" onclick="addProduct();" value="新增" />
												</div>
												<div class="btn1_right"></div>
											</div>
										</td>
										<!--
										<td>
											<div class="btn1">
												<div class="btn1_left"></div>
												<div class="btn1_center">
													<input class="input80" type="button" onclick="updProduct();" value="更改" />
												</div>
												<div class="btn1_right"></div>
											</div>
										</td>
										-->
										<td>
											<div class="btn1">
												<div class="btn1_left"></div>
												<div class="btn1_center">
													<input class="input80" type="button" onclick="delProduct();" value="删除" />
												</div>
												<div class="btn1_right"></div>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="trade">
					<table cellpadding="10" style="margin:0 auto;">
						<tr>
							<s:if test='updSalesDto.res02 == "1"'>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="导出" onclick="exportData(1);"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<!--  
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="导出order" onclick="exportData('order');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="导出sumitube" onclick="exportData('sumitube');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							<td>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<input class="input80" type="button" value="导出sumicard" onclick="exportData('sumicard');"/>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
							-->
							</s:if>
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
										<input class="input80" type="button" value="返回" onclick="goList();"/>
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
					<div style="height:225px;"></div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
