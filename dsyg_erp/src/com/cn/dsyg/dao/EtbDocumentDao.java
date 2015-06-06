package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.EtbDocumentDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface EtbDocumentDao {
	
	/**
	 * 根据ID查询文件物品（查询所有记录）
	 * @param documentNo
	 * @return
	 */
	public EtbDocumentDto queryAllEtbDocumentByID(String documentNo);

	/**
	 * 翻页查询文件物品
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbDocumentDto> queryEtbDocumentByPage(String documentNoLow,
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
	public EtbDocumentDto queryEtbDocumentByID(String documentNo);
	
	/**
	 * 查询所有的文件物品
	 * @return
	 */
	public List<EtbDocumentDto> queryAllEtbDocument();
	
	/**
	 * 新增文件物品
	 * @param document
	 */
	public void insertEtbDocument(EtbDocumentDto document);
	
	/**
	 * 修改文件物品
	 * @param document
	 */
	public void updateEtbDocument(EtbDocumentDto document);
	
	/**
	 * 查询文件物品（Excel导出用）
	 * @param documentNoLow
	 * @param documentNoHigh
	 * @return
	 */
	public List<EtbDocumentDto> queryAllEtbDocumentExport(String documentNoLow, String documentNoHigh);
}
