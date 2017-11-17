package com.wizard.ptcbcs.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.wizard.ptcbcs.baseinfo.model.BusFactoryModel;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
/**
 * 车辆厂家service类
 * @author wizard
 *
 */
public interface IBusFactoryService {
		/**
		 * 增加车辆厂家
		 * @param busType 车辆厂家对象
		 * @throws Exception
		 */
		public void add(BusFactoryModel busFactory) throws Exception;
		/**
		 * 修改车辆厂家
		 * @param busType 车辆厂家对象
		 * @throws Exception
		 */
		public void modify(BusFactoryModel busFactory) throws Exception;
		/**
		 * 删除车辆厂家
		 * @param busType 车辆厂家对象
		 * @throws Exception
		 */
		public void delete(BusFactoryModel busFactory) throws Exception;
		/**
		 * 取得指定车辆厂家的列表
		 * @param factoryNo 车辆厂家编号
		 * @return 车辆类型对象
		 * @throws Exception
		 */
		public BusFactoryModel get(int factoryNo) throws Exception;
		/**
		 * 取得所有车辆厂家列表
		 * @return 车辆厂家对象集合
		 * @throws Exception
		 */
		public List<BusFactoryModel> getListByAll() throws Exception;
		/**
		 * 取得所有车辆厂家列表，分页方式
		 * @param rows 每页记录数
		 * @param page 当前页
		 * @return 车辆厂家对象集合
		 * @throws Exception
		 */
		public List<BusFactoryModel> getListByAllWithPage(int rows,int page) throws Exception;
		/**
		 * 取得车辆厂家个数
		 * @return 车辆厂家个数
		 * @throws Exception
		 */
		public int getCountByAll() throws Exception;
		/**
		 * 取得车辆厂家页数
		 * @param rows 每页记录数
		 * @return 车辆厂家页数
		 * @throws Exception
		 */
		public int getPageCountByAll(int rows) throws Exception;
		/**
		 * 检查指定车辆厂家是否可以被删除
		 * @param factoryNo 车辆厂家编号
		 * @return 车辆厂家是否可以被删除
		 * @throws Exception
		 */
		public boolean checkCanDelete(int factoryNo) throws Exception;
		/**
		 * 检查车辆厂家名称是否存在
		 * @param factoryName 车辆厂家名称
		 * @return 车辆厂家名称是否存在
		 * @throws Exception
		 */
		public boolean checkNameExist(String factoryName) throws Exception;
		/**
		 * 从Excel文件导入车辆厂家
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
