package com.wizard.ptcbcs.baseinfo.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.wizard.ptcbcs.baseinfo.model.BusDriverModel;
/**
 * 司机mapper类
 * @author wizard
 *
 */
public interface IBusDriverMapper {
	/**
	 * 插入司机
	 * @param busDriver 司机对象
	 * @throws Exception
	 */
	public void insert(BusDriverModel busDriver) throws Exception;
	/**
	 * 修改司机
	 * @param busDriver 司机对象
	 * @throws Exception
	 */
	public void update(BusDriverModel busDriver) throws Exception;
	/**
	 * 删除司机
	 * @param busDriver 司机对象
	 * @throws Exception
	 */
	public void delete(BusDriverModel busDriver) throws Exception;
	/**
	 * 按司机编号查询司机数据对象
	 * @param busDriver 司机编号
	 * @throws Exception
	 */
	public BusDriverModel select(String driverID) throws Exception;
	/**
	 * 返回所有司机数据对象集合
	 * @throws Exception
	 */
	public List<BusDriverModel> selectListByAll() throws Exception;
	/**
	 * 分页返回所有司机数据对象集合
	 * @param busDriver 分页条件
	 * @throws Exception
	 */
	public List<BusDriverModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	/**
	 * 返回所有司机数据对象个数
	 * @throws Exception
	 */
	public int selectCountByAll() throws Exception;
}
