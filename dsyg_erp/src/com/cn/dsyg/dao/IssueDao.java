package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.IssueDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface IssueDao {
	
	/**
	 * 根据ID查询紧急事件（查询所有记录）
	 * @param id
	 * @return
	 */
	public IssueDto queryAllIssueByID(String id);

	/**
	 * 翻页查询紧急事件
	 * @param idLow
	 * @param idHigh
	 * @param issueName
	 * @param start
	 * @param end
	 * @return
	 */
	public List<IssueDto> queryIssueByPage(String idLow,
				String idHigh, String issueName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param idLow
	 * @param idHigh
	 * @param issueName
	 * @return
	 */
	public int queryIssueCountByPage(String idLow, String idHigh, String issueName);
	
	/**
	 * 根据编号查询紧急事件（查询未删除的记录）
	 * @param id
	 * @return
	 */
	public IssueDto queryIssueByID(String id);
	
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
	 */
	public void insertIssue(IssueDto issue);
	
	/**
	 * 修改紧急事件
	 * @param issue
	 */
	public void updateIssue(IssueDto issue);
	
	/**
	 * 查询紧急事件（Excel导出用）
	 * @param idLow
	 * @param idHigh
	 * @param issueName
	 * @return
	 */
	public List<IssueDto> queryAllIssueExport(String idLow, String idHigh, String issueName);
}
