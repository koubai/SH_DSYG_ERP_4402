package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.EtbCustomerDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface EtbCustomerDao {
	
	/**
	 * 根据ID查询客户（查询所有记录）
	 * @param customerNo
	 * @return
	 */
	public EtbCustomerDto queryAllEtbCustomerByID(String customerNo);

	/**
	 * 翻页查询客户
	 * @param customerNoLow
	 * @param customerNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbCustomerDto> queryEtbCustomerByPage(String customerNoLow,
				String customerNoHigh, String customerName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param customerNoLow
	 * @param customerNoHigh
	 * @return
	 */
	public int queryEtbCustomerCountByPage(String customerNoLow, String customerNoHigh, String customerName);
	
	/**
	 * 根据ID查询客户（查询未删除的记录）
	 * @param customerNo
	 * @return
	 */
	public EtbCustomerDto queryEtbCustomerByID(String customerNo);
	
	/**
	 * 查询所有的客户
	 * @return
	 */
	public List<EtbCustomerDto> queryAllEtbCustomer();
	
	/**
	 * 新增客户
	 * @param customer
	 */
	public void insertEtbCustomer(EtbCustomerDto customer);
	
	/**
	 * 修改客户
	 * @param customer
	 */
	public void updateEtbCustomer(EtbCustomerDto customer);
	
	/**
	 * 查询客户（Excel导出用）
	 * @param customerNoLow
	 * @param customerNoHigh
	 * @return
	 */
	public List<EtbCustomerDto> queryAllEtbCustomerExport(String customerNoLow, String customerNoHigh);
}
