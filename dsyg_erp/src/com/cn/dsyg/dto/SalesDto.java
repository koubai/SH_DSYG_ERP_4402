package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.action.BaseAction;

/**
 * @name 销售单
 * @author Frank
 * @time 2015-5-19下午10:29:28
 * @version 1.0
 */
public class SalesDto extends BaseAction {

	private static final long serialVersionUID = -1315447015946259223L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 销售单号
	 */
	private String salesno;
	
	/**
	 * 退换货对应的RPT单号
	 */
	private String rptno;

	/**
	 * 销售单所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 销售主题
	 */
	private String theme1;

	/**
	 * 销售主题2
	 */
	private String theme2;

	/**
	 * 仓库
	 */
	private String warehouse;

	/**
	 * 客户ID
	 */
	private Long customerid;

	/**
	 * 客户名
	 */
	private String customername;

	/**
	 * 客户联系电话
	 */
	private String customertel;

	/**
	 * 客户联系人
	 */
	private String customermanager;

	/**
	 * 客户地址
	 */
	private String customeraddress;

	/**
	 * 客户邮箱
	 */
	private String customermail;
	
	/**
	 * 传真
	 */
	private String customerfax;

	/**
	 * 销售
	 */
	private String handler;
	
	/**
	 * 销售名字（显示用）
	 */
	private String handlername;

	/**
	 * 订单日期
	 */
	private String bookdate;
	
	/**
	 * 订单日期（显示用）
	 */
	private String showBookdate;
	
	/**
	 * 预入库时间
	 */
	private String plandate;

	/**
	 * 销售金额（不含税）
	 */
	private BigDecimal amount;

	/**
	 * 销售金额（含税）
	 */
	private BigDecimal taxamount;

	/**
	 * 已付金额（含税）
	 */
	private BigDecimal paidamount;

	/**
	 * 未付金额（含税）
	 */
	private BigDecimal unpaidamount;

	/**
	 * 确认者
	 */
	private String approverid;
	
	/**
	 * 确认者名字（显示用）
	 */
	private String approvername;
	
	/**
	 * 销售单对应的产品ID（逗号分割）
	 */
	private String productlist;
	
	/**
	 * 退换货标志：1为退换货，其他为非退换货
	 */
	private String refundflag;

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
	 * 销售模式（0为普通，1为询价，2为询样）
	 */
	private String res02;

	/**
	 * 交货期
	 */
	private String res03;

	/**
	 * 报价有效期
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

	public String getSalesno() {
		return salesno;
	}

	public void setSalesno(String salesno) {
		this.salesno = salesno;
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

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomertel() {
		return customertel;
	}

	public void setCustomertel(String customertel) {
		this.customertel = customertel;
	}

	public String getCustomermanager() {
		return customermanager;
	}

	public void setCustomermanager(String customermanager) {
		this.customermanager = customermanager;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomermail() {
		return customermail;
	}

	public void setCustomermail(String customermail) {
		this.customermail = customermail;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
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

	public BigDecimal getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(BigDecimal paidamount) {
		this.paidamount = paidamount;
	}

	public BigDecimal getUnpaidamount() {
		return unpaidamount;
	}

	public void setUnpaidamount(BigDecimal unpaidamount) {
		this.unpaidamount = unpaidamount;
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

	public String getBookdate() {
		return bookdate;
	}

	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}

	public String getProductlist() {
		return productlist;
	}

	public void setProductlist(String productlist) {
		this.productlist = productlist;
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

	public String getCustomerfax() {
		return customerfax;
	}

	public void setCustomerfax(String customerfax) {
		this.customerfax = customerfax;
	}

	public String getPlandate() {
		return plandate;
	}

	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}

	public String getHandlername() {
		return handlername;
	}

	public void setHandlername(String handlername) {
		this.handlername = handlername;
	}

	public String getApprovername() {
		return approvername;
	}

	public void setApprovername(String approvername) {
		this.approvername = approvername;
	}

	public String getRefundflag() {
		return refundflag;
	}

	public void setRefundflag(String refundflag) {
		this.refundflag = refundflag;
	}

	public String getRptno() {
		return rptno;
	}

	public void setRptno(String rptno) {
		this.rptno = rptno;
	}
}
