package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Page;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.UserService;

/**
 * @name UserAction.java
 * @author Frank
 * @time 2015-7-18下午10:34:37
 * @version 1.0
 */
public class UserAction extends BaseAction {

	private static final Logger log = LogManager.getLogger(UserAction.class);
	private static final long serialVersionUID = -36067602288448902L;
	
	private UserService userService;

	//用户选择页面=================
	//用户名
	private String strSelectUserName;
	//用户列表
	private List<UserDto> listSelectUser;
	//页码
	private int selectStartIndex;
	//翻页page
	private Page selectPage;
	//一页显示记录数
	private Integer intSelectPageSize;
	//用户选择页面=================
	
	//用户选择页面start
	/**
	 * 显示用户选择页面
	 * @return
	 */
	public String showSelectUserAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			strSelectUserName = "";
			//默认10条
			intSelectPageSize = 10;
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("showSelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 用户查询（用户选择页面）
	 * @return
	 */
	public String querySelectUserAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			//默认10条
			if(intSelectPageSize == null) {
				intSelectPageSize = 10;
			}
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("querySelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（用户选择页面）
	 * @return
	 */
	public String turnSelectUserAction() {
		try {
			this.clearMessages();
			//查询数据
			querySelectUser();
		} catch(Exception e) {
			log.error("turnSelectUserAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//用户选择页面end
	
	/**
	 * 翻页查询用户列表（用户选择页面用）
	 */
	@SuppressWarnings("unchecked")
	private void querySelectUser() {
		listSelectUser = new ArrayList<UserDto>();
		if(selectPage == null) {
			selectPage = new Page(intSelectPageSize);
		}
		//翻页查询所有用户
		this.selectPage.setStartIndex(selectStartIndex);
		selectPage = userService.queryUserByPage(strSelectUserName, selectPage);
		listSelectUser = (List<UserDto>) selectPage.getItems();
		
		this.setSelectStartIndex(selectPage.getStartIndex());
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getStrSelectUserName() {
		return strSelectUserName;
	}

	public void setStrSelectUserName(String strSelectUserName) {
		this.strSelectUserName = strSelectUserName;
	}

	public List<UserDto> getListSelectUser() {
		return listSelectUser;
	}

	public void setListSelectUser(List<UserDto> listSelectUser) {
		this.listSelectUser = listSelectUser;
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
}
