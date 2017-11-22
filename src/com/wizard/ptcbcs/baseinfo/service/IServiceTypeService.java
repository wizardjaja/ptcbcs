package com.wizard.ptcbcs.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import com.wizard.ptcbcs.baseinfo.model.ServiceTypeModel;
/**
 * 维修类型service类
 * @author wizard
 *
 */
public interface IServiceTypeService {
		/**
		 * 增加维修类型
		 * @param busType 维修类型对象
		 * @throws Exception
		 */
		public void add(ServiceTypeModel serviceType) throws Exception;
		/**
		 * 修改维修类型
		 * @param busType 维修类型对象
		 * @throws Exception
		 */
		public void modify(ServiceTypeModel serviceType) throws Exception;
		/**
		 * 删除维修类型
		 * @param busType 维修类型对象
		 * @throws Exception
		 */
		public void delete(ServiceTypeModel serviceType) throws Exception;
		/**
		 * 取得指定维修类型的列表
		 * @param factoryNo 维修类型编号
		 * @return 车辆类型对象
		 * @throws Exception
		 */
		public ServiceTypeModel get(int typeNo) throws Exception;
		/**
		 * 取得所有维修类型列表
		 * @return 维修类型对象集合
		 * @throws Exception
		 */
		public List<ServiceTypeModel> getListByAll() throws Exception;
		/**
		 * 取得所有维修类型列表，分页方式
		 * @param rows 每页记录数
		 * @param page 当前页
		 * @return 维修类型对象集合
		 * @throws Exception
		 */
		public List<ServiceTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
		/**
		 * 取得维修类型个数
		 * @return 维修类型个数
		 * @throws Exception
		 */
		public int getCountByAll() throws Exception;
		/**
		 * 取得维修类型页数
		 * @param rows 每页记录数
		 * @return 维修类型页数
		 * @throws Exception
		 */
		public int getPageCountByAll(int rows) throws Exception;
		/**
		 * 检查指定维修类型是否可以被删除
		 * @param factoryNo 维修类型编号
		 * @return 维修类型是否可以被删除
		 * @throws Exception
		 */
		public boolean checkCanDelete(int typeNo) throws Exception;
		/**
		 * 检查维修类型名称是否存在
		 * @param factoryName 维修类型名称
		 * @return 维修类型名称是否存在
		 * @throws Exception
		 */
		public boolean checkNameExist(String typeName) throws Exception;
		/**
		 * 从Excel文件导入维修类型
		 * @param excelFile excel文件输入流
		 * @throws Exception 
		 */
		public void importFromExcel(InputStream excelFile) throws Exception;
		/**
		 * 导出Excel文件
		 * @param source 模板文件
		 * @param exportFile 目标文件
		 * @throws Exception
		 */
		public void exportToExcel(File source, File exportFile) throws Exception;
}
