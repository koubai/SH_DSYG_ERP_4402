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
		document.mainform.action = '../product/queryProductidSelectPage.action';
		document.mainform.submit();
	}
	
	function showUnit(id){
		var strCustomerId = document.getElementById("strCustomerId").value;
		var url = '<%=request.getContextPath()%>/sales/showProductidSelectPage.action';
		//strFlag=1采购单，strFlag=2销售单
		strCustomerId="";
		url += "?strProdoctid=" + id + "&strCustomerid=" + strCustomerId + "&strFlag=2" + "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:400px;dialogwidth:600px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//给父页面添加记录
	function addProductid() {
		var obj = null;
		var id = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				obj = list[i];
				id = list[i].value;
				//添加父页面记录
				addProductToParent(obj, id);
			}
		}
		if(obj == null) {
			alert("请选择一条记录！");
			return;
		}
		//刷新父页面斑马线
//		refreshParentBidExpertData();
		
		window.close();
	}
	
	//
	function addProductToParent(obj, id) {
		//验证该产品是否在产品列表中
//		alert(id);
		var productid = getOpener().document.getElementById("productid").value;
		if(productid == id) {
			//alert("该产品已存在！");
			return;
		}

		//添加产品信息
		var tr = obj.parentNode.parentNode;
		var tds = tr.getElementsByTagName("td");
		var inputs = tds[0].getElementsByTagName("input");
		
		//类型
		var fieldno = inputs[1].value;
		//类型名
		var fieldnoName = inputs[2].value;
		//品名
		var tradename = inputs[3].value;
		//规格
		var typeno = inputs[4].value;
		//颜色
		var color = inputs[5].value;
		//颜色名
		var colorName = inputs[6].value;
		//单位
		var unit = inputs[7].value;
		//单位名
		var unitName = inputs[8].value;
		//形式
		var packaging = inputs[9].value;
		//形式名
		var packagingName = inputs[10].value;
		//是否样品
		var sampleflag = inputs[11].value;
				
		//产地
		var makearea = inputs[14].value;
		//包装
		var item10 = inputs[15].value;
		//产地名称
		var makeareaname = inputs[16].value;
		
		getOpener().document.getElementById("productid").value = id;
		getOpener().document.getElementById("productinfo").value = tradename + " " +typeno+" "+colorName+" "+item10+" "+ makeareaname;
//		alert(tradename + " " +typeno+" "+colorName+" "+item10+" "+ makeareaname);

	}	
	
	function refreshParentBidExpertData() {
		var rows = getOpener().document.getElementById("productData").rows;
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
		
	function getSelectedID() {
		var obj = null;
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				obj = list[i];
				break;
			}
		}
		return obj;
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../product/queryProductidSelectPage.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../product/turnProductidSelectPage.action';
		document.mainform.submit();
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
	<s:hidden name="common_rate" id="common_rate"></s:hidden>
	<s:hidden name="startIndex" id="startIndex"/>
	<s:hidden name="intPageSize" id="intPageSize"/>
	<s:hidden name="strCustomerId" id="strCustomerId"/>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="searchbox">
			<div class="box1">
				<label class="pdf10">产品名：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strTradename" id="strTradename" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="box1">
				<label class="pdf10">规格：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<s:textfield name="strTypeno" id="strTypeno" cssStyle="width:120px;" maxlength="32" theme="simple"></s:textfield>
				</div>
				<div class="box1_right"></div>
			</div>
			<div class="box1">
				<label class="pdf10">颜色：</label>
				<div class="box1_left"></div>
				<div class="box1_center">
					<select name="strColor" id="color" style="width: 120px;">
						<option value="" selected="selected">请选择</option>
						<s:iterator value="colorList" id="colorList" status="st1">
							<option value="<s:property value="code"/>" <s:if test="%{colorList[#st1.index].code == strColor}">selected</s:if>><s:property value="fieldname"/></option>
						</s:iterator>
					</select>
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
						<td style="display: none;"></td>
						<td width="20"></td>
						<td width="20">序号</td>
						<td width="60">类型</td>
						<td width="60">品名</td>
						<td width="60">规格</td>
						<td width="60">颜色</td>
						<td width="60">形式</td>
						<td width="60">包装</td>
						<td width="60">产地</td>
					</tr>
					<s:iterator id="productList" value="productList" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg" onclick="checkRadioTr1(this, event, 1, 0);">
						</s:if>
						<s:else>
							<tr onclick="checkRadioTr1(this, event, 1, 0);">
						</s:else>
							<td style="display: none;">
								<input type="hidden" value="<s:property value="id"/>"/>
								<input type="hidden" value="<s:property value="fieldno"/>"/>
								<input type="hidden" value="<s:iterator id="goodsList" value="goodsList" status="st3"><s:if test="%{goodsList[#st3.index].code == productList[#st1.index].fieldno}"><s:property value="fieldname"/></s:if></s:iterator>"/>
								<input type="hidden" value="<s:property value="tradename"/>"/>
								<input type="hidden" value="<s:property value="typeno"/>"/>
								<input type="hidden" value="<s:property value="color"/>"/>
								<input type="hidden" value="<s:iterator id="colorList" value="colorList" status="st3"><s:if test="%{colorList[#st3.index].code == productList[#st1.index].color}"><s:property value="fieldname"/></s:if></s:iterator>"/>
								<input type="hidden" value="<s:property value="unit"/>"/>
								<input type="hidden" value="<s:iterator id="unitList" value="unitList" status="st3"><s:if test="%{unitList[#st3.index].code == productList[#st1.index].unit}"><s:property value="fieldname"/></s:if></s:iterator>"/>
								<input type="hidden" value="<s:property value="packaging"/>"/>
								<input type="hidden" value="<s:if test='%{productList[#st1.index].packaging == "0"}'>整箱</s:if><s:elseif test='%{productList[#st1.index].packaging == "1"}'>乱尺</s:elseif><s:elseif test='%{productList[#st1.index].packaging == "0"}'>样品</s:elseif><s:else>乱尺</s:else>"/>
								<input type="hidden" value="<s:property value="sampleflag"/>"/>
								<input type="hidden" value="<s:property value="purchaseprice"/>"/>
								<input type="hidden" value="<s:property value="salesprice"/>"/>
								<input type="hidden" value="<s:property value="makearea"/>"/>
								<input type="hidden" value="<s:property value="item10"/>"/>
								<input type="hidden" value="<s:iterator id="makeareaList" value="makeareaList" status="st3"><s:if test="%{makeareaList[#st3.index].code == productList[#st1.index].makearea}"><s:property value="fieldname"/></s:if></s:iterator>"/>
							</td>
							<!-- <td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td> -->
							<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>							
							<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
							<td>
								<s:iterator id="goodsList" value="goodsList" status="st3">
									<s:if test="%{goodsList[#st3.index].code == productList[#st1.index].fieldno}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
							<td><a href="#" onclick="showUnit('<s:property value="id"/>','<s:property value="supplierid"/>');"><s:property value="tradename"/></a></td>
							<td><s:property value="typeno"/></td>
							<td>
								<s:iterator id="colorList" value="colorList" status="st3">
									<s:if test="%{colorList[#st3.index].code == productList[#st1.index].color}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
							<td>
								<s:if test='%{productList[#st1.index].packaging == "0"}'>整箱</s:if>
								<s:elseif test='%{productList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
								<s:elseif test='%{productList[#st1.index].packaging == "2"}'>样品</s:elseif>
								<s:else>
									<s:property value="packaging"/>
								</s:else>
							</td>
							<td>
								<s:property value="item10"/>
							</td>
							<td>
								<s:iterator id="makeareaList" value="makeareaList" status="st3">
									<s:if test="%{makeareaList[#st3.index].code == productList[#st1.index].makearea}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="pages">
				<ul>
					<li style="width: 180px;">
						<s:if test="intPageSize != null && intPageSize == 20">
							显示：<input name="tmpPagesize" type="radio" value="10" onclick="changepagesize('10')"/>10 
							<input name="tmpPagesize" type="radio" value="20" checked="checked" onclick="changepagesize('20')"/>20 
							<input name="tmpPagesize" type="radio" value="30" onclick="changepagesize('30')"/>30
						</s:if>
						<s:elseif test="intPageSize != null && intPageSize == 30">
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
								<input type="button" class="input80" value="追加" onclick="addProductid();"/>
							</div>
							<div class="box1_right"></div>
						</div>
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
