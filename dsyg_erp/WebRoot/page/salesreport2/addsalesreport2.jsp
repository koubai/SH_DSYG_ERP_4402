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
<title>SALES报告信息输入</title>
<script type="text/javascript">
	function add() {
		var id = $("#salesreportno").val().trim();
		var salesreportname = $("#salesreportname").val().trim();
		var tmpnote = $("#tmpnote").val();
		$("#note").attr("value", tmpnote);
		if(id == "") {
			//alert("SALES报告编号不能为空！");
			//$("#id").focus();
			//return;
		}
		if(salesreportname == "") {
			alert("SALES报告名不能为空！");
			$("#salesreportname").focus();
			return;
		}

		var tmpregisterdate = $("#tmpregisterdate").val().trim();
		if(tmpregisterdate == "") {
			alert("登记日期不能为空！");
			$("#tmpregisterdate").focus();
			return;
		}
		$("#registerdate").attr("value", tmpregisterdate);
		/*
		var handler = $("#handler").val();
		if(handler.trim() == "") {
			alert("负责人不能为空！");
			$("#handler").focus();
			return;
		} */
		if(tmpnote.length > 500) {
			alert("备注不能超过500个字！");
			$("#tmpnote").focus();
			return;
		}

		//图片验证
		var file01Name = $("#addReportFile01").val();
		var file02Name = $("#addReportFile02").val();
		var file03Name = $("#addReportFile03").val();
		/*
		//文件验证
		if(file01Name == "") {
			alert("请选择对应销售报告2文件！");
			$("#addReportFile01").focus();
			return false;
		}
		if(file02Name == "") {
			alert("请选择对应销售报告2文件！");
			$("#addReportFile02").focus();
			return false;
		}
		if(file03Name == "") {
			alert("请选择对应销售报告2文件！");
			$("#addReportFile03").focus();
			return false;
		} */
		
		//文件名
		$("#file01Name").val(file01Name);
		$("#file02Name").val(file02Name);
		$("#file03Name").val(file03Name);
		
		document.mainform.action = '<c:url value="/salesreport2/addSalesReport2Action.action"></c:url>';
		document.mainform.submit();
	}
	
	function golist() {
		document.mainform.action = '<c:url value="/salesreport2/querySalesReport2List.action"></c:url>';
		document.mainform.submit();
	};

	//客户
	function selectCustomer() {
		var url = "../customer/showSelectCustomerAction.action";
		url += "?date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:550px;dialogwidth:800px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}

</script>
<base target="_self"/>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:scroll;">
<s:form id="mainform" name="mainform" method="POST" enctype="multipart/form-data">
	<s:hidden name="addSalesReport2Dto.note" id="note"></s:hidden>
	<s:hidden name="addSalesReport2Dto.registerdate" id="registerdate"></s:hidden>
	<s:hidden name="file01Name" id="file01Name"></s:hidden>
	<s:hidden name="file02Name" id="file02Name"></s:hidden>
	<s:hidden name="file03Name" id="file03Name"></s:hidden>
	<s:hidden name="addSalesReport2Dto.salesreportno" id="salesreportno"></s:hidden>
	
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					SALES报告信息输入
				</div>
				<div class="tittle_right">
				</div>
			</div>
		</div>
		<div style="position:absolute; margin-left: 150px; margin-top: 10px; text-align: center; color: red;">
			<s:actionmessage />
		</div>
		<table style="margin-left: 50px; margin-top: 30px;" border="0" cellspacing="15" cellpadding="0">
			<tr>
				<td><label class="pdf10"><font color="red">*</font>报告名</label></td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addSalesReport2Dto.salesreportname" id="salesreportname" cssStyle="width:350px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><label class="pdf10" id="labCustomer"><font color="red">*</font>客户</label>	</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<input type="hidden" id="customermanager"/>
						<input type="hidden" id="customeraddress"/>
						<input type="hidden" id="customertel"/>
						<input type="hidden" id="customerfax"/>
						<input type="hidden" id="customermail"/>
						<input type="hidden" id="customerid" value="<s:property value="addSalesReport2Dto.customerid"/>"/>
						<s:textfield name="addSalesReport2Dto.customername" id="customername" cssStyle="width:350px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right" style="width:100px">
						<div class="btn">
							<div class="box1_left"></div>
							<div class="box1_center">
								<input id="btnCustomer" class="input40" type="button" value="检索" onclick="selectCustomer();" />
							</div>
							<div class="box1_right"></div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td><label class="pdf10">用途</label></td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addSalesReport2Dto.purpose" id="purpose" cssStyle="width:350px;" maxlength="64" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><label class="pdf10">负责人</label></td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="addSalesReport2Dto.handler" id="handler" cssStyle="width:350px;" maxlength="32" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td><label class="pdf10"><font color="red">*</font>登记日期</label></td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center date_input">
						<input type="text" disabled="disabled" style="width: 100px;" id="tmpregisterdate" value="<s:property value="addSalesReport2Dto.showregisterdate"/>" maxlength="10" />
						<a class="date" href="javascript:;" onclick="new Calendar().show(document.getElementById('tmpregisterdate'));"></a>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="pdf10">文件1上传</label>
				</td>
				<td colspan="3">
					<input type="file" name="addReportFile01" style="width: 500px;" id="addReportFile01"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="pdf10">文件2上传</label>
				</td>
				<td colspan="3">
					<input type="file" name="addReportFile02" style="width: 500px;" id="addReportFile02"/>
				</td>
			</tr>
			<tr>
				<td>
					<label class="pdf10">文件3上传</label>
				</td>
				<td colspan="3">
					<input type="file" name="addReportFile03" style="width: 500px;" id="addReportFile03"/>
				</td>
			</tr>
			<tr>
				<td><label class="pdf10">备注</label></td>
				<td>
					<textarea name="" id="tmpnote" cols="" rows="5" style="width:350px;"><s:property value="addSalesReport2Dto.note"/></textarea>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="追加" onclick="add();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="关闭" onclick="golist();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</s:form>
</body>
</html>
