package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.DeliveryDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface DeliveryDao {
	
	/**
	 * 根据ID查询快递（查询所有记录）
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
	 * 翻页查询快递
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<DeliveryDto> queryEtbDeliveryByPage(String deliveryNoLow,
				String deliveryNoHigh, String deliveryName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @return
	 */
	public int queryEtbDeliveryCountByPage(String deliveryNoLow, String deliveryNoHigh, String deliveryName);
	
	/**
	 * 根据ID查询快递（查询未删除的记录）
	 * @param deliveryNo
	 * @return
	 */
	public DeliveryDto queryEtbDeliveryByID(String deliveryNo);
	
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
	 * 查询快递（Excel导出用）
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @return
	 */
	public List<DeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow, String deliveryNoHigh);
}
