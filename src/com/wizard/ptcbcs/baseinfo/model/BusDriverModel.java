package com.wizard.ptcbcs.baseinfo.model;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 司机model类
 * @author wizard
 *
 */
@Alias("BusDriver")
public class BusDriverModel {
	/**
	 * 司机编号
	 */
	private String driverID = null;
	/**
	 * 姓名
	 */
	private String dname = null;
	/**
	 * 性别
	 */
	private String sex = null;
	/**
	 * 年龄
	 */
	private Integer age = 0;
	/**
	 * 生日
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date birthday;
	/**
	 * 身份证号
	 */
	private String dcard = null;
	/**
	 * 驾照编号
	 */
	private String dcore = null;
	/**
	 * 入职时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date joinDate;
	/**
	 * 离职时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
	private Date leaveDate;
	
	public String getdriverID() {
		return driverID;
	}
	public void setdriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getdname() {
		return dname;
	}
	public void setdname(String dname) {
		this.dname = dname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDcard() {
		return dcard;
	}
	public void setDcard(String dcard) {
		this.dcard = dcard;
	}
	public String getDcore() {
		return dcore;
	}
	public void setDcore(String dcore) {
		this.dcore = dcore;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	
	

}
