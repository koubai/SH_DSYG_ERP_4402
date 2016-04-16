package com.cn.dsyg.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dto.ChartSaleTotalDto;

public interface ChartService {

	public String getStr();
	public void setStr(String str);
	public String getSeries();
	public void setSeries(String series);
	public String getSeries_X();
	public void setSeries_X(String series_X);
	public JSONArray getM_jsonArr();
	public void setM_jsonArr(JSONArray m_jsonArr);
	public ApplicationContext getCtx();
	public void setCtx(ApplicationContext ctx);
	public ChartDao getChartDao();
	public void setChartDao(ChartDao chartDao);
    public JSONArray getData(String belongto, String pattern, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> getSaleData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> getSaleDetailData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
    public List<ChartDto> getBuyData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
    public List<ChartDto> getDeliveryData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
    public List<ChartDto> getAccountData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
    public List<ChartDto> getAccountSubData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList, String tp);
	public List<ChartDto> getSupplierData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> getCustomerData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryPurchaseByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySalesByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySalesDetailByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryWareHouseRptByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryFinanceByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> querySupplierByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryCustomerByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartSaleTotalDto> getSaleTotalData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList);
	public List<ChartDto> queryProductProfitByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList, String tp);
    public JSONArray getWarehouseCostData();
    public JSONArray getUnOutWarehouseCostData();
    public JSONArray getUnInWarehouseCostData();

	public Map<String, String> getInitDataMap(int i_fy, int i_ty, int i_fm, int i_tm, String dur_type);  
	public Map<String, String> setDataMap( Map<String, String> data_map, ChartDto chd );  
    public Date add(Date day, int dist);
    public JSONArray setJsonData(JSONArray jsonArr, String use_id, Map<String, String> item_map) throws JSONException;  
	public Map sort(Map map);

}
