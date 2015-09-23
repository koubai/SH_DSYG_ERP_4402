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
<title>Q/A明细</title>
<script type="text/javascript">
	//页面关闭响应
	window.onunload = function() {
		//刷新父页面
		getOpener().window.location = getOpener().window.location;
	};
</script>
</head>
<body scroll="no">
	<div id="containermain">
		<div class="content">
			<s:form id="mainform" name="mainform" method="POST">
				<div class="searchbox update" style="height:0px;">
					<table id="purchaseItemTable" style="display: none;">
					</table>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td class="red" align="center" colspan="4"><s:actionmessage /></td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">姓名</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.fullname" disabled="true" id="fullname" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">电话</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.tell" disabled="true" id="tell" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">地址</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.address" disabled="true" id="address" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">传真</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.fax" disabled="true" id="fax" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">邮件</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.email" disabled="true" id="email" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
							<td align="right">
								<label class="pdf10">提交IP</label>
							</td>
							<td>
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.ip" disabled="true" id="ip" cssStyle="width:300px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">标题</label>
							</td>
							<td colspan="3">
								<div class="box1_left"></div>
								<div class="box1_center">
									<s:textfield name="detailQaDto.title" disabled="true" id="title" cssStyle="width:708px;" maxlength="32" theme="simple"></s:textfield>
								</div>
								<div class="box1_right"></div>
							</td>
						</tr>
						<tr>
							<td align="right">
								<label class="pdf10">内容</label>
							</td>
							<td colspan="3">
								<table>
									<tr>
										<td>
											<s:textarea name="detailQaDto.data" disabled="true" rows="4" cssStyle="width:708px;"></s:textarea>
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
					<div style="height:225px;"></div>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>
