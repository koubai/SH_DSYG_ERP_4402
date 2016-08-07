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
import com.cn.dsyg.dto.WarehouserptDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.PurchaseItemService;
import com.cn.dsyg.service.PurchaseService;
import com.cn.dsyg.service.WarehouserptService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name WarehouserptAction.java
 * @author Frank
 * @time 2015-6-3下午9:58:32
 * @version 1.0
 */
public class WarehouserptAction extends BaseAction {

	private static final long serialVersionUID = -8930942457017818387L;
	private static final Logger log = LogManager.getLogger(WarehouserptAction.class);
	
	private WarehouserptService warehouserptService;
	private PurchaseService purchaseService;
	private PurchaseItemService purchaseItemService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	private List<WarehouserptDto> warehouserptList;
	
	//采购单号
	private String strPurchaseno;
	
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
	
	//编辑
	private String updWarehouserptId;
	private WarehouserptDto updWarehouserptDto;
	//新增
	private WarehouserptDto addWarehouserptDto;
	
	//导出明细
	private String strExportDetailId;
	private String strInter;
	private String exportunitprice;
	
	private String strSuppliername;
	private String strWarehouseno;
	
	//出库单+入库单一览查询条件
	private String strCreatedateLow;
	private String strCreatedateHigh;
	
	//采购单OR订单
	private String strNo;

	//发货单
	/**
	 * 修改发货单页面
	 * @return
	 */
	public String showUpdWarehouserptOutAction() {
		try {
			this.clearMessages();
			updWarehouserptDto = warehouserptService.queryWarehouserptByID(updWarehouserptId);
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showUpdWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	//发货单
	/**
	 * 发货单详细页面
	 * @return
	 */
	public String showUpdWarehouserptOutItemAction() {
		try {
			this.clearMessages();
			updWarehouserptDto = warehouserptService.queryWarehouserptByID(updWarehouserptId);
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showUpdWarehouserptOutItemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改发货单
	 * @return
	 */
	public String updWarehouserptOutAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(updWarehouserptDto == null) {
				this.addActionMessage("数据为空，请检查数据是否正确！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressid())) {
				this.addActionMessage("请选择快递！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressno())) {
				this.addActionMessage("快递单号不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressname())) {
				this.addActionMessage("快递名称不能为空！");
				return "checkerror";
			}
/*			if(StringUtil.isBlank(updWarehouserptDto.getExpressaddress())) {
				this.addActionMessage("快递地址不能为空！");
				return "checkerror";
			}
*/			
			if(updWarehouserptDto.getExpresstaxamount() == null) {
				this.addActionMessage("转运费用合计不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressmanager())) {
				this.addActionMessage("快递联系人不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpresstel())) {
				this.addActionMessage("快递联系人电话不能为空！");
				return "checkerror";
			}
/*			if(StringUtil.isBlank(updWarehouserptDto.getExpressfax())) {
				this.addActionMessage("快递联系人传真不能为空！");
				return "checkerror";
			}
*/			
			if(StringUtil.isBlank(updWarehouserptDto.getWarehousedate())) {
				this.addActionMessage("发货日期不能为空！");
				return "checkerror";
			}
/*			if(StringUtil.isBlank(updWarehouserptDto.getExpressmail())) {
				this.addActionMessage("信箱不能为空！");
				return "checkerror";
			}
*/			
			//保存数据
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updWarehouserptDto.setUpdateuid(username);
			warehouserptService.updateWarehouserpt(updWarehouserptDto, Constants.WAREHOUSE_TYPE_OUT);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 发货单一览
	 * @return
	 */
	public String showWarehouserptOutAction() {
		try {
			this.clearMessages();
			strSuppliername = "";
			strWarehouseno = "";
			strCreatedateLow = "";
			strCreatedateHigh = "";
			strNo = "";
			warehouserptList = new ArrayList<WarehouserptDto>();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			initDictList();
			
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("showWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询入库数据
	 * @return
	 */
	public String queryWarehouserptOutAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("queryWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnWarehouserptOutAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("turnWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//入库单
	/**
	 * 新增入库单页面
	 * @return
	 */
	public String showAddWarehouserptInAction() {
		try {
			this.clearMessages();
			addWarehouserptDto = new WarehouserptDto();
		} catch(Exception e) {
			log.error("showAddWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增入库单页面
	 * @return
	 */
	public String addWarehouserptInAction() {
		try {
			this.clearMessages();
			addWarehouserptDto = new WarehouserptDto();
			this.addActionMessage("新增成功！");
		} catch(Exception e) {
			log.error("addWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改入库单页面
	 * @return
	 */
	public String showUpdWarehouserptInAction() {
		try {
			this.clearMessages();
			updWarehouserptDto = warehouserptService.queryWarehouserptByID(updWarehouserptId);
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showUpdWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 入库单详细页面
	 * @return
	 */
	public String showUpdWarehouserptInItemAction() {
		try {
			this.clearMessages();
			updWarehouserptDto = warehouserptService.queryWarehouserptByID(updWarehouserptId);
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showUpdWarehouserptInItemAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改入库单
	 * @return
	 */
	public String updWarehouserptInAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(updWarehouserptDto == null) {
				this.addActionMessage("数据为空，请检查数据是否正确！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressid())) {
				this.addActionMessage("请选择快递！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressno())) {
				this.addActionMessage("快递单号不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressname())) {
				this.addActionMessage("快递名称不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressaddress())) {
				this.addActionMessage("快递地址不能为空！");
				return "checkerror";
			}
			if(updWarehouserptDto.getExpresstaxamount() == null) {
				this.addActionMessage("转运费用合计不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressmanager())) {
				this.addActionMessage("快递联系人不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpresstel())) {
				this.addActionMessage("快递联系人电话不能为空！");
				return "checkerror";
			}
/*			if(StringUtil.isBlank(updWarehouserptDto.getExpressfax())) {
				this.addActionMessage("快递联系人传真不能为空！");
				return "checkerror";
			}
*/
			if(StringUtil.isBlank(updWarehouserptDto.getWarehousedate())) {
				this.addActionMessage("收货日期不能为空！");
				return "checkerror";
			}
			if(StringUtil.isBlank(updWarehouserptDto.getExpressmail())) {
				this.addActionMessage("信箱不能为空！");
				return "checkerror";
			}
			//保存数据
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updWarehouserptDto.setUpdateuid(username);
			warehouserptService.updateWarehouserpt(updWarehouserptDto, Constants.WAREHOUSE_TYPE_IN);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 入库单一览
	 * @return
	 */
	public String showWarehouserptInAction() {
		try {
			this.clearMessages();
			strSuppliername = "";
			strWarehouseno = "";
			strCreatedateLow = "";
			strCreatedateHigh = "";
			strNo = "";
			warehouserptList = new ArrayList<WarehouserptDto>();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			initDictList();
			
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("showWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询入库数据
	 * @return
	 */
	public String queryWarehouserptInAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("queryWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnWarehouserptInAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("turnWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出入库单数据
	 * @return
	 */
	public String exportWarehouserptInAction() {
		try {
			this.clearMessages();
			exportData("" + Constants.WAREHOUSE_TYPE_IN);
		} catch(Exception e) {
			log.error("exportWarehouserptInAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出出库单数据
	 * @return
	 */
	public String exportWarehouserptOutAction() {
		try {
			this.clearMessages();
			exportData("" + Constants.WAREHOUSE_TYPE_OUT);
		} catch(Exception e) {
			log.error("exportWarehouserptOutAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出入库单明细数据
	 * @return
	 */
	public String exportWarehouserptInDetailAction() {
		try {
			this.clearMessages();
			exportDetail("" + Constants.WAREHOUSE_TYPE_IN, strExportDetailId);
		} catch(Exception e) {
			log.error("exportWarehouserptInDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出出库单明细数据
	 * @return
	 */
	public String exportWarehouserptOutDetailAction() {
		try {
			this.clearMessages();
			exportDetail("" + Constants.WAREHOUSE_TYPE_OUT, strExportDetailId);
		} catch(Exception e) {
			log.error("exportWarehouserptOutDetailAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 导出明细数据
	 * @param type
	 * @param id
	 * @throws IOException
	 */
	private void exportDetail(String type, String id) throws IOException {
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

		WarehouserptDto rpt;
		String exceltype = "";
		if(("" + Constants.WAREHOUSE_TYPE_IN).equals(type)) {
			if(strInter != null && strInter.equals("1")){
				//入库单
				exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_INTER_LIST;
				rpt = warehouserptService.queryWarehouserptInterByID(strExportDetailId);
			} else {
				//入库单明细
				if(exportunitprice != null && exportunitprice.equals("1")){
					exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_LIST;
				} else {
					exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_IN_DETAIL_LIST_NOPRICE;
				}
				rpt = warehouserptService.queryWarehouserptByID(strExportDetailId);
			}
		} else {
			if(strInter != null && strInter.equals("1")){
				//出库单
				exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_INTER_LIST;
				rpt = warehouserptService.queryWarehouserptInterByID(strExportDetailId);
			} else {
				//出库单明细
				if(exportunitprice != null && exportunitprice.equals("1")){
					exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_LIST;
				} else {
					exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_OUT_DETAIL_LIST_NOPRICE;
				}
				rpt = warehouserptService.queryWarehouserptByID(strExportDetailId);
			}
		}
		String name = StringUtil.createFileName(exceltype);
		response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		Poi2007Base base = PoiFactory.getPoi(exceltype);
		//根据ID查询数据
		List<WarehouserptDto> list = new ArrayList<WarehouserptDto>();
		if(rpt != null) {
			list.add(rpt);
		} else {
			log.warn("queryWarehouserptByID is null, id=" + strExportDetailId);
		}
		base.setDatas(list);
		base.setSheetName(exceltype);
		base.setDictMap(dictMap);
		base.exportExcel(response.getOutputStream());
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
		
		String exceltype = "";
		if(("" + Constants.WAREHOUSE_TYPE_IN).equals(type)) {
			//入库单
			exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_IN_LIST;
		} else {
			//出库单
			exceltype = Constants.EXCEL_TYPE_WAREHOUSERPT_OUT_LIST;
		}
		String name = StringUtil.createFileName(exceltype);
		response.setHeader("Content-Disposition","attachment;filename=" + name);//指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		Poi2007Base base = PoiFactory.getPoi(exceltype);
		//查询所有数据
		List<WarehouserptDto> list = warehouserptService.queryAllWarehouserptToExport("", type, "", "", "", "", "");
		
		base.setDatas(list);
		base.setSheetName(exceltype);
		base.setDictMap(dictMap);
		base.exportExcel(response.getOutputStream());
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData(String type) {
		if(page == null) {
			page = new Page(intPageSize);
		}
		initDictList();
		//翻页查询所有预入库待确认数据
		this.page.setStartIndex(startIndex);
		page = warehouserptService.queryWarehouserptByPage(strNo, "", type, "", "", "", "", "", "", "",
				strSuppliername, strWarehouseno, strCreatedateLow, strCreatedateHigh, page);
		warehouserptList = (List<WarehouserptDto>) page.getItems();
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
		//excel密码
		List<Dict01Dto> listPass = dict01Service.queryDict01ByFieldcode(Constants.EXCEL_PASS, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listPass != null && listPass.size() > 0) {
			excelPass = listPass.get(0).getCode();
		}
	}
	
	public WarehouserptService getWarehouserptService() {
		return warehouserptService;
	}

	public void setWarehouserptService(WarehouserptService warehouserptService) {
		this.warehouserptService = warehouserptService;
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

	public String getStrPurchaseno() {
		return strPurchaseno;
	}

	public void setStrPurchaseno(String strPurchaseno) {
		this.strPurchaseno = strPurchaseno;
	}

	public List<WarehouserptDto> getWarehouserptList() {
		return warehouserptList;
	}

	public void setWarehouserptList(List<WarehouserptDto> warehouserptList) {
		this.warehouserptList = warehouserptList;
	}

	public String getUpdWarehouserptId() {
		return updWarehouserptId;
	}

	public void setUpdWarehouserptId(String updWarehouserptId) {
		this.updWarehouserptId = updWarehouserptId;
	}

	public WarehouserptDto getUpdWarehouserptDto() {
		return updWarehouserptDto;
	}

	public void setUpdWarehouserptDto(WarehouserptDto updWarehouserptDto) {
		this.updWarehouserptDto = updWarehouserptDto;
	}

	public WarehouserptDto getAddWarehouserptDto() {
		return addWarehouserptDto;
	}

	public void setAddWarehouserptDto(WarehouserptDto addWarehouserptDto) {
		this.addWarehouserptDto = addWarehouserptDto;
	}

	public String getStrExportDetailId() {
		return strExportDetailId;
	}

	public void setStrExportDetailId(String strExportDetailId) {
		this.strExportDetailId = strExportDetailId;
	}

	public String getExcelPass() {
		return excelPass;
	}

	public void setExcelPass(String excelPass) {
		this.excelPass = excelPass;
	}


	public String getStrInter() {
		return strInter;
	}


	public void setStrInter(String strInter) {
		this.strInter = strInter;
	}


	public String getStrSuppliername() {
		return strSuppliername;
	}


	public void setStrSuppliername(String strSuppliername) {
		this.strSuppliername = strSuppliername;
	}

	public String getStrWarehouseno() {
		return strWarehouseno;
	}


	public void setStrWarehouseno(String strWarehouseno) {
		this.strWarehouseno = strWarehouseno;
	}


	public String getExportunitprice() {
		return exportunitprice;
	}


	public void setExportunitprice(String exportunitprice) {
		this.exportunitprice = exportunitprice;
	}


	public String getStrNo() {
		return strNo;
	}


	public void setStrNo(String strNo) {
		this.strNo = strNo;
	}


	public String getStrCreatedateLow() {
		return strCreatedateLow;
	}


	public void setStrCreatedateLow(String strCreatedateLow) {
		this.strCreatedateLow = strCreatedateLow;
	}


	public String getStrCreatedateHigh() {
		return strCreatedateHigh;
	}


	public void setStrCreatedateHigh(String strCreatedateHigh) {
		this.strCreatedateHigh = strCreatedateHigh;
	}

}
