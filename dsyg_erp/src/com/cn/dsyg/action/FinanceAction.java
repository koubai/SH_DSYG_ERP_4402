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
import com.cn.dsyg.service.WarehouserptService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name FinanceAction.java
 * @author Frank
 * @time 2015-6-27下午11:50:52
 * @version 1.0
 */
public class FinanceAction extends BaseAction {

	private static final long serialVersionUID = 1458515012654870733L;
	private static final Logger log = LogManager.getLogger(FinanceAction.class);

	private FinanceService financeService;
	private Dict01Service dict01Service;
	private WarehouserptService warehouserptService;
	
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
	//发票号
	private String strBillno;
	
	//新增
	private FinanceDto addFinanceDto;
	
	//修改
	private FinanceDto updFinanceDto;
	private String updFinanceId;
	
	private String updStatusFinanceId;
	private String updStatus;
	
	private String strBillno1;
	private String strBillno2;
	private String strBillno3;
	private String strReceiptdate;
	
	/**
	 * 更新财务记录状态（需输入发票号）
	 * @return
	 */
	public String updFinanceStatusBillnoAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			FinanceDto finance = financeService.queryFinanceByID(updStatusFinanceId);
			if(finance != null) {
				finance.setUpdateuid(username);
				finance.setStatus(Integer.valueOf(updStatus));
				
				//发票号
				String res10 = "";
				if(StringUtil.isNotBlank(strBillno1)) {
					res10 += strBillno1 + ";";
				}
				if(StringUtil.isNotBlank(strBillno2)) {
					res10 += strBillno2 + ";";
				}
				if(StringUtil.isNotBlank(strBillno3)) {
					res10 += strBillno3 + ";";
				}
				finance.setRes10(res10);
				//开票日期
				finance.setReceiptdate(strReceiptdate);
				
				financeService.updateFinance(finance);
			}
			//刷新页面
			queryData();
		} catch(Exception e) {
			log.error("updFinanceStatusBillnoAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新财务记录状态
	 * @return
	 */
	public String updFinanceStatusAction() {
		try {
			this.clearMessages();
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			FinanceDto finance = financeService.queryFinanceByID(updStatusFinanceId);
			if(finance != null) {
				finance.setUpdateuid(username);
				finance.setStatus(Integer.valueOf(updStatus));
				financeService.updateFinance(finance);
			}
			//刷新页面
			queryData();
		} catch(Exception e) {
			log.error("updFinanceStatusAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改财务记录页面
	 * @return
	 */
	public String showUpdFinanceAction() {
		try {
			this.clearMessages();
			//财务主题
			financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			updFinanceDto = financeService.queryFinanceByID(updFinanceId);
		} catch(Exception e) {
			log.error("showUpdFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改财务记录
	 * @return
	 */
	public String updFinanceAction() {
		try {
			this.clearMessages();
			//财务主题
			financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			//数据验证
			if(!checkData(updFinanceDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			
			//发票号
			String res10 = "";
			if(StringUtil.isNotBlank(strBillno1)) {
				res10 += strBillno1 + ";";
			}
			if(StringUtil.isNotBlank(strBillno2)) {
				res10 += strBillno2 + ";";
			}
			if(StringUtil.isNotBlank(strBillno3)) {
				res10 += strBillno3 + ";";
			}
			updFinanceDto.setRes10(res10);
			updFinanceDto.setBillno1(strBillno1);
			updFinanceDto.setBillno2(strBillno2);
			updFinanceDto.setBillno3(strBillno3);
			
			updFinanceDto.setUpdateuid(username);
			financeService.updateFinance(updFinanceDto);
			this.addActionMessage("修改成功！");
		} catch(Exception e) {
			log.error("updFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示新增财务记录页面
	 * @return
	 */
	public String showAddFinanceAction() {
		try {
			this.clearMessages();
			addFinanceDto = new FinanceDto();
			//财务主题
			financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			//默认收款
			addFinanceDto.setMode("1");
			//财务类型为手动输入
			addFinanceDto.setFinancetype(Constants.WAREHOUSERPT_TYPE_INPUT);
		} catch(Exception e) {
			log.error("showAddFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增财务记录
	 * @return
	 */
	public String addFinanceAction() {
		try {
			this.clearMessages();
			//财务主题
			financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			//数据验证
			if(!checkData(addFinanceDto)) {
				return "checkerror";
			}
			//当前操作用户ID
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			addFinanceDto.setCreateuid(username);
			addFinanceDto.setUpdateuid(username);
			String no = financeService.insertFinance(addFinanceDto);
			this.addActionMessage("添加成功！账目编号为：" + no);
			addFinanceDto = new FinanceDto();
			//默认收款
			addFinanceDto.setMode("1");
			//财务类型为手动输入
			addFinanceDto.setFinancetype(Constants.WAREHOUSERPT_TYPE_INPUT);
		} catch(Exception e) {
			log.error("addFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示账目管理页面
	 * @return
	 */
	public String showFinanceAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			strReceiptdateLow = "";
			strReceiptdateHigh = "";
			strBillno = "";
			financeList = new ArrayList<FinanceDto>();
		} catch(Exception e) {
			log.error("showFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询账目
	 * @return
	 */
	public String queryFinanceAction() {
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
			log.error("queryFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnFinanceAction() {
		try {
			this.clearMessages();
			//页面数据初期化
			queryData();
		} catch(Exception e) {
			log.error("turnFinanceAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据
	 * @param finance
	 * @return
	 */
	private boolean checkData(FinanceDto finance) {
		if(finance == null) {
			this.addActionMessage("经手人不能为空！！");
			return false;
		}
//		if(StringUtil.isBlank(finance.getReceiptid())) {
//			this.addActionMessage("账目编号不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(finance.getHandler())) {
			this.addActionMessage("经手人不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(finance.getInvoiceid())) {
//			this.addActionMessage("关联单据编号不能为空！");
//			return false;
//		}
//		if(StringUtil.isBlank(finance.getReceiptdate())) {
//			this.addActionMessage("单据日期不能为空！");
//			return false;
//		}
		if(finance.getFinancetype() == null) {
			this.addActionMessage("请选择主题！");
			return false;
		} else {
			if(finance.getFinancetype() == Constants.WAREHOUSERPT_TYPE_INPUT) {
				if(StringUtil.isBlank(finance.getTheme())) {
					this.addActionMessage("请选择主题！");
					return false;
				}
			}
		}
		if(StringUtil.isBlank(finance.getMode())) {
			this.addActionMessage("请选择方式！");
			return false;
		}
//		if(StringUtil.isBlank(finance.getCustomername())) {
//			this.addActionMessage("对象不能为空！");
//			return false;
//		}
//		if(StringUtil.isBlank(finance.getCustomermanager())) {
//			this.addActionMessage("联系人不能为空！");
//			return false;
//		}
		if(finance.getAmount() == null) {
			this.addActionMessage("金额合计不能为空！");
			return false;
		}
		if(StringUtil.isBlank(finance.getAccountdate())) {
			this.addActionMessage("结算日期不能为空！");
			return false;
		}
		if(finance.getStatus() == null) {
			this.addActionMessage("请选择状态！");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		strBillno1 = "";
		strBillno2 = "";
		strBillno3 = "";
		strReceiptdate = DateUtil.dateToShortStr(new Date());
		if(page == null) {
			page = new Page(intPageSize);
		}
		//财务主题
		financeDictList = dict01Service.queryDict01ByFieldcode(Constants.FINANCE_THEME, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//翻页查询所有委托公司
		this.page.setStartIndex(startIndex);
		page = financeService.queryFinanceByPage("", "", "",
				"", "", strReceiptdateLow, strReceiptdateHigh, strBillno, "", page);
		financeList = (List<FinanceDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	public FinanceService getFinanceService() {
		return financeService;
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
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

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public List<Dict01Dto> getFinanceDictList() {
		return financeDictList;
	}

	public void setFinanceDictList(List<Dict01Dto> financeDictList) {
		this.financeDictList = financeDictList;
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

	public WarehouserptService getWarehouserptService() {
		return warehouserptService;
	}

	public void setWarehouserptService(WarehouserptService warehouserptService) {
		this.warehouserptService = warehouserptService;
	}

	public String getUpdStatusFinanceId() {
		return updStatusFinanceId;
	}

	public void setUpdStatusFinanceId(String updStatusFinanceId) {
		this.updStatusFinanceId = updStatusFinanceId;
	}

	public String getUpdStatus() {
		return updStatus;
	}

	public void setUpdStatus(String updStatus) {
		this.updStatus = updStatus;
	}

	public String getStrBillno1() {
		return strBillno1;
	}

	public void setStrBillno1(String strBillno1) {
		this.strBillno1 = strBillno1;
	}

	public String getStrBillno2() {
		return strBillno2;
	}

	public void setStrBillno2(String strBillno2) {
		this.strBillno2 = strBillno2;
	}

	public String getStrBillno3() {
		return strBillno3;
	}

	public void setStrBillno3(String strBillno3) {
		this.strBillno3 = strBillno3;
	}

	public String getStrReceiptdate() {
		return strReceiptdate;
	}

	public void setStrReceiptdate(String strReceiptdate) {
		this.strReceiptdate = strReceiptdate;
	}

	public String getStrBillno() {
		return strBillno;
	}

	public void setStrBillno(String strBillno) {
		this.strBillno = strBillno;
	}
}
