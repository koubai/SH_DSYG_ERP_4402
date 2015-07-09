package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseItemDto;

/**
 * @name PurchaseService.java
 * @author Frank
 * @time 2015-5-9下午10:26:10
 * @version 1.0
 */
public interface PurchaseService {
	
	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @return
	 */
	public List<PurchaseDto> queryAllPurchaseToExcel(String purchasedateLow, String purchasedateHigh, String status);
	
	//finance start
	/**
	 * 翻页查询采购单（财务）
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @param page
	 * @return
	 */
	public Page queryFinancePurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String status, Page page);
	//finance end

	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param page
	 * @return
	 */
	public Page queryPurchaseByPage(String purchasedateLow, String purchasedateHigh, Page page);
	
	/**
	 * 根据ID查询采购单数据
	 * @param id
	 * @return
	 */
	public PurchaseDto queryPurchaseByID(String id);
	
	/**
	 * 物理删除采购单
	 * @param id
	 */
	public void deletePurchase(String id);
	
	/**
	 * 新增采购单和产品
	 * @param Purchase
	 * @param listPurchaseItem
	 * @param userid
	 * @return
	 */
	public String addPurchase(PurchaseDto Purchase, List<PurchaseItemDto> listPurchaseItem, String userid);
	
	/**
	 * 新增采购单
	 * @param Purchase
	 */
	public void insertPurchase(PurchaseDto purchase);
	
	/**
	 * 修改采购单
	 * @param Purchase
	 * @param listPurchaseItem
	 * @param userid
	 */
	public void updatePurchase(PurchaseDto purchase, List<PurchaseItemDto> listPurchaseItem, String userid);
	
	/**
	 * 修改采购单
	 * @param Purchase
	 */
	public void updatePurchase(PurchaseDto purchase);
}
