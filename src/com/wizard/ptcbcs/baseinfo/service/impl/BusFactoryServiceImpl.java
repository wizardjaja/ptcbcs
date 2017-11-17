package com.wizard.ptcbcs.baseinfo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wizard.ptcbcs.baseinfo.mapper.IBusFactoryMapper;
import com.wizard.ptcbcs.baseinfo.model.BusFactoryModel;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IBusFactoryService;
/**
 * 车辆厂家service实现类
 * @author wizard
 *
 */
@Service
public class BusFactoryServiceImpl implements IBusFactoryService {
	private IBusFactoryMapper busFactoryMapper;
	@Autowired
	public void setBusFactoryMapper(IBusFactoryMapper busFactoryMapper) {
		this.busFactoryMapper = busFactoryMapper;
	}
	
	@Override
	public void add(BusFactoryModel busFactory) throws Exception {
		busFactoryMapper.insert(busFactory);
	}

	@Override
	public void modify(BusFactoryModel busFactory) throws Exception {
		// TODO Auto-generated method stub
		busFactoryMapper.update(busFactory);
	}

	@Override
	public void delete(BusFactoryModel busFactory) throws Exception {
		// TODO Auto-generated method stub
		busFactoryMapper.delete(busFactory);
	}

	@Override
	public BusFactoryModel get(int factoryNo) throws Exception {
		return busFactoryMapper.select(factoryNo);
	}

	@Override
	public List<BusFactoryModel> getListByAll() throws Exception {
		return busFactoryMapper.selectListByAll();
	}

	@Override
	public List<BusFactoryModel> getListByAllWithPage(int rows, int page) throws Exception {
		return busFactoryMapper.selectListByAllWithPage(new RowBounds(rows*(page-1), rows));
	}

	@Override
	public int getCountByAll() throws Exception {
		return busFactoryMapper.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int count = this.getCountByAll();
		if(count%rows ==0) {
			return count/rows;
		}else {
			return count/rows + 1;
		}
	}

	@Override
	public boolean checkCanDelete(int factoryNo) throws Exception {
		return true;
	}

	@Override
	public boolean checkNameExist(String factoryName) throws Exception {
		boolean result=false;
		List<BusFactoryModel> list=this.getListByAll();
		for(BusFactoryModel busFactory:list){
			if(busFactory!=null&&busFactory.getFactoryName()!=null&&busFactory.getFactoryName().equals(factoryName)){
				result=true;
				break;
			}
		}
		return false;
	}

	@Override
	public void importFromExcel(InputStream excelFile) throws Exception {
		//打开上传的excel文件
		Workbook wb = WorkbookFactory.create(excelFile);
		//取得第1个sheet
		Sheet sheet=wb.getSheetAt(0);
		//取得第1行
		Row row0=sheet.getRow(0);
		for (Row row : sheet) {
			if(row.getRowNum()!=0) {
				Cell c0 = row.getCell(0);
				String factoryName = c0.getStringCellValue();
				System.out.println("0");
				Cell c1 = row.getCell(1);
				String factoryDesc = c1.getStringCellValue();
				System.out.println("1");
				BusFactoryModel busFactory = new BusFactoryModel();
				busFactory.setFactoryName(factoryName);
				busFactory.setFactoryDesc(factoryDesc);
				this.add(busFactory);
			}
		}
	}

	@Override
	public void exportToExcel(File source, File exportFile) throws Exception {
		//打开excel模板文件
		//Workbook wb = WorkbookFactory.create(source);
		OPCPackage pkg = OPCPackage.open(source);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		//取得第一个sheet
		Sheet sheet = wb.getSheetAt(0);
		//取得所有的车辆厂家列表
		List<BusFactoryModel> list = busFactoryMapper.selectListByAll();
		for (BusFactoryModel busFactoty : list) {
			System.out.println(busFactoty);
		}
		int i = 1;
		for(BusFactoryModel busFactoty:list){
			Row row = sheet.createRow(i);
			Cell c0 = row.createCell(0);
			c0.setCellValue(busFactoty.getFactoryName());
			Cell c1 = row.createCell(1);
			c1.setCellValue(busFactoty.getFactoryDesc());
			i++;
		}
		FileOutputStream fos = new FileOutputStream(exportFile);
		wb.write(fos);
		fos.close();
		wb.close();

	}

}
