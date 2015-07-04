package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.DeliveryDao;
import com.cn.dsyg.dto.DeliveryDto;
import com.cn.dsyg.service.DeliveryService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class DeliveryServiceImpl implements DeliveryService {
	
	private DeliveryDao deliveryDao;

	@Override
	public DeliveryDto queryAllEtbDeliveryByID(String ID) {
		return deliveryDao.queryAllEtbDeliveryByID(ID);
	}

	@Override
	public Page queryEtbDeliveryByPage(Page page, String deliveryNoLow,
			String deliveryNoHigh, String deliveryName) {
		deliveryNoLow = StringUtil.replaceDatabaseKeyword_mysql(deliveryNoLow);
		//查询总记录数
		int totalCount = deliveryDao.queryEtbDeliveryCountByPage(deliveryNoLow, deliveryNoHigh, deliveryName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<DeliveryDto> list = deliveryDao.queryEtbDeliveryByPage(deliveryNoLow, deliveryNoHigh,
				deliveryName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public DeliveryDto queryEtbDeliveryByID(String deliveryNo) {
		return deliveryDao.queryEtbDeliveryByID(deliveryNo);
	}

	@Override
	public List<DeliveryDto> queryAllEtbDelivery() {
		return deliveryDao.queryAllEtbDelivery();
	}

	@Override
	public void insertEtbDelivery(DeliveryDto delivery) {
		deliveryDao.insertEtbDelivery(delivery);
	}

	@Override
	public void updateEtbDelivery(DeliveryDto delivery) {
		deliveryDao.updateEtbDelivery(delivery);
	}

	@Override
	public void deleteEtbDelivery(String deliveryNo, String username) {
		DeliveryDto delivery = deliveryDao.queryEtbDeliveryByID(deliveryNo);
		if(delivery != null) {
			//状态=已删除
			delivery.setStatus(Constants.STATUS_DEL);
			delivery.setUpdateuid(username);
			deliveryDao.updateEtbDelivery(delivery);
		}
	}

	@Override
	public List<DeliveryDto> queryAllEtbDeliveryExport(String deliveryNoLow,
			String deliveryNoHigh) {
		return deliveryDao.queryAllEtbDeliveryExport(deliveryNoLow, deliveryNoHigh);
	}

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}
}
