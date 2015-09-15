package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.SampleDto;

/**
 * @name SampleService.java
 * @author Frank
 * @time 2015-9-12下午9:18:47
 * @version 1.0
 */
public interface SampleService {

	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public SampleDto querySampleId(String id);
	
	/**
	 * 分页查询数据
	 * @param warehousename
	 * @param productid
	 * @param status
	 * @param tradename
	 * @param page
	 * @return
	 */
	public Page querySampleByPage(String warehousename,
			String productid, String status, String tradename, Page page);
	
	/**
	 * 新增记录
	 * @param sample
	 */
	public void insertSample(SampleDto sample);
	
	/**
	 * 修改记录
	 * @param sample
	 */
	public void updateSample(SampleDto sample);
}
