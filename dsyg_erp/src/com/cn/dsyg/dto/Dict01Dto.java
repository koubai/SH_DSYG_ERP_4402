package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * @name 字典表
 * @author Frank
 * @time 2014-12-15下午11:25:09
 * @version 1.0
 */
public class Dict01Dto extends BaseDto {

	private static final long serialVersionUID = 6905706797159051998L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 大分类
	 */
	private String fieldcode;

	/**
	 * 名称
	 */
	private String fieldname;
	
	/**
	 * 代码
	 */
	private String code;

	/**
	 * 语言，默认为C=Chinese
	 */
	private String lang;

	/**
	 * 含义（单位）
	 */
	private String mean;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 状态，1为有效，其他为无效
	 */
	private Integer status;

	/**
	 * 数据创建者
	 */
	private String createuid;

	/**
	 * 创建时间
	 */
	private String createdate;

	/**
	 * 数据更新者
	 */
	private String updateuid;

	/**
	 * 更新时间
	 */
	private String updatedate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldcode() {
		return fieldcode;
	}

	public void setFieldcode(String fieldcode) {
		this.fieldcode = fieldcode;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
