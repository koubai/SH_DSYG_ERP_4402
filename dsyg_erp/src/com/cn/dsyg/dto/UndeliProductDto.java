package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.action.BaseAction;

/**
 * @name 采购单货物表
 * @author Frank
 * @time 2015-5-17下午10:07:53
 * @version 1.0
 */
public class UndeliProductDto extends BaseAction {


	private static final long serialVersionUID = -9149943393576456276L;

	/**
	 * fieldno
	 */
	private String fieldno;

	/**
	 * iotype 出库/入库
	 */
	private String iotype;

	/**
	 * 采购单所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 采购主题1
	 */
	private String theme1;

	/**
	 * 采购主题2
	 */
	private String theme2;

	/**
	 * 产品ID号
	 */
	private Long productid;
	
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
	 * 产地
	 */
	private String makearea;
	
	/**
	 * 单位
	 */
	private String unit;
	

	/**
	 * 未入库数量
	 */
	private BigDecimal remainquantity;

	
	/**
	 * 采购单价（税后）
	 */
	private BigDecimal taxunitprice;

	/**
	 * 金额（含税）
	 */
	private BigDecimal taxamount;
	
	/**
	 * 包装
	 */
	private String item01;
	
	/**
	 * 形式
	 */
	private String item10;


	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getTheme1() {
		return theme1;
	}

	public void setTheme1(String theme1) {
		this.theme1 = theme1;
	}

	public String getTheme2() {
		return theme2;
	}

	public void setTheme2(String theme2) {
		this.theme2 = theme2;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
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

	public String getMakearea() {
		return makearea;
	}

	public void setMakearea(String makearea) {
		this.makearea = makearea;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getRemainquantity() {
		return remainquantity;
	}

	public void setRemainquantity(BigDecimal remainquantity) {
		this.remainquantity = remainquantity;
	}

	public BigDecimal getTaxunitprice() {
		return taxunitprice;
	}

	public void setTaxunitprice(BigDecimal taxunitprice) {
		this.taxunitprice = taxunitprice;
	}

	public BigDecimal getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(BigDecimal taxamount) {
		this.taxamount = taxamount;
	}

	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public String getFieldno() {
		return fieldno;
	}

	public void setFieldno(String fieldno) {
		this.fieldno = fieldno;
	}

	public String getIotype() {
		return iotype;
	}

	public void setIotype(String iotype) {
		this.iotype = iotype;
	}
}
