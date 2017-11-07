package com.wizard.ptcbcs.baseinfo.service.impl;

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
	public boolean checkCanDelete(int typeeNo) throws Exception {
		// TODO Auto-generated method stub
		return false;
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

}
