package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.service.SupplierService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 供应商Action
 * @author 
 * @time 
 * @version 1.0
 */
public class SupplierAction extends BaseAction {
	

	private static final long serialVersionUID = -6455172522033059230L;

	private static final Logger log = LogManager.getLogger(SupplierAction.class);
	
	private SupplierService supplierService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	/**
	 * 供应商列表
	 */
	private List<SupplierDto> listSupplier;
	
	/**
	 * 供应商编号（起）
	 */
	private String strSupplierNoLow;
	
	/**
	 * 供应商编号（终）
	 */
	private String strSupplierNoHigh;
	
	/**
	 * 供应商名称
	 */
	private String strSupplierName;
	
	/**
	 * 新增供应商对象
	 */
	private SupplierDto addSupplierDto;
	
	/**
	 * 修改的供应商编号
	 */
	private String updateSupplierNo;
	
	/**
	 * 修改供应商对象
	 */
	private SupplierDto updateSupplierDto;
	
	/**
	 * 删除的供应商编号
	 */
	private String delSupplierNo;
	
	/**
	 * ajax查询条件-供应商编号
	 */
	private String querySupplierNo;
	
	//供应商查询页面（共通）
	/**
	 * 供应商信息页码
	 */
	private int startIndexSupplier;
	
	/**
	 * 供应商信息翻页
	 */
	private Page pageSupplier;
	
	private List<SupplierDto> supplierList;
	
	private String supplierNoLow;
	
	private String supplierNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;
	
	//供应商选择页面=================
	//供应商名
	private String strSelectSupplierName;
	//供应商列表
	private List<SupplierDto> listSelectSupplier;
	//页码
	private int selectStartIndex;
	//翻页page
	private Page selectPage;
	//一页显示记录数
	private Integer intSelectPageSize;
	//供应商选择页面=================
	
	//供应商选择页面start
	/**
	 * 显示供应商选择页面
	 * @return
	 */
	public String showSelectSupplierAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			strSelectSupplierName = "";
			//默认10条
			intSelectPageSize = 10;
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectSupplier();
		} catch(Exception e) {
			log.error("showSelectSupplierAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 供应商查询（供应商选择页面）
	 * @return
	 */
	public String querySelectSupplierAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			//默认10条
			if(intSelectPageSize == null) {
				intSelectPageSize = 10;
			}
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectSupplier();
		} catch(Exception e) {
			log.error("querySelectSupplierAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（供应商选择页面）
	 * @return
	 */
	public String turnSelectSupplierAction() {
		try {
			this.clearMessages();
			//查询数据
			querySelectSupplier();
		} catch(Exception e) {
			log.error("turnSelectSupplierAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//供应商选择页面end

	/**
	 * 显示供应商页面
	 * @return
	 */
	public String showSupplierAction() {
		try {
			this.clearMessages();
			strSupplierNoLow = "";
			strSupplierNoHigh = "";
			strSupplierName = "";
			addSupplierDto = new SupplierDto();
			updateSupplierDto = new SupplierDto();
			updateSupplierNo = "";
			delSupplierNo = "";
			page = new Page();
			startIndex = 0;
			listSupplier = new ArrayList<SupplierDto>();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询供应商列表
	 * @return
	 */
	public String querySupplierList() {
		try {
			this.clearMessages();
			page = new Page();
			startIndex = 0;
			querySupplier();
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
	public String turnSupplierPage() {
		try {
			this.clearMessages();
			querySupplier();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示添加供应商页面
	 * @return
	 */
	public String showAddSupplierAction() {
		try {
			this.clearMessages();
			addSupplierDto = new SupplierDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加供应商
	 * @return
	 */
	public String addSupplierAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addSupplierDto)) {
				return "checkerror";
			}
			log.info("addSupplierDto.getId()=" + addSupplierDto.getId());
			log.info("addSupplierDto.getSuppliername()=" + addSupplierDto.getSuppliername());
			//校验供应商代码是否存在
			SupplierDto supplier = supplierService.queryAllSupplierByID(addSupplierDto.getId()+"");
			if(supplier != null) {
				this.addActionMessage("供应商代码已经存在！");
				return "checkerror";
			}
			//保存数据
			addSupplierDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addSupplierDto.setCreateuid(username);
			supplierService.insertSupplier(addSupplierDto);
			this.addActionMessage("添加供应商成功！");
			addSupplierDto = new SupplierDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加供应商失败！");
			log.error("addSupplierAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改供应商页面
	 * @return
	 */
	public String showUpdSupplierAction() {
		try {
			this.clearMessages();
			System.out.println("id is: "+updateSupplierNo);
			updateSupplierDto = supplierService.querySupplierByID(updateSupplierNo);
			if(updateSupplierDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询供应商异常！");
			log.error("showUpdSupplierAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改供应商
	 * @return
	 */
	public String updSupplierAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateSupplierDto)) {
				return "checkerror";
			}
			System.out.println("id is: "+updateSupplierDto.getId());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateSupplierDto.setUpdateuid(username);
			supplierService.updateSupplier(updateSupplierDto);
			this.addActionMessage("修改供应商成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改供应商失败！");
			log.error("updSupplierAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除供应商
	 * @return
	 */
	public String delSupplierAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delSupplierNo)) {
				this.addActionMessage("供应商代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			supplierService.deleteSupplier(delSupplierNo, username);
			this.addActionMessage("删除供应商成功！");
			delSupplierNo = "";
			//刷新页面
			startIndex = 0;
			querySupplier();
		} catch(Exception e) {
			log.error("delSupplierAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param supplier
	 * @return
	 */
	private boolean checkData(SupplierDto supplier) {
		if(supplier == null) {
			this.addActionMessage("供应商代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(supplier.getId()+"")) {
			this.addActionMessage("供应商代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(supplier.getSuppliername())) {
			this.addActionMessage("供应商名称不能为空！");
			return false;
		}
		if(supplier.getSuppliername().length() > 40) {
			this.addActionMessage("供应商名称不能超过40个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(supplier.getNote()) && supplier.getNote().length() > 100) {
			this.addActionMessage("备考不能超过100个字符！");
			return false;
		}
		return true;
	}
	
	/**
	 * 翻页查询所有供应商列表
	 */
	@SuppressWarnings("unchecked")
	private void querySupplier() {
		listSupplier = new ArrayList<SupplierDto>();
		if(page == null) {
			page = new Page();
		}
		//翻页查询所有供应商
		this.page.setStartIndex(startIndex);
		page = supplierService.querySupplierByPage(page, strSupplierNoLow, strSupplierNoHigh, strSupplierName);
		listSupplier = (List<SupplierDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 翻页查询供应商列表（供应商选择页面用）
	 */
	@SuppressWarnings("unchecked")
	private void querySelectSupplier() {
		listSelectSupplier = new ArrayList<SupplierDto>();
		if(selectPage == null) {
			selectPage = new Page(intSelectPageSize);
		}
		//翻页查询所有供应商
		this.selectPage.setStartIndex(selectStartIndex);
		selectPage = supplierService.querySupplierByPage(selectPage, "", "", strSelectSupplierName);
		listSelectSupplier = (List<SupplierDto>) selectPage.getItems();
		
		this.setSelectStartIndex(selectPage.getStartIndex());
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

	public List<SupplierDto> getListSupplier() {
		return listSupplier;
	}

	public void setListSupplier(List<SupplierDto> listSupplier) {
		this.listSupplier = listSupplier;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public String getStrSupplierNoLow() {
		return strSupplierNoLow;
	}

	public void setStrSupplierNoLow(String strSupplierNoLow) {
		this.strSupplierNoLow = strSupplierNoLow;
	}

	public String getStrSupplierNoHigh() {
		return strSupplierNoHigh;
	}

	public void setStrSupplierNoHigh(String strSupplierNoHigh) {
		this.strSupplierNoHigh = strSupplierNoHigh;
	}

	public SupplierDto getAddSupplierDto() {
		return addSupplierDto;
	}

	public void setAddSupplierDto(SupplierDto addSupplierDto) {
		this.addSupplierDto = addSupplierDto;
	}

	public SupplierDto getUpdateSupplierDto() {
		return updateSupplierDto;
	}

	public void setUpdateSupplierDto(SupplierDto updateSupplierDto) {
		this.updateSupplierDto = updateSupplierDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateSupplierNo() {
		return updateSupplierNo;
	}

	public void setUpdateSupplierNo(String updateSupplierNo) {
		this.updateSupplierNo = updateSupplierNo;
	}

	public String getDelSupplierNo() {
		return delSupplierNo;
	}

	public void setDelSupplierNo(String delSupplierNo) {
		this.delSupplierNo = delSupplierNo;
	}

	public String getQuerySupplierNo() {
		return querySupplierNo;
	}

	public void setQuerySupplierNo(String querySupplierNo) {
		this.querySupplierNo = querySupplierNo;
	}

	public int getStartIndexSupplier() {
		return startIndexSupplier;
	}

	public void setStartIndexSupplier(int startIndexSupplier) {
		this.startIndexSupplier = startIndexSupplier;
	}

	public Page getPageSupplier() {
		return pageSupplier;
	}

	public void setPageSupplier(Page pageSupplier) {
		this.pageSupplier = pageSupplier;
	}

	public List<SupplierDto> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<SupplierDto> supplierList) {
		this.supplierList = supplierList;
	}

	public String getSupplierNoLow() {
		return supplierNoLow;
	}

	public void setSupplierNoLow(String supplierNoLow) {
		this.supplierNoLow = supplierNoLow;
	}

	public String getSupplierNoHigh() {
		return supplierNoHigh;
	}

	public void setSupplierNoHigh(String supplierNoHigh) {
		this.supplierNoHigh = supplierNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrSupplierName() {
		return strSupplierName;
	}

	public void setStrSupplierName(String strSupplierName) {
		this.strSupplierName = strSupplierName;
	}

	public String getStrSelectSupplierName() {
		return strSelectSupplierName;
	}
	public void setStrSelectSupplierName(String strSelectSupplierName) {
		this.strSelectSupplierName = strSelectSupplierName;
	}

	public List<SupplierDto> getListSelectSupplier() {
		return listSelectSupplier;
	}

	public void setListSelectSupplier(List<SupplierDto> listSelectSupplier) {
		this.listSelectSupplier = listSelectSupplier;
	}

	public int getSelectStartIndex() {
		return selectStartIndex;
	}

	public Page getSelectPage() {
		return selectPage;
	}

	public void setSelectStartIndex(int selectStartIndex) {
		this.selectStartIndex = selectStartIndex;
	}

	public void setSelectPage(Page selectPage) {
		this.selectPage = selectPage;
	}

	public Integer getIntSelectPageSize() {
		return intSelectPageSize;
	}

	public void setIntSelectPageSize(Integer intSelectPageSize) {
		this.intSelectPageSize = intSelectPageSize;
	}
}
