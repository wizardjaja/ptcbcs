package com.wizard.ptcbcs.baseinfo.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import com.wizard.ptcbcs.baseinfo.model.ServiceTypeModel;
/**
 * 维修类型mapper类
 * @author wizard
 *
 */
public interface IServiceTypeMapper {
	/**
	 * 插入维修类型
	 * @param ServiceType 维修类型对象
	 * @throws Exception
	 */
	public void insert(ServiceTypeModel serviceType) throws Exception;
	/**
	 * 修改维修类型
	 * @param ServiceType 维修类型对象
	 * @throws Exception
	 */
	public void update(ServiceTypeModel serviceType) throws Exception;
	/**
	 * 删除维修类型
	 * @param ServiceType 维修类型对象
	 * @throws Exception
	 */
	public void delete(ServiceTypeModel serviceType) throws Exception;
	/**
	 * 按维修类型编号查询维修类型数据对象
	 * @param ServiceType 维修类型编号
	 * @throws Exception
	 */
	public ServiceTypeModel select(int typeNo) throws Exception;
	/**
	 * 返回所有维修类型数据对象集合
	 * @throws Exception
	 */
	public List<ServiceTypeModel> selectListByAll() throws Exception;
	/**
	 * 分页返回所有维修类型数据对象集合
	 * @param ServiceType 分页条件
	 * @throws Exception
	 */
	public List<ServiceTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	/**
	 * 返回所有维修类型数据对象个数
	 * @throws Exception
	 */
	public int selectCountByAll() throws Exception;
}
