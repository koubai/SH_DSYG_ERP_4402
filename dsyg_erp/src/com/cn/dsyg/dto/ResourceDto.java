package com.cn.dsyg.dto;

import java.util.Date;

import com.cn.common.dto.BaseDto;

/**
 * @name 系统菜单资源表
 * @author Frank
 * @time 2014-12-15下午11:25:09
 * @version 1.0
 */
public class ResourceDto extends BaseDto {

	private static final long serialVersionUID = -4530468223912122242L;

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 菜单地址
	 */
	private String url;

	/**
	 * 描述
	 */
	private String note;
	
	/**
	 * 数据状态
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 资源顺序
	 */
	private Integer sort;
	
	/**
	 * 资源父节点ID
	 */
	private Integer parentid;
	
	/**
	 * 资源类型：1为主节点，其他为子节
	 */
	private Integer restype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getRestype() {
		return restype;
	}

	public void setRestype(Integer restype) {
		this.restype = restype;
	}
}
