package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.EtbPurchaseDto;

/**
 * @name EtbPurchaseDao.java
 * @author Frank
 * @time 2015-5-9下午10:21:07
 * @version 1.0
 */
public interface EtbPurchaseDao {

	/**
	 * 根据条件查询满足条件的采购单数量
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @return
	 */
	public int queryEtbPurchaseCountByPage(String purchasedateLow, String purchasedateHigh);
	
	/**
	 * 翻页查询满足条件的采购数据
	 * @param purchasedateLow
	 * @param purchasedateHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbPurchaseDto> queryEtbPurchaseByPage(String purchasedateLow, String purchasedateHigh, int start, int end);
	
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
