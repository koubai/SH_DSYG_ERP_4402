package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ResourceDto;

/**
 * @name ResourceDao.java
 * @author Frank
 * @time 2015-4-28下午10:09:24
 * @version 1.0
 */
public interface ResourceDao {
	
	/**
	 * 查询所有资源
	 * @return
	 */
	public List<ResourceDto> queryAllResource();

	/**
	 * 根据角色ID查询资源信息
	 * @param roleid
	 * @param restype
	 * @return
	 */
	public List<ResourceDto> queryResourceByRole(String roleid, String restype);
	
	/**
	 * 根据ID查询资源信息
	 * @param id
	 * @return
	 */
	public ResourceDto queryResourceID(String id);
	
	/**
	 * 新增资源
	 * @param resource
	 */
	public void insertResource(ResourceDto resource);
	
	/**
	 * 修改资源
	 * @param resource
	 */
	public void updateResource(ResourceDto resource);
}
