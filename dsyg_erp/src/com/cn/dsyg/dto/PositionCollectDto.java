package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

/**
 * @name PositionCollectDto.java
 * @author Frank
 * @time 2015-9-19下午2:39:48
 * @version 1.0
 */
public class PositionCollectDto extends BaseDto {

	private static final long serialVersionUID = -2353178516326718098L;

	/**
	 * 0：上海，1：深圳
	 */
	private String belongto;

	/**
	 * 盘点日期，格式yyyy-MM-dd
	 */
	private String checkday;

	/**
	 * 盘点人
	 */
	private String handler;
	
	/**
	 * 盘点人名称
	 */
	private String handlername;
	
	/**
	 * 盘点次数
	 */
	private String num;

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getCheckday() {
		return checkday;
	}

	public void setCheckday(String checkday) {
		this.checkday = checkday;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getHandlername() {
		return handlername;
	}

	public void setHandlername(String handlername) {
		this.handlername = handlername;
	}
}
