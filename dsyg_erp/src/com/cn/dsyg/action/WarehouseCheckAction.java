package com.cn.dsyg.action;

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
import com.cn.dsyg.dto.PositionCollectDto;
import com.cn.dsyg.dto.PositionDto;
import com.cn.dsyg.dto.WarehouseCheckDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PositionService;
import com.cn.dsyg.service.WarehouseService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name 库存盘点
 * @author Frank
 * @time 2015-7-26下午2:56:49
 * @version 1.0
 */
public class WarehouseCheckAction extends BaseAction {

	private static final long serialVersionUID = 842353236788134771L;
	private static final Logger log = LogManager.getLogger(WarehouseCheckAction.class);
	
	private Dict01Service dict01Service;
	private WarehouseService warehouseService;
	private PositionService positionService;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//盘点集集
	private List<PositionCollectDto> positionCollectList;
	//盘点日期
	private String strCheckday;
	
	//页码
	private int checkStartIndex;
	//翻页page
	private Page checkPage;
	//一页显示数据条数
	private Integer checkIntPageSize;
	private List<WarehouseCheckDto> warehouseCheckList;
	
	private String strTheme;

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
	
	//盘点的产品ID
	private String strCheckProductid;
	//盘点的库存值
	private String strCheckProductNum;
	//盘点库存位置
	private String strPosition;
	
	//盘点明细
	private String strDay;
	private String strUser;
	private List<PositionDto> positionDetailList;
	
	/**
	 * 盘点明细
	 * @return
	 */
	public String showCollectDetailAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			positionDetailList = new ArrayList<PositionDto>();
			positionDetailList = positionService.queryPositionListByLogicId(strUser, "", strDay);
		} catch(Exception e) {
			log.error("showCollectDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 盘点集集页面
	 * @return
	 */
	public String showPositionCollectAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			strCheckday = "";
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			positionCollectList = new ArrayList<PositionCollectDto>();
			warehouseCheckList = new ArrayList<WarehouseCheckDto>();
			
			queryData();
		} catch(Exception e) {
			log.error("showPositionCollectAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询盘点集集
	 * @return
	 */
	public String queryPositionCollectAction() {
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
			log.error("queryPositionCollectAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnPositionCollectAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnPositionCollectAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//
	
	/**
	 * 库存盘点页面
	 * @return
	 */
	public String showWarehouseCheckAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			checkStartIndex = 0;
			strTheme = "";
			//默认10条
			checkIntPageSize = 10;
			checkPage = new Page(checkIntPageSize);
			warehouseCheckList = new ArrayList<WarehouseCheckDto>();
			
			strDay = "";
			//初期化字典数据
			initDictList();
			queryData_check();
		} catch(Exception e) {
			log.error("showWarehouseCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询仓库盘点
	 * @return
	 */
	public String queryWarehouseCheckAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			checkStartIndex = 0;
			//默认10条
			if(checkIntPageSize == null) {
				checkIntPageSize = 10;
			}
			checkPage = new Page(checkIntPageSize);
			queryData_check();
		} catch(Exception e) {
			log.error("queryWarehouseCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnWarehouseCheckAction() {
		try {
			this.clearMessages();
			queryData_check();
		} catch(Exception e) {
			log.error("turnWarehouseCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 盘点
	 * @return
	 */
	public String checkProductQuantity() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//查询原始库存
			boolean b = warehouseService.checkProductQuantity(strCheckProductid, strCheckProductNum, strPosition, username);
			if(b) {
				this.addActionMessage("盘点成功！");
			} else {
				//没有库存数据
				this.addActionMessage("没有该产品的库存数据！");
			}
			//刷新页面
			queryData_check();
		} catch(Exception e) {
			log.error("showWarehouseCheckAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//导出盘点数据
	public String exportWarehouserCheckAction() {
		try {
			this.clearMessages();
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
			
			String name = StringUtil.createFileName(Constants.EXCEL_TYPE_WAREHOUSCHECK);
			response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			Poi2007Base base = PoiFactory.getPoi(Constants.EXCEL_TYPE_WAREHOUSCHECK);
			
			//查询所有审价履历
			List<WarehouseCheckDto> list = warehouseService.queryWarehouseCheckToExcel("", "", "", 
					strTheme, "", "", "", "", "");
			
			base.setDatas(list);
			base.setSheetName(Constants.EXCEL_TYPE_WAREHOUSCHECK);
			base.setDictMap(dictMap);
			base.exportExcel(response.getOutputStream());
		} catch(Exception e) {
			log.error("exportAuditHist error:" + e);
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
		page = positionService.queryPositionCollectByPage("", strCheckday, page);
		positionCollectList = (List<PositionCollectDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData_check() {
		if(checkPage == null) {
			checkPage = new Page(checkIntPageSize);
		}
		//初期化字典数据
		initDictList();
		//翻页查询所有入库汇总记录
		this.checkPage.setStartIndex(checkStartIndex);
		checkPage = warehouseService.queryWarehouseCheckByPage("", "",
				"", strTheme, "", "", "", "", "", checkPage);
		warehouseCheckList = (List<WarehouseCheckDto>) checkPage.getItems();
		this.setStartIndex(checkPage.getStartIndex());
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

	public List<WarehouseCheckDto> getWarehouseCheckList() {
		return warehouseCheckList;
	}

	public void setWarehouseCheckList(List<WarehouseCheckDto> warehouseCheckList) {
		this.warehouseCheckList = warehouseCheckList;
	}
	
	public String getExcelPass() {
		return excelPass;
	}

	public void setExcelPass(String excelPass) {
		this.excelPass = excelPass;
	}

	public String getStrCheckProductid() {
		return strCheckProductid;
	}

	public void setStrCheckProductid(String strCheckProductid) {
		this.strCheckProductid = strCheckProductid;
	}

	public String getStrCheckProductNum() {
		return strCheckProductNum;
	}

	public void setStrCheckProductNum(String strCheckProductNum) {
		this.strCheckProductNum = strCheckProductNum;
	}

	public String getStrPosition() {
		return strPosition;
	}

	public void setStrPosition(String strPosition) {
		this.strPosition = strPosition;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public List<PositionCollectDto> getPositionCollectList() {
		return positionCollectList;
	}

	public void setPositionCollectList(List<PositionCollectDto> positionCollectList) {
		this.positionCollectList = positionCollectList;
	}

	public String getStrCheckday() {
		return strCheckday;
	}

	public void setStrCheckday(String strCheckday) {
		this.strCheckday = strCheckday;
	}

	public int getCheckStartIndex() {
		return checkStartIndex;
	}

	public void setCheckStartIndex(int checkStartIndex) {
		this.checkStartIndex = checkStartIndex;
	}

	public Page getCheckPage() {
		return checkPage;
	}

	public void setCheckPage(Page checkPage) {
		this.checkPage = checkPage;
	}

	public Integer getCheckIntPageSize() {
		return checkIntPageSize;
	}

	public void setCheckIntPageSize(Integer checkIntPageSize) {
		this.checkIntPageSize = checkIntPageSize;
	}

	public String getStrDay() {
		return strDay;
	}

	public void setStrDay(String strDay) {
		this.strDay = strDay;
	}

	public List<PositionDto> getPositionDetailList() {
		return positionDetailList;
	}

	public void setPositionDetailList(List<PositionDto> positionDetailList) {
		this.positionDetailList = positionDetailList;
	}

	public String getStrUser() {
		return strUser;
	}

	public void setStrUser(String strUser) {
		this.strUser = strUser;
	}
}
