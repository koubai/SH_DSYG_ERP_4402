package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.DeliveryDto;
import com.cn.dsyg.dto.SupplierDto;
import com.cn.dsyg.service.DeliveryService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 快递Action
 * @author 
 * @time 
 * @version 1.0
 */
public class DeliveryAction extends BaseAction {

	private static final long serialVersionUID = -8051284865822816022L;

	private static final Logger log = LogManager.getLogger(DeliveryAction.class);
	
	private DeliveryService deliveryService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 一页显示数据条数
	 */
	private Integer intPageSize;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	/**
	 * 快递列表
	 */
	private List<DeliveryDto> listDelivery;
	
	/**
	 * 快递编号（起）
	 */
	private String strDeliveryNoLow;
	
	/**
	 * 快递编号（终）
	 */
	private String strDeliveryNoHigh;
	
	/**
	 * 快递名称
	 */
	private String strDeliveryName;
	
	/**
	 * 新增快递对象
	 */
	private DeliveryDto addDeliveryDto;
	
	/**
	 * 修改的快递编号
	 */
	private String updateDeliveryNo;
	
	/**
	 * 修改快递对象
	 */
	private DeliveryDto updateDeliveryDto;
	
	/**
	 * 删除的快递编号
	 */
	private String delDeliveryNo;
	
	/**
	 * ajax查询条件-快递编号
	 */
	private String queryDeliveryNo;
	
	//快递查询页面（共通）
	/**
	 * 快递信息页码
	 */
	private int startIndexDelivery;
	
	/**
	 * 快递信息翻页
	 */
	private Page pageDelivery;
	
	private List<DeliveryDto> deliveryList;
	
	private String deliveryNoLow;
	
	private String deliveryNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;
	
	//快递选择页面=================
	//快递名
	private String strSelectDeliveryName;
	//快递列表
	private List<DeliveryDto> listSelectDelivery;
	//页码
	private int selectStartIndex;
	//翻页page
	private Page selectPage;
	//一页显示记录数
	private Integer intSelectPageSize;
	//快递选择页面=================
	
	//快递选择页面start
	/**
	 * 显示快递选择页面
	 * @return
	 */
	public String showSelectDeliveryAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			strSelectDeliveryName = "";
			//默认10条
			intSelectPageSize = 10;
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectDelivery();
		} catch(Exception e) {
			log.error("showSelectDeliveryAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 快递查询（快递选择页面）
	 * @return
	 */
	public String querySelectDeliveryAction() {
		try {
			this.clearMessages();
			selectStartIndex = 0;
			//默认10条
			if(intSelectPageSize == null) {
				intSelectPageSize = 10;
			}
			selectPage = new Page(intSelectPageSize);
			//查询数据
			querySelectDelivery();
		} catch(Exception e) {
			log.error("querySelectDeliveryAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页（快递选择页面）
	 * @return
	 */
	public String turnSelectDeliveryAction() {
		try {
			this.clearMessages();
			//查询数据
			querySelectDelivery();
		} catch(Exception e) {
			log.error("turnSelectDeliveryAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	//快递选择页面end

	/**
	 * 显示快递页面
	 * @return
	 */
	public String showEtbDeliveryAction() {
		try {
			this.clearMessages();
			strDeliveryNoLow = "";
			strDeliveryNoHigh = "";
			strDeliveryName = "";
			addDeliveryDto = new DeliveryDto();
			updateDeliveryDto = new DeliveryDto();
			updateDeliveryNo = "";
			delDeliveryNo = "";
			page = new Page();
			startIndex = 0;
			intPageSize = 10;
			listDelivery = new ArrayList<DeliveryDto>();
			
			queryEtbDelivery();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询快递列表
	 * @return
	 */
	public String queryEtbDeliveryList() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			queryEtbDelivery();
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
	public String turnEtbDeliveryPage() {
		try {
			this.clearMessages();
			queryEtbDelivery();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有快递列表
	 */
	@SuppressWarnings("unchecked")
	private void queryEtbDelivery() {
		listDelivery = new ArrayList<DeliveryDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有快递
		this.page.setStartIndex(startIndex);
		page = deliveryService.queryEtbDeliveryByPage(page, strDeliveryNoLow, strDeliveryNoHigh, strDeliveryName);
		listDelivery = (List<DeliveryDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加快递页面
	 * @return
	 */
	public String showAddEtbDeliveryAction() {
		try {
			this.clearMessages();
			addDeliveryDto = new DeliveryDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加快递
	 * @return
	 */
	public String addEtbDeliveryAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addDeliveryDto)) {
				return "checkerror";
			}
			log.info("addDeliveryDto.getId()=" + addDeliveryDto.getId());
			log.info("addDeliveryDto.getDeliveryname()=" + addDeliveryDto.getDeliveryname());
			//校验快递代码是否存在
			DeliveryDto delivery = deliveryService.queryAllEtbDeliveryByID(addDeliveryDto.getId()+"");
			if(delivery != null) {
				this.addActionMessage("快递代码已经存在！");
				return "checkerror";
			}
			//校验快递名称是否存在
			DeliveryDto delivery1 = deliveryService.queryAllEtbDeliveryByName(addDeliveryDto.getDeliveryname()+"");
			if(delivery1 != null) {
				this.addActionMessage("相同快递名称已经存在！");
				return "checkerror";
			}

			//保存数据
			addDeliveryDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addDeliveryDto.setCreateuid(username);
			addDeliveryDto.setBelongto((String)ActionContext.getContext().getSession().get(Constants.SESSION_BELONGTO));
			deliveryService.insertEtbDelivery(addDeliveryDto);
			this.addActionMessage("添加快递成功！");
			addDeliveryDto = new DeliveryDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加快递失败！");
			log.error("addEtbDeliveryAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改快递页面
	 * @return
	 */
	public String showUpdEtbDeliveryAction() {
		try {
			this.clearMessages();
//			System.out.println("id is: "+updateDeliveryNo);
			updateDeliveryDto = deliveryService.queryEtbDeliveryByID(updateDeliveryNo);
			if(updateDeliveryDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询快递异常！");
			log.error("showUpdEtbDeliveryAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改快递
	 * @return
	 */
	public String updEtbDeliveryAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateDeliveryDto)) {
				return "checkerror";
			}
//			System.out.println("id is: "+updateDeliveryDto.getId());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateDeliveryDto.setUpdateuid(username);
			deliveryService.updateEtbDelivery(updateDeliveryDto);
			this.addActionMessage("修改快递成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改快递失败！");
			log.error("updEtbDeliveryAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除快递
	 * @return
	 */
	public String delEtbDeliveryAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delDeliveryNo)) {
				this.addActionMessage("快递代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			deliveryService.deleteEtbDelivery(delDeliveryNo, username);
			this.addActionMessage("删除快递成功！");
			delDeliveryNo = "";
			//刷新页面
			startIndex = 0;
			queryEtbDelivery();
		} catch(Exception e) {
			log.error("delEtbDeliveryAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询快递列表（快递选择页面用）
	 */
	@SuppressWarnings("unchecked")
	private void querySelectDelivery() {
		listSelectDelivery = new ArrayList<DeliveryDto>();
		if(selectPage == null) {
			selectPage = new Page(intSelectPageSize);
		}
		//翻页查询所有快递
		this.selectPage.setStartIndex(selectStartIndex);
		selectPage = deliveryService.queryEtbDeliveryByPage(selectPage, "", "", strSelectDeliveryName);
		listSelectDelivery = (List<DeliveryDto>) selectPage.getItems();
		
		this.setSelectStartIndex(selectPage.getStartIndex());
	}
	
	/**
	 * 验证数据格式
	 * @param delivery
	 * @return
	 */
	private boolean checkData(DeliveryDto delivery) {
		if(delivery == null) {
			this.addActionMessage("快递代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(delivery.getId()+"")) {
			this.addActionMessage("快递代码不能为空！");
			return false;
		}
		if(StringUtil.isBlank(delivery.getDeliveryname())) {
			this.addActionMessage("快递名称不能为空！");
			return false;
		}
		if(delivery.getDeliveryname().length() > 40) {
			this.addActionMessage("快递名称不能超过40个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(delivery.getNote()) && delivery.getNote().length() > 100) {
			this.addActionMessage("备考不能超过100个字符！");
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

	public List<DeliveryDto> getListDelivery() {
		return listDelivery;
	}

	public void setListDelivery(List<DeliveryDto> listDelivery) {
		this.listDelivery = listDelivery;
	}
	public String getStrDeliveryNoLow() {
		return strDeliveryNoLow;
	}

	public void setStrDeliveryNoLow(String strDeliveryNoLow) {
		this.strDeliveryNoLow = strDeliveryNoLow;
	}

	public String getStrDeliveryNoHigh() {
		return strDeliveryNoHigh;
	}

	public void setStrDeliveryNoHigh(String strDeliveryNoHigh) {
		this.strDeliveryNoHigh = strDeliveryNoHigh;
	}

	public DeliveryDto getAddDeliveryDto() {
		return addDeliveryDto;
	}

	public void setAddDeliveryDto(DeliveryDto addDeliveryDto) {
		this.addDeliveryDto = addDeliveryDto;
	}

	public DeliveryDto getUpdateDeliveryDto() {
		return updateDeliveryDto;
	}

	public void setUpdateDeliveryDto(DeliveryDto updateDeliveryDto) {
		this.updateDeliveryDto = updateDeliveryDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateDeliveryNo() {
		return updateDeliveryNo;
	}

	public void setUpdateDeliveryNo(String updateDeliveryNo) {
		this.updateDeliveryNo = updateDeliveryNo;
	}

	public String getDelDeliveryNo() {
		return delDeliveryNo;
	}

	public void setDelDeliveryNo(String delDeliveryNo) {
		this.delDeliveryNo = delDeliveryNo;
	}

	public String getQueryDeliveryNo() {
		return queryDeliveryNo;
	}

	public void setQueryDeliveryNo(String queryDeliveryNo) {
		this.queryDeliveryNo = queryDeliveryNo;
	}

	public int getStartIndexDelivery() {
		return startIndexDelivery;
	}

	public void setStartIndexDelivery(int startIndexDelivery) {
		this.startIndexDelivery = startIndexDelivery;
	}

	public Page getPageDelivery() {
		return pageDelivery;
	}

	public void setPageDelivery(Page pageDelivery) {
		this.pageDelivery = pageDelivery;
	}

	public List<DeliveryDto> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<DeliveryDto> deliveryList) {
		this.deliveryList = deliveryList;
	}

	public String getDeliveryNoLow() {
		return deliveryNoLow;
	}

	public void setDeliveryNoLow(String deliveryNoLow) {
		this.deliveryNoLow = deliveryNoLow;
	}

	public String getDeliveryNoHigh() {
		return deliveryNoHigh;
	}

	public void setDeliveryNoHigh(String deliveryNoHigh) {
		this.deliveryNoHigh = deliveryNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrDeliveryName() {
		return strDeliveryName;
	}

	public void setStrDeliveryName(String strDeliveryName) {
		this.strDeliveryName = strDeliveryName;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public String getStrSelectDeliveryName() {
		return strSelectDeliveryName;
	}

	public void setStrSelectDeliveryName(String strSelectDeliveryName) {
		this.strSelectDeliveryName = strSelectDeliveryName;
	}

	public List<DeliveryDto> getListSelectDelivery() {
		return listSelectDelivery;
	}

	public void setListSelectDelivery(List<DeliveryDto> listSelectDelivery) {
		this.listSelectDelivery = listSelectDelivery;
	}

	public int getSelectStartIndex() {
		return selectStartIndex;
	}

	public void setSelectStartIndex(int selectStartIndex) {
		this.selectStartIndex = selectStartIndex;
	}

	public Page getSelectPage() {
		return selectPage;
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

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}
}
