package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.SupplierDao;
import com.cn.dsyg.dto.Dict01Dto;
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
	private Dict01Dao dict01Dao;

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

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
		supplierName = StringUtil.replaceDatabaseKeyword_mysql(supplierName);
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
	public SupplierDto queryAllSupplierByName(String supplierName){
		return supplierDao.querySupplierByName(supplierName);
	}

	@Override
	public List<SupplierDto> queryAllSupplier() {
		return supplierDao.queryAllSupplier();
	}

	@Override
	public void insertSupplier(SupplierDto supplier) {
		//供应商番号
		String code = "";
		
		List<Dict01Dto> listDict = dict01Dao.queryDict01ByFieldcode(Constants.DICT_SUPPLIER_ORDER, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listDict != null && listDict.size() > 0) {
			Dict01Dto dict = listDict.get(0);
			code = dict.getCode();
			//番号+1
			dict.setCode("" + (Integer.valueOf(dict.getCode()) + 1));
			dict01Dao.updateDict01(dict);
			code = "" + (Integer.valueOf(dict.getCode()) + 1);
		} else {
			//插入数据
			Dict01Dto dict = new Dict01Dto();
			dict.setFieldcode(Constants.DICT_SUPPLIER_ORDER);
			dict.setFieldname("供应商番号");
			//番号默认从1开始
			dict.setCode("1");
			code = "1";
			dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			dict.setMean("供应商番号");
			dict.setNote("供应商番号");
			dict.setStatus(Constants.STATUS_NORMAL);
			dict.setCreateuid("admin");
			dict.setUpdateuid("admin");
			dict01Dao.insertDict01(dict);
		}
		supplier.setId(Integer.valueOf(code));
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
