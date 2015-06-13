package com.cn.dsyg.dto;

import com.cn.common.action.BaseAction;

/**
 * 预入库确认DTO
 * @name PurchaseItemOkDto.java
 * @author Frank
 * @time 2015-6-4下午9:53:22
 * @version 1.0
 */
public class WarehouseOkDto extends BaseAction {

	private static final long serialVersionUID = 4006937398518292644L;
	
	/**
	 * 入库数量
	 */
	private Integer quantity;
	
	/**
	 * 供应商ID
	 */
	private Long supplierid;
	
	/**
	 * 供应商名
	 */
	private String suppliername;
	
	/**
	 * 产品ID
	 */
	private Long productid;
	
	/**
	 * 主题
	 */
	private String theme1;
	
	/**
	 * 品名
	 */
	private String tradename;
	
	/**
	 * 规格
	 */
	private String typeno;
	
	/**
	 * 颜色
	 */
	private String color;
	
	/**
	 * 包装
	 */
	private String packaging;
	
	/**
	 * 单位
	 */
	private String unit;

	public Long getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Long supplierid) {
		this.supplierid = supplierid;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getTheme1() {
		return theme1;
	}

	public void setTheme1(String theme1) {
		this.theme1 = theme1;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getTypeno() {
		return typeno;
	}

	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
}
