package com.cn.dsyg.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;

import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dto.ChartDto;

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
    public JSONArray getData(String pattern, String from_date, String to_date, String handerList);
	public List<ChartDto> getSaleData(String theme, String from_date, String to_date, String handerList);
    public List<ChartDto> getBuyData(String theme, String from_date, String to_date, String handerList);
    public List<ChartDto> getDeliveryData(String theme, String from_date, String to_date, String handerList);
    public List<ChartDto> getAccountData(String theme, String from_date, String to_date, String handerList);
	public List<ChartDto> queryPurchaseByDate(String theme1, String from_date, String to_date, String handerList);
	public List<ChartDto> querySalesByDate(String theme1, String from_date, String to_date, String handerList);
	public List<ChartDto> queryWareHouseRptByDate(String theme1, String from_date, String to_date, String handerList);
	public List<ChartDto> queryFinanceByDate(String theme1, String from_date, String to_date, String handerList);
	public Map<String, String> getInitDataMap(int i_fy, int i_ty, int i_fm, int i_tm);  
	public Map<String, String> setDataMap( Map<String, String> data_map, ChartDto chd );  
    public Date add(Date day, int dist);
    public JSONArray setJsonData(JSONArray jsonArr, String use_id, Map<String, String> item_map) throws JSONException;  
	public Map sort(Map map);

}
