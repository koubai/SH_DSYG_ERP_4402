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
import com.cn.dsyg.dto.WarehouseOkDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.WarehouseService;

/**
 * 仓位确认
 * @name ProductOkAction.java
 * @author Frank
 * @time 2015-6-29下午9:58:03
 * @version 1.0
 */
public class ProductOkAction extends BaseAction {
	
	private static final long serialVersionUID = 5575940119895001662L;

	private static final Logger log = LogManager.getLogger(ProductOkAction.class);
	
	private WarehouseService warehouseService;
	private Dict01Service dict01Service;

	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	private List<WarehouseOkDto> warehouseOkList;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//查询条件
	//主题
	private String strTheme;
	//品名
	private String strTradename;
	//规格
	private String strTypeno;
	//颜色
	private String strColor;
	//仓库名
	private String strWarehousename;
	//产品id
	private String strProdoctid;
		
	/**
	 * 显示仓位确认页面
	 * @return
	 */
	public String showProductOkAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			warehouseOkList = new ArrayList<WarehouseOkDto>();
			
			//初期化字典数据
			initDictList();
			
			strTheme = "";
			strTradename = "";
			strTypeno = "";
			strColor = "";
			strWarehousename = "";
			
			queryData();
		} catch(Exception e) {
			log.error("showProductOkAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品
	 * @return
	 */
	public String queryProductOkAction() {
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
			log.error("queryProductOkAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnProductOkAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnProductOkAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品流水账
	 * @return
	 */
	public String showProductBookPage() {
		try {
			this.clearMessages();
//			System.out.println("strProdoctid is: " + strProdoctid);
			warehouseOkList = warehouseService.queryProductBookByProductid(strProdoctid);
		} catch(Exception e) {
			log.error("showProductBookPage error:" + e);
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
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = warehouseService.queryWarehouseOkByPage("",
				strTheme, strTradename, strTypeno, strColor, strWarehousename, "", page);
//		page = warehouseService.queryWarehouseOkByPage("" + Constants.WAREHOUSE_TYPE_IN,
//				strTheme, strTradename, strTypeno, strColor, strWarehousename, "", page);
		warehouseOkList = (List<WarehouseOkDto>) page.getItems();
//		System.out.println(warehouseOkList.get(0).getItem10());
		this.setStartIndex(page.getStartIndex());
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

	public String getStrTheme() {
		return strTheme;
	}

	public void setStrTheme(String strTheme) {
		this.strTheme = strTheme;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
	}

	public String getStrTypeno() {
		return strTypeno;
	}

	public void setStrTypeno(String strTypeno) {
		this.strTypeno = strTypeno;
	}

	public String getStrColor() {
		return strColor;
	}

	public void setStrColor(String strColor) {
		this.strColor = strColor;
	}

	public List<WarehouseOkDto> getWarehouseOkList() {
		return warehouseOkList;
	}

	public void setWarehouseOkList(List<WarehouseOkDto> warehouseOkList) {
		this.warehouseOkList = warehouseOkList;
	}

	public WarehouseService getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
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

	public String getStrWarehousename() {
		return strWarehousename;
	}

	public void setStrWarehousename(String strWarehousename) {
		this.strWarehousename = strWarehousename;
	}

	public String getStrProdoctid() {
		return strProdoctid;
	}

	public void setStrProdoctid(String strProdoctid) {
		this.strProdoctid = strProdoctid;
	}
}
