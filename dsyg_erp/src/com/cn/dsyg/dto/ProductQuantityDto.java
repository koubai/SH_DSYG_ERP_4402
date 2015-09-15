package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * @name ProductQuantityDto.java
 * @author Frank
 * @time 2015-9-15下午11:05:26
 * @version 1.0
 */
public class ProductQuantityDto extends BaseDto {

	private static final long serialVersionUID = -7128576729562158250L;

	/**
	 * 产品ID
	 */
	private String productid;
	
	/**
	 * 库存数量
	 */
	private String quantity;

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
