package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.EtbDeliveryDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface EtbDeliveryDao {
	
	/**
	 * 根据ID查询快递（查询所有记录）
	 * @param deliveryNo
	 * @return
	 */
	public EtbDeliveryDto queryAllEtbDeliveryByID(String deliveryNo);

	/**
	 * 翻页查询快递
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbDeliveryDto> queryEtbDeliveryByPage(String deliveryNoLow,
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
	public EtbDeliveryDto queryEtbDeliveryByID(String deliveryNo);
	
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
	 * 查询快递（Excel导出用）
	 * @param deliveryNoLow
	 * @param deliveryNoHigh
	 * @return
	 */
	public List<EtbDeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow, String deliveryNoHigh);
}
