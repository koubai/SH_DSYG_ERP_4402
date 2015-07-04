package com.cn.dsyg.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;  
import java.io.PrintWriter;  
import java.text.SimpleDateFormat;  
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

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.service.ChartService;

public class ChartServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7285713183683200542L;
	/**
	 * 
	 */
	private ChartService chartService;

	
	public Map<String, String> getInitDataMap(int i_fy, int i_ty, int i_fm, int i_tm) {  
		Map<String, String> data_map = new HashMap<String, String>();
		
		for (int i = i_fy; i < i_ty + 1; i++ ){
			for (int j = i_fm; (j < 13 && i_fy != i_ty) || (j<i_tm+1 && i_fy == i_ty); j++ ){
				data_map.put((Integer.toString(i)+String.format("%02d", Integer.toString(j))),"0");
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
	
	public static Map sort(Map map) { 
        Map<Object, Object> mapVK = new TreeMap<Object, Object>( 
            new Comparator<Object>() { 
                public int compare(Object obj1, Object obj2) { 
                    String v1 = (String)obj1; 
                    String v2 = (String)obj2; 
                    int s = v2.compareTo(v1); 
                    return s; 
                } 
            } 
        ); 

        Set col = map.keySet(); 
        Iterator iter = col.iterator(); 
        while (iter.hasNext()) { 
            String key = (String) iter.next(); 
            Integer value = (Integer) map.get(key); 
            mapVK.put(key, value); 
        } 
        return mapVK; 
    } 
    	
    public JSONArray getData() {  
    	String from_date;
    	String to_date;
    	int period_days = -180;
    	int i_fy;
    	int i_ty;
    	int i_fm;
    	int i_tm;
    	int idx2 = 0;
    	String X_m[];
    	String Y_m[];
    	String tmp_user_id = "";
    	JSONArray jsonArr = new JSONArray();  
        JSONObject item = null;  
    	    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        to_date = sdf.format(new Date(System.currentTimeMillis()));
        from_date = sdf.format(add(new Date(System.currentTimeMillis()), period_days));
        System.out.println("from_date:" + from_date);
        System.out.println("to_date:" + to_date);

        i_fy =  Integer.parseInt(from_date.substring(2,3));
        i_ty =  Integer.parseInt(to_date.substring(2,3));
        i_fm =  Integer.parseInt(from_date.substring(5,6));
        i_tm =  Integer.parseInt(to_date.substring(5,6));
        
		
		
		
		for (int x = i_fm - 1; x < (i_ty-i_fy)*12 + i_tm; x++ ){
			X_map.put(Integer.toString(i_fy),"0");
			item.put("x_data", x);
			item.put("y_data", Y_m[x]);
			if (j == i_tm){
				break;
			}
			j++ ;
		}

        
        
        try {
	        List<ChartDto> list = chartService.queryPurchaseByDate(from_date, to_date);
	        if (list.size() > 0) 
	        	tmp_user_id = list.get(0).getHandler();
            int idx = 0;
	        for (int z = 0; z < list.size(); z++) {  
	        	ChartDto chd = list.get(z);
	        	if (list.get(z).getHandler() != tmp_user_id){
	        		tmp_user_id = list.get(z).getHandler();
	        	}
	        	
	        	
	        	
	            System.out.println("----------------"); 
	            //排序后的输出 
	           Map<String, Integer> sortMaps = sort(maps); 
	           Set sortSet = sortMaps.entrySet(); 
	           Iterator ii = sortSet.iterator(); 
	           while(ii.hasNext()){ 
	               Map.Entry<String, Integer> entry1=(Map.Entry<String, Integer>)ii.next(); 
	               System.out.println(entry1.getKey() + "-------->" + entry1.getValue()); 
	           } 
	          
	        	
	            item = new JSONObject();  
				item.put("name", tmp_user_id);            	
	        	for (int i = 0; i < 12; i++){
	        		X_m[idx*12+i] = new String(chd.getX_Year() + "/" + Integer.toString(i+1)); 
	        		switch(i+1)
	        		{
	        		case 1:
	            		Y_m[idx*12+i] = new String(chd.getY_Month_01());
	        			break;
	        			
	        		case 2:
	            		Y_m[idx*12+i] = new String(chd.getY_Month_02());
	        			break;
	
	        		case 3: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_03());
	        			break;
	
	        		case 4: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_04());
	        			break;
	
	        		case 5: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_05());
	        			break;
	
	        		case 6: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_06());
	        			break;
	
	        		case 7: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_07());
	        			break;
	        		
	        		case 8: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_08());
	        			break;
	        		
	        		case 9: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_09());
	        			break;
	
	        		case 10: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_10());
	        			break;
	
	        		case 11: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_11());
	        			break;
	
	        		case 12: 
	            		Y_m[idx*12+i] = new String(chd.getY_Month_12());
	        			break;
	        		}
	        		idx++;
	        	}
	        }	
	        
	        jsonArr.put(item);  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		
        return(jsonArr);  
    }
	
	public ChartServlet() {  
        super();  
    }  
    public void destroy() {  
        super.destroy(); // Just puts "destroy" string in log  
    }  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        this.doPost(request, response);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("application/json;charset=utf-8");  
        request.setCharacterEncoding("utf-8");  
        PrintWriter out = response.getWriter();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
          
        //生成一组随机的时间序列  
        JSONArray jsonArr = new JSONArray();  
        JSONObject item = null;  
        for (int i = 0; i < 10; i++) {  
            item = new JSONObject();  
            //从今日开始统计  
            try {
				item.put("id", sdf.format(add(new Date(),i)));
				item.put("heigh", Math.round(1000*Math.random()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            jsonArr.put(item);  
        }  
        out.println(jsonArr.toString());  
        out.flush();  
        out.close();  
    }  
      
    //日期加N天  
    public Date add(Date day,int dist) {  
        Calendar calendar = new GregorianCalendar();  
        calendar.setTime(day);  
        calendar.add(calendar.DATE, dist);  
        day = calendar.getTime();  
        return day;  
    }  
        
    public void init() throws ServletException {  
        // Put your code here  
    }  
 
}
