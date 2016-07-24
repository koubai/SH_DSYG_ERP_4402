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
<title>跟踪记录履历一览</title>
<script type="text/javascript">
	function showDetail() {
		var id = getSelectedID();
		if(id == "") {
			alert("请选择一条记录！");
			return;
		} else {
			var url = '<c:url value="/customertrack/showTrackHistDetail.action"></c:url>' + "?detailTrackHisSeq=" + id + "&date=" + new Date();
			window.showModalDialog(url, window, "dialogheight:600px;dialogwidth:1024px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
		}
	}
	
	function getSelectedID() {
		var id = "";
		var list = document.getElementsByName("radioKey");
		for(var i = 0; i < list.length; i++) {
			if(list[i].checked) {
				id = list[i].value;
				break;
			}
		}
		return id;
	}
</script>
</head>
<body style="background: url(''); overflow-x:hidden;overflow-y:hidden;">
<s:form id="mainform" name="mainform" method="POST">
	<s:hidden name="strTrackIdHist" id="strTrackIdHist"></s:hidden>
	<div id="container" style="width: 100%; height: 100%;">
		<div class="content" style="margin-top: 0px;">
			<div class="tittle">
				<div class="icons"></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center" style="width:150px;">
					跟踪记录履历一览
				</div>
				<div class="tittle_right">
				</div>
			</div>
		</div>
		<div class="data_table" style="padding:0px;">
			<div class="tab_tittle">
				<table width="100%" border="1" cellpadding="5" cellspacing="0">
				</table>
			</div>
			<div class="tab_content">
				<table class="info_tab" width="150%" border="1" cellpadding="0" cellspacing="0" style="border-top:black solid 0px; border-bottom: 0px;">
					<tr class="tittle" style="height: 50px;">
						<td width="3%"></td>
						<td width="8%">更新者</td>
						<td width="10%">更新日期</td>
						<td width="8%">客户名</td>
						<td width="8%">客户来源</td>
						<td width="7%">联系人</td>
						<td width="7%">电话</td>
						<td width="10%">产品一览</td>
						<td width="7%">担当者</td>
						<td width="7%">接待日期</td>
						<td width="5%">状态</td>
						<td width="20%">跟踪记录</td>
					</tr>
					<s:iterator id="listTrackHist" value="listTrackHist" status="st1">
						<s:if test="#st1.odd==true">
							<tr class="tr_bg" onclick="checkRadioTr(this, event);">
						</s:if>
						<s:else>
							<tr onclick="checkRadioTr(this, event);">
						</s:else>
							<td><input name="radioKey" type="radio" value="<s:property value="id"/>"/></td>
							<td><s:property value="updateuid"/></td>
							<td><s:date name="updatedate" format="yyyy/MM/dd HH:mm:ss"/></td>
							
							<td>
								<div noWrap style="text-overflow:ellipsis;overflow:hidden">
									<s:property value="customername"/>
								</div>
							</td>
							<td><s:property value="source"/></td>
							<td><s:property value="customermanager1"/></td>
							<td><s:property value="customertel1"/></td>
							<td>
								<div noWrap style="width:150px;text-overflow:ellipsis;overflow:hidden">
									<%-- <s:property value="product"/> --%>
									<s:iterator id="listProduct" value="listProduct" status="st2">
										<s:property value="tradename"/> <s:property value="typeno"/><br>
									</s:iterator>
								</div>
							</td>
							<td><s:property value="handlername"/></td>
							<td><s:property value="showReceivedate"/></td>
							<td>
								<s:if test="#listTrackHist.status==2">
									成功
								</s:if>
								<s:elseif test="#listTrackHist.status==3">
									失败
								</s:elseif>
								<s:elseif test="#listTrackHist.status==4">
									进行中
								</s:elseif>
								<s:else>
								</s:else>
							</td>
							<td>
								<div noWrap style="width:150px;text-overflow:ellipsis;overflow:hidden">
									<s:property value="note"/>
								</div>
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
								<input type="button" class="input80" value="详细" onclick="showDetail();"/>
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
