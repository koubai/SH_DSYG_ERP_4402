package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.EtbPurchaseDto;

/**
 * @name EtbPurchaseService.java
 * @author Frank
 * @time 2015-5-9下午10:26:10
 * @version 1.0
 */
public interface EtbPurchaseService {

	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param page
	 * @return
	 */
	public Page queryEtbPurchaseByPage(String purchasedateLow, String purchasedateHigh, Page page);
	
	/**
	 * 根据ID查询采购单数据
	 * @param id
	 * @return
	 */
	public EtbPurchaseDto queryEtbPurchaseByID(String id);
	
	/**
	 * 物理删除采购单
	 * @param id
	 */
	public void deleteEtbPurchase(String id);
	
	/**
	 * 新增采购单
	 * @param etbPurchase
	 */
	public void insertEtbPurchase(EtbPurchaseDto etbPurchase);
	
	/**
	 * 修改采购单
	 * @param etbPurchase
	 */
	public void updateEtbPurchase(EtbPurchaseDto etbPurchase);
}
