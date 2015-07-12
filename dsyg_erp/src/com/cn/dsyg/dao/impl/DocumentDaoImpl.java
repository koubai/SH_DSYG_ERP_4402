package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.DocumentDao;
import com.cn.dsyg.dto.DocumentDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class DocumentDaoImpl extends BaseDao implements DocumentDao {

	@Override
	public DocumentDto queryAllEtbDocumentByID(String documentNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", documentNo);
		@SuppressWarnings("unchecked")
		List<DocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocumentByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DocumentDto> queryEtbDocumentByPage(String documentNoLow,
			String documentNoHigh, String documentName,
			int start, int end) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若文件物品名称存在，则忽略文件物品代码按文件物品名称来查询。
		if(StringUtil.isNotBlank(documentName)) {
			paramMap.put("DOCUMENT_NAME", StringUtil.replaceDatabaseKeyword_mysql(documentName));
		} else {
			paramMap.put("ID_LOW", documentNoLow);
			paramMap.put("ID_HIGH", documentNoHigh);
		}
		paramMap.put("start", start);
		paramMap.put("end", end);
		@SuppressWarnings("unchecked")
		List<DocumentDto> list = getSqlMapClientTemplate().queryForList("queryEtbDocumentByPage", paramMap);
		return list;
	}

	@Override
	public int queryEtbDocumentCountByPage(String documentNoLow,
			String documentNoHigh, String documentName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//这里按照需求，若文件名称存在，则忽略文件编号按文件名来查询。
		if(StringUtil.isNotBlank(documentName)) {
			paramMap.put("DOCUMENT_NAME", StringUtil.replaceDatabaseKeyword_mysql(documentName));
		} else {
			paramMap.put("ID_LOW", documentNoLow);
			paramMap.put("ID_HIGH", documentNoHigh);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("queryEtbDocumentCountByPage", paramMap);
	}

	@Override
	public DocumentDto queryEtbDocumentByID(String documentNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", documentNo);
		@SuppressWarnings("unchecked")
		List<DocumentDto> list = getSqlMapClientTemplate().queryForList("queryEtbDocumentByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DocumentDto> queryAllEtbDocument() {
		@SuppressWarnings("unchecked")
		List<DocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocument");
		return list;
	}

	@Override
	public void insertEtbDocument(DocumentDto document) {
		getSqlMapClientTemplate().insert("insertEtbDocument", document);
	}

	@Override
	public void updateEtbDocument(DocumentDto document) {
		getSqlMapClientTemplate().update("updateEtbDocument", document);
	}

	@Override
	public List<DocumentDto> queryAllEtbDocumentExport(String documentNoLow,
			String documentNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", documentNoLow);
		paramMap.put("ID_HIGH", documentNoHigh);
		@SuppressWarnings("unchecked")
		List<DocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocumentExport", paramMap);
		return list;
	}
}
