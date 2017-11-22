package com.wizard.ptcbcs.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.wizard.ptcbcs.baseinfo.model.BusDriverModel;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
/**
 * 司机service类
 * @author wizard
 *
 */
public interface IBusDriverService {
		/**
		 * 增加司机
		 * @param busType 司机对象
		 * @throws Exception
		 */
		public void add(BusDriverModel busDriver) throws Exception;
		/**
		 * 修改司机
		 * @param busType 司机对象
		 * @throws Exception
		 */
		public void modify(BusDriverModel busDriver) throws Exception;
		/**
		 * 删除司机
		 * @param busType 司机对象
		 * @throws Exception
		 */
		public void delete(BusDriverModel busDriver) throws Exception;
		/**
		 * 取得指定司机的列表
		 * @param factoryNo 司机编号
		 * @return 车辆类型对象
		 * @throws Exception
		 */
		public BusDriverModel get(int factoryNo) throws Exception;
		/**
		 * 取得所有司机列表
		 * @return 司机对象集合
		 * @throws Exception
		 */
		public List<BusDriverModel> getListByAll() throws Exception;
		/**
		 * 取得所有司机列表，分页方式
		 * @param rows 每页记录数
		 * @param page 当前页
		 * @return 司机对象集合
		 * @throws Exception
		 */
		public List<BusDriverModel> getListByAllWithPage(int rows,int page) throws Exception;
		/**
		 * 取得司机个数
		 * @return 司机个数
		 * @throws Exception
		 */
		public int getCountByAll() throws Exception;
		/**
		 * 取得司机页数
		 * @param rows 每页记录数
		 * @return 司机页数
		 * @throws Exception
		 */
		public int getPageCountByAll(int rows) throws Exception;
		/**
		 * 检查指定司机是否可以被删除
		 * @param factoryNo 司机编号
		 * @return 司机是否可以被删除
		 * @throws Exception
		 */
		public boolean checkCanDelete(int factoryNo) throws Exception;
		/**
		 * 检查司机名称是否存在
		 * @param factoryName 司机名称
		 * @return 司机名称是否存在
		 * @throws Exception
		 */
		public boolean checkNameExist(String factoryName) throws Exception;
		/**
		 * 从Excel文件导入司机
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
