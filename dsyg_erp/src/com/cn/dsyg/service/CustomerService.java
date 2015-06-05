package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.EtbCustomerDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface CustomerService {
	
	/**
	 * 翻页查询客户
	 * @param page
	 * @param customerNoLow
	 * @param customerNoHigh
	 * @param customerAddFlag
	 * @param customerName
	 * @return
	 */
	public Page queryEtbCustomerByPage(Page page, String customerNoLow, String customerNoHigh, String customerName);

	/**
	 * 根据ID查询客户（查询未删除的记录）
	 * @param customerNo
	 * @return
	 */
	public EtbCustomerDto queryEtbCustomerByID(String customerNo);
	
	/**
	 * 根据ID客户（查询所有记录）
	 * @param customerNo
	 * @return
	 */
	public EtbCustomerDto queryAllEtbCustomerByID(String customerNo);
	
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
	 * 删除客户
	 * @param customerNo
	 * @param username
	 */
	public void deleteEtbCustomer(String customerNo, String username);
	
	/**
	 * 查询客户（Excel导出用）
	 * @param customerNoLow
	 * @param customerNoHigh
	 * @return
	 */
	public List<EtbCustomerDto> queryAllEtbCustomerExport(String customerNoLow, String customerNoHigh);
}
