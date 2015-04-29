package com.cn.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action基类
 * @author Frank
 * @time 2013-9-24下午8:36:07
 * @version 1.0
 */
public class BaseAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {

	private static final long serialVersionUID = -7904140128742149982L;

	protected HttpServletResponse response;

	protected HttpServletRequest request;

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	/**
	 * 获得访问者IP
	 * @return
	 */
	protected String getIP() {
		String ip = request.getHeader("x-forwarded-for") == null ? request
				.getRemoteAddr() : request.getHeader("x-forwarded-for");
		return ip;
	}
}
