package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.QaDto;

public interface QaDao {

	/**
	 * 翻页查询数据
	 * @param title
	 * @param company
	 * @param tell
	 * @param status
	 * @param start
	 * @param end
	 * @return
	 */
	public List<QaDto> queryQaByPage(String title, String company, String tell, String status, int start, int end);
	
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
	 * 删除数据
	 * @param id
	 */
	public void deleteQa(String id);
	
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
