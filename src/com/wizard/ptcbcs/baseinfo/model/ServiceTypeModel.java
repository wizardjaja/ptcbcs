package com.wizard.ptcbcs.baseinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 维修类型model类
 * @author wizard
 *
 */
@Alias("ServiceType")
public class ServiceTypeModel {
	/**
	 * 维修类型编号
	 */
	private int typeNo = 0;
	/**
	 * 维修类型名称
	 */
	private String typeName = null;
	/**
	 * 维修类型描述
	 */
	private String typeDesc = null;
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
	 * 获取维修类型编号
	 * @return
	 */
	public int getTypeNo() {
		return typeNo;
	}
	/**
	 * 设置维修类型编号
	 * @param typeNo
	 */
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	/**
	 * 获取维修类型名称
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置维修类型名称
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取维修类型描述
	 * @return
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	/**
	 * 设置维修类型描述
	 * @param typeDesc
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
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
