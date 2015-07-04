package com.cn.dsyg.dto;

import java.util.Date;

import com.cn.common.dto.BaseDto;
import com.cn.common.util.StringUtil;

/**
 * 客户
 * @author 
 * @time 
 * @version 1.0
 */
public class CustomerDto extends BaseDto {

	private static final long serialVersionUID = -1090654494027541951L;

	/**
	 * 客户ID
	 */
	private int id;

	/**
	 * 客户名
	 */
	private String customername;

	/**
	 * checkbox
	 */
	private String checkKey;
	private String test;
	
	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
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

	public String getCustomertel2() {
		return customertel2;
	}

	public void setCustomertel2(String customertel2) {
		this.customertel2 = customertel2;
	}

	public String getCustomermanager2() {
		return customermanager2;
	}

	public void setCustomermanager2(String customermanager2) {
		this.customermanager2 = customermanager2;
	}

	public String getCustomeraddress2() {
		return customeraddress2;
	}

	public void setCustomeraddress2(String customeraddress2) {
		this.customeraddress2 = customeraddress2;
	}

	public String getCustomermail2() {
		return customermail2;
	}

	public void setCustomermail2(String customermail2) {
		this.customermail2 = customermail2;
	}

	public String getCustomerfax2() {
		return customerfax2;
	}

	public void setCustomerfax2(String customerfax2) {
		this.customerfax2 = customerfax2;
	}

	public String getCustomertel3() {
		return customertel3;
	}

	public void setCustomertel3(String customertel3) {
		this.customertel3 = customertel3;
	}

	public String getCustomermanager3() {
		return customermanager3;
	}

	public void setCustomermanager3(String customermanager3) {
		this.customermanager3 = customermanager3;
	}

	public String getCustomeraddress3() {
		return customeraddress3;
	}

	public void setCustomeraddress3(String customeraddress3) {
		this.customeraddress3 = customeraddress3;
	}

	public String getCustomermail3() {
		return customermail3;
	}

	public void setCustomermail3(String customermail3) {
		this.customermail3 = customermail3;
	}

	public String getCustomerfax3() {
		return customerfax3;
	}

	public void setCustomerfax3(String customerfax3) {
		this.customerfax3 = customerfax3;
	}

	public String getCustomertel4() {
		return customertel4;
	}

	public void setCustomertel4(String customertel4) {
		this.customertel4 = customertel4;
	}

	public String getCustomermanager4() {
		return customermanager4;
	}

	public void setCustomermanager4(String customermanager4) {
		this.customermanager4 = customermanager4;
	}

	public String getCustomeraddress4() {
		return customeraddress4;
	}

	public void setCustomeraddress4(String customeraddress4) {
		this.customeraddress4 = customeraddress4;
	}

	public String getCustomermail4() {
		return customermail4;
	}

	public void setCustomermail4(String customermail4) {
		this.customermail4 = customermail4;
	}

	public String getCustomerfax4() {
		return customerfax4;
	}

	public void setCustomerfax4(String customerfax4) {
		this.customerfax4 = customerfax4;
	}

	public String getCustomertel5() {
		return customertel5;
	}

	public void setCustomertel5(String customertel5) {
		this.customertel5 = customertel5;
	}

	public String getCustomermanager5() {
		return customermanager5;
	}

	public void setCustomermanager5(String customermanager5) {
		this.customermanager5 = customermanager5;
	}

	public String getCustomeraddress5() {
		return customeraddress5;
	}

	public void setCustomeraddress5(String customeraddress5) {
		this.customeraddress5 = customeraddress5;
	}

	public String getCustomermail5() {
		return customermail5;
	}

	public void setCustomermail5(String customermail5) {
		this.customermail5 = customermail5;
	}

	public String getCustomerfax5() {
		return customerfax5;
	}

	public void setCustomerfax5(String customerfax5) {
		this.customerfax5 = customerfax5;
	}

	public int getCustomertype() {
		return customertype;
	}

	public void setCustomertype(int customertype) {
		this.customertype = customertype;
	}

	/**
	 * 客户所属地（以后可能分上海和深圳）
	 */
	private String belongto;

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

	/**
	 * 传真1
	 */
	private String customerfax1;

	/**
	 * 联系电话2
	 */
	private String customertel2;

	/**
	 * 联系人2
	 */
	private String customermanager2;

	/**
	 * 地址2
	 */
	private String customeraddress2;
	
	/**
	 * 邮箱2
	 */
	private String customermail2;

	/**
	 * 传真2
	 */
	private String customerfax2;

	/**
	 * 联系电话3
	 */
	private String customertel3;

	/**
	 * 联系人3
	 */
	private String customermanager3;

	/**
	 * 地址3
	 */
	private String customeraddress3;
	
	/**
	 * 邮箱3
	 */
	private String customermail3;

	/**
	 * 传真3
	 */
	private String customerfax3;

	/**
	 * 联系电话4
	 */
	private String customertel4;

	/**
	 * 联系人4
	 */
	private String customermanager4;

	/**
	 * 地址4
	 */
	private String customeraddress4;
	
	/**
	 * 邮箱4
	 */
	private String customermail4;

	/**
	 * 传真4
	 */
	private String customerfax4;

	/**
	 * 联系电话5
	 */
	private String customertel5;

	/**
	 * 联系人5
	 */
	private String customermanager5;

	/**
	 * 地址5
	 */
	private String customeraddress5;
	
	/**
	 * 邮箱5
	 */
	private String customermail5;

	/**
	 * 传真5
	 */
	private String customerfax5;
	
	private String mail_pr1;
	
	private String mail_suffix1;
	
	private String mail_pr2;
	
	private String mail_suffix2;
	
	private String mail_pr3;
	
	private String mail_suffix3;
	
	private String mail_pr4;
	
	private String mail_suffix4;
	
	private String mail_pr5;
	
	private String mail_suffix5;

	/**
	 * 客户类型
	 */
	private int customertype;
	
	/**
	 * 客户担当
	 */
	private String handlerid;
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHandlerid() {
		return handlerid;
	}

	public void setHandlerid(String handlerid) {
		this.handlerid = handlerid;
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

	public String getMail_pr2() {
		if(StringUtil.isNotBlank(customermail2)) {
			mail_pr2 = customermail2.substring(0, customermail2.indexOf("@"));
		}
		return mail_pr2;
	}

	public void setMail_pr2(String mail_pr2) {
		this.mail_pr2 = mail_pr2;
	}

	public String getMail_suffix2() {
		if(StringUtil.isNotBlank(customermail2)) {
			mail_suffix2 = customermail2.substring(customermail2.indexOf("@") + 1, customermail2.length());
		}
		return mail_suffix2;
	}

	public void setMail_suffix2(String mail_suffix2) {
		this.mail_suffix2 = mail_suffix2;
	}

	public String getMail_pr3() {
		if(StringUtil.isNotBlank(customermail3)) {
			mail_pr3 = customermail3.substring(0, customermail3.indexOf("@"));
		}
		return mail_pr3;
	}

	public void setMail_pr3(String mail_pr3) {
		this.mail_pr3 = mail_pr3;
	}

	public String getMail_suffix3() {
		if(StringUtil.isNotBlank(customermail3)) {
			mail_suffix3 = customermail3.substring(customermail3.indexOf("@") + 1, customermail3.length());
		}
		return mail_suffix3;
	}

	public void setMail_suffix3(String mail_suffix3) {
		this.mail_suffix3 = mail_suffix3;
	}

	public String getMail_pr4() {
		if(StringUtil.isNotBlank(customermail4)) {
			mail_pr4 = customermail4.substring(0, customermail4.indexOf("@"));
		}
		return mail_pr4;
	}

	public void setMail_pr4(String mail_pr4) {
		this.mail_pr4 = mail_pr4;
	}

	public String getMail_suffix4() {
		if(StringUtil.isNotBlank(customermail4)) {
			mail_suffix4 = customermail4.substring(customermail4.indexOf("@") + 1, customermail4.length());
		}
		return mail_suffix4;
	}

	public void setMail_suffix4(String mail_suffix4) {
		this.mail_suffix4 = mail_suffix4;
	}

	public String getMail_pr5() {
		if(StringUtil.isNotBlank(customermail5)) {
			mail_pr5 = customermail5.substring(0, customermail5.indexOf("@"));
		}
		return mail_pr5;
	}

	public void setMail_pr5(String mail_pr5) {
		this.mail_pr5 = mail_pr5;
	}

	public String getMail_suffix5() {
		if(StringUtil.isNotBlank(customermail5)) {
			mail_suffix5 = customermail5.substring(customermail5.indexOf("@") + 1, customermail5.length());
		}
		return mail_suffix5;
	}

	public void setMail_suffix5(String mail_suffix5) {
		this.mail_suffix5 = mail_suffix5;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public String getCheckKey() {
		return checkKey;
	}

	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	

}
