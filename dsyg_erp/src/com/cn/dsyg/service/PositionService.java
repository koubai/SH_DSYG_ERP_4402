package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.PositionCollectDto;
import com.cn.dsyg.dto.PositionDto;

/**
 * @name PositionService.java
 * @author Frank
 * @time 2015-9-19下午2:54:42
 * @version 1.0
 */
public interface PositionService {

	/**
	 * 根据盘点日期查询记录
	 * @param handler
	 * @param checkday
	 * @return
	 */
	public List<PositionCollectDto> queryPositionCollectByDay(String handler, String checkday);
	
	/**
	 * 根据盘点日期分页查询记录
	 * @param handler
	 * @param checkday
	 * @param page
	 * @return
	 */
	public Page queryPositionCollectByPage(String handler, String checkday, Page page);
	
	/**
	 * 根据盘点日期和产品ID查询最新的一条记录
	 * @param productid
	 * @param checkday
	 * @return
	 */
	public PositionDto queryPositionByLogicId(String productid, String checkday);
	
	/**
	 * 根据盘点日期和产品ID查询记录
	 * @param userid
	 * @param productid
	 * @param checkday
	 * @return
	 */
	public List<PositionDto> queryPositionListByLogicId(String userid, String productid, String checkday);
	
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
