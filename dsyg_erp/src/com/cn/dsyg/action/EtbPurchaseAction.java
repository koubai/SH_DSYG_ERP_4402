package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Page;
import com.cn.dsyg.dto.EtbPurchaseDto;
import com.cn.dsyg.service.EtbPurchaseService;

/**
 * 采购单管理Action
 * @author Frank
 * @time 2015-5-7下午10:29:11
 * @version 1.0
 */
public class EtbPurchaseAction extends BaseAction {
	
	private static final long serialVersionUID = -1441939555094967866L;
	private static final Logger log = LogManager.getLogger(FrameAction.class);
	private EtbPurchaseService etbPurchaseService;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的采购数据列表
	private List<EtbPurchaseDto> etbPurchaseList;
	
	//查询条件
	//采购日期起
	private String strPurchasedateLow;
	//采购日期终
	private String strPurchasedateHigh;

	/**
	 * 显示采购管理页面
	 * @return
	 */
	public String showEtbPurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strPurchasedateLow = "";
			strPurchasedateHigh = "";
			etbPurchaseList = new ArrayList<EtbPurchaseDto>();
		} catch(Exception e) {
			log.error("showEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询采购
	 * @return
	 */
	public String queryEtbPurchaseAction() {
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
			log.error("queryEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnEtbPurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = etbPurchaseService.queryEtbPurchaseByPage(strPurchasedateLow, strPurchasedateHigh, page);
		etbPurchaseList = (List<EtbPurchaseDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public EtbPurchaseService getEtbPurchaseService() {
		return etbPurchaseService;
	}

	public void setEtbPurchaseService(EtbPurchaseService etbPurchaseService) {
		this.etbPurchaseService = etbPurchaseService;
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

	public List<EtbPurchaseDto> getEtbPurchaseList() {
		return etbPurchaseList;
	}

	public void setEtbPurchaseList(List<EtbPurchaseDto> etbPurchaseList) {
		this.etbPurchaseList = etbPurchaseList;
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
}
