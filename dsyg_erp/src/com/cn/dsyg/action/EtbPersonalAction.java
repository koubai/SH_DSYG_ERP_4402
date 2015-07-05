package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.EtbPersonalDto;
import com.cn.dsyg.service.PersonalService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 员工档案Action
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbPersonalAction extends BaseAction {

	private static final long serialVersionUID = -2625985468676301647L;

	private static final Logger log = LogManager.getLogger(EtbPersonalAction.class);
	
	private PersonalService personalService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	/**
	 * 员工档案列表
	 */
	private List<EtbPersonalDto> listPersonal;
	
	/**
	 * 员工档案编号（起）
	 */
	private String strUserNoLow;
	
	/**
	 * 员工档案编号（终）
	 */
	private String strUserNoHigh;
	
	/**
	 * 员工姓名
	 */
	private String strUserName;
	
	/**
	 * 新增员工档案对象
	 */
	private EtbPersonalDto addPersonalDto;
	
	/**
	 * 修改的员工ID
	 */
	private String updateUserNo;
	
	/**
	 * 修改员工档案对象
	 */
	private EtbPersonalDto updatePersonalDto;
	
	/**
	 * 删除的员工ID
	 */
	private String delUserNo;
	
	/**
	 * ajax查询条件-员工ID
	 */
	private String queryUserNo;
	
	//员工档案查询页面（共通）
	/**
	 * 员工档案信息页码
	 */
	private int startIndexPersonal;
	
	/**
	 * 员工档案信息翻页
	 */
	private Page pagePersonal;
	
	private List<EtbPersonalDto> personalList;
	
	private String userNoLow;
	
	private String userNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;

	/**
	 * 显示员工档案页面
	 * @return
	 */
	public String showEtbPersonalAction() {
		try {
			this.clearMessages();
			strUserNoLow = "";
			strUserNoHigh = "";
			strUserName = "";
			addPersonalDto = new EtbPersonalDto();
			updatePersonalDto = new EtbPersonalDto();
			updateUserNo = "";
			delUserNo = "";
			page = new Page();
			startIndex = 0;
			listPersonal = new ArrayList<EtbPersonalDto>();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询员工档案列表
	 * @return
	 */
	public String queryEtbPersonalList() {
		try {
			this.clearMessages();
			page = new Page();
			startIndex = 0;
			queryEtbPersonal();
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
	public String turnEtbPersonalPage() {
		try {
			this.clearMessages();
			queryEtbPersonal();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有员工档案列表
	 */
	@SuppressWarnings("unchecked")
	private void queryEtbPersonal() {
		listPersonal = new ArrayList<EtbPersonalDto>();
		if(page == null) {
			page = new Page();
		}
		//翻页查询所有员工档案
		this.page.setStartIndex(startIndex);
		page = personalService.queryEtbPersonalByPage(page, strUserNoLow, strUserNoHigh, strUserName);
		listPersonal = (List<EtbPersonalDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加员工档案页面
	 * @return
	 */
	public String showAddEtbPersonalAction() {
		try {
			this.clearMessages();
			addPersonalDto = new EtbPersonalDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加员工档案
	 * @return
	 */
	public String addEtbPersonalAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addPersonalDto)) {
				return "checkerror";
			}
			log.info("addPersonalDto.getUserno()=" + addPersonalDto.getUserno());
			log.info("addPersonalDto.getUsername()=" + addPersonalDto.getUsername());
			//校验员工编号是否存在
			EtbPersonalDto personal = personalService.queryAllEtbPersonalByID(addPersonalDto.getUserno()+"");
			if(personal != null) {
				this.addActionMessage("员工ID已经存在！");
				return "checkerror";
			}
			//保存数据
			addPersonalDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addPersonalDto.setCreateuid(username);
			personalService.insertEtbPersonal(addPersonalDto);
			this.addActionMessage("添加员工档案成功！");
			addPersonalDto = new EtbPersonalDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加员工档案失败！");
			log.error("addEtbPersonalAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改员工档案页面
	 * @return
	 */
	public String showUpdEtbPersonalAction() {
		try {
			this.clearMessages();
			System.out.println("userNo is: "+updateUserNo);
			updatePersonalDto = personalService.queryEtbPersonalByID(updateUserNo);
			if(updatePersonalDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询员工档案异常！");
			log.error("showUpdEtbPersonalAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改员工档案
	 * @return
	 */
	public String updEtbPersonalAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updatePersonalDto)) {
				return "checkerror";
			}
			System.out.println("userNo is: "+updatePersonalDto.getUserno());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updatePersonalDto.setUpdateuid(username);
			personalService.updateEtbPersonal(updatePersonalDto);
			this.addActionMessage("修改员工档案成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改员工档案失败！");
			log.error("updEtbPersonalAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除员工档案
	 * @return
	 */
	public String delEtbPersonalAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delUserNo)) {
				this.addActionMessage("员工ID为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			personalService.deleteEtbPersonal(delUserNo, username);
			this.addActionMessage("删除员工档案成功！");
			delUserNo = "";
			//刷新页面
			startIndex = 0;
			queryEtbPersonal();
		} catch(Exception e) {
			log.error("delEtbPersonalAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param personal
	 * @return
	 */
	private boolean checkData(EtbPersonalDto personal) {
		if(personal == null) {
			this.addActionMessage("员工编号不能为空！");
			return false;
		}
		if(StringUtil.isBlank(personal.getUserid()+"")) {
			this.addActionMessage("员工编号不能为空！");
			return false;
		}
		if(StringUtil.isBlank(personal.getUsername())) {
			this.addActionMessage("员工姓名不能为空！");
			return false;
		}
		if(personal.getUsername().length() > 32) {
			this.addActionMessage("员工姓名不能超过32个字符！");
			return false;
		}
		/*
		if(StringUtil.isNotBlank(personal.getRegistdate()) && !DateUtil.isDate(personal.getRegistdate().toString())) {
			this.addActionMessage("登记时间格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(personal.getEmployeddate()) && !DateUtil.isDate(personal.getEmployeddate().toString())) {
			this.addActionMessage("入职时间格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(personal.getRetiredate()) && !DateUtil.isDate(personal.getRetiredate().toString())) {
			this.addActionMessage("离职时间格式不正确！");
			return false;
		}*/
		if(StringUtil.isNotBlank(personal.getPersonaldesc()) && personal.getPersonaldesc().length() > 500) {
			this.addActionMessage("档案明细不能超过500个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(personal.getNote()) && personal.getNote().length() > 500) {
			this.addActionMessage("内容介绍不能超过500个字符！");
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

	public List<EtbPersonalDto> getListPersonal() {
		return listPersonal;
	}

	public void setListPersonal(List<EtbPersonalDto> listPersonal) {
		this.listPersonal = listPersonal;
	}

	public String getStrUserNoLow() {
		return strUserNoLow;
	}

	public void setStrUserNoLow(String strUserNoLow) {
		this.strUserNoLow = strUserNoLow;
	}

	public String getStrUserNoHigh() {
		return strUserNoHigh;
	}

	public void setStrUserNoHigh(String strUserNoHigh) {
		this.strUserNoHigh = strUserNoHigh;
	}

	public EtbPersonalDto getAddPersonalDto() {
		return addPersonalDto;
	}

	public void setAddPersonalDto(EtbPersonalDto addPersonalDto) {
		this.addPersonalDto = addPersonalDto;
	}

	public EtbPersonalDto getUpdatePersonalDto() {
		return updatePersonalDto;
	}

	public void setUpdatePersonalDto(EtbPersonalDto updatePersonalDto) {
		this.updatePersonalDto = updatePersonalDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}

	public String getDelUserNo() {
		return delUserNo;
	}

	public void setDelUserNo(String delUserNo) {
		this.delUserNo = delUserNo;
	}

	public String getQueryUserNo() {
		return queryUserNo;
	}

	public void setQueryUserNo(String queryUserNo) {
		this.queryUserNo = queryUserNo;
	}

	public int getStartIndexPersonal() {
		return startIndexPersonal;
	}

	public void setStartIndexPersonal(int startIndexPersonal) {
		this.startIndexPersonal = startIndexPersonal;
	}

	public Page getPagePersonal() {
		return pagePersonal;
	}

	public void setPagePersonal(Page pagePersonal) {
		this.pagePersonal = pagePersonal;
	}

	public List<EtbPersonalDto> getPersonalList() {
		return personalList;
	}

	public void setPersonalList(List<EtbPersonalDto> personalList) {
		this.personalList = personalList;
	}

	public String getUserNoLow() {
		return userNoLow;
	}

	public void setUserNoLow(String userNoLow) {
		this.userNoLow = userNoLow;
	}

	public String getUserNoHigh() {
		return userNoHigh;
	}

	public void setUserNoHigh(String userNoHigh) {
		this.userNoHigh = userNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrUserName() {
		return strUserName;
	}

	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}

	public PersonalService getPersonalService() {
		return personalService;
	}

	public void setPersonalService(PersonalService personalService) {
		this.personalService = personalService;
	}
}
