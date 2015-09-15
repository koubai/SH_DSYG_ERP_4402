package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.QaDao;
import com.cn.dsyg.dto.QaDto;
import com.cn.dsyg.service.QaService;

/**
 * @name QaServiceImpl.java
 * @author Frank
 * @time 2015-2-5上午12:38:29
 * @version 1.0
 */
public class QaServiceImpl implements QaService {
	
	private QaDao qaDao;
	
	@Override
	public int queryQaCountByPage(String title, String company, String tell,
			String status) {
		return qaDao.queryQaCountByPage(title, company, tell, status);
	}
	
	@Override
	public QaDto queryQaDetail(String id) {
		QaDto qa = qaDao.queryQaByID(id);
		//状态=已读
		qa.setStatus("2");
		qaDao.updateQa(qa);
		return qa;
	}

	@Override
	public Page queryQaByPage(String title, String company, String tell,
			Page page) {
		title = StringUtil.replaceDatabaseKeyword_mysql(title);
		company = StringUtil.replaceDatabaseKeyword_mysql(company);
		//查询总记录数，这里只查询状态=1的
		int totalCount = qaDao.queryQaCountByPage(title, company, tell, "");
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录，这里只查询状态=1的
		List<QaDto> list = qaDao.queryQaByPage(title, company, tell, "",
				page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public QaDto queryQaByID(String id) {
		return qaDao.queryQaByID(id);
	}

	@Override
	public void deleteQa(String id, String curruser) {
		QaDto qa = qaDao.queryQaByID(id);
		qa.setStatus("" + Constants.STATUS_DEL);
		qa.setUpdateuid(curruser);
		qaDao.updateQa(qa);
		//qaDao.deleteQa(id);
	}

	@Override
	public void insertQa(QaDto qa) {
		qaDao.insertQa(qa);
	}

	@Override
	public void updateQa(QaDto qa) {
		qaDao.updateQa(qa);
	}

	public QaDao getQaDao() {
		return qaDao;
	}

	public void setQaDao(QaDao qaDao) {
		this.qaDao = qaDao;
	}
}
