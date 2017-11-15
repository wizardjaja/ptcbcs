package com.wizard.ptcbcs.baseinfo.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
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
						Cell c0 = row.getCell(0);
						int homeFeeNo = (int) c0.getNumericCellValue();
						System.out.println(homeFeeNo);
						Cell c1 = row.getCell(1);
						int payTypes = (int) c1.getNumericCellValue();
						System.out.println(payTypes);
						Cell c2 = row.getCell(2);
						double payAmount = c2.getNumericCellValue();
						System.out.println(payAmount);
						Cell c3 = row.getCell(3);
						Date payDate = c3.getDateCellValue();
						System.out.println(c3.getDateCellValue());
						Cell c4 = row.getCell(4);
						String payPerson = c4.getStringCellValue();
						System.out.println("4");
						Cell c5 = row.getCell(5);
						String mobile = c5.getStringCellValue();				
						Cell c6 = row.getCell(6);
						String invoiceCode = c6.getStringCellValue();
						Cell c7 = row.getCell(7);
						String payNoteCode = c7.getStringCellValue();
						Cell c8 = row.getCell(8);
						String payDesc = c8.getStringCellValue();
						HomeCustomerFeePayRecordModel hcfpr = new HomeCustomerFeePayRecordModel();
						hcfpr.setHomeFeeNo(homeFeeNo);
						PayTypeModel payType = new PayTypeModel();
						payType.setNo(payTypes);
						hcfpr.setPayType(payType);
						hcfpr.setPayAmount(payAmount);
						hcfpr.setPayDate(payDate);
						hcfpr.setPayPerson(payPerson);
						hcfpr.setMobile(mobile);
						hcfpr.setInvoiceCode(invoiceCode);
						hcfpr.setPayNoteCode(payNoteCode);
						hcfpr.setPayDesc(payDesc);
						this.add(hcfpr);
					}
				}
		
	}

	@Override
	public void exportToExcel(File source, File exportFile) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
