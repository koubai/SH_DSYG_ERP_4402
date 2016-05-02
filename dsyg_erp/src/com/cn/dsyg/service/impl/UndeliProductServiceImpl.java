package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.dsyg.dao.Dict01Dao;
import com.cn.dsyg.dao.UndeliProductDao;
import com.cn.dsyg.dto.UndeliProductDto;
import com.cn.dsyg.service.UndeliProductService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class UndeliProductServiceImpl implements UndeliProductService {
	
	private UndeliProductDao undeliproductDao;
	private Dict01Dao dict01Dao;

	public Dict01Dao getDict01Dao() {
		return dict01Dao;
	}

	public void setDict01Dao(Dict01Dao dict01Dao) {
		this.dict01Dao = dict01Dao;
	}


	@Override
	public List<UndeliProductDto> queryUnDeliProductByType(String fromDate, String toDate, String ioType, String fieldno) {
		System.out.println("fromDate" + fromDate);
		System.out.println("toDate" + toDate);
		System.out.println("ioType" + ioType);
		System.out.println("fieldno" + fieldno);
		if (ioType.compareTo("1") == 0){
			System.out.println("queryUnDeliProductByType 111");
			return undeliproductDao.queryUnDeliSaleProductByFieldno(fromDate, toDate, fieldno);
		}
		else if (ioType.compareTo("2") == 0){
			System.out.println("queryUnDeliProductByType 222");
			return undeliproductDao.queryUnDeliPurchaseProductByFieldno(fromDate, toDate, fieldno);
		} else 	
			return undeliproductDao.queryUnDeliSaleProductByFieldno(fromDate, toDate, fieldno);
	}

	public UndeliProductDao getUndeliproductDao() {
		return undeliproductDao;
	}

	public void setUndeliproductDao(UndeliProductDao undeliproductDao) {
		this.undeliproductDao = undeliproductDao;
	}
}
