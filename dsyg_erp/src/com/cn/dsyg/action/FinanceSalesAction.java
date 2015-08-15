package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.SalesService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 财务管理（订单）
 * @name FinanceSalesAction.java
 * @author Frank
 * @time 2015-6-28下午9:45:29
 * @version 1.0
 */
public class FinanceSalesAction extends BaseAction {

	private static final long serialVersionUID = -5466092919455085173L;
	private static final Logger log = LogManager.getLogger(FinanceSalesAction.class);
	
	private SalesService salesService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	//页面显示的订单数据列表
	private List<SalesDto> salesList;
	
	//订单主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//销售日期起
	private String strSalesdateLow;
	//销售日期终
	private String strSalesdateHigh;
	
	//更新状态
	private String updSalesId;
	private String updSalesStatus;
	
	/**
	 * 更新订单状态
	 * @return
	 */
	public String updFinanceSalesStatusAction() {
		try {
			this.clearMessages();
			
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			salesService.updateFinanceSales(updSalesId, username, updSalesStatus);
			//刷新页面
			queryData();
		} catch(Exception e) {
			log.error("updFinanceSalesStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示订单管理（财务管理）
	 * @return
	 */
	public String showFinanceSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strSalesdateLow = "";
			strSalesdateHigh = "";
			salesList = new ArrayList<SalesDto>();
		} catch(Exception e) {
			log.error("showFinanceSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询订单
	 * @return
	 */
	public String queryFinanceSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryFinanceSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnFinanceSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnFinanceSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
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
	private void queryData() {
		initDictList();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = salesService.queryFinanceSalesByPage(strSalesdateLow, strSalesdateHigh, "" + Constants.SALES_STATUS_WAREHOUSE_OK, page);
		salesList = (List<SalesDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public SalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(SalesService salesService) {
		this.salesService = salesService;
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

	public List<SalesDto> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SalesDto> salesList) {
		this.salesList = salesList;
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

	public String getStrSalesdateLow() {
		return strSalesdateLow;
	}

	public void setStrSalesdateLow(String strSalesdateLow) {
		this.strSalesdateLow = strSalesdateLow;
	}

	public String getStrSalesdateHigh() {
		return strSalesdateHigh;
	}

	public void setStrSalesdateHigh(String strSalesdateHigh) {
		this.strSalesdateHigh = strSalesdateHigh;
	}

	public String getUpdSalesId() {
		return updSalesId;
	}

	public void setUpdSalesId(String updSalesId) {
		this.updSalesId = updSalesId;
	}

	public String getUpdSalesStatus() {
		return updSalesStatus;
	}

	public void setUpdSalesStatus(String updSalesStatus) {
		this.updSalesStatus = updSalesStatus;
	}
}
