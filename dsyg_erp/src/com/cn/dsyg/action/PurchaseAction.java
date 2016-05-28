package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.factory.Poi2007Base;
import com.cn.common.factory.PoiFactory;
import com.cn.common.factory.PoiPurchaseOrder;
import com.cn.common.factory.PurchaseSumicardXml;
import com.cn.common.factory.PurchaseSumitubeXml;
import com.cn.common.factory.PurchaseXml;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;
import com.cn.dsyg.dto.SalesItemDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PurchaseItemService;
import com.cn.dsyg.service.PurchaseService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 采购单管理Action
 * @author Frank
 * @time 2015-5-7下午10:29:11
 * @version 1.0
 */
public class PurchaseAction extends BaseAction {
	
	private static final long serialVersionUID = -1441939555094967866L;
	private static final Logger log = LogManager.getLogger(PurchaseAction.class);
	private PurchaseService purchaseService;
	private PurchaseItemService purchaseItemService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的采购数据列表
	private List<PurchaseDto> purchaseList;
	
	//查询条件
	//采购日期起
	private String strPurchasedateLow;
	//采购日期终
	private String strPurchasedateHigh;
	//采购单号
	private String strTheme2;
	//状态
	private String strStatus;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//支付方式
	private List<Dict01Dto> payTypeList;
	//产品ID
	private String productid;
	//产品信息
	private String 	productinfo;	

	//新增
	private PurchaseDto addPurchaseDto;
	private List<PurchaseItemDto> addPurchaseItemList;
	
	//修改
	private String updPurchaseId;
	private PurchaseDto updPurchaseDto;
	private List<PurchaseItemDto> updPurchaseItemList;
	private List<PurchaseItemDto> tmpUpdPurchaseItemList;
	private String theme2;
	private String exporttype;

	//删除
	private String delPurchaseId;
	
	//采购价用
	private String strProdoctid;
	private String strSupplierid;
	private List<PurchaseItemDto> purchaseItemList;
	
	//未入库产品一览用
	//供应商名
	private String strRemainSuppliername;
	private List<PurchaseItemDto> remainPurchaseItemList;
	
	/**
	 * 终了采购单
	 * @return
	 */
	public String finishPurchaseAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			purchaseService.finishPurchase(updPurchaseId, username);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("finishPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除采购单
	 * @return
	 */
	public String delPurchaseAction() {
		try {
			this.clearMessages();
			PurchaseDto purchase = purchaseService.queryPurchaseByID(delPurchaseId);
			if(purchase != null) {
				if(purchase.getStatus() != Constants.PURCHASE_STATUS_NEW) {
					this.addActionMessage("该数据不可以删除！");
				} else {
					//当前操作用户ID
					String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
					purchaseService.deletePurchase(delPurchaseId, username);
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
	
	//数据导出
	public String exportPurchaseList() {
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
			if(payTypeList != null && payTypeList.size() > 0) {
				for(Dict01Dto dict : payTypeList) {
					dictMap.put(Constants.DICT_PAY_TYPE + "_" + dict.getCode(), dict.getFieldname());
				}
			}
			
			String name = StringUtil.createFileName(Constants.EXCEL_TYPE_PURCHASELIST);
			response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
			response.setContentType("application/vnd.ms-excel");
			Poi2007Base base = PoiFactory.getPoi(Constants.EXCEL_TYPE_PURCHASELIST);
			
			//查询所有审价履历
			List<PurchaseDto> list = purchaseService.queryAllPurchaseToExcel(
					strPurchasedateLow, strPurchasedateHigh, "");
			
			base.setDatas(list);
			base.setSheetName(Constants.EXCEL_TYPE_PURCHASELIST);
			base.setDictMap(dictMap);
			base.exportExcel(response.getOutputStream());
		} catch(Exception e) {
			log.error("exportAuditHist error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 采购单预入库页面
	 * @return
	 */
	public String showUpdPurchaseitemAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updPurchaseItemList = new ArrayList<PurchaseItemDto>();
			updPurchaseDto = purchaseService.queryPurchaseByID(updPurchaseId);
			if(updPurchaseDto != null) {
				updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			}
		} catch(Exception e) {
			log.error("showUpdPurchaseitemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 采购单预入库
	 * @return
	 */
	public String updPurchaseitemAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//验证是否可以更新（状态=新增才可以更新）
			PurchaseDto purchaseDto = purchaseService.queryPurchaseByID("" + updPurchaseDto.getId());
			if(purchaseDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
			if(purchaseDto.getStatus() > Constants.PURCHASE_STATUS_WAREHOUSE_PART) {
				if(!"1".equals(updPurchaseDto.getRefundflag())) {
					this.addActionMessage("该数据不能更新！");
					return "checkerror";
				}
			}
			//数据验证
			if(!checkData(updPurchaseDto)) {
				return "checkerror";
			}
			if(updPurchaseItemList == null || updPurchaseItemList.size() <= 0) {
				this.addActionMessage("采购单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//更新数据
			purchaseService.updatePurchase(updPurchaseDto, updPurchaseItemList, username);
			//刷新页面
			updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			updPurchaseDto.setRefundflag("0");
			this.addActionMessage("预入库成功！");
		} catch(Exception e) {
			log.error("updPurchaseitemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示更新采购单页面
	 * @return
	 */
	public String showUpdPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updPurchaseItemList = new ArrayList<PurchaseItemDto>();
			tmpUpdPurchaseItemList = new ArrayList<PurchaseItemDto>();
			updPurchaseDto = purchaseService.queryPurchaseByID(updPurchaseId);
			if(updPurchaseDto != null) {
				updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			}
		} catch(Exception e) {
			log.error("showUpdPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示更新采购单页面(BY PurchaseNo)
	 * @return
	 */
	public String showUpdPurchaseitemByPurchaseNoAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updPurchaseItemList = new ArrayList<PurchaseItemDto>();
			tmpUpdPurchaseItemList = new ArrayList<PurchaseItemDto>();
			updPurchaseDto = purchaseService.queryPurchaseByTheme2(theme2);
			if(updPurchaseDto != null) {
				updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			}
		} catch(Exception e) {
			log.error("showUpdPurchaseByPurchaseNoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新采购单
	 * @return
	 */
	public String updPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//验证是否可以更新（状态=新增才可以更新）
			PurchaseDto purchaseDto = purchaseService.queryPurchaseByID("" + updPurchaseDto.getId());
			if(purchaseDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
			//非退换货时，检查数据状态
			if(purchaseDto.getStatus() > Constants.PURCHASE_STATUS_WAREHOUSE_PART) {
				if(!"1".equals(updPurchaseDto.getRefundflag())) {
					this.addActionMessage("该数据不能更新！");
					return "checkerror";
				}
			}
			//数据验证
			if(!checkData(updPurchaseDto)) {
				return "checkerror";
			}
			if(updPurchaseItemList == null || updPurchaseItemList.size() <= 0) {
				this.addActionMessage("采购单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			//更新数据
			purchaseService.updatePurchase(updPurchaseDto, tmpUpdPurchaseItemList, username);
			//刷新页面
			updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			tmpUpdPurchaseItemList = new ArrayList<PurchaseItemDto>();
			updPurchaseDto.setRefundflag("0");
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增采购页面
	 * @return
	 */
	public String showAddPurchaseAction() {
		try {
			this.clearMessages();
			addPurchaseDto = new PurchaseDto();
			//默认为当天
			addPurchaseDto.setPurchasedate(DateUtil.dateToShortStr(new Date()));
			addPurchaseDto.setPlandate(DateUtil.dateToShortStr(new Date()));
			
			addPurchaseItemList = new ArrayList<PurchaseItemDto>();
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showAddPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增采购
	 * @return
	 */
	public String addPurchaseAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addPurchaseDto)) {
				return "checkerror";
			}
			if(addPurchaseItemList == null || addPurchaseItemList.size() <= 0) {
				this.addActionMessage("采购单货物列表不能为空！");
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			String purchaseorder = purchaseService.addPurchase(addPurchaseDto, addPurchaseItemList, username);
			this.addActionMessage("采购单添加成功！采购订单号为：" + purchaseorder);
			//this.addActionMessage("采购单添加成功！");
			
			//清空数据
			addPurchaseDto = new PurchaseDto();
			//默认为当天
			addPurchaseDto.setPurchasedate(DateUtil.dateToShortStr(new Date()));
			addPurchaseItemList = new ArrayList<PurchaseItemDto>();
		} catch(Exception e) {
			log.error("addPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示采购管理页面
	 * @return
	 */
	public String showPurchaseAction() {
		try {
			this.clearMessages();
			delPurchaseId = "";
			updPurchaseId = "";
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strPurchasedateLow = "";
			strPurchasedateHigh = "";
			strTheme2 = "";
			strStatus = "";
			productid = "";
			purchaseList = new ArrayList<PurchaseDto>();
			
			queryData();
		} catch(Exception e) {
			log.error("showPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询采购
	 * @return
	 */
	public String queryPurchaseAction() {
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
			log.error("queryPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询采购价
	 * @return
	 */
	public String showProductPricePage() {
		try {
			this.clearMessages();
//			System.out.println("strProdoctid is: " + strProdoctid + " ,strSupplierid is:" + strSupplierid);
			purchaseItemList = purchaseItemService.queryPurchaseItemByProductid(strProdoctid, strSupplierid, 0, 9);
			
		} catch(Exception e) {
			log.error("queryPurchaseItemByProductid error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnPurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 显示未入库产品一览
	 * @return
	 */
	public String showRemainPurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strRemainSuppliername = "";
			remainPurchaseItemList = new ArrayList<PurchaseItemDto>();
			
			queryRemainData();
		} catch(Exception e) {
			log.error("showRemainSalesAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询未入库产品一览
	 * @return
	 */
	public String queryRemainPurchaseAction() {
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
			log.error("queryRemainPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnRemainPurchaseAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryRemainData();
		} catch(Exception e) {
			log.error("turnRemainPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 未入库产品一览数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryRemainData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);

		page = purchaseItemService.queryRemainPurchaseByPage(strRemainSuppliername, page);
		remainPurchaseItemList = (List<PurchaseItemDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	//导出数据
	public String exportPurchaseAction() {
		try {
			this.clearMessages();
			initDictList();
			
			//颜色，这里需要是英文的，故这里写死取英文字典数据 update by frank
			colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, Constants.SYSTEM_LANGUAGE_ENGLISH);
			makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, Constants.SYSTEM_LANGUAGE_ENGLISH);
			
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

			String name = StringUtil.createXmlFileName(Constants.EXCEL_TYPE_PURCHASEITEM);
			response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
			response.setContentType("text/xml;charset=GB2312");
			
			updPurchaseItemList = new ArrayList<PurchaseItemDto>();
			updPurchaseDto = purchaseService.queryPurchaseByID(updPurchaseId);
			if(updPurchaseDto != null) {
				updPurchaseItemList = purchaseItemService.queryPurchaseItemByPurchaseno(updPurchaseDto.getPurchaseno());
			}
			
			System.out.println("exporttype is: " + exporttype);
			if(exporttype != null && exporttype.equals("sumitube")){
				//导出xml
				PurchaseSumitubeXml salesXml = new PurchaseSumitubeXml();
				salesXml.setDictMap(dictMap);
				salesXml.exportXml(response.getOutputStream(), updPurchaseDto, updPurchaseItemList);
				log.info("exportXML success.");
				return SUCCESS;
			} else if(exporttype != null && exporttype.equals("sumicard")){
				//导出xml
				PurchaseSumicardXml salesXml = new PurchaseSumicardXml();
				salesXml.setDictMap(dictMap);
				salesXml.exportXml(response.getOutputStream(), updPurchaseDto, updPurchaseItemList);
				log.info("exportXML success.");
				return SUCCESS;
			} else if(exporttype != null && exporttype.equals("html")){
				String name_html = StringUtil.createHtmlFileName(Constants.EXCEL_TYPE_PURCHASEITEM);
				response.setHeader("Content-Disposition","attachment;filename=" + name_html);//指定下载的文件名
				response.setContentType("text/html; charset=GB2312");
				response.setCharacterEncoding("GB2312");
				PoiPurchaseOrder base = new PoiPurchaseOrder();
				base.setDictMap(dictMap);
				base.toHtml(response, updPurchaseDto, updPurchaseItemList);
				log.info("exportHTML success.");
				return SUCCESS;
			}
			
			//导出xml
			PurchaseXml purchaseXml = new PurchaseXml();
			purchaseXml.setDictMap(dictMap);
			purchaseXml.exportXml(response.getOutputStream(), updPurchaseDto, updPurchaseItemList);
	        
			log.info("exportExcel success.");
		} catch(Exception e) {
			log.error("exportPurchaseAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据
	 * @param purchase
	 * @return
	 */
	private boolean checkData(PurchaseDto purchase) {
		if(purchase == null) {
			this.addActionMessage("采购日期不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(purchase.getTheme2())) {
//			this.addActionMessage("采购订单号不能为空！");
//			return false;
//		}
		if(purchase.getPurchasedate() == null) {
			this.addActionMessage("采购日期不能为空！");
			return false;
		}
		if(StringUtil.isBlank(purchase.getRes01())) {
			this.addActionMessage("请选择支付方式！");
			return false;
		}
		if(StringUtil.isBlank(purchase.getPlandate())) {
			this.addActionMessage("预入库时间不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(purchase.getHandler())) {
//			this.addActionMessage("经手人不能为空！");
//			return false;
//		}
//		if(StringUtil.isBlank(purchase.getTheme1())) {
//			this.addActionMessage("请选择采购主题！");
//			return false;
//		}
//		if(StringUtil.isBlank(purchase.getWarehouse())) {
//			this.addActionMessage("仓库不能为空！");
//			return false;
//		}
//		if(purchase.getSupplierid() == null) {
//			this.addActionMessage("请选择供应商！");
//			return false;
//		}
		if(purchase.getTotalamount() == null || purchase.getTotalamount().doubleValue() < 0) {
			this.addActionMessage("采购金额（不含税）不能小于0！");
			return false;
		}
		if(purchase.getTaxamount() == null || purchase.getTaxamount().doubleValue() < 0) {
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
		delPurchaseId = "";
		updPurchaseId = "";
		if(page == null) {
			page = new Page(intPageSize);
		}
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = purchaseService.queryPurchaseExtByPage(strPurchasedateLow, strPurchasedateHigh, strTheme2, productid, strStatus, page);
		purchaseList = (List<PurchaseDto>) page.getItems();
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
		//支付方式
		payTypeList = dict01Service.queryDict01ByFieldcode(Constants.DICT_PAY_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
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

	public List<PurchaseItemDto> getAddPurchaseItemList() {
		return addPurchaseItemList;
	}

	public void setAddPurchaseItemList(List<PurchaseItemDto> addPurchaseItemList) {
		this.addPurchaseItemList = addPurchaseItemList;
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

	public List<PurchaseItemDto> getUpdPurchaseItemList() {
		return updPurchaseItemList;
	}

	public void setUpdPurchaseItemList(List<PurchaseItemDto> updPurchaseItemList) {
		this.updPurchaseItemList = updPurchaseItemList;
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public PurchaseItemService getPurchaseItemService() {
		return purchaseItemService;
	}

	public void setPurchaseItemService(PurchaseItemService purchaseItemService) {
		this.purchaseItemService = purchaseItemService;
	}

	public List<PurchaseDto> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseDto> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public PurchaseDto getAddPurchaseDto() {
		return addPurchaseDto;
	}

	public void setAddPurchaseDto(PurchaseDto addPurchaseDto) {
		this.addPurchaseDto = addPurchaseDto;
	}

	public String getUpdPurchaseId() {
		return updPurchaseId;
	}

	public void setUpdPurchaseId(String updPurchaseId) {
		this.updPurchaseId = updPurchaseId;
	}

	public PurchaseDto getUpdPurchaseDto() {
		return updPurchaseDto;
	}

	public void setUpdPurchaseDto(PurchaseDto updPurchaseDto) {
		this.updPurchaseDto = updPurchaseDto;
	}

	public String getStrProdoctid() {
		return strProdoctid;
	}

	public void setStrProdoctid(String strProdoctid) {
		this.strProdoctid = strProdoctid;
	}

	public String getStrSupplierid() {
		return strSupplierid;
	}

	public void setStrSupplierid(String strSupplierid) {
		this.strSupplierid = strSupplierid;
	}

	public List<PurchaseItemDto> getPurchaseItemList() {
		return purchaseItemList;
	}

	public void setPurchaseItemList(List<PurchaseItemDto> purchaseItemList) {
		this.purchaseItemList = purchaseItemList;
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

	public String getDelPurchaseId() {
		return delPurchaseId;
	}

	public void setDelPurchaseId(String delPurchaseId) {
		this.delPurchaseId = delPurchaseId;
	}

	public List<PurchaseItemDto> getTmpUpdPurchaseItemList() {
		return tmpUpdPurchaseItemList;
	}

	public void setTmpUpdPurchaseItemList(
			List<PurchaseItemDto> tmpUpdPurchaseItemList) {
		this.tmpUpdPurchaseItemList = tmpUpdPurchaseItemList;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
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

	public String getStrRemainSuppliername() {
		return strRemainSuppliername;
	}

	public void setStrRemainSuppliername(String strRemainSuppliername) {
		this.strRemainSuppliername = strRemainSuppliername;
	}

	public List<PurchaseItemDto> getRemainPurchaseItemList() {
		return remainPurchaseItemList;
	}

	public void setRemainPurchaseItemList(List<PurchaseItemDto> remainPurchaseItemList) {
		this.remainPurchaseItemList = remainPurchaseItemList;
	}
}
