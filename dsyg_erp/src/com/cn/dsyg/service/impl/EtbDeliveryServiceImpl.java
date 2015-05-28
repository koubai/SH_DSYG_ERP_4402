package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbDeliveryDao;
import com.cn.dsyg.dto.EtbDeliveryDto;
import com.cn.dsyg.service.EtbDeliveryService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbDeliveryServiceImpl implements EtbDeliveryService {
	
	private EtbDeliveryDao etbDeliveryDao;

	@Override
	public EtbDeliveryDto queryAllEtbDeliveryByID(String ID) {
		return etbDeliveryDao.queryAllEtbDeliveryByID(ID);
	}

	public EtbDeliveryDao getEtbDeliveryDao() {
		return etbDeliveryDao;
	}

	public void setEtbDeliveryDao(EtbDeliveryDao etbDeliveryDao) {
		this.etbDeliveryDao = etbDeliveryDao;
	}

	@Override
	public Page queryEtbDeliveryByPage(Page page, String deliveryNoLow,
			String deliveryNoHigh, String deliveryName) {
		deliveryNoLow = StringUtil.replaceDatabaseKeyword_mysql(deliveryNoLow);
		//查询总记录数
		int totalCount = etbDeliveryDao.queryEtbDeliveryCountByPage(deliveryNoLow, deliveryNoHigh, deliveryName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbDeliveryDto> list = etbDeliveryDao.queryEtbDeliveryByPage(deliveryNoLow, deliveryNoHigh,
				deliveryName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbDeliveryDto queryEtbDeliveryByID(String deliveryNo) {
		return etbDeliveryDao.queryEtbDeliveryByID(deliveryNo);
	}

	@Override
	public List<EtbDeliveryDto> queryAllEtbDelivery() {
		return etbDeliveryDao.queryAllEtbDelivery();
	}

	@Override
	public void insertEtbDelivery(EtbDeliveryDto delivery) {
		etbDeliveryDao.insertEtbDelivery(delivery);
	}

	@Override
	public void updateEtbDelivery(EtbDeliveryDto delivery) {
		etbDeliveryDao.updateEtbDelivery(delivery);
	}

	@Override
	public void deleteEtbDelivery(String deliveryNo, String username) {
		EtbDeliveryDto delivery = etbDeliveryDao.queryEtbDeliveryByID(deliveryNo);
		if(delivery != null) {
			//状态=已删除
			delivery.setStatus(Constants.STATUS_DEL);
			delivery.setUpdateuid(username);
			etbDeliveryDao.updateEtbDelivery(delivery);
		}
	}

	@Override
	public List<EtbDeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow,
			String deliveryNoHigh) {
		return etbDeliveryDao.queryAllEtbDeliveryExport(deliveryNoLow, deliveryNoHigh);
	}
}
