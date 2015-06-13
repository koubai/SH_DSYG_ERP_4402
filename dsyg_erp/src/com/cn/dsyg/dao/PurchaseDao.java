package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.PurchaseDto;

/**
 * @name PurchaseDao.java
 * @author Frank
 * @time 2015-5-9下午10:21:07
 * @version 1.0
 */
public interface PurchaseDao {

	/**
	 * 根据条件查询满足条件的采购单数量
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @return
	 */
	public int queryPurchaseCountByPage(String purchasedateLow, String purchasedateHigh);
	
	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PurchaseDto> queryPurchaseByPage(String purchasedateLow, String purchasedateHigh, int start, int end);
	
	/**
	 * 根据ID查询采购单数据
	 * @param id
	 * @return
	 */
	public PurchaseDto queryPurchaseByID(String id);
	
	/**
	 * 根据编号查询采购单数据
	 * @param purchaseno
	 * @return
	 */
	public PurchaseDto queryPurchaseByNo(String purchaseno);
	
	/**
	 * 物理删除采购单
	 * @param id
	 */
	public void deletePurchase(String id);
	
	/**
	 * 新增采购单
	 * @param Purchase
	 */
	public void insertPurchase(PurchaseDto purchase);
	
	/**
	 * 修改采购单
	 * @param Purchase
	 */
	public void updatePurchase(PurchaseDto purchase);
}
