package com.cn.dsyg.service;

import com.cn.common.util.Page;
import com.cn.dsyg.dto.ProductDto;

/**
 * @name ProductService.java
 * @author Frank
 * @time 2015-5-23下午10:39:06
 * @version 1.0
 */
public interface ProductService {

	/**
	 * 翻页查询信息
	 * @param fieldno 主题
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param color 颜色
	 * @param supplierId 供应商
	 * @param status 状态
	 * @param page
	 * @return
	 */
	public Page queryProductByPage(String fieldno, String keyword, String tradename,
			String typeno, String color, String supplierId, String status, Page page);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public ProductDto queryProductByID(String id);
	
	/**
	 * 新增数据
	 * @param product
	 */
	public void insertProduct(ProductDto product);
	
	/**
	 * 更新数据
	 * @param product
	 */
	public void updateProduct(ProductDto product);
	
	/**
	 * 删除数据
	 * @param productId
	 * @param userid
	 */
	public void deleteProduct(String productId, String userid);
}
