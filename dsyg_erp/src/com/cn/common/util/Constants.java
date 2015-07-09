package com.cn.common.util;

/**
 * 常量类
 * @name Constants.java
 * @author Frank
 * @time 2013-9-24下午8:57:14
 * @version 1.0
 */
public class Constants {
	
	//数据导出常量
	//采购单数据
	public final static String EXCEL_TYPE_PURCHASELIST = "purchase";
	
	//当前系统所属地
	public final static String SYSTEM_BELONG = "belongto";
	//多语言
	public final static String SYSTEM_LANGUAGE = "language";
	//URL后缀名
	public final static String URL_SUFFIX = ".shtml";
	//ul编码为空字符串
	public final static String UL_EMPTY = "empty";
	//首页新闻条数
	public final static int SHOW_NEWS_COUNT = 6;
	//公司新闻，初始显示年份显示
	public final static int SHOW_NEWS_YEAR = 2012;
	//最多显示N年的新闻
	public final static int SHOW_NEWS_YEAR_COUNT = 5;
	
	//dict01
	//产品分类
	public final static String DICT_GOODS_TYPE = "goods";
	public final static String DICT_GOODS_TYPE_CODE_01 = "01";//电线
	public final static String DICT_GOODS_TYPE_CODE_02 = "02";//套管
	public final static String DICT_GOODS_TYPE_CODE_03 = "03";//扁平线
	public final static String DICT_GOODS_TYPE_CODE_04 = "04";//线束
	public final static String DICT_GOODS_TYPE_CODE_05 = "05";//连接器
	public final static String DICT_GOODS_TYPE_CODE_06 = "06";//FPC
	
	//包装
	public final static String DICT_PACKAGING_TYPE_CODE_1 = "1";//乱尺
	public final static String DICT_PACKAGING_TYPE_CODE_0 = "0";//整箱
	
	//产地
	public final static String DICT_MAKEAREA = "makearea";
	
	//小分类：耐温
	public final static String DICT_SUB_TYPE_ITEM01 = "item01";
	//小分类：耐压
	public final static String DICT_SUB_TYPE_ITEM02 = "item02";
	//小分类：材质（电线）/绝缘（套管）
	public final static String DICT_SUB_TYPE_ITEM03 = "item03";
	//小分类：环保（电线）/收缩比（套管）
	public final static String DICT_SUB_TYPE_ITEM04 = "item04";
	//小分类：材质（套管）
	public final static String DICT_SUB_TYPE_ITEM05 = "item05";
	//小分类：环保（套管）
	public final static String DICT_SUB_TYPE_ITEM06 = "item06";
	
	//单位
	public final static String DICT_UNIT_TYPE = "unit";
	
	//颜色
	public final static String DICT_COLOR_TYPE = "color";
	
	//税率
	public final static String DICT_RATE = "rate";
	
	//财务主题
	public final static String FINANCE_THEME = "financetheme";
	
	//properties
	public final static String PROPERTIES_IMAGES_URL = "images_url";
	public final static String PROPERTIES_IMAGES_PATH = "images_path";
	public final static String PROPERTIES_PDF_URL = "pdf_url";
	public final static String PROPERTIES_PDF_PATH = "pdf_path";
	public final static String PROPERTIES_NEW_PIC_URL = "news_img_url";
	public final static String PROPERTIES_NEW_PIC_PATH = "news_img_path";
	
	/**
	 * 当前用户角色对应的资源列表
	 */
	public final static String SESSION_RESOURCE_LIST = "resource_list";
	
	/**
	 * 当前用户角色对应的资源MAP（拦截器用）
	 */
	public final static String SESSION_RESOURCE_MAP = "resource_map";
	
	/**
	 * 验证码
	 */
	public final static String SESSION_RANDOM = "random";
	
	/**
	 * 是否登录
	 */
	public final static String SESSION_ISLOGIN = "is_login";
	
	/**
	 * 已登录
	 */
	public final static String SESSION_FLAG_IS_LOGIN = "1";
	
	/**
	 * 未登录
	 */
	public final static String SESSION_FLAG_NOT_LOGIN = "0";
	
	/**
	 * 登录ID
	 */
	public final static String SESSION_USER_ID = "user_id";
	
	/**
	 * 用户名
	 */
	public final static String SESSION_USER_NAME = "user_name";
	
	/**
	 * 用户颜色
	 * 
	 */
//	public final static String SESSION_USER_COLOR = "user_color";
	
	
	/**
	 * 登录时间
	 */
	public final static String SESSION_LOGIN_TIME = "login_time";
	
	/**
	 * 用户安全级别（权限）
	 */
	public final static String SESSION_ROLE_RANK = "user_rank";
	//用户权限级别（公司用户99=超级管理员）
	public final static int ROLE_RANK_ADMIN = 99;
	//用户权限级别（公司用户70=一般用户）只能输入和更新产品、库存和新闻，不能删除产品、库存和新闻
	public final static int ROLE_RANK_NORMAL = 70;
	//普通浏览用户（非公司用户50=操作员）只能输入和更新产品和库存，不能删除产品和库存，可以看新闻
	public final static int ROLE_RANK_OPERATOR = 50;
	
	//角色code
	public final static String ROLE_CODE_ADMIN = "admin";
	public final static String ROLE_CODE_MANAGER = "manager";
	public final static String ROLE_CODE_WAREHOUSE = "warehouse";
	public final static String ROLE_CODE_EMPLOYEE = "employee";
	public final static String ROLE_CODE_NORMAL = "normal";
	
	//库存类型：1为入库单
	public final static int WAREHOUSE_TYPE_IN = 1;
	//库存类型：2为出库单
	public final static int WAREHOUSE_TYPE_OUT = 2;
	
	//发货单数据来源：1为入库单
	public final static int WAREHOUSERPT_TYPE_IN = 1;
	//发货单数据来源：2为出库单
	public final static int WAREHOUSERPT_TYPE_OUT = 2;
	//发货单数据来源：3为退货
	public final static int WAREHOUSERPT_TYPE_REFUND = 3;
	//发货单数据来源：4为手动录入
	public final static int WAREHOUSERPT_TYPE_INPUT = 4;
	
	//库存记录状态
	//新增
	public final static int WAREHOUSE_STATUS_NEW = 10;
	//入库（出库）确认
	public final static int WAREHOUSE_STATUS_OK = 20;
	//已发货=========预留
	public final static int WAREHOUSE_STATUS_SEND = 30;
	//退货OR损毁
	public final static int WAREHOUSE_STATUS_REFUND = 40;
	
	//采购单状态
	//采购单新增
	public final static int PURCHASE_STATUS_NEW = 10;
	//采购单付款申请
	public final static int PURCHASE_STATUS_PAY_APPLY = 20;
	//采购单付款审批
	public final static int PURCHASE_STATUS_PAY_APPROVE = 30;
	//采购单付款完了
	public final static int PURCHASE_STATUS_PAY_OK = 40;
	//采购单已开票
	public final static int PURCHASE_STATUS_PAY_INVOICE = 50;
	
	//销售单状态
	//采购单新增
	public final static int SALES_STATUS_NEW = 10;
	//销售单开票申请
	public final static int SALES_STATUS_BILL_APPLY = 20;
	//销售单开票审批
	public final static int SALES_STATUS_BILL_APPROVE = 30;
	//销售单开票完了
	public final static int SALES_STATUS_BILL_OK = 40;
	
	//财务状态
	//新增
	public final static int FINANCE_STATUS_NEW = 10;
	//付款申请
	public final static int FINANCE_STATUS_PAY_APPLY = 20;
	//付款审批
	public final static int FINANCE_STATUS_PAY_APPROVE = 30;
	//已付款
	public final static int FINANCE_STATUS_PAY_OK = 40;
	//已开票
	public final static int FINANCE_STATUS_PAY_INVOICE = 50;
	
	//删除标记
	/**
	 * 已删除
	 */
	public final static int STATUS_DEL = 0;
	
	/**
	 * 正常
	 */
	public final static int STATUS_NORMAL = 1;
}
