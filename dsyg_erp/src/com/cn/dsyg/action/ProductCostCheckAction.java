package com.cn.dsyg.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.factory.Poi2007Base;
import com.cn.common.factory.PoiFactory;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.ProductCostDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.ProductService;
import com.cn.dsyg.service.WarehouseService;

/**
 * @name 产品成本
 * @author Pei
 * @time 2015-8-28下午2:56:49
 * @version 1.0
 */
public class ProductCostCheckAction extends BaseAction {

	private static final long serialVersionUID = 2297367871683739005L;

	private static final Logger log = LogManager.getLogger(ProductCostCheckAction.class);
	
	private Dict01Service dict01Service;
	private ProductService productService;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	private List<ProductCostDto> productCostCheckList;
	
	private String strTheme;
	private String strTradename;
	private String strItem10;//包装
	private String strKeyword;

	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//excel密码
	private String excelPass;
	
	/**
	 * 产品成本页面
	 * @return
	 */
	public String showProductCostCheckAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			strTheme = "";
			strTradename = "";
			strKeyword = "";
			strItem10 = "";
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			productCostCheckList = new ArrayList<ProductCostDto>();
			//初期化字典数据
			initDictList();
			queryProductCostCheckAction();
		} catch(Exception e) {
			log.error("showProductCostCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品成本
	 * @return
	 */
	public String queryProductCostCheckAction() {
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
			log.error("queryProductCostCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnProductCostCheckAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnProductCostCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出数据
	 * @return
	 */
	public String exportProductCostAction() {
		try {
			this.clearMessages();
			exportData("" + Constants.EXCEL_TYPE_PRODUCT_COST);
		} catch(Exception e) {
			log.error("exportProductCostAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出一览数据
	 * @param type
	 * @throws IOException 
	 */
	private void exportData(String type) throws IOException {
		initDictList();
		//字典数据组织个MAP
		Map<String, String> dictMap = new HashMap<String, String>();
		if(goodsList != null && goodsList.size() > 0) {
			for(Dict01Dto dict : goodsList) {
				dictMap.put(Constants.DICT_GOODS_TYPE + "_" + dict.getCode(), dict.getFieldname());
			}
		}
		if(unitList != null && unitList.size() > 0) {
			for(Dict01Dto dict : unitList) {
				dictMap.put(Constants.DICT_UNIT_TYPE + "_" + dict.getCode(), dict.getFieldname());
			}
		}
		if(makeareaList != null && makeareaList.size() > 0) {
			for(Dict01Dto dict : makeareaList) {
				dictMap.put(Constants.DICT_MAKEAREA + "_" + dict.getCode(), dict.getFieldname());
			}
		}
		if(colorList != null && colorList.size() > 0) {
			for(Dict01Dto dict : colorList) {
				dictMap.put(Constants.DICT_COLOR_TYPE + "_" + dict.getCode(), dict.getFieldname());
			}
		}
		dictMap.put(Constants.EXCEL_PASS, excelPass);
		String name = StringUtil.createFileName(type);
		response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		Poi2007Base base = PoiFactory.getPoi(type);
		//查询所有数据
		List<ProductDto> list = productService.queryProductCostToExport(strTheme, "", "", strTradename, "", "", "", "", "");
		
		base.setDatas(list);
		base.setSheetName(type);
		base.setDictMap(dictMap);
		base.exportExcel(response.getOutputStream());
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
		//翻页查询所有 产品总记录
		this.page.setStartIndex(startIndex);
		
		page = productService.queryProductCostCheckByPage(strTheme, strItem10,
				strKeyword,strTradename, "", "", "", "", "" + Constants.STATUS_NORMAL, page);
//		String fieldno, String item01, String keyword, String tradename,
//		String typeno, String color, String supplierId, String belongto, "" + Constants.STATUS_NORMAL, page
		productCostCheckList = (List<ProductCostDto>) page.getItems();
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
		//excel密码
		List<Dict01Dto> listPass = dict01Service.queryDict01ByFieldcode(Constants.EXCEL_PASS, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listPass != null && listPass.size() > 0) {
			excelPass = listPass.get(0).getCode();
		}
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public List<ProductCostDto> getProductCostCheckList() {
		return productCostCheckList;
	}

	public void setProductCostCheckList(List<ProductCostDto> productCostCheckList) {
		this.productCostCheckList = productCostCheckList;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
	}

	public String getExcelPass() {
		return excelPass;
	}

	public void setExcelPass(String excelPass) {
		this.excelPass = excelPass;
	}

	public String getStrItem10() {
		return strItem10;
	}

	public void setStrItem10(String strItem10) {
		this.strItem10 = strItem10;
	}

	public String getStrKeyword() {
		return strKeyword;
	}

	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}

}
