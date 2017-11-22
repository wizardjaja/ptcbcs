package com.wizard.ptcbcs.baseinfo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
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
import com.wizard.ptcbcs.baseinfo.mapper.IBusDriverMapper;
import com.wizard.ptcbcs.baseinfo.model.BusDriverModel;
import com.wizard.ptcbcs.baseinfo.service.IBusDriverService;
/**
 * 司机service实现类
 * @author wizard
 *
 */
@Service
public class BusDriverServiceImpl implements IBusDriverService {
	private IBusDriverMapper busDriverMapper;
	@Autowired
	public void setBusDriverMapper(IBusDriverMapper busDriverMapper) {
		this.busDriverMapper = busDriverMapper;
	}

	@Override
	public void add(BusDriverModel busDriver) throws Exception {
		busDriverMapper.insert(busDriver);
	}

	
	@Override
	public void modify(BusDriverModel busDriver) throws Exception {
		// TODO Auto-generated method stub
		busDriverMapper.update(busDriver);
	}

	@Override
	public void delete(BusDriverModel busDriver) throws Exception {
		// TODO Auto-generated method stub
		busDriverMapper.delete(busDriver);
	}

	@Override
	public BusDriverModel get(String driverID) throws Exception {
		return busDriverMapper.select(driverID);
	}

	@Override
	public List<BusDriverModel> getListByAll() throws Exception {
		return busDriverMapper.selectListByAll();
	}

	@Override
	public List<BusDriverModel> getListByAllWithPage(int rows, int page) throws Exception {
		return busDriverMapper.selectListByAllWithPage(new RowBounds(rows*(page-1), rows));
	}

	@Override
	public int getCountByAll() throws Exception {
		return busDriverMapper.selectCountByAll();
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
	public boolean checkCanDelete(String driverID) throws Exception {
		return true;
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
				String dName = c0.getStringCellValue();
				System.out.println("0");
				
				Cell c1 = row.getCell(1);
				String sex = c1.getStringCellValue();
				
				Cell c2 = row.getCell(2);
				Integer age = (int)c2.getNumericCellValue();
				
				Cell c3 = row.getCell(3);
				Date birthday = c3.getDateCellValue();
				
				Cell c4 = row.getCell(4);
				String dcard = c4.getStringCellValue();
				
				Cell c5 = row.getCell(5);
				String dcore = c5.getStringCellValue();
				
				Cell c6 = row.getCell(6);
				Date joinDate = c6.getDateCellValue();
				
				Cell c7 = row.getCell(7);
				Date leaveDate = c7.getDateCellValue();
				
				BusDriverModel busDriver = new BusDriverModel();
				busDriver.setdname(dName);
				busDriver.setSex(sex);
				busDriver.setAge(age);
				busDriver.setBirthday(birthday);
				busDriver.setDcard(dcard);
				busDriver.setDcore(dcore);
				busDriver.setJoinDate(joinDate);
				busDriver.setLeaveDate(leaveDate);
				
				this.add(busDriver);
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
		//取得所有的司机列表
		List<BusDriverModel> list = busDriverMapper.selectListByAll();
		for (BusDriverModel busDriver : list) {
			System.out.println(busDriver);
		}
		int i = 1;
		for(BusDriverModel busDriver:list){
			Row row = sheet.createRow(i);
			Cell c0 = row.createCell(0);
			c0.setCellValue(busDriver.getdname());
			Cell c1 = row.createCell(1);
			c1.setCellValue(busDriver.getSex());
			Cell c2 = row.createCell(2);
			c2.setCellValue(busDriver.getAge());
			Cell c3 = row.createCell(3);
			c3.setCellValue(busDriver.getBirthday());
			Cell c4 = row.createCell(4);
			c4.setCellValue(busDriver.getDcard());
			Cell c5 = row.createCell(5);
			c5.setCellValue(busDriver.getDcore());
			Cell c6 = row.createCell(6);
			c6.setCellValue(busDriver.getJoinDate());
			Cell c7 = row.createCell(7);
			c7.setCellValue(busDriver.getLeaveDate());
			i++;
		}
		FileOutputStream fos = new FileOutputStream(exportFile);
		wb.write(fos);
		fos.close();
		wb.close();

	}



}
