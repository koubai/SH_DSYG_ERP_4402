package com.cn.dsyg.service.impl;

import java.util.List;

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
