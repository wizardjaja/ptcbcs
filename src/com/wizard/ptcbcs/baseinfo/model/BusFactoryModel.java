package com.wizard.ptcbcs.baseinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 车辆厂家model类
 * @author wizard
 *
 */
@Alias("BusFactory")
public class BusFactoryModel {
	/**
	 * 车辆厂家编号
	 */
	private int factoryNo;
	/**
	 * 车辆厂家名称
	 */
	private String factoryName;
	/**
	 * 车辆厂家描述
	 */
	private String factoryDesc;
	/**
	 * 获取车辆厂家编号
	 * @return
	 */
	public int getFactoryNo() {
		return factoryNo;
	}
	/**
	 * 设置车辆厂家编号
	 * @param factoryNo
	 */
	public void setFactoryNo(int factoryNo) {
		this.factoryNo = factoryNo;
	}
	/**
	 * 获取车辆厂家名称
	 * @return
	 */
	public String getFactoryName() {
		return factoryName;
	}
	/**
	 * 设置车辆厂家名称
	 * @param factoryName
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	/**
	 * 获取车辆厂家描述
	 * @return
	 */
	public String getFactoryDesc() {
		return factoryDesc;
	}
	/**
	 * 设置车辆厂家描述
	 * @param factoryDesc
	 */
	public void setFactoryDesc(String factoryDesc) {
		this.factoryDesc = factoryDesc;
	}
	

}
