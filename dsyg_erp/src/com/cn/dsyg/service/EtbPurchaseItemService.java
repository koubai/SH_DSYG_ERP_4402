package com.cn.dsyg.service;

import java.util.List;

import com.cn.dsyg.dto.EtbPurchaseItemDto;

/**
 * @name EtbPurchaseItemService.java
 * @author Frank
 * @time 2015-5-17下午10:43:57
 * @version 1.0
 */
public interface EtbPurchaseItemService {

	/**
	 * 根据采购单号查询采购单货物列表
	 * @param purchaseno
	 * @return
	 */
	public List<EtbPurchaseItemDto> queryPurchaseItemByPurchaseno(String purchaseno);
	
	/**
	 * 根据ID查询采购单货物
	 * @param id
	 * @return
	 */
	public EtbPurchaseItemDto queryPurchaseItemByID(String id);
	
	/**
	 * 根据采购单ID逻辑删除采购单货物
	 * @param purchaseno
	 * @param updateuid
	 */
	public void deletePurchaseItemByPurchaseno(String purchaseno, String updateuid);
	
	/**
	 * 新增采购单货物
	 * @param purchaseItem
	 */
	public void insertPurchaseItem(EtbPurchaseItemDto purchaseItem);
	
	/**
	 * 修改采购单货物
	 * @param purchaseItem
	 */
	public void updatePurchaseItem(EtbPurchaseItemDto purchaseItem);
}
