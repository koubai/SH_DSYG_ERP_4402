package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.SampleDto;
import com.cn.dsyg.dto.SampleTotleDto;

/**
 * @name SampleService.java
 * @author Frank
 * @time 2015-9-12下午9:18:47
 * @version 1.0
 */
public interface SampleService {
	
	/**
	 * 根据产品ID汇总
	 * @param productid
	 * @return
	 */
	public List<SampleTotleDto> querySampleNumByProductId(String productid);

	/**
	 * 根据产品tradename, typeno, color汇总
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @return
	 */
	public List<SampleTotleDto> querySampleNumByKeys(String tradename, String typeno, String color);
	
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
	 * @param customername
	 * @param page
	 * @return
	 */
	public Page querySampleByPage(String warehousename,
			String productid, String status, String tradename, String customername, Page page);
	
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
