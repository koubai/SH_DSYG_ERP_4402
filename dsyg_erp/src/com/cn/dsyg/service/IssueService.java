package com.cn.dsyg.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.IssueDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface IssueService {
	
	/**
	 * 翻页查询紧急事件
	 * @param page
	 * @param idLow
	 * @param idHigh
	 * @param issueName
	 * @return
	 */
	public Page queryIssueByPage(Page page, String idLow, String idHigh, String issueName);

	/**
	 * 根据员工编号查询紧急事件（查询未删除的记录）
	 * @param id
	 * @return
	 */
	public IssueDto queryIssueByID(String id);
	
	/**
	 * 根据员工编号查询紧急事件（查询所有记录）
	 * @param id
	 * @return
	 */
	public IssueDto queryAllIssueByID(String id);
	
	/**
	 * 查询所有的紧急事件
	 * @return
	 */
	public List<IssueDto> queryAllIssue();
	
	/**
	 * 查询所有未完成的紧急事件
	 * @return
	 */
	public List<IssueDto> queryIssueWorking();
	
	/**
	 * 新增紧急事件
	 * @param issue
	 * @return
	 */
	public String insertIssue(IssueDto issue);
	
	/**
	 * 修改紧急事件
	 * @param issue
	 */
	public void updateIssue(IssueDto issue);
	
	/**
	 * 删除紧急事件
	 * @param id
	 * @param username
	 */
	public void deleteIssue(String id, String username);
	
	/**
	 * 查询紧急事件（Excel导出用）
	 * @param idLow
	 * @param idHigh
	 * @param issueName
	 * @return
	 */
	public List<IssueDto> queryAllIssueExport(String idLow, String idHigh, String issueName);

	public void setCtx(ApplicationContext ctx);
}
