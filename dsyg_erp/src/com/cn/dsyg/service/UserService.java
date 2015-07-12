package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.UserDto;

public interface UserService {
	
	/**
	 * 修改密码
	 * @param user
	 */
	public void updPassword(UserDto user);

	/**
	 * 翻页查询用户
	 * @param username
	 * @param page
	 * @return
	 */
	public Page queryUserByPage(String username, Page page);
	
	/**
	 * 根据登录ID查询用户
	 * @param userid
	 * @return
	 */
	public UserDto queryUserByID(String userid);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<UserDto> queryAllUser();
	
	/**
	 * 删除用户
	 * @param userid
	 */
	public void deleteUser(String userid);
	
	/**
	 * 新增用户
	 * @param user
	 */
	public void insertUser(UserDto user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateUser(UserDto user);
	
	/**
	 * 翻页查询用户2
	 * @param 
	 */
	public Page queryUserByPage(String fieldno, String keyword, String useridfrom,
			String useridto, String status, Page page);

}
