package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.EtbDeliveryDto;

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
	public EtbDeliveryDto queryEtbDeliveryByID(String deliveryNo);
	
	/**
	 * 根据ID快递（查询所有记录）
	 * @param deliveryNo
	 * @return
	 */
	public EtbDeliveryDto queryAllEtbDeliveryByID(String deliveryNo);
	
	/**
	 * 查询所有的快递
	 * @return
	 */
	public List<EtbDeliveryDto> queryAllEtbDelivery();
	
	/**
	 * 新增快递
	 * @param delivery
	 */
	public void insertEtbDelivery(EtbDeliveryDto delivery);
	
	/**
	 * 修改快递
	 * @param delivery
	 */
	public void updateEtbDelivery(EtbDeliveryDto delivery);
	
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
	public List<EtbDeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow, String deliveryNoHigh);
}
