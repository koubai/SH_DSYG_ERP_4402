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
<title>未税销售产品信息输入</title>
<script type="text/javascript">
	function add() {
		if(checkItem()) {
			if(confirm("确定追加吗？")) {
				document.mainform.action = "../untaxinfo/addUntaxInfoAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//计算销售数量及金额
	function calcquantity(obj, type) {
		if(type == "1") {
			//是否是数字check
			if(!isReal(obj.value)) {
				alert("销售数量必须是数字！");
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
		} else {
			//是否实数check
			if(!isReal(obj.value)) {
				alert("销售金额（未税）格式不正确！");
				obj.focus();
				checkflag = false;
				return;
			}
		}

		//销售货物数量
		var salesQuantity = $("#tmpQuantity").val().trim();			
		if(salesQuantity == "") {
			salesQuantity = 0;
		} else {
			salesQuantity = parseFloat(salesQuantity).toFixed(2);
		}
	
		//单价
		var price = $("#tmpUnitprice").val().trim();
		if(price == "") {
			price = 0;
		} else {
			price = parseFloat(price).toFixed(6);			
		}
		
		//销售金额
		var amount = parseFloat(price) * parseFloat(salesQuantity);
		$("#tmpAmount").val(amount.toFixed(2));
		if(amount == "") {
			amount = 0;
		} else {
			amount = parseFloat(amount).toFixed(2);			
		}
		$("#quantity").val(salesQuantity);
		$("#unitprice").val(price);
		$("#amount").val(amount);
	}
	
	//验证数据格式
	function checkItem() {
		//产品ID
		var productid = $("#productid").val().trim();
		//品名
		var tradename = $("#tradename").val().trim();
		
		//客户ID
		var customerid = $("#customerid").val().trim();
		//客户名
		var customername = $("#customername").val().trim();
		
		//供应商ID
		var supplierid = $("#supplierid").val().trim();
		//供应商名
		var suppliername = $("#suppliername").val().trim();
		
		var tmpQuantity = $("#tmpQuantity").val().trim();
		var tmpUnitprice = $("#tmpUnitprice").val().trim();
		var tmpAmount = $("#tmpAmount").val().trim();
		var tempNote = $("#tempNote").val().trim();
		var tempNote2 = $("#tempNote2").val().trim();
		
		if(productid == "") {
			alert("请选择产品！");
			$("#tradename").focus();
			return;
		}
		if(tradename == "") {
			alert("请选择产品！");
			$("#tradename").focus();
			return;
		}
		
/*		var list = document.getElementsByName("tmpType");
		var type = "";
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				type = list[i].value;
				break;
			}
		}
		if(type == "") {
			alert("请选择类型！");
			return;
		}
		
		if(type == "1") {
			if(supplierid == "") {
				alert("请选择供应商！");
				$("#supplierid").focus();
				return;
			}
			if(suppliername == "") {
				alert("请选择供应商！");
				$("#suppliername").focus();
				return;
			}
			$("#customertype").val(type);
			$("#sampleCustomerid").val(supplierid);
			$("#sampleCustomername").val(suppliername);
		} else if(type == "2") {
			if(customerid == "") {
				alert("请选择客户！");
				$("#customerid").focus();
				return;
			}
			if(customername == "") {
				alert("请选择客户！");
				$("#customername").focus();
				return;
			}
			$("#customertype").val(type);
			$("#sampleCustomerid").val(customerid);
			$("#sampleCustomername").val(customername);
		}
*/		
		$("#customertype").val("2");
		
		if(tmpQuantity == "") {
			alert("数量不能为空！");
			$("#tmpQuantity").focus();
			return;
		}
		if(!isReal(tmpQuantity)) {
			alert("数量格式不正确！");
			$("#tmpQuantity").focus();
			return;
		}
		if(!isReal(tmpUnitprice)) {
			alert("未税单价格式不正确！");
			$("#tmpUnitprice").focus();
			return;
		}
		if(!isReal(tmpAmount)) {
			alert("未税金额格式不正确！");
			$("#tmpAmount").focus();
			return;
		}
/*		if(tempNote ==  "") {
			alert("备注不能为空！");
			$("#tempNote").focus();
			return false;
		}
		*/
		if(tempNote.length > 2500) {
			alert("备注不能超过2500个字！");
			$("#tempNote").focus();
			return false;
		}
		if(tempNote2.length > 250) {
			alert("备注2不能超过250个字！");
			$("#tempNote2").focus();
			return false;
		}
		$("#untaxinfoCustomerid").val(customerid);
		$("#untaxinfoCustomername").val(customername);
		$("#quantity").val(tmpQuantity);
		$("#showQuantity").val(tmpQuantity);
		$("#unitprice").val(tmpUnitprice);
		$("#amount").val(tmpAmount);
		//备注
		$("#note").val($("#tempNote").val());
		$("#note2").val($("#tempNote2").val());
		return true;
	}
	
	function selectProduct() {
		var url = '<%=request.getContextPath()%>/product/showSampleProductSelectPage.action';
		url += "?strFieldno=&date=" + new Date();
		
		//window.open(url);
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//客户
	function selectCustomer() {
		var url = "../customer/showSelectCustomerAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//供应商
	function selectSupplier() {
		var url = "../supplier/showSelectSupplierAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
/*	function selectType(obj) {
		if(obj.checked) {
			if(obj.value == "1") {
				//供应商
				$("#labSupplier").show();
				$("#suppliername").show();
				$("#btnSupplier").show();
				$("#labCustomer").hide();
				$("#customername").hide();
				$("#btnCustomer").hide();
				
				$("#customername").val("");
				$("#customerid").val("");
			} else if(obj.value == "2") {
				//客户
				$("#labSupplier").hide();
				$("#suppliername").hide();
				$("#btnSupplier").hide();
				$("#labCustomer").show();
				$("#customername").show();
				$("#btnCustomer").show();
				
				$("#suppliername").val("");
				$("#supplierid").val("");
			}
		}
	}
*/	
	function goBack() {
		window.location.href = "../untaxinfo/queryUntaxInfoAction.action";
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
					未税销售产品信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data">
				<s:hidden name="addUntaxInfoDto.note" id="note"></s:hidden>
				<s:hidden name="addUntaxInfoDto.productid" id="productid"></s:hidden>				
				<s:hidden name="addUntaxInfoDto.quantity" id="quantity"></s:hidden>
				<s:hidden name="addUntaxInfoDto.showQuantity" id="showQuantity"></s:hidden>
				<s:hidden name="addUntaxInfoDto.color" id="color"></s:hidden>
				<s:hidden name="addUntaxInfoDto.packaging" id="packaging"></s:hidden>
				<s:hidden name="addUntaxInfoDto.unit" id="unit"></s:hidden>
				<s:hidden name="addUntaxInfoDto.customertype" id="customertype"></s:hidden>
				<s:hidden name="addUntaxInfoDto.customerid" id="untaxinfoCustomerid"></s:hidden>
				<s:hidden name="addUntaxInfoDto.customername" id="untaxinfoCustomername"></s:hidden>
				<s:hidden name="addUntaxInfoDto.unitprice" id="unitprice"></s:hidden>
				<s:hidden name="addUntaxInfoDto.amount" id="amount"></s:hidden>
				<s:hidden name="addUntaxInfoDto.note2" id="note2"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>品名</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addUntaxInfoDto.tradename" disabled="true" id="tradename" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
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
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>规格</label>
							</td>
							<td>
								<s:textfield name="addUntaxInfoDto.typeno" disabled="true" id="typeno" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>颜色</label>
							</td>
							<td>
								<s:textfield name="show_color" disabled="true" id="show_color" cssStyle="width:300px;" maxlength="64" theme="simple">
  								<s:iterator id="colorList" value="colorList" status="st3">
									<s:if test="%{colorList[#st3.index].code == addUntaxInfoDto.color}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
								</s:textfield>
 							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>形式</label>
							</td>
							<td>
								<s:textfield name="show_packaging" disabled="true" id="show_packaging" cssStyle="width:300px;" maxlength="64" theme="simple">
	  	 							<s:if test='%{addUntaxInfoDto.packaging == "0"}'><s:property value="整箱"/>
									</s:if>
									<s:elseif test='%{addUntaxInfoDto.packaging == "1"}'><s:property value="乱尺"/>
									</s:elseif>
									<s:elseif test='%{addUntaxInfoDto.packaging == "2"}'><s:property value="样品"/>
									</s:elseif>
									<s:else>
										<s:property value="addUntaxInfoDto.packaging"/>
									</s:else>
								</s:textfield>
 							</td>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>包装</label>
							</td>
							<td>
								<s:textfield name="addUntaxInfoDto.item10" disabled="true" id="item10" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>单位</label>
							</td>
							<td>
								<s:textfield name="fieldname" disabled="true" id="show_unit" cssStyle="width:100px;" maxlength="64" theme="simple">
								<s:iterator id="unitList" value="unitList" status="st4">
									<s:if test="%{unitList[#st4.index].code == addUntaxInfoDto.unit}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>			
								</s:textfield>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp;</font>产地</label>
							</td>
							<td>
								<s:textfield name="addUntaxInfoDto.makearea" disabled="true" id="makearea" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
							</td>
						</tr>
						<tr>
							<td align="right">
								<s:if test='addUntaxInfoDto.customertype == "1"'>
									<label class="pdf10" id="labCustomer" style="display: none;"><font color="red">*</font>客户</label>
									<label class="pdf10" id="labSupplier"><font color="red">*</font>供应商</label>
								</s:if>
								<s:elseif test='addUntaxInfoDto.customertype == "2"'>
									<label class="pdf10" id="labCustomer"><font color="red">*</font>客户</label>
									<label class="pdf10" id="labSupplier" style="display: none;"><font color="red">*</font>供应商</label>
								</s:elseif>
								<s:else>
									<label class="pdf10" id="labCustomer"><font color="red">*</font>客户</label>
									<label class="pdf10" id="labSupplier" style="display: none;"><font color="red">*</font>供应商</label>
								</s:else>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="hidden" id="customermanager"/>
									<input type="hidden" id="customeraddress"/>
									<input type="hidden" id="customertel"/>
									<input type="hidden" id="customerfax"/>
									<input type="hidden" id="customermail"/>
									<input type="hidden" id="customerid" value="<s:property value="addUntaxInfoDto.customerid"/>"/>
									
									<input type="hidden" id="suppliermanager"/>
									<input type="hidden" id="suppliermanageraddr"/>
									<input type="hidden" id="suppliertel"/>
									<input type="hidden" id="supplierfax"/>
									<input type="hidden" id="suppliermail"/>
									<input type="hidden" id="supplierid" value="<s:property value="addUntaxInfoDto.customerid"/>"/>
									
									<s:if test='addUntaxInfoDto.customertype == "1"'>
										<input type="text" id="customername" style="width:300px; display: none;" maxlength="64" value=""/>
										<input type="text" id="suppliername" style="width:300px;" maxlength="64" value="<s:property value="addUntaxInfoDto.customername"/>"/>
									</s:if>
									<s:elseif test='addUntaxInfoDto.customertype == "2"'>
										<input type="text" id="customername" style="width:300px;" maxlength="64" value="<s:property value="addUntaxInfoDto.customername"/>"/>
										<input type="text" id="suppliername" style="width:300px; display: none;" maxlength="64" value=""/>
									</s:elseif>
									<s:else>
										<input type="text" id="customername" style="width:300px;" maxlength="64" value="<s:property value="addUntaxInfoDto.customername"/>"/>
										<input type="text" id="suppliername" style="width:300px; display: none;" maxlength="64" value=""/>
									</s:else>
								</div>
								<div class="box1_right"></div>
								<div class="btn">
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:if test='addUntaxInfoDto.customertype == "1"'>
											<input id="btnCustomer" class="input40" style="display: none;" type="button" value="检索" onclick="selectCustomer();" />
											<input id="btnSupplier" class="input40" type="button" value="检索" onclick="selectSupplier();" />
										</s:if>
										<s:elseif test='addUntaxInfoDto.customertype == "2"'>
											<input id="btnCustomer" class="input40" type="button" value="检索" onclick="selectCustomer();" />
											<input id="btnSupplier" class="input40" style="display: none;" type="button" value="检索" onclick="selectSupplier();" />
										</s:elseif>
										<s:else>
											<input id="btnCustomer" class="input40" type="button" value="检索" onclick="selectCustomer();" />
											<input id="btnSupplier" class="input40" style="display: none;" type="button" value="检索" onclick="selectSupplier();" />
										</s:else>
									</div>
									<div class="box1_right"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>数量</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpQuantity" style="width:300px;" maxlength="64" onblur="calcquantity(this, '1');" value="<s:property value="addUntaxInfoDto.showQuantity"/>"/>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>未税单价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpUnitprice" style="width:300px;" maxlength="64" onblur="calcquantity(this, '4');" value="<s:property value="addUntaxInfoDto.unitprice"/>"/>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>未税金额</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<input type="text" id="tmpAmount" style="width:300px;" maxlength="64" value="<s:property value="addUntaxInfoDto.amount"/>"/>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="addUntaxInfoDto.note"/></textarea>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">&nbsp</font>备注2</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote2"><s:property value="addUntaxInfoDto.note2"/></textarea>
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
										<input class="input80" type="button" value="追加" onclick="add();"/>
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
						</tr>
					</table>
					<div style="height:225px;"></div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
