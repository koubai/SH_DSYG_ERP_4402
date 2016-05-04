package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.PurchaseDto;
import com.cn.dsyg.dto.PurchaseExtDto;

/**
 * @name PurchaseDao.java
 * @author Frank
 * @time 2015-5-9下午10:21:07
 * @version 1.0
 */
public interface PurchaseDao {
	
	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @return
	 */
	public List<PurchaseDto> queryAllPurchaseToExcel(String purchasedateLow,
			String purchasedateHigh, String status);

	//finance start
	/**
	 * 翻页查询采购单数（财务）
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @return
	 */
	public int queryFinancePurchaseCountByPage(String purchasedateLow,
			String purchasedateHigh, String status);
	
	/**
	 * 翻页查询采购单（财务）
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PurchaseDto> queryFinancePurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String status, int start, int end);
	//finance end
	
	/**
	 * 根据条件查询满足条件的采购单数量
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param theme2
	 * @param status
	 * @return
	 */
	public int queryPurchaseCountByPage(String purchasedateLow, String purchasedateHigh, String theme2, String status);
	
	/**
	 * 根据条件查询满足条件的采购单数量
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param theme2
	 * @param status
	 * @return
	 */
	public int queryPurchaseExtCountByPage(String purchasedateLow, String purchasedateHigh, String theme2, String productid, String status);

	/**
	 * 根据条件查询满足条件的采购单数量合计
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param theme2
	 * @param productid
	 * @param status
	 * @return
	 */
	public String queryPurchaseQuantity(String purchasedateLow, String purchasedateHigh, String theme2, String productid, String status);

	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param theme2
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PurchaseExtDto> queryPurchaseByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String status, int start, int end);
	
	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param theme2
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PurchaseExtDto> queryPurchaseExtByPage(String purchasedateLow,
			String purchasedateHigh, String theme2, String productid, String status, int start, int end);

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
	 * 根据采购编号查询采购单数据
	 * @param theme2
	 * @return
	 */
	public PurchaseDto queryPurchaseByTheme2(String theme2);

	
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
