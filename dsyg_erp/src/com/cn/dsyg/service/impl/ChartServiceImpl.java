package com.cn.dsyg.service.impl;

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
import org.springframework.context.ApplicationContext;

import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.ChartDao;
import com.cn.dsyg.dao.UserDao;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.dto.ChartSaleTotalDto;
import com.cn.dsyg.dto.WarehouseCostDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.ChartService;
import com.cn.dsyg.servlet.ChartServlet;

public class ChartServiceImpl implements ChartService{

	private ChartDao chartDao;
	private UserDao userDao;
	private ApplicationContext ctx;
	private static final Logger log = LogManager.getLogger(ChartServlet.class);

	private String str;  
	private String series;  
	private String series_X;  
	private JSONArray m_jsonArr;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	// Y 轴数据
	public String getSeries() {
		return series;
	}

	// Y 轴数据
	public void setSeries(String series) {
		this.series = series;
	}

	// X 轴数据
	public String getSeries_X() {
		return series_X;
	}

	// X 轴数据
	public void setSeries_X(String series_X) {
		this.series_X = series_X;
	}

	// 全体数据
	public JSONArray getM_jsonArr() {
		return m_jsonArr;
	}

	// 全体数据
	public void setM_jsonArr(JSONArray m_jsonArr) {
		this.m_jsonArr = m_jsonArr;
	}

	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public ChartDao getChartDao() {
		return chartDao;
	}

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	// 取得销售数据
	public List<ChartDto> getSaleData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = querySalesByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }

	// 取得销售详细数据
	public List<ChartDto> getSaleDetailData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = querySalesDetailByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }
    
	// 取得采购数据
    public List<ChartDto> getBuyData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = queryPurchaseByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }

	// 取得快递数据
    public List<ChartDto> getDeliveryData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = queryWareHouseRptByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }
    
	// 取得会计数据
    public List<ChartDto> getAccountData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = queryFinanceByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }

	// 取得产品销售数据
    public List<ChartDto> getAccountSubData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList, String tp) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = queryProductProfitByDate(belongto, theme, from_date, to_date, dur_type, handerList, tp);
    	
    	return list;
    }

    // 取得供应商数据
    public List<ChartDto> getSupplierData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = querySupplierByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }

    // 取得客户数据
    public List<ChartDto> getCustomerData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
        list = queryCustomerByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }

	// 取得销售额数据
	public List<ChartSaleTotalDto> getSaleTotalData(String belongto, String theme, String from_date, String to_date, String dur_type, String handerList) {  
    	List<ChartSaleTotalDto>  list = new ArrayList<ChartSaleTotalDto>();
        list = querySaleTotalByDate(belongto, theme, from_date, to_date, dur_type, handerList);
    	
    	return list;
    }
    
    // get 采购 data by date
	public List<ChartDto> queryPurchaseByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.queryPurchaseByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.queryPurchaseByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

    // get 销售 data by date
	public List<ChartDto> querySalesByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.querySalesByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.querySalesByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;		
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	// get 销售 data by date
	public List<ChartDto> querySalesDetailByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.querySalesDetailByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.querySalesDetailByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

    // get 库存 data by date
	public List<ChartDto> queryWareHouseRptByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
			
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.queryWareHouseRptByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.queryWareHouseRptByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

    // get 财务 data by date
	public List<ChartDto> queryFinanceByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.queryFinanceByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.queryFinanceByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
    // get Product profit data by date
	public List<ChartDto> queryProductProfitByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList, String tp) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.queryProductProfitByDate(belongto, theme1, from_date, to_date, dur_type, s, tp);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.queryProductProfitByDate(belongto, theme1, from_date, to_date, dur_type, handerList, tp);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
    // get supplier data by date
	public List<ChartDto> querySupplierByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.querySupplierByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.querySupplierByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
    // get customer data by date
	public List<ChartDto> queryCustomerByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartDto> alllist = new ArrayList<ChartDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartDto> list = chartDao.queryCustomerByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && list.get(0).getId() != null)
						alllist.addAll(list);
				}
			} else {
				List<ChartDto> list = chartDao.queryCustomerByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && list.get(0).getId() != null)
					alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
    // get Sale Total data by date
	public List<ChartSaleTotalDto> querySaleTotalByDate(String belongto, String theme1, String from_date, String to_date, String dur_type, String handerList) {
		try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );
				
			List<ChartSaleTotalDto> alllist = new ArrayList<ChartSaleTotalDto>();
			if(StringUtil.isNotBlank(handerList)) {
				String ss[] = handerList.split(",");
				for(String s : ss) {
					List<ChartSaleTotalDto> list = chartDao.querySaleTotalByDate(belongto, theme1, from_date, to_date, dur_type, s);
					if (list.size() > 0 && (list.get(0).getTotaltaxamount()!= null || list.get(0).getExpresstaxamount()!= null|| list.get(0).getBusinesstripamount()!= null))
						alllist.addAll(list);
				}
			} else {
				List<ChartSaleTotalDto> list = chartDao.querySaleTotalByDate(belongto, theme1, from_date, to_date, dur_type, handerList);
				if (list.size() > 0 && (list.get(0).getTotaltaxamount()!= null || list.get(0).getExpresstaxamount()!= null|| list.get(0).getBusinesstripamount()!= null))
						alllist.addAll(list);
			}
			return alllist;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	// 从数据库中取得各类数据，放入JSON，由年单位转为月数组，并排序
    public JSONArray getWarehouseCostData() { 
    	try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );

			ArrayList<WarehouseCostDto>  list = new ArrayList<WarehouseCostDto>();
    		list = (ArrayList<WarehouseCostDto>) chartDao.queryWarehouseCost();
        	JSONArray jsonArr = new JSONArray();  
        	for (int i=0; i<list.size(); i++){
        	    JSONObject item = null;  
        	    ArrayList<String> datalist = new ArrayList<String>();  
        	    datalist.add(list.get(i).getWarehouseCost());
        	    datalist.add(list.get(i).getLeaveQuantity());
        	    item = new JSONObject();
        	    item.put("name", list.get(i).getTypeName());
        	    item.put("data", datalist);
        	    jsonArr.put(item);
        	}
        	return jsonArr;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
    }

	// 从数据库中取得各类数据，放入JSON，由年单位转为月数组，并排序
    public JSONArray getUnOutWarehouseCostData() { 
    	try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );

			ArrayList<WarehouseCostDto>  list = new ArrayList<WarehouseCostDto>();
    		list = (ArrayList<WarehouseCostDto>) chartDao.queryUnOutWarehouseCost();
        	JSONArray jsonArr = new JSONArray();  
        	for (int i=0; i<list.size(); i++){
        	    JSONObject item = null;  
        	    ArrayList<String> datalist = new ArrayList<String>();  
        	    datalist.add(list.get(i).getWarehouseCost());
        	    datalist.add(list.get(i).getLeaveQuantity());
        	    item = new JSONObject();
        	    item.put("name", list.get(i).getTypeName());
        	    item.put("data", datalist);
        	    jsonArr.put(item);
        	}
        	return jsonArr;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
    }

	// 从数据库中取得各类数据，放入JSON，由年单位转为月数组，并排序
    public JSONArray getUnInWarehouseCostData() { 
    	try {
			if (ctx != null){
	        	chartDao = (ChartDao)ctx.getBean("chartDao");
		        System.out.println("chartDao not null" );
			}else
		        System.out.println("chartDao is null" );

			ArrayList<WarehouseCostDto>  list = new ArrayList<WarehouseCostDto>();
    		list = (ArrayList<WarehouseCostDto>) chartDao.queryUnInWarehouseCost();
        	JSONArray jsonArr = new JSONArray();  
        	for (int i=0; i<list.size(); i++){
        	    JSONObject item = null;  
        	    ArrayList<String> datalist = new ArrayList<String>();  
        	    datalist.add(list.get(i).getWarehouseCost());
        	    datalist.add(list.get(i).getLeaveQuantity());
        	    item = new JSONObject();
        	    item.put("name", list.get(i).getTypeName());
        	    item.put("data", datalist);
        	    jsonArr.put(item);
        	}
        	return jsonArr;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
    }

    
	// 从数据库中取得各类数据，放入JSON，由年单位转为月数组，并排序
    public JSONArray getData(String belongto, String pattern, String from_date, String to_date, String dur_type, String handerList) {  
    	int i_fy;
    	int i_ty;
    	int i_fm;
    	int i_tm;
    	String user_id = "";
    	String tmp_user_id = "";
    	JSONArray jsonArr = new JSONArray();  
    	    
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//        to_date = sdf.format(new Date(System.currentTimeMillis()));
//        from_date = sdf.format(add(new Date(System.currentTimeMillis()), period_days));

//        from_date= "2015-06-01";
//        to_date = "2015-12-31";
        System.out.println("from_date:" + from_date);
        System.out.println("to_date:" + to_date);

        i_fy =  Integer.parseInt(from_date.substring(2,4));
        i_ty =  Integer.parseInt(to_date.substring(2,4));
        i_fm =  Integer.parseInt(from_date.substring(5,7));
        i_tm =  Integer.parseInt(to_date.substring(5,7));
        
        try {
        	// get Sale summary data 
        	if (pattern.equals("8")){
            	List<ChartSaleTotalDto>  saletotallist = new ArrayList<ChartSaleTotalDto>();
            	saletotallist = getSaleTotalData(belongto, "", from_date, to_date, dur_type, handerList);

                JSONObject item = null;  

            	//Add user information into json array 
                item = new JSONObject(); 

                if (saletotallist.size()>0) {
            		item.put("name", "1");            	
                	ChartSaleTotalDto saletotaldto = saletotallist.get(0);

                	ArrayList<String> arrY=new ArrayList<String>();
                	if (saletotaldto.getTotaltaxamount()!=null)
                		arrY.add(saletotaldto.getTotaltaxamount());  
                	else 
                		arrY.add("0");
                	if (saletotaldto.getExpresstaxamount()!=null)
                		arrY.add(saletotaldto.getExpresstaxamount());  
                	else 
                		arrY.add("0");
                	if (saletotaldto.getBusinesstripamount()!=null)
                		arrY.add(saletotaldto.getBusinesstripamount());  
                	else 
                		arrY.add("0");
            		
            		item.put("data", arrY);            	
                    System.out.println("GetData:" + arrY);
                    jsonArr.put(item);  		         			        	
                }        	
        	} else {
            	List<ChartDto>  list = new ArrayList<ChartDto>();

            	// get Buyer's data 
            	if (pattern.equals("1")){
            		list = getBuyData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Saler's data 
            	else if (pattern.equals("2")){
            		list = getSaleData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Delivery's data 
            	else if (pattern.equals("3")){
            		list = getDeliveryData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Account's data 
            	else if (pattern.equals("4")){
            		list = getAccountData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Saler's detail data 
            	else if (pattern.equals("5")){
            		list = getSaleDetailData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get supplier data 
            	else if (pattern.equals("6")){
            		list = getSupplierData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Saler's detail data 
            	else if (pattern.equals("7")){
            		list = getCustomerData(belongto, "", from_date, to_date, dur_type, handerList);
            	}
            	// get Account's data 
            	else if (pattern.equals("9")){
            		list = getAccountSubData(belongto, "", from_date, to_date, dur_type, handerList, "0");
            	}
            	else if (pattern.equals("10")){
            		list = getAccountSubData(belongto, "", from_date, to_date, dur_type, handerList, "1");
            	}
        		
                Map<String, String> item_map = null;
                Map<String, String> temp_item_map = null;
                Map<String, String> user_item_map = null;

                if (list==null || list.size()<= 0)
    	            System.out.println("getData list.size is 0 or null");	        	
    	        if (list.size() > 0) {
    	            System.out.println("list.size:" + list.size());
    		        for (int z = 0; z < list.size(); z++) {  
    		            System.out.println("Z:" + z);
    		        	ChartDto chd = list.get(z);
    		        	user_id = chd.getHandler();	        	
    		            System.out.println("user_id_loop:" + user_id);
    		        	if (!tmp_user_id.equals(user_id)){
    		        		// part of every user_id 
    			            System.out.println("This user_id is:" + user_id);
    			            if (temp_item_map != null){
    			            	// put pre_user's data into array
    				        	item_map= sort(user_item_map);
    				        	jsonArr = setJsonData(jsonArr, tmp_user_id,  item_map );
    			            }
    			            // initial the user's data map
    			            temp_item_map = getInitDataMap(i_fy, i_ty, i_fm, i_tm, dur_type);
    		        	}
    		            if (temp_item_map != null){
    		            	// add user data to his data map
    		            	user_item_map = setDataMap(temp_item_map, chd);
    		            }
    		        	tmp_user_id = user_id;	        	
    		        }	                  
    	        }

                if (temp_item_map != null){
    	        	item_map= sort(user_item_map);
    	        	jsonArr = setJsonData(jsonArr, tmp_user_id,  item_map );
                }
                
                JSONObject[] arr=new JSONObject[jsonArr.length()];
                System.out.println("JsonArr length:" + jsonArr.length());
        	    System.out.println("JO: " + jsonArr);  
        	    setM_jsonArr(jsonArr);            
        	}
		}
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
        return(jsonArr);  
    }	
    
	public Map<String, String> getInitDataMap(int i_fy, int i_ty, int i_fm, int i_tm, String dur_type) {  
		Map<String, String> data_map = new HashMap<String, String>();

		int i_year = 0;
		int i_month = 0;
		int dv = 1;
		if (dur_type.equals("1"))
			dv = 1;
		else if (dur_type.equals("2"))
			dv = 3;
		else if (dur_type.equals("3"))
			dv = 12;
		
		if (dur_type.equals("1")){
			for (int i = 0; i < (i_ty - i_fy)*12 + (i_tm - i_fm) + 1; i++ ){
				i_year = i_fy  + (i + i_fm)/12;
				i_month = (i + i_fm)%12; 
				if (i_month == 0){
					i_year--;
					i_month = 12;
				}				
				data_map.put((Integer.toString(i_year)+String.format("%02d", i_month)),"0.00");
		        System.out.println("key:" +(Integer.toString(i_year)+String.format("%02d", i_month)));
			}
		}else if (dur_type.equals("2")){
			for (int j = 0; j < ((i_ty - i_fy)*12 + (i_tm - i_fm) + 1)/dv; j++ ){
				i_year = i_fy  + (j * dv + i_fm) / 12 ;
				i_month = ((j*dv + i_fm - 1) / dv+1) % 4; 				
				if (i_month == 0){
					i_month = 4;
				}				
				data_map.put((Integer.toString(i_year)+String.format("%02d", i_month)),"0.00");
		        System.out.println("key:" +(Integer.toString(i_year)+String.format("%02d", i_month)));
			}
		}else if (dur_type.equals("3")){
			for (int k = 0; k < (i_ty - i_fy) + 1; k++ ){
				i_year = i_fy  + k;
				i_month = 1; 				
				data_map.put((Integer.toString(i_year)+String.format("%02d", i_month)),"0.00");
		        System.out.println("key:" +(Integer.toString(i_year)+String.format("%02d", i_month)));
			}
		}
		return data_map;
	}

	public Map<String, String> setDataMap( Map<String, String> data_map, ChartDto chd ) {  
		String str = data_map.get(chd.getX_Year()+"01");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"01");
			data_map.put(chd.getX_Year()+"01",chd.getY_Month_01());
		}
		str = data_map.get(chd.getX_Year()+"02");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"02");
			data_map.put(chd.getX_Year()+"02",chd.getY_Month_02());
		}
		str = data_map.get(chd.getX_Year()+"03");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"03");
			data_map.put(chd.getX_Year()+"03",chd.getY_Month_03());
		}
		str = data_map.get(chd.getX_Year()+"04");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"04");
			data_map.put(chd.getX_Year()+"04",chd.getY_Month_04());
		}
		str = data_map.get(chd.getX_Year()+"05");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"05");
			data_map.put(chd.getX_Year()+"05",chd.getY_Month_05());
		}
		str = data_map.get(chd.getX_Year()+"06");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"06");
			data_map.put(chd.getX_Year()+"06",chd.getY_Month_06());
		}
		str = data_map.get(chd.getX_Year()+"07");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"07");
			data_map.put(chd.getX_Year()+"07",chd.getY_Month_07());
		}
		str = data_map.get(chd.getX_Year()+"08");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"08");
			data_map.put(chd.getX_Year()+"08",chd.getY_Month_08());
		}
		str = data_map.get(chd.getX_Year()+"09");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"09");
			data_map.put(chd.getX_Year()+"09",chd.getY_Month_09());
		}
		str = data_map.get(chd.getX_Year()+"10");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"10");
			data_map.put(chd.getX_Year()+"10",chd.getY_Month_10());
		}
		str = data_map.get(chd.getX_Year()+"11");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"11");
			data_map.put(chd.getX_Year()+"11",chd.getY_Month_11());
		}
		str = data_map.get(chd.getX_Year()+"12");
		if (str!=null && str !=""){
			data_map.remove(chd.getX_Year()+"12");
			data_map.put(chd.getX_Year()+"12",chd.getY_Month_12());
		}
		return data_map;
	}
    
	public Map sort(Map map) { 
        Map<Object, Object> mapVK = new TreeMap<Object, Object>( 
                new Comparator<Object>() { 
                    public int compare(Object obj1, Object obj2) { 
                        String v1 = (String)obj1; 
                        String v2 = (String)obj2; 
                        int s = v2.compareTo(v1); 
                        return -s; 
                    } 
                } 
            ); 
            Set col = map.keySet(); 
            Iterator iter = col.iterator(); 
            while (iter.hasNext()) { 
                String key = (String) iter.next(); 
                String value = (String) map.get(key); 
                mapVK.put(key, value); 
            } 
            return mapVK; 
    } 

	public Date add(Date day,int dist) {  
        Calendar calendar = new GregorianCalendar();  
        calendar.setTime(day);  
        calendar.add(calendar.DATE, dist);  
        day = calendar.getTime();  
        return day;  
    }  	

    public JSONArray setJsonData(JSONArray jsonArr, String use_id, Map<String, String> item_map ) throws JSONException{  
        JSONObject item = null;  

    	//Add user information into json array 
        item = new JSONObject(); 
        String tmp_Series_X = getSeries_X();

        if(StringUtil.isNotBlank(use_id)) {
        	item.put("name", use_id.replace("\"", ""));
        } else {
        	item.put("name", use_id);
        }
    	Set sortSet = item_map.entrySet(); 
        Iterator ii = sortSet.iterator();
        ArrayList arrY=new ArrayList();
        while(ii.hasNext()){ 
            Map.Entry<String, String> entry1=(Map.Entry<String, String>)ii.next(); 
            System.out.println(entry1.getKey() + "-------->" + entry1.getValue()); 
            arrY.add(entry1.getValue());  

            if (tmp_Series_X == null || tmp_Series_X=="")
            	tmp_Series_X = entry1.getKey();
            else
            	tmp_Series_X = tmp_Series_X + "," + entry1.getKey();  
        } 
        
        if (getSeries_X()== null || getSeries_X()== "" )
        	setSeries_X("[" + tmp_Series_X + "]");

		item.put("data", arrY);            	
        jsonArr.put(item);  		         			        	
        return jsonArr;  
    }

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
