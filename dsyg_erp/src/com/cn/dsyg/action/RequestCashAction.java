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
import com.cn.dsyg.dto.SalesReportDto;
import com.cn.dsyg.service.SalesReportService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 销售报告Action
 * @author 
 * @time 
 * @version 1.0
 */
public class RequestCashAction extends BaseAction {

	private static final long serialVersionUID = 7368309388514409960L;

	private static final Logger log = LogManager.getLogger(RequestCashAction.class);
	
	private SalesReportService salesreportService;
	
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
	 * 销售报告列表
	 */
	private List<SalesReportDto> listSalesReport;
	
	/**
	 * 销售报告编号（起）
	 */
	private String strSalesReportNoLow;
	
	/**
	 * 销售报告编号（终）
	 */
	private String strSalesReportNoHigh;
	
	/**
	 * 销售报告名称
	 */
	private String strSalesReportName;
	
	/**
	 * 新增销售报告对象
	 */
	private SalesReportDto addSalesReportDto;
	
	/**
	 * 修改的销售报告编号
	 */
	private String updateSalesReportNo;
	
	/**
	 * 修改销售报告对象
	 */
	private SalesReportDto updateSalesReportDto;
	
	/**
	 * 删除的销售报告编号
	 */
	private String delSalesReportNo;
	
	/**
	 * ajax查询条件-销售报告编号
	 */
	private String querySalesReportNo;
	
	//销售报告查询页面（共通）
	/**
	 * 销售报告信息页码
	 */
	private int startIndexSalesReport;
	
	/**
	 * 销售报告信息翻页
	 */
	private Page pageSalesReport;
	
	private List<SalesReportDto> salesreportList;
	
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
	 * 显示销售报告页面
	 * @return
	 */
	public String showSalesReportAction() {
		try {
			this.clearMessages();
			strSalesReportNoLow = "";
			strSalesReportNoHigh = "";
			strSalesReportName = "";
			addSalesReportDto = new SalesReportDto();
			updateSalesReportDto = new SalesReportDto();
			updateSalesReportNo = "";
			delSalesReportNo = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listSalesReport = new ArrayList<SalesReportDto>();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询销售报告列表
	 * @return
	 */
	public String querySalesReportList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			querySalesReport();
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
	public String turnSalesReportPage() {
		try {
			this.clearMessages();
			querySalesReport();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有销售报告列表
	 */
	@SuppressWarnings("unchecked")
	private void querySalesReport() {
		listSalesReport = new ArrayList<SalesReportDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有销售报告
		this.page.setStartIndex(startIndex);
		page = salesreportService.querySalesReportByPage(page, strSalesReportNoLow, strSalesReportNoHigh, strSalesReportName);
		listSalesReport = (List<SalesReportDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加销售报告页面
	 * @return
	 */
	public String showAddSalesReportAction() {
		try {
			this.clearMessages();
			addSalesReportDto = new SalesReportDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加销售报告
	 * @return
	 */
	public String addSalesReportAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addSalesReportDto)) {
				return "checkerror";
			}
			log.info("addSalesReportDto.getSalesReportno()=" + addSalesReportDto.getSalesreportno());
			log.info("addSalesReportDto.getSalesReportname()=" + addSalesReportDto.getSalesreportname());
			//校验销售报告代码是否存在
			SalesReportDto salesreport = salesreportService.queryAllSalesReportByID(addSalesReportDto.getSalesreportno()+"");
			if(salesreport != null) {
				this.addActionMessage("销售报告已经存在！");
				return "checkerror";
			}
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			if(addReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(addReportFile01, pdf_path, file01Name);
				addSalesReportDto.setReportpath01(newfile01);
			}
			if(addReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(addReportFile02, pdf_path, file02Name);
				addSalesReportDto.setReportpath02(newfile02);
			}
			if(addReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(addReportFile03, pdf_path, file03Name);
				addSalesReportDto.setReportpath03(newfile03);
			}
			
			//保存数据
			addSalesReportDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addSalesReportDto.setCreateuid(username);
			String salesreportno = salesreportService.insertSalesReport(addSalesReportDto);
			this.addActionMessage("添加销售报告成功！销售报告编号为：" + salesreportno);
			addSalesReportDto = new SalesReportDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加销售报告失败！");
			log.error("addSalesReportAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改销售报告页面
	 * @return
	 */
	public String showUpdSalesReportAction() {
		try {
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
			this.clearMessages();
			System.out.println("salesreportNo is: "+updateSalesReportNo);
			updateSalesReportDto = salesreportService.querySalesReportByID(updateSalesReportNo);
			if(updateSalesReportDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询销售报告异常！");
			log.error("showUpdSalesReportAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改销售报告
	 * @return
	 */
	public String updSalesReportAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateSalesReportDto)) {
				return "checkerror";
			}
			System.out.println("salesreportNo is: "+updateSalesReportDto.getSalesreportno());
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			String oldfile1 = "";
			String oldfile2 = "";
			String oldfile3 = "";
			SalesReportDto oldReport = salesreportService.querySalesReportByID(updateSalesReportNo);
			
			if(updReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(updReportFile01, pdf_path, file01Name);
				updateSalesReportDto.setReportpath01(newfile01);
				oldfile1 = oldReport.getReportpath01();
			}
			if(updReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(updReportFile02, pdf_path, file02Name);
				updateSalesReportDto.setReportpath02(newfile02);
				oldfile2 = oldReport.getReportpath02();
			}
			if(updReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(updReportFile03, pdf_path, file03Name);
				updateSalesReportDto.setReportpath03(newfile03);
				oldfile3 = oldReport.getReportpath03();
			}
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateSalesReportDto.setUpdateuid(username);
			salesreportService.updateSalesReport(updateSalesReportDto);
			this.addActionMessage("修改销售报告成功！");
			//清空数据
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改销售报告失败！");
			log.error("updSalesReportAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除销售报告
	 * @return
	 */
	public String delSalesReportAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delSalesReportNo)) {
				this.addActionMessage("销售报告代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			salesreportService.deleteSalesReport(delSalesReportNo, username);
			this.addActionMessage("删除销售报告成功！");
			delSalesReportNo = "";
			//刷新页面
			startIndex = 0;
			querySalesReport();
		} catch(Exception e) {
			log.error("delSalesReportAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param salesreport
	 * @return
	 */
	private boolean checkData(SalesReportDto salesreport) {
		if(salesreport == null) {
			this.addActionMessage("销售报告名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(salesreport.getSalesReportno()+"")) {
//			this.addActionMessage("销售报告代码不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(salesreport.getSalesreportname())) {
			this.addActionMessage("销售报告名称不能为空！");
			return false;
		}
		if(salesreport.getSalesreportname().length() > 40) {
			this.addActionMessage("销售报告名称不能超过64个字符！");
			return false;
		}
		if(!DateUtil.isDate(salesreport.getRegisterdate().toString())) {
			this.addActionMessage("登记日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(salesreport.getNote()) && salesreport.getNote().length() > 500) {
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

	public List<SalesReportDto> getListSalesReport() {
		return listSalesReport;
	}

	public void setListSalesReport(List<SalesReportDto> listSalesReport) {
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

	public SalesReportDto getAddSalesReportDto() {
		return addSalesReportDto;
	}

	public void setAddSalesReportDto(SalesReportDto addSalesReportDto) {
		this.addSalesReportDto = addSalesReportDto;
	}

	public SalesReportDto getUpdateSalesReportDto() {
		return updateSalesReportDto;
	}

	public void setUpdateSalesReportDto(SalesReportDto updateSalesReportDto) {
		this.updateSalesReportDto = updateSalesReportDto;
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

	public List<SalesReportDto> getSalesReportList() {
		return salesreportList;
	}

	public void setSalesReportList(List<SalesReportDto> salesreportList) {
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

	public SalesReportService getSalesReportService() {
		return salesreportService;
	}

	public void setSalesReportService(SalesReportService salesreportService) {
		this.salesreportService = salesreportService;
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
}