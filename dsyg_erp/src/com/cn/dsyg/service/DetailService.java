package com.cn.dsyg.service;

import com.cn.common.util.Page;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface DetailService {
	
	/**
	 * 翻页查询客户
	 * @param page
	 * @param productid
	 * @return
	 */
	public Page queryDetailCustomerByPage(Page page, String productid);
	
	/**
	 * 翻页查询产品
	 * @param page
	 * @param customerno
	 * @return
	 */
	public Page queryDetailProductByPage(Page page, String customerno);
}
