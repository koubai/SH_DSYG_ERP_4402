package com.cn.dsyg.dto;

import java.util.Date;

import com.cn.common.dto.BaseDto;
import com.cn.common.util.StringUtil;

/**
 * 快递
 * @author 
 * @time 
 * @version 1.0
 */
public class DeliveryDto extends BaseDto {

	private static final long serialVersionUID = 3016953987368363082L;

	/**
	 * 快递ID
	 */
	private int id;

	/**
	 * 快递名
	 */
	private String deliveryname;
	
	public String getDeliveryname() {
		return deliveryname;
	}

	public void setDeliveryname(String deliveryname) {
		this.deliveryname = deliveryname;
	}

	public String getDeliverytel1() {
		return deliverytel1;
	}

	public void setDeliverytel1(String deliverytel1) {
		this.deliverytel1 = deliverytel1;
	}

	public String getDeliverymanager1() {
		return deliverymanager1;
	}

	public void setDeliverymanager1(String deliverymanager1) {
		this.deliverymanager1 = deliverymanager1;
	}

	public String getDeliveryaddress1() {
		return deliveryaddress1;
	}

	public void setDeliveryaddress1(String deliveryaddress1) {
		this.deliveryaddress1 = deliveryaddress1;
	}

	public String getDeliverymail1() {
		return deliverymail1;
	}

	public void setDeliverymail1(String deliverymail1) {
		this.deliverymail1 = deliverymail1;
	}

	public String getDeliveryfax1() {
		return deliveryfax1;
	}

	public void setDeliveryfax1(String deliveryfax1) {
		this.deliveryfax1 = deliveryfax1;
	}

	public String getDeliverytel2() {
		return deliverytel2;
	}

	public void setDeliverytel2(String deliverytel2) {
		this.deliverytel2 = deliverytel2;
	}

	public String getDeliverymanager2() {
		return deliverymanager2;
	}

	public void setDeliverymanager2(String deliverymanager2) {
		this.deliverymanager2 = deliverymanager2;
	}

	public String getDeliveryaddress2() {
		return deliveryaddress2;
	}

	public void setDeliveryaddress2(String deliveryaddress2) {
		this.deliveryaddress2 = deliveryaddress2;
	}

	public String getDeliverymail2() {
		return deliverymail2;
	}

	public void setDeliverymail2(String deliverymail2) {
		this.deliverymail2 = deliverymail2;
	}

	public String getDeliveryfax2() {
		return deliveryfax2;
	}

	public void setDeliveryfax2(String deliveryfax2) {
		this.deliveryfax2 = deliveryfax2;
	}

	public String getDeliverytel3() {
		return deliverytel3;
	}

	public void setDeliverytel3(String deliverytel3) {
		this.deliverytel3 = deliverytel3;
	}

	public String getDeliverymanager3() {
		return deliverymanager3;
	}

	public void setDeliverymanager3(String deliverymanager3) {
		this.deliverymanager3 = deliverymanager3;
	}

	public String getDeliveryaddress3() {
		return deliveryaddress3;
	}

	public void setDeliveryaddress3(String deliveryaddress3) {
		this.deliveryaddress3 = deliveryaddress3;
	}

	public String getDeliverymail3() {
		return deliverymail3;
	}

	public void setDeliverymail3(String deliverymail3) {
		this.deliverymail3 = deliverymail3;
	}

	public String getDeliveryfax3() {
		return deliveryfax3;
	}

	public void setDeliveryfax3(String deliveryfax3) {
		this.deliveryfax3 = deliveryfax3;
	}

	public String getDeliverytel4() {
		return deliverytel4;
	}

	public void setDeliverytel4(String deliverytel4) {
		this.deliverytel4 = deliverytel4;
	}

	public String getDeliverymanager4() {
		return deliverymanager4;
	}

	public void setDeliverymanager4(String deliverymanager4) {
		this.deliverymanager4 = deliverymanager4;
	}

	public String getDeliveryaddress4() {
		return deliveryaddress4;
	}

	public void setDeliveryaddress4(String deliveryaddress4) {
		this.deliveryaddress4 = deliveryaddress4;
	}

	public String getDeliverymail4() {
		return deliverymail4;
	}

	public void setDeliverymail4(String deliverymail4) {
		this.deliverymail4 = deliverymail4;
	}

	public String getDeliveryfax4() {
		return deliveryfax4;
	}

	public void setDeliveryfax4(String deliveryfax4) {
		this.deliveryfax4 = deliveryfax4;
	}

	public String getDeliverytel5() {
		return deliverytel5;
	}

	public void setDeliverytel5(String deliverytel5) {
		this.deliverytel5 = deliverytel5;
	}

	public String getDeliverymanager5() {
		return deliverymanager5;
	}

	public void setDeliverymanager5(String deliverymanager5) {
		this.deliverymanager5 = deliverymanager5;
	}

	public String getDeliveryaddress5() {
		return deliveryaddress5;
	}

	public void setDeliveryaddress5(String deliveryaddress5) {
		this.deliveryaddress5 = deliveryaddress5;
	}

	public String getDeliverymail5() {
		return deliverymail5;
	}

	public void setDeliverymail5(String deliverymail5) {
		this.deliverymail5 = deliverymail5;
	}

	public String getDeliveryfax5() {
		return deliveryfax5;
	}

	public void setDeliveryfax5(String deliveryfax5) {
		this.deliveryfax5 = deliveryfax5;
	}

	public int getDeliverytype() {
		return deliverytype;
	}

	public void setDeliverytype(int deliverytype) {
		this.deliverytype = deliverytype;
	}

	/**
	 * 快递所属地（以后可能分上海和深圳）
	 */
	private String belongto;

	/**
	 * 快递备注
	 */
	private String note;

	/**
	 * 联系电话1
	 */
	private String deliverytel1;

	/**
	 * 联系人1
	 */
	private String deliverymanager1;

	/**
	 * 地址1
	 */
	private String deliveryaddress1;
	
	/**
	 * 邮箱1
	 */
	private String deliverymail1;

	/**
	 * 传真1
	 */
	private String deliveryfax1;

	/**
	 * 联系电话2
	 */
	private String deliverytel2;

	/**
	 * 联系人2
	 */
	private String deliverymanager2;

	/**
	 * 地址2
	 */
	private String deliveryaddress2;
	
	/**
	 * 邮箱2
	 */
	private String deliverymail2;

	/**
	 * 传真2
	 */
	private String deliveryfax2;

	/**
	 * 联系电话3
	 */
	private String deliverytel3;

	/**
	 * 联系人3
	 */
	private String deliverymanager3;

	/**
	 * 地址3
	 */
	private String deliveryaddress3;
	
	/**
	 * 邮箱3
	 */
	private String deliverymail3;

	/**
	 * 传真3
	 */
	private String deliveryfax3;

	/**
	 * 联系电话4
	 */
	private String deliverytel4;

	/**
	 * 联系人4
	 */
	private String deliverymanager4;

	/**
	 * 地址4
	 */
	private String deliveryaddress4;
	
	/**
	 * 邮箱4
	 */
	private String deliverymail4;

	/**
	 * 传真4
	 */
	private String deliveryfax4;

	/**
	 * 联系电话5
	 */
	private String deliverytel5;

	/**
	 * 联系人5
	 */
	private String deliverymanager5;

	/**
	 * 地址5
	 */
	private String deliveryaddress5;
	
	/**
	 * 邮箱5
	 */
	private String deliverymail5;

	/**
	 * 传真5
	 */
	private String deliveryfax5;
	
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
	 * 快递类型
	 */
	private int deliverytype;
	
	/**
	 * 快递担当
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
		if(StringUtil.isNotBlank(deliverymail1)) {
			mail_pr1 = deliverymail1.substring(0, deliverymail1.indexOf("@"));
		}
		return mail_pr1;
	}

	public void setMail_pr1(String mail_pr1) {
		this.mail_pr1 = mail_pr1;
	}

	public String getMail_suffix1() {
		if(StringUtil.isNotBlank(deliverymail1)) {
			mail_suffix1 = deliverymail1.substring(deliverymail1.indexOf("@") + 1, deliverymail1.length());
		}
		return mail_suffix1;
	}

	public void setMail_suffix1(String mail_suffix1) {
		this.mail_suffix1 = mail_suffix1;
	}

	public String getMail_pr2() {
		if(StringUtil.isNotBlank(deliverymail2)) {
			mail_pr2 = deliverymail2.substring(0, deliverymail2.indexOf("@"));
		}
		return mail_pr2;
	}

	public void setMail_pr2(String mail_pr2) {
		this.mail_pr2 = mail_pr2;
	}

	public String getMail_suffix2() {
		if(StringUtil.isNotBlank(deliverymail2)) {
			mail_suffix2 = deliverymail2.substring(deliverymail2.indexOf("@") + 1, deliverymail2.length());
		}
		return mail_suffix2;
	}

	public void setMail_suffix2(String mail_suffix2) {
		this.mail_suffix2 = mail_suffix2;
	}

	public String getMail_pr3() {
		if(StringUtil.isNotBlank(deliverymail3)) {
			mail_pr3 = deliverymail3.substring(0, deliverymail3.indexOf("@"));
		}
		return mail_pr3;
	}

	public void setMail_pr3(String mail_pr3) {
		this.mail_pr3 = mail_pr3;
	}

	public String getMail_suffix3() {
		if(StringUtil.isNotBlank(deliverymail3)) {
			mail_suffix3 = deliverymail3.substring(deliverymail3.indexOf("@") + 1, deliverymail3.length());
		}
		return mail_suffix3;
	}

	public void setMail_suffix3(String mail_suffix3) {
		this.mail_suffix3 = mail_suffix3;
	}

	public String getMail_pr4() {
		if(StringUtil.isNotBlank(deliverymail4)) {
			mail_pr4 = deliverymail4.substring(0, deliverymail4.indexOf("@"));
		}
		return mail_pr4;
	}

	public void setMail_pr4(String mail_pr4) {
		this.mail_pr4 = mail_pr4;
	}

	public String getMail_suffix4() {
		if(StringUtil.isNotBlank(deliverymail4)) {
			mail_suffix4 = deliverymail4.substring(deliverymail4.indexOf("@") + 1, deliverymail4.length());
		}
		return mail_suffix4;
	}

	public void setMail_suffix4(String mail_suffix4) {
		this.mail_suffix4 = mail_suffix4;
	}

	public String getMail_pr5() {
		if(StringUtil.isNotBlank(deliverymail5)) {
			mail_pr5 = deliverymail5.substring(0, deliverymail5.indexOf("@"));
		}
		return mail_pr5;
	}

	public void setMail_pr5(String mail_pr5) {
		this.mail_pr5 = mail_pr5;
	}

	public String getMail_suffix5() {
		if(StringUtil.isNotBlank(deliverymail5)) {
			mail_suffix5 = deliverymail5.substring(deliverymail5.indexOf("@") + 1, deliverymail5.length());
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
	

}
