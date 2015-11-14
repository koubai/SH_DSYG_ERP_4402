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
import com.cn.dsyg.dto.DocumentDto;
import com.cn.dsyg.service.DocumentService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 文件物品Action
 * @author 
 * @time 
 * @version 1.0
 */
public class DocumentAction extends BaseAction {

	private static final long serialVersionUID = -99617567714090925L;

	private static final Logger log = LogManager.getLogger(DocumentAction.class);
	
	private DocumentService documentService;
	
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
	 * 文件物品列表
	 */
	private List<DocumentDto> listDocument;
	
	/**
	 * 文件物品编号（起）
	 */
	private String strDocumentNoLow;
	
	/**
	 * 文件物品编号（终）
	 */
	private String strDocumentNoHigh;
	
	/**
	 * 文件名
	 */
	private String strDocumentName;
	
	/**
	 * 新增文件物品对象
	 */
	private DocumentDto addDocumentDto;
	
	/**
	 * 修改的文件物品编号
	 */
	private String updateDocumentNo;
	
	/**
	 * 修改文件物品对象
	 */
	private DocumentDto updateDocumentDto;
	
	/**
	 * 删除的文件物品编号
	 */
	private String delDocumentNo;
	
	/**
	 * ajax查询条件-文件物品编号
	 */
	private String queryDocumentNo;
	
	//文件物品查询页面（共通）
	/**
	 * 文件物品信息页码
	 */
	private int startIndexDocument;
	
	/**
	 * 文件物品信息翻页
	 */
	private Page pageDocument;
	
	private List<DocumentDto> documentList;
	
	private String documentNoLow;
	
	private String documentNoHigh;
	
	/**
	 * 控件ID
	 */
	private String strKey;

	/**
	 * 显示文件物品页面
	 * @return
	 */
	public String showEtbDocumentAction() {
		try {
			this.clearMessages();
			strDocumentNoLow = "";
			strDocumentNoHigh = "";
			strDocumentName = "";
			addDocumentDto = new DocumentDto();
			updateDocumentDto = new DocumentDto();
			updateDocumentNo = "";
			delDocumentNo = "";
			
			//默认10条
			intPageSize = 10;
			page = new Page(intPageSize);
			
			startIndex = 0;
			listDocument = new ArrayList<DocumentDto>();
			
			queryEtbDocument();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询文件物品列表
	 * @return
	 */
	public String queryEtbDocumentList() {
		try {
			this.clearMessages();
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			page = new Page(intPageSize);
			startIndex = 0;
			queryEtbDocument();
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
	public String turnEtbDocumentPage() {
		try {
			this.clearMessages();
			queryEtbDocument();
		} catch(Exception e) {
			log.error(e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页查询所有文件物品列表
	 */
	@SuppressWarnings("unchecked")
	private void queryEtbDocument() {
		listDocument = new ArrayList<DocumentDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		//翻页查询所有文件物品
		this.page.setStartIndex(startIndex);
		page = documentService.queryEtbDocumentByPage(page, strDocumentNoLow, strDocumentNoHigh, strDocumentName);
		listDocument = (List<DocumentDto>) page.getItems();
		
		this.setStartIndex(page.getStartIndex());
	}
	
	/**
	 * 显示添加文件物品页面
	 * @return
	 */
	public String showAddEtbDocumentAction() {
		try {
			this.clearMessages();
			addDocumentDto = new DocumentDto();
		} catch(Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 添加文件物品
	 * @return
	 */
	public String addEtbDocumentAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(addDocumentDto)) {
				return "checkerror";
			}
			log.info("addDocumentDto.getDocumentno()=" + addDocumentDto.getDocumentno());
			log.info("addDocumentDto.getDocumentname()=" + addDocumentDto.getDocumentname());
			//校验文件编号是否存在
//			DocumentDto document = documentService.queryAllEtbDocumentByID(addDocumentDto.getDocumentno()+"");
//			if(document != null) {
//				this.addActionMessage("文件编号已经存在！");
//				return "checkerror";
//			}
			//保存数据
			addDocumentDto.setStatus(Constants.STATUS_NORMAL);
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			addDocumentDto.setCreateuid(username);
			String documentno = documentService.insertEtbDocument(addDocumentDto);
			this.addActionMessage("添加文件物品成功！文件编号为：" + documentno);
			addDocumentDto = new DocumentDto();
		} catch(Exception e) {
			this.addActionMessage("系统异常，添加文件物品失败！");
			log.error("addEtbDocumentAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示修改文件物品页面
	 * @return
	 */
	public String showUpdEtbDocumentAction() {
		try {
			this.clearMessages();
			System.out.println("documentNo is: "+updateDocumentNo);
			updateDocumentDto = documentService.queryEtbDocumentByID(updateDocumentNo);
			if(updateDocumentDto == null) {
				this.addActionMessage("该数据不存在！");
				return "checkerror";
			}
		} catch(Exception e) {
			this.addActionMessage("系统错误，查询文件物品异常！");
			log.error("showUpdEtbDocumentAction error:" + e);
			return "checkerror";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改文件物品
	 * @return
	 */
	public String updEtbDocumentAction() {
		try {
			this.clearMessages();
			//数据校验
			if(!checkData(updateDocumentDto)) {
				return "checkerror";
			}
			System.out.println("documentNo is: "+updateDocumentDto.getDocumentno());
			//修改数据
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			updateDocumentDto.setUpdateuid(username);
			documentService.updateEtbDocument(updateDocumentDto);
			this.addActionMessage("修改文件物品成功！");
		} catch(Exception e) {
			this.addActionMessage("系统异常，修改文件物品失败！");
			log.error("updEtbDocumentAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除文件物品
	 * @return
	 */
	public String delEtbDocumentAction() {
		try {
			this.clearMessages();
			if(StringUtil.isBlank(delDocumentNo)) {
				this.addActionMessage("文件编号为空！");
				return "checkerror";
			}
			String username = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
			//删除
			documentService.deleteEtbDocument(delDocumentNo, username);
			this.addActionMessage("删除文件物品成功！");
			delDocumentNo = "";
			//刷新页面
			startIndex = 0;
			queryEtbDocument();
		} catch(Exception e) {
			log.error("delEtbDocumentAction error:" + e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 验证数据格式
	 * @param document
	 * @return
	 */
	private boolean checkData(DocumentDto document) {
		if(document == null) {
			this.addActionMessage("文件名不能为空！");
			return false;
		}
//		if(StringUtil.isBlank(document.getDocumentno()+"")) {
//			this.addActionMessage("文件编号不能为空！");
//			return false;
//		}
		if(StringUtil.isBlank(document.getDocumentname())) {
			this.addActionMessage("文件名不能为空！");
			return false;
		}
		if(document.getDocumentname().length() > 128) {
			this.addActionMessage("文件名不能超过128个字符！");
			return false;
		}
		if(!DateUtil.isDate(document.getRegisterdate().toString())) {
			this.addActionMessage("登记日期格式不正确！");
			return false;
		}
		if(StringUtil.isNotBlank(document.getNote()) && document.getNote().length() > 500) {
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

	public List<DocumentDto> getListDocument() {
		return listDocument;
	}

	public void setListDocument(List<DocumentDto> listDocument) {
		this.listDocument = listDocument;
	}

	public String getStrDocumentNoLow() {
		return strDocumentNoLow;
	}

	public void setStrDocumentNoLow(String strDocumentNoLow) {
		this.strDocumentNoLow = strDocumentNoLow;
	}

	public String getStrDocumentNoHigh() {
		return strDocumentNoHigh;
	}

	public void setStrDocumentNoHigh(String strDocumentNoHigh) {
		this.strDocumentNoHigh = strDocumentNoHigh;
	}

	public DocumentDto getAddDocumentDto() {
		return addDocumentDto;
	}

	public void setAddDocumentDto(DocumentDto addDocumentDto) {
		this.addDocumentDto = addDocumentDto;
	}

	public DocumentDto getUpdateDocumentDto() {
		return updateDocumentDto;
	}

	public void setUpdateDocumentDto(DocumentDto updateDocumentDto) {
		this.updateDocumentDto = updateDocumentDto;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUpdateDocumentNo() {
		return updateDocumentNo;
	}

	public void setUpdateDocumentNo(String updateDocumentNo) {
		this.updateDocumentNo = updateDocumentNo;
	}

	public String getDelDocumentNo() {
		return delDocumentNo;
	}

	public void setDelDocumentNo(String delDocumentNo) {
		this.delDocumentNo = delDocumentNo;
	}

	public String getQueryDocumentNo() {
		return queryDocumentNo;
	}

	public void setQueryDocumentNo(String queryDocumentNo) {
		this.queryDocumentNo = queryDocumentNo;
	}

	public int getStartIndexDocument() {
		return startIndexDocument;
	}

	public void setStartIndexDocument(int startIndexDocument) {
		this.startIndexDocument = startIndexDocument;
	}

	public Page getPageDocument() {
		return pageDocument;
	}

	public void setPageDocument(Page pageDocument) {
		this.pageDocument = pageDocument;
	}

	public List<DocumentDto> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<DocumentDto> documentList) {
		this.documentList = documentList;
	}

	public String getDocumentNoLow() {
		return documentNoLow;
	}

	public void setDocumentNoLow(String documentNoLow) {
		this.documentNoLow = documentNoLow;
	}

	public String getDocumentNoHigh() {
		return documentNoHigh;
	}

	public void setDocumentNoHigh(String documentNoHigh) {
		this.documentNoHigh = documentNoHigh;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public String getStrDocumentName() {
		return strDocumentName;
	}

	public void setStrDocumentName(String strDocumentName) {
		this.strDocumentName = strDocumentName;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}
}
