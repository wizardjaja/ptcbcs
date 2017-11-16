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
import com.wizard.ptcbcs.baseinfo.mapper.IBusTypeMapper;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IBusTypeService;
/**
 * 车辆类型service实现类
 * @author wizard
 *
 */
@Service
public class BusTypeServiceImpl implements IBusTypeService {
	private IBusTypeMapper busTypeMapper;
	@Autowired
	public void setBusTypeMapper(IBusTypeMapper busTypeMapper) {
		this.busTypeMapper = busTypeMapper;
	}

	@Override
	public void add(BusTypeModel busType) throws Exception {
		busTypeMapper.insert(busType);
	}

	@Override
	public void modify(BusTypeModel busType) throws Exception {
		busTypeMapper.update(busType);
	}

	@Override
	public void delete(BusTypeModel busType) throws Exception {
		busTypeMapper.delete(busType);
	}

	@Override
	public BusTypeModel get(int typeNo) throws Exception {
		return busTypeMapper.select(typeNo);
	}

	@Override
	public List<BusTypeModel> getListByAll() throws Exception {
		return busTypeMapper.selectListByAll();
	}

	@Override
	public List<BusTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		return busTypeMapper.selectListByAllWithPage(new RowBounds(rows*(page-1), rows));
	}

	@Override
	public int getCountByAll() throws Exception {
		return busTypeMapper.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int count = this.getCountByAll();
		if(count%rows==0) 
			return count/rows;
		else 
			return count/rows + 1;
	}

	@Override
	public boolean checkCanDelete(int typeNo) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean checkNameExist(String typeName) throws Exception {
		boolean result=false;
		List<BusTypeModel> list=this.getListByAll();
		for(BusTypeModel busType:list){
			if(busType!=null&&busType.getTypeName()!=null&&busType.getTypeName().equals(typeName)){
				result=true;
				break;
			}
		}
		
		return result;
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
				Cell c0 = row.getCell(1);
				String typeName = c0.getStringCellValue();
				System.out.println("1");
				BusTypeModel busType = new BusTypeModel();
				busType.setTypeName(typeName);
				this.add(busType);
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
		//取得所有的车辆类型列表
		List<BusTypeModel> list = busTypeMapper.selectListByAll();
		for (BusTypeModel busType : list) {
			System.out.println(busType);
		}
		int i = 1;
		for(BusTypeModel busType:list){
			Row row = sheet.createRow(i);
			Cell c0 = row.createCell(0);
			c0.setCellValue(busType.getTypeName());
			i++;
		}
		FileOutputStream fos = new FileOutputStream(exportFile);
		wb.write(fos);
		fos.close();
		wb.close();
  	}

}
