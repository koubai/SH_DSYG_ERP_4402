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
<title>订单未出货产品管理</title>
<script type="text/javascript">
	Date.prototype.Format = function(fmt)   
	{ 
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}

	$(function () {  
		$("#fromDate").val("2015-08-01");
		var myDate = new Date().Format("yyyy-MM-dd");
		$("#toDate").val(myDate);
	});	     	    
	
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});	
	
	//查询日期赋值
	function setQueryDate() {
		$("#strPurchasedateLow").attr("value", $("#purchaseDateLow").val());
		$("#strPurchasedateHigh").attr("value", $("#purchaseDateHigh").val());
	}

	//查询数据
	function queryList() {
		document.mainform.action = '../chart/queryUnDeliProductDataAction.action';
		document.mainform.submit();
	}
	
	function goBack() {
		window.location.href = "../chart/showProfitInfoMainChartAction.action";
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
					订单未出货产品管理
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<div class="searchbox">
					<div class="box1_left"></div>
					<div class="box1_center date_input">				
						<input type="text" name="fromDate" id="fromDate" value=#fromDate />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('fromDate'));"></a>
					</div>
					<div class="box1_right">&nbsp&nbsp-</div>
					<div class="box1_left" style="margin-left: 30px;"></div>
					<div class="box1_center date_input">				
						<input type="text" name="toDate" id="toDate" value=#toDate />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('toDate'));"></a>
					</div>
					<div class="box1_right"></div>
					&nbsp&nbsp&nbsp&nbsp<select name="fieldno" id="fieldno" style="width: 100px;" onchange="changeFieldcode(this);">
						<option value="" selected="selected">请选择</option>
						<s:iterator value="goodsList" id="goodsList" status="st1">
							<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == fieldno}">selected</s:if>><s:property value="fieldname"/></option>
						</s:iterator>
					</select>	
					<div class="btn" style="margin-right: 200px; float: right;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-3px; margin-left: -240px; color: red;">
						<s:actionmessage />
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="700"/>px;">
						<input type="hidden" id="iotype" value="<s:property value="iotype" />" />		
					
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="20">序号</td>
								<td width="100">品名</td>
								<td width="100">规格</td>
								<td width="100">订单号</td>
								<td width="40">颜色</td>
								<td width="40">形式</td>
								<td width="100">包装</td>
								<td width="40">产地</td>
								<td width="20">单位</td>
								<td width="60">数量</td>
								<td width="60">金额</td>
							</tr>
							<s:set var="sum_remainquantity" value="0"/>
							<s:set var="sum_taxamount" value="0"/>
							<s:iterator id="undeliproductList" value="undeliproductList" status="st1">
								<tr>
									<td><s:property value="#st1.index + 1"/></td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td><s:property value="theme2"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == undeliproductList[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{undeliproductList[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{undeliproductList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:elseif test='%{undeliproductList[#st1.index].packaging == "2"}'>样品</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td>
										<s:property value="item10"/>
									</td>
									<td>
										<s:iterator id="makeareaList" value="makeareaList" status="st5">
											<s:if test="%{makeareaList[#st5.index].code == undeliproductList[#st1.index].makearea}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:iterator id="unitList" value="unitList" status="st4">
											<s:if test="%{unitList[#st4.index].code == undeliproductList[#st1.index].unit}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td align="right">
										<s:property value="remainquantity"/>
										<s:set var="sum_remainquantity" value="#sum_remainquantity+remainquantity"/>
									</td>
									<td align="right"> 
										<s:property value="taxamount"/>
										<s:set var="sum_taxamount" value="#sum_taxamount+taxamount"/>
									</td>
								</tr>
							</s:iterator>
							<tr class="tittle">
								<td width="20"></td>
								<td width="120"></td>
								<td width="100"></td>
								<td width="100"></td>
								<td width="40"></td>
								<td width="40"></td>
								<td width="100"></td>
								<td width="40"></td>
								<td width="20">合计</td>
								<td width="60" align="right"><s:property value="#sum_remainquantity"/></td>
								<td width="60" align="right"><s:property value="#sum_taxamount"/></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="trade">
					<table cellpadding="10" style="margin:0 auto;">
						<tr>
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
