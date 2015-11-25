package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dao.PositionDao;
import com.cn.dsyg.dao.ProductDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dto.PositionCollectDto;
import com.cn.dsyg.dto.PositionDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.PositionService;

/**
 * @name PositionServiceImpl.java
 * @author Frank
 * @time 2015-9-19下午2:55:29
 * @version 1.0
 */
public class PositionServiceImpl implements PositionService {
	
	private PositionDao positionDao;
	private ProductDao productDao;
	private UserDao userDao;
	
	@Override
	public List<PositionCollectDto> queryPositionCollectByDay(String handler,
			String checkday) {
		return positionDao.queryPositionCollectByDay(handler, checkday);
	}

	@Override
	public Page queryPositionCollectByPage(String handler, String checkday,
			Page page) {
		//查询总记录数
		int totalCount = positionDao.queryPositionCollectCountByPage(handler, checkday);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<PositionCollectDto> list = positionDao.queryPositionCollectByPage(handler, checkday,
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		if(list != null && list.size() > 0) {
			UserDto user = null;
			for(PositionCollectDto positionCollect : list) {
				user = userDao.queryUserByID(positionCollect.getHandler());
				if(user != null) {
					positionCollect.setHandlername(user.getUsername());
				} else {
					positionCollect.setHandlername(positionCollect.getHandler());
				}
			}
		}
		page.setItems(list);
		return page;
	}

	@Override
	public PositionDto queryPositionByLogicId(String productid, String checkday) {
		List<PositionDto> list = positionDao.queryPositionByLogicId("", productid, checkday);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<PositionDto> queryPositionListByLogicId(String userid, String productid,
			String checkday) {
		List<PositionDto> list = positionDao.queryPositionByLogicId(userid, productid, checkday);
		if(list != null && list.size() > 0) {
			//ProductDto product = null;
			UserDto user = null;
			for(PositionDto position : list) {
//				product = productDao.queryProductByID(position.getProductid());
//				if(product != null) {
//					position.setTradename(product.getTradename());
//					position.setTypeno(product.getTypeno());
//					position.setColor(product.getColor());
//					position.setPackaging(product.getPackaging());
//					position.setUnit(product.getUnit());
//					position.setItem10(product.getItem10());
//				}
				user = userDao.queryUserByID(position.getHandler());
				if(user != null) {
					position.setHandlername(user.getUsername());
				} else {
					position.setHandlername(position.getHandler());
				}
			}
		}
		return list;
	}

	@Override
	public PositionDto queryPositionById(String id) {
		return positionDao.queryPositionById(id);
	}

	@Override
	public void insertPosition(PositionDto position) {
		positionDao.insertPosition(position);
	}

	@Override
	public void updatePosition(PositionDto position) {
		positionDao.updatePosition(position);
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
