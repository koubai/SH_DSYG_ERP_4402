function changeBackcolor(obj) {
	if(obj.checked) {
		document.getElementById("containermain").style.backgroundColor = "#ffffcc";
	} else {
		document.getElementById("containermain").style.backgroundColor = "";
	}
}

function getOpener() {
	var obj = window.dialogArguments;
	if(obj == null || obj == undefined || obj == "undefined") {
		obj = window.opener;
	} else {
		obj = window.dialogArguments;
	}
	return obj;
}

function getOpenerElement(id) {
	var obj = window.dialogArguments;
	if(obj == null || obj == undefined || obj == "undefined") {
		return window.opener.document.getElementById(id);
	} else {
		return  window.dialogArguments.document.getElementById(id);
	}
}

function getOpenerElementValue(id) {
	var obj = window.dialogArguments;
	if(obj == null || obj == undefined || obj == "undefined") {
		return window.opener.document.getElementById(id).value;
	} else {
		return window.dialogArguments.document.getElementById(id).value;
	}
}

function checkRadioTd(obj, evt) {
	var tr = obj.parentNode;
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[0].getElementsByTagName("input");
	inputs[0].checked = true;
	var e = (evt) ? evt : window.event;
	//防止冒泡
	if(window.event) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}
}

function checkRadioTr(tr, evt) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[0].getElementsByTagName("input");
	inputs[0].checked = true;
}

function checkRadioTr1(tr, evt) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[1].getElementsByTagName("input");
	inputs[0].checked = true;
}

function checkCheckboxTr(tr, evt) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[0].getElementsByTagName("input");
	if(inputs[0].checked) {
		inputs[0].checked = false;
	} else {
		inputs[0].checked = true;
	}
}

function checkCheckboxTr1(tr, evt) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[1].getElementsByTagName("input");
	if(inputs[0].checked) {
		inputs[0].checked = false;
	} else {
		inputs[0].checked = true;
	}
}

function checkRadioCuTr(tr, evt, i, j) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[i].getElementsByTagName("input");
	inputs[j].checked = true;
}

function checkCheckboxCuTr(tr, evt, i, j) {
	var tds = tr.getElementsByTagName("td");
	var inputs = tds[i].getElementsByTagName("input");
	if(inputs[j].checked) {
		inputs[j].checked = false;
	} else {
		inputs[j].checked = true;
	}
}


/**
 * 取得时间
 * @return 返回格式为yyyyMMddHHmmss的字符串
 */
function getDate() {
	var date = new Date();
	var time = date.toLocaleString();
	time = time.replace("年","");
	time = time.replace("月","");
	time = time.replace("日","");
	time = time.replace(":","");
	time = time.replace(":","");
	time = time.replace(" ","");
	return time;
}

//取得浏览器版本
function getBrower(userAgent) {
	if(userAgent.indexOf("Firefox") != -1) {
		//fireFox
		return "Firefox";
	} else if(userAgent.indexOf("Chrome") != -1) {
		return "Chrome";
	} else {
		return "Other";
	}
}

/**
 * 判断STR是否为整数组成的字符串 ，返回true 和FALSE(可接收0,01等,不可接收+01,+1等)
 * @param str
 * @return
 */
function isInteger(str) {
	re=/[^0-9]/;
	rp=str.search(re); 
	return rp==-1;
}

/**
 * 判断STR是否为整数组成的字符串，可带一个负号
 * @param str
 * @return
 */
function checkInteger(str) {
	var reg;
	if(str.indexOf("-") >= 0) {
		reg = /^-[0-9]{0,}$/;
	} else {
		reg = /^[0-9]{0,}$/;
	}
	return reg.test(str);
}

/**
 * 根据位数判断整数 
 * @param str
 * @param pos
 * @return
 */
function iscInteger(str, pos) {
	if(str.length > parseInt(pos)){
		return false;
	}
	re=/[^0-9]/;
	rp=str.search(re); 
	return rp==-1;
}

/**
 * 验证是否是大于0的数字
 * @param s
 * @return
 */
function isNumber(s) {
	var reg = /^[1-9][0-9]{0,}$/;
	return reg.test(s);
}

/**
 * 验证是否是数字
 * @param s
 * @return
 */
function numberCheck(s) {
	var reg = /^\d+$/;
	return reg.test(s);
}

/**
 * 验证邮箱格式是否正确
 * @param s
 * @returns
 */
function isMail(s) {
	var reg = /^([a-z0-9A-Z_-]+[-|\.]?)+[a-z0-9A-Z_-]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
	return reg.test(s);
}

/**
 * 判断是否是实数
 * @param s
 * @return
 */
function isReal(s) {
	var reg;
	if(s.indexOf(".") >= 0) {
		reg = /^\d+\.\d+$/;
	} else {
		reg = /^\d+$/;
	}
	return reg.test(s);
}

/**
 * 判断是否是实数
 * @param s
 * @return
 */
function isAllReal(s) {
	var reg;
	if(s.indexOf("-") >= 0) {
		if(s.indexOf(".") >= 0) {
			reg = /^[-]\d+\.\d+$/;
		} else {
			reg = /^[-]\d+$/;
		}
	} else {
		if(s.indexOf(".") >= 0) {
			reg = /^\d+\.\d+$/;
		} else {
			reg = /^\d+$/;
		}
	}
	return reg.test(s);
}

/**
 * 设置整数显示为小数显示
 * @param str
 * @return
 */
function number2real(str) {
	if(str.indexOf(".") == 0){
		str = "0" + str;
	}
	if(str.indexOf(".") == -1){
		if(isInteger(str)){
			str += ".00";
		}
	}
	return str;
}

//验证web地址
function regUrl(url) {
	var urlreg = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	return urlreg.test(url);
}

//验证招标编号，类型=招标
function isZB(zbno) {
	//格式:LHZB-YY-NNN
	var reg = /^LHZB\-[0-9]{2}\-[0-9]{3}$/;
	return reg.test(zbno);
}

//验证招标编号，类型=比选
function isBX(zbno) {
	//格式:LHBX-YY-NNN
	var reg = /^LHBX\-[0-9]{2}\-[0-9]{3}$/;
	return reg.test(zbno);
}

/**
 * 设置小数显示为整数显示
 * @param obj
 * @return
 */
function real2number(obj){
	var tmpValue = obj.value;
	var flg = tmpValue.indexOf(".");
	if( flg != -1){
		tmpValue = tmpValue.substring(0,flg);
		//alert(tmpValue);
	}
	obj.value = tmpValue;
}

//去空格函数
String.prototype.trim = function() {
	// 用正则表达式将前后空格 
	// 用空字符串替代。
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

//取得长度（包括中文按字符计算）
String.prototype.gblen = function() {   
    var len = 0;   
    for (var i=0; i<this.length; i++) {   
        if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {   
            len += 2;   
        } else {   
            len ++;   
        }   
    }   
    return len;   
};

/**
 * 计算名称长度
 * @param name
 * @return
 */
function getLength(name) {
	var leng = name.length;
	for(i = 0;i < name.length;i++) {
		if(name.substring(i,i + 1).replace(/[\x00-\xff]/g,"") != "") {
	    	leng = leng + 1;
		}
	}
	return leng;
}

/**
 * 替换单引号
 * @param obj
 * @return
 */
function dofilter(obj){
	if(obj.value.indexOf("\'") > -1){
		obj.value = obj.value.replace(/\'/g,'');
		//obj.value.replace(/>/g,'&gt;').replace(/</g,'&lt;').replace(/\'/g,'&#39;').replace(/\"/g,'&quot;');
	}
}

/**
 * 判断是否是Email格式
 * @param s
 * @return
 */
function isEmail(s){
	//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	var reg = /^([a-z0-9A-Z_-]+[-|\.]?)+[a-z0-9A-Z_-]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
	return reg.test(s);
}

/**
 * 判断是否是日期格式
 * @param s
 * @return
 */
function isDate(s){
	var patrn=/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/; 
	if (!patrn.exec(s)){
		return false;
	}
	return true;
}

/**
 * 验证是否包含非法字符
 * @param s
 * @return
 */
function checkString(s){
	var valid= /[\"\'\\\/\<\>\%\&\!\`\*\=\;\?\#\+\|\:\,\$]+/;
	return (valid.test(s));
}

/**
 * 验证是否是手机号码
 * @param s
 * @return
 */
function isMobile(s) {
	//var valid= /^1\d{10}$/;
	var valid= /^1[3|4|5|8][0-9]\d{4,8}$/;
	return (valid.test(s));
}

/**
 * 验证是否是固定电话
 * @param s
 * @return
 */
function isTelephone(s) {
	var valid= /^[0-9]{1}[0-9]{2,3}[-]{0,1}[1-9]{1}[0-9]{5,8}$/;
	return (valid.test(s));
}

//验证身份证开始
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}; 
/**
 * 验证身份证
 * @param sId
 * @return
 */
function cidInfo(sId){ 
	var iSum=0;
	var info="";
	if(!/^\d{17}(\d|x)$/i.test(sId)){
		return false; 
	}

	sId=sId.replace(/x$/i,"a"); 
	if(aCity[parseInt(sId.substr(0,2))]==null){
		return false;
	}
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/"));
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
		return false;
	}
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11);
	if(iSum%11!=1){
		return false; 
	}
	return true; 
} 

function checkIDCard(str){   
	//身份证正则表达式(15位)   
	isIDCard1=/^[1-9]\d{14}$/; 
	//身份证正则表达式(18位) 
	isIDCard2=/^[1-9]\d{16}(?:\d|x|X)$/; 
	//验证身份证，返回结果   
	return (isIDCard1.test(str)||isIDCard2.test(str)); 
}

/**
 * 验证图片后缀名
 * @param file
 * @return
 */
function checkIconName(file) {
	var filepath = $("#" + file).val(); 
	var extStart = filepath.lastIndexOf("."); 
	var ext = filepath.substring(extStart,filepath.length).toUpperCase();
	if(ext != ".PNG" && ext != ".GIF" && ext != ".JPG") {
		return false;
	}
	return true;
}

/**
 * 验证文件后缀名
 * @param file
 * @return
 */
function checkDocName(file) {
	var filepath = $("#" + file).val(); 
	var extStart = filepath.lastIndexOf("."); 
	var ext = filepath.substring(extStart,filepath.length).toUpperCase();
	if(ext != ".DOC" && ext != ".DOCX") {
		return false;
	}
	return true;
}

/**
 * 验证图片后缀名
 * @param file
 * @return
 */
function checkImgName(file) {
	var filepath = file.value; 
	var extStart = filepath.lastIndexOf("."); 
	var ext = filepath.substring(extStart,filepath.length).toUpperCase(); 
	if(ext != ".PNG" && ext != ".GIF" && ext != ".JPG") {
		return false;
	}
	return true;
}

/**
 * 验证图片大小
 * @param file
 * @return
 */
function checkIconSize(file) {
	var imgSize = 1024 * 100; //最大100K
	var file = document.getElementById(file);
	if(file.value != "") {
		var img = new Image();
        img.onreadystatechange = function() {
        	if(img.readyState == "complete") {
                if(img.fileSize <= 0 || img.fileSize > imgSize) {
                    return false;
                }else{
                    return true;
                }
            }
        };
	}
	return false;
}

function mouseover(obj) {
	obj.style.backgroundColor = "#ccdcec";
}

function mouseout(obj, color) {
	obj.style.backgroundColor = color;
}

/**
 * 是否中标名称转化为code
 * @param name
 * @returns {String}
 */
function bidResultName2Code(name) {
	name = name.trim();
	if(name == "是") {
		return "Y";
	} else if(name == "否") {
		return "N";
	}
	return "";
}

/**
 * 是否中标code转化为名称
 * @param code
 * @returns {String}
 */
function bidResultCode2Name(code) {
	code = code.trim();
	if(code == "Y") {
		return "是";
	} else if(code == "N") {
		return "否";
	}
	return "";
}

/**
 * 支付方式名称转化为code
 * @param name
 * @returns
 */
function bidPaymentTypeName2Code(name) {
	name = name.trim();
	if(name == "现金") {
		return "1";
	} else if(name == "支票") {
		return "2";
	} else if(name == "转账") {
		return "3";
	} else if(name == "汇票") {
		return "4";
	} else if(name == "保函") {
		return "5";
	}
	return "";
}

/**
 * 支付方式code转化为名称
 * @param code
 * @returns
 */
function bidPaymentTypeCode2Name(code) {
	code = code.trim();
	if(code == "1") {
		return "现金";
	} else if(code == "2") {
		return "支票";
	} else if(code == "3") {
		return "转账";
	} else if(code == "4") {
		return "汇票";
	} else if(code == "5") {
		return "保函";
	} else if(code == "6") {
		return "现金2";
	}
	return "";
}