package com.cn.dsyg.dao;

import com.cn.dsyg.dto.PositionDto;

/**
 * @name PositionDao.java
 * @author Frank
 * @time 2015-9-19下午2:50:43
 * @version 1.0
 */
public interface PositionDao {

	/**
	 * 根据产品ID查询位置记录
	 * @param productid
	 * @param warehousename
	 * @return
	 */
	public PositionDto queryPositionByLogicId(String productid, String warehousename);
	
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public PositionDto queryPositionById(String id);
	
	/**
	 * 新增记录
	 * @param position
	 */
	public void insertPosition(PositionDto position);
	
	/**
	 * 修改记录
	 * @param position
	 */
	public void updatePosition(PositionDto position);
}
