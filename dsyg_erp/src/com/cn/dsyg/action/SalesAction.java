package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.SalesItemService;
import com.cn.dsyg.service.SalesService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 销售单Action
 * @name SalesAction.java
 * @author Frank
 * @time 2015-6-13下午11:46:16
 * @version 1.0
 */
public class SalesAction extends BaseAction {

	private static final long serialVersionUID = 3137296006898420519L;
	private static final Logger log = LogManager.getLogger(SalesAction.class);
	
	private SalesService salesService;
	private SalesItemService salesItemService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的销售数据列表
	private List<SalesDto> salesList;
	
	//查询条件
	//销售日期起
	private String strSalesdateLow;
	//销售日期终
	private String strSalesdateHigh;
	
	//销售主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
		
	//新增
	private SalesDto addSalesDto;
	private List<SalesItemDto> addSalesItemList;
	
	//修改
	private String updSalesId;
	private SalesDto updSalesDto;
	private List<SalesItemDto> updSalesItemList;

	/**
	 * 显示更新销售单页面
	 * @return
	 */
	public String showUpdSalesAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updSalesItemList = new ArrayList<SalesItemDto>();
			updSalesDto = salesService.querySalesByID(updSalesId);
			if(updSalesDto != null) {
				updSalesItemList = salesItemService.querySalesItemBySalesno(updSalesDto.getSalesno());
			}
		} catch(Exception e) {
			log.error("showUpdSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新销售单
	 * @return
	 */
	public String updSalesAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			
			//验证是否可以更新（状态=新增才可以更新）
			SalesDto salesDto = salesService.querySalesByID("" + updSalesDto.getId());
			if(salesDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
			if(salesDto.getStatus() > Constants.SALES_STATUS_NEW) {
				this.addActionMessage("该数据不能更新！");
				return "checkerror";
			}
			//数据验证
			if(!checkData(updSalesDto)) {
				return "checkerror";
			}
			if(updSalesItemList == null || updSalesItemList.size() <= 0) {
				this.addActionMessage("销售单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//更新数据
			salesService.updateSales(updSalesDto, updSalesItemList, username);
			//刷新页面
			updSalesItemList = salesItemService.querySalesItemBySalesno(updSalesDto.getSalesno());
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增销售页面
	 * @return
	 */
	public String showAddSalesAction() {
		try {
			this.clearMessages();
			addSalesDto = new SalesDto();
			//默认为当天
			addSalesDto.setBookdate(DateUtil.dateToShortStr(new Date()));
			//预入库时间默认为当天
			addSalesDto.setPlandate(DateUtil.dateToShortStr(new Date()));
			
			addSalesItemList = new ArrayList<SalesItemDto>();
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showAddSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增销售
	 * @return
	 */
	public String addSalesAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addSalesDto)) {
				return "checkerror";
			}
			if(addSalesItemList == null || addSalesItemList.size() <= 0) {
				this.addActionMessage("销售单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			String salesno = salesService.addSales(addSalesDto, addSalesItemList, username);
			this.addActionMessage("销售单添加成功！销售单号为：" + salesno);
			
			//清空数据
			addSalesDto = new SalesDto();
			//默认为当天
			addSalesDto.setBookdate(DateUtil.dateToShortStr(new Date()));
			//预入库时间默认为当天
			addSalesDto.setPlandate(DateUtil.dateToShortStr(new Date()));
			addSalesItemList = new ArrayList<SalesItemDto>();
		} catch(Exception e) {
			log.error("addSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示销售管理页面
	 * @return
	 */
	public String showSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strSalesdateLow = "";
			strSalesdateHigh = "";
			salesList = new ArrayList<SalesDto>();
		} catch(Exception e) {
			log.error("showSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询销售
	 * @return
	 */
	public String querySalesAction() {
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
			log.error("querySalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据
	 * @param sales
	 * @return
	 */
	private boolean checkData(SalesDto sales) {
		if(sales == null) {
			this.addActionMessage("销售日期不能为空！");
			return false;
		}
		if(sales.getBookdate() == null) {
			this.addActionMessage("销售日期不能为空！");
			return false;
		}
		if(StringUtil.isBlank(sales.getHandler())) {
			this.addActionMessage("经手人不能为空！");
			return false;
		}
		if(StringUtil.isBlank(sales.getTheme1())) {
			this.addActionMessage("请选择销售主题！");
			return false;
		}
		if(StringUtil.isBlank(sales.getWarehouse())) {
			this.addActionMessage("仓库不能为空！");
			return false;
		}
//		if(sales.getCustomerid() == null) {
//			this.addActionMessage("请选择客户！");
//			return false;
//		}
		if(sales.getAmount() == null || sales.getAmount().doubleValue() <= 0) {
			this.addActionMessage("销售金额（不含税）不能小于0！");
			return false;
		}
		if(sales.getTaxamount() == null || sales.getTaxamount().doubleValue() <= 0) {
			this.addActionMessage("销售金额（含税）不能小于0！");
			return false;
		}
		//销售金额不含税金额不能大于含税金额
		if(sales.getTaxamount().compareTo(sales.getAmount()) == -1) {
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
		page = salesService.querySalesByPage(strSalesdateLow, strSalesdateHigh, page);
		salesList = (List<SalesDto>) page.getItems();
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
		//销售主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}

	public SalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(SalesService salesService) {
		this.salesService = salesService;
	}

	public SalesItemService getSalesItemService() {
		return salesItemService;
	}

	public void setSalesItemService(SalesItemService salesItemService) {
		this.salesItemService = salesItemService;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
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

	public List<SalesDto> getSalesList() {
		return salesList;
	}

	public String getStrSalesdateLow() {
		return strSalesdateLow;
	}

	public String getStrSalesdateHigh() {
		return strSalesdateHigh;
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

	public SalesDto getAddSalesDto() {
		return addSalesDto;
	}

	public List<SalesItemDto> getAddSalesItemList() {
		return addSalesItemList;
	}

	public String getUpdSalesId() {
		return updSalesId;
	}

	public SalesDto getUpdSalesDto() {
		return updSalesDto;
	}

	public List<SalesItemDto> getUpdSalesItemList() {
		return updSalesItemList;
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

	public void setSalesList(List<SalesDto> salesList) {
		this.salesList = salesList;
	}

	public void setStrSalesdateLow(String strSalesdateLow) {
		this.strSalesdateLow = strSalesdateLow;
	}

	public void setStrSalesdateHigh(String strSalesdateHigh) {
		this.strSalesdateHigh = strSalesdateHigh;
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

	public void setAddSalesDto(SalesDto addSalesDto) {
		this.addSalesDto = addSalesDto;
	}

	public void setAddSalesItemList(List<SalesItemDto> addSalesItemList) {
		this.addSalesItemList = addSalesItemList;
	}

	public void setUpdSalesId(String updSalesId) {
		this.updSalesId = updSalesId;
	}

	public void setUpdSalesDto(SalesDto updSalesDto) {
		this.updSalesDto = updSalesDto;
	}

	public void setUpdSalesItemList(List<SalesItemDto> updSalesItemList) {
		this.updSalesItemList = updSalesItemList;
	}
}
