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
<title>产品信息输入</title>
<script type="text/javascript">
	function add() {
		if(checkItem()) {
			if(confirm("确定追加吗？")) {
				document.mainform.action = "../product/addProductAction.action";
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
		var item10 = $("#item10").val().trim();
		//住友编码
		var item11 = $("#item11").val().trim();
		
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
		//由于柔软扁平电缆是没有颜色的.
		if(color == "" && fieldno!='03') {
			alert("请选择颜色！");
			$("#color").focus();
			return;
		}
		if(item10 == "") {
			alert("包装不能为空！");
			$("#item10").focus();
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
			//alert("采购价格不能为空！");
			//$("#purchaseprice").focus();
			//return;
		}
		if(purchaseprice != "" && !isReal(purchaseprice)) {
			alert("采购价格必须为大于0的实数！");
			$("#purchaseprice").focus();
			return;
		}
		if(salesprice == "") {
			///alert("销售价格不能为空！");
			//$("#salesprice").focus();
			//return;
		}
		if(salesprice != "" && !isReal(salesprice)) {
			alert("销售价格必须为大于0的实数！");
			$("#salesprice").focus();
			return;
		}
/*		if(item11 == "") {
			alert("住友代码不能为空！");
			$("#item11").focus();
			return;
		}
*/		
		//特征编辑值设定
		$("#item01").val("");
		$("#item02").val("");
		$("#item03").val("");
		$("#item04").val("");
		$("#item05").val("");
		$("#item06").val("");
		if(fieldno == "01") {
			for(var i = 1; i <= 4; i++) {
				var name = "code01_item0" + i;
				//var v = $("input[name='" + name + "'][@checked]").val();
				var list = document.getElementsByName(name);
				for(var j = 0; j < list.length; j++) {
					if(list[j].checked) {
						var v = list[j].value;
						$("#" + "item0" + i).val(v);
						break;
					}
				}
			}
			if($("#item01").val() == "") {
				alert("请选择耐温！");
				$("#code01_01").focus();
				return false;
			}
			if($("#item02").val() == "") {
				alert("请选择耐压！");
				$("#code01_02").focus();
				return false;
			}
			if($("#item03").val() == "") {
				alert("请选择材质！");
				$("#code01_03").focus();
				return false;
			}
			//环保可以为空
			/*
			if($("#item04").val() == "") {
				alert("请选择环保！");
				$("#code01_04").focus();
				return false;
			}//*/
		} else if(fieldno == "02") {
			for(var i = 1; i <= 6; i++) {
				var name = "code02_item0" + i;
				var list = document.getElementsByName(name);
				for(var j = 0; j < list.length; j++) {
					if(list[j].checked) {
						var v = list[j].value;
						$("#" + "item0" + i).val(v);
						break;
					}
				}
			}
			if($("#item01").val() == "") {
				alert("请选择耐温！");
				$("#code02_01").focus();
				return false;
			}
			if($("#item02").val() == "") {
				alert("请选择耐压！");
				$("#code02_02").focus();
				return false;
			}
			if($("#item03").val() == "") {
				alert("请选择绝缘！");
				$("#code02_03").focus();
				return false;
			}
			if($("#item04").val() == "") {
				alert("请选择收缩比！");
				$("#code02_04").focus();
				return false;
			}
			if($("#item05").val() == "") {
				alert("请选择材质！");
				$("#code02_05").focus();
				return false;
			}
			//环保可以为空
			/*
			if($("#item06").val() == "") {
				alert("请选择环保！");
				$("#code02_06").focus();
				return false;
			}//*/
		}
		
		//图片验证
		var file01Name = $("#addPicFile01").val();
		var file02Name = $("#addPicFile02").val();
		var file03Name = $("#addPicFile03").val();
		var file04Name = $("#addPdfFile").val();
		//图1
		var n = "";
		if(file01Name != "") {
			n = file01Name.substring(file01Name.lastIndexOf("."), file01Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#addPicFile01").focus();
				return false;
			}
		}
		
		//图2
		if(file02Name != "") {
			n = file02Name.substring(file02Name.lastIndexOf("."), file02Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#addPicFile02").focus();
				return false;
			}
		}
		//图3
		if(file03Name != "") {
			n = file03Name.substring(file03Name.lastIndexOf("."), file03Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#addPicFile03").focus();
				return false;
			}
		}
		//PDT文件验证
		if(file04Name == "") {
			//alert("请选择对应PDF文件！");
			//$("#addPdfFile").focus();
			//return false;
		}
		if(file04Name != "") {
			n = file04Name.substring(file04Name.lastIndexOf("."), file04Name.length).toUpperCase();
			if(n != ".PDF") {
				alert("请选择正确的PDF文件！");
				$("#addPdfFile").focus();
				return false;
			}
		}
		
		//文件名
		$("#file01Name").val(file01Name);
		$("#file02Name").val(file02Name);
		$("#file03Name").val(file03Name);
		$("#file04Name").val(file04Name);
		
		if(tempNote.length > 250) {
			alert("备注不能超过250个字！");
			$("#tempNote").focus();
			return false;
		}
		//备注
		$("#note").val($("#tempNote").val());
		return true;
	}
	
	function changeFieldcode(obj) {
		//清空选择
		$("input[name^='code01_item0']").attr("checked", false);
		$("input[name^='code02_item0']").attr("checked", false);
		var v = obj.value;
		if(v == "01") {
			$("#fieldcode01").show();
			$("#fieldcode02").hide();
		} else if(v == "02") {
			$("#fieldcode02").show();
			$("#fieldcode01").hide();
		} else {
			$("#fieldcode01").hide();
			$("#fieldcode02").hide();
		}
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
					产品信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data">
				<s:hidden name="addProductDto.packaging" id="packaging"></s:hidden>
				<s:hidden name="addProductDto.note" id="note"></s:hidden>
				<s:hidden name="strTradename" id="strTradename"></s:hidden>
				<s:hidden name="strTypeno" id="strTypeno"></s:hidden>
				<s:hidden name="strColor" id="strColor"></s:hidden>
				
				<s:hidden name="addProductDto.item01" id="item01"></s:hidden>
				<s:hidden name="addProductDto.item02" id="item02"></s:hidden>
				<s:hidden name="addProductDto.item03" id="item03"></s:hidden>
				<s:hidden name="addProductDto.item04" id="item04"></s:hidden>
				<s:hidden name="addProductDto.item05" id="item05"></s:hidden>
				<s:hidden name="addProductDto.item06" id="item06"></s:hidden>
				<s:hidden name="file01Name" id="file01Name"></s:hidden>
				<s:hidden name="file02Name" id="file02Name"></s:hidden>
				<s:hidden name="file03Name" id="file03Name"></s:hidden>
				<s:hidden name="file04Name" id="file04Name"></s:hidden>
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
									<select name="addProductDto.fieldno" id="fieldno" style="width: 300px;" onchange="changeFieldcode(this);">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == addProductDto.fieldno}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>品名</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.tradename" id="tradename" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>品牌</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.brand" id="brand" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>是否显示</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addProductDto.rank" id="rank" style="width:300px;">
										<s:if test="addProductDto.rank == 50">
											<option value="50" selected="selected">显示</option>
											<option value="70">不显示</option>
										</s:if>
										<s:else>
											<option value="50">显示</option>
											<option value="70" selected="selected">不显示</option>
										</s:else>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>规格</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.typeno" id="typeno" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>颜色</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addProductDto.color" id="color" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="colorList" id="colorList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{colorList[#st1.index].code == addProductDto.color}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>包装</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.item10" id="item10" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>形式</label>
							</td>
							<td>
								<s:if test='%{addProductDto.packaging == "1"}'>
									<input type="radio" name="tmpPackaging" value="0"/>整箱
									<input type="radio" name="tmpPackaging" value="1" checked="checked"/>乱尺
								</s:if>
								<s:elseif test='%{addProductDto.packaging == "0"}'>
									<input type="radio" name="tmpPackaging" value="0" checked="checked"/>整箱
									<input type="radio" name="tmpPackaging" value="1"/>乱尺
								</s:elseif>
								<s:else>
									<input type="radio" name="tmpPackaging" value="0"/>整箱
									<input type="radio" name="tmpPackaging" value="1"/>乱尺
								</s:else>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>单位</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addProductDto.unit" id="unit" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="unitList" id="unitList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{unitList[#st1.index].code == addProductDto.unit}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>产地</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="addProductDto.makearea" id="makearea" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="makeareaList" id="makeareaList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{makeareaList[#st1.index].code == addProductDto.makearea}">selected</s:if>><s:property value="fieldname"/></option>
										</s:iterator>
									</select>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>采购价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.purchaseprice" id="purchaseprice" maxlength="64" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red"></font>销售价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.salesprice" id="salesprice" maxlength="13" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">UL型号/编号</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.item09" id="item09" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">住友编码</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="addProductDto.item11" id="item11" maxlength="32" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<s:if test='addProductDto.fieldno == "01"'>
							<tr id="fieldcode01">
						</s:if>
						<s:else>
							<tr style="display: none;" id="fieldcode01">
						</s:else>
							<td class="td_tittle"><label class="pdf10"><font color="red">*</font>特征编辑：</label></td>
							<td>
								<s:iterator value="featureList01" id="featureList01" status="st1">
									<dl>
										<dt><s:property value="name"/>：</dt>
										<dd>
											<s:if test='%{featureList01[#st1.index].codename == "code01_item04" && #st1.index == 3}'>
												<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' checked value='' type="radio" /><label>无</label>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{addProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item04" && #st1.index == 3}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item04}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:if>
													<s:else>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:else>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{addProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item01" && #st1.index == 0}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item01}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:if>
													<s:elseif test='%{addProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item02" && #st1.index == 1}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item02}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:elseif>
													<s:elseif test='%{addProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item03" && #st1.index == 2}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item03}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:elseif>
													<s:else>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" /><label><s:property value="fieldname"/></label>
													</s:else>
												</s:iterator>
											</s:else>
										</dd>
									</dl>
								</s:iterator>
							</td>
						</tr>
						<s:if test='addProductDto.fieldno == "02"'>
							<tr id="fieldcode02">
						</s:if>
						<s:else>
							<tr style="display: none;" id="fieldcode02">
						</s:else>
							<td class="td_tittle"><label class="pdf10"><font color="red">*</font>特征编辑：</label></td>
							<td>
								<s:iterator value="featureList02" id="featureList02" status="st1">
									<dl>
										<dt><s:property value="name"/>：</dt>
										<dd>
											<s:if test='%{featureList02[#st1.index].codename == "code02_item06" && #st1.index == 5}'>
												<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' checked value='' type="radio" /><label>无</label>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item06" && #st1.index == 5}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item06}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:if>
													<s:else>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:else>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item01" && #st1.index == 0}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item01}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:if>
													<s:elseif test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item02" && #st1.index == 1}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item02}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item03" && #st1.index == 2}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item03}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item04" && #st1.index == 3}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item04}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{addProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item05" && #st1.index == 4}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == addProductDto.item05}'>checked</s:if> value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:elseif>
													<s:else>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" /><s:property value="fieldname"/>
													</s:else>
												</s:iterator>
											</s:else>
										</dd>
									</dl>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">图片上传</label>
							</td>
							<td colspan="3">
								<input type="file" name="addPicFile01" style="width: 500px;" id="addPicFile01"/><br />
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">特性图片上传</label>
							</td>
							<td colspan="3">
								<input type="file" name="addPicFile02" style="width: 500px;" id="addPicFile02"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">尺寸图片上传</label>
							</td>
							<td colspan="3">
								<input type="file" name="addPicFile03" style="width: 500px;" id="addPicFile03"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10"><font color="red"></font>PDF上传</label>
							</td>
							<td colspan="3">
								<input type="file" name="addPdfFile" style="width: 500px;" id="addPdfFile"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" style="width:895px;" id="tempNote"><s:property value="addProductDto.note"/></textarea>
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
