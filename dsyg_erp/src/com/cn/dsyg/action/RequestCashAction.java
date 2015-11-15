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
import com.cn.dsyg.dto.RequestCashDto;
import com.cn.dsyg.service.RequestCashService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 请款报告Action
 * @author 
 * @time 
 * @version 1.0
 */
public class RequestCashAction extends BaseAction {


	private static final long serialVersionUID = -8380399168765184296L;

	private static final Logger log = LogManager.getLogger(RequestCashAction.class);
	
	private RequestCashService requestcashService;
	
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
	 * 请款报告列表
	 */
	private List<RequestCashDto> listRequestCash;
	
	/**
	 * 请款报告编号（起）
	 */
	private String strRequestCashNoLow;
	
	/**
	 * 请款报告编号（终）
	 */
	private String strRequestCashNoHigh;
	
	/**
	 * 请款报告名称
	 */
	private String strRequestCashName;
	
	/**
	 * 新增请款报告对象
	 */
	private RequestCashDto addRequestCashDto;
	
	/**
	 * 修改的请款报告编号
	 */
	private String updateRequestCashNo;
	
	/**
	 * 修改请款报告对象
	 */
	private RequestCashDto updateRequestCashDto;
	
	/**
	 * 删除的请款报告编号
	 */
	private String delRequestCashNo;
	
	/**
	 * ajax查询条件-请款报告编号
	 */
	private String queryRequestCashNo;
	
	//请款报告查询页面（共通）
	/**
	 * 请款报告信息页码
	 */
	private int startIndexRequestCash;
	
	/**
	 * 请款报告信息翻页
	 */
	private Page pageRequestCash;
	
	private List<RequestCashDto> requestCashList;
	
	private String requestCashNoLow;
	
	private String requestCashNoHigh;
	
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
	 * 显示请款报告页面
	 * @return
	 */
	public String showRequestCashAction() {
		try {
			this.clearMessages();
			strRequestCashNoLow = "";
			strRequestCashNoHigh = "";
			strRequestCashName = "";
			addRequestCashDto = new RequestCashDto();
			updateRequestCashDto = new RequestCashDto();
			updateRequestCashNo = "";
			delRequestCashNo = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listRequestCash = new ArrayList<RequestCashDto>();
			queryRequestCashList();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询请款报告列表
	 * @return
	 */
	public String queryRequestCashList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			queryRequestCash();
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
	public String turnRequestCashPage() {
		try {
			this.clearMessages();
			queryRequestCash();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有请款报告列表
	 */
	@SuppressWarnings("unchecked")
	private void queryRequestCash() {
		listRequestCash = new ArrayList<RequestCashDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有请款报告
		this.page.setStartIndex(startIndex);
		page = requestcashService.queryRequestCashByPage(page, strRequestCashNoLow, strRequestCashNoHigh, strRequestCashName);
		listRequestCash = (List<RequestCashDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加请款报告页面
	 * @return
	 */
	public String showAddRequestCashAction() {
		try {
			this.clearMessages();
			addRequestCashDto = new RequestCashDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加请款报告
	 * @return
	 */
	public String addRequestCashAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addRequestCashDto)) {
				return "checkerror";
			}
			log.info("addRequestCashDto.getRequestCashno()=" + addRequestCashDto.getRequestcashno());
			log.info("addRequestCashDto.getRequestCashname()=" + addRequestCashDto.getRequestcashname());
			//校验请款报告代码是否存在
			RequestCashDto requestCash = requestcashService.queryAllRequestCashByID(addRequestCashDto.getRequestcashno()+"");
			if(requestCash != null) {
				this.addActionMessage("请款报告已经存在！");
				return "checkerror";
			}
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			if(addReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(addReportFile01, pdf_path, file01Name);
				addRequestCashDto.setReportpath01(newfile01);
			}
			if(addReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(addReportFile02, pdf_path, file02Name);
				addRequestCashDto.setReportpath02(newfile02);
			}
			if(addReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(addReportFile03, pdf_path, file03Name);
				addRequestCashDto.setReportpath03(newfile03);
			}
			
			//保存数据
			addRequestCashDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addRequestCashDto.setCreateuid(username);
			String requestCashno = requestcashService.insertRequestCash(addRequestCashDto);
			this.addActionMessage("添加请款报告成功！请款报告编号为：" + requestCashno);
			addRequestCashDto = new RequestCashDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加请款报告失败！");
			log.error("addRequestCashAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改请款报告页面
	 * @return
	 */
	public String showUpdRequestCashAction() {
		try {
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
			this.clearMessages();
			System.out.println("requestCashNo is: "+updateRequestCashNo);
			updateRequestCashDto = requestcashService.queryRequestCashByID(updateRequestCashNo);
			if(updateRequestCashDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询请款报告异常！");
			log.error("showUpdRequestCashAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改请款报告
	 * @return
	 */
	public String updRequestCashAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateRequestCashDto)) {
				return "checkerror";
			}
			System.out.println("requestCashNo is: "+updateRequestCashDto.getRequestcashno());
			
			//文件目录
			String pdf_path = PropertiesConfig.getPropertiesValueByKey(Constants.PROPERTIES_PDF_PATH);
			
			//保存文件到指定目录
			String oldfile1 = "";
			String oldfile2 = "";
			String oldfile3 = "";
			RequestCashDto oldReport = requestcashService.queryRequestCashByID(updateRequestCashNo);
			
			if(updReportFile01 != null) {
				String newfile01 = FileUtil.uploadFile(updReportFile01, pdf_path, file01Name);
				updateRequestCashDto.setReportpath01(newfile01);
				oldfile1 = oldReport.getReportpath01();
			}
			if(updReportFile02 != null) {
				String newfile02 = FileUtil.uploadFile(updReportFile02, pdf_path, file02Name);
				updateRequestCashDto.setReportpath02(newfile02);
				oldfile2 = oldReport.getReportpath02();
			}
			if(updReportFile03 != null) {
				String newfile03 = FileUtil.uploadFile(updReportFile03, pdf_path, file03Name);
				updateRequestCashDto.setReportpath03(newfile03);
				oldfile3 = oldReport.getReportpath03();
			}
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateRequestCashDto.setUpdateuid(username);
			requestcashService.updateRequestCash(updateRequestCashDto);
			this.addActionMessage("修改请款报告成功！");
			//清空数据
			updReportFile01 = null;
			updReportFile02 = null;
			updReportFile03 = null;
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改请款报告失败！");
			log.error("updRequestCashAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除请款报告
	 * @return
	 */
	public String delRequestCashAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delRequestCashNo)) {
				this.addActionMessage("请款报告代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			requestcashService.deleteRequestCash(delRequestCashNo, username);
			this.addActionMessage("删除请款报告成功！");
			delRequestCashNo = "";
			//刷新页面
			startIndex = 0;
			queryRequestCash();
		} catch(Exception e) {
			log.error("delRequestCashAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param requestCash
	 * @return
	 */
	private boolean checkData(RequestCashDto requestCash) {
		if(requestCash == null) {
			this.addActionMessage("请款报告名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(requestCash.getRequestCashno()+"")) {
//			this.addActionMessage("请款报告代码不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(requestCash.getRequestcashname())) {
			this.addActionMessage("请款报告名称不能为空！");
			return false;
		}
		if(requestCash.getRequestcashname().length() > 64) {
			this.addActionMessage("请款报告名称不能超过64个字符！");
			return false;
		}
		if(!DateUtil.isDate(requestCash.getRegisterdate().toString())) {
			this.addActionMessage("登记日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(requestCash.getNote()) && requestCash.getNote().length() > 500) {
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

	public List<RequestCashDto> getListRequestCash() {
		return listRequestCash;
	}

	public void setListRequestCash(List<RequestCashDto> listRequestCash) {
		this.listRequestCash = listRequestCash;
	}

	public String getStrRequestCashNoLow() {
		return strRequestCashNoLow;
	}

	public void setStrRequestCashNoLow(String strRequestCashNoLow) {
		this.strRequestCashNoLow = strRequestCashNoLow;
	}

	public String getStrRequestCashNoHigh() {
		return strRequestCashNoHigh;
	}

	public void setStrRequestCashNoHigh(String strRequestCashNoHigh) {
		this.strRequestCashNoHigh = strRequestCashNoHigh;
	}

	public RequestCashDto getAddRequestCashDto() {
		return addRequestCashDto;
	}

	public void setAddRequestCashDto(RequestCashDto addRequestCashDto) {
		this.addRequestCashDto = addRequestCashDto;
	}

	public RequestCashDto getUpdateRequestCashDto() {
		return updateRequestCashDto;
	}

	public void setUpdateRequestCashDto(RequestCashDto updateRequestCashDto) {
		this.updateRequestCashDto = updateRequestCashDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateRequestCashNo() {
		return updateRequestCashNo;
	}

	public void setUpdateRequestCashNo(String updateRequestCashNo) {
		this.updateRequestCashNo = updateRequestCashNo;
	}

	public String getDelRequestCashNo() {
		return delRequestCashNo;
	}

	public void setDelRequestCashNo(String delRequestCashNo) {
		this.delRequestCashNo = delRequestCashNo;
	}

	public String getQueryRequestCashNo() {
		return queryRequestCashNo;
	}

	public void setQueryRequestCashNo(String queryRequestCashNo) {
		this.queryRequestCashNo = queryRequestCashNo;
	}

	public int getStartIndexRequestCash() {
		return startIndexRequestCash;
	}

	public void setStartIndexRequestCash(int startIndexRequestCash) {
		this.startIndexRequestCash = startIndexRequestCash;
	}

	public Page getPageRequestCash() {
		return pageRequestCash;
	}

	public void setPageRequestCash(Page pageRequestCash) {
		this.pageRequestCash = pageRequestCash;
	}

	public List<RequestCashDto> getRequestCashList() {
		return requestCashList;
	}

	public void setRequestCashList(List<RequestCashDto> requestCashList) {
		this.requestCashList = requestCashList;
	}

	public String getRequestCashNoLow() {
		return requestCashNoLow;
	}

	public void setRequestCashNoLow(String salesreportNoLow) {
		this.requestCashNoLow = salesreportNoLow;
	}

	public String getRequestCashNoHigh() {
		return requestCashNoHigh;
	}

	public void setRequestCashNoHigh(String requestCashNoHigh) {
		this.requestCashNoHigh = requestCashNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrRequestCashName() {
		return strRequestCashName;
	}

	public void setStrRequestCashName(String strRequestCashName) {
		this.strRequestCashName = strRequestCashName;
	}

	public RequestCashService getRequestcashService() {
		return requestcashService;
	}

	public void setRequestcashService(RequestCashService requestcashService) {
		this.requestcashService = requestcashService;
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
