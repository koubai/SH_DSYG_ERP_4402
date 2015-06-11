package com.cn.dsyg.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cn.common.action.BaseAction;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.service.ChartService;


public class ChartAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3220766635860750596L;

	private static final Logger log = LogManager.getLogger(ChartAction.class);

	private static final String SUCCESS = null;

	private static final String ERROR = null;

	private String str;  
	private String series;  
	private String series_X;  
	
	public String getSeries_X() {
		return series_X;
	}
	public void setSeries_X(String series_X) {
		this.series_X = series_X;
	}

	private ChartService chartService;

	private JSONArray m_jsonArr;
	
	public JSONArray getM_jsonArr() {
		return m_jsonArr;
	}
	public void setM_jsonArr(JSONArray m_jsonArr) {
		this.m_jsonArr = m_jsonArr;
	}
	public ChartService getChartService() {
		return chartService;
	}
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getStr() {  
        return str;  
    }  
    public void setStr(String str) {  
        this.str = str;  
    }
    
    public String showSaleInfoMainChartAction(){  
    	try{
            System.out.println("showSaleInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showSaleInfoMainChartAction error:" + e);
			return ERROR;
		}
	}
    
    public String getDataAction() {  
		log.error("getDataAction");
//    	JSONArray arr = getData();
        return SUCCESS;  
    }
    
    public String chgDate(Integer interval) {  
    	Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	    long IntOutTime = currentTime.getTime() + 3600000 * 24 * 30 * interval;
	    Date outTime = new Date(IntOutTime);
	    String strOutTime = formatter.format(outTime);
	    return strOutTime;
    	
    }
	
    // Get Saler's individual data
    public String getSaleDataAction() {  
    	JSONArray arr = chartService.getData("1",chgDate(0), chgDate(-3), "");        	
        return SUCCESS;  
    } 
    // Get Buyer's individual data
    public String getBuyDataAction() {  
    	JSONArray arr = chartService.getData("2",chgDate(0), chgDate(-3), "");        	
        return SUCCESS;  
    } 
    // Get Delivery's individual data
    public String getDeliveryDataAction() {  
    	JSONArray arr = chartService.getData("3",chgDate(0), chgDate(-3), "");        	
        return SUCCESS;  
    } 
    // Get Accounting's individual data
    public String getAccountDataAction() {  
    	JSONArray arr = chartService.getData("4",chgDate(0), chgDate(-3), "");        	
        return SUCCESS;  
    }
          
}
