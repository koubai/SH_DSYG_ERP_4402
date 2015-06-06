package com.cn.dsyg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cn.common.dao.BaseDao;
import com.cn.common.util.StringUtil;
import com.cn.dsyg.dao.EtbDocumentDao;
import com.cn.dsyg.dto.EtbDocumentDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public class EtbDocumentDaoImpl extends BaseDao implements EtbDocumentDao {

	@Override
	public EtbDocumentDto queryAllEtbDocumentByID(String documentNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", documentNo);
		@SuppressWarnings("unchecked")
		List<EtbDocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocumentByID", paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbDocumentDto> queryEtbDocumentByPage(String documentNoLow,
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
		List<EtbDocumentDto> list = getSqlMapClientTemplate().queryForList("queryEtbDocumentByPage", paramMap);
		for (EtbDocumentDto documenttmp : list) {  
			String registerdate = documenttmp.getRegisterdate();
			documenttmp.setRegisterdate(registerdate.substring(0, 10)); 
		}  
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
	public EtbDocumentDto queryEtbDocumentByID(String documentNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID", documentNo);
		@SuppressWarnings("unchecked")
		List<EtbDocumentDto> list = getSqlMapClientTemplate().queryForList("queryEtbDocumentByID", paramMap);
		if(list != null && list.size() > 0) {
			String registerdate = list.get(0).getRegisterdate();
			list.get(0).setRegisterdate(registerdate.substring(0,10));
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EtbDocumentDto> queryAllEtbDocument() {
		@SuppressWarnings("unchecked")
		List<EtbDocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocument");
		return list;
	}

	@Override
	public void insertEtbDocument(EtbDocumentDto document) {
		getSqlMapClientTemplate().insert("insertEtbDocument", document);
	}

	@Override
	public void updateEtbDocument(EtbDocumentDto document) {
		getSqlMapClientTemplate().update("updateEtbDocument", document);
	}

	@Override
	public List<EtbDocumentDto> queryAllEtbDocumentExport(String documentNoLow,
			String documentNoHigh) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ID_LOW", documentNoLow);
		paramMap.put("ID_HIGH", documentNoHigh);
		@SuppressWarnings("unchecked")
		List<EtbDocumentDto> list = getSqlMapClientTemplate().queryForList("queryAllEtbDocumentExport", paramMap);
		return list;
	}
}
