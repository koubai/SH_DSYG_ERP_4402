package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.EtbPurchaseDto;
import com.cn.dsyg.dto.EtbPurchaseItemDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.EtbPurchaseItemService;
import com.cn.dsyg.service.EtbPurchaseService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 采购单管理Action
 * @author Frank
 * @time 2015-5-7下午10:29:11
 * @version 1.0
 */
public class EtbPurchaseAction extends BaseAction {
	
	private static final long serialVersionUID = -1441939555094967866L;
	private static final Logger log = LogManager.getLogger(EtbPurchaseAction.class);
	private EtbPurchaseService etbPurchaseService;
	private EtbPurchaseItemService etbPurchaseItemService;
	private Dict01Service dict01Service;
	
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
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
		
	//新增
	private EtbPurchaseDto addEtbPurchaseDto;
	private List<EtbPurchaseItemDto> addPurchaseItemList;
	
	//修改
	private String updEtbPurchaseId;
	private EtbPurchaseDto updEtbPurchaseDto;
	private List<EtbPurchaseItemDto> updPurchaseItemList;
	
	/**
	 * 显示更新采购单页面
	 * @return
	 */
	public String showUpdEtbPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updPurchaseItemList = new ArrayList<EtbPurchaseItemDto>();
			updEtbPurchaseDto = etbPurchaseService.queryEtbPurchaseByID(updEtbPurchaseId);
			if(updEtbPurchaseDto != null) {
				updPurchaseItemList = etbPurchaseItemService.queryPurchaseItemByPurchaseno(updEtbPurchaseDto.getPurchaseno());
			}
		} catch(Exception e) {
			log.error("showUpdEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新采购单
	 * @return
	 */
	public String updEtbPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(updEtbPurchaseDto)) {
				return "checkerror";
			}
			if(updPurchaseItemList == null || updPurchaseItemList.size() <= 0) {
				this.addActionMessage("采购单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//更新数据
			etbPurchaseService.updateEtbPurchase(updEtbPurchaseDto, updPurchaseItemList, username);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增采购页面
	 * @return
	 */
	public String showAddEtbPurchaseAction() {
		try {
			this.clearMessages();
			addEtbPurchaseDto = new EtbPurchaseDto();
			//默认为当天
			addEtbPurchaseDto.setPurchasedate(new Date());
			addPurchaseItemList = new ArrayList<EtbPurchaseItemDto>();
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showAddEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增采购
	 * @return
	 */
	public String addEtbPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addEtbPurchaseDto)) {
				return "checkerror";
			}
			if(addPurchaseItemList == null || addPurchaseItemList.size() <= 0) {
				this.addActionMessage("采购单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			String purchaseno = etbPurchaseService.addEtbPurchase(addEtbPurchaseDto, addPurchaseItemList, username);
			this.addActionMessage("采购单添加成功！采购单号为：" + purchaseno);
			
			//清空数据
			addEtbPurchaseDto = new EtbPurchaseDto();
			//默认为当天
			addEtbPurchaseDto.setPurchasedate(new Date());
			addPurchaseItemList = new ArrayList<EtbPurchaseItemDto>();
		} catch(Exception e) {
			log.error("addEtbPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

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
	 * 验证数据
	 * @param purchase
	 * @return
	 */
	private boolean checkData(EtbPurchaseDto purchase) {
		if(purchase == null) {
			this.addActionMessage("采购日期不能为空！");
			return false;
		}
		if(purchase.getPurchasedate() == null) {
			this.addActionMessage("采购日期不能为空！");
			return false;
		}
		if(StringUtil.isBlank(purchase.getHandler())) {
			this.addActionMessage("经手人不能为空！");
			return false;
		}
		if(StringUtil.isBlank(purchase.getTheme1())) {
			this.addActionMessage("请选择采购主题！");
			return false;
		}
		if(StringUtil.isBlank(purchase.getWarehouse())) {
			this.addActionMessage("仓库不能为空！");
			return false;
		}
//		if(purchase.getSupplierid() == null) {
//			this.addActionMessage("请选择供应商！");
//			return false;
//		}
		if(purchase.getTotalamount() == null || purchase.getTotalamount().doubleValue() <= 0) {
			this.addActionMessage("采购金额（不含税）不能小于0！");
			return false;
		}
		if(purchase.getTaxamount() == null || purchase.getTaxamount().doubleValue() <= 0) {
			this.addActionMessage("采购金额（含税）不能小于0！");
			return false;
		}
		//采购金额不含税金额不能大于含税金额
		if(purchase.getTaxamount().compareTo(purchase.getTotalamount()) == -1) {
			this.addActionMessage("含税金额不能小于不含税金额！");
			return false;
		}
		return true;
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = etbPurchaseService.queryEtbPurchaseByPage(strPurchasedateLow, strPurchasedateHigh, page);
		etbPurchaseList = (List<EtbPurchaseDto>) page.getItems();
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

	public EtbPurchaseDto getAddEtbPurchaseDto() {
		return addEtbPurchaseDto;
	}

	public void setAddEtbPurchaseDto(EtbPurchaseDto addEtbPurchaseDto) {
		this.addEtbPurchaseDto = addEtbPurchaseDto;
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

	public List<EtbPurchaseItemDto> getAddPurchaseItemList() {
		return addPurchaseItemList;
	}

	public void setAddPurchaseItemList(List<EtbPurchaseItemDto> addPurchaseItemList) {
		this.addPurchaseItemList = addPurchaseItemList;
	}

	public EtbPurchaseItemService getEtbPurchaseItemService() {
		return etbPurchaseItemService;
	}

	public void setEtbPurchaseItemService(
			EtbPurchaseItemService etbPurchaseItemService) {
		this.etbPurchaseItemService = etbPurchaseItemService;
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

	public EtbPurchaseDto getUpdEtbPurchaseDto() {
		return updEtbPurchaseDto;
	}

	public void setUpdEtbPurchaseDto(EtbPurchaseDto updEtbPurchaseDto) {
		this.updEtbPurchaseDto = updEtbPurchaseDto;
	}

	public List<EtbPurchaseItemDto> getUpdPurchaseItemList() {
		return updPurchaseItemList;
	}

	public void setUpdPurchaseItemList(List<EtbPurchaseItemDto> updPurchaseItemList) {
		this.updPurchaseItemList = updPurchaseItemList;
	}

	public String getUpdEtbPurchaseId() {
		return updEtbPurchaseId;
	}

	public void setUpdEtbPurchaseId(String updEtbPurchaseId) {
		this.updEtbPurchaseId = updEtbPurchaseId;
	}
}
