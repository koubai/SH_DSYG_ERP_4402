package com.cn.dsyg.action;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.dsyg.dto.ResourceDto;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name FrameAction.java
 * @author Frank
 * @time 2013-10-14下午11:06:43
 * @version 1.0
 */
public class FrameAction extends BaseAction {

	private static final long serialVersionUID = 303939388808414480L;
	private static final Logger log = LogManager.getLogger(FrameAction.class);
	
	private List<ResourceDto> resourceList;
	
	/**
	 * 显示管理后台主页
	 * @return
	 */
	public String showManageHomeAction() {
		return SUCCESS;
	}
	
	/**
	 * 显示Top页面
	 * @return
	 */
	public String showTopAction() {
		return SUCCESS;
	}
	
	/**
	 * 显示Frame左边菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showLeftAction() {
		try {
			this.clearMessages();
			resourceList = (List<ResourceDto>) ActionContext.getContext().getSession().get(Constants.SESSION_RESOURCE_LIST);
		} catch(Exception e) {
			log.error("showManageHomeAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示Middle页面
	 * @return
	 */
	public String showMiddleAction() {
		return SUCCESS;
	}
	
	/**
	 * 显示MainFrame页面
	 * @return
	 */
	public String showMainFrameAction() {
		return SUCCESS;
	}

	public List<ResourceDto> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceDto> resourceList) {
		this.resourceList = resourceList;
	}
}
