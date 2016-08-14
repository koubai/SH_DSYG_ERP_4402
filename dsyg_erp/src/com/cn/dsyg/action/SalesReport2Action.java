package com.cn.dsyg.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.FileUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.SalesReport2Dto;
import com.cn.dsyg.service.SalesReport2Service;
import com.opensymphony.xwork2.ActionContext;

/**
 * SALES 报告Action
 * @author 
 * @time 
 * @version 1.0
 */
public class SalesReport2Action extends BaseAction {

	private static final long serialVersionUID = -1471385266392741604L;

	private static final Logger log = LogManager.getLogger(SalesReport2Action.class);
	
	private SalesReport2Service salesreport2Service;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	//一页显示数据条数
	private Integer intPageSize;
	
	/**
	 * SALES报告列表
	 */
	private List<SalesReport2Dto> listSalesReport;
	
	/**
	 * SALES报告编号（起）
	 */
	private String strSalesReportNoLow;
	
	/**
	 * SALES报告编号（终）
	 */
	private String strSalesReportNoHigh;
	
	/**
	 * SALES报告名称
	 */
	private String strSalesReportName;
	
	/**
	 * SALES报告客户名
	 */
	private String strCustomerName;

	/**
	 * 新增SALES报告对象
	 */
	private SalesReport2Dto addSalesReport2Dto;
	
	/**
	 * 修改的SALES报告编号
	 */
	private String updateSalesReportNo;
	
	/**
	 * 修改SALES报告对象
	 */
	private SalesReport2Dto updateSalesReport2Dto;
	
	/**
	 * 删除的SALES报告编号
	 */
	private String delSalesReportNo;
	
	/**
	 * ajax查询条件-SALES报告编号
	 */
	private String querySalesReportNo;
	
	//SALES报告查询页面（共通）
	/**
	 * SALES报告信息页码
	 */
	private int startIndexSalesReport;
	
	/**
	 * SALES报告信息翻页
	 */
	private Page pageSalesReport;
	
	private List<SalesReport2Dto> salesreportList;
	
	private String salesreportNoLow;
	
	private String salesreportNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;
	
	//新增
	private File addReportFile01;
	private File addReportFile02;
	private File addReportFile03;
	//对应的文件名
	private String file01Name;
	private String file02Name;
	private String file03Name;
	
	//修改
	private File updReportFile01;
	private File updReportFile02;
	private File updReportFile03;

	/**
	 * 显示SALES报告页面
	 * @return
	 */
	public String showSalesReport2Action() {
		try {
			this.clearMessages();
			System.out.println("showSalesReport2Action start");
			strSalesReportNoLow = "";
			strSalesReportNoHigh = "";
			strSalesReportName = "";
			strCustomerName = "";
			addSalesReport2Dto = new SalesReport2Dto();
			updateSalesReport2Dto = new SalesReport2Dto();
			updateSalesReportNo = "";
			delSalesReportNo = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listSalesReport = new ArrayList<SalesReport2Dto>();
			
			querySalesReport2();
			System.out.println("showSalesReport2Action end");
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询SALES报告列表
	 * @return
	 */
	public String querySalesReport2List() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			querySalesReport2();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnSalesReport2Page() {
		try {
			this.clearMessages();
			querySalesReport2();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有SALES报告列表
	 */
	@SuppressWarnings("unchecked")
	private void querySalesReport2() {
		listSalesReport = new ArrayList<SalesReport2Dto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有SALES报告
		this.page.setStartIndex(startIndex);
		System.out.println("querySalesReport2 start");
		page = salesreport2Service.querySalesReport2ByPage(page, strSalesReportNoLow, strSalesReportNoHigh, strSalesReportName, strCustomerName);
		listSalesReport = (List<SalesReport2Dto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加SALES报告页面
	 * @return
	 */
	public String showAddSalesReport2Action() {
		try {
			this.clearMessages();
			addSalesReport2Dto = new SalesReport2Dto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加SALES报告
	 * @return
	 */
	public String addSalesReport2Action() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addSalesReport2Dto)) {
				return "checkerror";
			}
			log.info("addSalesReport2Dto.getSalesReportno()=" + addSalesReport2Dto.getSalesreportno());
			log.info("addSalesReport2Dto.getSalesReportname()=" + addSalesReport2Dto.getSalesreportname());
			//校验SALES报告代码是否存在
			SalesReport2Dto salesreport2 = salesreport2Service.queryAllSalesReport2ByID(addSalesReport2Dto.getSalesreportno()+"");
			if(salesreport2 != null) {
				this.addActionMessage("SALES报告已经存在！");
				return "checkerror";
			}
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			if(addReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(addReportFile01, pdf_path, file01Name);
				addSalesReport2Dto.setReportpath01(newfile01);
			}
			if(addReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(addReportFile02, pdf_path, file02Name);
				addSalesReport2Dto.setReportpath02(newfile02);
			}
			if(addReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(addReportFile03, pdf_path, file03Name);
				addSalesReport2Dto.setReportpath03(newfile03);
			}
			
			//保存数据
			addSalesReport2Dto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addSalesReport2Dto.setCreateuid(username);
			String salesreportno = salesreport2Service.insertSalesReport2(addSalesReport2Dto);
			this.addActionMessage("添加SALES报告成功！SALES报告编号为：" + salesreportno);
			addSalesReport2Dto = new SalesReport2Dto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加SALES报告失败！");
			log.error("addSalesReport2Action error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改SALES报告页面
	 * @return
	 */
	public String showUpdSalesReport2Action() {
		try {
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
			this.clearMessages();
			System.out.println("salesreport2No is: "+updateSalesReportNo);
			updateSalesReport2Dto = salesreport2Service.querySalesReport2ByID(updateSalesReportNo);
			if(updateSalesReport2Dto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询SALES报告异常！");
			log.error("showUpdSalesReport2Action error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改SALES报告
	 * @return
	 */
	public String updSalesReport2Action() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateSalesReport2Dto)) {
				return "checkerror";
			}
			System.out.println("salesreport2No is: "+updateSalesReport2Dto.getSalesreportno());
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			String oldfile1 = "";
			String oldfile2 = "";
			String oldfile3 = "";
			SalesReport2Dto oldReport = salesreport2Service.querySalesReport2ByID(updateSalesReportNo);
			
			if(updReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(updReportFile01, pdf_path, file01Name);
				updateSalesReport2Dto.setReportpath01(newfile01);
				oldfile1 = oldReport.getReportpath01();
			}
			if(updReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(updReportFile02, pdf_path, file02Name);
				updateSalesReport2Dto.setReportpath02(newfile02);
				oldfile2 = oldReport.getReportpath02();
			}
			if(updReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(updReportFile03, pdf_path, file03Name);
				updateSalesReport2Dto.setReportpath03(newfile03);
				oldfile3 = oldReport.getReportpath03();
			}
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateSalesReport2Dto.setUpdateuid(username);
			salesreport2Service.updateSalesReport2(updateSalesReport2Dto);
			this.addActionMessage("修改SALES报告成功！");
			//清空数据
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改SALES报告失败！");
			log.error("updSalesReportAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除SALES报告
	 * @return
	 */
	public String delSalesReport2Action() {
		try {
			this.clearMessages();
			System.out.println("delSalesReport2Action");
			if(StringUtil.isBlank(delSalesReportNo)) {
				this.addActionMessage("SALES报告代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			salesreport2Service.deleteSalesReport2(delSalesReportNo, username);
			this.addActionMessage("删除SALES报告成功！");
			delSalesReportNo = "";
			//刷新页面
			startIndex = 0;
			querySalesReport2();
		} catch(Exception e) {
			log.error("delSalesReport2Action error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param salesreport
	 * @return
	 */
	private boolean checkData(SalesReport2Dto salesreport2) {
		if(salesreport2 == null) {
			this.addActionMessage("SALES报告名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(salesreport.getSalesReportno()+"")) {
//			this.addActionMessage("SALES报告代码不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(salesreport2.getSalesreportname())) {
			this.addActionMessage("SALES报告名称不能为空！");
			return false;
		}
		if(salesreport2.getSalesreportname().length() > 128) {
			this.addActionMessage("SALES报告名称不能超过128个字符！");
			return false;
		}
		if(StringUtil.isBlank(salesreport2.getCustomername())) {
			this.addActionMessage("客户名称不能为空！");
			return false;
		}
		if(salesreport2.getCustomername().length() > 64) {
			this.addActionMessage("客户名称不能超过64个字符！");
			return false;
		}
		if(!DateUtil.isDate(salesreport2.getRegisterdate().toString())) {
			this.addActionMessage("登记日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(salesreport2.getNote()) && salesreport2.getNote().length() > 500) {
			this.addActionMessage("内容介绍不能超过500个字符！");
			return false;
		}
		return true;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<SalesReport2Dto> getListSalesReport() {
		return listSalesReport;
	}

	public void setListSalesReport(List<SalesReport2Dto> listSalesReport) {
		this.listSalesReport = listSalesReport;
	}

	public String getStrSalesReportNoLow() {
		return strSalesReportNoLow;
	}

	public void setStrSalesReportNoLow(String strSalesReportNoLow) {
		this.strSalesReportNoLow = strSalesReportNoLow;
	}

	public String getStrSalesReportNoHigh() {
		return strSalesReportNoHigh;
	}

	public void setStrSalesReportNoHigh(String strSalesReportNoHigh) {
		this.strSalesReportNoHigh = strSalesReportNoHigh;
	}

	public SalesReport2Dto getAddSalesReport2Dto() {
		return addSalesReport2Dto;
	}

	public void setAddSalesReport2Dto(SalesReport2Dto addSalesReport2Dto) {
		this.addSalesReport2Dto = addSalesReport2Dto;
	}

	public SalesReport2Dto getUpdateSalesReport2Dto() {
		return updateSalesReport2Dto;
	}

	public void setUpdateSalesReport2Dto(SalesReport2Dto updateSalesReport2Dto) {
		this.updateSalesReport2Dto = updateSalesReport2Dto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateSalesReportNo() {
		return updateSalesReportNo;
	}

	public void setUpdateSalesReportNo(String updateSalesReportNo) {
		this.updateSalesReportNo = updateSalesReportNo;
	}

	public String getDelSalesReportNo() {
		return delSalesReportNo;
	}

	public void setDelSalesReportNo(String delSalesReportNo) {
		this.delSalesReportNo = delSalesReportNo;
	}

	public String getQuerySalesReportNo() {
		return querySalesReportNo;
	}

	public void setQuerySalesReportNo(String querySalesReportNo) {
		this.querySalesReportNo = querySalesReportNo;
	}

	public int getStartIndexSalesReport() {
		return startIndexSalesReport;
	}

	public void setStartIndexSalesReport(int startIndexSalesReport) {
		this.startIndexSalesReport = startIndexSalesReport;
	}

	public Page getPageSalesReport() {
		return pageSalesReport;
	}

	public void setPageSalesReport(Page pageSalesReport) {
		this.pageSalesReport = pageSalesReport;
	}

	public List<SalesReport2Dto> getSalesReportList() {
		return salesreportList;
	}

	public void setSalesReportList(List<SalesReport2Dto> salesreportList) {
		this.salesreportList = salesreportList;
	}

	public String getSalesReportNoLow() {
		return salesreportNoLow;
	}

	public void setSalesReportNoLow(String salesreportNoLow) {
		this.salesreportNoLow = salesreportNoLow;
	}

	public String getSalesReportNoHigh() {
		return salesreportNoHigh;
	}

	public void setSalesReportNoHigh(String salesreportNoHigh) {
		this.salesreportNoHigh = salesreportNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrSalesReportName() {
		return strSalesReportName;
	}

	public void setStrSalesReportName(String strSalesReportName) {
		this.strSalesReportName = strSalesReportName;
	}

	public SalesReport2Service getSalesReport2Service() {
		return salesreport2Service;
	}

	public void setSalesReport2Service(SalesReport2Service salesreport2Service) {
		this.salesreport2Service = salesreport2Service;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public File getAddReportFile01() {
		return addReportFile01;
	}

	public void setAddReportFile01(File addReportFile01) {
		this.addReportFile01 = addReportFile01;
	}

	public File getAddReportFile02() {
		return addReportFile02;
	}

	public void setAddReportFile02(File addReportFile02) {
		this.addReportFile02 = addReportFile02;
	}

	public File getAddReportFile03() {
		return addReportFile03;
	}

	public void setAddReportFile03(File addReportFile03) {
		this.addReportFile03 = addReportFile03;
	}

	public String getFile01Name() {
		return file01Name;
	}

	public void setFile01Name(String file01Name) {
		this.file01Name = file01Name;
	}

	public String getFile02Name() {
		return file02Name;
	}

	public void setFile02Name(String file02Name) {
		this.file02Name = file02Name;
	}

	public String getFile03Name() {
		return file03Name;
	}

	public void setFile03Name(String file03Name) {
		this.file03Name = file03Name;
	}

	public File getUpdReportFile01() {
		return updReportFile01;
	}

	public void setUpdReportFile01(File updReportFile01) {
		this.updReportFile01 = updReportFile01;
	}

	public File getUpdReportFile02() {
		return updReportFile02;
	}

	public void setUpdReportFile02(File updReportFile02) {
		this.updReportFile02 = updReportFile02;
	}

	public File getUpdReportFile03() {
		return updReportFile03;
	}

	public void setUpdReportFile03(File updReportFile03) {
		this.updReportFile03 = updReportFile03;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

}
