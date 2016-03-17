package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.action.BaseAction;

/**
 * @name WarehouseDto.java
 * @author Frank
 * @time 2015-5-19下午9:20:14
 * @version 1.0
 */
/**
 * @name WarehouseDto.java
 * @author Frank
 * @time 2015-9-19下午4:38:11
 * @version 1.0
 */
public class WarehouseDto extends BaseAction {

	private static final long serialVersionUID = -1820867715011735185L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 数据来源单号
	 */
	private String parentid;

	/**
	 * 类型
	 */
	private Integer warehousetype;
	
	/**
	 * 仓库
	 */
	private String warehousename;

	/**
	 * 入出库单号
	 */
	private String warehouseno;

	/**
	 * 所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 主题1
	 */
	private String theme1;

	/**
	 * 主题2
	 */
	private String theme2;

	/**
	 * 产品ID号
	 */
	private String productid;

	//
	/**
	 * 产品名
	 */
	private String productname;
	
	/**
	 * 规格
	 */
	private String typeno;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 形式
	 */
	private String packaging;
	
	/**
	 * 包装
	 */
	private String item10;
	
	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 产地
	 */
	private String makearea;

	/**
	 * 入出库数量
	 */
	private BigDecimal quantity;

	/**
	 * 实际入出库数量
	 */
	private BigDecimal actualquantity;

	/**
	 * 单价
	 */
	private BigDecimal unitprice;

	/**
	 * 入出库金额
	 */
	private BigDecimal amount;

	/**
	 * 入出库金额（含税）
	 */
	private BigDecimal taxamount;

	/**
	 * 入库日期
	 */
	private String warehousedate;
	
	/**
	 * 预入库时间
	 */
	private String plandate;

	/**
	 * 收货人
	 */
	private String handler;

	/**
	 * 供应商ID/客户
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
	 * 支付方式
	 */
	private String res01;

	/**
	 * 含税单价
	 */
	private String res02;

	/**
	 * 产地  -> Pei.change 20151118
	 */
	private String res03;

	/**
	 * 成本价 update by frank 20160317
	 */
	private String res04;

	/**
	 * 是否是退换货，1为退换货
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
	 * 特殊订单号
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getWarehousetype() {
		return warehousetype;
	}

	public void setWarehousetype(Integer warehousetype) {
		this.warehousetype = warehousetype;
	}

	public String getWarehouseno() {
		return warehouseno;
	}

	public void setWarehouseno(String warehouseno) {
		this.warehouseno = warehouseno;
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

	public String getWarehousedate() {
		return warehousedate;
	}

	public void setWarehousedate(String warehousedate) {
		this.warehousedate = warehousedate;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getActualquantity() {
		return actualquantity;
	}

	public void setActualquantity(BigDecimal actualquantity) {
		this.actualquantity = actualquantity;
	}

	public String getMakearea() {
		return makearea;
	}

	public void setMakearea(String makearea) {
		this.makearea = makearea;
	}

}
