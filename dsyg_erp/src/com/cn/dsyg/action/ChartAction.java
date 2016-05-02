package com.cn.dsyg.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.dsyg.dto.CustomerDto;
import com.cn.dsyg.dto.DeliveryDto;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.dto.UndeliProductDto;
import com.cn.dsyg.dto.UserDto;
import com.cn.dsyg.service.ChartService;
import com.cn.dsyg.service.CustomerService;
import com.cn.dsyg.service.DeliveryService;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.ProductService;
import com.cn.dsyg.service.SampleService;
import com.cn.dsyg.service.SupplierService;
import com.cn.dsyg.service.UndeliProductService;
import com.cn.dsyg.service.UserService;


public class ChartAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4283678393190804679L;

	private static final Logger log = LogManager.getLogger(ChartAction.class);

	private static final String SUCCESS = null;

	private static final String ERROR = null;

	private String str;  
	private String series;  
	private String series_X;  
	
	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	//页面显示的用户列表
	private List<UserDto> userList;

	private List<SupplierDto> supplierList;
	private List<CustomerDto> customerList;
	private List<DeliveryDto> deliveryList;
	private List<UndeliProductDto> undeliproductList;

	private String strKeyword;	
	private String strFieldno;
	private String strUserIdFrom;
	private String strUserIdTo;
	private UserService userService;
	private SupplierService supplierService;
	private CustomerService customerService;
	private DeliveryService deliveryService;
	private UndeliProductService undeliproductService;
	private String fieldno;
	private String iotype;
	private String fromDate;	
	private String toDate;
	private Dict01Service dict01Service;

	//采购主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	
	public Dict01Service getDict01Service() {
		return dict01Service;
	}
	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}
	public String getFieldno() {
		return fieldno;
	}
	public List<Dict01Dto> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Dict01Dto> goodsList) {
		this.goodsList = goodsList;
	}
	public List<Dict01Dto> getColorList() {
		return colorList;
	}
	public void setColorList(List<Dict01Dto> colorList) {
		this.colorList = colorList;
	}
	public List<Dict01Dto> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<Dict01Dto> unitList) {
		this.unitList = unitList;
	}
	public List<Dict01Dto> getMakeareaList() {
		return makeareaList;
	}
	public void setMakeareaList(List<Dict01Dto> makeareaList) {
		this.makeareaList = makeareaList;
	}
	public void setFieldno(String fieldno) {
		this.fieldno = fieldno;
	}

	public String getIotype() {
		return iotype;
	}
	public void setIotype(String iotype) {
		this.iotype = iotype;
	}

	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public UndeliProductService getUndeliproductService() {
		return undeliproductService;
	}
	public void setUndeliproductService(UndeliProductService undeliproductService) {
		this.undeliproductService = undeliproductService;
	}
	public List<SupplierDto> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<SupplierDto> supplierList) {
		this.supplierList = supplierList;
	}
	public List<CustomerDto> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<CustomerDto> customerList) {
		this.customerList = customerList;
	}
	public List<DeliveryDto> getDeliveryList() {
		return deliveryList;
	}
	public void setDeliveryList(List<DeliveryDto> deliveryList) {
		this.deliveryList = deliveryList;
	}
	public SupplierService getSupplierService() {
		return supplierService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public DeliveryService getDeliveryService() {
		return deliveryService;
	}
	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getStrFieldno() {
		return strFieldno;
	}
	public void setStrFieldno(String strFieldno) {
		this.strFieldno = strFieldno;
	}
	public String getStrUserIdFrom() {
		return strUserIdFrom;
	}
	public void setStrUserIdFrom(String strUserIdFrom) {
		this.strUserIdFrom = strUserIdFrom;
	}
	public String getStrUserIdTo() {
		return strUserIdTo;
	}
	public void setStrUserIdTo(String strUserIdTo) {
		this.strUserIdTo = strUserIdTo;
	}

	public String getStrKeyword() {
		return strKeyword;
	}
	public void setStrKeyword(String strKeyword) {
		this.strKeyword = strKeyword;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getIntPageSize() {
		return intPageSize;
	}
	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}
	public List<UserDto> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}
	
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
    
    public String showSaleTotalInfoMainChartAction(){  
    	try{
            System.out.println("showSaleTotalInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showSaleTotalInfoMainChartAction error:" + e);
			return ERROR;
		}
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

    public String showSaleDetailInfoMainChartAction(){  
    	try{
            System.out.println("showSaleDetailInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showSaleDetailInfoMainChartAction error:" + e);
			return ERROR;
		}
	}
    
    public String showPurchaseInfoMainChartAction(){  
    	try{
            System.out.println("showPurchaseInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showPurchaseInfoMainChartAction error:" + e);
			return ERROR;
		}
	}

    public String showDeliveryInfoMainChartAction(){  
    	try{
            System.out.println("showDeliveryInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showDeliveryInfoMainChartAction error:" + e);
			return ERROR;
		}
	}

    public String showAccountInfoMainChartAction(){  
    	try{
            System.out.println("showAccountInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showAccountInfoMainChartAction error:" + e);
			return ERROR;
		}
	}

    public String showProfitInfoMainChartAction(){  
    	try{
            System.out.println("showProfitInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showProfitInfoMainChartAction error:" + e);
			return ERROR;
		}
	}
    
    public String showSupplierInfoMainChartAction(){  
    	try{
            System.out.println("showSupplierInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showSupplierInfoMainChartAction error:" + e);
			return ERROR;
		}
	}

    public String showCustomerInfoMainChartAction(){  
    	try{
            System.out.println("showCustomerInfoMainChartAction success"); 
	        return SUCCESS;  
		} catch(Exception e) {
			log.error("showCustomerInfoMainChartAction error:" + e);
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
        return SUCCESS;  
    } 
    // Get Buyer's individual data
    public String getBuyDataAction() {  
        return SUCCESS;  
    } 
    // Get Delivery's individual data
    public String getDeliveryDataAction() {  
        return SUCCESS;  
    } 
    // Get Accounting's individual data
    public String getAccountDataAction() {  
        return SUCCESS;  
    }
    // Get Product profit individual data
    public String getAccountSubDataAction() {  
        return SUCCESS;  
    }
          
	//用户选择页面========================
	/**
	 * 显示用户选择页面
	 * @return
	 */
	public String showUserSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryUserData();
		} catch(Exception e) {
			log.error("showUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户（选择页面）
	 * @return
	 */
	public String queryUserSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryUserData();
		} catch(Exception e) {
			log.error("queryUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页用户（选择页面）
	 * @return
	 */
	public String turnUserSelectPage() {
		try {
			System.out.println("turnUserSelectPage");
			this.clearMessages();
			queryUserData();
		} catch(Exception e) {
			log.error("turnUserSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryUserData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		System.out.println("strUserIdFrom:" +strUserIdFrom);
		System.out.println("strUserIdTo:" +strUserIdTo);
		page = userService.queryUserByPage(strFieldno, strKeyword, strUserIdFrom, strUserIdTo, "" + Constants.STATUS_NORMAL, page);
		userList = (List<UserDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	//供应商选择页面========================
	/**
	 * 显示供应商选择页面
	 * @return
	 */
	public String showSupplierSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			querySupplierData();
		} catch(Exception e) {
			log.error("showSupplierSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询供应商（选择页面）
	 * @return
	 */
	public String querySupplierSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			querySupplierData();
		} catch(Exception e) {
			log.error("querySupplierSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页供应商（选择页面）
	 * @return
	 */
	public String turnSupplierSelectPage() {
		try {
			System.out.println("turnUserSelectPage");
			this.clearMessages();
			querySupplierData();
		} catch(Exception e) {
			log.error("turnSupplierSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void querySupplierData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		System.out.println("strUserIdFrom:" +strUserIdFrom);
		System.out.println("strUserIdTo:" +strUserIdTo);
		page = supplierService.querySupplierByPage(page,  strUserIdFrom, strUserIdTo, "");
		supplierList = (List<SupplierDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

	//用户选择页面========================
	/**
	 * 显示用户选择页面
	 * @return
	 */
	public String showCustomerSelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryCustomerData();
		} catch(Exception e) {
			log.error("showCustomerSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户（选择页面）
	 * @return
	 */
	public String queryCustomerSelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryCustomerData();
		} catch(Exception e) {
			log.error("queryCustomerSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页用户（选择页面）
	 * @return
	 */
	public String turnCustomerSelectPage() {
		try {
			System.out.println("turnCustomerSelectPage");
			this.clearMessages();
			queryCustomerData();
		} catch(Exception e) {
			log.error("turnCustomerSelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryCustomerData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		System.out.println("strUserIdFrom:" +strUserIdFrom);
		System.out.println("strUserIdTo:" +strUserIdTo);
		page = customerService.queryEtbCustomerByPage(page,  strUserIdFrom, strUserIdTo, "");
		customerList = (List<CustomerDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}

////////////////////////////////////////////////
	//快递选择页面========================
	/**
	 * 显示快递选择页面
	 * @return
	 */
	public String showDeliverySelectPage() {
		try {
			this.clearMessages();
			//这里产品选择页面，不需要关键字检索
			strKeyword = "";
			startIndex = 0;
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			queryDeliveryData();
		} catch(Exception e) {
			log.error("showDeliverySelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询快递（选择页面）
	 * @return
	 */
	public String queryDeliverySelectPage() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryDeliveryData();
		} catch(Exception e) {
			log.error("queryDeliverySelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页快递（选择页面）
	 * @return
	 */
	public String turnDeliverySelectPage() {
		try {
			System.out.println("turnDeliverySelectPage");
			this.clearMessages();
			queryDeliveryData();
		} catch(Exception e) {
			log.error("turnDeliverySelectPage error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryDeliveryData() {
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有用户
		this.page.setStartIndex(startIndex);
		System.out.println("strUserIdFrom:" +strUserIdFrom);
		System.out.println("strUserIdTo:" +strUserIdTo);
		page = deliveryService.queryEtbDeliveryByPage(page,  strUserIdFrom, strUserIdTo, "");
		deliveryList = (List<DeliveryDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
	}


	public List<UndeliProductDto> getUndeliproductList() {
		return undeliproductList;
	}
	public void setUndeliproductList(List<UndeliProductDto> undeliproductList) {
		this.undeliproductList = undeliproductList;
	}

	/**
	 * 数据查询
	 */
	public String queryUnDeliProductDataAction(){
    	try{
    		initDictList();
			undeliproductList = (List<UndeliProductDto>)undeliproductService.queryUnDeliProductByType(fromDate, toDate, iotype, fieldno);
	        return SUCCESS;  
		} catch(Exception e) {
			return ERROR;
		}	
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//采购主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		System.out.println("goodsList.size" + goodsList.size());
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		System.out.println("makeareaList" + makeareaList.size());
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		System.out.println("colorList" + colorList.size());
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		System.out.println("unitList" + unitList.size());
	}
}
