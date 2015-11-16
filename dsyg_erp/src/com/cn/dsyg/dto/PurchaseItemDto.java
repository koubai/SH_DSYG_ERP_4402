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
public class PurchaseItemDto extends BaseAction {

	private static final long serialVersionUID = 365512931487590594L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 采购单单号
	 */
	private String purchaseno;

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
	
	//================产品信息
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
	//==================
	
	//================采购价信息
	/**
	 * 品名
	 */
	private String suppliername;
	
	/**
	 * 采购日期
	 */
	private String purchasedate;
	
	/**
	 * 采购日期（显示用）
	 */
	private String showPurchasedate;
	//==================

	/**
	 * 采购数量
	 */
	private BigDecimal quantity;

	/**
	 * 确认入库数量
	 */
	private BigDecimal inquantity;
	
	/**
	 * 预入库数量
	 */
	private BigDecimal beforequantity;

	/**
	 * 未入库数量
	 */
	private BigDecimal remainquantity;

	/**
	 * 采购单价（税前）
	 */
	private BigDecimal unitprice;
	
	/**
	 * 采购单价（税后）
	 */
	private BigDecimal taxunitprice;

	/**
	 * 金额(未税)
	 */
	private BigDecimal amount;

	/**
	 * 金额（含税）
	 */
	private BigDecimal taxamount;
	
	/**
	 * 预入库时间
	 */
	private String plandate;

	/**
	 * 收货人
	 */
	private String handler;

	/**
	 * 供应商
	 */
	private Long supplierid;

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
	 * 住友代码
	 */
	private String item11;

	/**
	 * 预备项目1
	 */
	private String res01;

	/**
	 * 预备项目2
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
	 * 备注
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

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
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

	public Long getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Long supplierid) {
		this.supplierid = supplierid;
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

	public String getPurchaseno() {
		return purchaseno;
	}

	public void setPurchaseno(String purchaseno) {
		this.purchaseno = purchaseno;
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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getShowPurchasedate() {
		if(purchasedate != null && !"".equals(purchasedate)) {
			showPurchasedate = purchasedate.substring(0, 10);
		} else {
			showPurchasedate = "";
		}
		return showPurchasedate;
	}

	public void setShowPurchasedate(String showPurchasedate) {
		this.showPurchasedate = showPurchasedate;
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

	public String getItem11() {
		return item11;
	}

	public void setItem11(String item11) {
		this.item11 = item11;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getInquantity() {
		return inquantity;
	}

	public void setInquantity(BigDecimal inquantity) {
		this.inquantity = inquantity;
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

	public String getMakearea() {
		return makearea;
	}

	public void setMakearea(String makearea) {
		this.makearea = makearea;
	}
}
