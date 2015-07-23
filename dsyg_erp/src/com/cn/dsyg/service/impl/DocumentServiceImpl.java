package com.cn.dsyg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cn.common.util.Constants;
import com.cn.common.util.Page;
import com.cn.common.util.PropertiesConfig;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.DocumentDao;
import com.cn.dsyg.dto.DocumentDto;
import com.cn.dsyg.service.DocumentService;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class DocumentServiceImpl implements DocumentService {
	
	private DocumentDao documentDao;

	@Override
	public DocumentDto queryAllEtbDocumentByID(String ID) {
		return documentDao.queryAllEtbDocumentByID(ID);
	}

	@Override
	public Page queryEtbDocumentByPage(Page page, String documentNoLow,
			String documentNoHigh, String documentName) {
		documentNoLow = StringUtil.replaceDatabaseKeyword_mysql(documentNoLow);
		//查询总记录数
		int totalCount = documentDao.queryEtbDocumentCountByPage(documentNoLow, documentNoHigh, documentName);
		page.setTotalCount(totalCount);
		if(totalCount % page.getPageSize() > 0) {
			page.setTotalPage(totalCount / page.getPageSize() + 1);
		} else {
			page.setTotalPage(totalCount / page.getPageSize());
		}
		//翻页查询记录
		List<DocumentDto> list = documentDao.queryEtbDocumentByPage(documentNoLow, documentNoHigh,
				documentName, page.getStartIndex() * page.getPageSize(), page.getPageSize());
		page.setItems(list);
		return page;
	}

	@Override
	public DocumentDto queryEtbDocumentByID(String documentNo) {
		return documentDao.queryEtbDocumentByID(documentNo);
	}

	@Override
	public List<DocumentDto> queryAllEtbDocument() {
		return documentDao.queryAllEtbDocument();
	}

	@Override
	public String insertEtbDocument(DocumentDto document) {
		String documentno = "";
		String belongto = PropertiesConfig.getPropertiesValueByKey(Constants.SYSTEM_BELONG);
		document.setBelongto(belongto);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(uuid.length() - 8, uuid.length());
		documentno = Constants.DOCUMENT_NO_PRE + belongto + sdf.format(date) + uuid;
		
		document.setDocumentno(documentno);
		documentDao.insertEtbDocument(document);
		return documentno;
	}

	@Override
	public void updateEtbDocument(DocumentDto document) {
		documentDao.updateEtbDocument(document);
	}

	@Override
	public void deleteEtbDocument(String documentNo, String username) {
		DocumentDto document = documentDao.queryEtbDocumentByID(documentNo);
		if(document != null) {
			//状态=已删除
			document.setStatus(Constants.STATUS_DEL);
			document.setUpdateuid(username);
			documentDao.updateEtbDocument(document);
		}
	}

	@Override
	public List<DocumentDto> queryAllEtbDocumentExport(String documentNoLow,
			String documentNoHigh) {
		return documentDao.queryAllEtbDocumentExport(documentNoLow, documentNoHigh);
	}

	public DocumentDao getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}
}
