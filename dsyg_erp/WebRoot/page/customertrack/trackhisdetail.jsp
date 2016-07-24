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
<title>跟踪记录履历明细</title>
<script type="text/javascript">
</script>
<base target="_self"/>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:scroll;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="trackHistDtoDetail.product" id="product"></s:hidden>
	<s:hidden name="trackHistDtoDetail.productinfo" id="productinfo"></s:hidden>
	<s:hidden name="trackHistDtoDetail.note" id="note"></s:hidden>
	<s:hidden name="trackHistDtoDetail.customermail1" id="customermail1"></s:hidden>
	<s:hidden name="trackHistDtoDetail.receivedate" id="receivedate"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle" style="width:750px">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					跟踪记录履历明细
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
				<td width="150"><font color="red">*</font>客户名称</td>
				<td width="550">
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="trackHistDtoDetail.customername" id="customername" disabled="true" cssStyle="width:425px;" maxlength="64" theme="simple"></s:textfield>
						<s:hidden name="customerid" id="customerid"></s:hidden>
						<s:hidden name="customermanager" id="customermanager"></s:hidden>
						<s:hidden name="customeraddress" id="customeraddress"></s:hidden>
						<s:hidden name="customertel" id="customertel"></s:hidden>
						<s:hidden name="customerfax" id="customerfax"></s:hidden>
						<s:hidden name="customermail" id="customermail"></s:hidden>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="customerButton" class="input40" type="button" disabled="disabled" value="检索" onclick="selectCustomer();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>客户来源</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="trackHistDtoDetail.source" id="source" disabled="true" cssStyle="width:425px;" maxlength="16" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="85%" border="0">
							<tr>
								<td align="left" width="15%"><strong>联系人</strong></td>
								<td><font color="red">*</font>联系人姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="trackHistDtoDetail.customermanager1" id="customermanager1" disabled="true" cssStyle="width:250px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="trackHistDtoDetail.customertel1" id="customertel1" disabled="true" cssStyle="width:250px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="trackHistDtoDetail.mail_pr1" id="mail_pr1" disabled="true" cssStyle="width:116px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="trackHistDtoDetail.mail_suffix1" id="mail_suffix1" disabled="true" cssStyle="width:116px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="trackHistDtoDetail.customeraddress1" id="customeraddress1" disabled="true" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="trackHistDtoDetail.customerfax1" id="customerfax1" disabled="true" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>产品一览</td>
				<td>
					<div style="float:left;">
						<textarea name="" id="tmpproductinfo" disabled="disabled" cols="" rows="3" disabled="disabled" style="width:425px;"><s:property value="trackHistDtoDetail.productinfo"/></textarea>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="productButton" class="input40" disabled="disabled" type="button" value="产品" onclick="selectProduct();" />
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-top: 5px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input id="productButton" class="input40" disabled="disabled" type="button" value="清除" onclick="clearProduct();" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr><%-- 
			<tr>
				<td>客户类型</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="trackHistDtoDetail.customertype" id="customertype" style="width: 120px;" onchange="selectCustomerType();">
							<s:if test='trackHistDtoDetail.customertype == 0'>
								<option value="0" selected="selected">公司开拓</option>
								<option value="1">个人开拓</option>
							</s:if>
							<s:else>
								<option value="0">公司开拓</option>
								<option value="1" selected="selected">个人开拓</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr> --%>
			<tr>
				<td>担当者</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<%-- <s:if test='trackHistDtoDetail.customertype == 0'>
							<s:textfield name="trackHistDtoDetail.handlerid" id="handlerid" disabled="true" maxlength="32" cssStyle="width:360px;" theme="simple"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield name="trackHistDtoDetail.handlerid" id="handlerid" maxlength="32" cssStyle="width:360px;" theme="simple"></s:textfield>
						</s:else> --%>
						<input type="hidden" name="trackHistDtoDetail.handlerid" id="handlerid" value="<s:property value="trackHistDtoDetail.handlerid"/>"/>
						<s:textfield name="trackHistDtoDetail.handlername" id="handlername" disabled="true" maxlength="32" cssStyle="width:425px;" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<%-- <s:if test='trackHistDtoDetail.customertype == 0'>
								<input id="handleridButton" class="input40" type="button" value="检索" disabled="disabled" onclick="selectUser('handlerid');" />
							</s:if>
							<s:else>
								<input id="handleridButton" class="input40" type="button" value="检索" onclick="selectUser('handlerid');" />
							</s:else> --%>
							<input id="handleridButton" disabled="disabled" class="input40" type="button" value="检索" onclick="selectUser('handlername');" />
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td>接待日期</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center date_input">
						<input type="text" id="tmpReceivedate" disabled="disabled" style="width:405px;" value="<s:property value="trackHistDtoDetail.showReceivedate"/>" />
						<a class="date" href="javascript:;"></a>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>状态</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<select name="trackHistDtoDetail.status" id="status" style="width: 425px;" disabled="disabled">
							<s:if test='trackHistDtoDetail.status == 2'>
								<option value="1">请选择</option>
								<option value="2" selected="selected">成功</option>
								<option value="3">失败</option>
								<option value="4">进行中</option>
							</s:if>
							<s:elseif test='trackHistDtoDetail.status == 3'>
								<option value="1">请选择</option>
								<option value="2">成功</option>
								<option value="3" selected="selected">失败</option>
								<option value="4">进行中</option>
							</s:elseif>
							<s:elseif test='trackHistDtoDetail.status == 4'>
								<option value="1">请选择</option>
								<option value="2">成功</option>
								<option value="3">失败</option>
								<option value="4" selected="selected">进行中</option>
							</s:elseif>
							<s:else>
								<option value="1" selected="selected">请选择</option>
								<option value="2">成功</option>
								<option value="3">失败</option>
								<option value="4">进行中</option>
							</s:else>
						</select>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td>跟踪记录</td>
				<td>
					<textarea name="" id="tmpnote" disabled="disabled" cols="" rows="3" style="width:430px;"><s:property value="trackHistDtoDetail.note"/></textarea>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="修改" onclick="upd();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="关闭" onclick="closepage();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr> -->
		</table>
	</div>
</s:form>
</body>
</html>
