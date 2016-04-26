package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.UntaxInfoDto;

/**
 * @name UntaxInfoDao.java
 * @author Frank
 * @time 2015-9-12下午8:58:50
 * @version 1.0
 */
public interface UntaxInfoDao {
	
	/**
	 * 根据产品ID汇总
	 * @param productid
	 * @return
	 */
	public List<UntaxInfoDto> queryUntaxInfoNumByProductId(String productid);
	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public UntaxInfoDto queryUntaxInfoId(String id);
	
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
	public List<UntaxInfoDto> queryUntaxInfoByPage(
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
	public int queryUntaxInfoCountByPage(
			String productid, String status, String tradename, String customername);
	
	/**
	 * 新增记录
	 * @param untaxinfo
	 */
	public void insertUntaxInfo(UntaxInfoDto untaxinfo);
	
	/**
	 * 修改记录
	 * @param untaxinfo
	 */
	public void updateUntaxInfo(UntaxInfoDto untaxinfo);
}
