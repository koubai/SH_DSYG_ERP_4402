package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.action.BaseAction;

/**
 * @name SalesItemDto.java
 * @author Frank
 * @time 2015-5-19下午10:40:15
 * @version 1.0
 */
public class SalesItemDto extends BaseAction {

	private static final long serialVersionUID = 2337982978512345040L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 销售单单号
	 */
	private String salesno;

	/**
	 * 销售单所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 销售主题1
	 */
	private String theme1;

	/**
	 * 销售主题2
	 */
	private String theme2;

	/**
	 * 产品ID号
	 */
	private String productid;
	
	//================产品信息
	/**
	 * 类型
	 */
	private String fieldno;
	
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
	 * 产地
	 */
	private String makearea;
	//==================
	
	//================销售价信息
	/**
	 * 客户名
	 */
	private String customername;

	/**
	 * 订单日期
	 */
	private String bookdate;
	
	/**
	 * 订单日期（显示用）
	 */
	private String showBookdate;
	
	
	/**
	 * 销售数量
	 */
	private BigDecimal quantity;

	/**
	 * 确认出库数量
	 */
	private BigDecimal outquantity;
	
	/**
	 * 预出库数量
	 */
	private BigDecimal beforequantity;

	/**
	 * 未出库数量
	 */
	private BigDecimal remainquantity;

	/**
	 * 销售单价
	 */
	private BigDecimal unitprice;
	
	/**
	 * 销售单价（税后）
	 */
	private BigDecimal taxunitprice;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 金额（含税）
	 */
	private BigDecimal taxamount;
	
	/**
	 * 预出库时间
	 */
	private String plandate;

	/**
	 * 销售
	 */
	private String handler;

	/**
	 * 客户ID
	 */
	private String customerid;

	/**
	 * 确认者
	 */
	private String approverid;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 级别(0-99)
	 */
	private Integer rank;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 包装
	 */
	private String item01;
	
	/**
	 * 预备项目1
	 */
	private String res01;

	/**
	 * 销售模式（0为普通，1为询价，2为询样）
	 */
	private String res02;

	/**
	 * 预备项目3
	 */
	private String res03;

	/**
	 * 预备项目4
	 */
	private String res04;

	/**
	 * 预备项目5
	 */
	private String res05;

	/**
	 * 预备项目6
	 */
	private String res06;

	/**
	 * 预备项目7
	 */
	private String res07;

	/**
	 * 预备项目8
	 */
	private String res08;

	/**
	 * 预备项目9
	 */
	private String res09;

	/**
	 * 预备项目10
	 */
	private String res10;

	/**
	 * 作成者
	 */
	private String createuid;

	/**
	 * 作成时间
	 */
	private Date createdate;

	/**
	 * 更新者
	 */
	private String updateuid;

	/**
	 * 更新时间
	 */
	private Date updatedate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(BigDecimal taxamount) {
		this.taxamount = taxamount;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getApproverid() {
		return approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRes01() {
		return res01;
	}

	public void setRes01(String res01) {
		this.res01 = res01;
	}

	public String getRes02() {
		return res02;
	}

	public void setRes02(String res02) {
		this.res02 = res02;
	}

	public String getRes03() {
		return res03;
	}

	public void setRes03(String res03) {
		this.res03 = res03;
	}

	public String getRes04() {
		return res04;
	}

	public void setRes04(String res04) {
		this.res04 = res04;
	}

	public String getRes05() {
		return res05;
	}

	public void setRes05(String res05) {
		this.res05 = res05;
	}

	public String getRes06() {
		return res06;
	}

	public void setRes06(String res06) {
		this.res06 = res06;
	}

	public String getRes07() {
		return res07;
	}

	public void setRes07(String res07) {
		this.res07 = res07;
	}

	public String getRes08() {
		return res08;
	}

	public void setRes08(String res08) {
		this.res08 = res08;
	}

	public String getRes09() {
		return res09;
	}

	public void setRes09(String res09) {
		this.res09 = res09;
	}

	public String getRes10() {
		return res10;
	}

	public void setRes10(String res10) {
		this.res10 = res10;
	}

	public String getCreateuid() {
		return createuid;
	}

	public void setCreateuid(String createuid) {
		this.createuid = createuid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdateuid() {
		return updateuid;
	}

	public void setUpdateuid(String updateuid) {
		this.updateuid = updateuid;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getSalesno() {
		return salesno;
	}

	public void setSalesno(String salesno) {
		this.salesno = salesno;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getBookdate() {
		return bookdate;
	}

	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}

	public String getShowBookdate() {
		if(bookdate != null && !"".equals(bookdate)) {
			showBookdate = bookdate.substring(0, 10);
		} else {
			showBookdate = "";
		}
		return showBookdate;
	}

	public void setShowBookdate(String showBookdate) {
		this.showBookdate = showBookdate;
	}

	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}

	public BigDecimal getTaxunitprice() {
		return taxunitprice;
	}

	public void setTaxunitprice(BigDecimal taxunitprice) {
		this.taxunitprice = taxunitprice;
	}

	public String getFieldno() {
		return fieldno;
	}

	public void setFieldno(String fieldno) {
		this.fieldno = fieldno;
	}

	public String getMakearea() {
		return makearea;
	}

	public void setMakearea(String makearea) {
		this.makearea = makearea;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOutquantity() {
		return outquantity;
	}

	public void setOutquantity(BigDecimal outquantity) {
		this.outquantity = outquantity;
	}

	public BigDecimal getBeforequantity() {
		return beforequantity;
	}

	public void setBeforequantity(BigDecimal beforequantity) {
		this.beforequantity = beforequantity;
	}

	public BigDecimal getRemainquantity() {
		return remainquantity;
	}

	public void setRemainquantity(BigDecimal remainquantity) {
		this.remainquantity = remainquantity;
	}	
}
