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
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cn.dsyg.dto.ChartDto;
import com.cn.dsyg.service.ChartService;

public class ChartServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7285713183683200542L;
	private static final Logger log = LogManager.getLogger(ChartServlet.class);

	private ChartService chartService;
	public ApplicationContext ctx;
	
	public ChartService getChartService() {
		return chartService;
	}
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
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
        String dur_type=request.getParameter("dur_type");  
        System.out.println("request.dur_type:" + dur_type);
        String handerList=request.getParameter("handerList");  
        System.out.println("request.handerList:" + handerList);
        String belongto=(String)request.getSession().getAttribute("belongto");  
        System.out.println("request.belongto:" + belongto);
        
        ServletContext servletContext = request.getSession().getServletContext();
    	ctx= WebApplicationContextUtils.getWebApplicationContext(servletContext);
                  
        JSONArray jsonArr = new JSONArray();
        
        chartService = (ChartService)ctx.getBean("chartService");
    	chartService.setCtx(ctx);    	
        // Get Saler's individual data
        if (act.equals("getPurchaseData")){
            jsonArr = chartService.getData(belongto, "1",from_date, to_date, dur_type, handerList);        	
        } 
        // Get Buyer's individual data
        else if (act.equals("getSaleData")){
            jsonArr = chartService.getData(belongto, "2", from_date, to_date, dur_type, handerList);        	
        }
        // Get Delivery's individual data
        else if (act.equals("getDeliveryData")){
            jsonArr = chartService.getData(belongto, "3", from_date, to_date, dur_type, handerList);        	
        }
        // Get Accounting's individual data
        else if (act.equals("getAccountData")){
            jsonArr = chartService.getData(belongto, "4", from_date, to_date, dur_type, handerList);        	
        }
        // Get Saler's detail individual data
        else if (act.equals("getSaleDetailData")){
            jsonArr = chartService.getData(belongto, "5", from_date, to_date, dur_type, handerList);        	
        }
        // Get Saler's detail individual data
        else if (act.equals("getSupplierData")){
            jsonArr = chartService.getData(belongto, "6", from_date, to_date, dur_type, handerList);        	
        }
        // Get Saler's detail individual data
        else if (act.equals("getCustomerData")){
            jsonArr = chartService.getData(belongto, "7", from_date, to_date, dur_type, handerList);        	
        }
        // Get Sale summary data
        else if (act.equals("getSaleTotalData")){
            jsonArr = chartService.getData(belongto, "8", from_date, to_date, dur_type, handerList);        	
        }
        // Get Product profit individual data
        else if (act.equals("getAccountSubDataA")){
            jsonArr = chartService.getData(belongto, "9", from_date, to_date, dur_type, handerList);        	
        }
        else if (act.equals("getAccountSubDataB")){
            jsonArr = chartService.getData(belongto, "10", from_date, to_date, dur_type, handerList);        	
        }
        else if (act.equals("getWarehouseCostData")){
            jsonArr = chartService.getWarehouseCostData();        	
        }
        else if (act.equals("getUnOutWarehouseCostData")){
            jsonArr = chartService.getUnOutWarehouseCostData();        	
        }
        else if (act.equals("getUnInWarehouseCostData")){
            jsonArr = chartService.getUnInWarehouseCostData();        	
        }
        
        out.println(jsonArr.toString());  
        out.flush();  
        out.close();  
    }        
        
    public void init() throws ServletException {  
        // Put your code here  
    }  
 
}
