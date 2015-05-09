package com.cn.dsyg.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.cn.common.action.BaseAction;

public class ChartAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(FrameAction.class);

	private String str;  
	private String series;  

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
	        str  = "[49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]";  
	        setStr(str);
	        series = "[{ name: '方', data:[59.9, 81.5, 96.4, 129.8, 124.0, 196.0, 132.6, 140.5, 220.4, 214.1, 100.6, 50.4]},";
	        series += " {name: '陈', data:[49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4] }] ";
	        setSeries(series); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showManageHomeAction error:" + e);
			return ERROR;
		}
	}
}
