<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.tree.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #A6EAD3;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(<%=request.getContextPath()%>/images/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	background-image: url(<%=request.getContextPath()%>/images/menu_bg1.gif);
	background-repeat: repeat-y;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	function openUrl(url, obj) {
		var urlList = document.getElementsByName("urlList");
		for(var i = 0; i < urlList.length; i++) {
			urlList[i].style.color = "";
		}
		parent.window.frames['mainFrame'].location = url;
		obj.style.color = "red";
	}
</script>
</head>
<body>
<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
	<tr>
		<td width="182" valign="top">
			<div id="container">
				<s:if test="resourceList != null && resourceList.size() > 0">
					<s:iterator id="resourceList" value="resourceList" status="st1">
						<s:if test='%{resourceList[#st1.index].restype == "1"}'>
							<h1 class="type"><a href="javascript:void(0)"><s:property value="note"/></a></h1>
							<s:if test="%{#st1.index == 0}">
								<div class="content" style="padding-top:8px;padding-bottom:8px;">
							</s:if>
							<s:else>
								<div class="content" style="padding-top:8px;padding-bottom:8px;display: none;">
							</s:else>
								<ul>
									<s:iterator id="resourceList" value="resourceList" status="st2">
										<s:if test='%{resourceList[#st2.index].parentid == resourceList[#st1.index].id}'>
											<li>
												<a href="#" name="urlList" onclick="openUrl('<%=request.getContextPath()%>/${resourceList[st2.index].url}', this);">${resourceList[st2.index].note}</a>
											</li>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</s:if>
					</s:iterator>
				</s:if>
			</div>
			<div class="" style="width:182px;background-image: url(<%=request.getContextPath()%>/images/menuline.gif);background-repeat: repeat-x;"></div>
		</td>
	</tr>
</table>
<script type="text/javascript">
	$(function() {
		$('.type').bind('click', function() {
			toggleMenu(this);
		});
		$(".content").tree({
			types : {
				"default" : {
					clickable	: true, // can be function
					renameable	: false, // can be function
					deletable	: false, // can be function
					creatable	: false, // can be function
					draggable	: false, // can be function
					max_children	: -1, // -1 - not set, 0 - no children, 1 - one child, etc // can be function
					max_depth		: -1, // -1 - not set, 0 - no children, 1 - one level of children, etc // can be function
					valid_children	: "all" // all, none, array of values // can be function
      
				}
			},		    
			ui : {
				theme_name : "classic"
			},
			callable: {
				onselect: function(NODE, TREE_OBJ) {
				}
			}

		});  
	});
	
	function toggleMenu(obj){
		var num=$(obj).index(".type");
		$(".content:visible,not(.content:eq("+num+")").hide();
		$(".content:eq("+num+")").show("fast");
	}
</script>
</body>
</html>
