package com.cn.dsyg.service.impl;

import com.cn.dsyg.dao.PositionDao;
import com.cn.dsyg.dto.PositionDto;
import com.cn.dsyg.service.PositionService;

/**
 * @name PositionServiceImpl.java
 * @author Frank
 * @time 2015-9-19下午2:55:29
 * @version 1.0
 */
public class PositionServiceImpl implements PositionService {
	
	private PositionDao positionDao;

	@Override
	public PositionDto queryPositionByLogicId(String productid, String warehousename) {
		return positionDao.queryPositionByLogicId(productid, warehousename);
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
}
