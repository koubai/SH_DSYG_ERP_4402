package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.WarehouserptService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 财务管理（入出库单审核）
 * @name FinanceApproveAction.java
 * @author Frank
 * @time 2015-6-28下午9:45:29
 * @version 1.0
 */
public class FinanceApproveAction extends BaseAction {

	private static final long serialVersionUID = -5466092919455085173L;
	private static final Logger log = LogManager.getLogger(FinanceApproveAction.class);
	
	private WarehouserptService warehouserptService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	//页面显示的订单数据列表
	private List<WarehouserptDto> warehouserptList;
	
	//订单主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//日期起
	private String strWarehousedateLow;
	//日期终
	private String strWarehousedateHigh;
	private String strSuppliername;
	
	//更新状态
	private String updWarehouserptId;
	private String updWarehouserptStatus;
	
	private String strBillno1;
	private String strBillno2;
	private String strBillno3;
	private String strReceiptdate1;
	private String strReceiptdate2;
	private String strReceiptdate3;
	private String strBillamount1;
	private String strBillamount2;
	private String strBillamount3;
	
	//入库单
	/**
	 * 更新入库单状态（输入发票号）
	 * @return
	 */
	public String updFinanceInStatusBillnoAction() {
		try {
			this.clearMessages();
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			
			//发票号
			String res10 = "";
			//开票日期
			String receiptdate = "";
			//开票金额
			String billamount = "";
			
			if(StringUtil.isNotBlank(strBillno1)) {
				res10 += strBillno1 + ";";
				receiptdate += strReceiptdate1 + ";";
				billamount += strBillamount1 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate1 = "";
				strBillamount1 = "";
			}
			if(StringUtil.isNotBlank(strBillno2)) {
				res10 += strBillno2 + ";";
				receiptdate += strReceiptdate2 + ";";
				billamount += strBillamount2 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate2 = "";
				strBillamount2 = "";
			}
			if(StringUtil.isNotBlank(strBillno3)) {
				res10 += strBillno3 + ";";
				receiptdate += strReceiptdate3 + ";";
				billamount += strBillamount3 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate3 = "";
				strBillamount3 = "";
			}
			if(StringUtil.isNotBlank(receiptdate)) {
				receiptdate = receiptdate + "&&" + billamount;
			}
			
			//更新数据，带发票号
			warehouserptService.approveWarehouserpt(updWarehouserptId, username, res10, receiptdate, updWarehouserptStatus);
			//刷新页面
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("updFinanceInStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新入库单状态
	 * @return
	 */
	public String updFinanceInStatusAction() {
		try {
			this.clearMessages();
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			warehouserptService.approveWarehouserpt(updWarehouserptId, username, updWarehouserptStatus);
			//刷新页面
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("updFinanceInStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示财务管理（入库单）
	 * @return
	 */
	public String showFinanceInAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strWarehousedateLow = "";
			strWarehousedateHigh = "";
			updWarehouserptId = "";
			updWarehouserptStatus = "";
			strSuppliername = "";
			warehouserptList = new ArrayList<WarehouserptDto>();
			
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("showFinanceInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询入库单
	 * @return
	 */
	public String queryFinanceInAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("queryFinanceInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（入库单）
	 * @return
	 */
	public String turnFinanceInAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("turnFinanceInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//出库单
	/**
	 * 更新出库单状态（输入发票号）
	 * @return
	 */
	public String updFinanceOutStatusBillnoAction() {
		try {
			this.clearMessages();
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			
			//发票号
			String res10 = "";
			//开票日期
			String receiptdate = "";
			//开票金额
			String billamount = "";
			
			if(StringUtil.isNotBlank(strBillno1)) {
				res10 += strBillno1 + ";";
				receiptdate += strReceiptdate1 + ";";
				billamount += strBillamount1 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate1 = "";
				strBillamount1 = "";
			}
			if(StringUtil.isNotBlank(strBillno2)) {
				res10 += strBillno2 + ";";
				receiptdate += strReceiptdate2 + ";";
				billamount += strBillamount2 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate2 = "";
				strBillamount2 = "";
			}
			if(StringUtil.isNotBlank(strBillno3)) {
				res10 += strBillno3 + ";";
				receiptdate += strReceiptdate3 + ";";
				billamount += strBillamount3 + ";";
			} else {
				//发票为空，则清空金额和开票日期
				strReceiptdate3 = "";
				strBillamount3 = "";
			}
			if(StringUtil.isNotBlank(receiptdate)) {
				receiptdate = receiptdate + "&&" + billamount;
			}
			
			//更新数据，带发票号
			warehouserptService.approveWarehouserpt(updWarehouserptId, username, res10, receiptdate, updWarehouserptStatus);
			//刷新页面
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("updFinanceOutStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新出库单状态
	 * @return
	 */
	public String updFinanceOutStatusAction() {
		try {
			this.clearMessages();
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			warehouserptService.approveWarehouserpt(updWarehouserptId, username, updWarehouserptStatus);
			//刷新页面
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("updFinanceOutStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示财务管理（出库单）
	 * @return
	 */
	public String showFinanceOutAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strWarehousedateLow = "";
			strWarehousedateHigh = "";
			strSuppliername = "";
			warehouserptList = new ArrayList<WarehouserptDto>();
			
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("showFinanceOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询出库单
	 * @return
	 */
	public String queryFinanceOutAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("queryFinanceOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（出库单）
	 * @return
	 */
	public String turnFinanceOutAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("turnFinanceOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		updWarehouserptId = "";
		updWarehouserptStatus = "";
		//订单主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData(String type) {
		strBillno1 = "";
		strBillno2 = "";
		strBillno3 = "";
		strReceiptdate1 = "";
		strReceiptdate2 = "";
		strReceiptdate3 = "";
		strBillamount1 = "";
		strBillamount2 = "";
		strBillamount3 = "";
		initDictList();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = warehouserptService.queryWarehouserptByPage("", "", type, "", "", "", "", "",
				strWarehousedateLow, strWarehousedateHigh, strSuppliername,"", "", "", page);
		warehouserptList = (List<WarehouserptDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public List<Dict01Dto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Dict01Dto> goodsList) {
		this.goodsList = goodsList;
	}

	public List<Dict01Dto> getColorList() {
		return colorList;
	}

	public void setColorList(List<Dict01Dto> colorList) {
		this.colorList = colorList;
	}

	public List<Dict01Dto> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<Dict01Dto> unitList) {
		this.unitList = unitList;
	}

	public List<Dict01Dto> getMakeareaList() {
		return makeareaList;
	}

	public void setMakeareaList(List<Dict01Dto> makeareaList) {
		this.makeareaList = makeareaList;
	}

	public WarehouserptService getWarehouserptService() {
		return warehouserptService;
	}

	public void setWarehouserptService(WarehouserptService warehouserptService) {
		this.warehouserptService = warehouserptService;
	}

	public List<WarehouserptDto> getWarehouserptList() {
		return warehouserptList;
	}

	public void setWarehouserptList(List<WarehouserptDto> warehouserptList) {
		this.warehouserptList = warehouserptList;
	}

	public String getUpdWarehouserptId() {
		return updWarehouserptId;
	}

	public void setUpdWarehouserptId(String updWarehouserptId) {
		this.updWarehouserptId = updWarehouserptId;
	}

	public String getUpdWarehouserptStatus() {
		return updWarehouserptStatus;
	}

	public void setUpdWarehouserptStatus(String updWarehouserptStatus) {
		this.updWarehouserptStatus = updWarehouserptStatus;
	}

	public String getStrWarehousedateLow() {
		return strWarehousedateLow;
	}

	public void setStrWarehousedateLow(String strWarehousedateLow) {
		this.strWarehousedateLow = strWarehousedateLow;
	}

	public String getStrWarehousedateHigh() {
		return strWarehousedateHigh;
	}

	public void setStrWarehousedateHigh(String strWarehousedateHigh) {
		this.strWarehousedateHigh = strWarehousedateHigh;
	}

	public String getStrBillno1() {
		return strBillno1;
	}

	public void setStrBillno1(String strBillno1) {
		this.strBillno1 = strBillno1;
	}

	public String getStrBillno2() {
		return strBillno2;
	}

	public void setStrBillno2(String strBillno2) {
		this.strBillno2 = strBillno2;
	}

	public String getStrBillno3() {
		return strBillno3;
	}

	public void setStrBillno3(String strBillno3) {
		this.strBillno3 = strBillno3;
	}

	public String getStrReceiptdate1() {
		return strReceiptdate1;
	}

	public void setStrReceiptdate1(String strReceiptdate1) {
		this.strReceiptdate1 = strReceiptdate1;
	}

	public String getStrReceiptdate2() {
		return strReceiptdate2;
	}

	public void setStrReceiptdate2(String strReceiptdate2) {
		this.strReceiptdate2 = strReceiptdate2;
	}

	public String getStrReceiptdate3() {
		return strReceiptdate3;
	}

	public void setStrReceiptdate3(String strReceiptdate3) {
		this.strReceiptdate3 = strReceiptdate3;
	}

	public String getStrBillamount1() {
		return strBillamount1;
	}

	public void setStrBillamount1(String strBillamount1) {
		this.strBillamount1 = strBillamount1;
	}

	public String getStrBillamount2() {
		return strBillamount2;
	}

	public void setStrBillamount2(String strBillamount2) {
		this.strBillamount2 = strBillamount2;
	}

	public String getStrBillamount3() {
		return strBillamount3;
	}

	public void setStrBillamount3(String strBillamount3) {
		this.strBillamount3 = strBillamount3;
	}

	public String getStrSuppliername() {
		return strSuppliername;
	}

	public void setStrSuppliername(String strSuppliername) {
		this.strSuppliername = strSuppliername;
	}
}
