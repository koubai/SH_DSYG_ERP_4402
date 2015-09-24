package com.cn.dsyg.service;

import java.util.List;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.RequestCashDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface RequestCashService {
	
	/**
	 * 翻页查询请款
	 * @param page
	 * @param requestcashNoLow
	 * @param requestcashNoHigh
	 * @param requestcashName
	 * @return
	 */
	public Page queryRequestCashByPage(Page page, String requestcashNoLow, String requestcashNoHigh, String requestcashName);

	/**
	 * 根据ID查询请款（查询未删除的记录）
	 * @param requestcashNo
	 * @return
	 */
	public RequestCashDto queryRequestCashByID(String requestcashNo);
	
	/**
	 * 根据ID请款（查询所有记录）
	 * @param requestcashNo
	 * @return
	 */
	public RequestCashDto queryAllRequestCashByID(String requestcashNo);
	
	/**
	 * 查询所有的请款
	 * @return
	 */
	public List<RequestCashDto> queryAllRequestCash();
	
	/**
	 * 新增请款
	 * @param requestcash
	 * @return
	 */
	public String insertRequestCash(RequestCashDto requestcash);
	
	/**
	 * 修改请款
	 * @param requestcash
	 */
	public void updateRequestCash(RequestCashDto requestcash);
	
	/**
	 * 删除请款
	 * @param requestcashNo
	 * @param username
	 */
	public void deleteRequestCash(String requestcashNo, String username);
	
	/**
	 * 查询请款（Excel导出用）
	 * @param requestcashNoLow
	 * @param requestcashNoHigh
	 * @return
	 */
	public List<RequestCashDto> queryAllRequestCashExport(String requestcashNoLow, String requestcashNoHigh);
}
