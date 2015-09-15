package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.QaDto;

public interface QaService {

	/**
	 * 翻页查询数据
	 * @param title
	 * @param company
	 * @param tell
	 * @param page
	 * @return
	 */
	public Page queryQaByPage(String title, String company, String tell, Page page);
	
	/**
	 * 查询总记录数
	 * @param title
	 * @param company
	 * @param tell
	 * @param status
	 * @return
	 */
	public int queryQaCountByPage(String title, String company, String tell, String status);

	/**
	 * 根据登录ID查询数据
	 * @param id
	 * @return
	 */
	public QaDto queryQaByID(String id);
	
	/**
	 * Q/A明细
	 * @param id
	 * @return
	 */
	public QaDto queryQaDetail(String id);
	
	/**
	 * 删除数据
	 * @param id
	 * @param curruser
	 */
	public void deleteQa(String id, String curruser);
	
	/**
	 * 新增数据
	 * @param qa
	 */
	public void insertQa(QaDto qa);
	
	/**
	 * 修改数据
	 * @param qa
	 */
	public void updateQa(QaDto qa);
}
