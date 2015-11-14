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
import com.cn.dsyg.dto.WarehouseDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.WarehouseService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name WarehouseRefundAction.java
 * @author Frank
 * @time 2015-7-28下午9:15:49
 * @version 1.0
 */
public class WarehouseRefundAction extends BaseAction {

	private static final long serialVersionUID = -1776911930337556664L;
	private static final Logger log = LogManager.getLogger(WarehouseRefundAction.class);
	
	private Dict01Service dict01Service;
	private WarehouseService warehouseService;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	private List<WarehouseDto> warehouseList;
	
	private String strTheme;
	
	//新增
	private WarehouseDto addWarehouseDto;
	
	//修改
	private WarehouseDto updWarehouseDto;
	private String updWarehouseid;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	/**
	 * 显示修改记录页面
	 * @return
	 */
	public String showUpdWarehouseRefundAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updWarehouseDto = warehouseService.queryWarehouseByID(updWarehouseid);
		} catch(Exception e) {
			log.error("showUpdWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改记录
	 * @return
	 */
	public String updWarehouseRefundAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(updWarehouseDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updWarehouseDto.setUpdateuid(username);
			warehouseService.updateWarehouse(updWarehouseDto);
			
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增记录页面
	 * @return
	 */
	public String showAddWarehouseRefundAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			addWarehouseDto = new WarehouseDto();
		} catch(Exception e) {
			log.error("showAddWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增记录
	 * @return
	 */
	public String addWarehouseRefundAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addWarehouseDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addWarehouseDto.setCreateuid(username);
			addWarehouseDto.setUpdateuid(username);
			addWarehouseDto.setBelongto((String) ActionContext.getContext().getSession().get(Constants.SESSION_BELONGTO));
			String warehouseno = warehouseService.insertRefundWarehouse(addWarehouseDto);
			
			this.addActionMessage("添加成功！库存单号：" + warehouseno);
			addWarehouseDto = new WarehouseDto();
		} catch(Exception e) {
			log.error("addWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示库存退换货列表
	 * @return
	 */
	public String showWarehouseRefundAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			initDictList();
			startIndex = 0;
			strTheme = "";
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			warehouseList = new ArrayList<WarehouseDto>();
			updWarehouseid = "";
			
			queryData();
		} catch(Exception e) {
			log.error("showWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询库存退换货记录
	 * @return
	 */
	public String queryWarehouseRefundAction() {
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
			log.error("queryWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turmWarehouseRefundAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turmWarehouseRefundAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
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
		//翻页查询所有入库汇总记录
		this.page.setStartIndex(startIndex);
		page = warehouseService.queryWarehouseRefundByPage("", strTheme, "", page);
		warehouseList = (List<WarehouseDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 验证数据
	 * @param warehouse
	 * @return
	 */
	private boolean checkData(WarehouseDto warehouse) {
		if(warehouse == null) {
			this.addActionMessage("请选择类型！");
			return false;
		}
//		if(warehouse.getTheme1() == null) {
//			this.addActionMessage("主题不能为空！");
//			return false;
//		}
		if(warehouse.getWarehousetype() == null) {
			this.addActionMessage("请选择类型！");
			return false;
		}
/*		if(StringUtil.isBlank(warehouse.getWarehousename())) {
			this.addActionMessage("仓库不能为空！");
			return false;
		}
*/		
		if(StringUtil.isBlank(warehouse.getProductid())) {
			this.addActionMessage("产品不能为空！");
			return false;
		}
		if(warehouse.getQuantity() == null) {
			this.addActionMessage("入库数量不能为空！");
			return false;
		}
		return true;
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

	public List<WarehouseDto> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<WarehouseDto> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public WarehouseDto getAddWarehouseDto() {
		return addWarehouseDto;
	}

	public void setAddWarehouseDto(WarehouseDto addWarehouseDto) {
		this.addWarehouseDto = addWarehouseDto;
	}

	public WarehouseDto getUpdWarehouseDto() {
		return updWarehouseDto;
	}

	public void setUpdWarehouseDto(WarehouseDto updWarehouseDto) {
		this.updWarehouseDto = updWarehouseDto;
	}

	public String getUpdWarehouseid() {
		return updWarehouseid;
	}

	public void setUpdWarehouseid(String updWarehouseid) {
		this.updWarehouseid = updWarehouseid;
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

	public List<Dict01Dto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Dict01Dto> goodsList) {
		this.goodsList = goodsList;
	}

	public String getStrTheme() {
		return strTheme;
	}

	public void setStrTheme(String strTheme) {
		this.strTheme = strTheme;
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
}
