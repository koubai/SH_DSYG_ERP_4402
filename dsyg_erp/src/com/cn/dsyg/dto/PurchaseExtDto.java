package com.cn.dsyg.dto;

/**
 * @name PurchaseDto.java
 * @author Frank
 * @time 2015-5-7下午10:26:56
 * @version 1.0
 */
public class PurchaseExtDto extends PurchaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7311464734448993684L;
	/**
	 * 数量
	 */
	private String quantity;
	/**
	 * 数量合计
	 */
	private String allquantity;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAllquantity() {
		return allquantity;
	}

	public void setAllquantity(String allquantity) {
		this.allquantity = allquantity;
	}
}
