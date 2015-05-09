package com.cn.dsyg.service;

import java.util.List;
import java.util.Map;

import com.cn.dsyg.dto.ResourceDto;

/**
 * @name ResourceService.java
 * @author Frank
 * @time 2015-4-28下午10:13:55
 * @version 1.0
 */
public interface ResourceService {
	
	/**
	 * 根据角色id查询资源MAP
	 * @param roleid
	 * @return
	 */
	public Map<String, Integer> getRoleResourceMap(String roleid);

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
