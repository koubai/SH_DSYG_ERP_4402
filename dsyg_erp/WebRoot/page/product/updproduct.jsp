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
<title>产品信息编辑</title>
<script type="text/javascript">
	function upd() {
		if(checkItem()) {
			if(confirm("确定修改吗？")) {
				document.mainform.action = "../product/updProductAction.action";
				document.mainform.submit();
			}
		}
	}
	
	//验证数据格式
	function checkItem() {
		//产品类型
		var fieldno = $("#fieldno").val().trim();
		//品牌
		var brand = $("#brand").val().trim();
		//品名
		var tradename = $("#tradename").val().trim();
		//规格
		var typeno = $("#typeno").val().trim();
		//颜色
		var color = $("#color").val().trim();
		//包装
		var item01 = $("#item01").val().trim();
		
		//形式
		var list = document.getElementsByName("tmpPackaging");
		var v = "";
		for(var j = 0; j < list.length; j++) {
			if(list[j].checked) {
				v = list[j].value;
				break;
			}
		}
		$("#packaging").val(v);
		
		var packaging = $("#packaging").val().trim();
		
		//产地
		var makearea = $("#makearea").val().trim();
		//单位
		var unit = $("#unit").val().trim();
		
		//销售价
		var salesprice = $("#salesprice").val().trim();
		//采购价
		var purchaseprice = $("#purchaseprice").val().trim();
		var tempNote = $("#tempNote").val().trim();
		
		if(fieldno == "") {
			alert("请选择类型！");
			$("#fieldno").focus();
			return;
		}
		if(brand == "") {
			alert("品牌不能为空！");
			$("#brand").focus();
			return;
		}
		if(tradename == "") {
			alert("品名不能为空！");
			$("#tradename").focus();
			return;
		}
		if(typeno == "") {
			alert("规格不能为空！");
			$("#typeno").focus();
			return;
		}
		if(color == "") {
			alert("请选择颜色！");
			$("#color").focus();
			return;
		}
		if(item01 == "") {
			alert("包装不能为空！");
			$("#item01").focus();
			return;
		}
		if(packaging == "") {
			alert("请选择形式！");
			$("#tmpPackaging").focus();
			return;
		}
		if(unit == "") {
			alert("请选择单位！");
			$("#unit").focus();
			return;
		}
		if(makearea == "") {
			alert("请选择产地！");
			$("#makearea").focus();
			return;
		}
		
		if(purchaseprice == "") {
			alert("采购价格不能为空！");
			$("#purchaseprice").focus();
			return;
		}
		if(!isReal(purchaseprice)) {
			alert("采购价格必须为大于0的实数！");
			$("#purchaseprice").focus();
			return;
		}
		if(salesprice == "") {
			alert("销售价格不能为空！");
			$("#salesprice").focus();
			return;
		}
		if(!isReal(salesprice)) {
			alert("采购价格必须为大于0的实数！");
			$("#salesprice").focus();
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
	
	function goProductList() {
		window.location.href = "../product/queryProductAction.action";
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
					产品信息编辑
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="updProductDto.packaging" id="packaging"></s:hidden>
				<s:hidden name="updProductDto.note" id="note"></s:hidden>
				<div class="searchbox update" style="height:0px;">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>类型</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.fieldno" id="fieldno" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == updProductDto.fieldno}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>品牌</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.brand" id="brand" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>品名</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.tradename" id="tradename" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>规格</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.typeno" id="typeno" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>颜色</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.color" id="color" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="colorList" id="colorList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{colorList[#st1.index].code == updProductDto.color}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>包装</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.item01" id="item01" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>形式</label>
							</td>
							<td>
								<s:if test='%{updProductDto.packaging == "1"}'>
									<input type="radio" name="tmpPackaging" value="1" checked="checked"/>整箱
									<input type="radio" name="tmpPackaging" value="0"/>乱尺
								</s:if>
								<s:elseif test='%{updProductDto.packaging == "0"}'>
									<input type="radio" name="tmpPackaging" value="1"/>整箱
									<input type="radio" name="tmpPackaging" value="0" checked="checked"/>乱尺
								</s:elseif>
								<s:else>
									<input type="radio" name="tmpPackaging" value="1"/>整箱
									<input type="radio" name="tmpPackaging" value="0"/>乱尺
								</s:else>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>产地</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.makearea" id="makearea" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="makeareaList" id="makeareaList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{makeareaList[#st1.index].code == updProductDto.makearea}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>单位</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.unit" id="unit" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="unitList" id="unitList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{unitList[#st1.index].code == updProductDto.unit}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>采购价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.purchaseprice" id="purchaseprice" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>销售价</label>
							</td>
							<td colspan="3">
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.salesprice" id="salesprice" maxlength="13" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="updProductDto.note"/></textarea>
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
										<input class="input80" type="button" value="返回" onclick="goProductList();"/>
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
