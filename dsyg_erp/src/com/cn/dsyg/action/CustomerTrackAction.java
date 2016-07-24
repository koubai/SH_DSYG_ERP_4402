package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.CustomerTrackDto;
import com.cn.dsyg.dto.CustomerTrackHistDto;
import com.cn.dsyg.service.CustomerTrackService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 客户跟踪Action
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerTrackAction extends BaseAction {

	private static final long serialVersionUID = -1375363904142583170L;

	private static final Logger log = LogManager.getLogger(CustomerTrackAction.class);
	
	private CustomerTrackService customerTrackService;
	
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
	 * 客户跟踪列表
	 */
	private List<CustomerTrackDto> listCustomerTrack;
	
	/**
	 * 客户跟踪编号（起）
	 */
	private String strIdLow;
	
	/**
	 * 客户跟踪编号（终）
	 */
	private String strIdHigh;
	
	/**
	 * 客户名称
	 */
	private String strCustomerName;
	
	/**
	 * 新增客户跟踪对象
	 */
	private CustomerTrackDto addCustomerTrackDto;
	
	/**
	 * 修改的ID
	 */
	private String updateId;
	
	/**
	 * 修改客户跟踪对象
	 */
	private CustomerTrackDto updateCustomerTrackDto;
	
	/**
	 * 删除的ID
	 */
	private String delId;
	
	/**
	 * ajax查询条件-ID
	 */
	private String queryId;
	
	/**
	 * 控件ID
	 */
	private String strKey;
	
	/**
	 * 客户跟踪状态
	 */
	private String strStatus;
	
	//履历
	/**
	 * 履历查询条件
	 */
	private String strTrackNoHist;

	/**
	 * 履历列表
	 */
	private List<CustomerTrackHistDto> listTrackHist;
	
	/**
	 * 履历SEQ
	 */
	private String detailTrackHisSeq;
	
	/**
	 * 履历明细
	 */
	private CustomerTrackHistDto trackHistDtoDetail;

	/**
	 * 显示客户跟踪页面
	 * @return
	 */
	public String showCustomerTrackAction() {
		try {
			this.clearMessages();
			strIdLow = "";
			strIdHigh = "";
			strCustomerName = "";
			addCustomerTrackDto = new CustomerTrackDto();
			updateCustomerTrackDto = new CustomerTrackDto();
			updateId = "";
			delId = "";
			strStatus = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listCustomerTrack = new ArrayList<CustomerTrackDto>();
			
			queryCustomerTrack();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询客户跟踪列表
	 * @return
	 */
	public String queryCustomerTrackList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			queryCustomerTrack();
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
	public String turnCustomerTrackPage() {
		try {
			this.clearMessages();
			queryCustomerTrack();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有客户跟踪列表
	 */
	@SuppressWarnings("unchecked")
	private void queryCustomerTrack() {
		listCustomerTrack = new ArrayList<CustomerTrackDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有客户跟踪
		this.page.setStartIndex(startIndex);
		page = customerTrackService.queryCustomerTrackByPage(page, strIdLow, strIdHigh, strCustomerName, strStatus);
		listCustomerTrack = (List<CustomerTrackDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加客户跟踪页面
	 * @return
	 */
	public String showAddCustomerTrackAction() {
		try {
			this.clearMessages();
			addCustomerTrackDto = new CustomerTrackDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加客户跟踪
	 * @return
	 */
	public String addCustomerTrackAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addCustomerTrackDto)) {
				return "checkerror";
			}
			log.info("addCustomerTrackDto.getId()=" + addCustomerTrackDto.getTrackno());
			log.info("addCustomerTrackDto.getCustomername()=" + addCustomerTrackDto.getCustomername());
			//校验编号是否存在
			CustomerTrackDto customerTrack = customerTrackService.queryAllCustomerTrackByID(addCustomerTrackDto.getTrackno()+"");
			if(customerTrack != null) {
				this.addActionMessage("客户跟踪编号已经存在！");
				return "checkerror";
			}
			//保存数据
			//addCustomerTrackDto.setStatus(Constants.STATUS_NORMAL);
			String user_id = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			if (addCustomerTrackDto.getHandlerid().compareTo("")==0)
				addCustomerTrackDto.setHandlerid(user_id);
			addCustomerTrackDto.setCreateuid(user_id);
			String trackno = customerTrackService.insertCustomerTrack(addCustomerTrackDto);
			log.error("added trackno:" + trackno);
			this.addActionMessage("添加客户跟踪成功！");
			addCustomerTrackDto = new CustomerTrackDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加客户跟踪失败！");
			log.error("addCustomerTrackAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改客户跟踪页面
	 * @return
	 */
	public String showUpdCustomerTrackAction() {
		try {
			this.clearMessages();
//			System.out.println("id is: "+updateId);
			updateCustomerTrackDto = customerTrackService.queryCustomerTrackByID(updateId);
			if(updateCustomerTrackDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询客户跟踪异常！");
			log.error("showUpdCustomerTrackAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改客户跟踪
	 * @return
	 */
	public String updateCustomerTrackAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateCustomerTrackDto)) {
				return "checkerror";
			}
//			System.out.println("id is: "+updateCustomerTrackDto.getId());
			//修改数据
			String user_id = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updateCustomerTrackDto.setUpdateuid(user_id);
			customerTrackService.updateCustomerTrack(updateCustomerTrackDto);
			this.addActionMessage("修改客户跟踪成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改客户跟踪失败！");
			log.error("updCustomerTrackAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除客户跟踪
	 * @return
	 */
	public String delCustomerTrackAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delId)) {
				this.addActionMessage("ID为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			customerTrackService.deleteCustomerTrack(delId, username);
			this.addActionMessage("删除客户跟踪成功！");
			delId = "";
			//刷新页面
			startIndex = 0;
			queryCustomerTrack();
		} catch(Exception e) {
			log.error("delCustomerTrackAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 履历明细页面
	 * @return
	 */
	public String showTrackHistDetail() {
		try {
			this.clearMessages();
			trackHistDtoDetail = new CustomerTrackHistDto();
			trackHistDtoDetail = customerTrackService.queryTrackHistByID(detailTrackHisSeq);
		} catch(Exception e) {
			log.error("showTrackHistDetail error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 显示所有的履历（不翻页）
	 * @return
	 */
	public String showAllTrackHisAction() {
		try {
			this.clearMessages();
			detailTrackHisSeq = "";
			trackHistDtoDetail = new CustomerTrackHistDto();
			listTrackHist = new ArrayList<CustomerTrackHistDto>();
			//查询所有履历
			System.out.println("action id is: " + strTrackNoHist);
			listTrackHist = customerTrackService.queryAllTrackHist(strTrackNoHist);
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param customerTrack
	 * @return
	 */
	private boolean checkData(CustomerTrackDto customerTrack) {
		if(customerTrack == null) {
			this.addActionMessage("客户名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(personal.getUserid()+"")) {
//			this.addActionMessage("员工编号不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(customerTrack.getCustomername())) {
			this.addActionMessage("客户名称不能为空！");
			return false;
		}
		if(customerTrack.getCustomername().length() > 32) {
			this.addActionMessage("客户名称不能超过32个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(customerTrack.getSource()) && customerTrack.getSource().length() > 16) {
			this.addActionMessage("客户来源不能超过16个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(customerTrack.getNote()) && customerTrack.getNote().length() > 256) {
			this.addActionMessage("客户备注不能超过256个字符！");
			return false;
		}
		if(StringUtil.isBlank(customerTrack.getReceivedate())){
			customerTrack.setReceivedate(null);
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

	public CustomerTrackDto getAddCustomerTrackDto() {
		return addCustomerTrackDto;
	}

	public void setAddCustomerTrackDto(CustomerTrackDto addCustomerTrackDto) {
		this.addCustomerTrackDto = addCustomerTrackDto;
	}

	public CustomerTrackDto getUpdateCustomerTrackDto() {
		return updateCustomerTrackDto;
	}

	public void setUpdateCustomerTrackDto(CustomerTrackDto updateCustomerTrackDto) {
		this.updateCustomerTrackDto = updateCustomerTrackDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public CustomerTrackService getCustomerTrackService() {
		return customerTrackService;
	}

	public void setCustomerTrackService(CustomerTrackService customerTrackService) {
		this.customerTrackService = customerTrackService;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public List<CustomerTrackDto> getListCustomerTrack() {
		return listCustomerTrack;
	}

	public void setListCustomerTrack(List<CustomerTrackDto> listCustomerTrack) {
		this.listCustomerTrack = listCustomerTrack;
	}

	public String getStrIdLow() {
		return strIdLow;
	}

	public void setStrIdLow(String strIdLow) {
		this.strIdLow = strIdLow;
	}

	public String getStrIdHigh() {
		return strIdHigh;
	}

	public void setStrIdHigh(String strIdHigh) {
		this.strIdHigh = strIdHigh;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getDelId() {
		return delId;
	}

	public void setDelId(String delId) {
		this.delId = delId;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	public List<CustomerTrackHistDto> getListTrackHist() {
		return listTrackHist;
	}

	public void setListTrackHist(List<CustomerTrackHistDto> listTrackHist) {
		this.listTrackHist = listTrackHist;
	}

	public String getDetailTrackHisSeq() {
		return detailTrackHisSeq;
	}

	public void setDetailTrackHisSeq(String detailTrackHisSeq) {
		this.detailTrackHisSeq = detailTrackHisSeq;
	}

	public CustomerTrackHistDto getTrackHistDtoDetail() {
		return trackHistDtoDetail;
	}

	public void setTrackHistDtoDetail(CustomerTrackHistDto trackHistDtoDetail) {
		this.trackHistDtoDetail = trackHistDtoDetail;
	}

	public String getStrTrackNoHist() {
		return strTrackNoHist;
	}

	public void setStrTrackNoHist(String strTrackNoHist) {
		this.strTrackNoHist = strTrackNoHist;
	}
}
