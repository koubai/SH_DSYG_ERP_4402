package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dto.ChartSaleTotalDto;
import com.cn.dsyg.dto.WarehouseCostDto;

public interface ChartDao {
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @return
	 */
	public List<ChartDto> queryPurchaseByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySalesByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySalesDetailByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryWareHouseRptByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryFinanceByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySupplierByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryCustomerByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartSaleTotalDto> querySaleTotalByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryProductProfitByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList, String tp);
	public List<WarehouseCostDto> queryWarehouseCost();
	public List<WarehouseCostDto> queryUnOutWarehouseCost();
	public List<WarehouseCostDto> queryUnInWarehouseCost();

}
