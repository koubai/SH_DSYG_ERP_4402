package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.DeliveryDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface DeliveryService {
	
	/**
	 * 翻页查询快递
	 * @param page
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @param deliveryAddFlag
	 * @param deliveryName
	 * @return
	 */
	public Page queryEtbDeliveryByPage(Page page, String deliveryNoLow, String deliveryNoHigh, String deliveryName);

	/**
	 * 根据ID查询快递（查询未删除的记录）
	 * @param deliveryNo
	 * @return
	 */
	public DeliveryDto queryEtbDeliveryByID(String deliveryNo);
	
	/**
	 * 根据ID快递（查询所有记录）
	 * @param deliveryNo
	 * @return
	 */
	public DeliveryDto queryAllEtbDeliveryByID(String deliveryNo);
	
	
	/**
	 * 根据名称查询快递（查询所有记录）
	 * @param deliveryName
	 * @return
	 */
	public DeliveryDto queryAllEtbDeliveryByName(String deliveryName);

	/**
	 * 查询所有的快递
	 * @return
	 */
	public List<DeliveryDto> queryAllEtbDelivery();
	
	/**
	 * 新增快递
	 * @param delivery
	 */
	public void insertEtbDelivery(DeliveryDto delivery);
	
	/**
	 * 修改快递
	 * @param delivery
	 */
	public void updateEtbDelivery(DeliveryDto delivery);
	
	/**
	 * 删除快递
	 * @param deliveryNo
	 * @param username
	 */
	public void deleteEtbDelivery(String deliveryNo, String username);
	
	/**
	 * 查询快递（Excel导出用）
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @return
	 */
	public List<DeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow, String deliveryNoHigh);
}
