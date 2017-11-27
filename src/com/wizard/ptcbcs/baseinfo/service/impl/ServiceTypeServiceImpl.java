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
import com.wizard.ptcbcs.baseinfo.mapper.IServiceTypeMapper;
import com.wizard.ptcbcs.baseinfo.model.ServiceTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IServiceTypeService;
/**
 * 维修类型service实现类
 * @author wizard
 *
 */
@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {
	private IServiceTypeMapper serviceTypeMapper;
	
	@Autowired
	public void setServiceTypeMapper(IServiceTypeMapper serviceTypeMapper) {
		this.serviceTypeMapper = serviceTypeMapper;
	}

	@Override
	public void add(ServiceTypeModel serviceType) throws Exception {
		serviceTypeMapper.insert(serviceType);
	}

	@Override
	public void modify(ServiceTypeModel serviceType) throws Exception {
		// TODO Auto-generated method stub
		serviceTypeMapper.update(serviceType);
	}
	@Override
	public void modifyWithPhoto(ServiceTypeModel serviceType) throws Exception {
		serviceTypeMapper.updateWithPhoto(serviceType);
		
	}

	@Override
	public void modifyForDeletePhoto(ServiceTypeModel serviceType) throws Exception {
		serviceTypeMapper.updateForDeletePhoto(serviceType);
	}

	@Override
	public void delete(ServiceTypeModel serviceType) throws Exception {
		// TODO Auto-generated method stub
		serviceTypeMapper.delete(serviceType);
	}

	@Override
	public ServiceTypeModel get(int typeNo) throws Exception {
		return serviceTypeMapper.select(typeNo);
	}

	@Override
	public List<ServiceTypeModel> getListByAll() throws Exception {
		return serviceTypeMapper.selectListByAll();
	}

	@Override
	public List<ServiceTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		return serviceTypeMapper.selectListByAllWithPage(new RowBounds(rows*(page-1), rows));
	}

	@Override
	public int getCountByAll() throws Exception {
		return serviceTypeMapper.selectCountByAll();
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
	public boolean checkCanDelete(int typeNo) throws Exception {
		return true;
	}

	@Override
	public boolean checkNameExist(String typeName) throws Exception {
		boolean result=false;
		List<ServiceTypeModel> list=this.getListByAll();
		for(ServiceTypeModel serviceType:list){
			if(serviceType!=null&&serviceType.getTypeName()!=null&&serviceType.getTypeName().equals(typeName)){
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
				ServiceTypeModel serviceType = new ServiceTypeModel();
				Cell c0 = row.getCell(0);
				String typeName = c0.getStringCellValue();
				serviceType.setTypeName(typeName);
				System.out.println("0");
				Cell c1 = row.getCell(1);
				if(c1!=null) {
				String typeDesc = c1.getStringCellValue();
				serviceType.setTypeDesc(typeDesc);
				System.out.println("1");
				}else {
					String typeDesc = " ";
					serviceType.setTypeDesc(typeDesc);
				}
				this.add(serviceType);
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
		//取得所有的维修类型列表
		List<ServiceTypeModel> list = serviceTypeMapper.selectListByAll();
		for (ServiceTypeModel busFactoty : list) {
			System.out.println(busFactoty);
		}
		int i = 1;
		for(ServiceTypeModel busFactoty:list){
			Row row = sheet.createRow(i);
			Cell c0 = row.createCell(0);
			c0.setCellValue(busFactoty.getTypeName());
			Cell c1 = row.createCell(1);
			c1.setCellValue(busFactoty.getTypeDesc());
			i++;
		}
		FileOutputStream fos = new FileOutputStream(exportFile);
		wb.write(fos);
		fos.close();
		wb.close();

	}

}
