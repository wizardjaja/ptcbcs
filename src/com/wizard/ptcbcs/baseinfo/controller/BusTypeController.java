package com.wizard.ptcbcs.baseinfo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
   	 * 检查此车辆类型能否被删除
   	 * @param typeNo 车辆类型编号
   	 * @return 检查此车辆类型能否被删除的信息
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
	 * 检查车辆类型名称是否存在的信息
	 * @param typeName
	 * @return 检车
	 * @throws Exception
	 */
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam String typeName) throws Exception{
		return !busTypeService.checkNameExist(typeName);
		
	}
	/**
	 * 导入车辆类型的excel文件
	 * @param importfile excel文件
	 * @return 检查车辆类型导入是否成功的信息
	 * @throws Exception
	 */
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 busTypeService.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入车辆类型成功");
		 }
		 else{
			 result.setResult("N");
			 result.setMessage("没有上传导入Excel文件");
		 }
		 return result;
		
	}
	
	/**
	 * 
	 * @param session 会话
	 * @return 响应体
	 * @throws Exception
	 */
	@RequestMapping(value="/exporttoexcel", method=RequestMethod.GET)
	public ResponseEntity<byte[]> exportToExcel(HttpSession session)throws Exception{
		ServletContext application = session.getServletContext();
		String sourcepath = application.getRealPath("/excelexport/bustypeexport.xlsx");
		String exportfilepath = application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		busTypeService.exportToExcel(new File(sourcepath), new File(exportfilepath));
		
		String mainType="application";
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("车辆类型导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
		FileInputStream fis = new FileInputStream(exportfilepath);
		byte[] data = new byte[fis.available()];
		fis.read(data, 0, data.length);
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(new MediaType(mainType,subType));
		File excelFile = new File(exportfilepath);
		excelFile.delete();
		return new ResponseEntity<byte[]>(data,headers,HttpStatus.CREATED);
	}
   
}
