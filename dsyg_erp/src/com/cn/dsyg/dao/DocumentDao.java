package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.DocumentDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface DocumentDao {
	
	/**
	 * 根据ID查询文件物品（查询所有记录）
	 * @param documentNo
	 * @return
	 */
	public DocumentDto queryAllEtbDocumentByID(String documentNo);

	/**
	 * 翻页查询文件物品
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<DocumentDto> queryEtbDocumentByPage(String documentNoLow,
				String documentNoHigh, String documentName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @return
	 */
	public int queryEtbDocumentCountByPage(String documentNoLow, String documentNoHigh, String documentName);
	
	/**
	 * 根据ID查询文件物品（查询未删除的记录）
	 * @param documentNo
	 * @return
	 */
	public DocumentDto queryEtbDocumentByID(String documentNo);
	
	/**
	 * 查询所有的文件物品
	 * @return
	 */
	public List<DocumentDto> queryAllEtbDocument();
	
	/**
	 * 新增文件物品
	 * @param document
	 */
	public void insertEtbDocument(DocumentDto document);
	
	/**
	 * 修改文件物品
	 * @param document
	 */
	public void updateEtbDocument(DocumentDto document);
	
	/**
	 * 查询文件物品（Excel导出用）
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @return
	 */
	public List<DocumentDto> queryAllEtbDocumentExport(String documentNoLow, String documentNoHigh);
}
