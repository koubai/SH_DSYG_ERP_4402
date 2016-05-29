package com.cn.dsyg.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.DateUtil;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.IssueDto;
import com.cn.dsyg.service.Dict01Service;
import com.cn.dsyg.service.IssueService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 紧急事件Action
 * @author 
 * @time 
 * @version 1.0
 */
public class IssueAction extends BaseAction {

	private static final long serialVersionUID = 7166408421093585753L;

	private static final Logger log = LogManager.getLogger(IssueAction.class);
	
	private IssueService issueService;
	private Dict01Service dict01Service;
	
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
	 * 紧急事件列表
	 */
	private List<IssueDto> listIssue;
	
	/**
	 * 紧急事件编号（起）
	 */
	private String strIdLow;
	
	/**
	 * 紧急事件编号（终）
	 */
	private String strIdHigh;
	
	/**
	 * 事件名称
	 */
	private String strIssueName;
	
	//产品信息
	private String 	productinfo;	
	//销售主题
	private List<Dict01Dto> goodsList;
	//颜色
	private List<Dict01Dto> colorList;
	//单位
	private List<Dict01Dto> unitList;
	//产地
	private List<Dict01Dto> makeareaList;
	//支付方式
	private List<Dict01Dto> payTypeList;
	
	/**
	 * 新增紧急事件对象
	 */
	private IssueDto addIssueDto;
	
	/**
	 * 修改的事件ID
	 */
	private String updateId;
	
	/**
	 * 修改紧急事件对象
	 */
	private IssueDto updateIssueDto;
	
	/**
	 * 删除的事件ID
	 */
	private String delId;
	
	/**
	 * ajax查询条件-事件ID
	 */
	private String queryId;
	
	/**
	 * 控件ID
	 */
	private String strKey;

	/**
	 * 显示紧急事件页面
	 * @return
	 */
	public String showIssueAction() {
		try {
			this.clearMessages();
			strIdLow = "";
			strIdHigh = "";
			strIssueName = "";
			addIssueDto = new IssueDto();
			updateIssueDto = new IssueDto();
			updateId = "";
			delId = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listIssue = new ArrayList<IssueDto>();
			
			queryIssue();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询紧急事件列表
	 * @return
	 */
	public String queryIssueList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			queryIssue();
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
	public String turnIssuePage() {
		try {
			this.clearMessages();
			queryIssue();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有紧急事件列表
	 */
	@SuppressWarnings("unchecked")
	private void queryIssue() {
		//初期化字典数据
		initDictList();
		listIssue = new ArrayList<IssueDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有紧急事件
		this.page.setStartIndex(startIndex);
		page = issueService.queryIssueByPage(page, strIdLow, strIdHigh, strIssueName);
		listIssue = (List<IssueDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加紧急事件页面
	 * @return
	 */
	public String showAddIssueAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			addIssueDto = new IssueDto();
			productinfo = "";
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加紧急事件
	 * @return
	 */
	public String addIssueAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据校验
			if(!checkData(addIssueDto)) {
				return "checkerror";
			}
			log.info("addIssueDto.getId()=" + addIssueDto.getId());
			log.info("addIssueDto.getIssuename()=" + addIssueDto.getIssuename());
			//校验员工编号是否存在
			IssueDto issue = issueService.queryAllIssueByID(addIssueDto.getId()+"");
			if(issue != null) {
				this.addActionMessage("事件ID已经存在！");
				return "checkerror";
			}
			//保存数据
			//addIssueDto.setStatus(Constants.STATUS_NORMAL);
			String user_id = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			if (addIssueDto.getHandlerid().compareTo("")==0)
				addIssueDto.setHandlerid(user_id);
			if (addIssueDto.getStatus()==1)
				addIssueDto.setStatus(2);
			addIssueDto.setCreateuid(user_id);
			String id = issueService.insertIssue(addIssueDto);
			this.addActionMessage("添加紧急事件成功！");
			addIssueDto = new IssueDto();
			productinfo = "";
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加紧急事件失败！");
			log.error("addIssueAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改紧急事件页面
	 * @return
	 */
	public String showUpdIssueAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
//			System.out.println("id is: "+updateId);
			updateIssueDto = issueService.queryIssueByID(updateId);
			if(updateIssueDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
			String fieldname = updateIssueDto.getFieldno();
			if(StringUtil.isNotBlank(fieldname)){
				for(Dict01Dto field: goodsList){
					if(updateIssueDto.getFieldno().equals(field.getCode())){
						fieldname = field.getFieldname();
					}
				}
			}
			String colorname = updateIssueDto.getColor();
			if(StringUtil.isNotBlank(colorname)){
				for(Dict01Dto color: colorList){
					if(updateIssueDto.getColor().equals(color.getCode())){
						colorname = color.getFieldname();
					}
				}
			}
			String makeareaname = updateIssueDto.getMakearea();
			if(StringUtil.isNotBlank(makeareaname)){
				for(Dict01Dto makearea: makeareaList){
					if(updateIssueDto.getMakearea().equals(makearea.getCode())){
						makeareaname = makearea.getFieldname();
					}
				}
			}
			if(StringUtil.isNotBlank(updateIssueDto.getProductid())){
				productinfo = fieldname + " " + updateIssueDto.getTradename() + " " + updateIssueDto.getTypeno() + " " + 
						colorname + " " + updateIssueDto.getItem10() + " " + makeareaname;
			} else {
				productinfo = "";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询紧急事件异常！");
			log.error("showUpdIssueAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改紧急事件
	 * @return
	 */
	public String updIssueAction() {
		try {
			this.clearMessages();
			//初期化字典数据
			initDictList();
			//数据校验
			if(!checkData(updateIssueDto)) {
				return "checkerror";
			}
//			System.out.println("id is: "+updateIssueDto.getId());
			//修改数据
			String user_id = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_ID);
			updateIssueDto.setUpdateuid(user_id);
			issueService.updateIssue(updateIssueDto);
			this.addActionMessage("修改紧急事件成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改紧急事件失败！");
			log.error("updIssueAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除紧急事件
	 * @return
	 */
	public String delIssueAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delId)) {
				this.addActionMessage("事件ID为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			issueService.deleteIssue(delId, username);
			this.addActionMessage("删除紧急事件成功！");
			delId = "";
			//刷新页面
			startIndex = 0;
			queryIssue();
		} catch(Exception e) {
			log.error("delIssueAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 初期化字典数据
	 */
	private void initDictList() {
		//税率
		List<Dict01Dto> listRate = dict01Service.queryDict01ByFieldcode(Constants.DICT_RATE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		if(listRate != null && listRate.size() > 0) {
			common_rate = listRate.get(0).getCode();
		}
		//销售主题
		goodsList = dict01Service.queryDict01ByFieldcode(Constants.DICT_GOODS_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//单位
		unitList = dict01Service.queryDict01ByFieldcode(Constants.DICT_UNIT_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//产地
		makeareaList = dict01Service.queryDict01ByFieldcode(Constants.DICT_MAKEAREA, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//颜色
		colorList = dict01Service.queryDict01ByFieldcode(Constants.DICT_COLOR_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
		//支付方式
		payTypeList = dict01Service.queryDict01ByFieldcode(Constants.DICT_PAY_TYPE, PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_LANGUAGE));
	}
	
	/**
	 * 验证数据格式
	 * @param personal
	 * @return
	 */
	private boolean checkData(IssueDto issue) {
		if(issue == null) {
			this.addActionMessage("紧急事件名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(personal.getUserid()+"")) {
//			this.addActionMessage("员工编号不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(issue.getIssuename())) {
			this.addActionMessage("紧急事件名称不能为空！");
			return false;
		}
		if(issue.getIssuename().length() > 300) {
			this.addActionMessage("紧急事件名称不能超过300个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(issue.getResult()) && issue.getResult().length() > 300) {
			this.addActionMessage("处理结果不能超过500个字符！");
			return false;
		}
		if(StringUtil.isNotBlank(issue.getNote()) && issue.getNote().length() > 500) {
			this.addActionMessage("内容介绍不能超过500个字符！");
			return false;
		}
		if(StringUtil.isBlank(issue.getIssuedate())){
			issue.setIssuedate(null);
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

	public List<IssueDto> getListIssue() {
		return listIssue;
	}

	public void setListIssue(List<IssueDto> listIssue) {
		this.listIssue = listIssue;
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

	public IssueDto getAddIssueDto() {
		return addIssueDto;
	}

	public void setAddIssueDto(IssueDto addIssueDto) {
		this.addIssueDto = addIssueDto;
	}

	public IssueDto getUpdateIssueDto() {
		return updateIssueDto;
	}

	public void setUpdateIssueDto(IssueDto updateIssueDto) {
		this.updateIssueDto = updateIssueDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
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

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrUserName() {
		return strIssueName;
	}

	public void setStrUserName(String strIssueName) {
		this.strIssueName = strIssueName;
	}

	public IssueService getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public Dict01Service getDict01Service() {
		return dict01Service;
	}

	public void setDict01Service(Dict01Service dict01Service) {
		this.dict01Service = dict01Service;
	}

	public String getStrIssueName() {
		return strIssueName;
	}

	public void setStrIssueName(String strIssueName) {
		this.strIssueName = strIssueName;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
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

	public List<Dict01Dto> getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(List<Dict01Dto> payTypeList) {
		this.payTypeList = payTypeList;
	}
}
