package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.SupplierDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface SupplierDao {
	
	/**
	 * 根据ID查询供应商（查询所有记录）
	 * @param supplierNo
	 * @return
	 */
	public SupplierDto queryAllSupplierByID(String supplierNo);

	/**
	 * 翻页查询供应商
	 * @param supplierNoLow
	 * @param supplierNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SupplierDto> querySupplierByPage(String supplierNoLow,
				String supplierNoHigh, String supplierName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param supplierNoLow
	 * @param supplierNoHigh
	 * @return
	 */
	public int querySupplierCountByPage(String supplierNoLow, String supplierNoHigh, String supplierName);
	
	/**
	 * 根据ID查询供应商（查询未删除的记录）
	 * @param supplierNo
	 * @return
	 */
	public SupplierDto querySupplierByID(String supplierNo);
		
	/**
	 * 根据名称查询供应商（查询未删除的记录）
	 * @param supplierName
	 * @return
	 */
	public SupplierDto querySupplierByName(String supplierName);
	
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
	 * 查询供应商（Excel导出用）
	 * @param supplierNoLow
	 * @param supplierNoHigh
	 * @return
	 */
	public List<SupplierDto> queryAllSupplierExport(String supplierNoLow, String supplierNoHigh);
}
