package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.factory.PoiSalesPrice;
import com.cn.common.factory.PoiSalesPriceOrder;
import com.cn.common.factory.SalesSumicardXml;
import com.cn.common.factory.SalesSumitubeXml;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.SalesDto;
import com.cn.dsyg.dto.SalesExtDto;
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
	private List<SalesExtDto> salesList;
	
	//查询条件
	//销售日期起
	private String strSalesdateLow;
	//销售日期终
	private String strSalesdateHigh;
	//销售单号
	private String strTheme2;
	//客户名
	private String strCustomername;
	//类别
	private String strType;
	//状态
	private String strStatus;
	//产品ID
	private String productid;
	//产品信息
	private String 	productinfo;	
	//销售主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//支付方式
	private List<Dict01Dto> payTypeList;
		
	//新增
	private SalesDto addSalesDto;
	private List<SalesItemDto> addSalesItemList;
	
	//修改
	private String updSalesId;
	private SalesDto updSalesDto;
	private List<SalesItemDto> updSalesItemList;
	private List<SalesItemDto> tmpUpdSalesItemList;
	private String theme2;
	private String salesno;
	private String exporttype;
	
	//删除
	private String delSalesId;

	//销售价用
	private String strProdoctid;
	private String strCustomerid;
	private List<SalesItemDto> salesItemList;
	
	//未出库产品一览用
	//客户名
	private String strRemainCustomername;
	private List<SalesItemDto> remainSalesItemList;
	
	/**
	 * 终了销售单
	 * @return
	 */
	public String finishSalesAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			salesService.finishSales(updSalesId, username);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("finishSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除销售单
	 * @return
	 */
	public String delSalesAction() {
		try {
			this.clearMessages();
			SalesDto sales = salesService.querySalesByID(delSalesId);
			if(sales != null) {
				if(sales.getStatus() != Constants.SALES_STATUS_NEW) {
					this.addActionMessage("该数据不可以删除！");
				} else {
					//当前操作用户ID
					String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
					salesService.deleteSales(delSalesId, username);
					this.addActionMessage("删除成功！");
				}
			} else {
				this.addActionMessage("该数据不存在！");
			}
			//刷新页面
			queryData();
		} catch(Exception e) {
			log.error("exportAuditHist error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示订单预出库页面
	 * @return
	 */
	public String showUpdSalesitemAction() {
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
			log.error("showUpdSalesitemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示订单预出库页面(BySaleNo)
	 * @return
	 */
	public String showUpdSalesitemBySalesNoAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updSalesItemList = new ArrayList<SalesItemDto>();
			updSalesDto = salesService.querySalesByNo(salesno);
			if(updSalesDto != null) {
				updSalesItemList = salesItemService.querySalesItemBySalesno(updSalesDto.getSalesno());
			}
		} catch(Exception e) {
			log.error("showUpdSalesitemBySalesNoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
		
	/**
	 * 订单预出库页面
	 * @return
	 */
	public String updSalesitemAction() {
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
			if(salesDto.getStatus() > Constants.SALES_STATUS_WAREHOUSE_PART) {
				if(!"1".equals(updSalesDto.getRefundflag())) {
					this.addActionMessage("该数据不能更新！");
					return "checkerror";
				}
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
			updSalesDto.setRefundflag("0");
			this.addActionMessage("预出库成功！");
		} catch(Exception e) {
			log.error("updSalesitemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

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
			tmpUpdSalesItemList = new ArrayList<SalesItemDto>();
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
			if(salesDto.getStatus() > Constants.SALES_STATUS_WAREHOUSE_PART) {
				if(!"1".equals(updSalesDto.getRefundflag())) {
					this.addActionMessage("该数据不能更新！");
					return "checkerror";
				}
			}
			//数据验证
			if(!checkData(updSalesDto)) {
				return "checkerror";
			}
			
			//if("0".equals(updSalesDto.getRes02()) || "2".equals(updSalesDto.getRes02())) {
				if(updSalesItemList == null || updSalesItemList.size() <= 0) {
					this.addActionMessage("销售单货物列表不能为空！");
					return "checkerror";
				}
			//}
			//数据验证(防止相同订单号)
			SalesDto tmp_salesDto = salesService.querySalesByTheme2(updSalesDto.getTheme2());
			if(tmp_salesDto != null && tmp_salesDto.getStatus().intValue() != Constants.STATUS_DEL){
				if (!tmp_salesDto.getId().equals(updSalesDto.getId())) {
					this.addActionMessage("存在相同的销售订单号！");
					System.out.println(tmp_salesDto.getId()+";"+updSalesDto.getId());
					return "checkerror";
				}
			}

			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//更新数据
			salesService.updateSales(updSalesDto, tmpUpdSalesItemList, username);
			//刷新页面
			updSalesItemList = salesItemService.querySalesItemBySalesno(updSalesDto.getSalesno());
			tmpUpdSalesItemList = new ArrayList<SalesItemDto>();
			updSalesDto.setRefundflag("0");
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
			//if("0".equals(addSalesDto.getRes02()) || "2".equals(addSalesDto.getRes02())) {
				if(addSalesItemList == null || addSalesItemList.size() <= 0) {
					this.addActionMessage("销售单货物列表不能为空！");
					return "checkerror";
				}
			//}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//数据验证(防止相同订单号)
			SalesDto salesDto = salesService.querySalesByTheme2(addSalesDto.getTheme2());
			if(salesDto != null && salesDto.getStatus().intValue() != Constants.STATUS_DEL) {
				this.addActionMessage("存在相同的销售订单号！");
				return "checkerror";
			}
			String theme2 = salesService.addSales(addSalesDto, addSalesItemList, username);
			if(!"0".equals(addSalesDto.getRes02())) {
				//销售方式为询价询样时，订单号自动生成
				this.addActionMessage("销售单添加成功！销售订单号为：" + theme2);
			} else {
				this.addActionMessage("销售单添加成功！");
			}
			
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
			strTheme2 = "";
			strCustomername = "";
			strType = "0";
			strStatus = "10";
			productid = "";
			salesList = new ArrayList<SalesExtDto>();
			
			queryData();
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
	 * 查询销售价
	 * @return
	 */
	public String showProductSalesPricePage() {
		try {
			this.clearMessages();
//			System.out.println("strProdoctid is: " + strProdoctid + " ,strCustomerid is:" + strCustomerid);
			salesItemList = salesItemService.querySalesItemByProductid(strProdoctid, strCustomerid, 0, 99);
			
		} catch(Exception e) {
			log.error("querySalesItemByProductid error:" + e);
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
	 * 显示未出库产品一览
	 * @return
	 */
	public String showRemainSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strRemainCustomername = "";
			remainSalesItemList = new ArrayList<SalesItemDto>();
			
			queryRemainData();
		} catch(Exception e) {
			log.error("showRemainSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询未出库产品一览
	 * @return
	 */
	public String queryRemainSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryRemainData();
		} catch(Exception e) {
			log.error("queryRemainSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnRemainSalesAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryRemainData();
		} catch(Exception e) {
			log.error("turnRemainSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 未出库产品一览数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryRemainData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);

		page = salesItemService.queryRemainSalesByPage(strRemainCustomername, page);
		remainSalesItemList = (List<SalesItemDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 导出数据
	 * @return
	 */
	public String exportSalesPriceAction() {
		try {
			this.clearMessages();
			initDictList();
			//颜色，这里需要是英文的，故这里写死取英文字典数据 update by frank
			colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, Constants.SYSTEM_LANGUAGE_ENGLISH);
			makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, Constants.SYSTEM_LANGUAGE_ENGLISH);
			String type = "" + Constants.EXCEL_TYPE_SALES_PRICE;
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
			if(payTypeList != null && payTypeList.size() > 0) {
				for(Dict01Dto dict : payTypeList) {
					dictMap.put(Constants.DICT_PAY_TYPE + "_" + dict.getCode(), dict.getFieldname());
				}
			}

			//查询所有数据
			updSalesItemList = new ArrayList<SalesItemDto>();
			updSalesDto = salesService.querySalesByID(updSalesId);
			if(updSalesDto != null) {
				updSalesItemList = salesItemService.querySalesItemBySalesno(updSalesDto.getSalesno());
			}
			
			System.out.println("exporttype is: " + exporttype);
			if(exporttype != null && exporttype.equals("sumitube")){
				String name = StringUtil.createXmlFileName(type);
				response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
				response.setContentType("text/xml;charset=GB2312");
				//导出xml
				SalesSumitubeXml salesXml = new SalesSumitubeXml();
				salesXml.setDictMap(dictMap);
				salesXml.exportXml(response.getOutputStream(), updSalesDto, updSalesItemList);
				log.info("exportXML success.");
				return SUCCESS;
			} else if(exporttype != null && exporttype.equals("sumicard")){
				String name = StringUtil.createXmlFileName(type);
				response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
				response.setContentType("text/xml;charset=GB2312");
				//导出xml
				SalesSumicardXml salesXml = new SalesSumicardXml();
				salesXml.setDictMap(dictMap);
				salesXml.exportXml(response.getOutputStream(), updSalesDto, updSalesItemList);
				log.info("exportXML success.");
				return SUCCESS;
			}

			String name = StringUtil.createHtmlFileName(type);
			response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
			response.setContentType("text/html; charset=GB2312");
			response.setCharacterEncoding("GB2312");
			PoiSalesPrice base = new PoiSalesPrice();
			if(exporttype != null && exporttype.equals("order")){
				base = new PoiSalesPriceOrder();
			}
			base.setDictMap(dictMap);
			base.toHtml(response, updSalesDto, updSalesItemList);
			
		} catch(Exception e) {
			log.error("exportSalesPriceAction error:" + e);
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
			this.addActionMessage("销售订单号不能为空！");
			return false;
		}
		//销售方式=普通的时候，订单号不能为空
		if(sales.getRes02() != null && "0".equals(sales.getRes02())) {
			if(StringUtil.isBlank(sales.getTheme2())) {
				this.addActionMessage("销售订单号不能为空！");
				return false;
			}
		}
		if(sales.getBookdate() == null) {
			this.addActionMessage("销售日期不能为空！");
			return false;
		}
		if(StringUtil.isBlank(sales.getRes02())) {
			this.addActionMessage("请选择销售方式！");
			return false;
		}
		
		if(StringUtil.isBlank(sales.getRes01())) {
			this.addActionMessage("请选择支付方式！");
			return false;
		}
//		if(StringUtil.isBlank(sales.getHandler())) {
//			this.addActionMessage("经手人不能为空！");
//			return false;
//		}
//		if(StringUtil.isBlank(sales.getTheme1())) {
//			this.addActionMessage("请选择销售主题！");
//			return false;
//		}
//		if(StringUtil.isBlank(sales.getWarehouse())) {
//			this.addActionMessage("仓库不能为空！");
//			return false;
//		}
//		if(sales.getCustomerid() == null) {
//			this.addActionMessage("请选择客户！");
//			return false;
//		}
		if(sales.getAmount() == null || sales.getAmount().doubleValue() < 0) {
			this.addActionMessage("销售金额（不含税）不能小于0！");
			return false;
		}
		if(sales.getTaxamount() == null || sales.getTaxamount().doubleValue() < 0) {
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

		page = salesService.querySalesExtByPage(strSalesdateLow, strSalesdateHigh, strTheme2, strType, strCustomername, productid, strStatus, page);
		salesList = (List<SalesExtDto>) page.getItems();
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
		//支付方式
		payTypeList = dict01Service.queryDict01ByFieldcode(Constants.DICT_PAY_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
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

	public List<SalesExtDto> getSalesList() {
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

	public void setSalesList(List<SalesExtDto> salesList) {
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

	public String getStrProdoctid() {
		return strProdoctid;
	}

	public void setStrProdoctid(String strProdoctid) {
		this.strProdoctid = strProdoctid;
	}

	public String getStrCustomerid() {
		return strCustomerid;
	}

	public void setStrCustomerid(String strCustomerid) {
		this.strCustomerid = strCustomerid;
	}

	public List<SalesItemDto> getSalesItemList() {
		return salesItemList;
	}

	public void setSalesItemList(List<SalesItemDto> salesItemList) {
		this.salesItemList = salesItemList;
	}

	public List<Dict01Dto> getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(List<Dict01Dto> payTypeList) {
		this.payTypeList = payTypeList;
	}

	public String getStrTheme2() {
		return strTheme2;
	}

	public void setStrTheme2(String strTheme2) {
		this.strTheme2 = strTheme2;
	}
	
	public String getTheme2() {
		return theme2;
	}

	public void setTheme2(String theme2) {
		this.theme2 = theme2;
	}

	public String getDelSalesId() {
		return delSalesId;
	}

	public void setDelSalesId(String delSalesId) {
		this.delSalesId = delSalesId;
	}

	public String getStrCustomername() {
		return strCustomername;
	}

	public void setStrCustomername(String strCustomername) {
		this.strCustomername = strCustomername;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}

	public List<SalesItemDto> getTmpUpdSalesItemList() {
		return tmpUpdSalesItemList;
	}

	public void setTmpUpdSalesItemList(List<SalesItemDto> tmpUpdSalesItemList) {
		this.tmpUpdSalesItemList = tmpUpdSalesItemList;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
	public String getSalesno() {
		return salesno;
	}

	public void setSalesno(String salesno) {
		this.salesno = salesno;
	}

	public String getExporttype() {
		return exporttype;
	}

	public void setExporttype(String exporttype) {
		this.exporttype = exporttype;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getStrRemainCustomername() {
		return strRemainCustomername;
	}

	public void setStrRemainCustomername(String strRemainCustomername) {
		this.strRemainCustomername = strRemainCustomername;
	}

	public List<SalesItemDto> getRemainSalesItemList() {
		return remainSalesItemList;
	}

	public void setRemainSalesItemList(List<SalesItemDto> remainSalesItemList) {
		this.remainSalesItemList = remainSalesItemList;
	}


}
