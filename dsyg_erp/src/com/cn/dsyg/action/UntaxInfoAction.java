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
import com.cn.dsyg.dto.UntaxInfoDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.ProductService;
import com.cn.dsyg.service.UntaxInfoService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name UntaxInfoAction.java
 * @author Pei
 * @time 2016-4-25下午5:59:54
 * @version 1.0
 */
public class UntaxInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -937579539202210904L;

	private static final Logger log = LogManager.getLogger(UntaxInfoAction.class);
	
	private ProductService productService;
	private UntaxInfoService untaxinfoService;
	private Dict01Service dict01Service;

	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	private List<UntaxInfoDto> untaxInfoList;
	
	//查询条件
	//品名
	private String strTradename;
	//客户名
	private String strCustomername;
	
	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	//新增
	private UntaxInfoDto addUntaxInfoDto;
	
	//修改
	private UntaxInfoDto updUntaxInfoDto;
	private String updUntaxInfoId;
	//删除
	private UntaxInfoDto delUntaxInfoDto;
	private String delUntaxInfoId;
	
	//
	private String strProductid;
	
	/**
	 * 显示修改页面
	 * @return
	 */
	public String showUpdUntaxInfoAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			updUntaxInfoDto = untaxinfoService.queryUntaxInfoId(updUntaxInfoId);
		} catch(Exception e) {
			log.error("showUpdUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改样品
	 * @return
	 */
	public String updUntaxInfoAction() {
		try {
			this.clearMessages();
			log.error("updUntaxInfoAction AAA:");

			//数据验证
			if(!checkData(updUntaxInfoDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updUntaxInfoDto.setUpdateuid(username);
			untaxinfoService.updateUntaxInfo(updUntaxInfoDto);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delUntaxInfoAction() {
		try {			
			this.clearMessages();
			if(StringUtil.isBlank(delUntaxInfoId)) {
				this.addActionMessage("未税销售信息代码为空！");
				return "checkerror";
			}
			delUntaxInfoDto = untaxinfoService.queryUntaxInfoId(delUntaxInfoId);			
			delUntaxInfoDto.setStatus(Constants.STATUS_DEL);
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			delUntaxInfoDto.setUpdateuid(username);
			//删除
			untaxinfoService.updateUntaxInfo(delUntaxInfoDto);
			this.addActionMessage("删除未税销售信息成功！");
			delUntaxInfoId = "";
			//刷新页面
			startIndex = 0;
			queryData();
		} catch(Exception e) {
			log.error("delUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	
	/**
	 * 显示新增页面
	 * @return
	 */
	public String showAddUntaxInfoAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			addUntaxInfoDto = new UntaxInfoDto();
		} catch(Exception e) {
			log.error("showAddUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String addUntaxInfoAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();

			//数据验证
			if(!checkData(addUntaxInfoDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addUntaxInfoDto.setHandler(username);
			addUntaxInfoDto.setUpdateuid(username);
			addUntaxInfoDto.setCreateuid(username);
			addUntaxInfoDto.setRank(Constants.ROLE_RANK_OPERATOR);
			addUntaxInfoDto.setBelongto(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG));
			//默认状态=有效
			addUntaxInfoDto.setStatus(Constants.STATUS_NORMAL);
			untaxinfoService.insertUntaxInfo(addUntaxInfoDto);
			this.addActionMessage("添加成功！");
			addUntaxInfoDto = new UntaxInfoDto();
		} catch(Exception e) {
			log.error("addUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示管理页面
	 * @return
	 */
	public String showUntaxInfoAction() {
		try {
			this.clearMessages();
			updUntaxInfoId = "";
			strTradename = "";
			strCustomername = "";
			untaxInfoList = new ArrayList<UntaxInfoDto>();
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("showUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询
	 * @return
	 */
	public String queryUntaxInfoAction() {
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
			log.error("queryUntaxInfoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnUntaxInfoAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnUntaxInfoAction error:" + e);
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
		page = untaxinfoService.queryUntaxInfoByPage("", "1", strTradename, strCustomername, page);
		untaxInfoList = (List<UntaxInfoDto>) page.getItems();
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
	 * @param untaxinfo
	 * @return
	 */
	private boolean checkData(UntaxInfoDto untaxInfo) {
		if(untaxInfo == null) {
			this.addActionMessage("请选择产品！");
			return false;
		}
		if(StringUtil.isBlank(untaxInfo.getProductid())) {
			this.addActionMessage("请选择产品！");
			return false;
		}
		if(StringUtil.isBlank(untaxInfo.getCustomertype())) {
			this.addActionMessage("请选择类型！");
			return false;
		}
		if("1".equals(untaxInfo.getCustomertype())) {
			//供应商
			if(StringUtil.isBlank(untaxInfo.getCustomerid())) {
				this.addActionMessage("请选择供应商！");
				return false;
			}
			if(StringUtil.isBlank(untaxInfo.getCustomername())) {
				this.addActionMessage("请选择供应商！");
				return false;
			}
		} else if("2".equals(untaxInfo.getCustomertype())) {
			//客户
			if(StringUtil.isBlank(untaxInfo.getCustomerid())) {
				this.addActionMessage("请选择客户！");
				return false;
			}
			if(StringUtil.isBlank(untaxInfo.getCustomername())) {
				this.addActionMessage("请选择客户！");
				return false;
			}
		} else {
			this.addActionMessage("类型不正确！");
			return false;
		}
		if(StringUtil.isBlank(untaxInfo.getQuantity())) {
			this.addActionMessage("数量不能为空！");
			return false;
		}
		if(StringUtil.isBlank(untaxInfo.getNote())) {
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

	public UntaxInfoService getUntaxInfoService() {
		return untaxinfoService;
	}

	public void setUntaxInfoService(UntaxInfoService untaxInfoService) {
		this.untaxinfoService = untaxInfoService;
	}

	public List<UntaxInfoDto> getUntaxInfoList() {
		return untaxInfoList;
	}

	public void setUntaxInfoList(List<UntaxInfoDto> untaxInfoList) {
		this.untaxInfoList = untaxInfoList;
	}

	public UntaxInfoDto getAddUntaxInfoDto() {
		return addUntaxInfoDto;
	}

	public void setAddUntaxInfoDto(UntaxInfoDto addUntaxInfoDto) {
		this.addUntaxInfoDto = addUntaxInfoDto;
	}

	public UntaxInfoDto getUpdUntaxInfoDto() {
		return updUntaxInfoDto;
	}

	public void setUpdUntaxInfoDto(UntaxInfoDto updUntaxInfoDto) {
		this.updUntaxInfoDto = updUntaxInfoDto;
	}

	public String getUpdUntaxInfoId() {
		return updUntaxInfoId;
	}

	public void setUpdUntaxInfoId(String updUntaxInfoId) {
		this.updUntaxInfoId = updUntaxInfoId;
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

	public String getDelUntaxInfoId() {
		return delUntaxInfoId;
	}

	public void setDelUntaxInfoId(String delUntaxInfoId) {
		this.delUntaxInfoId = delUntaxInfoId;
	}

	public UntaxInfoDto getDelUntaxInfoDto() {
		return delUntaxInfoDto;
	}

	public void setDelUntaxInfoDto(UntaxInfoDto delUntaxInfoDto) {
		this.delUntaxInfoDto = delUntaxInfoDto;
	}

}
