package com.wizard.ptcbcs.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.wizard.ptcbcs.baseinfo.model.BusFactoryModel;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
/**
 * 车辆厂家mapper类
 * @author wizard
 *
 */
public interface IBusFactoryMapper {
	/**
	 * 插入车辆厂家
	 * @param busFactory 车辆厂家对象
	 * @throws Exception
	 */
	public void insert(BusFactoryModel busFactory) throws Exception;
	/**
	 * 修改车辆厂家
	 * @param busFactory 车辆厂家对象
	 * @throws Exception
	 */
	public void update(BusFactoryModel busFactory) throws Exception;
	/**
	 * 删除车辆厂家
	 * @param busFactory 车辆厂家对象
	 * @throws Exception
	 */
	public void delete(BusFactoryModel busFactory) throws Exception;
	/**
	 * 按车辆厂家编号查询车辆厂家数据对象
	 * @param busFactory 车辆厂家编号
	 * @throws Exception
	 */
	public BusFactoryModel select(int factoryNo) throws Exception;
	/**
	 * 返回所有车辆厂家数据对象集合
	 * @throws Exception
	 */
	public List<BusFactoryModel> selectListByAll() throws Exception;
	/**
	 * 分页返回所有车辆厂家数据对象集合
	 * @param busFactory 分页条件
	 * @throws Exception
	 */
	public List<BusFactoryModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	/**
	 * 返回所有车辆厂家数据对象个数
	 * @throws Exception
	 */
	public int selectCountByAll() throws Exception;
}
