package com.wizard.ptcbcs.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
/**
 * 车辆类型service类
 * @author wizard
 *
 */
public interface IBusTypeService {
		/**
		 * 增加车辆类型
		 * @param busType 车辆类型对象
		 * @throws Exception
		 */
		public void add(BusTypeModel busType) throws Exception;
		/**
		 * 修改车辆类型
		 * @param busType 车辆类型对象
		 * @throws Exception
		 */
		public void modify(BusTypeModel busType) throws Exception;
		/**
		 * 修改车辆类型,有附件信息处理
		 * @param busType 车辆类型对象
		 * @throws Exception
		 */
		public void modifyWithPhoto(BusTypeModel busType) throws Exception;
		/**
		 * 删除车辆类型,有附件信息处理
		 * @param busType 车辆类型对象
		 * @throws Exception
		 */
		public void modifyForDeletePhoto(BusTypeModel busType) throws Exception;
		/**
		 * 删除车辆类型
		 * @param busType 车辆类型对象
		 * @throws Exception
		 */
		public void delete(BusTypeModel busType) throws Exception;
		/**
		 * 取得指定车辆类型的列表
		 * @param typeNo 车辆类型编号
		 * @return 车辆类型对象
		 * @throws Exception
		 */
		public BusTypeModel get(int typeNo) throws Exception;
		/**
		 * 取得所有车辆类型列表
		 * @return 车辆类型对象集合
		 * @throws Exception
		 */
		public List<BusTypeModel> getListByAll() throws Exception;
		/**
		 * 取得所有车辆类型列表，分页方式
		 * @param rows 每页记录数
		 * @param page 当前页
		 * @return 车辆类型对象集合
		 * @throws Exception
		 */
		public List<BusTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
		/**
		 * 取得车辆类型个数
		 * @return 车辆类型个数
		 * @throws Exception
		 */
		public int getCountByAll() throws Exception;
		/**
		 * 取得车辆类型页数
		 * @param rows 每页记录数
		 * @return 车辆类型页数
		 * @throws Exception
		 */
		public int getPageCountByAll(int rows) throws Exception;
		/**
		 * 检查指定车辆类型是否可以被删除
		 * @param typeNo 车辆类型编号
		 * @return 车辆类型是否可以被删除
		 * @throws Exception
		 */
		public boolean checkCanDelete(int typeNo) throws Exception;
		/**
		 * 检查车辆类型名称是否存在
		 * @param typeName 车辆类型名称
		 * @return 车辆类型名称是否存在
		 * @throws Exception
		 */
		public boolean checkNameExist(String typeName) throws Exception;
		/**
		 * 从Excel文件导入车辆类型
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
