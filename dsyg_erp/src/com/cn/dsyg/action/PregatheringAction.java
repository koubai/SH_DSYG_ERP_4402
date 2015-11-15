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
import com.cn.dsyg.dto.PregatheringDto;
import com.cn.dsyg.service.PregatheringService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 资产Action
 * @author 
 * @time 
 * @version 1.0
 */
public class PregatheringAction extends BaseAction {


	private static final long serialVersionUID = 439820985218301434L;

	private static final Logger log = LogManager.getLogger(PregatheringAction.class);
	
	private PregatheringService pregatheringService;
	
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
	 * 预收款列表
	 */
	private List<PregatheringDto> listPregathering;
	
	/**
	 * 预收款编号（起）
	 */
	private String strPregatheringNoLow;
	
	/**
	 * 预收款编号（终）
	 */
	private String strPregatheringNoHigh;
	
	/**
	 * 预收款名称
	 */
	private String strPregatheringName;

	/**
	 * 预收款客户名称
	 */
	private String strCustomerName;
	
	/**
	 * 新增预收款对象
	 */
	private PregatheringDto addPregatheringDto;
	
	/**
	 * 修改的预收款编号
	 */
	private String updatePregatheringNo;
	
	/**
	 * 修改预收款对象
	 */
	private PregatheringDto updatePregatheringDto;
	
	/**
	 * 删除的预收款编号
	 */
	private String delPregatheringNo;
	
	/**
	 * ajax查询条件-预收款编号
	 */
	private String queryPregatheringNo;
	
	//预收款查询页面（共通）
	/**
	 * 预收款信息页码
	 */
	private int startIndexPregathering;
	
	/**
	 * 预收款信息翻页
	 */
	private Page pagePregathering;
	
	private List<PregatheringDto> pregatheringList;
	
	private String pregatheringNoLow;
	
	private String pregatheringNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;

	/**
	 * 显示预收款页面
	 * @return
	 */
	public String showPregatheringAction() {
		try {
			this.clearMessages();
			strPregatheringNoLow = "";
			strPregatheringNoHigh = "";
			strPregatheringName = "";
			addPregatheringDto = new PregatheringDto();
			updatePregatheringDto = new PregatheringDto();
			updatePregatheringNo = "";
			delPregatheringNo = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listPregathering = new ArrayList<PregatheringDto>();
			queryPregatheringList();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询预收款列表
	 * @return
	 */
	public String queryPregatheringList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			log.info("AAAAAAA");
			queryPregathering();
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
	public String turnPregatheringPage() {
		try {
			this.clearMessages();
			queryPregathering();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有预收款列表
	 */
	@SuppressWarnings("unchecked")
	private void queryPregathering() {
		listPregathering = new ArrayList<PregatheringDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有资产
		this.page.setStartIndex(startIndex);
		page = pregatheringService.queryPregatheringByPage(page, strPregatheringNoLow, strPregatheringNoHigh, strPregatheringName, strCustomerName);
		listPregathering = (List<PregatheringDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加预收款页面
	 * @return
	 */
	public String showAddPregatheringAction() {
		try {
			this.clearMessages();
			addPregatheringDto = new PregatheringDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加预收款
	 * @return
	 */
	public String addPregatheringAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addPregatheringDto)) {
				return "checkerror";
			}
			log.info("addPregatheringDto.getPregatheringno()=" + addPregatheringDto.getPregatheringno());
			log.info("addPregatheringDto.getPregatheringname()=" + addPregatheringDto.getPregatheringname());
			//校验预收款代码是否存在
			PregatheringDto pregathering = pregatheringService.queryAllPregatheringByID(addPregatheringDto.getPregatheringno()+"");
			if(pregathering != null) {
				this.addActionMessage("预收款已经存在！");
				return "checkerror";
			}
			//保存数据
			addPregatheringDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addPregatheringDto.setCreateuid(username);
			String pregatheringno = pregatheringService.insertPregathering(addPregatheringDto);
			this.addActionMessage("添加预收款成功！预收款编号为：" + pregatheringno);
			addPregatheringDto = new PregatheringDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加预收款失败！");
			log.error("addEtbPregatheringAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改预收款页面
	 * @return
	 */
	public String showUpdPregatheringAction() {
		try {
			this.clearMessages();
			System.out.println("pregatheringNo is: "+updatePregatheringNo);
			updatePregatheringDto = pregatheringService.queryPregatheringByID(updatePregatheringNo);
			if(updatePregatheringDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询预收款异常！");
			log.error("showUpdEtbPregatheringAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改预收款
	 * @return
	 */
	public String updPregatheringAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updatePregatheringDto)) {
				return "checkerror";
			}
			System.out.println("pregatheringNo is: "+updatePregatheringDto.getPregatheringno());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updatePregatheringDto.setUpdateuid(username);
			pregatheringService.updatePregathering(updatePregatheringDto);
			this.addActionMessage("修改预收款成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改预收款失败！");
			log.error("updEtbPregatheringAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除预收款
	 * @return
	 */
	public String delPregatheringAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delPregatheringNo)) {
				this.addActionMessage("预收款代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			pregatheringService.deletePregathering(delPregatheringNo, username);
			this.addActionMessage("删除预收款成功！");
			delPregatheringNo = "";
			//刷新页面
			startIndex = 0;
			queryPregathering();
		} catch(Exception e) {
			log.error("delEtbPregatheringAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param pregathering
	 * @return
	 */
	private boolean checkData(PregatheringDto pregathering) {
		if(pregathering == null) {
			this.addActionMessage("预收款不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(pregathering.getPregatheringno()+"")) {
//			this.addActionMessage("预收款代码不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(pregathering.getPregatheringname())) {
			this.addActionMessage("预收款名称不能为空！");
			return false;
		}
		if(pregathering.getPregatheringname().length() > 100) {
			this.addActionMessage("预收款名称不能超过100个字符！");
			return false;
		}
		if(StringUtil.isBlank(pregathering.getAmount())) {
			this.addActionMessage("到款金额不能为空！");
			return false;
		}
		if(!DateUtil.isDate(pregathering.getRegisterdate().toString())) {
			this.addActionMessage("预收款日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(pregathering.getNote()) && pregathering.getNote().length() > 500) {
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

	public List<PregatheringDto> getListPregathering() {
		return listPregathering;
	}

	public void setListPregathering(List<PregatheringDto> listPregathering) {
		this.listPregathering = listPregathering;
	}

	public String getStrPregatheringNoLow() {
		return strPregatheringNoLow;
	}

	public void setStrPregatheringNoLow(String strPregatheringNoLow) {
		this.strPregatheringNoLow = strPregatheringNoLow;
	}

	public String getStrPregatheringNoHigh() {
		return strPregatheringNoHigh;
	}

	public void setStrPregatheringNoHigh(String strPregatheringNoHigh) {
		this.strPregatheringNoHigh = strPregatheringNoHigh;
	}

	public PregatheringDto getAddPregatheringDto() {
		return addPregatheringDto;
	}

	public void setAddPregatheringDto(PregatheringDto addPregatheringDto) {
		this.addPregatheringDto = addPregatheringDto;
	}

	public PregatheringDto getUpdatePregatheringDto() {
		return updatePregatheringDto;
	}

	public void setUpdatePregatheringDto(PregatheringDto updatePregatheringDto) {
		this.updatePregatheringDto = updatePregatheringDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdatePregatheringNo() {
		return updatePregatheringNo;
	}

	public void setUpdatePregatheringNo(String updatePregatheringNo) {
		this.updatePregatheringNo = updatePregatheringNo;
	}

	public String getDelPregatheringNo() {
		return delPregatheringNo;
	}

	public void setDelPregatheringNo(String delPregatheringNo) {
		this.delPregatheringNo = delPregatheringNo;
	}

	public String getQueryPregatheringNo() {
		return queryPregatheringNo;
	}

	public void setQueryPregatheringNo(String queryPregatheringNo) {
		this.queryPregatheringNo = queryPregatheringNo;
	}

	public int getStartIndexPregathering() {
		return startIndexPregathering;
	}

	public void setStartIndexPregathering(int startIndexPregathering) {
		this.startIndexPregathering = startIndexPregathering;
	}

	public Page getPagePregathering() {
		return pagePregathering;
	}

	public void setPagePregathering(Page pagePregathering) {
		this.pagePregathering = pagePregathering;
	}

	public List<PregatheringDto> getPregatheringList() {
		return pregatheringList;
	}

	public void setPregatheringList(List<PregatheringDto> pregatheringList) {
		this.pregatheringList = pregatheringList;
	}

	public String getPregatheringNoLow() {
		return pregatheringNoLow;
	}

	public void setPregatheringNoLow(String pregatheringNoLow) {
		this.pregatheringNoLow = pregatheringNoLow;
	}

	public String getPregatheringNoHigh() {
		return pregatheringNoHigh;
	}

	public void setPregatheringNoHigh(String pregatheringNoHigh) {
		this.pregatheringNoHigh = pregatheringNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrPregatheringName() {
		return strPregatheringName;
	}

	public void setStrPregatheringName(String strPregatheringName) {
		this.strPregatheringName = strPregatheringName;
	}

	public PregatheringService getPregatheringService() {
		return pregatheringService;
	}

	public void setPregatheringService(PregatheringService pregatheringService) {
		this.pregatheringService = pregatheringService;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public String getStrCustomerName() {
		return strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

}
