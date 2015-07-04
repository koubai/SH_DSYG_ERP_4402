package com.cn.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @name DateUtil.java
 * @author Frank
 * @time 2013-9-24下午8:57:48
 * @version 1.0
 */
public class DateUtil {

	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

	public static final String DATE_FORMAT_SHORTE = "yyyy年MM月dd日";

	public static final String DATE_FORMAT_SHORTER = "yyyy-M-d";

	public static final String DATE_FORMAT_POINT = "yyyy.MM.dd";

	public static final String DATE_FORMAT_MONTH_DAY = "M/d";

	public static final String DATE_FORMAT_YEAR_MONTH = "yyyyMM";

	public static final String DATE_FORMAT_SHORT_YEAR = "yyMMdd";
	
	public static final String DATE_FORMAT_LOGIN_TIME = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 返回格式化的日期字符串
	 * @param date 日期
	 * @param format 格式
	 * @return 格式化的日期字符串
	 */
	public static String dateToStr(Date date, String format) {
		if(date != null) {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
		return "";
	}
	
	/**
	 * 返回格式化(yyyy/MM/dd HH:mm:ss)的日期字符串
	 * @param date 日期
	 * @return 格式化的日期字符串
	 */
	public static String dateToLogintime(Date date) {
		if(date != null) {
			return dateToStr(date, DATE_FORMAT_LOGIN_TIME);
		}
		return "";
	}

	/**
	 * 返回格式化(yyyy-MM-dd HH:mm:ss.SSS)的日期字符串
	 * @param date 日期
	 * @return 格式化的日期字符串
	 */
	public static String dateToFullStr(Date date) {
		return dateToStr(date, DATE_FORMAT_FULL);
	}

	/**
	 * 返回格式化(yyyy-MM-dd HH:mm:ss)的日期字符串
	 * @param date 日期
	 * @return 格式化的日期字符串
	 */
	public static String dateToLongStr(Date date) {
		return dateToStr(date, DATE_FORMAT_LONG);
	}

	/**
	 * 返回格式化(yyyy-MM-dd)的日期字符串
	 * @param date 日期
	 * @return 格式化的日期字符串
	 */
	public static String dateToShortStr(Date date) {
		return dateToStr(date, DATE_FORMAT_SHORT);
	}

	/**
	 * 将字符串转换为日期
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return 日期
	 * @throws ParseException
	 */
	public static Date strToDate(String dateStr, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 判断字符串是否为日期类型("yyyy-MM-dd")
	 * @param dateStr 字符串
	 * @return true是日期类型，否则返回false
	 */
	public static boolean isDate(String dateStr) {
		return dateStr.matches("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$");
	}

	/**
	 * 判断字符串是否为日期带时间类型("yyyy-MM-dd HH:mm:ss")
	 * @param dateStr 字符串
	 * @return true是日期带时间类型，否则返回false
	 */
	public static boolean isDateTime(String dateStr) {
		return dateStr.matches("^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)) (([01]{1}\\d{1}|2[0-3]{1}):[0-5]{1}\\d{1}:[0-5]{1}\\d{1})$");
	}

	/**
	 * 判断字符串是否为时间类型("HH:mm:ss")
	 * @param dateStr 字符串
	 * @return true是时间类型，否则返回false
	 */
	public static boolean isTime(String dateStr) {
		return dateStr.matches("^([01]{1}\\d{1}|2[0-3]{1}):[0-5]{1}\\d{1}:[0-5]{1}\\d{1}$");
	}

	/**
	 * 日期增加或减去某个天数
	 * @param dt 需要操作的时间
	 * @param num 增加或减去的天数
	 * @return 返回操作后的时间
	 */
	public static Date getBeforeOrAfterDate(Date dt, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}

	/**
	 * 日期增加或减去某个时间单位数
	 * @param dt 需要操作的时间
	 * @param num 增加或减去的天数
	 * @param typ 时间单位类型(年，月，日，星期，分钟等)
	 * @return 返回操作后的时间
	 */
	public static Date getBeforeOrAfterDate(Date dt, int num, int field) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(field, num);
		return cal.getTime();
	}

	/**
	 * 返回日期是星期几 Calendar.SUNDAY, MONDAY, TUESDAY,
	 * WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	 * @param dt
	 * @return
	 */
	public static int getDayOfWeek(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 判断两个日期区间是否有交集
	 * @param a1 第一个日期区间的首日日期
	 * @param a2 第一个日期区间的末日日期
	 * @param b1 第二个日期区间的首日日期
	 * @param b2 第二个日期区间的末日日期
	 * @return 有交集返回true, 没交集返回false
	 */
	public static boolean hasIntersection(Date a1, Date a2, Date b1, Date b2) {
		if ((a1.before(b1) && (a2.after(b1) || a2.equals(b1)))
				|| ((a1.after(b1) || a1.equals(b1)))
				&& ((a1.before(b2)) || (a1.equals(b2))))
			return true;
		return false;
	}

	/**
	 * 返回指定日期的月份
	 * @param date 日期
	 * @return 月份
	 */
	public static String getMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * 返回月份所在季度
	 */
	public static String getQuarterByMonth(String mon) {
		int month = Integer.parseInt(mon);
		return String.valueOf(month / 3 + 1);
	}

	/**
	 * 返回两个日期小时差
	 */
	public static double getHour(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long ms = getMilliSecond(d1, d2);
		return ms / 1000.00 / 60 / 60;
	}

	/**
	 * 返回两个日期天数差
	 */
	public static long getDay(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long ms = getMilliSecond(d1, d2);
		return ms / 1000 / 60 / 60 / 24;
	}

	/**
	 * 返回两个日期毫秒差
	 */
	public static long getMilliSecond(Date d1, Date d2) {
		long d1MS = d1.getTime();
		long d2MS = d2.getTime();
		return Math.abs(d1MS - d2MS);
	}

	/**
	 * 返回两个日期秒差
	 */
	public static long getSecond(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long ms = getMilliSecond(d1, d2);
		return ms / 1000;
	}

	/**
	 * 获得递增数天后的时间
	 * @param date
	 * @param amount 可以为任意整数
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 在日期上加月数
	 * @param sDate
	 * @param num
	 * @return
	 */
	public static Date addMonths(Date sDate, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sDate);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}

	/**
	 * 获得递增数年后的时间
	 * @param date
	 * @param amount 可以为任意整数
	 * @return
	 */
	public static Date addYears(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}

	/**
	 * 获取某月的天数
	 * @param year 年份
	 * @param month 月份
	 * @return 天数
	 */
	public static int getDaysByMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算日期和指定日期天数差
	 * @param strDate
	 * @param standardDate
	 * @return
	 */
	public static long calcDayMinus(String strDate, String standardDate) {
		try {
			if(isDate(strDate)) {
				Date date = strToDate(strDate, DATE_FORMAT_SHORT);
				Date today = strToDate(standardDate, DATE_FORMAT_LONG);
				long l = Math.abs((date.getTime() - today.getTime()) / (24 * 60 * 60 * 1000));
				return l;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 时间戳转化为日期格式
	 * @param t 时间戳
	 * @param n 精确到毫秒则n=1，精确到秒则n=1000
	 * @return
	 */
	public static String exchangeDate(long t, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(t);
		return date;
	}
	
	/**
	 * 时间戳转化Date类型
	 * @param t
	 * @return
	 */
	public static Date long2Date(long t) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t);
		Date d = cal.getTime();
		return d;
	}
	
	/**
	 * 取得当前周的周一（按中国传统周计算）
	 * @param date
	 * @return
	 */
	public static Date getCurrentWeekMonday(Date date) {
		int week = getDayOfWeek(date);
		if(week == 1) {
			return addDays(date, -6);
		}
		return addDays(date, 2 - week);
	}
	
	/**
	 * 取得当前周的周天（按中国传统周计算）
	 * @param date
	 * @return
	 */
	public static Date getCurrentWeekSunday(Date date) {
		int week = getDayOfWeek(date);
		if(week == 1) {
			return date;
		}
		return addDays(date, 8 - week);
	}
	
	/**
	 * 字符串时间转LONG
	 * @param sdate
	 * @return
	 */
	public static long string2long(String sdate){
		if(sdate.length() < 11){
			sdate = sdate + " 00:00:00";
		}
		SimpleDateFormat sdf= new SimpleDateFormat(DATE_FORMAT_LONG);
		Date dt2 = null;
		try {
			dt2 = sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//继续转换得到秒数的long型
		long lTime = dt2.getTime() / 1000;
		return lTime;
	}
	
	/**
	 * LONG时间转字符串
	 * @param ldate
	 * @return
	 */
	public static String long2string(long ldate){
		SimpleDateFormat sdf= new SimpleDateFormat(DATE_FORMAT_LONG);
		//前面的ldate是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
		java.util.Date dt = new Date(ldate * 1000);  
		String sDateTime = sdf.format(dt);  //得到精确到秒的表示
		if(sDateTime.endsWith("00:00:00")){
			sDateTime = sDateTime.substring(0,10);
		}
		return sDateTime;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(calcDayMinus("2013-06-12", "2013-06-12 12:01:02"));
	}
}
