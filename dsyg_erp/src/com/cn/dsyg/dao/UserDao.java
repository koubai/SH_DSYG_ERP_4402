package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.UserDto;

/**
 * @name UserDao.java
 * @author Frank
 * @time 2014-12-9上午12:38:28
 * @version 1.0
 */
public interface UserDao {
	
	/**
	 * 翻页查询数据
	 * @param username
	 * @param start
	 * @param end
	 * @return
	 */
	public List<UserDto> queryUserByPage(String username, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param username
	 * @return
	 */
	public int queryUserCountByPage(String username);

	/**
	 * 根据登录ID查询数据
	 * @param userid
	 * @return
	 */
	public UserDto queryUserByID(String userid);
	
	/**
	 * 根据多个登录ID查询数据
	 * @param userids
	 * @return
	 */
	public List<UserDto> queryMulUserByID(String userids);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<UserDto> queryAllUser();
	
	/**
	 * 删除数据
	 * @param userid
	 */
	public void deleteUser(String userid);
	
	/**
	 * 新增数据
	 * @param user
	 */
	public void insertUser(UserDto user);
	
	/**
	 * 修改数据
	 * @param user
	 */
	public void updateUser(UserDto user);
	
	public List<ProductDto> queryUserListByPage(String fieldno, String keyword, String useridfrom, String useridto, String status, int start, int end);

	public int queryUserListCountByPage(String fieldno, String keyword, String useridfrom, String useridto, String status);
}
