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
<title>盘点明细信息一览</title>
<script type="text/javascript">
</script>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:hidden;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="startIndex" id="startIndex"/>
	<s:hidden name="intPageSize" id="intPageSize"/>
	<s:hidden name="strCustomerId" id="strCustomerId"/>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="data_table" style="padding:0px;">
			<div class="tab_tittle">
				<table width="100%" border="1" cellpadding="5" cellspacing="0">
				</table>
			</div>
			<div class="tab_content">
				<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
					<tr class="tittle">
						<td width="40">序号</td>
						<td width="80">盘点日期</td>
						<td width="60">品名</td>
						<td width="60">规格</td>
						<td width="60">颜色</td>
						<td width="60">形式</td>
						<td width="60">包装</td>
						<td width="100">盘点前数量</td>
						<td width="80">盘点数量</td>
						<td width="100">位置</td>
						<td width="80">盘点人</td>
					</tr>
					<s:iterator id="positionDetailList" value="positionDetailList" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg">
						</s:if>
						<s:else>
							<tr>
						</s:else>
							<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
							<td><s:property value="checkday"/></td>
							<td><s:property value="tradename"/></td>
							<td><s:property value="typeno"/></td>
							<td>
								<s:iterator id="colorList" value="colorList" status="st3">
									<s:if test="%{colorList[#st3.index].code == positionDetailList[#st1.index].color}">
										<s:property value="fieldname"/>
									</s:if>
								</s:iterator>
							</td>
							<td>
								<s:if test='%{positionDetailList[#st1.index].packaging == "0"}'>整箱</s:if>
								<s:elseif test='%{positionDetailList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
								<s:else>
									<s:property value="packaging"/>
								</s:else>
							</td>
							<td>
								<s:property value="item10"/>
							</td>
							<td>
								<s:property value="beforeamount"/>
							</td>
							<td>
								<s:property value="amount"/>
							</td>
							<td>
								<s:property value="productposition"/>
							</td>
							<td>
								<s:property value="handlername"/>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="btns" style="margin-top:40px; margin-left: 0px;">
			<table border="0" style="margin:0 auto;">
				<tr>
					<td>
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
