package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * @name UserDto.java
 * @author Frank
 * @time 2014-12-9上午12:25:56
 * @version 1.0
 */
public class UserDto extends BaseDto {

	private static final long serialVersionUID = -3957879938440024040L;

	/**
	 * 用户登录ID（主键）
	 */
	private String userid;

	/**
	 * 用户所属
	 */
	private String belongto;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户颜色
	 */
//	private String usercolor;
	
	/**
	 * 旧登录密码，修改密码用
	 */
	private String oldpassword;

	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 重复密码
	 */
	private String repassword;
	
	/**
	 * 登录验证码
	 */
	private String verificationcode;
	
	/**
	 * 角色code
	 */
	private String rolecode;
	
	/**
	 * 数据状态：1有效，其他无效
	 */
	private Integer status;
	
	/**
	 * 备注
	 */
	private String note;

	/**
	 * 数据创建者
	 */
	private String createuid;

	/**
	 * 创建时间
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

/*
	public String getUsercolor() {
		return usercolor;
	}

	public void setUsercolor(String usercolor) {
		this.usercolor = usercolor;
	}
*/
}
