package com.cn.dsyg.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;  
import java.io.PrintWriter;  
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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.dsyg.action.ChartAction;
import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.service.ChartService;
import com.cn.dsyg.service.impl.ChartServiceImpl;

public class ChartServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7285713183683200542L;
	private static final Logger log = LogManager.getLogger(ChartServlet.class);

	private ChartService chartService;
	private String str;  
	public ApplicationContext ctx;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSeries_X() {
		return series_X;
	}

	public void setSeries_X(String series_X) {
		this.series_X = series_X;
	}

	private String series;  
	private String series_X;  
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

	
	public Map<String, String> getInitDataMap(int i_fy, int i_ty, int i_fm, int i_tm) {  
		Map<String, String> data_map = new HashMap<String, String>();

		int i_year = 0;
		int i_month = 0;
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


    	
    public JSONArray setJsonData(JSONArray jsonArr, String use_id, Map<String, String> item_map ) throws JSONException {  
        JSONObject item = null;  

    	//Add user information into json array 
        item = new JSONObject(); 
        String tmp_Series_X = getSeries_X();

		item.put("name", use_id.replace("\"", ""));            	
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

    public List<ChartDto> getSaleData(String theme, String from_date, String to_date) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
    	chartService = (ChartService)ctx.getBean("chartService");
    	chartService.setCtx(ctx);    	
        list = chartService.queryPurchaseByDate(theme, from_date, to_date);
    	
    	return list;
    }
    
    public List<ChartDto> getBuyData(String theme, String from_date, String to_date) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
    	chartService = (ChartService)ctx.getBean("chartService");
    	chartService.setCtx(ctx);    	
        list = chartService.queryPurchaseByDate(theme, from_date, to_date);
    	
    	return list;
    }

    public List<ChartDto> getDeliveryData(String theme, String from_date, String to_date) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
    	chartService = (ChartService)ctx.getBean("chartService");
    	chartService.setCtx(ctx);    	
        list = chartService.queryPurchaseByDate(theme, from_date, to_date);
    	
    	return list;
    }
    
    public List<ChartDto> getAccountData(String theme, String from_date, String to_date) {  
    	List<ChartDto>  list = new ArrayList<ChartDto>();
    	chartService = (ChartService)ctx.getBean("chartService");
    	chartService.setCtx(ctx);    	
        list = chartService.queryPurchaseByDate(theme, from_date, to_date);
    	
    	return list;
    }
    
    public JSONArray getData(String pattern, String from_date, String to_date) {  
    	int i_fy;
    	int i_ty;
    	int i_fm;
    	int i_tm;
    	String user_id = "";
    	String tmp_user_id = "";
    	JSONArray jsonArr = new JSONArray();  
		log.error("getData");
        System.out.println("-------->"); 
    	    
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
        	List<ChartDto>  list = new ArrayList<ChartDto>();

        	// get Saler's data 
        	if (pattern=="1"){
        		list = getSaleData("", from_date, to_date);
        	}
        	// get Buyer's data 
        	else if (pattern=="2"){
        		list = getBuyData("", from_date, to_date);
        	}
        	// get Delivery's data 
        	else if (pattern=="3"){
        		list = getDeliveryData("", from_date, to_date);
        	}
        	// get Account's data 
        	else if (pattern=="4"){
        		list = getAccountData("", from_date, to_date);
        	}
        	
            Map<String, String> item_map = null;
            Map<String, String> temp_item_map = null;
            Map<String, String> user_item_map = null;

            if (list==null || list.size()<= 0)
	            System.out.println("list.size error");	        	
	        if (list.size() > 0) {
	            System.out.println("list.size:" + list.size());
		        for (int z = 0; z < list.size(); z++) {  
		            System.out.println("Z:" + z);
		        	ChartDto chd = list.get(z);
		        	user_id = chd.getHandler();	        	
		            System.out.println("user_id_loop:" + user_id);
		        	if (user_id != tmp_user_id){
		        		// part of every user_id 
			            System.out.println("This user_id is:" + user_id);
			            if (temp_item_map != null){
			            	// put pre_user's data into array
				        	item_map= sort(user_item_map);
				        	jsonArr = setJsonData(jsonArr, tmp_user_id,  item_map );
			            }
			            // initial the user's data map
			            temp_item_map = getInitDataMap(i_fy, i_ty, i_fm, i_tm);
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
            System.out.println("Saler jsonArr length:" + jsonArr.length());
    	    System.out.println("JO: " + jsonArr);  
    	    setM_jsonArr(jsonArr);            
		}
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
        return(jsonArr);  
    }	
      
    public Date add(Date day,int dist) {  
        Calendar calendar = new GregorianCalendar();  
        calendar.setTime(day);  
        calendar.add(calendar.DATE, dist);  
        day = calendar.getTime();  
        return day;  
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

        String act=request.getParameter("action");  
        System.out.println("request.act:" + act);
        String from_date=request.getParameter("from_date");  
        System.out.println("request.from_date:" + from_date);
        String to_date=request.getParameter("to_date");  
        System.out.println("request.to_date:" + to_date);
        
        ServletContext servletContext = request.getSession().getServletContext();
    	ctx= WebApplicationContextUtils.getWebApplicationContext(servletContext);
                  
        JSONArray jsonArr = new JSONArray();
        // Get Saler's individual data
        if (act.equals("getSaleData")){
            jsonArr = getData("1",from_date, to_date);        	
        } 
        // Get Buyer's individual data
        else if (act.equals("getBuyData")){
            jsonArr = getData("2", from_date, to_date);        	
        }
        // Get Delivery's individual data
        else if (act.equals("getDeliveryData")){
            jsonArr = getData("3", from_date, to_date);        	
        }
        // Get Accounting's individual data
        else if (act.equals("getAccountData")){
            jsonArr = getData("4", from_date, to_date);        	
        }
        
        out.println(jsonArr.toString());  
        out.flush();  
        out.close();  
    }        
        
    public void init() throws ServletException {  
        // Put your code here  
    }  
 
}
