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
<title>仓位确认</title>
<script type="text/javascript">
	$(document).ready(function(){
		var h = screen.availHeight; 
		$("#container").height(h - 20);
	});
	
	//查询数据
	function queryList() {
		document.mainform.action = '../warehouse/queryProductOkAction.action';
		document.mainform.submit();
	}
	
	function showDetail(id) {
		var url = '<%=request.getContextPath()%>/warehouse/showProductBookPage.action';
		url += "?strProdoctid=" + id + "&date=" + new Date();
		window.showModalDialog(url, window, "dialogheight:400px;dialogwidth:600px;center:yes;status:0;resizable=no;Minimize=no;Maximize=no");
	}
	
	//修改pagesize
	function changepagesize(pagesize) {
		$("#intPageSize").attr("value", pagesize);
		$("#startIndex").attr("value", "0");
		document.mainform.action = '../warehouse/queryProductOkAction.action';
		document.mainform.submit();
	}
	
	//翻页
	function changePage(pageNum) {
		$("#startIndex").attr("value", pageNum);
		document.mainform.action = '../warehouse/turnProductOkAction.action';
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
<body>
	<div id="containermain">
		<div class="content">
			<jsp:include page="../info.jsp" flush="true" />
			<div class="tittle">
				<div class="icons"><a class="home" href="#" onclick="goHome();">返回首页</a><a class="quit" href="#" onclick="logout();">退出</a></div>
				<div class="tittle_left">
				</div>
				<div class="tittle_center">
					仓位确认
				</div>
				<div class="tittle_right">
				</div>
			</div>
			<s:form id="mainform" name="mainform" method="POST">
				<s:hidden name="startIndex" id="startIndex"/>
				<s:hidden name="intPageSize" id="intPageSize"/>
				<div class="searchbox">
					<div class="box1">
						<label class="pdf10">类型</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<select name="strTheme" id="strTheme" style="width: 150px;">
								<option value="" selected="selected">请选择</option>
								<s:iterator value="goodsList" id="goodsList" status="st1">
									<option value="<s:property value="code"/>" <s:if test="%{goodsList[#st1.index].code == strTheme}">selected</s:if>><s:property value="fieldname"/></option>
								</s:iterator>
							</select>
						</div>
						<div class="box1_right"></div>
						
						<label class="pdf10">品名</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strTradename" id="strTradename" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
						
						<label class="pdf10">规格</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strTypeno" id="strTypeno" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
						
						<label class="pdf10">颜色</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<select name="strColor" id="strColor" style="width: 150px;">
								<option value="" selected="selected">请选择</option>
								<s:iterator value="colorList" id="colorList" status="st1">
									<option value="<s:property value="code"/>" <s:if test="%{colorList[#st1.index].code == strColor}">selected</s:if>><s:property value="fieldname"/></option>
								</s:iterator>
							</select>
						</div>
						<div class="box1_right"></div>
						
						<label class="pdf10">仓库</label>
						<div class="box1_left"></div>
						<div class="box1_center">
							<s:textfield name="strWarehousename" id="strWarehousename" cssStyle="width:150px;" maxlength="32" theme="simple"></s:textfield>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="btn" style="margin-left: 60px;">
						<div class="box1_left"></div>
						<div class="box1_center">
							<input type="button" class="input40" value="检索" onclick="queryList();"/>
						</div>
						<div class="box1_right"></div>
					</div>
					<div class="box1" style="margin-top:-3px; margin-left: -240px; color: red;">
						<s:actionmessage />
					</div>
					<div class="icons thums">
					</div>
				</div>
				<div class="data_table" style="padding:0px;">
					<div class="tab_tittle">
						<table width="100%" border="1" cellpadding="5" cellspacing="0">
						</table>
					</div>
					<div class="tab_content" style="height: <s:property value="intPageSize * 35"/>px;">
						<table class="info_tab" width="100%" border="1" cellpadding="5" cellspacing="0">
							<tr class="tittle">
								<td width="40">序号</td>
								<td width="20">仓库</td>
								<td width="80">类型</td>
								<td width="100">品名</td>
								<td width="120">规格</td>
								<td width="60">颜色</td>
								<td width=40">形式</td>
								<td width="100">包装</td>
								<td width="60">产地</td>
								<td width="120">供应商/客户</td>
								<td width="80">出入库存量</td>
							</tr>
							<s:iterator id="warehouseOkList" value="warehouseOkList" status="st1">
								<s:if test="#st1.odd==true">
									<tr class="tr_bg">
								</s:if>
								<s:else>
									<tr>
								</s:else>
									<td><s:property value="page.pageSize * (page.nextIndex - 1) + #st1.index + 1"/></td>
									<td><s:property value="warehousename"/></td>
									<td>
										<s:if test='%{warehouseOkList[#st1.index].warehousetype == 1}'>采购</s:if>
										<s:elseif test='%{warehouseOkList[#st1.index].warehousetype == 2}'>销售</s:elseif>
										<s:elseif test='%{warehouseOkList[#st1.index].warehousetype == 6}'>库存修正</s:elseif>
										<s:else>
											<s:property value="warehousetype"/>
										</s:else>
									</td>
									<td><s:property value="tradename"/></td>
									<td><s:property value="typeno"/></td>
									<td>
										<s:iterator id="colorList" value="colorList" status="st3">
											<s:if test="%{colorList[#st3.index].code == warehouseOkList[#st1.index].color}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td>
										<s:if test='%{warehouseOkList[#st1.index].packaging == "0"}'>整箱</s:if>
										<s:elseif test='%{warehouseOkList[#st1.index].packaging == "1"}'>乱尺</s:elseif>
										<s:else>
											<s:property value="packaging"/>
										</s:else>
									</td>
									<td><s:property value="item10"/></td>
									<td>
										<s:iterator id="makeareaList" value="makeareaList" status="st3">
											<s:if test="%{makeareaList[#st3.index].code == warehouseOkList[#st1.index].makearea}">
												<s:property value="fieldname"/>
											</s:if>
										</s:iterator>
									</td>
									<td><s:property value="suppliername"/></td>
									<td align="right"><a href="#" onclick="showDetail('<s:property value="productid"/>');"><u><s:property value="quantity"/></u></a></td>
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
			</s:form>
		</div>
	</div>
</body>
</html>
