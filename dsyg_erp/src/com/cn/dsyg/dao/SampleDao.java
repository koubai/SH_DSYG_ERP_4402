package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.SampleDto;
import com.cn.dsyg.dto.SampleTotleDto;

/**
 * @name SampleDao.java
 * @author Frank
 * @time 2015-9-12下午8:58:50
 * @version 1.0
 */
public interface SampleDao {
	
	/**
	 * 根据产品ID汇总
	 * @param productid
	 * @return
	 */
	public List<SampleTotleDto> querySampleNumByProductId(String productid);

	/**
	 * 根据KEYS汇总
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SampleDto> querySampleByPage(String warehousename,
			String productid, String status, String tradename, String customername, int start, int end);
	
	/**
	 * 查询总记录数字
	 * @param warehousename
	 * @param productid
	 * @param status
	 * @param tradename
	 * @param customername
	 * @return
	 */
	public int querySampleCountByPage(String warehousename,
			String productid, String status, String tradename, String customername);
	
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
