package com.wizard.ptcbcs.baseinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 车辆类型
 * @author wizard
 *
 */
@Alias("BusType")
public class BusTypeModel {
	private int typeNo;
	private String typeName;
	/**
	 * 得到并返回车辆类型编号
	 * @return
	 */
	public int getTypeNo() {
		return typeNo;
	}
	/**
	 * 设置车辆类型编号
	 * @param typeNo 车辆类型编号
	 */
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	/**
	 * 得到并返回车辆类型名称
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置车辆类型名称
	 * @param typeName 车辆类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
