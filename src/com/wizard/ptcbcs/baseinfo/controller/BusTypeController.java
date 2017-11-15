package com.wizard.ptcbcs.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IBusTypeService;
import com.wizard.ptcbcs.result.ResultInfo;
import com.wizard.ptcbcs.result.ResultMessage;

/**
 * 车辆类型控制器类
 * @author wizard
 *
 */
@RestController
@RequestMapping(value="/bustype")
public class BusTypeController {
   private IBusTypeService busTypeService=null;
   @Autowired
   public void setBusTypeService(IBusTypeService busTypeService) {
		this.busTypeService = busTypeService;
	}
   /**
    * 增加车辆类型
    * @param busType 车辆类型类
    * @return ResultMessage 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/add",method=RequestMethod.POST)
   public ResultMessage add(BusTypeModel busType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busTypeService.add(busType);
	   result.setResult("Y");
	   result.setMessage("增加车辆类型成功");
	   return result;
	   
	  
   }
   
   
/**
 * 修改车辆类型
 * @param busType 车辆类型类
 * @return	消息响应类
 * @throws Exception
 */
@RequestMapping(value="/modify",method=RequestMethod.POST)
   public ResultMessage modify(BusTypeModel busType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busTypeService.modify(busType);
	   result.setResult("Y");
	   result.setMessage("修改车辆类型成功");
	   return result;
	   
	  
   }
   /**
    * 删除车辆类型
    * @param busType 车辆类型类
    * @return 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/delete",method=RequestMethod.POST)
   public ResultMessage delete(BusTypeModel busType) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busTypeService.delete(busType);
	   result.setResult("Y");
	   result.setMessage("删除车辆类型成功");
	   return result;
   }
   /**
    * 按车辆类型编号得到车辆类型对象
    * @param typeNo 车辆类型编号
    * @return 车辆类型对象
    * @throws Exception
    */
   @RequestMapping(value="/get",method=RequestMethod.GET)
   public BusTypeModel get(@RequestParam int typeNo) throws Exception
   {
	   return busTypeService.get(typeNo);
   }
   /**
    * 取得所有车辆类型列表，不分页
    * @return 取得所有车辆类型列表，不分页
    * @throws Exception
    */
   @RequestMapping(value="/list/all",method=RequestMethod.GET)
   public List<BusTypeModel> getListByAll() throws Exception
   {
	   return busTypeService.getListByAll();
   }
   /**
    * 取得所有车辆类型列表，分页方式
    * @param rows 每页记录数
    * @param page 当前页
    * @return 取得所有车辆类型列表，分页方式
    * @throws Exception
    */
   @RequestMapping(value="/list/page",method=RequestMethod.GET)
   public ResultInfo<BusTypeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
   {
	   ResultInfo<BusTypeModel> result=new ResultInfo<BusTypeModel>();
	   result.setCount(busTypeService.getCountByAll());
	   result.setPageCount(busTypeService.getPageCountByAll(rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(busTypeService.getListByAllWithPage(rows, page));
	   
	   return result;
   }
   	/**
   	 * 检查此建筑类型能否被删除
   	 * @param typeNo 车辆类型编号
   	 * @return 检查此建筑类型能否被删除
   	 * @throws Exception
   	 */
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
	public ResultMessage checkCanDelete(@RequestParam int typeNo) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(busTypeService.checkCanDelete(typeNo)){
			result.setResult("Y");
			result.setMessage("此车辆类型可以删除");
		}
		else{
			result.setResult("N");
			result.setMessage("此车辆类型不能删除");
		}
		return result;
	}
	/**
	 * 检查车辆类型名称是否存在
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam String typeName) throws Exception{
		return !busTypeService.checkNameExist(typeName);
		
	}
   
}
