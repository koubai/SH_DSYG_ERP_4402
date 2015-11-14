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
import com.cn.dsyg.dto.AssetsDto;
import com.cn.dsyg.service.AssetsService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 资产Action
 * @author 
 * @time 
 * @version 1.0
 */
public class AssetsAction extends BaseAction {

	private static final long serialVersionUID = -7389352305629425273L;

	private static final Logger log = LogManager.getLogger(AssetsAction.class);
	
	private AssetsService assetsService;
	
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
	 * 资产列表
	 */
	private List<AssetsDto> listAssets;
	
	/**
	 * 资产编号（起）
	 */
	private String strAssetsNoLow;
	
	/**
	 * 资产编号（终）
	 */
	private String strAssetsNoHigh;
	
	/**
	 * 资产名称
	 */
	private String strAssetsName;
	
	/**
	 * 新增资产对象
	 */
	private AssetsDto addAssetsDto;
	
	/**
	 * 修改的资产编号
	 */
	private String updateAssetsNo;
	
	/**
	 * 修改资产对象
	 */
	private AssetsDto updateAssetsDto;
	
	/**
	 * 删除的资产编号
	 */
	private String delAssetsNo;
	
	/**
	 * ajax查询条件-资产编号
	 */
	private String queryAssetsNo;
	
	//资产查询页面（共通）
	/**
	 * 资产信息页码
	 */
	private int startIndexAssets;
	
	/**
	 * 资产信息翻页
	 */
	private Page pageAssets;
	
	private List<AssetsDto> assetsList;
	
	private String assetsNoLow;
	
	private String assetsNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;

	/**
	 * 显示资产页面
	 * @return
	 */
	public String showEtbAssetsAction() {
		try {
			this.clearMessages();
			strAssetsNoLow = "";
			strAssetsNoHigh = "";
			strAssetsName = "";
			addAssetsDto = new AssetsDto();
			updateAssetsDto = new AssetsDto();
			updateAssetsNo = "";
			delAssetsNo = "";

			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listAssets = new ArrayList<AssetsDto>();
			
			queryEtbAssets();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询资产列表
	 * @return
	 */
	public String queryEtbAssetsList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			queryEtbAssets();
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
	public String turnEtbAssetsPage() {
		try {
			this.clearMessages();
			queryEtbAssets();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有资产列表
	 */
	@SuppressWarnings("unchecked")
	private void queryEtbAssets() {
		listAssets = new ArrayList<AssetsDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有资产
		this.page.setStartIndex(startIndex);
		page = assetsService.queryEtbAssetsByPage(page, strAssetsNoLow, strAssetsNoHigh, strAssetsName);
		listAssets = (List<AssetsDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加资产页面
	 * @return
	 */
	public String showAddEtbAssetsAction() {
		try {
			this.clearMessages();
			addAssetsDto = new AssetsDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加资产
	 * @return
	 */
	public String addEtbAssetsAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addAssetsDto)) {
				return "checkerror";
			}
			log.info("addAssetsDto.getAssetsno()=" + addAssetsDto.getAssetsno());
			log.info("addAssetsDto.getAssetsname()=" + addAssetsDto.getAssetsname());
			//校验资产代码是否存在
			AssetsDto assets = assetsService.queryAllEtbAssetsByID(addAssetsDto.getAssetsno()+"");
			if(assets != null) {
				this.addActionMessage("资产代码已经存在！");
				return "checkerror";
			}
			//保存数据
			addAssetsDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addAssetsDto.setCreateuid(username);
			String assetsno = assetsService.insertEtbAssets(addAssetsDto);
			this.addActionMessage("添加资产成功！资产编号为：" + assetsno);
			addAssetsDto = new AssetsDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加资产失败！");
			log.error("addEtbAssetsAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改资产页面
	 * @return
	 */
	public String showUpdEtbAssetsAction() {
		try {
			this.clearMessages();
			System.out.println("assetsNo is: "+updateAssetsNo);
			updateAssetsDto = assetsService.queryEtbAssetsByID(updateAssetsNo);
			if(updateAssetsDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询资产异常！");
			log.error("showUpdEtbAssetsAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改资产
	 * @return
	 */
	public String updEtbAssetsAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateAssetsDto)) {
				return "checkerror";
			}
			System.out.println("assetsNo is: "+updateAssetsDto.getAssetsno());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateAssetsDto.setUpdateuid(username);
			assetsService.updateEtbAssets(updateAssetsDto);
			this.addActionMessage("修改资产成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改资产失败！");
			log.error("updEtbAssetsAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除资产
	 * @return
	 */
	public String delEtbAssetsAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delAssetsNo)) {
				this.addActionMessage("资产代码为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			assetsService.deleteEtbAssets(delAssetsNo, username);
			this.addActionMessage("删除资产成功！");
			delAssetsNo = "";
			//刷新页面
			startIndex = 0;
			queryEtbAssets();
		} catch(Exception e) {
			log.error("delEtbAssetsAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param assets
	 * @return
	 */
	private boolean checkData(AssetsDto assets) {
		if(assets == null) {
			this.addActionMessage("资产名称不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(assets.getAssetsno()+"")) {
//			this.addActionMessage("资产代码不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(assets.getAssetsname())) {
			this.addActionMessage("资产名称不能为空！");
			return false;
		}
		if(assets.getAssetsname().length() > 40) {
			this.addActionMessage("资产名称不能超过64个字符！");
			return false;
		}
		if(!DateUtil.isDate(assets.getRegisterdate().toString())) {
			this.addActionMessage("登记日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(assets.getNote()) && assets.getNote().length() > 500) {
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

	public List<AssetsDto> getListAssets() {
		return listAssets;
	}

	public void setListAssets(List<AssetsDto> listAssets) {
		this.listAssets = listAssets;
	}

	public String getStrAssetsNoLow() {
		return strAssetsNoLow;
	}

	public void setStrAssetsNoLow(String strAssetsNoLow) {
		this.strAssetsNoLow = strAssetsNoLow;
	}

	public String getStrAssetsNoHigh() {
		return strAssetsNoHigh;
	}

	public void setStrAssetsNoHigh(String strAssetsNoHigh) {
		this.strAssetsNoHigh = strAssetsNoHigh;
	}

	public AssetsDto getAddAssetsDto() {
		return addAssetsDto;
	}

	public void setAddAssetsDto(AssetsDto addAssetsDto) {
		this.addAssetsDto = addAssetsDto;
	}

	public AssetsDto getUpdateAssetsDto() {
		return updateAssetsDto;
	}

	public void setUpdateAssetsDto(AssetsDto updateAssetsDto) {
		this.updateAssetsDto = updateAssetsDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateAssetsNo() {
		return updateAssetsNo;
	}

	public void setUpdateAssetsNo(String updateAssetsNo) {
		this.updateAssetsNo = updateAssetsNo;
	}

	public String getDelAssetsNo() {
		return delAssetsNo;
	}

	public void setDelAssetsNo(String delAssetsNo) {
		this.delAssetsNo = delAssetsNo;
	}

	public String getQueryAssetsNo() {
		return queryAssetsNo;
	}

	public void setQueryAssetsNo(String queryAssetsNo) {
		this.queryAssetsNo = queryAssetsNo;
	}

	public int getStartIndexAssets() {
		return startIndexAssets;
	}

	public void setStartIndexAssets(int startIndexAssets) {
		this.startIndexAssets = startIndexAssets;
	}

	public Page getPageAssets() {
		return pageAssets;
	}

	public void setPageAssets(Page pageAssets) {
		this.pageAssets = pageAssets;
	}

	public List<AssetsDto> getAssetsList() {
		return assetsList;
	}

	public void setAssetsList(List<AssetsDto> assetsList) {
		this.assetsList = assetsList;
	}

	public String getAssetsNoLow() {
		return assetsNoLow;
	}

	public void setAssetsNoLow(String assetsNoLow) {
		this.assetsNoLow = assetsNoLow;
	}

	public String getAssetsNoHigh() {
		return assetsNoHigh;
	}

	public void setAssetsNoHigh(String assetsNoHigh) {
		this.assetsNoHigh = assetsNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrAssetsName() {
		return strAssetsName;
	}

	public void setStrAssetsName(String strAssetsName) {
		this.strAssetsName = strAssetsName;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}
}
