package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cn.common.action.BaseAction;

/**
 * @name WarehouserptDto.java
 * @author Frank
 * @time 2015-5-19下午10:18:02
 * @version 1.0
 */
public class WarehouserptDto extends BaseAction {

	private static final long serialVersionUID = -5743344392215386839L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 类型：1为入库单，2为出库单，3为退换货，4为手动录入
	 */
	private Integer warehousetype;

	/**
	 * 入出库单号
	 */
	private String warehouseno;
	
	/**
	 * 仓库名
	 */
	private String warehousename;
	
	/**
	 * 货物信息：产品ID,产品数量,产品金额,备考#产品ID,产品数量,产品金额,备考
	 */
	private String productinfo;
	
	/**
	 * 入出库单对应的货物记录列表
	 */
	private List<ProductDto> listProduct;
	
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
	 * 数据来源单号
	 */
	private String parentid;

	/**
	 * 入出库单总金额（含税）
	 */
	private BigDecimal totaltaxamount;
	
	/**
	 * 入库数量
	 */
	private BigDecimal totalnum;
	
	//损毁数量
	/**
	 * 损毁数量
	 */
	private Integer brokennum;

	/**
	 * 是否损毁（1为损毁）
	 */
	private String hasbroken;

	/**
	 * 入出库单日期
	 */
	private String warehousedate;
	
	/**
	 * 采购日期（显示用）
	 */
	private String showWarehousedate;

	/**
	 * 收货人
	 */
	private String handler;

	/**
	 * 供应商/客户
	 */
	private String supplierid;

	/**
	 * 供应商名/客户
	 */
	private String suppliername;

	/**
	 * 供应商联系电话/客户
	 */
	private String suppliertel;
	
	/**
	 * 供应商传真
	 */
	private String supplierfax;

	/**
	 * 供应商联系人/客户
	 */
	private String suppliermanager;

	/**
	 * 供应商地址/客户
	 */
	private String supplieraddress;

	/**
	 * 供应商邮箱/客户
	 */
	private String suppliermail;

	/**
	 * 快递公司ID
	 */
	private String expressid;

	/**
	 * 快递公司名
	 */
	private String expressname;

	/**
	 * 快递公司联系电话
	 */
	private String expresstel;
	
	/**
	 * 快递公司传真
	 */
	private String expressfax;

	/**
	 * 快递联系人
	 */
	private String expressmanager;

	/**
	 * 快递地址
	 */
	private String expressaddress;

	/**
	 * 快递邮箱
	 */
	private String expressmail;

	/**
	 * 快递单号
	 */
	private String expressno;

	/**
	 * 快递金额(未含税)
	 */
	private BigDecimal expressamount;

	/**
	 * 快递金额(含税)
	 */
	private BigDecimal expresstaxamount;

	/**
	 * 快递备注
	 */
	private String expressnote;

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
	
	//财务记录发票
	private String financeBillno;
	
	//发票号
	private String billno1;
	private String billno2;
	private String billno3;

	//快递单据日期
	private String receiptdate;

	/**
	 * 开票日期（显示用）
	 */
	private String showReceiptdate;

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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public BigDecimal getTotaltaxamount() {
		return totaltaxamount;
	}

	public void setTotaltaxamount(BigDecimal totaltaxamount) {
		this.totaltaxamount = totaltaxamount;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getSuppliertel() {
		return suppliertel;
	}

	public void setSuppliertel(String suppliertel) {
		this.suppliertel = suppliertel;
	}

	public String getSuppliermanager() {
		return suppliermanager;
	}

	public void setSuppliermanager(String suppliermanager) {
		this.suppliermanager = suppliermanager;
	}

	public String getSupplieraddress() {
		return supplieraddress;
	}

	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}

	public String getSuppliermail() {
		return suppliermail;
	}

	public void setSuppliermail(String suppliermail) {
		this.suppliermail = suppliermail;
	}

	public String getExpressid() {
		return expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid = expressid;
	}

	public String getExpressname() {
		return expressname;
	}

	public void setExpressname(String expressname) {
		this.expressname = expressname;
	}

	public String getExpresstel() {
		return expresstel;
	}

	public void setExpresstel(String expresstel) {
		this.expresstel = expresstel;
	}

	public String getExpressmanager() {
		return expressmanager;
	}

	public void setExpressmanager(String expressmanager) {
		this.expressmanager = expressmanager;
	}

	public String getExpressaddress() {
		return expressaddress;
	}

	public void setExpressaddress(String expressaddress) {
		this.expressaddress = expressaddress;
	}

	public String getExpressmail() {
		return expressmail;
	}

	public void setExpressmail(String expressmail) {
		this.expressmail = expressmail;
	}

	public String getExpressno() {
		return expressno;
	}

	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}

	public BigDecimal getExpressamount() {
		return expressamount;
	}

	public void setExpressamount(BigDecimal expressamount) {
		this.expressamount = expressamount;
	}

	public BigDecimal getExpresstaxamount() {
		return expresstaxamount;
	}

	public void setExpresstaxamount(BigDecimal expresstaxamount) {
		this.expresstaxamount = expresstaxamount;
	}

	public String getExpressnote() {
		return expressnote;
	}

	public void setExpressnote(String expressnote) {
		this.expressnote = expressnote;
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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getSupplierfax() {
		return supplierfax;
	}

	public void setSupplierfax(String supplierfax) {
		this.supplierfax = supplierfax;
	}

	public String getWarehousedate() {
		return warehousedate;
	}

	public void setWarehousedate(String warehousedate) {
		this.warehousedate = warehousedate;
	}

	public Integer getBrokennum() {
		return brokennum;
	}

	public void setBrokennum(Integer brokennum) {
		this.brokennum = brokennum;
	}

	public String getHasbroken() {
		return hasbroken;
	}

	public void setHasbroken(String hasbroken) {
		this.hasbroken = hasbroken;
	}

	public String getExpressfax() {
		return expressfax;
	}

	public void setExpressfax(String expressfax) {
		this.expressfax = expressfax;
	}

	public String getShowWarehousedate() {
		if(warehousedate != null && !"".equals(warehousedate)) {
			showWarehousedate = warehousedate.substring(0, 10);
		} else {
			showWarehousedate = "";
		}
		return showWarehousedate;
	}

	public void setShowWarehousedate(String showWarehousedate) {
		this.showWarehousedate = showWarehousedate;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public List<ProductDto> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductDto> listProduct) {
		this.listProduct = listProduct;
	}

	public String getFinanceBillno() {
		return financeBillno;
	}

	public void setFinanceBillno(String financeBillno) {
		this.financeBillno = financeBillno;
	}

	public String getBillno1() {
		return billno1;
	}

	public void setBillno1(String billno1) {
		this.billno1 = billno1;
	}

	public String getBillno2() {
		return billno2;
	}

	public void setBillno2(String billno2) {
		this.billno2 = billno2;
	}

	public String getBillno3() {
		return billno3;
	}

	public void setBillno3(String billno3) {
		this.billno3 = billno3;
	}

	public String getReceiptdate() {
		return receiptdate;
	}

	public void setReceiptdate(String receiptdate) {
		this.receiptdate = receiptdate;
	}
	
	public String getShowReceiptdate() {
		if(receiptdate != null && !"".equals(receiptdate)) {
			showReceiptdate = receiptdate.substring(0, 10);
		} else {
			showReceiptdate = "";
		}
		return showReceiptdate;
	}

	public void setShowReceiptdate(String showReceiptdate) {
		this.showReceiptdate = showReceiptdate;
	}

	public BigDecimal getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(BigDecimal totalnum) {
		this.totalnum = totalnum;
	}

}
