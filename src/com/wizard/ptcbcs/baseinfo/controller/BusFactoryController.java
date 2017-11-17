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

import com.wizard.ptcbcs.baseinfo.model.BusFactoryModel;
import com.wizard.ptcbcs.baseinfo.model.BusTypeModel;
import com.wizard.ptcbcs.baseinfo.service.IBusFactoryService;
import com.wizard.ptcbcs.result.ResultInfo;
import com.wizard.ptcbcs.result.ResultMessage;

/**
 * 车辆厂家控制器类
 * @author wizard
 *
 */
@RestController
@RequestMapping(value="/busfactory")
public class BusFactoryController {
   private IBusFactoryService busFactoryService=null;
   @Autowired
   public void setBusFactoryService(IBusFactoryService busFactoryService) {
		this.busFactoryService = busFactoryService;
	}
   /**
    * 增加车辆厂家
    * @param busType 车辆厂家类
    * @return ResultMessage 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/add",method=RequestMethod.POST)
   public ResultMessage add(BusFactoryModel busFactory) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("增加车辆厂家失败");
	   busFactoryService.add(busFactory);
	   result.setResult("Y");
	   result.setMessage("增加车辆厂家成功");
	   return result;
	   
	  
   }
   
   



/**
 * 修改车辆厂家
 * @param busType 车辆厂家类
 * @return	消息响应类
 * @throws Exception
 */
@RequestMapping(value="/modify",method=RequestMethod.POST)
   public ResultMessage modify(BusFactoryModel busFactory) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("修改车辆厂家失败");
	   busFactoryService.modify(busFactory);
	   result.setResult("Y");
	   result.setMessage("修改车辆厂家成功");
	   return result;
	   
	  
   }
   /**
    * 删除车辆厂家
    * @param busType 车辆厂家类
    * @return 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/delete",method=RequestMethod.POST)
   public ResultMessage delete(BusFactoryModel busFactory) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("删除车辆厂家失败");
	   busFactoryService.delete(busFactory);
	   result.setResult("Y");
	   result.setMessage("删除车辆厂家成功");
	   return result;
   }
   /**
    * 按车辆厂家编号得到车辆厂家对象
    * @param typeNo 车辆厂家编号
    * @return 车辆厂家对象
    * @throws Exception
    */
   @RequestMapping(value="/get",method=RequestMethod.GET)
   public BusFactoryModel get(@RequestParam int factoryNo) throws Exception
   {
	   return busFactoryService.get(factoryNo);
   }
   /**
    * 取得所有车辆厂家列表，不分页
    * @return 取得所有车辆厂家列表，不分页
    * @throws Exception
    */
   @RequestMapping(value="/list/all",method=RequestMethod.GET)
   public List<BusFactoryModel> getListByAll() throws Exception
   {
	   return busFactoryService.getListByAll();
   }
   /**
    * 取得所有车辆厂家列表，分页方式
    * @param rows 每页记录数
    * @param page 当前页
    * @return 取得所有车辆厂家列表，分页方式
    * @throws Exception
    */
   @RequestMapping(value="/list/page",method=RequestMethod.GET)
   public ResultInfo<BusFactoryModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
   {
	   ResultInfo<BusFactoryModel> result=new ResultInfo<BusFactoryModel>();
	   result.setCount(busFactoryService.getCountByAll());
	   result.setPageCount(busFactoryService.getPageCountByAll(rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(busFactoryService.getListByAllWithPage(rows, page));
	   
	   return result;
   }
   	/**
   	 * 检查此车辆厂家能否被删除
   	 * @param typeNo 车辆厂家编号
   	 * @return 检查此车辆厂家能否被删除的信息
   	 * @throws Exception
   	 */
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
	public ResultMessage checkCanDelete(@RequestParam int factoryNo) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(busFactoryService.checkCanDelete(factoryNo)){
			result.setResult("Y");
			result.setMessage("此车辆厂家可以删除");
		}
		else{
			result.setResult("N");
			result.setMessage("此车辆厂家不能删除");
		}
		return result;
	}
	/**
	 * 检查车辆厂家名称是否存在的信息
	 * @param typeName
	 * @return 检车
	 * @throws Exception
	 */
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam String factoryName) throws Exception{
		return !busFactoryService.checkNameExist(factoryName);
		
	}
	/**
	 * 导入车辆厂家的excel文件
	 * @param importfile excel文件
	 * @return 检查车辆厂家导入是否成功的信息
	 * @throws Exception
	 */
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 busFactoryService.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入车辆厂家成功");
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
		String sourcepath = application.getRealPath("/excelexport/busfactoryexport.xlsx");
		String exportfilepath = application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		busFactoryService.exportToExcel(new File(sourcepath), new File(exportfilepath));
		
		String mainType="application";
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("车辆厂家导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
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
