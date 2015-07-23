package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.DocumentDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface DocumentService {
	
	/**
	 * 翻页查询文件物品
	 * @param page
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @param documentAddFlag
	 * @param documentName
	 * @return
	 */
	public Page queryEtbDocumentByPage(Page page, String documentNoLow, String documentNoHigh, String documentName);

	/**
	 * 根据ID查询文件物品（查询未删除的记录）
	 * @param documentNo
	 * @return
	 */
	public DocumentDto queryEtbDocumentByID(String documentNo);
	
	/**
	 * 根据ID文件物品（查询所有记录）
	 * @param documentNo
	 * @return
	 */
	public DocumentDto queryAllEtbDocumentByID(String documentNo);
	
	/**
	 * 查询所有的文件物品
	 * @return
	 */
	public List<DocumentDto> queryAllEtbDocument();
	
	/**
	 * 新增文件物品
	 * @param document
	 * @return
	 */
	public String insertEtbDocument(DocumentDto document);
	
	/**
	 * 修改文件物品
	 * @param document
	 */
	public void updateEtbDocument(DocumentDto document);
	
	/**
	 * 删除文件物品
	 * @param documentNo
	 * @param username
	 */
	public void deleteEtbDocument(String documentNo, String username);
	
	/**
	 * 查询文件物品（Excel导出用）
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @return
	 */
	public List<DocumentDto> queryAllEtbDocumentExport(String documentNoLow, String documentNoHigh);
}
