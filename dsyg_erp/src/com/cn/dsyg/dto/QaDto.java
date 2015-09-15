package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * @name QaDto.java
 * @author Frank
 * @time 2015-2-2下午9:08:20
 * @version 1.0
 */
public class QaDto extends BaseDto {

	private static final long serialVersionUID = -6034458506935056004L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String data;

	/**
	 * 姓名
	 */
	private String fullname;

	/**
	 * 公司名
	 */
	private String company;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 电话
	 */
	private String tell;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 邮件
	 */
	private String email;
	
	/**
	 * IP地址
	 */
	private String ip;

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
	 * 数据状态
	 */
	private String status;

	/**
	 * 作成者
	 */
	private String createuid;

	/**
	 * 作成时间
	 */
	private String createdate;

	/**
	 * 更新者
	 */
	private String updateuid;

	/**
	 * 更新时间
	 */
	private String updatedate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCreateuid() {
		return createuid;
	}

	public void setCreateuid(String createuid) {
		this.createuid = createuid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdateuid() {
		return updateuid;
	}

	public void setUpdateuid(String updateuid) {
		this.updateuid = updateuid;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
