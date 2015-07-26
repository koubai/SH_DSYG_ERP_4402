package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.PurchaseItemDto;

/**
 * @name PurchaseItemDao.java
 * @author Frank
 * @time 2015-5-17下午10:34:54
 * @version 1.0
 */
public interface PurchaseItemDao {
	
	/**
	 * 根据采购单号查询采购单货物列表
	 * @param purchaseno
	 * @return
	 */
	public List<PurchaseItemDto> queryPurchaseItemByPurchaseno(String purchaseno);
	
	/**
	 * 根据ID查询采购单货物
	 * @param id
	 * @return
	 */
	public PurchaseItemDto queryPurchaseItemByID(String id);
	
	/**
	 * 根据产品ID和供应商ID查询采购单货物
	 * @param productid
	 * @param supplierid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PurchaseItemDto> queryPurchaseItemByProductid(String productid, String supplierid, int start, int end);
	
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
	public void insertPurchaseItem(PurchaseItemDto purchaseItem);
	
	/**
	 * 修改采购单货物
	 * @param purchaseItem
	 */
	public void updatePurchaseItem(PurchaseItemDto purchaseItem);
}
