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
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.service.DetailService;
import com.cn.dsyg.service.Dict01Service;

/**
 * 详细查询Action
 * @author 
 * @time 
 * @version 1.0
 */
public class DetailAction extends BaseAction {

	private static final long serialVersionUID = 1545347919543119308L;

	private static final Logger log = LogManager.getLogger(DetailAction.class);
	
	private DetailService detailService;
	private Dict01Service dict01Service;
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//产地
	private List<Dict01Dto> makeareaList;

	//页码
	private int startIndex;
	private int startIndexProduct;
	//翻页page
	private Page page;
	private Page pageProduct;
	//一页显示数据条数
	private Integer intPageSize;
	private Integer intPageSizeProduct;
	//页面显示的列表
	private List<SalesDto> listSales;
	private List<ProductDto> listProduct;
	
	private String strProductid;
	private String strCustomerid;

	private String customername;
	private String productname;
	private String productdetail;
	private String tab;

	/**
	 * 显示产品到客户页面
	 * @return
	 */
	public String showDetailAction() {
		try {
			this.clearMessages();
			strProductid = "";
			productname = "";
			productdetail = "";
			strCustomerid = "";
			customername = "";

			//默认10条
			intPageSize = 10;
			intPageSizeProduct = 10;
			page = new Page(intPageSize);
			pageProduct = new Page(intPageSizeProduct);
			
			startIndex = 0;
			startIndexProduct = 0;
			listSales = new ArrayList<SalesDto>();
			listProduct = new ArrayList<ProductDto>();
			tab = "0";
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户列表
	 * @return
	 */
	public String queryDetailCustomerList() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryDetailCustomer();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnDetailCustomerPage() {
		try {
			this.clearMessages();
			queryDetailCustomer();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有客户列表
	 */
	@SuppressWarnings("unchecked")
	private void queryDetailCustomer() {
		listSales = new ArrayList<SalesDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//初期化字典数据
		initDictList();
		//翻页查询所有员工档案
		this.page.setStartIndex(startIndex);
		page = detailService.queryDetailCustomerByPage(page, strProductid);
		listSales = (List<SalesDto>) page.getItems();
		tab = "0";
		this.setStartIndex(page.getStartIndex());
	}

	
	/**
	 * 查询产品列表
	 * @return
	 */
	public String queryDetailProductList() {
		try {
			this.clearMessages();
			startIndexProduct = 0;
			//默认10条
			if(intPageSizeProduct == null) {
				intPageSizeProduct = 10;
			}
			pageProduct = new Page(intPageSizeProduct);
			queryDetailProduct();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnDetailProductPage() {
		try {
			this.clearMessages();
			queryDetailProduct();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有产品列表
	 */
	@SuppressWarnings("unchecked")
	private void queryDetailProduct() {
		listProduct = new ArrayList<ProductDto>();
		if(pageProduct == null) {
			pageProduct = new Page(intPageSizeProduct);
		}
		//初期化字典数据
		initDictList();
		//翻页查询所有员工档案
		this.pageProduct.setStartIndex(startIndexProduct);
		pageProduct = detailService.queryDetailProductByPage(pageProduct, strCustomerid);
		listProduct = (List<ProductDto>) pageProduct.getItems();
		tab = "1";
		this.setStartIndexProduct(pageProduct.getStartIndex());
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//采购主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}

	public DetailService getDetailService() {
		return detailService;
	}

	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}


	public int getStartIndexProduct() {
		return startIndexProduct;
	}

	public void setStartIndexProduct(int startIndexProduct) {
		this.startIndexProduct = startIndexProduct;
	}

	public Page getPageProduct() {
		return pageProduct;
	}

	public void setPageProduct(Page pageProduct) {
		this.pageProduct = pageProduct;
	}

	public List<ProductDto> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductDto> listProduct) {
		this.listProduct = listProduct;
	}

	public String getStrProductid() {
		return strProductid;
	}

	public void setStrProductid(String strProductid) {
		this.strProductid = strProductid;
	}
	
	public String getStrCustomerid() {
		return strCustomerid;
	}

	public void setStrCustomerid(String strCustomerid) {
		this.strCustomerid = strCustomerid;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getIntPageSizeProduct() {
		return intPageSizeProduct;
	}

	public void setIntPageSizeProduct(Integer intPageSizeProduct) {
		this.intPageSizeProduct = intPageSizeProduct;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<SalesDto> getListSales() {
		return listSales;
	}

	public void setListSales(List<SalesDto> listSales) {
		this.listSales = listSales;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductdetail() {
		return productdetail;
	}

	public void setProductdetail(String productdetail) {
		this.productdetail = productdetail;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
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

	public List<Dict01Dto> getMakeareaList() {
		return makeareaList;
	}

	public void setMakeareaList(List<Dict01Dto> makeareaList) {
		this.makeareaList = makeareaList;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}
}
