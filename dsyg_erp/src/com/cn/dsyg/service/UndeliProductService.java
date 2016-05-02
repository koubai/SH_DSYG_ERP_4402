package com.cn.dsyg.service;

import java.util.List;

import com.cn.dsyg.dto.UndeliProductDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface UndeliProductService {
	
	/**
	 * 根据产品种类查未出入库产品（查询remains的记录）
	 * @param fromDate  检索开始日  
	 * @param toDate    检索终了日
	 * @param field     产品种类
	 * @return
	 */
	public List<UndeliProductDto> queryUnDeliProductByType(String fromDate, String toDate, String iotype, String field);
	
}
