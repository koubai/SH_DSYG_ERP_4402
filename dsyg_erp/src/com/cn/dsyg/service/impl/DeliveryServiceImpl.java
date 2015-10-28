package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.DeliveryDao;
import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dto.DeliveryDto;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.service.DeliveryService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class DeliveryServiceImpl implements DeliveryService {
	
	private DeliveryDao deliveryDao;
	private Dict01Dao dict01Dao;

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}

	@Override
	public DeliveryDto queryAllEtbDeliveryByID(String ID) {
		return deliveryDao.queryAllEtbDeliveryByID(ID);
	}
	
	@Override
	public DeliveryDto queryAllEtbDeliveryByName(String deliveryName){
		return deliveryDao.queryAllEtbDeliveryByName(deliveryName);
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
		//快递商番号
		String code = "";
		
		List<Dict01Dto> listDict = dict01Dao.queryDict01ByFieldcode(Constants.DICT_EXPRESSER_ORDER, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listDict != null && listDict.size() > 0) {
			Dict01Dto dict = listDict.get(0);
			code = dict.getCode();
			//番号+1
			dict.setCode("" + (Integer.valueOf(dict.getCode()) + 1));
			dict01Dao.updateDict01(dict);
			code = "" + (Integer.valueOf(dict.getCode()) + 1);
		} else {
			//插入数据
			Dict01Dto dict = new Dict01Dto();
			dict.setFieldcode(Constants.DICT_EXPRESSER_ORDER);
			dict.setFieldname("快递商番号");
			//番号默认从1开始
			dict.setCode("1");
			code = "1";
			dict.setLang(PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
			dict.setMean("快递商番号");
			dict.setNote("快递商番号");
			dict.setStatus(Constants.STATUS_NORMAL);
			dict.setCreateuid("admin");
			dict.setUpdateuid("admin");
			dict01Dao.insertDict01(dict);
		}
		delivery.setId(Integer.valueOf(code));
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
