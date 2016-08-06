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
import com.cn.dsyg.dto.SampleDto;
import com.cn.dsyg.dto.SampleTotleDto;
import com.cn.dsyg.dto.WarehouseProductDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.ProductService;
import com.cn.dsyg.service.SampleService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name SampleAction.java
 * @author Frank
 * @time 2015-9-12下午5:59:54
 * @version 1.0
 */
public class SampleAction extends BaseAction {

	private static final long serialVersionUID = 8929973332117176661L;
	private static final Logger log = LogManager.getLogger(SampleAction.class);
	
	private ProductService productService;
	private SampleService sampleService;
	private Dict01Service dict01Service;

	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	private List<SampleDto> sampleList;
	
	//查询条件
	//品名
	private String strTradename;
	//客户名
	private String strCustomername;
	//规格
	private String strTypeno;
	//颜色
	private String strColor;
	
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//新增
	private SampleDto addSampleDto;
	
	//修改
	private SampleDto updSampleDto;
	private String updSampleId;
	
	//样品汇总
	private String strProductid;
	private SampleTotleDto sampleTotleDto;
	private List<SampleTotleDto> sampleTotleList;
	
	/**
	 * 显示样品汇总页面
	 * @return
	 */
	public String showSampleSumAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			sampleTotleList = (List<SampleTotleDto>)sampleService.querySampleNumByProductId(strProductid);
		} catch(Exception e) {
			log.error("showSampleSumAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改样品页面
	 * @return
	 */
	public String showUpdSampleAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			updSampleDto = sampleService.querySampleId(updSampleId);
		} catch(Exception e) {
			log.error("showUpdSampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改样品
	 * @return
	 */
	public String updSampleAction() {
		try {
			this.clearMessages();
			//数据验证
			if(!checkData(updSampleDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updSampleDto.setUpdateuid(username);
			sampleService.updateSample(updSampleDto);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updSampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增样品页面
	 * @return
	 */
	public String showAddSampleAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			addSampleDto = new SampleDto();
		} catch(Exception e) {
			log.error("showAddSampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增样品
	 * @return
	 */
	public String addSampleAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			//数据验证
			if(!checkData(addSampleDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addSampleDto.setUpdateuid(username);
			addSampleDto.setCreateuid(username);
			addSampleDto.setRank(Constants.ROLE_RANK_OPERATOR);
			addSampleDto.setWarehousename(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_WAREHOUSE_NAME));
			addSampleDto.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//默认状态=有效
			addSampleDto.setStatus(Constants.STATUS_NORMAL);
			sampleService.insertSample(addSampleDto);
			this.addActionMessage("添加成功！");
			addSampleDto = new SampleDto();
		} catch(Exception e) {
			log.error("addSampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示样品管理页面
	 * @return
	 */
	public String showSampleAction() {
		try {
			this.clearMessages();
			updSampleId = "";
			strTradename = "";
			strCustomername = "";
			sampleList = new ArrayList<SampleDto>();
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showSampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询样品
	 * @return
	 */
	public String querySampleAction() {
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
			log.error("querySampleAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询样品库存BY KEYS
	 * @return
	 */
	public String querySampleNumByKeysAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			System.out.println("strTradename:" + strTradename);
			System.out.println("strTypeno:" + strTypeno);
			System.out.println("strColor:" + strColor);
			sampleTotleList = (List<SampleTotleDto>)sampleService.querySampleNumByKeys(strTradename, strTypeno, strColor);
		} catch(Exception e) {
			log.error("querySampleNumByKeys error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnSampleAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnSampleAction error:" + e);
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
		page = sampleService.querySampleByPage("", "", "", strTradename, strCustomername, page);
		sampleList = (List<SampleDto>) page.getItems();
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
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}
	
	/**
	 * 验证数据
	 * @param sample
	 * @return
	 */
	private boolean checkData(SampleDto sample) {
		if(sample == null) {
			this.addActionMessage("请选择产品！");
			return false;
		}
		if(StringUtil.isBlank(sample.getProductid())) {
			this.addActionMessage("请选择产品！");
			return false;
		}
		if(StringUtil.isBlank(sample.getCustomertype())) {
			this.addActionMessage("请选择类型！");
			return false;
		}
		if("1".equals(sample.getCustomertype())) {
			//供应商
			if(StringUtil.isBlank(sample.getCustomerid())) {
				this.addActionMessage("请选择供应商！");
				return false;
			}
			if(StringUtil.isBlank(sample.getCustomername())) {
				this.addActionMessage("请选择供应商！");
				return false;
			}
		} else if("2".equals(sample.getCustomertype())) {
			//客户
			if(StringUtil.isBlank(sample.getCustomerid())) {
				this.addActionMessage("请选择客户！");
				return false;
			}
			if(StringUtil.isBlank(sample.getCustomername())) {
				this.addActionMessage("请选择客户！");
				return false;
			}
		} else {
			this.addActionMessage("类型不正确！");
			return false;
		}
		if(StringUtil.isBlank(sample.getQuantity())) {
			this.addActionMessage("数量不能为空！");
			return false;
		}
		if(StringUtil.isBlank(sample.getNote())) {
			this.addActionMessage("备注不能为空！");
			return false;
		}
		return true;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public SampleService getSampleService() {
		return sampleService;
	}

	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	public List<SampleDto> getSampleList() {
		return sampleList;
	}

	public void setSampleList(List<SampleDto> sampleList) {
		this.sampleList = sampleList;
	}

	public SampleDto getAddSampleDto() {
		return addSampleDto;
	}

	public void setAddSampleDto(SampleDto addSampleDto) {
		this.addSampleDto = addSampleDto;
	}

	public SampleDto getUpdSampleDto() {
		return updSampleDto;
	}

	public void setUpdSampleDto(SampleDto updSampleDto) {
		this.updSampleDto = updSampleDto;
	}

	public String getUpdSampleId() {
		return updSampleId;
	}

	public void setUpdSampleId(String updSampleId) {
		this.updSampleId = updSampleId;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
	}

	public String getStrCustomername() {
		return strCustomername;
	}

	public void setStrCustomername(String strCustomername) {
		this.strCustomername = strCustomername;
	}

	public String getStrProductid() {
		return strProductid;
	}

	public void setStrProductid(String strProductid) {
		this.strProductid = strProductid;
	}

	public SampleTotleDto getSampleTotleDto() {
		return sampleTotleDto;
	}

	public void setSampleTotleDto(SampleTotleDto sampleTotleDto) {
		this.sampleTotleDto = sampleTotleDto;
	}

	public List<SampleTotleDto> getSampleTotleList() {
		return sampleTotleList;
	}

	public void setSampleTotleList(List<SampleTotleDto> sampleTotleList) {
		this.sampleTotleList = sampleTotleList;
	}

	public String getStrTypeno() {
		return strTypeno;
	}

	public void setStrTypeno(String strTypeno) {
		this.strTypeno = strTypeno;
	}

	public String getStrColor() {
		return strColor;
	}

	public void setStrColor(String strColor) {
		this.strColor = strColor;
	}

}
