package com.cn.dsyg.dto;

/**
 * @name 销售单
 * @author Frank
 * @time 2015-5-19下午10:29:28
 * @version 1.0
 */
public class SalesExtDto extends SalesDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6855570042850211325L;

	/**
	 * 数量
	 */
	private String quantity;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
