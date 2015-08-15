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
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PurchaseService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 财务管理（采购单）
 * @name FinancePurchaseAction.java
 * @author Frank
 * @time 2015-6-28下午9:45:29
 * @version 1.0
 */
public class FinancePurchaseAction extends BaseAction {

	private static final long serialVersionUID = -5466092919455085173L;
	private static final Logger log = LogManager.getLogger(FinancePurchaseAction.class);
	
	private PurchaseService purchaseService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	//页面显示的采购数据列表
	private List<PurchaseDto> purchaseList;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//采购日期起
	private String strPurchasedateLow;
	//采购日期终
	private String strPurchasedateHigh;
	
	//更新状态
	private String updPurchaseId;
	private String updPurchaseStatus;
	
	/**
	 * 更新采购单状态
	 * @return
	 */
	public String updFinancePurchaseStatusAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			purchaseService.updateFinancePurchase(updPurchaseId, username, updPurchaseStatus);
			//刷新页面
			queryData();
		} catch(Exception e) {
			log.error("updFinancePurchaseStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示采购单管理（财务管理）
	 * @return
	 */
	public String showFinancePurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strPurchasedateLow = "";
			strPurchasedateHigh = "";
			purchaseList = new ArrayList<PurchaseDto>();
		} catch(Exception e) {
			log.error("showFinancePurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询采购单
	 * @return
	 */
	public String queryFinancePurchaseAction() {
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
			log.error("queryFinancePurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnFinancePurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnFinancePurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//采购主题
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
		page = purchaseService.queryFinancePurchaseByPage(strPurchasedateLow, strPurchasedateHigh, "" + Constants.PURCHASE_STATUS_WAREHOUSE_OK, page);
		purchaseList = (List<PurchaseDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
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

	public List<PurchaseDto> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseDto> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public String getStrPurchasedateLow() {
		return strPurchasedateLow;
	}

	public void setStrPurchasedateLow(String strPurchasedateLow) {
		this.strPurchasedateLow = strPurchasedateLow;
	}

	public String getStrPurchasedateHigh() {
		return strPurchasedateHigh;
	}

	public void setStrPurchasedateHigh(String strPurchasedateHigh) {
		this.strPurchasedateHigh = strPurchasedateHigh;
	}

	public String getUpdPurchaseId() {
		return updPurchaseId;
	}

	public void setUpdPurchaseId(String updPurchaseId) {
		this.updPurchaseId = updPurchaseId;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
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

	public String getUpdPurchaseStatus() {
		return updPurchaseStatus;
	}

	public void setUpdPurchaseStatus(String updPurchaseStatus) {
		this.updPurchaseStatus = updPurchaseStatus;
	}
}
