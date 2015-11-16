package com.cn.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

/**
 * 字符串操作工具类
 * @author Frank
 * @time 2013-10-15下午10:15:15
 * @version 1.0
 */
public class StringUtil {
	
	/**
	 * 取得对象的字符串
	 * @param o
	 * @return
	 */
	public String getStr(Object o) {
		if(o == null) {
			return "";
		}
		return o.toString();
	}
	
	/**
	 * 计算字符串长度
	 * @param s
	 * @return
	 */
	public static int calcStringLength(String s) {
		if (s == null) {
			return 0;
		}
		String anotherString = null;
		try {
			anotherString = new String(s.getBytes("GBK"), "ISO8859_1");
			return anotherString.length();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 验证字符串是否包含非法字符
	 * @param s
	 * @return
	 */
	public static boolean checkString(String s) {
		String regex = "[\"'\\/<>%&!`*=;?#+|:,$]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证字符串是否为手机号码
	 * @param s
	 * @return
	 */
	public static boolean isMobile(String s) {
		if(StringUtil.isBlank(s)) {
			return false;
		}
		//String regex = "^1\\d{10}$";
		String regex = "^1[3|4|5|8][0-9]\\d{4,8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 替换数据库关键字符
	 * @param value
	 * @return
	 * Date: 2012-8-6上午10:35:53
	 */
	public static String replaceDatabaseKeyword_mysql(String value) {
		if(value == null) {
			return null;
		}
		if (value.indexOf("/") >= 0) {
			value = value.replace("/", "//");
		}
		if (value.indexOf("'") >= 0) {
			value = value.replace("'", "''");
		}
		if (value.indexOf("%") >= 0) {
			value = value.replace("%", "/%");
		}
		if (value.indexOf("_") >= 0) {
			value = value.replace("_", "/_");
		}
		return value;
	}
	
	/**
	 * 逗号是分割符号，需要屏蔽掉
	 * @param keyword
	 * @return
	 */
	public static String searchKeyword(String keyword) {
		keyword = replaceDatabaseKeyword_mysql(keyword);
		//逗号是分割符号，需要屏蔽掉
		if(keyword != null && !"".equals(keyword)) {
			keyword = keyword.replace(";", "");
		}
		return keyword;
	}
	
	/**
	 * 替换数据库关键字符
	 * @param value
	 * @return
	 * Date: 2012-8-6上午10:35:53
	 */
	public static String replaceDatabaseKeyword_oracle(String value) {
		if(value == null) {
			return null;
		}
		if (value.indexOf("\\") >= 0) {
			value = value.replace("\\", "\\\\");
		}
		if (value.indexOf("'") >= 0) {
			value = value.replace("'", "''");
		}
		if (value.indexOf("%") >= 0) {
			value = value.replace("%", "\\%");
		}
		if (value.indexOf("_") >= 0) {
			value = value.replace("_", "\\_");
		}
		return value;
	}
	
	/**
	 * 验证字符串是否为电话号码
	 * @param s
	 * @return
	 */
	public static boolean isTelephone(String s) {
		String regex = "^[0-9]{1}[0-9]{2,3}-[1-9]{1}[0-9]{5,8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证是否是邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String regex = "^([a-z0-9A-Z_-]+[-|\\.]?)+[a-z0-9A-Z_-]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否包含中文
	 * @param s
	 * @return
	 */
	public static boolean isContainsChinese(String s) {
		if(s == null) {
			return false;
		}
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(s);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证字符串是否为空，false为空或空字符串
	 * @param s
	 * @return
	 */
	public static boolean isNotBlank(String s) {
		if(s == null) {
			return false;
		}
		if("".equals(s.trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证字符串是否为空，false为空
	 * @param s
	 * @return
	 */
	public static boolean isNotNull(String s) {
		if(s == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证字符串是否为空，true为空
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s) {
		if(s == null) {
			return true;
		}
		if("".equals(s.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为空，为空则返回空字符串，否则就返回s
	 * @param s
	 * @return
	 */
	public static String getStr(String s) {
		if(s == null) {
			return "";
		}
		return s;
	}
	
	/**
	 * 取得随机数
	 * @param num
	 * @return
	 */
	public static String getRandomStr(int length) {
		if(length < 1) {
			return "";
		}
		char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] randBuffer = new char[length];
		Random random = new Random();
		for(int i = 0;i < length;i++) {
			randBuffer[i] = numbersAndLetters[random.nextInt(61)];
		}
		return new String(randBuffer);
	}
	
	/**
	 * 转义字符
	 * @param a_strInput 被转义的字符
	 * @return 转义后的字符
	 */
	public static String specEncoding(String a_strInput) {
		if(a_strInput == null) {
			return null;
		}
		if (a_strInput.indexOf("\\") > -1) {
			a_strInput = a_strInput.replace("\\", "\\\\");
		}
		if (a_strInput.indexOf("'") > -1) {
			a_strInput = a_strInput.replace("'", "''");
		}
		if (a_strInput.indexOf("%") > -1) {
			a_strInput = a_strInput.replace("%", "\\%");
		}
		if (a_strInput.indexOf("_") > -1) {
			a_strInput = a_strInput.replace("_", "\\_");
		}
		return a_strInput;
	}
	
	/**
	 * 字符串截取
	 * @param input 被截取的字符串
	 * @param len 截取长度
	 * @return 截取后的字符串
	 */
	public static String trancate(String input, int len) {
		return trancate(input, len, "");
	}
	
	/**
	 * 字符串截取
	 * @param input 被截取的字符串
	 * @param len 截取长度
	 * @param promptText 截取后添加的后缀
	 * @return 截取后的字符串
	 */
	public static String trancate(String input, int len, String promptText) {
		if(StringUtil.isBlank(input)) {
			return input;
		}
		StringBuffer sb = new StringBuffer(len);
		int count = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
				if (c <= '\u00ff') {
					count++;
				} else {
					count += 2;
					if (count > len) {
						return sb.toString().trim() + promptText;
					}
				}
				sb.append(c);
				if (count >= len) {
					return sb.toString().trim() + promptText;
				}
		}
		return input;
	}
	
	/**
	 * 验证身份证的方法
	 * @param idNum
	 * @return 返回true为合法身份证，false为不合法
	 */
	public static boolean checkIDCard(String idNum){
		if(StringUtil.isBlank(idNum)) {
			return false;
		}
		String regex = "^[1-9](\\d{14}|(\\d{16}[0-9xX]))$";
		return idNum.matches(regex);
	}
	
	/**
	 * 验证招标编号格式是否正确，类型=招标
	 * @param zbno
	 * @return
	 */
	public static boolean isZB(String zbno) {
		if(StringUtil.isBlank(zbno)) {
			return false;
		}
		String regex = "^LHZB\\-[0-9]{2}\\-[0-9]{3}$";
		return zbno.matches(regex);
	}
	
	/**
	 * 验证招标编号格式是否正确，类型=比选
	 * @param zbno
	 * @return
	 */
	public static boolean isBX(String zbno) {
		if(StringUtil.isBlank(zbno)) {
			return false;
		}
		String regex = "^LHBX\\-[0-9]{2}\\-[0-9]{3}$";
		return zbno.matches(regex);
	}
	
	/**
	 * 大数据类型blob转换为String
	 * @param blob
	 * @return
	 */
	public static String convertBlobToString(Blob blob){
		String result = "";
		try {
			if(blob == null || blob.length() == 0) {
				return result;
			}
			result = new String(blob.getBytes((long) 1, (int) blob.length()), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建文件名
	 * @param pre
	 * @return
	 */
	public static String createFileName(String pre) {
		String name = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		name = sdf.format(date);
		return pre + name + ".xlsx";
	}
	
	/**
	 * 创建html文件名
	 * @param pre
	 * @return
	 */
	public static String createHtmlFileName(String pre) {
		String name = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		name = sdf.format(date);
		return pre + name + ".html";
	}
	
	/**
	 * 创建html文件名
	 * @param pre
	 * @return
	 */
	public static String createXmlFileName(String pre) {
		String name = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		name = sdf.format(date);
		return pre + name + ".xml";
	}
	
	/**
	 * 将字符串补满位数（前面补0）
	 * @param str
	 * @param length 补满后长度
	 * @return
	 */
	public static String replenishStr(String str, int length) {
		//若位数超过，则返回它本身
		if(str != null && str.length() >= length) {
			return str;
		} else if(length > 0) {
			String newStr = "";
			for(int i = 0; i < length; i++) {
				newStr += "0";
			}
			newStr = newStr + str;
			return newStr.substring(newStr.length() - length, newStr.length());
		}
		return str;
	}
	
	/**
	 * BigDecimal转化为字符
	 * @param d
	 * @param length
	 * @return
	 */
	public static String BigDecimal2Str(BigDecimal d, int length) {
		if(d != null) {
			return "" + d.setScale(length, BigDecimal.ROUND_HALF_UP);
		}
		return "";
	}
	
	/**
	 * BigDecimal转化为字符
	 * @param d
	 * @param length
	 * @return
	 */
	public static String BigDecimal2StrAbs(BigDecimal d, int length) {
		if(d != null) {
			return "" + d.setScale(length, BigDecimal.ROUND_HALF_UP).abs();
		}
		return "";
	}
	
	/**
	 * 合计价格，默认为0
	 * @param b
	 * @return
	 */
	public static String totalPrice(BigDecimal b) {
		if(b != null) {
			return StringUtil.BigDecimal2Str(b, 2);
		}
		return "0";
	}
	
	/**
	 * @param list
	 * @return
	 */
	public static String list2json(List<?> list) {
		if(list != null && list.size() > 0) {
			JSONArray array = JSONArray.fromObject(list);
			return array.toString();
		}
		return "";
	}
	
	/**
	 * 打印MAP
	 * @param map
	 */
	public static void printMap(Map<?, ?> map) {
		if(map != null) {
			Set<?> key = map.keySet();
			for (Iterator<?> it = key.iterator(); it.hasNext();) {
				String k = (String) it.next();
				System.out.println("key=" + k + ",value=" + map.get(k));
			}
		}
	}
	
	public static void main(String arg[]) {
//		List<Dict01Dto> list = new ArrayList<Dict01Dto>();
//		Dict01Dto dd = new Dict01Dto();
//		dd.setCode("aa");
//		dd.setFieldname("bbbbbnn");
//		list.add(dd);
//		dd = new Dict01Dto();
//		dd.setCode("aa");
//		dd.setFieldname("bbbbbnn");
//		list.add(dd);
//		System.out.println(list2json(list));
	}
}
