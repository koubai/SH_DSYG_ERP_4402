package com.cn.dsyg.dao;

import java.util.List;
import com.cn.dsyg.dto.RequestCashDto;

/**
 * @name 
 * @author 
 * @time 
 * @version 1.0
 */
public interface RequestCashDao {
	
	/**
	 * 根据ID查询请款（查询所有记录）
	 * @param requestcashNo
	 * @return
	 */
	public RequestCashDto queryAllRequestCashByID(String requestcashNo);

	/**
	 * 翻页查询请款
	 * @param requestcashNoLow
	 * @param requestcashNoHigh
	 * @param start
	 * @param end
	 * @return
	 */
	public List<RequestCashDto> queryRequestCashByPage(String requestcashNoLow,
				String requestcashNoHigh, String requestcashName, int start, int end);
	
	/**
	 * 查询总记录数
	 * @param requestcashNoLow
	 * @param requestcashNoHigh
	 * @return
	 */
	public int queryRequestCashCountByPage(String requestcashNoLow, String requestcashNoHigh, String requestcashName);
	
	/**
	 * 根据ID查询请款（查询未删除的记录）
	 * @param requestcashNo
	 * @return
	 */
	public RequestCashDto queryRequestCashByID(String requestcashNo);
	
	/**
	 * 查询所有的请款
	 * @return
	 */
	public List<RequestCashDto> queryAllRequestCash();
	
	/**
	 * 新增请款
	 * @param requestcash
	 */
	public void insertRequestCash(RequestCashDto requestcash);
	
	/**
	 * 修改请款
	 * @param requestcash
	 */
	public void updateRequestCash(RequestCashDto requestcash);
	
	/**
	 * 查询请款（Excel导出用）
	 * @param requestcashNoLow
	 * @param requestcashNoHigh
	 * @return
	 */
	public List<RequestCashDto> queryAllRequestCashExport(String requestcashNoLow, String requestcashNoHigh);
}
