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
import com.cn.dsyg.dto.EtbProductDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.EtbProductService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name EtbProductAction.java
 * @author Frank
 * @time 2015-5-18下午9:58:25
 * @version 1.0
 */
public class EtbProductAction extends BaseAction {

	private static final long serialVersionUID = 4977406296240495384L;
	private static final Logger log = LogManager.getLogger(EtbProductAction.class);
	
	private EtbProductService etbProductService;
	private Dict01Service dict01Service;
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的产品列表
	private List<EtbProductDto> etbProductList;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	private String strFieldno;
	private String strKeyword;
	private String strSupplierId;
	private String strTradename;
	private String strTypeno;
	private String strColor;
	private String strSeq;
	//1为采购单，2为销售单
	private String strFlag;
	
	//新增
	private EtbProductDto addEtbProductDto;
	
	//修改
	private String updEtbProductId;
	private EtbProductDto updEtbProductDto;
	
	//删除
	private String delEtbProductId;
	
	/**
	 * 删除数据
	 * @return
	 */
	public String delProductAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			etbProductService.deleteEtbProduct(delEtbProductId, username);
			this.addActionMessage("删除数据成功！");
			//刷新页面数据
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showUpdProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改产品页面
	 * @return
	 */
	public String showUpdProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			updEtbProductDto = etbProductService.queryEtbProductByID(updEtbProductId);
		} catch(Exception e) {
			log.error("showUpdProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改产品
	 * @return
	 */
	public String updProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(updEtbProductDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updEtbProductDto.setUpdateuid(username);
			etbProductService.updateEtbProduct(updEtbProductDto);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增产品页面
	 * @return
	 */
	public String showAddProductAction() {
		try {
			this.clearMessages();
			addEtbProductDto = new EtbProductDto();
			//初期化字典数据
			initDictList();
		} catch(Exception e) {
			log.error("showAddProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增产品
	 * @return
	 */
	public String addProductAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据验证
			if(!checkData(addEtbProductDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addEtbProductDto.setCreateuid(username);
			addEtbProductDto.setUpdateuid(username);
			etbProductService.insertEtbProduct(addEtbProductDto);
			this.addActionMessage("添加成功！");
			addEtbProductDto = new EtbProductDto();
		} catch(Exception e) {
			log.error("addProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 产品管理页面
	 * @return
	 */
	public String showEtbProductAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			strFieldno = "";
			strKeyword = "";
			strTradename = "";
			strTypeno = "";
			strColor = "";
			strSupplierId = "";
			strSeq = "";
			updEtbProductId = "";
			page = new Page(intPageSize);
			//初期化字典数据
			initDictList();
			etbProductList = new ArrayList<EtbProductDto>();
		} catch(Exception e) {
			log.error("showEtbProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品
	 * @return
	 */
	public String queryEtbProductAction() {
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
			log.error("queryEtbProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnEtbProductAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnEtbProductAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	//产品选择页面========================
	/**
	 * 显示产品选择页面
	 * @return
	 */
	public String showEtbProductSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showEtbProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询产品（选择页面）
	 * @return
	 */
	public String queryEtbProductSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryEtbProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页产品（选择页面）
	 * @return
	 */
	public String turnEtbProductSelectPage() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnEtbProductSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
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
	
	/**
	 * 验证数据
	 * @param purchase
	 * @return
	 */
	private boolean checkData(EtbProductDto product) {
		if(product == null) {
			this.addActionMessage("类型不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getFieldno())) {
			this.addActionMessage("请选择类型！");
			return false;
		}
		if(StringUtil.isBlank(product.getBrand())) {
			this.addActionMessage("品牌不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getTradename())) {
			this.addActionMessage("品名不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getTypeno())) {
			this.addActionMessage("规格不能为空！");
			return false;
		}
		if(StringUtil.isBlank(product.getColor())) {
			this.addActionMessage("请选择颜色！");
			return false;
		}
//		if(product.getSupplierid() == null) {
//			this.addActionMessage("请选择供应商！");
//			return false;
//		}
		if(product.getPurchaseprice() == null || product.getPurchaseprice().doubleValue() < 0) {
			this.addActionMessage("采购价必须为大于0的数！");
			return false;
		}
		if(product.getSalesprice() == null || product.getSalesprice().doubleValue() < 0) {
			this.addActionMessage("销售指导价必须为大于0的数！");
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
		//初期化字典数据
		initDictList();
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = etbProductService.queryEtbProductByPage(strFieldno, strKeyword, strTradename, strTypeno, strColor,
				strSupplierId, "" + Constants.STATUS_NORMAL, page);
		etbProductList = (List<EtbProductDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
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

	public EtbProductService getEtbProductService() {
		return etbProductService;
	}

	public void setEtbProductService(EtbProductService etbProductService) {
		this.etbProductService = etbProductService;
	}

	public String getStrFieldno() {
		return strFieldno;
	}

	public String getStrKeyword() {
		return strKeyword;
	}

	public void setStrFieldno(String strFieldno) {
		this.strFieldno = strFieldno;
	}

	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}

	public List<EtbProductDto> getEtbProductList() {
		return etbProductList;
	}

	public void setEtbProductList(List<EtbProductDto> etbProductList) {
		this.etbProductList = etbProductList;
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

	public EtbProductDto getAddEtbProductDto() {
		return addEtbProductDto;
	}

	public void setAddEtbProductDto(EtbProductDto addEtbProductDto) {
		this.addEtbProductDto = addEtbProductDto;
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

	public String getUpdEtbProductId() {
		return updEtbProductId;
	}

	public void setUpdEtbProductId(String updEtbProductId) {
		this.updEtbProductId = updEtbProductId;
	}

	public EtbProductDto getUpdEtbProductDto() {
		return updEtbProductDto;
	}

	public void setUpdEtbProductDto(EtbProductDto updEtbProductDto) {
		this.updEtbProductDto = updEtbProductDto;
	}

	public String getDelEtbProductId() {
		return delEtbProductId;
	}

	public void setDelEtbProductId(String delEtbProductId) {
		this.delEtbProductId = delEtbProductId;
	}

	public String getStrSupplierId() {
		return strSupplierId;
	}

	public void setStrSupplierId(String strSupplierId) {
		this.strSupplierId = strSupplierId;
	}

	public String getStrSeq() {
		return strSeq;
	}

	public void setStrSeq(String strSeq) {
		this.strSeq = strSeq;
	}

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrTradename() {
		return strTradename;
	}

	public void setStrTradename(String strTradename) {
		this.strTradename = strTradename;
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
