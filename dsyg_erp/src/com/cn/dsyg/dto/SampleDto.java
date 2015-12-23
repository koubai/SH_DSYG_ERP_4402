package com.cn.dsyg.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.cn.common.dto.BaseDto;

/**
 * @name SampleDto.java
 * @author Frank
 * @time 2015-9-12下午7:51:25
 * @version 1.0
 */
public class SampleDto extends BaseDto {

	private static final long serialVersionUID = 7374855688396250983L;

	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 仓库
	 */
	private String warehousename;

	/**
	 * 所属地（以后可能分上海和深圳）
	 */
	private String belongto;
	
	/**
	 * 产品ID
	 */
	private String productid;
	
	//产品信息
	/**
	 * 品名
	 */
	private String tradename;

	/**
	 * 规格
	 */
	private String typeno;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 形式
	 */
	private String packaging;
	
	/**
	 * 包装
	 */
	private String item10;
	//
	
	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 产地
	 */
	private String makearea;

	/**
	 * 数量
	 */
	private String quantity;
	
	/**
	 * 数量（显示用）
	 */
	private String showQuantity;
	
	/**
	 * 客户类型，1为供应商，2为客户
	 */
	private String customertype;
	
	/**
	 * 客户ID
	 */
	private String customerid;
	
	/**
	 * 客户名
	 */
	private String customername;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 级别(0-99)
	 */
	private Integer rank;

	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 客户ID
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
	 * 数据创建者
	 */
	private String createuid;

	/**
	 * 数据创建时间
	 */
	private Date createdate;

	/**
	 * 数据更新者
	 */
	private String updateuid;

	/**
	 * 数据更新时间
	 */
	private Date updatedate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getTradename() {
		return tradename;
	}

	public void setTradename(String tradename) {
		this.tradename = tradename;
	}

	public String getTypeno() {
		return typeno;
	}

	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getItem10() {
		return item10;
	}

	public void setItem10(String item10) {
		this.item10 = item10;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getShowQuantity() {
		if(quantity != null && !"".equals(quantity)) {
			BigDecimal tmp = new BigDecimal(quantity);
			if(tmp.compareTo(new BigDecimal(0)) < 0) {
				showQuantity = "" + tmp.multiply(new BigDecimal(-1));
			} else {
				showQuantity = quantity;
			}
		}
		return showQuantity;
	}

	public void setShowQuantity(String showQuantity) {
		this.showQuantity = showQuantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMakearea() {
		return makearea;
	}

	public void setMakearea(String makearea) {
		this.makearea = makearea;
	}


}
