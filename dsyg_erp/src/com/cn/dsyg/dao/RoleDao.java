package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.RoleDto;

/**
 * @name RoleDao.java
 * @author Frank
 * @time 2014-12-9上午12:38:28
 * @version 1.0
 */
public interface RoleDao {
	
	/**
	 * 翻页查询数据
	 * @param rolename
	 * @param start
	 * @param end
	 * @return
	 */
	public List<RoleDto> queryRoleByPage(String rolename, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param rolename
	 * @return
	 */
	public int queryRoleCountByPage(String rolename);

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
