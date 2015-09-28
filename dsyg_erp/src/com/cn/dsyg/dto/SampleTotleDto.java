package com.cn.dsyg.dto;

/**
 * @name SampleTotleDto.java
 * @author Frank
 * @time 2015-9-28上午1:04:29
 * @version 1.0
 */
public class SampleTotleDto {

	private String productid;
	
	/**
	 * 品名
	 */
	private String tradename;
	
	private Integer total;

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}
}
