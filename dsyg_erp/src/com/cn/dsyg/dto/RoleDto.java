package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * 角色表DTO
 * @author Frank
 * @time 2014-12-12下午10:58:55
 * @version 1.0
 */
public class RoleDto extends BaseDto {

	private static final long serialVersionUID = 5205855868740308990L;

	/**
	 * 角色ID
	 */
	private Integer id;
	
	/**
	 * 角色code
	 */
	private String rolecode;

	/**
	 * 角色名
	 */
	private String rolename;

	/**
	 * 角色权限级别
	 */
	private Integer rank;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 数据状态：1有效，其他无效
	 */
	private Integer status;

	/**
	 * 数据创建者ID
	 */
	private String createuid;

	/**
	 * 数据创建时间
	 */
	private String createdate;

	/**
	 * 更新者ID
	 */
	private String updateuid;

	/**
	 * 数据更新时间
	 */
	private String updatedate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
}
