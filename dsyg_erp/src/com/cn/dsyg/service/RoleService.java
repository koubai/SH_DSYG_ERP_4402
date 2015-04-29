package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.RoleDto;

/**
 * @name RoleService.java
 * @author Frank
 * @time 2014-12-12下午11:20:32
 * @version 1.0
 */
public interface RoleService {

	/**
	 * 翻页查询数据
	 * @param rolename
	 * @param page
	 * @return
	 */
	public Page queryRoleByPage(String rolename, Page page);
	
	/**
	 * 根据登录ID查询数据
	 * @param id
	 * @return
	 */
	public RoleDto queryRoleByID(Integer id);
	
	/**
	 * 根据登录code查询数据
	 * @param rolecode
	 * @return
	 */
	public RoleDto queryRoleByCode(String rolecode);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<RoleDto> queryAllRole();
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void deleteRole(String id);
	
	/**
	 * 新增数据
	 * @param role
	 */
	public void insertRole(RoleDto role);
	
	/**
	 * 修改数据
	 * @param role
	 */
	public void updateRole(RoleDto role);
}
