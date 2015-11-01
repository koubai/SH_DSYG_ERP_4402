package com.cn.dsyg.dao;

import java.util.List;

import com.cn.dsyg.dto.ProductDto;

/**
 * ProductDao
 * @Company 盛大游戏
 * @author chenguangquan.frank
 * @version 1.0
 * @create 2015-5-19下午1:47:08
 */
public interface ProductDao {

	/**
	 * 翻页查询信息
	 * @param fieldno 主题
	 * @param item01 包装
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
	public List<ProductDto> queryProductByPage(String fieldno, String item01, String keyword, String packaging, String tradename,
			String typeno, String color, String supplierId, String status, int start, int end);
	
	/**
	 * 翻页查询信息
	 * @param fieldno 主题
	 * @param item01 包装
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param belongto 
	 * @param status 状态
	 * @param start
	 * @param end
	 * @return
	 */
	public List<ProductDto> queryProductCostCheckByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String belongto, String status, int start, int end);

	/**
	 * 翻页查询信息
	 * @param fieldno 主题
	 * @param item01 包装
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param belongto 
	 * @param status 状态
	 * @return
	 */
	public List<ProductDto> queryProductCostToExport(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String belongto, String status);

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
	public int queryProductCountByPage(String fieldno, String item01, String keyword, String packaging, String tradename,
			String typeno, String color, String supplierId, String status);
	
	/**
	 * 查询翻页数据数量
	 * @param fieldno 主题
	 * @param keyword 关键字
	 * @param tradename 品名
	 * @param typeno 规格
	 * @param color 颜色
	 * @param supplierId 供应商
	 * @param belongto
	 * @param status 状态
	 * @return
	 */
	public int queryProductCostCountByPage(String fieldno, String item01, String keyword, String tradename,
			String typeno, String color, String supplierId, String belongto, String status);
	
	/**
	 * 根据产品名称，型号，颜色（逻辑主键）查询产品
	 * @param tradename
	 * @param typeno
	 * @param color
	 * @return
	 */
	public ProductDto queryProductByLogicId(String tradename, String typeno, String color, String item10, String packaging, String makearea);

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
}
