package com.cn.common.util;

/**
 * 常量类
 * @name Constants.java
 * @author Frank
 * @time 2013-9-24下午8:57:14
 * @version 1.0
 */
public class Constants {

	//销售报告编号前缀
	public final static String SALESREPORT_NO_PRE = "BGN";
	//SALES报告编号前缀
	public final static String SALESREPORT2_NO_PRE = "SGN";
	//资产编号前缀
	public final static String ASSETS_NO_PRE = "ZCN";
	//员工编号前缀
	public final static String PERSONAL_NO_PRE = "YGN";
	//文档编号前缀
	public final static String DOCUMENT_NO_PRE = "WDN";
	//采购单单号前缀
	public final static String PURCHASE_NO_PRE = "CGD";
	//采购订单号前缀
	public final static String PURCHASE_ORDER_PRE = "DS";
	//销售单单号前缀
	public final static String SALES_NO_PRE = "XSD";
	//销售单订单号前缀（销售方式为询价询样时，自动采番）
	public final static String SALES_DD_PRE = "DD";
	//入库单前缀
	public final static String WAREHOUSERPT_IN_NO_PRE = "RKD";
	//出库单前缀
	public final static String WAREHOUSERPT_OUT_NO_PRE = "CKD";
	//财务记录单号前缀
	public final static String FINANCE_NO_PRE = "CWN";
	//库存记录单号前缀
	public final static String WAREHOUSE_NO_PRE = "KCN";
	//退换货单号前缀
	public final static String WAREHOUSERPT_REFUND_NO_PRE = "THN";
	//预收款编号前缀
	public final static String PREGEATHERING_NO_PRE = "PG";
	//请款编号前缀
	public final static String REQUESTCASH_NO_PRE = "RC";
	//客户跟踪编号前缀
	public final static String CUSTOMER_TRACK_NO_PRE = "CTN";
	
	//数据导出常量
	//采购单数据
	public final static String EXCEL_TYPE_PURCHASELIST = "purchase";
	//入库单数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_IN_LIST = "warehouserpt_in";
	//出库单数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_OUT_LIST = "warehouserpt_out";
	//入库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_LIST = "warehouserpt_in_detail";
	//出库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_LIST = "warehouserpt_out_detail";
	//入库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_LIST_NOPRICE = "warehouserpt_in_detail_noprice";
	//出库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_LIST_NOPRICE = "warehouserpt_out_detail_noprice";
	//入库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_INTER_LIST = "warehouserpt_in_detail_inter";
	//出库单明细数据
	public final static String EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_INTER_LIST = "warehouserpt_out_detail_inter";
	//库存盘点数据
	public final static String EXCEL_TYPE_WAREHOUSCHECK = "warehousercheck";
	//入库单数据
	public final static String EXCEL_TYPE_PRODUCT_COST = "productcost";
	//报价单数据
	public final static String EXCEL_TYPE_SALES_PRICE = "salesprice";
	//报价单数据
	public final static String EXCEL_TYPE_SALES_PRICE_ORDER = "salespriceorder";
	//采购单数据
	public final static String EXCEL_TYPE_PURCHASEITEM = "purchaseitem";
	
	//当前系统所属地
	public final static String SYSTEM_BELONG = "belongto";
	//多语言
	public final static String SYSTEM_LANGUAGE = "language";
	//英文
	public final static String SYSTEM_LANGUAGE_ENGLISH = "e";
	//中文
	public final static String SYSTEM_LANGUAGE_CHINESE = "c";
	//仓库名
	public final static String SYSTEM_WAREHOUSE_NAME = "warehouse_name";
	
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
	
	//支付方式
	public final static String DICT_PAY_TYPE = "pay";
	
	//税率
	public final static String DICT_RATE = "rate";

	//产地
	public final static String DICT_MAKEAREA_TYPE = "makearea";

	//采购订单号番号
	public final static String DICT_PURCHASE_ORDER = "purchaseorder";
	
	//供应商番号
	public final static String DICT_SUPPLIER_ORDER = "suppliererorder";

	//客户番号
	public final static String DICT_CUSTOMER_ORDER = "customerorder";

	//快递商番号
	public final static String DICT_EXPRESSER_ORDER = "expresserorder";
	
	//财务主题
	public final static String FINANCE_THEME = "financetheme";
	
	//excel密码
	public final static String EXCEL_PASS = "excelpass";
	
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
	 * 用户所属
	 */
	public final static String SESSION_BELONGTO = "belongto";

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
	 * 用户角色CODE
	 */
	public final static String SESSION_ROLE_CODE = "user_role";
	
	/**
	 * 用户安全级别（权限）
	 */
	public final static String SESSION_ROLE_RANK = "user_rank";
	//用户权限级别（公司用户99=超级管理员）
	public final static int ROLE_RANK_ADMIN = 99;
	//经理级别
	public final static int ROLE_RANK_MANAGER = 80;
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
	//库存类型：3为盘点
	public final static int WAREHOUSE_TYPE_PD = 3;
	
	//发货单数据来源：1为入库单
	public final static int WAREHOUSERPT_TYPE_IN = 1;
	//发货单数据来源：2为出库单
	public final static int WAREHOUSERPT_TYPE_OUT = 2;
	//发货单数据来源：3为退货
	public final static int WAREHOUSERPT_TYPE_REFUND = 3;
	//发货单数据来源：4为手动录入
	public final static int WAREHOUSERPT_TYPE_INPUT = 4;
	//发货单数据来源：5为损毁
	public final static int WAREHOUSERPT_TYPE_BROKEN = 5;
	
	//财务记录数据来源类型
	//入库单
	public final static int FINANCE_TYPE_PURCHASE = 1;
	//出库单
	public final static int FINANCE_TYPE_SALES = 2;
	//快递
	public final static int FINANCE_TYPE_DELIVERY = 3;
	//财务录入
	public final static int FINANCE_TYPE_INPUT = 4;
	
	//库存记录状态
	//新增
	public final static int WAREHOUSE_STATUS_NEW = 10;
	//入库（出库）确认
	public final static int WAREHOUSE_STATUS_OK = 20;
	//已发货=========预留
	public final static int WAREHOUSE_STATUS_SEND = 30;
	//退货OR损毁
	public final static int WAREHOUSE_STATUS_REFUND = 40;
	//已付款
	public final static int WAREHOUSE_STATUS_FINISHED = 99;
	
	//采购单状态
	//采购单 未发货
	public final static int PURCHASE_STATUS_NEW = 10;
	//采购单 部分收货
	public final static int PURCHASE_STATUS_WAREHOUSE_PART = 15;
	//采购单 已收货
	public final static int PURCHASE_STATUS_WAREHOUSE_OK = 20;
	
	//销售单状态
	//采购单 未发货
	public final static int SALES_STATUS_NEW = 10;
	//销售单 部分发货
	public final static int SALES_STATUS_WAREHOUSE_PART = 15;
	//销售单 已发货
	public final static int SALES_STATUS_WAREHOUSE_OK = 20;
		
	//入出库单状态
	//未付款，未付款
	public final static int WAREHOUSERPT_STATUS_NEW = 1;
	//收到发票，安排付款/已对帐, 未开票
	public final static int WAREHOUSERPT_STATUS_APPLY = 10;
	//已开票, 未收款（出库单）
	public final static int WAREHOUSERPT_STATUS_APPROVE = 20;
	//已开票, 已收款/付款 (完成)
	public final static int WAREHOUSERPT_STATUS_INVOICE = 99;
	
	//财务状态
	//未收到发票, 未付款/未对帐
	public final static int FINANCE_STATUS_NEW = 1;
	//收到发票, 安排付款/已对帐, 未开票
	public final static int FINANCE_STATUS_PAY_APPLY = 10;
	//未收到发票, 已付款/已收款, 未对账
	public final static int FINANCE_STATUS_PAY_PAYED = 15;
	//已开票, 未收款
	public final static int FINANCE_STATUS_PAY_APPROVE = 20;
	//收到发票, 已付款 (完成)/已开票, 已收款
	public final static int FINANCE_STATUS_PAY_INVOICE = 99;
	
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
