package com.cn.dsyg.action;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;


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

	public String showCalendarAction(){  
    	try{
	        return SUCCESS;  
		} catch(Exception e) {
			return ERROR;
		}
	}

}
