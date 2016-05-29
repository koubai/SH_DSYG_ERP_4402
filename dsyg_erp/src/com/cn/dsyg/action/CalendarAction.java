package com.cn.dsyg.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.dsyg.dto.IssueDto;
import com.cn.dsyg.service.IssueService;


public class CalendarAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1598323357444100459L;

	/**
	 * 
	 */

	private static final Logger log = LogManager.getLogger(CalendarAction.class);

	private static final String SUCCESS = null;

	private static final String ERROR = null;
	

	/**
	 * 紧急事件列表
	 */
	private List<IssueDto> listIssue;
	private IssueService issueService;

	private boolean IssueFlg;
	
	public IssueService getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}

	public String showCalendarAction(){  
    	try{
//    		System.out.println("showCalendarAction");
//    		showIssueAction();
	        return SUCCESS;  
		} catch(Exception e) {
			return ERROR;
		}
	}

	public List<IssueDto> getListIssue() {
		return listIssue;
	}


	public void setListIssue(List<IssueDto> listIssue) {
		this.listIssue = listIssue;
	}

	/**
	 * 显示紧急事件页面
	 * @return
	 */
	public String showIssueAction() {
		try {
			this.clearMessages();
			queryIssue();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 翻页查询所有紧急事件列表
	 */
	@SuppressWarnings("unchecked")
	private void queryIssue() {
		//初期化字典数据
		listIssue = new ArrayList<IssueDto>();
		listIssue = issueService.queryIssueWorking();		
		if (listIssue.size()> 0){
			IssueFlg = true;
		}else{
			IssueFlg = false;			
		}
	}

	public boolean isIssueFlg() {
		return IssueFlg;
	}

	public void setIssueFlg(boolean issueFlg) {
		IssueFlg = issueFlg;
	}
}
