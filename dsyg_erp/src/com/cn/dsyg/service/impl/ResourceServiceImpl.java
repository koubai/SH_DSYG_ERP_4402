package com.cn.dsyg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.dsyg.dao.ResourceDao;
import com.cn.dsyg.dto.ResourceDto;
import com.cn.dsyg.service.ResourceService;

/**
 * @name ResourceServiceImpl.java
 * @author Frank
 * @time 2015-4-28下午10:14:48
 * @version 1.0
 */
public class ResourceServiceImpl implements ResourceService {
	
	private ResourceDao resourceDao;
	
	@Override
	public Map<String, Integer> getRoleResourceMap(String roleid) {
		//获得所有资源列表
		List<ResourceDto> listAll = resourceDao.queryAllResource();
		//根据角色CODE获得资源列表
		List<ResourceDto> list = resourceDao.queryResourceByRole(roleid, "");
		if(listAll != null && listAll.size() > 0) {
			if(list != null && list.size() > 0) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				for(ResourceDto r : listAll) {
					//默认没有权限
					int right = 0;
					for(ResourceDto r1 : list) {
						if(r.getUrl().equals(r1.getUrl())) {
							//有权限
							right = 1;
							break;
						}
					}
					map.put(r.getUrl(), right);
				}
				return map;
			}
		}
		return null;
	}

	@Override
	public List<ResourceDto> queryResourceByRole(String roleid, String restype) {
		return resourceDao.queryResourceByRole(roleid, restype);
	}

	@Override
	public ResourceDto queryResourceID(String id) {
		return resourceDao.queryResourceID(id);
	}

	@Override
	public void insertResource(ResourceDto resource) {
		resourceDao.insertResource(resource);
	}

	@Override
	public void updateResource(ResourceDto resource) {
		resourceDao.updateResource(resource);
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
