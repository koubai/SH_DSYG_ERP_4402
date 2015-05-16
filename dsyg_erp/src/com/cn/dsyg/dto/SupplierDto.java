package com.cn.dsyg.dto;

import java.util.Date;

import com.cn.common.dto.BaseDto;
import com.cn.common.util.StringUtil;

/**
 * 供应商
 * @author 
 * @time 
 * @version 1.0
 */
public class SupplierDto extends BaseDto {

	private static final long serialVersionUID = 6301088564902082330L;

	/**
	 * 供应商ID
	 */
	private int id;

	/**
	 * 供应商名
	 */
	private String suppliername;
	
	/**
	 * 供应商所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 供应商备注
	 */
	private String note;

	/**
	 * 联系电话1
	 */
	private String suppliertel1;

	/**
	 * 联系人1
	 */
	private String suppliermanager1;

	/**
	 * 地址1
	 */
	private String supplieraddress1;
	
	/**
	 * 邮箱1
	 */
	private String suppliermail1;

	/**
	 * 传真1
	 */
	private String supplierfax1;

	/**
	 * 联系电话2
	 */
	private String suppliertel2;

	/**
	 * 联系人2
	 */
	private String suppliermanager2;

	/**
	 * 地址2
	 */
	private String supplieraddress2;
	
	/**
	 * 邮箱2
	 */
	private String suppliermail2;

	/**
	 * 传真2
	 */
	private String supplierfax2;

	/**
	 * 联系电话3
	 */
	private String suppliertel3;

	/**
	 * 联系人3
	 */
	private String suppliermanager3;

	/**
	 * 地址3
	 */
	private String supplieraddress3;
	
	/**
	 * 邮箱3
	 */
	private String suppliermail3;

	/**
	 * 传真3
	 */
	private String supplierfax3;

	/**
	 * 联系电话4
	 */
	private String suppliertel4;

	/**
	 * 联系人4
	 */
	private String suppliermanager4;

	/**
	 * 地址4
	 */
	private String supplieraddress4;
	
	/**
	 * 邮箱4
	 */
	private String suppliermail4;

	/**
	 * 传真4
	 */
	private String supplierfax4;

	/**
	 * 联系电话5
	 */
	private String suppliertel5;

	/**
	 * 联系人5
	 */
	private String suppliermanager5;

	/**
	 * 地址5
	 */
	private String supplieraddress5;
	
	/**
	 * 邮箱5
	 */
	private String suppliermail5;

	/**
	 * 传真5
	 */
	private String supplierfax5;
	
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
	 * 供应商类型
	 */
	private int suppliertype;
	
	/**
	 * 供应商担当
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

	public String getSuppliertel1() {
		return suppliertel1;
	}

	public void setSuppliertel1(String suppliertel1) {
		this.suppliertel1 = suppliertel1;
	}

	public String getSuppliermanager1() {
		return suppliermanager1;
	}

	public void setSuppliermanager1(String suppliermanager1) {
		this.suppliermanager1 = suppliermanager1;
	}

	public String getSupplieraddress1() {
		return supplieraddress1;
	}

	public void setSupplieraddress1(String supplieraddress1) {
		this.supplieraddress1 = supplieraddress1;
	}

	public String getSuppliermail1() {
		return suppliermail1;
	}

	public void setSuppliermail1(String suppliermail1) {
		this.suppliermail1 = suppliermail1;
	}

	public String getSupplierfax1() {
		return supplierfax1;
	}

	public void setSupplierfax1(String supplierfax1) {
		this.supplierfax1 = supplierfax1;
	}

	public String getSuppliertel2() {
		return suppliertel2;
	}

	public void setSuppliertel2(String suppliertel2) {
		this.suppliertel2 = suppliertel2;
	}

	public String getSuppliermanager2() {
		return suppliermanager2;
	}

	public void setSuppliermanager2(String suppliermanager2) {
		this.suppliermanager2 = suppliermanager2;
	}

	public String getSupplieraddress2() {
		return supplieraddress2;
	}

	public void setSupplieraddress2(String supplieraddress2) {
		this.supplieraddress2 = supplieraddress2;
	}

	public String getSuppliermail2() {
		return suppliermail2;
	}

	public void setSuppliermail2(String suppliermail2) {
		this.suppliermail2 = suppliermail2;
	}

	public String getSupplierfax2() {
		return supplierfax2;
	}

	public void setSupplierfax2(String supplierfax2) {
		this.supplierfax2 = supplierfax2;
	}

	public String getSuppliertel3() {
		return suppliertel3;
	}

	public void setSuppliertel3(String suppliertel3) {
		this.suppliertel3 = suppliertel3;
	}

	public String getSuppliermanager3() {
		return suppliermanager3;
	}

	public void setSuppliermanager3(String suppliermanager3) {
		this.suppliermanager3 = suppliermanager3;
	}

	public String getSupplieraddress3() {
		return supplieraddress3;
	}

	public void setSupplieraddress3(String supplieraddress3) {
		this.supplieraddress3 = supplieraddress3;
	}

	public String getSuppliermail3() {
		return suppliermail3;
	}

	public void setSuppliermail3(String suppliermail3) {
		this.suppliermail3 = suppliermail3;
	}

	public String getSupplierfax3() {
		return supplierfax3;
	}

	public void setSupplierfax3(String supplierfax3) {
		this.supplierfax3 = supplierfax3;
	}

	public String getSuppliertel4() {
		return suppliertel4;
	}

	public void setSuppliertel4(String suppliertel4) {
		this.suppliertel4 = suppliertel4;
	}

	public String getSuppliermanager4() {
		return suppliermanager4;
	}

	public void setSuppliermanager4(String suppliermanager4) {
		this.suppliermanager4 = suppliermanager4;
	}

	public String getSupplieraddress4() {
		return supplieraddress4;
	}

	public void setSupplieraddress4(String supplieraddress4) {
		this.supplieraddress4 = supplieraddress4;
	}

	public String getSuppliermail4() {
		return suppliermail4;
	}

	public void setSuppliermail4(String suppliermail4) {
		this.suppliermail4 = suppliermail4;
	}

	public String getSupplierfax4() {
		return supplierfax4;
	}

	public void setSupplierfax4(String supplierfax4) {
		this.supplierfax4 = supplierfax4;
	}

	public String getSuppliertel5() {
		return suppliertel5;
	}

	public void setSuppliertel5(String suppliertel5) {
		this.suppliertel5 = suppliertel5;
	}

	public String getSuppliermanager5() {
		return suppliermanager5;
	}

	public void setSuppliermanager5(String suppliermanager5) {
		this.suppliermanager5 = suppliermanager5;
	}

	public String getSupplieraddress5() {
		return supplieraddress5;
	}

	public void setSupplieraddress5(String supplieraddress5) {
		this.supplieraddress5 = supplieraddress5;
	}

	public String getSuppliermail5() {
		return suppliermail5;
	}

	public void setSuppliermail5(String suppliermail5) {
		this.suppliermail5 = suppliermail5;
	}

	public String getSupplierfax5() {
		return supplierfax5;
	}

	public void setSupplierfax5(String supplierfax5) {
		this.supplierfax5 = supplierfax5;
	}

	public int getSuppliertype() {
		return suppliertype;
	}

	public void setSuppliertype(int suppliertype) {
		this.suppliertype = suppliertype;
	}

	public String getHandlerid() {
		return handlerid;
	}

	public void setHandlerid(String handlerid) {
		this.handlerid = handlerid;
	}

	public String getMail_pr1() {
		if(StringUtil.isNotBlank(suppliermail1)) {
			mail_pr1 = suppliermail1.substring(0, suppliermail1.indexOf("@"));
		}
		return mail_pr1;
	}

	public void setMail_pr1(String mail_pr1) {
		this.mail_pr1 = mail_pr1;
	}

	public String getMail_suffix1() {
		if(StringUtil.isNotBlank(suppliermail1)) {
			mail_suffix1 = suppliermail1.substring(suppliermail1.indexOf("@") + 1, suppliermail1.length());
		}
		return mail_suffix1;
	}

	public void setMail_suffix1(String mail_suffix1) {
		this.mail_suffix1 = mail_suffix1;
	}

	public String getMail_pr2() {
		if(StringUtil.isNotBlank(suppliermail2)) {
			mail_pr2 = suppliermail2.substring(0, suppliermail2.indexOf("@"));
		}
		return mail_pr2;
	}

	public void setMail_pr2(String mail_pr2) {
		this.mail_pr2 = mail_pr2;
	}

	public String getMail_suffix2() {
		if(StringUtil.isNotBlank(suppliermail2)) {
			mail_suffix2 = suppliermail2.substring(suppliermail2.indexOf("@") + 1, suppliermail2.length());
		}
		return mail_suffix2;
	}

	public void setMail_suffix2(String mail_suffix2) {
		this.mail_suffix2 = mail_suffix2;
	}

	public String getMail_pr3() {
		if(StringUtil.isNotBlank(suppliermail3)) {
			mail_pr3 = suppliermail3.substring(0, suppliermail3.indexOf("@"));
		}
		return mail_pr3;
	}

	public void setMail_pr3(String mail_pr3) {
		this.mail_pr3 = mail_pr3;
	}

	public String getMail_suffix3() {
		if(StringUtil.isNotBlank(suppliermail3)) {
			mail_suffix3 = suppliermail3.substring(suppliermail3.indexOf("@") + 1, suppliermail3.length());
		}
		return mail_suffix3;
	}

	public void setMail_suffix3(String mail_suffix3) {
		this.mail_suffix3 = mail_suffix3;
	}

	public String getMail_pr4() {
		if(StringUtil.isNotBlank(suppliermail4)) {
			mail_pr4 = suppliermail4.substring(0, suppliermail4.indexOf("@"));
		}
		return mail_pr4;
	}

	public void setMail_pr4(String mail_pr4) {
		this.mail_pr4 = mail_pr4;
	}

	public String getMail_suffix4() {
		if(StringUtil.isNotBlank(suppliermail4)) {
			mail_suffix4 = suppliermail4.substring(suppliermail4.indexOf("@") + 1, suppliermail4.length());
		}
		return mail_suffix4;
	}

	public void setMail_suffix4(String mail_suffix4) {
		this.mail_suffix4 = mail_suffix4;
	}

	public String getMail_pr5() {
		if(StringUtil.isNotBlank(suppliermail5)) {
			mail_pr5 = suppliermail5.substring(0, suppliermail5.indexOf("@"));
		}
		return mail_pr5;
	}

	public void setMail_pr5(String mail_pr5) {
		this.mail_pr5 = mail_pr5;
	}

	public String getMail_suffix5() {
		if(StringUtil.isNotBlank(suppliermail5)) {
			mail_suffix5 = suppliermail5.substring(suppliermail5.indexOf("@") + 1, suppliermail5.length());
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

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	

}
