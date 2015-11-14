package com.cn.dsyg.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.cn.common.action.BaseAction;
import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.dsyg.dto.QaDto;
import com.cn.dsyg.service.QaService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @name QaAction.java
 * @author Frank
 * @time 2015-2-6上午2:04:15
 * @version 1.0
 */
public class QaAction extends BaseAction {

	private static final Logger log = LogManager.getLogger(QaAction.class);
	private static final long serialVersionUID = 4504216653134620318L;
	
	private QaService qaService;

	//页码
	private int startIndex;
	//翻页page
	private Page page;
	//一页显示数据条数
	private Integer intPageSize;
	
	private QaDto queryQaDto;
	
	private List<QaDto> qaList;
	
	//QA明细
	private String detailQaId;
	private QaDto detailQaDto;
	
	/**
	 * 查询未读Q/A信息记录数
	 * @return
	 * @throws IOException 
	 */
	public String queryQaCountAction() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		String result = "0";
		try {
			this.clearMessages();
			//判断当前用户是否是经理级以上（rank为80以上）
			Integer rank = (Integer) ActionContext.getContext().getSession().get(Constants.SESSION_ROLE_RANK);
			if(rank != null && rank >= Constants.ROLE_RANK_MANAGER) {
				result = "" + qaService.queryQaCountByPage("", "", "", "1");
			}
		} catch(Exception e) {
			log.error("queryQaCountAction error:" + e);
		}
		out = response.getWriter();
		out.write(result);
		out.flush();
		return null;
	}
	
	/**
	 * QA明细页面
	 * @return
	 */
	public String showQaDetailAction() {
		try {
			this.clearMessages();
			detailQaDto = qaService.queryQaDetail(detailQaId);
		} catch(Exception e) {
			log.error("showQaDetailAction error:" + e);
		}
		return SUCCESS;
	}
	
	/**
	 * QA页面
	 * @return
	 */
	public String showQaAction() {
		try {
			this.clearMessages();
			queryQaDto = new QaDto();
			detailQaId = "";
			//默认10条
			intPageSize = 10;
			startIndex = 0;
			qaList = new ArrayList<QaDto>();
			page = new Page(intPageSize);
			
			queryData();
		} catch(Exception e) {
			log.error("showQaAction error:" + e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询QA
	 * @return
	 */
	public String queryQaAction() {
		try {
			this.clearMessages();
			startIndex = 0;
			//默认10条
			if(intPageSize == null) {
				intPageSize = 10;
			}
			if(queryQaDto == null) {
				queryQaDto = new QaDto();
			}
			page = new Page(intPageSize);
			queryData();
		} catch(Exception e) {
			log.error("queryQaAction error:" + e);
		}
		return SUCCESS;
	}
	
	/**
	 * 翻页
	 * @return
	 */
	public String turnQaAction() {
		try {
			this.clearMessages();
			queryData();
		} catch(Exception e) {
			log.error("turnQaAction error:" + e);
		}
		return SUCCESS;
	}
	
	/**
	 * 数据查询
	 */
	@SuppressWarnings("unchecked")
	private void queryData() {
		qaList = new ArrayList<QaDto>();
		if(page == null) {
			page = new Page(intPageSize);
		}
		this.page.setStartIndex(startIndex);
		page = qaService.queryQaByPage(queryQaDto.getTitle(), queryQaDto.getCompany(), queryQaDto.getTell(), page);
		qaList = (List<QaDto>) page.getItems();
		this.setStartIndex(page.getStartIndex());
		detailQaId = "";
	}

	public QaDto getQueryQaDto() {
		return queryQaDto;
	}

	public void setQueryQaDto(QaDto queryQaDto) {
		this.queryQaDto = queryQaDto;
	}

	public QaService getQaService() {
		return qaService;
	}

	public void setQaService(QaService qaService) {
		this.qaService = qaService;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<QaDto> getQaList() {
		return qaList;
	}

	public void setQaList(List<QaDto> qaList) {
		this.qaList = qaList;
	}

	public Integer getIntPageSize() {
		return intPageSize;
	}

	public void setIntPageSize(Integer intPageSize) {
		this.intPageSize = intPageSize;
	}

	public String getDetailQaId() {
		return detailQaId;
	}

	public void setDetailQaId(String detailQaId) {
		this.detailQaId = detailQaId;
	}

	public QaDto getDetailQaDto() {
		return detailQaDto;
	}

	public void setDetailQaDto(QaDto detailQaDto) {
		this.detailQaDto = detailQaDto;
	}
}
