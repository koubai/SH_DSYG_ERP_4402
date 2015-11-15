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
<title>产品信息详细</title>
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
			//alert("销售价格不能为空！");
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
		
		//尺寸数据验证
		/*
		if($("#item10").val() == "") {
			alert("称呼尺寸不能为空！");
			$("#item10").focus();
			return false;
		}
		if($("#item11").val() == "") {
			alert("内径不能为空！");
			$("#item11").focus();
			return false;
		}
		if($("#item12").val() == "") {
			alert("壁厚不能为空！");
			$("#item12").focus();
			return false;
		}
		if($("#item13").val() == "") {
			alert("外径不能为空！");
			$("#item13").focus();
			return false;
		}
		if($("#item14").val() == "") {
			alert("长度不能为空！");
			$("#item14").focus();
			return false;
		}
		if($("#item15").val() == "") {
			alert("请选择尺寸编辑单位！");
			$("#item15").focus();
			return false;
		}
		//*/
		
		//图片验证
		var file01Name = $("#updPicFile01").val();
		var file02Name = $("#updPicFile02").val();
		var file03Name = $("#updPicFile03").val();
		var file04Name = $("#updPdfFile").val();
		//图片格式
		if(file01Name != "") {
			var n = file01Name.substring(file01Name.lastIndexOf("."), file01Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#updPicFile01").focus();
				return false;
			}
			$("#file01Name").val(file01Name);
		}
		//图2
		if(file02Name != "") {
			var n = file02Name.substring(file02Name.lastIndexOf("."), file02Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#updPicFile02").focus();
				return false;
			}
			$("#file02Name").val(file02Name);
		}
		//图3
		if(file03Name != "") {
			var n = file03Name.substring(file03Name.lastIndexOf("."), file03Name.length).toUpperCase();
			if(n != ".JPG" && n != ".GIF" && n != ".PNG") {
				alert("图片只支持JPG、GIF和PNG格式！");
				$("#updPicFile03").focus();
				return false;
			}
			$("#file03Name").val(file03Name);
		}
		//PDT文件验证
		if(file04Name != "") {
			var n = file04Name.substring(file04Name.lastIndexOf("."), file04Name.length).toUpperCase();
			if(n != ".PDF") {
				alert("请选择正确的PDF文件！");
				$("#updPdfFile").focus();
				return false;
			}
			$("#file04Name").val(file04Name);
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
	
	function changefieldno(obj) {
		//清空选择
		$("input[name^='code01_item0']").attr("checked", false);
		$("input[name^='code02_item0']").attr("checked", false);
		var v = obj.value;
		if(v == "01") {
			$("#fieldno01").show();
			$("#fieldno02").hide();
		} else if(v == "02") {
			$("#fieldno02").show();
			$("#fieldno01").hide();
		} else {
			$("#fieldno01").hide();
			$("#fieldno02").hide();
		}
	}
	
	//删除图片
	function delPic(id1, id2, id3) {
		$("#" + id1).remove();
		$("#" + id2).remove();
		//清空图片地址
		$("#" + id3).val("");
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
					产品信息详细
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data">
				<s:hidden name="updProductDto.packaging" id="packaging"></s:hidden>
				<s:hidden name="updProductDto.note" id="note"></s:hidden>
				
				<s:hidden name="updProductDto.item01" id="item01"></s:hidden>
				<s:hidden name="updProductDto.item02" id="item02"></s:hidden>
				<s:hidden name="updProductDto.item03" id="item03"></s:hidden>
				<s:hidden name="updProductDto.item04" id="item04"></s:hidden>
				<s:hidden name="updProductDto.item05" id="item05"></s:hidden>
				<s:hidden name="updProductDto.item06" id="item06"></s:hidden>
				<s:hidden name="updProductDto.pic01" id="pic01"></s:hidden>
				<s:hidden name="updProductDto.pic02" id="pic02"></s:hidden>
				<s:hidden name="updProductDto.pic03" id="pic03"></s:hidden>
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
									<select name="updProductDto.fieldno" id="fieldno" disabled="true" style="width: 300px;" onchange="changefieldno(this);">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="goodsList" id="goodsList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == updProductDto.fieldno}">selected</s:if>><s:property value="fieldname"/></option>
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
									<s:textfield name="updProductDto.tradename" id="tradename" disabled="true" cssStyle="width:300px;" maxlength="64" theme="simple"></s:textfield>
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
									<s:textfield name="updProductDto.brand" id="brand" disabled="true" cssStyle="width:300px;" maxlength="16" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>是否显示</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.rank" id="rank" disabled="true" style="width:300px;">
										<s:if test="updProductDto.rank == 50">
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
									<s:textfield name="updProductDto.typeno" id="typeno" maxlength="32" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>颜色</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<select name="updProductDto.color" id="color" disabled="true" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="colorList" id="colorList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{colorList[#st1.index].code == updProductDto.color}">selected</s:if>><s:property value="fieldname"/></option>
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
									<s:textfield name="updProductDto.item10" id="item10" maxlength="32" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red">*</font>形式</label>
							</td>
							<td>
								<s:if test='%{updProductDto.packaging == "1"}'>
									<input type="radio" name="tmpPackaging" value="0" disabled="true" />整箱
									<input type="radio" name="tmpPackaging" value="1" checked="checked" disabled="true" />乱尺
								</s:if>
								<s:elseif test='%{updProductDto.packaging == "0"}'>
									<input type="radio" name="tmpPackaging" value="0" checked="checked" disabled="true" />整箱
									<input type="radio" name="tmpPackaging" value="1" disabled="true" />乱尺
								</s:elseif>
								<s:else>
									<input type="radio" name="tmpPackaging" value="0" disabled="true" />整箱
									<input type="radio" name="tmpPackaging" value="1" disabled="true" />乱尺
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
									<select name="updProductDto.unit" id="unit" disabled="true" style="width: 300px;">
										<option value="" selected="selected">请选择</option>
										<s:iterator value="unitList" id="unitList" status="st1">
											<option value="<s:property value="code"/>" <s:if test="%{unitList[#st1.index].code == updProductDto.unit}">selected</s:if>><s:property value="fieldname"/></option>
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
									<select name="updProductDto.makearea" id="makearea" disabled="true" style="width: 300px;">
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
								<label class="pdf10"><font color="red"></font>采购价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.purchaseprice" id="purchaseprice" maxlength="64" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10"><font color="red"></font>销售价</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.salesprice" id="salesprice" maxlength="13" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
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
									<s:textfield name="updProductDto.item09" id="item09" maxlength="32" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">住友编码</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="updProductDto.item11" id="item11" maxlength="32" disabled="true" cssStyle="width:300px;" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<s:if test='updProductDto.fieldno == "01"'>
							<tr id="fieldno01">
						</s:if>
						<s:else>
							<tr style="display: none;" id="fieldno01">
						</s:else>
							<td class="td_tittle"><label class="pdf10"><font color="red">*</font>特征编辑：</label></td>
							<td>
								<s:iterator value="featureList01" id="featureList01" status="st1">
									<dl>
										<dt><s:property value="name"/>：</dt>
										<dd>
											<s:if test='%{featureList01[#st1.index].codename == "code01_item04" && #st1.index == 3}'>
												<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' checked value='' type="radio" disabled="true" /><label>无</label>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{updProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item04" && #st1.index == 3}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item04}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:if>
													<s:else>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:else>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{updProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item01" && #st1.index == 0}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item01}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:if>
													<s:elseif test='%{updProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item02" && #st1.index == 1}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item02}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:elseif>
													<s:elseif test='%{updProductDto.fieldno == "01" && featureList01[#st1.index].codename == "code01_item03" && #st1.index == 2}'>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item03}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:elseif>
													<s:else>
														<input id="code01_0<s:property value="#st1.index + 1"/>" name='code01_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" disabled="true" /><label><s:property value="fieldname"/></label>
													</s:else>
												</s:iterator>
											</s:else>
										</dd>
									</dl>
								</s:iterator>
							</td>
						</tr>
						<s:if test='updProductDto.fieldno == "02"'>
							<tr id="fieldno02">
						</s:if>
						<s:else>
							<tr style="display: none;" id="fieldno02">
						</s:else>
							<td class="td_tittle"><label class="pdf10"><font color="red">*</font>特征编辑：</label></td>
							<td>
								<s:iterator value="featureList02" id="featureList02" status="st1">
									<dl>
										<dt><s:property value="name"/>：</dt>
										<dd>
											<s:if test='%{featureList02[#st1.index].codename == "code02_item06" && #st1.index == 5}'>
												<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' checked value='' type="radio" disabled="true" /><label>无</label>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item06" && #st1.index == 5}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item06}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:if>
													<s:else>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:else>
												</s:iterator>
											</s:if>
											<s:else>
												<s:iterator value="dictList" id="dictList" status="st2">
													<s:if test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item01" && #st1.index == 0}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item01}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:if>
													<s:elseif test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item02" && #st1.index == 1}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item02}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item03" && #st1.index == 2}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item03}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item04" && #st1.index == 3}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item04}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:elseif>
													<s:elseif test='%{updProductDto.fieldno == "02" && featureList02[#st1.index].codename == "code02_item05" && #st1.index == 4}'>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' <s:if test='%{dictList[#st2.index].code == updProductDto.item05}'>checked</s:if> value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:elseif>
													<s:else>
														<input id="code02_0<s:property value="#st1.index + 1"/>" name='code02_item0<s:property value="#st1.index + 1"/>' value='<s:property value="code"/>' type="radio" disabled="true" /><s:property value="fieldname"/>
													</s:else>
												</s:iterator>
											</s:else>
										</dd>
									</dl>
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td class="td_tittle"><span></span>图片上传：</td>
							<td>
								<input type="file" name="updPicFile01" id="updPicFile01" disabled="true" style="width: 500px;"/><br />
								<s:if test='updProductDto.pic01 != null && updProductDto.pic01 != ""'>
									<img id="tmppic01" width="300" height="300" src="<s:property value="updProductDto.imageurl"/><s:property value="updProductDto.pic01"/>" alt="" /><br />
									<a id="tmpdel1" href="javascript:void(0);" onclick="delPic('tmppic01', 'tmpdel1', 'pic01')">删除</a>
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="td_tittle">特性图片上传：</td>
							<td>
								<input type="file" name="updPicFile02" id="updPicFile02" disabled="true" style="width: 500px;"/><br />
								<s:if test='updProductDto.pic02 != null && updProductDto.pic02 != ""'>
									<img id="tmppic02" width="300" height="300" src="<s:property value="updProductDto.imageurl"/><s:property value="updProductDto.pic02"/>" alt="" /><br />
									<a id="tmpdel2" href="javascript:void(0);" onclick="delPic('tmppic02', 'tmpdel2', 'pic02')">删除</a>
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="td_tittle">尺寸图片上传：</td>
							<td>
								<input type="file" name="updPicFile03" id="updPicFile03" disabled="true" style="width: 500px;"/><br />
								<s:if test='updProductDto.pic03 != null && updProductDto.pic03 != ""'>
									<img id="tmppic03" width="300" height="300" src="<s:property value="updProductDto.imageurl"/><s:property value="updProductDto.pic03"/>" alt="" /><br />
									<a id="tmpdel3" href="javascript:void(0);" onclick="delPic('tmppic03', 'tmpdel3', 'pic03')">删除</a>
								</s:if>
							</td>
						</tr>
						<tr>
							<td class="td_tittle"><font color="red"></font>PDF上传：</td>
							<td>
								<input type="file" name="updPdfFile" id="updPdfFile" disabled="true" style="width: 500px;"/><br />
								<a target="_blank" href="<s:property value="updProductDto.pdfurl"/><s:property value="updProductDto.pdfpath"/>"><s:property value="updProductDto.pdfpath"/></a>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">备注</label>
							</td>
							<td colspan="3">
								<textarea rows="5" cols="150" disabled="true" style="width:895px;" id="tempNote"><s:property value="updProductDto.note"/></textarea>
							</td>
						</tr>
					</table>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
