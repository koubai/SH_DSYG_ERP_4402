package com.cn.dsyg.dto;

import com.cn.common.dto.BaseDto;

public class WarehouseCostDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4984222368457787047L;
	
	private String TypeName;
	private String WarehouseCost;
	private String LeaveQuantity;

	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getWarehouseCost() {
		return WarehouseCost;
	}
	public void setWarehouseCost(String warehouseCost) {
		WarehouseCost = warehouseCost;
	}
	public String getLeaveQuantity() {
		return LeaveQuantity;
	}
	public void setLeaveQuantity(String leaveQuantity) {
		LeaveQuantity = leaveQuantity;
	}

}
