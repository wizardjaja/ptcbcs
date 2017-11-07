package com.wizard.ptcbcs.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
/**
 * 车辆类型mapper类
 * @author wizard
 *
 */
public interface IBusTypeMapper {
	/**
	 * 插入车辆类型
	 * @param busType 车辆类型对象
	 * @throws Exception
	 */
	public void insert(BusTypeModel busType) throws Exception;
	/**
	 * 修改车辆类型
	 * @param busType 车辆类型对象
	 * @throws Exception
	 */
	public void update(BusTypeModel busType) throws Exception;
	/**
	 * 删除车辆类型
	 * @param busType 车辆类型对象
	 * @throws Exception
	 */
	public void delete(BusTypeModel busType) throws Exception;
	/**
	 * 按车辆类型编号查询车辆类型数据对象
	 * @param busType 车辆类型编号
	 * @throws Exception
	 */
	public BusTypeModel select(int typeNo) throws Exception;
	/**
	 * 返回所有车辆类型数据对象集合
	 * @throws Exception
	 */
	public List<BusTypeModel> selectListByAll() throws Exception;
	/**
	 * 分页返回所有车辆类型数据对象集合
	 * @param busType 分页条件
	 * @throws Exception
	 */
	public List<BusTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	/**
	 * 返回所有车辆类型数据对象个数
	 * @throws Exception
	 */
	public int selectCountByAll() throws Exception;
}
