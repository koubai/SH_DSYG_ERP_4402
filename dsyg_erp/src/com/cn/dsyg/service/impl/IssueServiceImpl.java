package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.IssueDao;
import com.cn.dsyg.dto.IssueDto;
import com.cn.dsyg.service.IssueService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class IssueServiceImpl implements IssueService {
	
	private IssueDao issueDao;
	private ApplicationContext ctx;

	@Override
	public IssueDto queryAllIssueByID(String id) {
		return issueDao.queryAllIssueByID(id);
	}

	@Override
	public Page queryIssueByPage(Page page, String idLow,
			String idHigh, String issueName) {
		idLow = StringUtil.replaceDatabaseKeyword_mysql(idLow);
		//查询总记录数
		int totalCount = issueDao.queryIssueCountByPage(idLow, idHigh, issueName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<IssueDto> list = issueDao.queryIssueByPage(idLow, idHigh,
				issueName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public IssueDto queryIssueByID(String id) {
		return issueDao.queryIssueByID(id);
	}

	@Override
	public List<IssueDto> queryAllIssue() {
		return issueDao.queryAllIssue();
	}

	@Override
	public String insertIssue(IssueDto issue) {
		String id = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		issue.setBelongto(belongto);
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		id = Constants.PERSONAL_NO_PRE + belongto + sdf.format(date) + uuid;
		
		issue.setId(id);*/
		issueDao.insertIssue(issue);
		return id;
	}

	@Override
	public void updateIssue(IssueDto issue) {
		issueDao.updateIssue(issue);
	}

	@Override
	public void deleteIssue(String id, String userName) {
		IssueDto issue = issueDao.queryIssueByID(id);
		if(issue != null) {
			//状态=已删除
			issue.setStatus(Constants.STATUS_DEL);
			issue.setUpdateuid(userName);
			issueDao.updateIssue(issue);
		}
	}
	
	/**
	 * 查询所有未完成的紧急事件
	 * @return
	 */
	public List<IssueDto> queryIssueWorking(){
//		System.out.println("queryIssueWorking DAO:");
		return issueDao.queryIssueWorking();		
	}

	@Override
	public List<IssueDto> queryAllIssueExport(String idLow,
			String idHigh, String issueName) {
		return issueDao.queryAllIssueExport(idLow, idHigh, issueName);
	}

	public IssueDao getissueDao() {
		return issueDao;
	}

	public void setissueDao(IssueDao issueDao) {
		this.issueDao = issueDao;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	public ApplicationContext getCtx() {
		return ctx;
	}

}
