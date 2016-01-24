package com.cn.dsyg.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.WarehouseProductDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.WarehouseService;

/**
 * WarehouseAction
 * @Company 盛大游戏
 * @author chenguangquan.frank
 * @version 1.0
 * @create 2015-6-19下午4:58:11
 */
public class WarehouseAction extends BaseAction {

	private static final long serialVersionUID = -7502773227490023518L;
	private static final Logger log = LogManager.getLogger(WarehouseAction.class);
	
	private WarehouseService warehouseService;
	private Dict01Service dict01Service;

	private String strFieldno;
	private String strTradename;
	private String strTypeno;
	private String strColor;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	private List<WarehouseProductDto> warehouseProductList;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//验证产品数量
	//信息格式：productid,productnum#productid,productnum...
	private String productInfo;
	
	/**
	 * 验证产品数量
	 * @return
	 * @throws IOException 
	 */
	public String checkProductAmountAction() throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		String result = "";
		String msg = "";
		try {
			msg = warehouseService.checkProductAmount(productInfo);
		} catch (Exception e) {
			log.error(e);
			msg = "在验证产品数量过程中产生系统异常！请联系管理员。";
		}
		out = response.getWriter();
		result = "{\"msg\":\"" + msg + "\"}";
		log.info("checkProductAmount result=" + result);
		out.write(result);
		out.flush();
		return null;
	}
	
	/**
	 * 显示库存产品页面
	 * @return
	 */
	public String showWarehouseProductSelectAction() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showWarehouseProductSelectAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询库存产品页面
	 * @return
	 */
	public String queryWarehouseProductSelectAction() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryWarehouseProductSelectAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnWarehouseProductSelectAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnWarehouseProductSelectAction error:" + e);
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
		page = warehouseService.queryWarehouseProductByPage("", "",
				"", strFieldno, "", strTradename, strTypeno, strColor, "", page);
		warehouseProductList = (List<WarehouseProductDto>) page.getItems();
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

	public WarehouseService getWarehouseService() {
		return warehouseService;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public String getStrTypeno() {
		return strTypeno;
	}

	public String getStrColor() {
		return strColor;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public Page getPage() {
		return page;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public List<Dict01Dto> getGoodsList() {
		return goodsList;
	}

	public List<Dict01Dto> getColorList() {
		return colorList;
	}

	public List<Dict01Dto> getUnitList() {
		return unitList;
	}

	public List<Dict01Dto> getMakeareaList() {
		return makeareaList;
	}

	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
	}

	public void setStrTypeno(String strTypeno) {
		this.strTypeno = strTypeno;
	}

	public void setStrColor(String strColor) {
		this.strColor = strColor;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public void setGoodsList(List<Dict01Dto> goodsList) {
		this.goodsList = goodsList;
	}

	public void setColorList(List<Dict01Dto> colorList) {
		this.colorList = colorList;
	}

	public void setUnitList(List<Dict01Dto> unitList) {
		this.unitList = unitList;
	}

	public void setMakeareaList(List<Dict01Dto> makeareaList) {
		this.makeareaList = makeareaList;
	}

	public List<WarehouseProductDto> getWarehouseProductList() {
		return warehouseProductList;
	}

	public void setWarehouseProductList(
			List<WarehouseProductDto> warehouseProductList) {
		this.warehouseProductList = warehouseProductList;
	}

	public String getStrFieldno() {
		return strFieldno;
	}

	public void setStrFieldno(String strFieldno) {
		this.strFieldno = strFieldno;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
}
