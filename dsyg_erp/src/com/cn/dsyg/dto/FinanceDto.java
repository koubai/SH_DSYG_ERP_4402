package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.dto.BaseDto;

/**
 * 财务信息表
 * @name FinanceDto.java
 * @author Frank
 * @time 2015-6-22下午10:25:31
 * @version 1.0
 */
public class FinanceDto extends BaseDto {

	private static final long serialVersionUID = -1662528598616627467L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 财务数据来源：1采购单，2销售单，3快递，4手动录入
	 */
	private Integer financetype;
	
	/**
	 * 主题（仅当financetype=4时有值）
	 */
	private String theme;
	
	/**
	 * 方式：1为收款，2为付款
	 */
	private String mode;

	/**
	 * 0：上海，1：深圳
	 */
	private String belongto;

	/**
	 * 关联单据号
	 */
	private String invoiceid;

	/**
	 * 发票号
	 */
	private String receiptid;

	/**
	 * 开票日期
	 */
	private String receiptdate;
	
	/**
	 * 开票日期（显示用）
	 */
	private String showReceiptdate;
	
	/**
	 * 结算日期
	 */
	private String accountdate;
	
	/**
	 * 结算日期（显示用）
	 */
	private String showAccountdate;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 负责人
	 */
	private String handler;
	
	/**
	 * 负责人名字（显示用）
	 */
	private String handlername;

	/**
	 * 确认者
	 */
	private String approverid;

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
	 * 内容介绍
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

	public Integer getFinancetype() {
		return financetype;
	}

	public void setFinancetype(Integer financetype) {
		this.financetype = financetype;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getReceiptid() {
		return receiptid;
	}

	public void setReceiptid(String receiptid) {
		this.receiptid = receiptid;
	}

	public String getReceiptdate() {
		return receiptdate;
	}

	public void setReceiptdate(String receiptdate) {
		this.receiptdate = receiptdate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getApproverid() {
		return approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
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

	public String getAccountdate() {
		return accountdate;
	}

	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
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

	public String getShowAccountdate() {
		if(accountdate != null && !"".equals(accountdate)) {
			showAccountdate = accountdate.substring(0, 10);
		} else {
			showAccountdate = "";
		}
		return showAccountdate;
	}

	public void setShowAccountdate(String showAccountdate) {
		this.showAccountdate = showAccountdate;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getHandlername() {
		return handlername;
	}

	public void setHandlername(String handlername) {
		this.handlername = handlername;
	}


}
