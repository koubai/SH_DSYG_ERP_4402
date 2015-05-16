package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.service.SupplierService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class SupplierServiceImpl implements SupplierService {
	
	private SupplierDao supplierDao;

	@Override
	public SupplierDto queryAllSupplierByID(String ID) {
		return supplierDao.queryAllSupplierByID(ID);
	}

	public SupplierDao getSupplierDao() {
		return supplierDao;
	}

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	@Override
	public Page querySupplierByPage(Page page, String supplierNoLow,
			String supplierNoHigh, String supplierName) {
		supplierNoLow = StringUtil.replaceDatabaseKeyword_mysql(supplierNoLow);
		//查询总记录数
		int totalCount = supplierDao.querySupplierCountByPage(supplierNoLow, supplierNoHigh, supplierName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SupplierDto> list = supplierDao.querySupplierByPage(supplierNoLow, supplierNoHigh,
				supplierName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public SupplierDto querySupplierByID(String supplierNo) {
		return supplierDao.querySupplierByID(supplierNo);
	}

	@Override
	public List<SupplierDto> queryAllSupplier() {
		return supplierDao.queryAllSupplier();
	}

	@Override
	public void insertSupplier(SupplierDto supplier) {
		supplierDao.insertSupplier(supplier);
	}

	@Override
	public void updateSupplier(SupplierDto supplier) {
		supplierDao.updateSupplier(supplier);
	}

	@Override
	public void deleteSupplier(String supplierNo, String username) {
		SupplierDto supplier = supplierDao.querySupplierByID(supplierNo);
		if(supplier != null) {
			//状态=已删除
			supplier.setStatus(Constants.STATUS_DEL);
			supplier.setUpdateuid(username);
			supplierDao.updateSupplier(supplier);
		}
	}

	@Override
	public List<SupplierDto> queryAllSupplierExport(String supplierNoLow,
			String supplierNoHigh) {
		return supplierDao.queryAllSupplierExport(supplierNoLow, supplierNoHigh);
	}
}
