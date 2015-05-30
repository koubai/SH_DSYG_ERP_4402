package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.EtbProductDto;

/**
 * EtbProductDao
 * @Company 盛大游戏
 * @author chenguangquan.frank
 * @version 1.0
 * @create 2015-5-19下午1:47:08
 */
public interface EtbProductDao {

	/**
	 * 翻页查询信息
	 * @param fieldno 主题
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param color 颜色
	 * @param supplierId 供应商
	 * @param status 状态
	 * @param start
	 * @param end
	 * @return
	 */
	public List<EtbProductDto> queryEtbProductByPage(String fieldno, String keyword, String tradename,
			String typeno, String color, String supplierId, String status, int start, int end);
	
	/**
	 * 查询翻页数据数量
	 * @param fieldno 主题
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param color 颜色
	 * @param supplierId 供应商
	 * @param status 状态
	 * @return
	 */
	public int queryEtbProductCountByPage(String fieldno, String keyword, String tradename,
			String typeno, String color, String supplierId, String status);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public EtbProductDto queryEtbProductByID(String id);
	
	/**
	 * 新增数据
	 * @param product
	 */
	public void insertEtbProduct(EtbProductDto product);
	
	/**
	 * 更新数据
	 * @param product
	 */
	public void updateEtbProduct(EtbProductDto product);
}
