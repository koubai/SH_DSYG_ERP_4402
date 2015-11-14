package com.cn.dsyg.dto;

import java.math.BigDecimal;

import com.cn.common.action.BaseAction;

/**
 * 预入出库确认DTO
 * @name PurchaseItemOkDto.java
 * @author Frank
 * @time 2015-6-4下午9:53:22
 * @version 1.0
 */
public class WarehouseInOutOkDto extends BaseAction {

	private static final long serialVersionUID = 4006937398518292644L;
	
	/**
	 * ID集合
	 */
	private String ids;
	
	/**
	 * 订单号/采购单号
	 */
	private String parentid;
	
	/**
	 * 入出库数量
	 */
	private BigDecimal quantity;
	
	/**
	 * 对于出库数量为负数时，用这个变量来展现= -1 * quantity
	 */
	private BigDecimal showQuantity;
	
	/**
	 * 预入库时间
	 */
	private String plandate;
	
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
	 * 用户手输的订单号
	 */
	private String theme2;
	
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
	
	/**
	 * 仓库名
	 */
	private String warehousename;

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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getTheme2() {
		return theme2;
	}

	public void setTheme2(String theme2) {
		this.theme2 = theme2;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getShowQuantity() {
		if(quantity != null) {
			showQuantity = quantity.multiply(new BigDecimal(-1));
		}
		return showQuantity;
	}

	public void setShowQuantity(BigDecimal showQuantity) {
		this.showQuantity = showQuantity;
	}
}
