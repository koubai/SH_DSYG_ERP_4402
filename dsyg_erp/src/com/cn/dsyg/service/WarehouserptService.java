package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.WarehouserptDto;

/**
 * @name WarehouserptService.java
 * @author Frank
 * @time 2015-6-3下午9:49:20
 * @version 1.0
 */
public interface WarehouserptService {
	
	/**
	 * 翻页查询
	 * @param status
	 * @param warehousetype
	 * @param warehouseno
	 * @param theme1
	 * @param parentid
	 * @param supplierid
	 * @param productid
	 * @param page
	 * @return
	 */
	public Page queryWarehouserptByPage(String status, String warehousetype,
			String warehouseno, String theme1, String parentid, String supplierid,
			String productid, Page page);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public WarehouserptDto queryWarehouserptByID(String id);
	
	/**
	 * 新增数据
	 * @param warehouserpt
	 */
	public void insertWarehouserpt(WarehouserptDto warehouserpt);
	
	/**
	 * 修改数据
	 * @param warehouserpt
	 * @param type 1为入库单，2为出库单
	 */
	public void updateWarehouserpt(WarehouserptDto warehouserpt, Integer type);
}
