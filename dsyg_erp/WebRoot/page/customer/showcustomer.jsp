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
<title>客户信息详细</title>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="containermain">
		<div class="content">
			<div class="tittle">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					客户信息详细
				</div>
				<div class="tittle_right">
				</div>
			</div>
		</div>
		<s:form id="mainform" name="mainform" method="POST">
			<s:hidden name="updateCustomerDto.note" id="note"></s:hidden>
			<s:hidden name="updateCustomerDto.customermail1" id="customermail1"></s:hidden>
			<s:hidden name="updateCustomerDto.customermail2" id="customermail2"></s:hidden>
			<s:hidden name="updateCustomerDto.customermail3" id="customermail3"></s:hidden>
			<s:hidden name="updateCustomerDto.customermail4" id="customermail4"></s:hidden>
			<s:hidden name="updateCustomerDto.customermail5" id="customermail5"></s:hidden>
		<div style="position:absolute; margin-left: 150px; margin-top: 10px; text-align: center; color: red;">
			<s:actionmessage />
		</div>
		<table style="margin-left: 50px; margin-top: 30px;" border="0" cellspacing="15" cellpadding="0">
			<tr>
				<td width="150"><font color="red">*</font>客户代码</td>
				<td width="550">&nbsp;<s:property value="updateCustomerDto.id"/></td>
			</tr>
			<tr>
				<td><font color="red">*</font>客户名称</td>
				<td>
					<div class="box1_left"></div>
					<div class="box1_center">
						<s:textfield name="updateCustomerDto.customername" disabled="true" id="customername" cssStyle="width:350px;" maxlength="40" theme="simple"></s:textfield>
					</div>
					<div class="box1_right"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="80%" border="0">
							<tr>
								<td align="left" width="15%"><strong>联系人1</strong></td>
								<td><font color="red">*</font>联系人1姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customermanager1" disabled="true" id="customermanager1" cssStyle="width:300px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人1电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customertel1" disabled="true" id="customertel1" cssStyle="width:300px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人1邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_pr1" disabled="true" id="mail_pr1" cssStyle="width:141px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_suffix1" disabled="true" id="mail_suffix1" cssStyle="width:141px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><font color="red">*</font>联系人1地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customeraddress1" disabled="true" id="customeraddress1" cssStyle="width:300px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;联系人1传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customerfax1" disabled="true" id="customerfax1" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="80%" border="0">
							<tr>
								<td align="left" width="15%"><strong>联系人2</strong></td>
								<td>联系人2姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customermanager2" disabled="true" id="customermanager2" cssStyle="width:300px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>联系人2电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customertel2" disabled="true" id="customertel2" cssStyle="width:300px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>联系人2邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_pr2" disabled="true" id="mail_pr2" cssStyle="width:141px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_suffix2" disabled="true" id="mail_suffix2" cssStyle="width:141px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>联系人2地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customeraddress2" disabled="true" id="customeraddress2" cssStyle="width:300px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>联系人2传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customerfax2" disabled="true" id="customerfax2" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="80%" border="0">
							<tr>
								<td align="left" width="15%"><strong>收货人3</strong></td>
								<td>收货人3姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customermanager3" disabled="true" id="customermanager3" cssStyle="width:300px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>收货人3电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customertel3" disabled="true" id="customertel3" cssStyle="width:300px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>收货人3邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_pr3" disabled="true" id="mail_pr3" cssStyle="width:141px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_suffix3" disabled="true" id="mail_suffix3" cssStyle="width:141px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>收货人3地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customeraddress3" disabled="true" id="customeraddress3" cssStyle="width:300px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>收货人3传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customerfax3" disabled="true" id="customerfax3" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="80%" border="0">
							<tr>
								<td align="left" width="15%"><strong>发票收件人4</strong></td>
								<td>发票收件人4姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customermanager4" disabled="true" id="customermanager4" cssStyle="width:300px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>发票收件人4电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customertel4" disabled="true" id="customertel4" cssStyle="width:300px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>发票收件人4邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_pr4" disabled="true" id="mail_pr4" cssStyle="width:141px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_suffix4" disabled="true" id="mail_suffix4" cssStyle="width:141px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>发票收件人4地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customeraddress4" disabled="true" id="customeraddress4" cssStyle="width:300px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>发票收件人4传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customerfax4" disabled="true" id="customerfax4" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="trade">
						<table class="trade_tab" width="80%" border="0">
							<tr>
								<td align="left" width="15%"><strong>其他5</strong></td>
								<td>其他5姓名</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customermanager5" disabled="true" id="customermanager5" cssStyle="width:300px;" maxlength="24" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>其他5电话</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customertel5" disabled="true" id="customertel5" cssStyle="width:300px;" maxlength="30" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>其他5邮箱</td>
								<td>
									<div class="box1">
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_pr5" disabled="true" id="mail_pr5" cssStyle="width:141px;" maxlength="29" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
										<label>@</label>
										<div class="box1_left"></div>
										<div class="box1_center">
											<s:textfield name="updateCustomerDto.mail_suffix5" disabled="true" id="mail_suffix5" cssStyle="width:141px;" maxlength="30" theme="simple"></s:textfield>
										</div>
										<div class="box1_right"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>其他5地址</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customeraddress5" disabled="true" id="customeraddress5" cssStyle="width:300px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>其他5传真</td>
								<td>
									<div class="box1_left"></div>
									<div class="box1_center">
										<s:textfield name="updateCustomerDto.customerfax5" disabled="true" id="customerfax5" cssStyle="width:250px;" maxlength="40" theme="simple"></s:textfield>
									</div>
									<div class="box1_right"></div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>备注</td>
				<td>
					<textarea name="" disabled="true" id="tmpnote" cols="" rows="5" style="width:350px;"><s:property value="updateCustomerDto.note"/></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<div class="btn">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input class="input80" type="button" value="关闭" onclick="window.close();"/>
						</div>
						<div class="box1_right"></div>
					</div>
				</td>
			</tr>
		</table>
	</s:form>
	</div>
</body>
</html>
