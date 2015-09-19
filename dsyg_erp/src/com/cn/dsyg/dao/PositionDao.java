package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.PositionCollectDto;
import com.cn.dsyg.dto.PositionDto;

/**
 * @name PositionDao.java
 * @author Frank
 * @time 2015-9-19下午2:50:43
 * @version 1.0
 */
public interface PositionDao {
	
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
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PositionCollectDto> queryPositionCollectByPage(String handler, String checkday, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param handler
	 * @param checkday
	 * @return
	 */
	public int queryPositionCollectCountByPage(String handler, String checkday);

	/**
	 * 根据盘点日期和产品ID查询记录（或者查询最新的盘点数据）
	 * @param userid
	 * @param productid
	 * @param checkday
	 * @return
	 */
	public List<PositionDto> queryPositionByLogicId(String userid, String productid, String checkday);
	
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
