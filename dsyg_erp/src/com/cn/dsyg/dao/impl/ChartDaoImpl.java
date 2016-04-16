package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dto.ChartSaleTotalDto;
import com.cn.dsyg.dto.WarehouseCostDto;

public class ChartDaoImpl extends BaseDao implements ChartDao {
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @return
	 */
	public List<ChartDto> queryPurchaseByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);
		
		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("queryPurchaseByDateY", paramMap);			
		}
		return list;
	}

	public List<ChartDto> querySalesByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("querySalesByDateY", paramMap);
		}
		return list;
	}

	
	public List<ChartSaleTotalDto> querySaleTotalByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartSaleTotalDto> list = null;
		if (dur_type.equals("0")){
			list = getSqlMapClientTemplate().queryForList("querySaleTotalByDate", paramMap);
		}
		return list;
	}
	
	
	public List<ChartDto> querySalesDetailByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("querySalesDetailByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("querySalesDetailByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("querySalesDetailByDateY", paramMap);
		}
		return list;
	}

	public List<ChartDto> queryWareHouseRptByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("queryDeliveryByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("queryDeliveryByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("queryDeliveryByDateY", paramMap);			
		}

		return list;
	}

	public List<ChartDto> queryFinanceByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("queryFinanceByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("queryFinanceByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("queryFinanceByDateY", paramMap);			
		}
		return list;
	}
	
	public List<ChartDto> queryProductProfitByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList, String tp){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (tp.equals("0")){
			if (dur_type.equals("1")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateM", paramMap);
			} else if (dur_type.equals("2")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateQ", paramMap);
			} else if (dur_type.equals("3")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateY", paramMap);			
			}
		}else{
			if (dur_type.equals("1")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateMB", paramMap);
			} else if (dur_type.equals("2")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateQB", paramMap);
			} else if (dur_type.equals("3")){
				list = getSqlMapClientTemplate().queryForList("queryProductProfitByDateYB", paramMap);			
			}
		}
		return list;
	}

	public List<ChartDto> querySupplierByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("querySupplierByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("querySupplierByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("querySupplierByDateY", paramMap);
		}
		return list;
	}

	public List<ChartDto> queryCustomerByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("belongto", belongto);
		paramMap.put("theme1", theme1);
		paramMap.put("from_date", from_date);
		paramMap.put("to_date", to_date);
		paramMap.put("handerList", handerList);

		List<ChartDto> list = null;
		if (dur_type.equals("1")){
			list = getSqlMapClientTemplate().queryForList("queryCustomerByDateM", paramMap);
		} else if (dur_type.equals("2")){
			list = getSqlMapClientTemplate().queryForList("queryCustomerByDateQ", paramMap);
		} else if (dur_type.equals("3")){
			list = getSqlMapClientTemplate().queryForList("queryCustomerByDateY", paramMap);
		}
		return list;
	}
	
	public List<WarehouseCostDto> queryWarehouseCost(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<WarehouseCostDto> list = null;
		list = getSqlMapClientTemplate().queryForList("queryWarehouseCost", paramMap);
		return list;
	}

	public List<WarehouseCostDto> queryUnOutWarehouseCost(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<WarehouseCostDto> list = null;
		list = getSqlMapClientTemplate().queryForList("queryUnOutWarehouseCost", paramMap);
		return list;
	}
	
	public List<WarehouseCostDto> queryUnInWarehouseCost(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<WarehouseCostDto> list = null;
		list = getSqlMapClientTemplate().queryForList("queryUnInWarehouseCost", paramMap);
		return list;
	}
}
