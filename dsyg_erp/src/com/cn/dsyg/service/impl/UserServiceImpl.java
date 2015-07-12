package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.MD5Util;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dto.ProductDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.UserService;

/**
 * @name UserServiceImpl.java
 * @author Frank
 * @time 2014-12-12下午11:24:49
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	@Override
	public void updPassword(UserDto user) {
		UserDto u = userDao.queryUserByID(user.getUserid());
		if(u != null) {
			u.setPassword(MD5Util.md5(user.getPassword()));
			u.setUpdateuid(user.getUserid());
			userDao.updateUser(u);
		}
	}
	
	@Override
	public Page queryUserByPage(String username, Page page) {
		username = StringUtil.replaceDatabaseKeyword_mysql(username);
		//查询总记录数
		int totalCount = userDao.queryUserCountByPage(username);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<UserDto> list = userDao.queryUserByPage(username,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public UserDto queryUserByID(String userid) {
		return userDao.queryUserByID(userid);
	}

	@Override
	public List<UserDto> queryAllUser() {
		return userDao.queryAllUser();
	}

	@Override
	public void deleteUser(String userid) {
		userDao.deleteUser(userid);
	}

	@Override
	public void insertUser(UserDto user) {
		//MD5加密密码
		user.setPassword(MD5Util.md5(user.getPassword()));
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(UserDto user) {
		userDao.updateUser(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Page queryUserByPage(String fieldno, String keyword, String useridfrom,
				String useridto, String status, Page page) {
		keyword = StringUtil.replaceDatabaseKeyword_mysql(keyword);
		useridfrom = StringUtil.replaceDatabaseKeyword_mysql(useridfrom);
		useridto = StringUtil.replaceDatabaseKeyword_mysql(useridto);
		
		//查询总记录数
		int totalCount = userDao.queryUserListCountByPage(fieldno, keyword, useridfrom, useridto, status);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<ProductDto> list = userDao.queryUserListByPage(fieldno, keyword, useridfrom, useridto, status,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

}
