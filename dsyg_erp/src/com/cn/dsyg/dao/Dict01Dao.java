package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.Dict01Dto;

/**
 * @name Dict01Dao.java
 * @author Frank
 * @time 2014-12-9上午12:38:28
 * @version 1.0
 */
public interface Dict01Dao {
	
	/**
	 * 翻页查询数据
	 * @param fieldcode
	 * @param fieldname
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Dict01Dto> queryDict01ByPage(String fieldcode, String fieldname, String lang, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param fieldcode
	 * @param fieldname
	 * @return
	 */
	public int queryDict01CountByPage(String fieldcode, String fieldname, String lang);

	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public Dict01Dto queryDict01ByID(String id);
	
	/**
	 * 根据逻辑主键查询数据
	 * @param fieldcode
	 * @param code
	 * @param lang
	 * @return
	 */
	public Dict01Dto queryDict01ByLogicId(String fieldcode, String code, String lang);
	
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @return
	 */
	public List<Dict01Dto> queryDict01ByFieldcode(String fieldcode, String lang);
	
	/**
	 * 删除数据
	 * @param id
	 */
	public void deleteDict01(String id);
	
	/**
	 * 新增数据
	 * @param dict01
	 */
	public void insertDict01(Dict01Dto dict01);
	
	/**
	 * 修改数据
	 * @param dict01
	 */
	public void updateDict01(Dict01Dto dict01);
}
