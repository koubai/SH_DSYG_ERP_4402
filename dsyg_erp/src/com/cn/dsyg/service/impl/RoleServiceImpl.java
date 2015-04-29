package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.RoleDao;
import com.cn.dsyg.dto.RoleDto;
import com.cn.dsyg.service.RoleService;

/**
 * @name RoleServiceImpl.java
 * @author Frank
 * @time 2014-12-12下午11:20:56
 * @version 1.0
 */
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	@Override
	public Page queryRoleByPage(String rolename, Page page) {
		rolename = StringUtil.replaceDatabaseKeyword_mysql(rolename);
		//查询总记录数
		int totalCount = roleDao.queryRoleCountByPage(rolename);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<RoleDto> list = roleDao.queryRoleByPage(rolename,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public RoleDto queryRoleByID(Integer id) {
		return roleDao.queryRoleByID(id);
	}
	
	@Override
	public RoleDto queryRoleByCode(String rolecode) {
		return roleDao.queryRoleByCode(rolecode);
	}

	@Override
	public List<RoleDto> queryAllRole() {
		return roleDao.queryAllRole();
	}

	@Override
	public void deleteRole(String id) {
		roleDao.deleteRole(id);
	}

	@Override
	public void insertRole(RoleDto role) {
		roleDao.insertRole(role);
	}

	@Override
	public void updateRole(RoleDto role) {
		roleDao.updateRole(role);
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
