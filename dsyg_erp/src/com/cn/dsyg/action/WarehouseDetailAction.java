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
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.dto.WarehouseDetailDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PurchaseItemService;
import com.cn.dsyg.service.SalesItemService;
import com.cn.dsyg.service.WarehouseService;

/**
 * 
 * @name 产品对比表
 * @author lql
 *
 */
public class WarehouseDetailAction extends BaseAction {

	private static final long serialVersionUID = 1249927531756025036L;

	private static final Logger log = LogManager.getLogger(WarehouseDetailAction.class);
	
	private Dict01Service dict01Service;
	private WarehouseService warehouseService;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	private List<WarehouseDetailDto> warehouseDetailList;

	private String strTheme;
	private String strKeyword;

	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//采购单一览用
	private String strProdoctid;
	private List<PurchaseItemDto> purchaseItemList;
	private PurchaseItemService purchaseItemService;

	//销售单一览用
	private List<SalesItemDto> salesItemList;
	private SalesItemService salesItemService;
	
	//POPUT START
	//订单、采购单新增和修改页面，产品对照
	/**
	 * 库存盘点页面（POPUP）
	 * @return
	 */
	public String showWarehouseDetailPopupAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			strTheme = "";
			strKeyword = "";
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			warehouseDetailList = new ArrayList<WarehouseDetailDto>();
			//初期化字典数据
			initDictList();
			
			queryData();
		} catch(Exception e) {
			log.error("showWarehouseDetailPopupAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询仓库盘点（POPUP）
	 * @return
	 */
	public String queryWarehouseDetailPopupAction() {
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
			log.error("queryWarehouseDetailPopupAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（POPUP）
	 * @return
	 */
	public String turnWarehouseDetailPopupAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnWarehouseDetailPopupAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//POPUP END
	
	/**
	 * 库存盘点页面
	 * @return
	 */
	public String showWarehouseDetailAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			strTheme = "";
			strKeyword = "";
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			warehouseDetailList = new ArrayList<WarehouseDetailDto>();
			//初期化字典数据
			initDictList();
			
			queryData();
		} catch(Exception e) {
			log.error("showWarehouseDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询仓库盘点
	 * @return
	 */
	public String queryWarehouseDetailAction() {
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
			log.error("queryWarehouseDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnWarehouseDetailAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnWarehouseDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询采购单
	 * @return
	 */
	public String showProductPurchasePage() {
		try {
			this.clearMessages();
//			System.out.println("strProdoctid is: " + strProdoctid);
			//update by frank
			setPurchaseItemList(purchaseItemService.queryPurchaseItemByProductidForCompare(strProdoctid, ""));
			//setPurchaseItemList(purchaseItemService.queryPurchaseItemByProductid(strProdoctid, "", 0, 100));
			
		} catch(Exception e) {
			log.error("queryPurchaseItemByProductid error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询销售单
	 * @return
	 */
	public String showProductSalesPage() {
		try {
			this.clearMessages();
//			System.out.println("strProdoctid is: " + strProdoctid);
			setSalesItemList(salesItemService.querySalesItemByProductidForCompare(strProdoctid, ""));
			//setSalesItemList(salesItemService.querySalesItemByProductid(strProdoctid, "", 0, 100));
			
		} catch(Exception e) {
			log.error("querySalesItemByProductid error:" + e);
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
		//初期化字典数据
		initDictList();
		//翻页查询所有入库汇总记录
		this.page.setStartIndex(startIndex);
		page = warehouseService.queryWarehouseDetailByPage("", strKeyword,
				"", "", strTheme, "", "", "", "", "", page);
		warehouseDetailList = (List<WarehouseDetailDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//税率
		List<Dict01Dto> listRate = dict01Service.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listRate != null && listRate.size() > 0) {
			common_rate = listRate.get(0).getCode();
		}
		//采购主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public WarehouseService getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
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

	public String getStrTheme() {
		return strTheme;
	}

	public void setStrTheme(String strTheme) {
		this.strTheme = strTheme;
	}
	
	public String getStrKeyword() {
		return strKeyword;
	}

	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}

	public List<WarehouseDetailDto> getWarehouseDetailList() {
		return warehouseDetailList;
	}

	public void setWarehouseDetailList(List<WarehouseDetailDto> warehouseDetailList) {
		this.warehouseDetailList = warehouseDetailList;
	}

	public String getStrProdoctid() {
		return strProdoctid;
	}

	public void setStrProdoctid(String strProdoctid) {
		this.strProdoctid = strProdoctid;
	}

	public List<PurchaseItemDto> getPurchaseItemList() {
		return purchaseItemList;
	}

	public void setPurchaseItemList(List<PurchaseItemDto> purchaseItemList) {
		this.purchaseItemList = purchaseItemList;
	}

	public List<SalesItemDto> getSalesItemList() {
		return salesItemList;
	}

	public void setSalesItemList(List<SalesItemDto> salesItemList) {
		this.salesItemList = salesItemList;
	}
	public PurchaseItemService getPurchaseItemService() {
		return purchaseItemService;
	}

	public void setPurchaseItemService(PurchaseItemService purchaseItemService) {
		this.purchaseItemService = purchaseItemService;
	}

	public SalesItemService getSalesItemService() {
		return salesItemService;
	}

	public void setSalesItemService(SalesItemService salesItemService) {
		this.salesItemService = salesItemService;
	}

}
