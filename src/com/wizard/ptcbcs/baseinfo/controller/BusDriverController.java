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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wizard.ptcbcs.baseinfo.model.BusDriverModel;
import com.wizard.ptcbcs.baseinfo.service.IBusDriverService;
import com.wizard.ptcbcs.result.ResultInfo;
import com.wizard.ptcbcs.result.ResultMessage;

/**
 * 司机控制器类
 * @author wizard
 *
 */
@RestController
@RequestMapping(value="/busdriver")
public class BusDriverController {
   private IBusDriverService busDriverService=null;
   @Autowired
   public void setBusDriverService(IBusDriverService busDriverService) {
		this.busDriverService = busDriverService;
	}

   /**
    * 增加司机
    * @param busType 司机类
    * @return ResultMessage 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/add",method=RequestMethod.POST)
   public ResultMessage add(BusDriverModel busDriver) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("增加司机失败");
	   busDriverService.add(busDriver);
	   result.setResult("Y");
	   result.setMessage("增加司机成功");
	   return result;
	   
	  
   }
   
   








/**
 * 修改司机
 * @param busType 司机类
 * @return	消息响应类
 * @throws Exception
 */
@RequestMapping(value="/modify",method=RequestMethod.POST)
   public ResultMessage modify(BusDriverModel busDriver) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("修改司机失败");
	   busDriverService.modify(busDriver);
	   result.setResult("Y");
	   result.setMessage("修改司机成功");
	   return result;
	   
	  
   }
   /**
    * 删除司机
    * @param busType 司机类
    * @return 消息响应类
    * @throws Exception
    */
   @RequestMapping(value="/delete",method=RequestMethod.POST)
   public ResultMessage delete(BusDriverModel busDriver) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   result.setResult("N");
	   result.setMessage("删除司机失败");
	   busDriverService.delete(busDriver);
	   result.setResult("Y");
	   result.setMessage("删除司机成功");
	   return result;
   }
   /**
    * 按司机编号得到司机对象
    * @param typeNo 司机编号
    * @return 司机对象
    * @throws Exception
    */
   @RequestMapping(value="/get",method=RequestMethod.GET)
   public BusDriverModel get(@RequestParam String driverID) throws Exception
   {
	   return busDriverService.get(driverID);
   }
   /**
    * 取得所有司机列表，不分页
    * @return 取得所有司机列表，不分页
    * @throws Exception
    */
   @RequestMapping(value="/list/all",method=RequestMethod.GET)
   public List<BusDriverModel> getListByAll() throws Exception
   {
	   return busDriverService.getListByAll();
   }
   /**
    * 取得所有司机列表，分页方式
    * @param rows 每页记录数
    * @param page 当前页
    * @return 取得所有司机列表，分页方式
    * @throws Exception
    */
   @RequestMapping(value="/list/page",method=RequestMethod.GET)
   public ResultInfo<BusDriverModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
   {
	   ResultInfo<BusDriverModel> result=new ResultInfo<BusDriverModel>();
	   result.setCount(busDriverService.getCountByAll());
	   result.setPageCount(busDriverService.getPageCountByAll(rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(busDriverService.getListByAllWithPage(rows, page));
	   
	   return result;
   }
   	/**
   	 * 检查此司机能否被删除
   	 * @param typeNo 司机编号
   	 * @return 检查此司机能否被删除的信息
   	 * @throws Exception
   	 */
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
	public ResultMessage checkCanDelete(@RequestParam String driverID) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(busDriverService.checkCanDelete(driverID)){
			result.setResult("Y");
			result.setMessage("此司机可以删除");
		}
		else{
			result.setResult("N");
			result.setMessage("此司机不能删除");
		}
		return result;
	}
	/**
	 * 导入司机的excel文件
	 * @param importfile excel文件
	 * @return 检查司机导入是否成功的信息
	 * @throws Exception
	 */
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 busDriverService.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入司机成功");
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
		String sourcepath = application.getRealPath("/excelexport/busdriverexport.xlsx");
		String exportfilepath = application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		busDriverService.exportToExcel(new File(sourcepath), new File(exportfilepath));
		
		String mainType="application";
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("司机导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
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
