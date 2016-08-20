package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.CustomerService;
import com.cn.dsyg.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户Action
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1894353012013584929L;

	private static final Logger log = LogManager.getLogger(CustomerAction.class);
	
	private CustomerService customerService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	//一页显示数据条数
	private Integer intPageSize;
	
	/**
	 * 客户列表
	 */
	private List<CustomerDto> listCustomer;
	
	/**
	 * 客户编号（起）
	 */
	private String strCustomerNoLow;
	
	/**
	 * 客户编号（终）
	 */
	private String strCustomerNoHigh;
	
	/**
	 * 客户名称
	 */
	private String strCustomerName;
	
	/**
	 * 新增客户对象
	 */
	private CustomerDto addCustomerDto;
	
	/**
	 * 修改的客户编号
	 */
	private String updateCustomerNo;
	
	/**
	 * 修改客户对象
	 */
	private CustomerDto updateCustomerDto;
	
	/**
	 * 删除的客户编号
	 */
	private String delCustomerNo;
	
	/**
	 * ajax查询条件-客户编号
	 */
	private String queryCustomerNo;
	
	//客户查询页面（共通）
	/**
	 * 客户信息页码
	 */
	private int startIndexCustomer;
	
	/**
	 * 客户信息翻页
	 */
	private Page pageCustomer;
	
	private List<CustomerDto> customerList;
	
	private String customerNoLow;
	
	private String customerNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;
	
	//客户选择页面=================
	//客户名
	private String strSelectCustomerName;
	//客户列表
	private List<CustomerDto> listSelectCustomer;
	//页码
	private int selectStartIndex;
	//翻页page
	private Page selectPage;
	//一页显示记录数
	private Integer intSelectPageSize;
	//客户选择页面=================
	

	//页面显示的用户列表
	private List<UserDto> userList;
	private String strKeyword;	
	private String strFieldno;
	private String strUserIdFrom;
	private String strUserIdTo;
	private UserService userService;
	private String customerindex;

	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getStrFieldno() {
		return strFieldno;
	}
	public void setStrFieldno(String strFieldno) {
		this.strFieldno = strFieldno;
	}
	public String getStrUserIdFrom() {
		return strUserIdFrom;
	}
	public void setStrUserIdFrom(String strUserIdFrom) {
		this.strUserIdFrom = strUserIdFrom;
	}
	public String getStrUserIdTo() {
		return strUserIdTo;
	}
	public void setStrUserIdTo(String strUserIdTo) {
		this.strUserIdTo = strUserIdTo;
	}

	public String getStrKeyword() {
		return strKeyword;
	}
	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}
	public List<UserDto> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	public String getCustomerindex() {
		return customerindex;
	}
	public void setCustomerindex(String customerindex) {
		this.customerindex = customerindex;
	}
	
	//客户选择页面start
	/**
	 * 显示客户选择页面
	 * @return
	 */
	public String showSelectCustomerAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			strSelectCustomerName = "";
			//默认10条
			intSelectPageSize = 10;
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectCustomer();
		} catch(Exception e) {
			log.error("showSelectCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 客户查询（客户选择页面）
	 * @return
	 */
	public String querySelectCustomerAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			//默认10条
			if(intSelectPageSize == null) {
				intSelectPageSize = 10;
			}
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectCustomer();
		} catch(Exception e) {
			log.error("querySelectCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（客户选择页面）
	 * @return
	 */
	public String turnSelectCustomerAction() {
		try {
			this.clearMessages();
			//查询数据
			querySelectCustomer();
		} catch(Exception e) {
			log.error("turnSelectCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//客户选择页面end

	/**
	 * 显示客户页面
	 * @return
	 */
	public String showEtbCustomerAction() {
		try {
			this.clearMessages();
			strCustomerNoLow = "";
			strCustomerNoHigh = "";
			strCustomerName = "";
			addCustomerDto = new CustomerDto();
			updateCustomerDto = new CustomerDto();
			updateCustomerNo = "";
			delCustomerNo = "";
			
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listCustomer = new ArrayList<CustomerDto>();
			
			queryEtbCustomer();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户列表
	 * @return
	 */
	public String queryEtbCustomerList() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryEtbCustomer();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnEtbCustomerPage() {
		try {
			this.clearMessages();
			queryEtbCustomer();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有客户列表
	 */
	@SuppressWarnings("unchecked")
	private void queryEtbCustomer() {
		listCustomer = new ArrayList<CustomerDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有客户
		this.page.setStartIndex(startIndex);
		page = customerService.queryEtbCustomerByPage(page, strCustomerNoLow, strCustomerNoHigh, strCustomerName);
		listCustomer = (List<CustomerDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加客户页面
	 * @return
	 */
	public String showAddEtbCustomerAction() {
		try {
			this.clearMessages();
			addCustomerDto = new CustomerDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加客户
	 * @return
	 */
	public String addEtbCustomerAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addCustomerDto)) {
				return "checkerror";
			}
			log.info("addCustomerDto.getId()=" + addCustomerDto.getId());
			log.info("addCustomerDto.getCustomername()=" + addCustomerDto.getCustomername());
			//校验客户代码是否存在
			CustomerDto customer = customerService.queryAllEtbCustomerByID(addCustomerDto.getId()+"");
			if(customer != null) {
				this.addActionMessage("客户代码已经存在！");
				return "checkerror";
			}
			//校验客户名称是否存在
			CustomerDto customer1 = customerService.queryAllEtbCustomerByName(addCustomerDto.getCustomername()+"");
			if(customer1 != null) {
				this.addActionMessage("相同客户名称已经存在！");
				return "checkerror";
			}
			//保存数据
			addCustomerDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addCustomerDto.setCreateuid(username);
			addCustomerDto.setBelongto((String)ActionContext.getContext().getSession().get(Constants.SESSION_BELONGTO));
			customerService.insertEtbCustomer(addCustomerDto);
			this.addActionMessage("添加客户成功！");
			addCustomerDto = new CustomerDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加客户失败！");
			log.error("addEtbCustomerAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改客户页面
	 * @return
	 */
	public String showUpdEtbCustomerAction() {
		try {
			this.clearMessages();
//			System.out.println("id is: "+updateCustomerNo);
			updateCustomerDto = customerService.queryEtbCustomerByID(updateCustomerNo);
			if(updateCustomerDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询客户异常！");
			log.error("showUpdEtbCustomerAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}

	/**
	 * 显示详细客户页面
	 * @return
	 */
	public String showCustomerAction() {
		try {
			this.clearMessages();
//			System.out.println("id is: "+updateCustomerNo);
			updateCustomerDto = customerService.queryEtbCustomerByID(updateCustomerNo);
			if(updateCustomerDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询客户异常！");
			log.error("showCustomerAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}

	
	
	/**
	 * 修改客户
	 * @return
	 */
	public String updEtbCustomerAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateCustomerDto)) {
				return "checkerror";
			}
//			System.out.println("id is: "+updateCustomerDto.getId());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateCustomerDto.setUpdateuid(username);
			customerService.updateEtbCustomer(updateCustomerDto);
			this.addActionMessage("修改客户成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改客户失败！");
			log.error("updEtbCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改客户
	 * @return
	 */
	public String updEtbCustomerManageAction() {
		try {
			this.clearMessages();
			/*
			//数据校验
			if(!checkData(updateCustomerDto)) {
				return "checkerror";
			}*/
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			for(CustomerDto etbCustomerDto:listCustomer){
				if(etbCustomerDto.getCheckKey()!=null && etbCustomerDto.getCheckKey().equals("on")){
					etbCustomerDto.setUpdateuid(username);
					customerService.updateEtbCustomer(etbCustomerDto);
				}
			}
			this.addActionMessage("修改客户成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改客户失败！");
			log.error("updEtbCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除客户
	 * @return
	 */
	public String delEtbCustomerAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delCustomerNo)) {
				this.addActionMessage("客户代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			customerService.deleteEtbCustomer(delCustomerNo, username);
			this.addActionMessage("删除客户成功！");
			delCustomerNo = "";
			//刷新页面
			startIndex = 0;
			queryEtbCustomer();
		} catch(Exception e) {
			log.error("delEtbCustomerAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询客户列表（客户选择页面用）
	 */
	@SuppressWarnings("unchecked")
	private void querySelectCustomer() {
		listSelectCustomer = new ArrayList<CustomerDto>();
		if(selectPage == null) {
			selectPage = new Page(intSelectPageSize);
		}
		//翻页查询所有客户
		this.selectPage.setStartIndex(selectStartIndex);
		
		selectPage = customerService.queryEtbCustomerByPage(selectPage, "", "", strSelectCustomerName);
		listSelectCustomer = (List<CustomerDto>) selectPage.getItems();
		
		this.setSelectStartIndex(selectPage.getStartIndex());
	}
	    
	//用户选择页面========================
	/**
	* 显示用户选择页面
	* @return
	*/
	public String showUserSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryUserData();
		} catch(Exception e) {
			log.error("showUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	* 查询用户（选择页面）
	* @return
	*/
	public String queryUserSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryUserData();
		} catch(Exception e) {
			log.error("queryUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	* 翻页用户（选择页面）
	* @return
	*/
	public String turnUserSelectPage() {
		try {
			System.out.println("turnUserSelectPage");
			this.clearMessages();
			queryUserData();
		} catch(Exception e) {
			log.error("turnUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	* 数据查询
	*/
	@SuppressWarnings("unchecked")
	private void queryUserData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		System.out.println("strUserIdFrom:" +strUserIdFrom);
		System.out.println("strUserIdTo:" +strUserIdTo);
		page = userService.queryUserByPage(strFieldno, strKeyword, strUserIdFrom, strUserIdTo, "" + Constants.STATUS_NORMAL, page);

		userList = (List<UserDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}
	
	//用户选择页面========================
	
	/**
	 * 验证数据格式
	 * @param customer
	 * @return
	 */
	private boolean checkData(CustomerDto customer) {
		if(customer == null) {
			this.addActionMessage("客户代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(customer.getId()+"")) {
			this.addActionMessage("客户代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(customer.getCustomername())) {
			this.addActionMessage("客户名称不能为空！");
			return false;
		}
		if(customer.getCustomername().length() > 40) {
			this.addActionMessage("客户名称不能超过40个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(customer.getNote()) && customer.getNote().length() > 100) {
			this.addActionMessage("备考不能超过100个字符！");
			return false;
		}
		return true;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<CustomerDto> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<CustomerDto> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public String getStrCustomerNoLow() {
		return strCustomerNoLow;
	}

	public void setStrCustomerNoLow(String strCustomerNoLow) {
		this.strCustomerNoLow = strCustomerNoLow;
	}

	public String getStrCustomerNoHigh() {
		return strCustomerNoHigh;
	}

	public void setStrCustomerNoHigh(String strCustomerNoHigh) {
		this.strCustomerNoHigh = strCustomerNoHigh;
	}

	public CustomerDto getAddCustomerDto() {
		return addCustomerDto;
	}

	public void setAddCustomerDto(CustomerDto addCustomerDto) {
		this.addCustomerDto = addCustomerDto;
	}

	public CustomerDto getUpdateCustomerDto() {
		return updateCustomerDto;
	}

	public void setUpdateCustomerDto(CustomerDto updateCustomerDto) {
		this.updateCustomerDto = updateCustomerDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateCustomerNo() {
		return updateCustomerNo;
	}

	public void setUpdateCustomerNo(String updateCustomerNo) {
		this.updateCustomerNo = updateCustomerNo;
	}

	public String getDelCustomerNo() {
		return delCustomerNo;
	}

	public void setDelCustomerNo(String delCustomerNo) {
		this.delCustomerNo = delCustomerNo;
	}

	public String getQueryCustomerNo() {
		return queryCustomerNo;
	}

	public void setQueryCustomerNo(String queryCustomerNo) {
		this.queryCustomerNo = queryCustomerNo;
	}

	public int getStartIndexCustomer() {
		return startIndexCustomer;
	}

	public void setStartIndexCustomer(int startIndexCustomer) {
		this.startIndexCustomer = startIndexCustomer;
	}

	public Page getPageCustomer() {
		return pageCustomer;
	}

	public void setPageCustomer(Page pageCustomer) {
		this.pageCustomer = pageCustomer;
	}

	public List<CustomerDto> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerDto> customerList) {
		this.customerList = customerList;
	}

	public String getCustomerNoLow() {
		return customerNoLow;
	}

	public void setCustomerNoLow(String customerNoLow) {
		this.customerNoLow = customerNoLow;
	}

	public String getCustomerNoHigh() {
		return customerNoHigh;
	}

	public void setCustomerNoHigh(String customerNoHigh) {
		this.customerNoHigh = customerNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getStrSelectCustomerName() {
		return strSelectCustomerName;
	}

	public void setStrSelectCustomerName(String strSelectCustomerName) {
		this.strSelectCustomerName = strSelectCustomerName;
	}

	public List<CustomerDto> getListSelectCustomer() {
		return listSelectCustomer;
	}

	public void setListSelectCustomer(List<CustomerDto> listSelectCustomer) {
		this.listSelectCustomer = listSelectCustomer;
	}

	public int getSelectStartIndex() {
		return selectStartIndex;
	}

	public void setSelectStartIndex(int selectStartIndex) {
		this.selectStartIndex = selectStartIndex;
	}

	public Page getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(Page selectPage) {
		this.selectPage = selectPage;
	}

	public Integer getIntSelectPageSize() {
		return intSelectPageSize;
	}

	public void setIntSelectPageSize(Integer intSelectPageSize) {
		this.intSelectPageSize = intSelectPageSize;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}
}
