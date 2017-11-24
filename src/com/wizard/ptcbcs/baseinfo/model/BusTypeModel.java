package com.wizard.ptcbcs.baseinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 车辆类型model类
 * @author wizard
 *
 */
@Alias("BusType")
public class BusTypeModel {
	/**
	 * 车辆类型编号
	 */
	private int typeNo;
	/**
	 * 车辆类型名称
	 */
	private String typeName;
	/**
	 * 车辆类型的附件
	 */
	private byte[] photo=null;
	/**
	 * 附件文件名
	 */
	private String photoFileName=null;
	/**
	 * 附件文件类型
	 */
	private String photoContentType=null;
	
	
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	
}
