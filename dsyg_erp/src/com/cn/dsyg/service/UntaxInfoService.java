package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.UntaxInfoDto;

/**
 * @name UntaxInfoService.java
 * @author Frank
 * @time 2015-9-12下午9:18:47
 * @version 1.0
 */
public interface UntaxInfoService {
	

	
	public List<UntaxInfoDto> queryUntaxInfoNumByProductId(String productid);

	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	public UntaxInfoDto queryUntaxInfoId(String id);
	
	/**
	 * 分页查询数据
	 * @param productid
	 * @param status
	 * @param tradename
	 * @param customername
	 * @param page
	 * @return
	 */
	public Page queryUntaxInfoByPage(
			String productid, String status, String tradename, String customername, Page page);
	
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
