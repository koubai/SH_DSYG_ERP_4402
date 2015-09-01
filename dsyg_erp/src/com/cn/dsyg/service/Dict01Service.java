package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.Dict01Dto;
import com.cn.dsyg.dto.FeatureDto;

/**
 * @name Dict01Service.java
 * @author Frank
 * @time 2014-12-16上午1:30:56
 * @version 1.0
 */
public interface Dict01Service {
	
	/**
	 * 翻页查询数据
	 * @param fieldcode
	 * @param fieldname
	 * @param lang
	 * @param page
	 * @return
	 */
	public Page queryDict01CountByPage(String fieldcode, String fieldname, String lang, Page page);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public Dict01Dto queryDict01ByID(String id);
	
	/**
	 * 根据大类型code查询所有数据
	 * @param fieldcode
	 * @param lang
	 * @return
	 */
	public List<Dict01Dto> queryDict01ByFieldcode(String fieldcode, String lang);
	
	/**
	 * 根据fieldcode查询特征列表
	 * @param fieldcode
	 * @param lang
	 * @return
	 */
	public List<FeatureDto> queryFeatureByFieldcode(String fieldcode, String lang);
	
	/**
	 * 查询大分类数据（没有其他）
	 * @param lang
	 * @return
	 */
	public List<Dict01Dto> queryGoodsNoOther(String lang);
	
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
