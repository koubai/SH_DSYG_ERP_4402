package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.UntaxInfoDao;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.UntaxInfoDto;
import com.cn.dsyg.service.UntaxInfoService;

/**
 * @name UntaxInfoServiceImpl.java
 * @author Frank
 * @time 2015-9-12下午9:19:04
 * @version 1.0
 */
public class UntaxInfoServiceImpl implements UntaxInfoService {
	
	private UntaxInfoDao untaxinfoDao;
	private ProductDao productDao;
	private CustomerDao customerDao;
	
	@Override
	public List<UntaxInfoDto> queryUntaxInfoNumByProductId(String productid) {
		List<UntaxInfoDto> list = untaxinfoDao.queryUntaxInfoNumByProductId(productid);
		return list;
	}

	@Override
	public UntaxInfoDto queryUntaxInfoId(String id) {
		UntaxInfoDto untaxinfo = untaxinfoDao.queryUntaxInfoId(id);
		untaxinfo.setShowQuantity(untaxinfo.getQuantity());
		ProductDto product = productDao.queryProductByID(untaxinfo.getProductid());
		if(product != null) {
			untaxinfo.setTradename(product.getTradename());
			untaxinfo.setTypeno(product.getTypeno());
			untaxinfo.setColor(product.getColor());
			untaxinfo.setPackaging(product.getPackaging());
			untaxinfo.setItem10(product.getItem10());
			untaxinfo.setUnit(product.getUnit());
			untaxinfo.setMakearea(product.getMakearea());
		}
		return untaxinfo;
	}

	@Override
	public Page queryUntaxInfoByPage(String productid,
			String status, String tradename, String customername, Page page) {
		customername = StringUtil.replaceDatabaseKeyword_mysql(customername);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		
		//查询总记录数
		int totalCount = untaxinfoDao.queryUntaxInfoCountByPage(productid, status, tradename, customername);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<UntaxInfoDto> list = untaxinfoDao.queryUntaxInfoByPage(productid, status, tradename, customername,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(UntaxInfoDto untaxinfo : list) {
				ProductDto product = productDao.queryProductByID(untaxinfo.getProductid());
				if(product != null) {
					untaxinfo.setTradename(product.getTradename());
					untaxinfo.setTypeno(product.getTypeno());
					untaxinfo.setColor(product.getColor());
					untaxinfo.setPackaging(product.getPackaging());
					untaxinfo.setItem10(product.getItem10());
					untaxinfo.setUnit(product.getUnit());
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public void insertUntaxInfo(UntaxInfoDto untaxinfo) {
		untaxinfoDao.insertUntaxInfo(untaxinfo);
	}

	@Override
	public void updateUntaxInfo(UntaxInfoDto untaxinfo) {
		untaxinfoDao.updateUntaxInfo(untaxinfo);
	}

	public UntaxInfoDao getUntaxInfoDao() {
		return untaxinfoDao;
	}

	public void setUntaxInfoDao(UntaxInfoDao untaxinfoDao) {
		this.untaxinfoDao = untaxinfoDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
