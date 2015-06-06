package com.cn.dsyg.service.impl;

import java.util.List;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbDocumentDao;
import com.cn.dsyg.dto.EtbDocumentDto;
import com.cn.dsyg.service.DocumentService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbDocumentServiceImpl implements DocumentService {
	
	private EtbDocumentDao etbDocumentDao;

	@Override
	public EtbDocumentDto queryAllEtbDocumentByID(String ID) {
		return etbDocumentDao.queryAllEtbDocumentByID(ID);
	}

	public EtbDocumentDao getEtbDocumentDao() {
		return etbDocumentDao;
	}

	public void setEtbDocumentDao(EtbDocumentDao etbDocumentDao) {
		this.etbDocumentDao = etbDocumentDao;
	}

	@Override
	public Page queryEtbDocumentByPage(Page page, String documentNoLow,
			String documentNoHigh, String documentName) {
		documentNoLow = StringUtil.replaceDatabaseKeyword_mysql(documentNoLow);
		//查询总记录数
		int totalCount = etbDocumentDao.queryEtbDocumentCountByPage(documentNoLow, documentNoHigh, documentName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<EtbDocumentDto> list = etbDocumentDao.queryEtbDocumentByPage(documentNoLow, documentNoHigh,
				documentName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public EtbDocumentDto queryEtbDocumentByID(String documentNo) {
		return etbDocumentDao.queryEtbDocumentByID(documentNo);
	}

	@Override
	public List<EtbDocumentDto> queryAllEtbDocument() {
		return etbDocumentDao.queryAllEtbDocument();
	}

	@Override
	public void insertEtbDocument(EtbDocumentDto document) {
		etbDocumentDao.insertEtbDocument(document);
	}

	@Override
	public void updateEtbDocument(EtbDocumentDto document) {
		etbDocumentDao.updateEtbDocument(document);
	}

	@Override
	public void deleteEtbDocument(String documentNo, String username) {
		EtbDocumentDto document = etbDocumentDao.queryEtbDocumentByID(documentNo);
		if(document != null) {
			//状态=已删除
			document.setStatus(Constants.STATUS_DEL);
			document.setUpdateuid(username);
			etbDocumentDao.updateEtbDocument(document);
		}
	}

	@Override
	public List<EtbDocumentDto> queryAllEtbDocumentExport(String documentNoLow,
			String documentNoHigh) {
		return etbDocumentDao.queryAllEtbDocumentExport(documentNoLow, documentNoHigh);
	}
}
