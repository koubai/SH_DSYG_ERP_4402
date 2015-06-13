package com.cn.dsyg.action;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Page;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PurchaseItemService;
import com.cn.dsyg.service.PurchaseService;
import com.cn.dsyg.service.WarehouserptService;

/**
 * @name WarehouserptAction.java
 * @author Frank
 * @time 2015-6-3下午9:58:32
 * @version 1.0
 */
public class WarehouserptAction extends BaseAction {

	private static final long serialVersionUID = -8930942457017818387L;
	private static final Logger log = LogManager.getLogger(WarehouserptAction.class);
	
	private WarehouserptService warehouserptService;
	private PurchaseService purchaseService;
	private PurchaseItemService purchaseItemService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的采购数据列表
	private List<WarehouseOkDto> purchaseItemOkList;
	
	//采购单号
	private String strPurchaseno;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	/**
	 * 显示库存确认页面
	 * @return
	 */
	public String showWarehouseOk() {
		try {
			this.clearMessages();
		} catch(Exception e) {
			log.error("showWarehouseOk error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 库存确认
	 * @return
	 */
	public String warehouseOk() {
		try {
			this.clearMessages();
		} catch(Exception e) {
			log.error("warehouseOk error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	public WarehouserptService getWarehouserptService() {
		return warehouserptService;
	}

	public void setWarehouserptService(WarehouserptService warehouserptService) {
		this.warehouserptService = warehouserptService;
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public PurchaseItemService getPurchaseItemService() {
		return purchaseItemService;
	}

	public void setPurchaseItemService(PurchaseItemService purchaseItemService) {
		this.purchaseItemService = purchaseItemService;
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

	public String getStrPurchaseno() {
		return strPurchaseno;
	}

	public void setStrPurchaseno(String strPurchaseno) {
		this.strPurchaseno = strPurchaseno;
	}

	public List<WarehouseOkDto> getPurchaseItemOkList() {
		return purchaseItemOkList;
	}

	public void setPurchaseItemOkList(List<WarehouseOkDto> purchaseItemOkList) {
		this.purchaseItemOkList = purchaseItemOkList;
	}
}
