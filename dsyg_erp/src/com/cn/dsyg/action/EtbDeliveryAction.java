package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.EtbDeliveryDto;
import com.cn.dsyg.service.DeliveryService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 快递Action
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbDeliveryAction extends BaseAction {

	private static final long serialVersionUID = -8051284865822816022L;

	private static final Logger log = LogManager.getLogger(EtbDeliveryAction.class);
	
	private DeliveryService deliveryService;
	
	/**
	 * 页码
	 */
	private int startIndex;
	
	/**
	 * 翻页
	 */
	private Page page;
	
	/**
	 * 快递列表
	 */
	private List<EtbDeliveryDto> listDelivery;
	
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
	private EtbDeliveryDto addDeliveryDto;
	
	/**
	 * 修改的快递编号
	 */
	private String updateDeliveryNo;
	
	/**
	 * 修改快递对象
	 */
	private EtbDeliveryDto updateDeliveryDto;
	
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
	
	private List<EtbDeliveryDto> deliveryList;
	
	private String deliveryNoLow;
	
	private String deliveryNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;

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
			addDeliveryDto = new EtbDeliveryDto();
			updateDeliveryDto = new EtbDeliveryDto();
			updateDeliveryNo = "";
			delDeliveryNo = "";
			page = new Page();
			startIndex = 0;
			listDelivery = new ArrayList<EtbDeliveryDto>();
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
			page = new Page();
			startIndex = 0;
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
		listDelivery = new ArrayList<EtbDeliveryDto>();
		if(page == null) {
			page = new Page();
		}
		//翻页查询所有快递
		this.page.setStartIndex(startIndex);
		page = deliveryService.queryEtbDeliveryByPage(page, strDeliveryNoLow, strDeliveryNoHigh, strDeliveryName);
		listDelivery = (List<EtbDeliveryDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加快递页面
	 * @return
	 */
	public String showAddEtbDeliveryAction() {
		try {
			this.clearMessages();
			addDeliveryDto = new EtbDeliveryDto();
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
			EtbDeliveryDto delivery = deliveryService.queryAllEtbDeliveryByID(addDeliveryDto.getId()+"");
			if(delivery != null) {
				this.addActionMessage("快递代码已经存在！");
				return "checkerror";
			}
			//保存数据
			addDeliveryDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addDeliveryDto.setCreateuid(username);
			deliveryService.insertEtbDelivery(addDeliveryDto);
			this.addActionMessage("添加快递成功！");
			addDeliveryDto = new EtbDeliveryDto();
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
			System.out.println("id is: "+updateDeliveryNo);
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
			System.out.println("id is: "+updateDeliveryDto.getId());
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
	 * 验证数据格式
	 * @param delivery
	 * @return
	 */
	private boolean checkData(EtbDeliveryDto delivery) {
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

	public List<EtbDeliveryDto> getListDelivery() {
		return listDelivery;
	}

	public void setListDelivery(List<EtbDeliveryDto> listDelivery) {
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

	public EtbDeliveryDto getAddDeliveryDto() {
		return addDeliveryDto;
	}

	public void setAddDeliveryDto(EtbDeliveryDto addDeliveryDto) {
		this.addDeliveryDto = addDeliveryDto;
	}

	public EtbDeliveryDto getUpdateDeliveryDto() {
		return updateDeliveryDto;
	}

	public void setUpdateDeliveryDto(EtbDeliveryDto updateDeliveryDto) {
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

	public List<EtbDeliveryDto> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<EtbDeliveryDto> deliveryList) {
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
}
