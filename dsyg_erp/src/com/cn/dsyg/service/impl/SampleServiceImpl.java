package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.CustomerDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.SampleDao;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.SampleDto;
import com.cn.dsyg.service.SampleService;

/**
 * @name SampleServiceImpl.java
 * @author Frank
 * @time 2015-9-12下午9:19:04
 * @version 1.0
 */
public class SampleServiceImpl implements SampleService {
	
	private SampleDao sampleDao;
	private ProductDao productDao;
	private CustomerDao customerDao;

	@Override
	public SampleDto querySampleId(String id) {
		SampleDto sample = sampleDao.querySampleId(id);
		ProductDto product = productDao.queryProductByID(sample.getProductid());
		if(product != null) {
			sample.setTradename(product.getTradename());
			sample.setTypeno(product.getTypeno());
			sample.setColor(product.getColor());
			sample.setPackaging(product.getPackaging());
			sample.setItem10(product.getItem10());
			CustomerDto customer = customerDao.queryAllEtbCustomerByID(sample.getRes01());
			if(customer != null) {
				sample.setCustomername(customer.getCustomername());
			}
		}
		return sample;
	}

	@Override
	public Page querySampleByPage(String warehousename, String productid,
			String status, String tradename, Page page) {
		warehousename = StringUtil.replaceDatabaseKeyword_mysql(warehousename);
		tradename = StringUtil.replaceDatabaseKeyword_mysql(tradename);
		
		//查询总记录数
		int totalCount = sampleDao.querySampleCountByPage(warehousename, productid, status, tradename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<SampleDto> list = sampleDao.querySampleByPage(warehousename, productid, status, tradename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			for(SampleDto sample : list) {
				ProductDto product = productDao.queryProductByID(sample.getProductid());
				if(product != null) {
					sample.setTradename(product.getTradename());
					sample.setTypeno(product.getTypeno());
					sample.setColor(product.getColor());
					sample.setPackaging(product.getPackaging());
					sample.setItem10(product.getItem10());
					CustomerDto customer = customerDao.queryAllEtbCustomerByID(sample.getRes01());
					if(customer != null) {
						sample.setCustomername(customer.getCustomername());
					}
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public void insertSample(SampleDto sample) {
		sampleDao.insertSample(sample);
	}

	@Override
	public void updateSample(SampleDto sample) {
		sampleDao.updateSample(sample);
	}

	public SampleDao getSampleDao() {
		return sampleDao;
	}

	public void setSampleDao(SampleDao sampleDao) {
		this.sampleDao = sampleDao;
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