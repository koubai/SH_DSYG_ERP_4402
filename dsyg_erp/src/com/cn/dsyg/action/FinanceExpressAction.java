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
import com.cn.dsyg.dto.FinanceDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.FinanceService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name FinanceExpressAction.java
 * @author Frank
 * @time 2015-9-9下午11:06:30
 * @version 1.0
 */
public class FinanceExpressAction extends BaseAction {

	private static final long serialVersionUID = -2791663852145584131L;
	private static final Logger log = LogManager.getLogger(FinanceExpressAction.class);
	
	private FinanceService financeService;
	private Dict01Service dict01Service;

	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	private List<FinanceDto> financeList;
	
	//财务主题字典
	private List<Dict01Dto> financeDictList;
	
	//查询条件
	//单据日期起
	private String strReceiptdateLow;
	//单据日期终
	private String strReceiptdateHigh;
	
	//快递单号
	private String strExpressno;
	//客户名称
	private String strCustomerName;
	//主题（快递名称）
	private String strExpressName;

	//新增
	private FinanceDto addFinanceDto;
	
	//修改
	private FinanceDto updFinanceDto;
	private String updFinanceId;
	
	//删除
	private String delFinanceId;
	
	/**
	 * 删除快递单信息
	 * @return
	 */
	public String delFinanceExpressAction() {
		try {
			this.clearMessages();
			//这里只可以删除快递单管理页面新增的信息（没有关联单据号的数据）
			FinanceDto finance = financeService.queryFinanceByID(delFinanceId);
			if(finance != null) {
				if(StringUtil.isNotBlank(finance.getInvoiceid())) {
					this.addActionMessage("该记录有关联单据号，不能删除！");
				}
				//删除
				financeService.deleteFinance(delFinanceId);
				this.addActionMessage("删除成功！");
				//刷新页面
				queryData();
			}
		} catch(Exception e) {
			log.error("showUpdFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改快递单页面
	 * @return
	 */
	public String showUpdFinanceExpressAction() {
		try {
			this.clearMessages();
			updFinanceDto = financeService.queryFinanceByID(updFinanceId);
		} catch(Exception e) {
			log.error("showUpdFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改快递单
	 * @return
	 */
	public String updFinanceExpressAction() {
		try {
			this.clearMessages();
			//数据验证
			if(!checkData(updFinanceDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updFinanceDto.setUpdateuid(username);
			financeService.updateFinance(updFinanceDto);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增快递单页面
	 * @return
	 */
	public String showAddFinanceExpressAction() {
		try {
			this.clearMessages();
			addFinanceDto = new FinanceDto();
			//默认为新增
			addFinanceDto.setStatus(Constants.FINANCE_STATUS_NEW);
		} catch(Exception e) {
			log.error("showAddFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增快递单
	 * @return
	 */
	public String addFinanceExpressAction() {
		try {
			this.clearMessages();
			//数据验证
			if(!checkData(addFinanceDto)) {
				return "checkerror";
			}
			addFinanceDto.setFinancetype(Constants.FINANCE_TYPE_DELIVERY);
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addFinanceDto.setUpdateuid(username);
			addFinanceDto.setCreateuid(username);
			//付款
			addFinanceDto.setMode("2");
			//经手人
			addFinanceDto.setHandler(username);
			//结算日期=当天
			Date date = new Date();
			addFinanceDto.setAccountdate(DateUtil.dateToShortStr(date));
			//状态=新增
			//addFinanceDto.setStatus(Constants.FINANCE_STATUS_NEW);
			String no = financeService.insertFinance(addFinanceDto);
			this.addActionMessage("添加成功！账目编号为：" + no);
			addFinanceDto = new FinanceDto();
		} catch(Exception e) {
			log.error("addFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示财务快递单列表页面
	 * @return
	 */
	public String showFinanceExpressAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strReceiptdateLow = "";
			strReceiptdateHigh = "";
			strExpressno = "";
			strExpressName = "";
			strCustomerName = "";
			
			financeList = new ArrayList<FinanceDto>();
			
			queryData();
		} catch(Exception e) {
			log.error("showFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询财务快递单
	 * @return
	 */
	public String queryFinanceExpressAction() {
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
			log.error("queryFinanceExpressAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnFinanceExpressAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnFinanceExpressAction error:" + e);
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
		//财务主题
		financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		//这里只查询快递财务记录
		page = financeService.queryFinanceByPage(strExpressno, "", "" + Constants.FINANCE_TYPE_DELIVERY, "",
				"", "", strReceiptdateLow, strReceiptdateHigh, "", strCustomerName, strExpressName, page);
		financeList = (List<FinanceDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 验证数据
	 * @param finance
	 * @return
	 */
	private boolean checkData(FinanceDto finance) {
		if(finance == null) {
			this.addActionMessage("请选择快递！");
			return false;
		}
		if(finance.getCustomerid() == null) {
			this.addActionMessage("请选择快递！");
			return false;
		}
		if(StringUtil.isBlank(finance.getCustomername())) {
			this.addActionMessage("快递名称不能为空！");
			return false;
		}
/*		if(StringUtil.isBlank(finance.getCustomeraddress())) {
			this.addActionMessage("快递地址不能为空！");
			return false;
		}
*/		
		if(finance.getAmount() == null) {
			this.addActionMessage("转运费用合计不能为空！");
			return false;
		}
		if(StringUtil.isBlank(finance.getCustomermanager())) {
			this.addActionMessage("快递联系人不能为空！");
			return false;
		}
		if(StringUtil.isBlank(finance.getCustomertel())) {
			this.addActionMessage("快递联系人电话不能为空！");
			return false;
		}
		if(StringUtil.isBlank(finance.getReceiptdate())) {
			this.addActionMessage("单据日期不能为空！");
			return false;
		}
/*		if(StringUtil.isBlank(finance.getCustomermail())) {
			this.addActionMessage("信箱不能为空！");
			return false;
		}
*/		
		if(finance.getStatus() == null) {
			this.addActionMessage("请选择状态！");
			return false;
		}
		return true;
	}

	public FinanceService getFinanceService() {
		return financeService;
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
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

	public List<FinanceDto> getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List<FinanceDto> financeList) {
		this.financeList = financeList;
	}

	public List<Dict01Dto> getFinanceDictList() {
		return financeDictList;
	}

	public void setFinanceDictList(List<Dict01Dto> financeDictList) {
		this.financeDictList = financeDictList;
	}

	public String getStrReceiptdateLow() {
		return strReceiptdateLow;
	}

	public void setStrReceiptdateLow(String strReceiptdateLow) {
		this.strReceiptdateLow = strReceiptdateLow;
	}

	public String getStrReceiptdateHigh() {
		return strReceiptdateHigh;
	}

	public void setStrReceiptdateHigh(String strReceiptdateHigh) {
		this.strReceiptdateHigh = strReceiptdateHigh;
	}

	public FinanceDto getAddFinanceDto() {
		return addFinanceDto;
	}

	public void setAddFinanceDto(FinanceDto addFinanceDto) {
		this.addFinanceDto = addFinanceDto;
	}

	public FinanceDto getUpdFinanceDto() {
		return updFinanceDto;
	}

	public void setUpdFinanceDto(FinanceDto updFinanceDto) {
		this.updFinanceDto = updFinanceDto;
	}

	public String getUpdFinanceId() {
		return updFinanceId;
	}

	public void setUpdFinanceId(String updFinanceId) {
		this.updFinanceId = updFinanceId;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public String getStrExpressno() {
		return strExpressno;
	}

	public void setStrExpressno(String strExpressno) {
		this.strExpressno = strExpressno;
	}

	public String getStrExpressName() {
		return strExpressName;
	}

	public void setStrExpressName(String strExpressName) {
		this.strExpressName = strExpressName;
	}

	public String getDelFinanceId() {
		return delFinanceId;
	}

	public void setDelFinanceId(String delFinanceId) {
		this.delFinanceId = delFinanceId;
	}

}
