package com.cn.dsyg.dto;

import java.util.Date;
import java.util.List;

import com.cn.common.dto.BaseDto;
import com.cn.common.util.StringUtil;

/**
 * 客户跟踪履历
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerTrackHistDto extends BaseDto {
	
	private static final long serialVersionUID = -7082533551026817869L;

	/**
	 * ID
	 */
	private int id;

	/**
	 * 客户跟踪编号
	 */
	private String trackno;

	/**
	 * 客户名
	 */
	private String customername;
	
	/**
	 * 客户所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 客户来源
	 */
	private String source;

	/**
	 * 客户备注
	 */
	private String note;

	/**
	 * 联系电话1
	 */
	private String customertel1;

	/**
	 * 联系人1
	 */
	private String customermanager1;

	/**
	 * 地址1
	 */
	private String customeraddress1;

	/**
	 * 邮箱1
	 */
	private String customermail1;
	
	private String mail_pr1;
	
	private String mail_suffix1;

	/**
	 * 传真1
	 */
	private String customerfax1;

	/**
	 * 产品ID一览
	 */
	private String product;
	
	/**
	 * 产品ID一览(对应品名，型号，颜色，包装）
	 */
	private String productinfo;
	
	/**
	 * 产品ID一览对应的货物记录列表
	 */
	private List<ProductDto> listProduct;

	/**
	 * 客户类型0：公司开拓，1：个人开拓
	 */
	private int customertype;

	/**
	 * 客户担当（个人开拓时担当ID）
	 */
	private String handlerid;

	/**
	 * 客户担当（个人开拓时担当ID）（显示用）
	 */
	private String handlername;

	/**
	 * 接待日期
	 */
	private String receivedate;

	/**
	 * 接待日期（显示用）
	 */
	private String showReceivedate;
	
	/**
	 * 状态
	 */
	private int status;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCustomertel1() {
		return customertel1;
	}

	public void setCustomertel1(String customertel1) {
		this.customertel1 = customertel1;
	}

	public String getCustomermanager1() {
		return customermanager1;
	}

	public void setCustomermanager1(String customermanager1) {
		this.customermanager1 = customermanager1;
	}

	public String getCustomeraddress1() {
		return customeraddress1;
	}

	public void setCustomeraddress1(String customeraddress1) {
		this.customeraddress1 = customeraddress1;
	}

	public String getCustomermail1() {
		return customermail1;
	}

	public void setCustomermail1(String customermail1) {
		this.customermail1 = customermail1;
	}

	public String getCustomerfax1() {
		return customerfax1;
	}

	public void setCustomerfax1(String customerfax1) {
		this.customerfax1 = customerfax1;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getCustomertype() {
		return customertype;
	}

	public void setCustomertype(int customertype) {
		this.customertype = customertype;
	}

	public String getHandlerid() {
		return handlerid;
	}

	public void setHandlerid(String handlerid) {
		this.handlerid = handlerid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMail_pr1() {
		if(StringUtil.isNotBlank(customermail1)) {
			mail_pr1 = customermail1.substring(0, customermail1.indexOf("@"));
		}
		return mail_pr1;
	}

	public void setMail_pr1(String mail_pr1) {
		this.mail_pr1 = mail_pr1;
	}

	public String getMail_suffix1() {
		if(StringUtil.isNotBlank(customermail1)) {
			mail_suffix1 = customermail1.substring(customermail1.indexOf("@") + 1, customermail1.length());
		}
		return mail_suffix1;
	}

	public void setMail_suffix1(String mail_suffix1) {
		this.mail_suffix1 = mail_suffix1;
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

	public String getReceivedate() {
		return receivedate;
	}

	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

	public String getShowReceivedate() {
		if(receivedate != null && !"".equals(receivedate)) {
			showReceivedate = receivedate.substring(0, 10);
		} else {
			showReceivedate = "";
		}
		return showReceivedate;
	}

	public void setShowReceivedate(String showReceivedate) {
		this.showReceivedate = showReceivedate;
	}

	public String getHandlername() {
		return handlername;
	}

	public void setHandlername(String handlername) {
		this.handlername = handlername;
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

	public String getTrackno() {
		return trackno;
	}

	public void setTrackno(String trackno) {
		this.trackno = trackno;
	}

}
