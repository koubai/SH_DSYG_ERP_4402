package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.SupplierDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface SupplierService {
	
	/**
	 * 翻页查询供应商
	 * @param page
	 * @param supplierNoLow
	 * @param supplierNoHigh
	 * @param supplierAddFlag
	 * @param supplierName
	 * @return
	 */
	public Page querySupplierByPage(Page page, String supplierNoLow, String supplierNoHigh, String supplierName);

	/**
	 * 根据ID查询供应商（查询未删除的记录）
	 * @param supplierNo
	 * @return
	 */
	public SupplierDto querySupplierByID(String supplierNo);
	
	/**
	 * 根据ID供应商（查询所有记录）
	 * @param supplierNo
	 * @return
	 */
	public SupplierDto queryAllSupplierByID(String supplierNo);
	
	/**
	 * 根据名称查供应商（查询所有记录）
	 * @param supplierName
	 * @return
	 */
	public SupplierDto queryAllSupplierByName(String supplierName);

	/**
	 * 查询所有的供应商
	 * @return
	 */
	public List<SupplierDto> queryAllSupplier();
	
	/**
	 * 新增供应商
	 * @param supplier
	 */
	public void insertSupplier(SupplierDto supplier);
	
	/**
	 * 修改供应商
	 * @param supplier
	 */
	public void updateSupplier(SupplierDto supplier);
	
	/**
	 * 删除供应商
	 * @param supplierNo
	 * @param username
	 */
	public void deleteSupplier(String supplierNo, String username);
	
	/**
	 * 查询供应商（Excel导出用）
	 * @param supplierNoLow
	 * @param supplierNoHigh
	 * @return
	 */
	public List<SupplierDto> queryAllSupplierExport(String supplierNoLow, String supplierNoHigh);
}
