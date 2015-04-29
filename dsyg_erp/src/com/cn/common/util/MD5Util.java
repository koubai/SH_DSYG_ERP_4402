package com.cn.common.util;


/**
 * @name MD5Util.java
 * @author Frank
 * @time 2013-10-11下午9:16:28
 * @version 1.0
 */
public class MD5Util {

	/**
	 * 对字符串进行MD5加密
	 * @param value
	 * @return
	 */
	public static String md5(String value) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			StringBuffer result = new StringBuffer();
			try {
				for (byte b : md.digest(value.toString().getBytes("UTF-8"))) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			} catch (Exception e) {
				for (byte b : md.digest(value.toString().getBytes())) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String arg[]) {
		System.out.println(md5("1111"));
	}
}
